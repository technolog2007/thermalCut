package shpp.com.services;

import static org.junit.jupiter.api.Assertions.assertEquals;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import shpp.com.model.Material;
import shpp.com.model.Workpiece;

@Slf4j
class CalculateTest {

  Calculate calculate;
  Workpiece workpiece;

  @BeforeEach
  void init() {
    this.calculate = new Calculate();
    this.workpiece = new Workpiece();
    this.workpiece.setMaterial(Material.CARBON);
    this.workpiece.setThickness(2f);
    this.workpiece.setLength(1000f);
    this.workpiece.setWidth(1000f);
  }

  @Test
  void operationCalculateCutTime() {
    calculate.operation(Receiver.getReceiver().getDataList(Material.CARBON), workpiece);
    Float actual = 3.489933f;
    Float expected = calculate.getCutTime();
    assertEquals(expected, actual);
  }

  @Test
  void operationCalculateGasConsumption() {
    calculate.operation(Receiver.getReceiver().getDataList(Material.CARBON), workpiece);
    Float actual = 0.11516779f;
    Float[] gasConsumption = calculate.getGasConsumption();
    Float expected = gasConsumption[0];
    assertEquals(expected, actual);
  }
}