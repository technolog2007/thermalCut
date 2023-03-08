package shpp.com.services;

import java.util.List;
import javax.swing.JTextField;
import shpp.com.model.Workpiece;

public class WindowCalculate {

  public void calculate(Workpiece workpiece, List<Float[]> carbonData, List<Float[]> stainlessData,
      List<Float[]> aluminumData, JTextField cutTime, JTextField gasO2, JTextField gasAir,
      JTextField gasN2, JTextField gasF5, JTextField gasH35) {
    Calculate calculate = new Calculate();
    switch (workpiece.getMaterial()) {
      case CARBON:
        calculate.operation(carbonData, workpiece);
      case STAINLESS:
        calculate.operation(stainlessData, workpiece);
      case ALUMINUM:
        calculate.operation(aluminumData, workpiece);
    }
    cutTime.setText(rounding(calculate.getCutTime()) + "");
    checkGasConsumption(calculate, gasO2, gasAir,
        gasN2, gasF5, gasH35);
  }

  private void checkGasConsumption(Calculate calculate, JTextField gasO2, JTextField gasAir,
      JTextField gasN2, JTextField gasF5, JTextField gasH35) {
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
