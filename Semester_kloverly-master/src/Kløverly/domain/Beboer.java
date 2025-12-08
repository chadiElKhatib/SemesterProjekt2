package Kløverly.domain;

import java.io.Serializable;

public class Beboer implements Serializable {

  private String navn;
  private String telefon;
  private String dato; // Før hed denne 'leilighed', nu er det 'dato'
  private int point;   // Husk vi skal gemme point også

  // Konstruktøren tager nu imod 'dato' som en String
  public Beboer(String navn, String telefon, String dato) {
    this.navn = navn;
    this.telefon = telefon;
    this.dato = dato;
    this.point = 0; // Alle starter med 0 point
  }

  public String getNavn() { return navn; }
  public String getTelefon() { return telefon; }
  public String getDato() { return dato; }

  // Metoder til point
  public int getPoint() { return point; }
  public void addPoint(int p) { this.point += p; }
  public void fjernPoint(int p) { this.point -= p; }

  @Override
  public String toString() {
    return navn + " - " + dato + " (" + point + " point)";
  }
}