package shpp.com.graf;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import lombok.extern.slf4j.Slf4j;
import shpp.com.model.Material;
import shpp.com.model.Workpiece;
import shpp.com.services.Calculate;

@Slf4j
public class Window {

  private JTextField thickness;
  private JTextField width;
  private JTextField length;
  private JTextField cuttingLength;
  private JTextField cutTime;
  private JTextField gasO2;
  private JTextField gasAir;
  private JTextField gasN2;
  private JTextField gasF5;
  private JTextField gasH35;

  private JRadioButton chooseCuttingLength;
  private JRadioButton chooseDimension;
  ButtonGroup group;

  private static final String FONT = "Arial";

  JComboBox<Material> comboBox = new JComboBox<>(Material.values());

  public void createWindow() {
    // create jFrame
    JFrame jFrame = new JFrame("My Frame");
    // create comboBox
    comboBox.setBounds(280, 10, 80, 30);
    jFrame.add(comboBox);
    // create label "material"
    jFrame.add(createLabel("Material:", 210, 10, 100, 30));
    // create label "dimension"
    jFrame.add(createLabel("Dimension:", 40, 10, 100, 30));
    // create label "thickness"
    jFrame.add(createLabel("Thickness:", 40, 50, 100, 30));
    // create text field "fieldThickness"
    this.thickness = createTextFieldForInput(145, 50, 80, 30, 16);
    jFrame.add(this.thickness);
    // create radio button "chooseDimension"
    this.chooseDimension = new JRadioButton();
    chooseDimension.setBounds(13, 15, 20, 20);
    chooseDimension.setSelected(true);
    this.group = new ButtonGroup();
    group.add(chooseDimension);
    jFrame.add(chooseDimension);
    // create label "Width"
    jFrame.add(createLabel("Width:", 40, 90, 100, 30));
    // create text field "fieldWidth"
    this.width = createTextField(145, 90, 80, 30, 16);
    jFrame.add(width);
    // create label "Length"
    jFrame.add(createLabel("Length:", 40, 130, 100, 30));
    // create text field "fieldLength"
    this.length = createTextField(145, 130, 80, 30, 16);
    jFrame.add(length);
    // create label "CuttingLength"
    jFrame.add(createLabel("Cutting length:", 40, 170, 150, 30));
    // create text field "fieldCuttingLength"
    this.cuttingLength = createTextField(145, 170, 80, 30, 16);
    jFrame.add(cuttingLength);
    // create radio button "chooseCuttingLength"
    this.chooseCuttingLength = new JRadioButton();
    chooseCuttingLength.setBounds(13, 175, 20, 20);
    group.add(chooseCuttingLength);

    jFrame.add(chooseCuttingLength);
    // create button "calculate"
    JButton calculate = new JButton();
    calculate.setBounds(200 - 50, 215, 100, 30);
    calculate.setFont(new Font(FONT, Font.PLAIN, 15));
    calculate.setText("calculate");
    jFrame.add(calculate);
    calculate.addActionListener(new ButtonListener());
    // create label "cutTime"
    jFrame.add(createLabel("Cut time:", 15, 260, 100, 30));
    // create label "gasConsumtion"
    jFrame.add(createLabel("Gas consumption:", 15, 300, 130, 30));
    // create text field "fieldCutTime"
    this.cutTime = createTextField(85, 260, 50, 30, 12);
    jFrame.add(this.cutTime);
    // create label and text field gasO2
    jFrame.add(createLabel("O2", 160, 260, 30, 30));
    this.gasO2 = createTextField(145, 300, 50, 30, 12);
    jFrame.add(this.gasO2);
    // create label and text field gasAIR
    jFrame.add(createLabel("AIR", 195, 260, 40, 30));
    this.gasAir = createTextField(197, 300, 25, 30, 12);
    jFrame.add(this.gasAir);
    // create label and text field gasN2
    jFrame.add(createLabel("N2", 240, 260, 30, 30));
    this.gasN2 = createTextField(224, 300, 50, 30, 12);
    jFrame.add(this.gasN2);
    // create label and text field gasF5
    jFrame.add(createLabel("F5", 290, 260, 30, 30));
    this.gasF5 = createTextField(276, 300, 50, 30, 12);
    jFrame.add(this.gasF5);
    // create label and text field gasH35
    jFrame.add(createLabel("H35", 335, 260, 30, 30));
    this.gasH35 = createTextField(328, 300, 50, 30, 12);
    jFrame.add(this.gasH35);

    jFrame.setLayout(null);
    jFrame.setSize(400, 400);
    jFrame.setVisible(true);
    jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    jFrame.setResizable(false);
  }

