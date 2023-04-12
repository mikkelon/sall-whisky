package application.controller;

import application.model.*;
import application.model.lager.Fad;
import application.model.produktion.*;
import com.sun.source.tree.Tree;
import storage.Storage;

import java.time.LocalDate;
import java.util.*;

/**
 * ControllerForProduktion-klassen håndterer forretningslogik for alt der har med destilleriets produktion at gøre og binder GUI sammen med modellen og storage-laget.
 */
public class ControllerForProduktion {
    private Storage storage;
    private static ControllerForProduktion controllerForProduktion;

    private ControllerForProduktion() {
        storage = Storage.getStorage();
    }

    /**
     * Opretter ny instans af controlleren eller returnerer den eksisterende
     * @return controlleren
     */
    public static ControllerForProduktion getController() {
        if (controllerForProduktion == null) {
            controllerForProduktion = new ControllerForProduktion();
        }
        return controllerForProduktion;
    }

    /**
     * Opretter en ny instans af controlleren med formål at teste denne
     * @return en ny instans af controlleren
     */
    public static ControllerForProduktion getTestController() {
        return new ControllerForProduktion();
    }

    /**
     * Opretter storage på ny med formål at teste denne.
     */
    public void clearStorage() {
        storage.clearStorage();
    }

    /**
     * Returnerer alle destillater
     * @return alle destillater
     */
    public HashSet<Destillat> getDestillater() {
        return storage.getDestillater();
    }

    /**
     * Opretter et nyt destillat.
     * @param newMakeNr           destillatets unikke nummer
     * @param medarbejder         navnet på medarbejderen der har destilleret
     * @param alkoholProcent      alkoholprocenten i destillatet
     * @param antalDestilleringer antallet af destilleringer
     * @param startDato           startdatoen for destilleringen
     * @param slutDato            slutdatoen for destilleringen
     * @param mængdeILiter        mængden af destillat i liter
     * @param kommentar           kommentar til destillatet
     * @param rygeMateriale       evt rygemateriale der er brugt
     * @return det oprettede destillat'
     * @throws RuntimeException hvis et destillat med de givne newMakeNr allerede findes
     * @Pre: newMakeNr != null<br />medarbejder != null<br />0 <= alkoholprocent <= 100<br />antalDestilleringer > 0<br />startDato != null<br />slutDato != null<br />mængdeILiter > 0<br />rygeMateriale != null
     */
    public Destillat createDestillat(String newMakeNr, String medarbejder, double alkoholProcent,
                                     int antalDestilleringer, LocalDate startDato, LocalDate slutDato,
                                     double mængdeILiter, String kommentar, RygeMateriale rygeMateriale) {
        if (getDestillat(newMakeNr) != null) {
            throw new RuntimeException("Destillat med det givne newMakeNr findes allerede.");
        }
        Destillat destillat = new Destillat(newMakeNr, medarbejder, alkoholProcent, antalDestilleringer, startDato, slutDato, mængdeILiter, kommentar, rygeMateriale);
        storage.addDestillat(destillat);
        return destillat;
    }

    /**
     * Returnerer det specifikke destillat, hvis det findes
     * @param newMakeNr destillatets unikke nummer
     * @return det specifikke destillat ellers null
     * @Pre: newMakeNr != null
     */
    private Object getDestillat(String newMakeNr) {
        for (Destillat destillat : storage.getDestillater()) {
            if (destillat.getNewMakeNr().equals(newMakeNr)) {
                return destillat;
            }
        }
        return null;
    }

    /**
     * Fjerner det specifikke destillat fra lageret
     * @param destillat destillatet der skal fjernes
     * @Pre: destillat != null
     */
    public void removeDestillat(Destillat destillat) {
        if (!destillat.getPåfyldninger().isEmpty()) {
            throw new RuntimeException("Destillatet er påført fad og kan derfor ikke fjernes.");
        }
        storage.removeDestillat(destillat);
    }

    /**
     * Opretter en ny påfyldning
     * @param destillat       destillatet der skal fyldes på fadet
     * @param fad             fadet hvorpå påfyldningen skal foregå
     * @param påfyldtAf       navnet på den person der har påfyldt fadet
     * @param mængdeILiter    mængden af destillat i fadet i liter
     * @param påfyldningsDato dato for påfyldning
     * @return den oprettede påfyldning
     * @throws RuntimeException hvis påfyldningen er større end destillatets resterende mængde eller fadets resterende plads
     * @Pre: destillat != null<br />fad != null<br />påfyldtAf != null<br />mængdeILiter > 0<br />påfyldningsDato != null
     */
    public Påfyldning createPåfyldning(Destillat destillat, Fad fad, String påfyldtAf,
                                       double mængdeILiter, LocalDate påfyldningsDato) {
        if (mængdeILiter > destillat.resterendeMængdeILiter()) {
            throw new RuntimeException("Påfyldningen er større end destillatets resterende mængde.");
        } else if (mængdeILiter > fad.resterendePladsILiter()) {
            throw new RuntimeException("Påfyldningen er større end fadets resterende mængde.");
        }
        Påfyldning påfyldning = fad.påfyld(destillat, mængdeILiter, påfyldtAf, påfyldningsDato);
        return påfyldning;
    }

