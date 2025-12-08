package Kløverly.domain;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.io.Serializable;
import java.util.ArrayList;

public class BeboerModel implements Serializable {

  // --- LISTER TIL DATA ---

  // Liste til beboere
  private ArrayList<Beboer> beboere = new ArrayList<>();

  // Lister til dine opgaver (Det er dem, du manglede!)
  private ArrayList<grønneopgaver> grønneOpgaverList = new ArrayList<>();
  private ArrayList<bytteopgaver> bytteOpgaverList = new ArrayList<>();
  private ArrayList<klimaopgaver> klimaOpgaverList = new ArrayList<>();

  private int fællesPoint = 0;
  public int getFællesPoint() {
    return fællesPoint;
  }


  // --- METODER TIL BEBOERE ---

  public ObservableList<Beboer> getBeboer() {
    return FXCollections.observableArrayList(beboere);
  }

  public void sletBeboer(Beboer beboerSlettes) {
    beboere.remove(beboerSlettes);
  }

  public void opretBeboer(Beboer nyBeboer) {
    beboere.add(nyBeboer);
  }

  // BeboerModel.java (Linje 37)

  // --- Metoder til opgaver ---

  // Tildeler PERSONLIGE POINT (forudsætter at du finder den beboer der skal have point)
  public void addGrønOpgave(grønneopgaver opgave) {
    grønneOpgaverList.add(opgave);

    // Bytte og Grønne opgaver tildeles PERSONLIGE POINT.
    // Denne linje er kommenteret, da den kræver viden om HVEM opgaven udførte:
    // Beboer beboer = findAktuelBeboer();
    // beboer.addPoint(opgave.getPoint()); // Bruger Beboer.addPoint

    System.out.println("Grøn opgave gemt i modellen: " + opgave.getPoint() + " point");
  }

  // Tildeler PERSONLIGE POINT
  public void addBytteOpgave(bytteopgaver opgave) {
    bytteOpgaverList.add(opgave);

    // Beboer beboer = findAktuelBeboer();
    // beboer.addPoint(opgave.getPoint());

    System.out.println("Bytte opgave gemt i modellen: " + opgave.getPoint() + " point");
  }

  // Tildeler FÆLLES POINT
  public void addKlimaOpgave(klimaopgaver opgave) {
    klimaOpgaverList.add(opgave);

    // Klima opgaver tildeles FÆLLES POINT
    this.fællesPoint += opgave.getPoint(); // <-- NYT: Tildel til fælles saldo

    System.out.println("Klima opgave gemt i modellen. Fælles point nu: " + this.fællesPoint);
  }

  // Getters til at hente listerne (hvis du skal vise dem senere)
  public ArrayList<grønneopgaver> getGrønneOpgaverList() { return grønneOpgaverList; }
  public ArrayList<bytteopgaver> getBytteOpgaverList() { return bytteOpgaverList; }
  public ArrayList<klimaopgaver> getKlimaOpgaverList() { return klimaOpgaverList; }

  public void tilføjBeboer(Beboer b)
  {
  }
}