  private JLabel createLabel(String label, int x, int y, int width, int height) {
    JLabel jLabel = new JLabel(label);
    jLabel.setBounds(x, y, width, height);
    jLabel.setFont(new Font(FONT, Font.PLAIN, 16));
    return jLabel;
  }

  private JTextField createTextFieldForInput(int x, int y, int width, int height, int fontSize) {
    JTextField textField = new JTextField("0");
    textField.setBounds(x, y, width, height);
    textField.setFont(new Font(FONT, Font.PLAIN, fontSize));
    textField.setHorizontalAlignment(JTextField.CENTER);
    return textField;
  }

  private JTextField createTextField(int x, int y, int width, int height, int fontSize) {
    JTextField textField = new JTextField();
    textField.setBounds(x, y, width, height);
    textField.setFont(new Font(FONT, Font.PLAIN, fontSize));
    textField.setHorizontalAlignment(JTextField.CENTER);
    return textField;
  }

  class ButtonListener implements ActionListener {

    public void actionPerformed(ActionEvent e) {
      // add input date for calculate
      Workpiece workpiece = new Workpiece();
      if (chooseDimension.isSelected()) {
        workpiece = setDimension(thickness.getText(), width.getText(), length.getText());
      } else if (chooseCuttingLength.isSelected()) {
        workpiece = createCuttingLength(thickness.getText(), cuttingLength.getText());
      }
      log.warn("Material is : {}", comboBox.getSelectedItem());
      workpiece.setMaterial((Material) comboBox.getSelectedItem());
      // calculate cut time and gas
      Calculate calculate = new Calculate();
      calculate.operation(workpiece.getMaterial(), workpiece);
      cutTime.setText(rounding(calculate.getCutTime()) + "");
      checkGasConsumption(calculate);
    }

    private Workpiece createCuttingLength(String thickness, String summLength) {
      Float workpieceThickness = parserStringToFloat(thickness);
      log.info("workpiece Thickness is: {}", workpieceThickness);
      Float workpieceCutiingLength = Float.parseFloat(summLength);
      log.info("cuttingLength is: {}", workpieceCutiingLength);
      return new Workpiece()
          .setThickness(workpieceThickness)
          .setCuttingLength(workpieceCutiingLength);
    }

    private Workpiece setDimension(String thickness, String width, String length) {
      Float workpieceThickness = parserStringToFloat(thickness);
      log.info("workpiece Thickness is: {}", workpieceThickness);
      Float workpieceWidth = parserStringToFloat(width);
      log.info("workpiece Width is: {}", workpieceWidth);
      Float workpieceLength = parserStringToFloat(length);
      log.info("workpiece Length is: {}", workpieceLength);
      return new Workpiece()
          .setThickness(workpieceThickness)
          .setWidth(workpieceWidth)
          .setLength(workpieceLength);
    }

    private Float parserStringToFloat(String dimension) {
      Float result = null;
      try {
        result = Float.parseFloat(dimension);
      } catch (NumberFormatException e) {
        log.error("Not valid input data: {}", dimension);
      }
      return result;
    }

    private void checkGasConsumption(Calculate calculate) {
      Float[] gasConsumption = calculate.getGasConsumption();
      gasO2.setText(rounding(gasConsumption[0]) + "");
      gasN2.setText(rounding(gasConsumption[1]) + "");
      gasF5.setText(rounding(gasConsumption[2]) + "");
      gasH35.setText(rounding(gasConsumption[3]) + "");
      gasAir.setText(rounding(gasConsumption[4]) + "");
    }

    private float rounding(float number) {
      return (float) (Math.round(number * 1000.0) / 1000.0);
    }
  }

}
