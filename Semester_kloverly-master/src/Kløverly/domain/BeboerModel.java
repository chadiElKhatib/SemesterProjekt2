package Kløverly.domain;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.io.Serializable;
import java.util.ArrayList;

public class BeboerModel implements Serializable {

  private ArrayList<Beboer> beboere = new ArrayList<>();

  private ArrayList<grønneopgaver> grønneOpgaverList = new ArrayList<>();
  private ArrayList<bytteopgaver> bytteOpgaverList = new ArrayList<>();
  private ArrayList<klimaopgaver> klimaOpgaverList = new ArrayList<>();

  private int fællesPoint = 0;
  public int getFællesPoint() {
    return fællesPoint;
  }

  public ObservableList<Beboer> getBeboer() {
    return FXCollections.observableArrayList(beboere);
  }

  public void sletBeboer(Beboer beboerSlettes) {
    beboere.remove(beboerSlettes);
  }

  public void opretBeboer(Beboer nyBeboer) {
    beboere.add(nyBeboer);
  }

  public void addGrønOpgave(grønneopgaver opgave) {
    grønneOpgaverList.add(opgave);
    System.out.println("Grøn opgave gemt i modellen: " + opgave.getPoint() + " point");
  }

  public void addBytteOpgave(bytteopgaver opgave) {
    bytteOpgaverList.add(opgave);
    System.out.println("Bytte opgave gemt i modellen: " + opgave.getPoint() + " point");
  }

  public void addKlimaOpgave(klimaopgaver opgave) {
    klimaOpgaverList.add(opgave);
    this.fællesPoint += opgave.getPoint();
    System.out.println("Klima opgave gemt i modellen. Fælles point nu: " + this.fællesPoint);
  }


  public ArrayList<grønneopgaver> getGrønneOpgaverList() { return grønneOpgaverList; }
  public ArrayList<bytteopgaver> getBytteOpgaverList() { return bytteOpgaverList; }
  public ArrayList<klimaopgaver> getKlimaOpgaverList() { return klimaOpgaverList; }

  public void tilføjBeboer(Beboer b)
  {
  }
}