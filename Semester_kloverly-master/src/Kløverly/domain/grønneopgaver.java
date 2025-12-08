package Kløverly.domain;

import java.io.Serializable;

public class grønneopgaver implements Serializable {

  private String titel;
  private String beskrivelse;

  // Konstruktør
  public grønneopgaver(String titel, String beskrivelse) {
    this.titel = titel;
    this.beskrivelse = beskrivelse;
  }

  // Getters (så vi kan hente info senere)
  public String getTitel() {
    return titel;
  }

  public String getBeskrivelse() {
    return beskrivelse;
  }

  // toString gør det pænt, hvis vi viser opgaven i en liste
  @Override
  public String toString() {
    return titel;
  }
}