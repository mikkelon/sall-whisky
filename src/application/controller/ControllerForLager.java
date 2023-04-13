package application.controller;

import application.model.*;
import application.model.lager.*;
import storage.Storage;

import java.util.*;

/**
 * ControllerForLager-klassen håndterer forretningslogik for alt der har med destilleriets lager at gøre og binder GUI sammen med modellen og storage-laget.
 */
public class ControllerForLager {
    private Storage storage;
    private static ControllerForLager controller;

    private ControllerForLager() {
        storage = Storage.getStorage();
    }

    /**
     * Opretter ny instans af controlleren eller returnerer den eksisterende
     * @return controlleren
     */
    public static ControllerForLager getController(){
        if (controller == null){
            controller = new ControllerForLager();
        }
        return controller;
    }

    /**
     * Opretter en ny instans af controlleren med formål at teste denne
     * @return en ny instans af controlleren
     */
    public static ControllerForLager getTestController() {
        return new ControllerForLager();
    }

    /**
     * Opretter storage på ny med formål at teste denne.
     */
    public void clearStorage() {
        storage.clearStorage();
    }

    /**
     * Opretter et nyt lager med en angivet antal hylder
     * @param adresse lagerets adresse
     * @param navn lagerets navn
     * @param kvm lagerets kvm
     * @param antalHylder antal hylder der skal oprettes
     * @return det oprettede lager
     * @Pre: adresse != null<br />navn != null<br />kvm > 0<br />antalHylder >= 0
     */
    public Lager createLagerWithAntalHylder(String adresse, String navn, double kvm, int antalHylder) {
        Lager lager = new Lager(adresse, navn, kvm);
        storage.addLager(lager);

        for (int i = 0; i < antalHylder; i++) {
            lager.createHylde();
        }

        return lager;
    }

    /**
     * Fjerner et lager fra systemet.
     * @param lager lageret
     * @Pre: lager != null
     */
    public void removeLager(Lager lager) {
        for (Hylde hylde : lager.getHylder()) {
            if (!hylde.getFade().isEmpty()) {
                throw new RuntimeException("Lageret kan ikke slettes, når der er fade tilknyttet til en eller flere hylder.");
            }
        }
        storage.removeLager(lager);
    }

    /**
     * Returnerer et HashSet med alle lagre.
     * @return alle lagre
     */
    public Set<Lager> getLagre() {
        return storage.getLagre();
    }

    /**
     * Opretter en ny fadleverandør.
     * @param navn navnet på fadleverandøren
     * @param land landet fadleverandøren kommer fra
     * @return den oprettede fadleverandør
     * @Pre: navn != null<br />land != null
     */
    public FadLeverandør createFadLeverandør(String navn, String land) {
        FadLeverandør fadLeverandør = new FadLeverandør(navn, land);
        storage.addFadLeverandør(fadLeverandør);
        return fadLeverandør;
    }

    /**
     * Fjerner en fadleverandør fra systemet
     * @param fadLeverandør fadleverandøren
     * @throws RuntimeException hvis der er fade tilknyttet fadleverandøren
     * @Pre: fadLeverandør != null
     */
    public void removeFadLeverandør(FadLeverandør fadLeverandør) {
        if (fadLeverandør.getAntalFade() != 0) {
            throw new RuntimeException("Fadleverandøren kan ikke slettes, når der er fade tilknyttet.");
        }
        storage.removeFadLeverandør(fadLeverandør);
    }

    /**
     * Returnerer alle fadleverandører.
     * @return et HashSet med fadleverandører
     */
    public Set<FadLeverandør> getFadLeverandører() {
        return storage.getFadLeverandører();
    }

    /**
     * Opretter en ny hylde i et givent lager.
     * @param lager lageret
     * @return den oprettede hylde
     * @Pre: lager != null
     */
    public Hylde createHylde(Lager lager) {
        Hylde hylde = lager.createHylde();
        return hylde;
    }

    /**
     * Fjerne den specifikke hylde fra et lager.
     * @param hylde hylden der skal fjernes
     * @throws RuntimeException hvis der er fade tilknyttet hylden
     * @Pre: hylde != null
     */
    public void removeHylde(Hylde hylde) {
        if (!hylde.getFade().isEmpty()) {
            throw new RuntimeException("Hylden kan ikke slettes, når der er fade liggende på hylden.");
        }

        hylde.getLager().removeHylde(hylde);
    }

    /**
     * Returnerer alle hylder i alle lagre
     * @return en ArrayList med alle hylder i alle lagre
     */
    public Set<Hylde> getHylder() {
        Set<Hylde> hylder = new TreeSet<>();
        if (!storage.getLagre().isEmpty()) {
            for (Lager lager : storage.getLagre()) {
                hylder.addAll(lager.getHylder());
            }
        }
        return hylder;
    }

    /**
     * Returnerer alle hylder i et givent lager
     * @param lager lageret
     * @return alle hylder i et givent lager
     * @Pre: lager != null
     */
    public Set<Hylde> getHylder(Lager lager) {
        return lager.getHylder();
    }

    /**
     * Opretter et nyt fad
     * @param fadType fadets tidligere indhold, f.eks. sherry, bourbon, osv.
     * @param størrelseILiter fadets volumen i liter
     * @param fadLeverandør fadleverandøren
     * @param hylde hylde hvor fadet skal placeres
     * @return det oprettede fad
     * @Pre: fadType != null<br />størrelseILiter > 0<br />fadLeverandør != null<br />hylde != null
     */
    public Fad createFad(FadType fadType, double størrelseILiter, FadLeverandør fadLeverandør, Hylde hylde) {
        Fad fad = new Fad(fadType, størrelseILiter, fadLeverandør, hylde);
        fadLeverandør.tilføjFad();
        return fad;
    }

