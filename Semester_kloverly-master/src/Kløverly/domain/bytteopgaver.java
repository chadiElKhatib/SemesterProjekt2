package Kløverly.domain;

import java.io.Serializable;

// Det er vigtigt med stort startbogstav i Java klasser, men hvis din fil hedder
// "bytteopgaver" med lille b, skal klassen også hedde det.
// Jeg anbefaler at omdøbe filen til "BytteOpgave.java" (ental, stort B), men her er koden til din nuværende fil:

public class bytteopgaver implements Serializable {

  private String titel;
  private String beskrivelse;

  public bytteopgaver(String titel, String beskrivelse) {
    this.titel = titel;
    this.beskrivelse = beskrivelse;
  }

  // Getters og Setters (hvis du skal bruge dem senere)
  public String getTitel() { return titel; }
  public String getBeskrivelse() { return beskrivelse; }

  @Override
  public String toString() {
    return titel + ": " + beskrivelse;
  }
}