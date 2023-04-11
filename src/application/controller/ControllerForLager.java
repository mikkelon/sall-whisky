package application.controller;

import application.model.*;
import storage.Storage;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.TreeSet;

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
    public HashSet<Lager> getLagre() {
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
    public HashSet<FadLeverandør> getFadLeverandører() {
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
    public ArrayList<Hylde> getHylder() {
        ArrayList<Hylde> hylder = new ArrayList<>();
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
    public ArrayList<Hylde> getHylder(Lager lager) {
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
    public void removeFad(Fad fad) {
        fad.getHylde().removeFad(fad);
        fad.getFadLeverandør().fjernFad();
    }

    /**
     * Returnerer alle fade fra alle lagre.
     * @return et TreeSet med alle fade fra alle lagre
     */
    public TreeSet<Fad> getAlleFade() {
        TreeSet<Fad> alleFade = new TreeSet<>((a,b) -> a.getFadNr() - b.getFadNr());
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
    public TreeSet<Fad> getAlleFade(Lager lager) {
        TreeSet<Fad> alleFade = new TreeSet<>((a,b) -> a.getFadNr() - b.getFadNr());
        for (Hylde hylde : lager.getHylder()) {
            alleFade.addAll(hylde.getFade());
        }
        return alleFade;
    }

    /**
     * Returnerer alle fade med indhold der har modnet i mere end 3 år
     * @return en ArrayList med fadene
     */
    public ArrayList<Fad> getModneFade() { //TODO: Hvis ikke metoden bliver brugt, skal den fjernes
        ArrayList<Fad> modneFade = new ArrayList<>();
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
    public ArrayList<Fad> getModneFadeUdenRegistreretAlkoholProcent() {
        ArrayList<Fad> modneFade = new ArrayList<>();
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

    public ArrayList<Fad> getModneFadeMedRegistreretAlkoholProcent() {
        ArrayList<Fad> modneFade = new ArrayList<>();
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
    public ArrayList<Fad> getIkkeTommeFade() {
        ArrayList<Fad> ikkeTommeFade = new ArrayList<>();
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
     * Sætter alkoholprocenten for et fad, efter modningen er overstået
     * @param fad fadet
     * @param alkoholProcentEfterModning alkoholprocenten
     * @Pre: fad != null<br />0 <= alkoholProcentEfterModning <= 100
     */
    public void setAlkoholProcentEfterModning(Fad fad, double alkoholProcentEfterModning) {
        fad.getFadIndhold().setAlkoholProcentEfterModning(alkoholProcentEfterModning);
    }
}
