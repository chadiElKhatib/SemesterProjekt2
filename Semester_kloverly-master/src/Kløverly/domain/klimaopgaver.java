package Kløverly.domain;

import java.io.Serializable;

public class klimaopgaver implements Serializable {

  private String titel;
  private String beskrivelse;
  private int point;

  public klimaopgaver(String titel, String beskrivelse, int point) {
    this.titel = titel;
    this.beskrivelse = beskrivelse;
    this.point = point;
  }

  public int getPoint() {
    return point;
  }

  public String getTitel() { return titel; }

  @Override
  public String toString() {
    return this.titel + " (" + this.point + " point)";
  }
}