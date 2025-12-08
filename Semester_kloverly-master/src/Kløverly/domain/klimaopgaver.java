package Kløverly.domain;

import java.io.Serializable;

public class klimaopgaver implements Serializable {

  private String titel;
  private String beskrivelse;

  public klimaopgaver(String titel, String beskrivelse) {
    this.titel = titel;
    this.beskrivelse = beskrivelse;
  }

  public String getTitel() { return titel; }

  @Override
  public String toString() {
    return titel;
  }
}