    /**
     * Opretter en aftapning.
     * @param aftappetAf     hvem det er aftappet af
     * @param mængdeILiter   hvor mange liter der er aftappet
     * @param aftapningsDato hvilket dato det er aftappet
     * @param fad            hvilket fad der er aftappet fra
     * @return den oprettede aftapning
     * @throws RuntimeException hvis fadet er tomt, fadets indhold ikke er modnet, aftapningen er større end fadets resterende mængde eller fadets indhold ikke har en registreret alkoholprocent efter modning
     * @Pre: aftappetAf != null<br />mængdeILiter > 0<br />aftapningsDato != null<br />fad != null<br />whisky != null
     */
    public Aftapning createAftapning(String aftappetAf, double mængdeILiter, LocalDate aftapningsDato, Fad fad) {
        if (fad.getFadIndhold() == null) {
            throw new RuntimeException("Fadet er tomt.");
        }
        if (fad.getFadIndhold().getAlkoholProcentEfterModning() == -1) {
            throw new RuntimeException("Fadets indhold skal have en registreret alkoholprocent efter modning");
        }
        if (!fad.getFadIndhold().isModnet()) {
            throw new RuntimeException("Fadets indhold er ikke modnet");
        }
        if (mængdeILiter > fad.getFadIndhold().getMængde()) {
            throw new RuntimeException("Aftapningen er større end fadets resterende mængde");
        }
        Aftapning aftapning = fad.aftap(aftappetAf, mængdeILiter, aftapningsDato);
        return aftapning;
    }

    /**
     * Fjerner en aftapning fra dets fad.
     * Anvendes når en aftapning fravælges i GUI'en.
     * Aftapning må ikke være tilknyttet en whisky.
     * <pre>
     *     Pre: aftapning != null, aftapning.getWhisky == null
     * </pre>
     * @param aftapning
     */
    public void removeAftapning(Aftapning aftapning) {
        Fad fad = aftapning.getFadIndhold().getFad();
        if (fad.getFadIndhold() == null) {
            fad.setFadIndhold(aftapning.getFadIndhold());
            fad.removeFromFadIndholdHistorik(aftapning.getFadIndhold());
        }
        aftapning.getFadIndhold().removeAftapning(aftapning);
    }

    /**
     * Returnerer alle maltbatches.
     * @return alle maltbatches
     */
    public Set<Maltbatch> getMaltbatches(){
        return storage.getMaltbatches();
    }

    /**
     * Opretter et maltbatch
     * @param kornsort  hvilken kornsort maltbatchen kommer fra
     * @param mark      hvilken mark maltbatchen kommer fra
     * @param gård      hvilken gård maltbatchen kommer fra
     * @param dyrketAf  hvem der har dyrket maltbatchen
     * @param økologisk om maltbatchen er økologisk
     * @return det oprettede maltbatch
     * @Pre: kornsort != null<br />mark != null<br />gård != null<br />dyrketAf != null<br />økologisk != null
     */
    public Maltbatch createMaltbatch(String kornsort, String mark, String gård, String dyrketAf, boolean økologisk) {
        Maltbatch maltbatch = new Maltbatch(kornsort, mark, gård, dyrketAf, økologisk);
        storage.addMaltbatch(maltbatch);
        return maltbatch;
    }

    /**
     * Fjerner et maltbatch
     * @param maltbatch fjernes
     * @throws RuntimeException hvis maltbatchen er tilknyttet et destillat
     * @Pre: maltbatch != null
     */
   public void removeMaltbatch(Maltbatch maltbatch){
        if(maltbatch.getAntalDestillater() != 0){
            throw new RuntimeException("Maltbatch kan ikke slettes, når der er destillater tilknyttet.");
        }
        storage.removeMaltbatch(maltbatch);
   }

   /**
    * Tilføjer et maltbatch til et destillat
    * @param maltbatch maltbatch der tilføjes
    * @param destillat destillat det tilføjes til
    * @Pre: maltbatch != null<br />destillat != null
    */
   public void addMaltbatchToDestillat(Destillat destillat, Maltbatch maltbatch) {
       destillat.addMaltbatch(maltbatch);
   }

