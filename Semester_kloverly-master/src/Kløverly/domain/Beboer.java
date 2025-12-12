package Kløverly.domain;

import java.io.Serializable;

public class Beboer implements Serializable {

  private String navn;
  private String telefon;
  private String dato;
  private int point;

  public Beboer(String navn, String telefon, String dato) {
    this.navn = navn;
    this.telefon = telefon;
    this.dato = dato;
    this.point = 0;
  }

  public String getNavn() { return navn; }
  public String getTelefon() { return telefon; }
  public String getDato() { return dato; }


  public int getPoint() { return point; }
  public void addPoint(int p) { this.point += p; }
  public void fjernPoint(int p) { this.point -= p; }

    public void setNavn(String navn) {
        this.navn = navn;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public void setDato(String dato) {
        this.dato = dato;
    }

  @Override
  public String toString() {
    return navn + " - " + dato + " (" + point + " point)";
  }
}