package shpp.com.services;

import java.util.List;
import lombok.extern.slf4j.Slf4j;
import shpp.com.model.Material;
import shpp.com.model.Workpiece;

@Slf4j
public class Calculate {

    private Float[] gasConsumption;
    private float cutTime;

    public void operation(List<Float[]> cutData, Workpiece workpiece) {
        // analysis and filter of input data
        Float[] inputData = choiceDataFromList(cutData, workpiece);
        log.info("input data is : {}, {}, {}", inputData[0], inputData[1], inputData[2]);
        // calculation of cutting time
        this.cutTime = calculateCutTime(inputData, workpiece);
        log.info("Cut time is : {}", cutTime);
        // calculation of the rate of gas consumption
        this.gasConsumption = calculateGasConsumption(inputData, workpiece);
        log.info(getMessageGas(workpiece));
    }

    private String getMessageGas(Workpiece workpiece) {
        String message = "";
        if (workpiece.getMaterial().equals(Material.CARBON)) {
            message = "The rate of Oxygen (O2) consumption is : " + gasConsumption[0] + " m3";
        } else if (workpiece.getMaterial().equals(Material.ALUMINUM)) {
            message = "The rate of air consumption is : " + gasConsumption[0] + " m3";
        } else if (workpiece.getMaterial().equals(Material.STAINLESS)) {
            message = "The rate of N2, F5, H35 consumption is : " +
                gasConsumption[0] + " , " +
                gasConsumption[1] + " , " +
                gasConsumption[2] + " , " + " m3";
        }
        return message;
    }

    private Float[] choiceDataFromList(List<Float[]> dataList, Workpiece workpiece) {
        float thickness = workpiece.getThickness();
        if (thickness > 0 && thickness <= dataList.get(dataList.size() - 1)[0]) {
            for (int i = 0; i < dataList.size() - 1; i++) {
                if (thickness == dataList.get(i)[0]) {
                    return dataList.get(i);
                } else if (thickness > dataList.get(i)[0] && thickness <= dataList.get(i + 1)[0]) {
                    return dataList.get(i + 1);
                }
            }
        } else {
            log.info("return to the beginning of the program!, or issue an error");
        }
        return new Float[0];
    }

    private float calculateCutTime(Float[] inputData, Workpiece workpiece) {
        float cutSpeed = inputData[1];
        float cutLength = getCuttingLength(workpiece);
        return (cutLength / cutSpeed) * (float) 1.3;
    }

    private float getCuttingLength(Workpiece workpiece) {
        if (workpiece.getCuttingLength() != null) {
            return workpiece.getCuttingLength();
        } else {
            return (workpiece.getLength() + workpiece.getWidth()) * 2;
        }
    }


    private Float[] calculateGasConsumption(Float[] inputData, Workpiece workpiece) {
        /*
         * gas[0] - O2
         * gas[1] - N2
         * gas[2] - F5
         * gas[3] - H35
         * gas[4] - AIR
         */
        Float[] gas = {0f, 0f, 0f, 0f, 0f};
        if (workpiece.getMaterial().equals(Material.CARBON)) {
            gas[0] = cutTime * (float) (inputData[2] * 1.5 * 0.001);
        } else if (workpiece.getMaterial().equals(Material.STAINLESS)) {
            if (workpiece.getThickness() >= 0.8 && workpiece.getThickness() <= 4 ||
                workpiece.getThickness() >= 10 && workpiece.getThickness() <= 12) {
                gas[1] = cutTime * (float) (inputData[2] * 1.5 * 0.001);
            } else if (workpiece.getThickness() > 4 && workpiece.getThickness() <= 8) {
                gas[1] = cutTime * (float) (inputData[2] * 1.5 * 0.001);
                gas[2] = cutTime * (float) (31 * 1.5 * 0.001);
            } else if (workpiece.getThickness() > 12 && workpiece.getThickness() <= 25) {
                gas[1] = cutTime * (float) (inputData[2] * 1.5 * 0.001);
                gas[3] = cutTime * (float) (26 * 1.5 * 0.001);
            }
        } else if (workpiece.getMaterial().equals(Material.ALUMINUM)) {
            gas[4] = (float) 0.0;
        }
        return gas;
    }

    public Float[] getGasConsumption() {
        return gasConsumption;
    }

    public float getCutTime() {
        return cutTime;
    }

}
