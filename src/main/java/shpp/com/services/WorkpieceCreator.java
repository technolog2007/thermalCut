package shpp.com.services;

import jakarta.validation.ValidationException;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import lombok.extern.slf4j.Slf4j;
import shpp.com.model.Material;
import shpp.com.model.Workpiece;

@Slf4j
public class WorkpieceCreator {

  public Workpiece createWorkpiece(JTextField thickness, JTextField width, JTextField length,
      JTextField cuttingLength, JComboBox<Material> comboBox, JRadioButton chooseCuttingLength,
      JRadioButton chooseDimension) {
    Workpiece workpiece = new Workpiece();
    if (chooseDimension.isSelected()) {
      workpiece = setDimension(checkComma(thickness.getText()), checkComma(width.getText()),
          checkComma(length.getText()));
    } else if (chooseCuttingLength.isSelected()) {
      workpiece = createCuttingLength(checkComma(thickness.getText()),
          checkComma(cuttingLength.getText()));
    }
    log.warn("Material is : {}", comboBox.getSelectedItem());
    workpiece.setMaterial((Material) comboBox.getSelectedItem());
    return workpiece;
  }

  private Workpiece setDimension(String thickness, String width, String length) {
    Float workpieceThickness = parserStringToFloat(thickness);
    Float workpieceWidth = parserStringToFloat(width);
    Float workpieceLength = parserStringToFloat(length);
    return new Workpiece()
        .setThickness(workpieceThickness)
        .setWidth(workpieceWidth)
        .setLength(workpieceLength);
  }

  private Float parserStringToFloat(String dimension) {
    try {
      return Float.parseFloat(dimension);
    } catch (NumberFormatException e) {
      log.error("Not valid input data: {}", dimension);
      JOptionPane.showMessageDialog(null,
          "The entered value: " + dimension + " is not valid\n"
              + "\n Please enter a valid value!",
          "Warning",
          JOptionPane.WARNING_MESSAGE
      );
      throw new ValidationException("ERROR! Input data is not valid!");
    }
  }

  private String checkComma(String input) {
    return input.replace(",", ".");
  }

  private Workpiece createCuttingLength(String thickness, String summLength) {
    Float workpieceThickness = parserStringToFloat(thickness);
    Float workpieceCuttingLength = Float.parseFloat(summLength);
    return new Workpiece()
        .setThickness(workpieceThickness)
        .setCuttingLength(workpieceCuttingLength);
  }
}
