package Kløverly.persistense;

import Kløverly.domain.BeboerModel;
import java.io.*;

public class Datamanager {

  // Navnet på filen, som data gemmes i på din computer
  private static final String FIL_NAVN = "data.bin";

  // --- GEM DATA (SERIALISERING) ---
  public static void gemModel(BeboerModel model) {
    try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FIL_NAVN))) {
      oos.writeObject(model);
      System.out.println("Succes: Data er gemt i " + FIL_NAVN);
    } catch (IOException e) {
      System.out.println("Fejl: Kunne ikke gemme data -> " + e.getMessage());
      e.printStackTrace();
    }
  }

  // --- HENT DATA (DESERIALISERING) ---
  public static BeboerModel hentModel() {
    File fil = new File(FIL_NAVN);

    // Hvis filen findes, så indlæs den
    if (fil.exists()) {
      try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fil))) {
        return (BeboerModel) ois.readObject();
      } catch (IOException | ClassNotFoundException e) {
        System.out.println("Kunne ikke indlæse filen, starter forfra.");
        e.printStackTrace();
      }
    } else {
      System.out.println("Ingen gemt fil fundet. Opretter en ny tom model.");
    }

    // Hvis ingen fil findes (første gang), returner vi bare en ny tom model
    return new BeboerModel();
  }
}