    /**
     * Fjerner det specifikke fad fra en hylde
     * @param fad fadet der skal fjernes
     * @Pre: fad != null
     */
    public void fjernFadFraHylde(Fad fad) {
        if (!fad.isEmpty()) {
            throw new RuntimeException("Fadet kan ikke fjernes fra hylden, når der er indhold i fadet.");
        }
        fad.getHylde().removeFad(fad);
    }

    /**
     * Returnerer alle fade fra alle lagre.
     * @return et TreeSet med alle fade fra alle lagre
     */
    public Set<Fad> getAlleFade() {
        Set<Fad> alleFade = new TreeSet<>();
        for (Lager lager : getLagre()) {
            for (Hylde hylde : lager.getHylder()) {
                alleFade.addAll(hylde.getFade());
            }
        }
        return alleFade;
    }

    /**
     * Returnerer alle fade fra et specifikt lager
     * @param lager lageret der skal hentes fade fra
     * @return alle fade fra et specifikt lager
     * @Pre: lager != null
     */
    public Set<Fad> getAlleFade(Lager lager) {
        Set<Fad> alleFade = new TreeSet<>();
        for (Hylde hylde : lager.getHylder()) {
            alleFade.addAll(hylde.getFade());
        }
        return alleFade;
    }

    /**
     * Returnerer alle fade med indhold der har modnet i mere end 3 år
     * @return en ArrayList med fadene
     */
    public Set<Fad> getModneFade() { //TODO: Hvis ikke metoden bliver brugt, skal den fjernes
        Set<Fad> modneFade = new TreeSet<>();
        for (Lager lager : getLagre()) {
            for (Hylde hylde : lager.getHylder()) {
                for (Fad fad : hylde.getFade()) {
                    if (fad.getFadIndhold() != null && fad.getFadIndhold().isModnet()) {
                        modneFade.add(fad);
                    }
                }
            }
        }
        return modneFade;
    }

    /**
     * Returnerer alle fade med indhold der har modnet i mere end 3 år og hvor der ikke er registreret alkoholprocent
     * @return en ArrayList med fadene
     */
    public Set<Fad> getModneFadeUdenRegistreretAlkoholProcent() {
        Set<Fad> modneFade = new TreeSet<>();
        for (Lager lager : getLagre()) {
            for (Hylde hylde : lager.getHylder()) {
                for (Fad fad : hylde.getFade()) {
                    if (fad.getFadIndhold() != null
                            && fad.getFadIndhold().isModnet()
                            && fad.getFadIndhold().getAlkoholProcentEfterModning() == -1) {
                        modneFade.add(fad);
                    }
                }
            }
        }
        return modneFade;
    }

    public Set<Fad> getModneFadeMedRegistreretAlkoholProcent() {
        Set<Fad> modneFade = new TreeSet<>();
        for (Lager lager : getLagre()) {
            for (Hylde hylde : lager.getHylder()) {
                for (Fad fad : hylde.getFade()) {
                    if (fad.getFadIndhold() != null
                            && fad.getFadIndhold().isModnet()
                            && fad.getFadIndhold().getAlkoholProcentEfterModning() != -1) {
                        modneFade.add(fad);
                    }
                }
            }
        }
        return modneFade;
    }

    /**
     * Returnerer alle fade som ikke er tomme.
     * @return alle fade som ikke er tomme
     */
    public Set<Fad> getIkkeTommeFade() {
        Set<Fad> ikkeTommeFade = new TreeSet<>(Comparator.comparingInt(Fad::getFadNr));
        for (Lager lager : getLagre()) {
            for (Hylde hylde : lager.getHylder()) {
                for (Fad fad : hylde.getFade()) {
                    if (fad.getFadIndhold() != null) {
                        ikkeTommeFade.add(fad);
                    }
                }
            }
        }
        return ikkeTommeFade;
    }

    /**
     * Returnerer alle fade som ikke er fyldte.
     * @return alle fade som ikke er fyldte
     */
    public Set<Fad> getIkkeFyldteFade() {
        Set<Fad> ikkeFyldteFade = new TreeSet<>(Comparator.comparingInt(Fad::getFadNr));
        for (Lager lager : getLagre()) {
            for (Hylde hylde : lager.getHylder()) {
                for (Fad fad : hylde.getFade()) {
                    if (fad.getFadIndhold() == null
                            || fad.getFadIndhold().getMængde() != fad.getStørrelseILiter()) {
                        ikkeFyldteFade.add(fad);
                    }
                }
            }
        }
        return ikkeFyldteFade;
    }

    /**
     * Sætter alkoholprocenten for et fad, efter modningen er overstået
     * @param fad fadet
     * @param alkoholProcentEfterModning alkoholprocenten
     * @Pre: fad != null<br />0 <= alkoholProcentEfterModning <= 100
     */
    public void setAlkoholProcentEfterModning(Fad fad, double alkoholProcentEfterModning) {
        fad.getFadIndhold().setAlkoholProcentEfterModning(alkoholProcentEfterModning);
    }

    /**
     * Returnerer alle flasker fra alle lagre.
     * @return alle flasker fra alle lagre
     */
    public Set<Flaske> getFlasker() {
        return storage.getFlasker();
    }
}
