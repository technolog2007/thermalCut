package shpp.com.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import shpp.com.model.Material;
import shpp.com.model.Workpiece;

import java.util.Scanner;

public class Receiver {

    private Workpiece workpiece;
    private final Scanner scannerIn = new Scanner(System.in);

    private final Logger logger = LoggerFactory.getLogger(Receiver.class);

    public void receive() {
        boolean flag = makeChoice();
        if (flag) {
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

    private boolean makeChoice() {
        logger.info(" Do you want to use the calculation option based on the total length of the outline?" +
                " Please input true or false! ");
        return scannerIn.nextBoolean();
    }

    private Float receiveField(String receiveField) {
        logger.info(receiveField);
        return scannerIn.nextFloat();
    }

    private Material receiveMaterial() {
        logger.info(" Material: ");
        String material = scannerIn.next();
        Material validateMaterial = checkMaterial(material);
        if (validateMaterial == null) {
            while (validateMaterial == null) {
                logger.info(" Material: ");
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
