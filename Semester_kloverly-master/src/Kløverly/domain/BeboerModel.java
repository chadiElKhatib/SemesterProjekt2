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


  // --- METODER TIL OPGAVER (DEM DU MANGLER) ---

  // Metode til at gemme en grøn opgave
  public void addGrønOpgave(grønneopgaver opgave) {
    grønneOpgaverList.add(opgave);
    System.out.println("Grøn opgave gemt i modellen: " + opgave);
  }

  // Metode til at gemme en bytte opgave
  public void addBytteOpgave(bytteopgaver opgave) {
    bytteOpgaverList.add(opgave);
    System.out.println("Bytte opgave gemt i modellen: " + opgave);
  }

  // Metode til at gemme en klima opgave
  public void addKlimaOpgave(klimaopgaver opgave) {
    klimaOpgaverList.add(opgave);
    System.out.println("Klima opgave gemt i modellen: " + opgave);
  }

  // Getters til at hente listerne (hvis du skal vise dem senere)
  public ArrayList<grønneopgaver> getGrønneOpgaverList() { return grønneOpgaverList; }
  public ArrayList<bytteopgaver> getBytteOpgaverList() { return bytteOpgaverList; }
  public ArrayList<klimaopgaver> getKlimaOpgaverList() { return klimaOpgaverList; }

  public void tilføjBeboer(Beboer b)
  {
  }
}