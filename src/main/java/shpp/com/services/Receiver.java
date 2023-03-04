package shpp.com.services;

import java.util.Scanner;
import lombok.extern.slf4j.Slf4j;
import shpp.com.model.Material;
import shpp.com.model.Workpiece;

@Slf4j
public class Receiver {

  private static Receiver receiver;

  private Workpiece workpiece;
  private final Scanner scannerIn = new Scanner(System.in);
  boolean flag;

  public static Receiver getReceiver() {
    if (receiver == null) {
      receiver = new Receiver();
    }
    return receiver;
  }

  public void receive() {
    if (this.flag) {
      this.workpiece = new Workpiece().
          setThickness(receiveField("Thickness : ")).
          setCuttingLength(receiveField("Length : ")).
          setMaterial(receiveMaterial());
    } else {
      this.workpiece = new Workpiece().
          setThickness(receiveField("Thickness : ")).
          setWidth(receiveField("Width : ")).
          setLength(receiveField("Length : ")).
          setMaterial(receiveMaterial());
    }
  }

  private Float receiveField(String receiveField) {
    log.info(receiveField);
    return scannerIn.nextFloat();
  }

  private Material receiveMaterial() {
    log.info(" Material: ");
    String material = scannerIn.next();
    Material validateMaterial = checkMaterial(material);
    if (validateMaterial == null) {
      while (validateMaterial == null) {
        log.info(" Material: ");
        material = scannerIn.next();
        validateMaterial = checkMaterial(material);
      }
    }
    return validateMaterial;
  }

  private Material checkMaterial(String material) {
    switch (material) {
      case "carbon":
        return Material.CARBON;
      case "stainless":
        return Material.STAINLESS;
      case "aluminum":
        return Material.ALUMINUM;
      default:
        return null;
    }
  }

  public Workpiece getWorkpiece() {
    return workpiece;
  }
}