    /**
     * Opretter en whisky
     * pre: aftapninger.size() > 0, alkoholprocent > 0, betegnelse != null, mængdeVandILiter > 0, vandAfstamning != null, tekstBeskrivelse != null
     * @param aftapninger      hvilke aftapninger der er foretaget
     * @param mængdeVandILiter mængden af vand i whiskyen
     * @param vandAfstamning   hvor vandet kommer fra
     * @param tekstBeskrivelse tekstbeskrivelse om whiskyen
     * @return den oprettede whisky
     * @Pre: aftapninger.size() > 0<br />0 <= alkoholprocent <= 100<br />betegnelse != null<br />mængdeVandILiter > 0<br />vandAfstamning != null<br />tekstBeskrivelse != null
     */
    public Whisky createWhisky(Set<Aftapning> aftapninger, double mængdeVandILiter, String vandAfstamning, String tekstBeskrivelse) {
        Whisky whisky = new Whisky(mængdeVandILiter, vandAfstamning, tekstBeskrivelse);
        for (Aftapning a : aftapninger) {
            whisky.addAftapning(a);
            a.setWhisky(whisky);
        }
        storage.addWhisky(whisky);
        Whisky.tælAntalWhiskyOp();
        return whisky;
   }

    /**
     * Returnerer betegnelsen for en whisky, udregnet ud fra aftapninger og mængden af vand der bruges til at lave whiskyen
     * Anvendes til at udregne betegnelsen inden der oprettes et egentligt objekt af en whisky.
     * <pre>
     *     Pre: aftapninger.size() > 0, mængdeVandILiter >= 0
     * </pre>
     * @param aftapninger
     * @param mængdeVandILiter
     * @return
     */
   public Betegnelse udregnBetegnelse(Set<Aftapning> aftapninger, double mængdeVandILiter) {
       Whisky whisky = new Whisky(mængdeVandILiter, "", "");
         for (Aftapning a : aftapninger) {
              whisky.addAftapning(a);
         }
         return whisky.getBetegnelse();
   }

    /**
     * Returnerer alle whiskyerne
     * @return alle whiskyerne
     */
   public Set<Whisky> getWhiskyer(){
        Set<Whisky> whiskyer = new TreeSet<>(Comparator.comparingInt(Whisky::getWhiskyNr));
        whiskyer.addAll(storage.getWhiskyer());
        return whiskyer;
   }

    /**
     * sætter alkoholprocenten efter modning på et fadindhold
     * @param fadIndhold hvilket fadindhold der skal sættes alkoholprocent på
     * @param alkoholprocent alkoholprocenten der skal sættes
     * @throws RuntimeException hvis alkoholprocenten allerede er sat
     * @Pre: fadIndhold != null<br />0 <= alkoholprocent <= 100
     */
    public void setAlkoholprocentEfterModning(FadIndhold fadIndhold, double alkoholprocent) {
        if (fadIndhold.getAlkoholProcentEfterModning() == -1) {
            fadIndhold.setAlkoholProcentEfterModning(alkoholprocent);
        } else {
            throw new RuntimeException("Alkoholprocenten er allerede sat.");
        }
    }

    /**
     * Opretter en omhældning.
     * @param omhældtAf hvem der har omhældt
     * @param mængdeILiter mængden der er omhældt
     * @param omhældningsDato hvornår omhældningen er foretaget
     * @param fraFad hvilket fad der er omhældt fra
     * @param tilFad hvilket fad der er omhældt til
     * @return den oprettede omhældning
     * @throws RuntimeException hvis der omhældes mere end der er i fraFadet eller hvis der omhældes fra og til samme fad
     * @Pre: omhældtAf != null<br />mængdeILiter > 0<br />omhældningsDato != null<br />fraFad != null<br />tilFad != null <br /> fraFad.getFadIndhold() != null
     */
    public Omhældning createOmhældning(String omhældtAf, double mængdeILiter, LocalDate omhældningsDato, Fad fraFad, Fad tilFad) {
        if (mængdeILiter > fraFad.getFadIndhold().getMængde()) {
            throw new RuntimeException("Der kan ikke omhældes mere end der er i fadet.");
        } else if (fraFad == tilFad) {
            throw new RuntimeException("Der kan ikke omhældes fra og til samme fad.");
        } else if (tilFad.getFadIndhold() != null
                && mængdeILiter > tilFad.resterendePladsILiter()) {
            throw new RuntimeException("Der kan ikke omhældes mere end der er plads til i fadet.");
        }

        return fraFad.omhæld(omhældtAf, mængdeILiter, omhældningsDato, tilFad);
    }
}