package Kløverly.domain;

import java.io.Serializable;

public class grønneopgaver implements Serializable {

  private String titel;
  private String beskrivelse;
  private int point;

  // Konstruktør
  public grønneopgaver(String titel, String beskrivelse, int point) {
    this.titel = titel;
    this.beskrivelse = beskrivelse;
    this.point = point; // Gem point
  }

  // Tilføj getter:
  public int getPoint() {
    return point;
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