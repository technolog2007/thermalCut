package shpp.com.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import shpp.com.model.Material;
import shpp.com.model.Workpiece;
import shpp.com.util.CSVLoader;
import shpp.com.util.Parser;
import shpp.com.util.PropertiesLoader;

import java.util.List;

public class Calculate {
    Float[] gasConsumption;
    float cutTime;
    private final Logger logger = LoggerFactory.getLogger(Calculate.class);

    public void operation(Material material, Workpiece workpiece) {
        // аналіз і фільтр вхідних даних;
        Float[] inputData = choiceDataFromList(getDataList(material), workpiece);
        logger.info("input data is : {}, {}, {}", inputData[0], inputData[1], inputData[2]);
        // розрахунок часу різання
        this.cutTime = calculateCutTime(inputData, workpiece);
        logger.info("Cut time is : {}", cutTime);
        // розрахунок норми витрати газу
        this.gasConsumption = calculateGasConsumption(inputData, workpiece);
        logger.info(getMessageGas(workpiece));
    }

    private String getMessageGas(Workpiece workpiece){
        String message = "";
        if(workpiece.getMaterial().equals(Material.CARBON)){
            message = "The rate of Oxygen (O2) consumption is : " + gasConsumption[0] + " m3";
        } else if (workpiece.getMaterial().equals(Material.ALUMINUM)){
            message = "The rate of air consumption is : " + gasConsumption[0] + " m3";
        } else if (workpiece.getMaterial().equals(Material.STAINLESS)){
            message = "The rate of N2, F5, H35 consumption is : " +
                    gasConsumption[0] + " , " +
                    gasConsumption[1] + " , " +
                    gasConsumption[2] + " , " + " m3";
        }
        return message;
    }

    private Float[] choiceDataFromList(List<Float[]> dataList, Workpiece workpiece) {
        float thickness = workpiece.getThickness();
        if (thickness > 0 && thickness < dataList.get(dataList.size() - 1)[0]) {
            for (int i = 0; i < dataList.size() - 1; i++) {
                if (thickness == dataList.get(i)[0]) {
                    return dataList.get(i);
                }
            }
        } else {
            logger.info("повернутися до початку програми!, або видати помилку");
        }
        return new Float[0];
    }

    private List<Float[]> getDataList(Material material) {
        CSVLoader loader = new CSVLoader();
        loader.load(new PropertiesLoader().loadProperties().getProperty(material.getMaterial()));
        return new Parser().parserCSVToFloat(loader.getList());
    }

    public float calculateCutTime(Float[] inputData, Workpiece workpiece) {
        float cutSpeed = inputData[1];
        float cutLength = getCuttingLength(workpiece);
        return  (cutLength / cutSpeed) * (float) 1.3;
    }

    private float getCuttingLength(Workpiece workpiece) {
        if (workpiece.getCuttingLength() != null) {
            return workpiece.getCuttingLength();
        } else {
            return workpiece.getLength() * workpiece.getWidth() * 2;
        }
    }


    private Float[] calculateGasConsumption(Float[] inputData, Workpiece workpiece) {
        Float [] gas = new Float[3];
        if(workpiece.getMaterial().equals(Material.CARBON)) {
            gas[0] = cutTime * (float) (inputData[2] * 1.5 * 0.001);
        } else if (workpiece.getMaterial().equals(Material.STAINLESS)){
            if(workpiece.getThickness() >= 0.8 && workpiece.getThickness() <= 4 ||
                    workpiece.getThickness() >= 10 && workpiece.getThickness() <= 12) {
                gas[0] = cutTime * (float) (inputData[2] * 1.5 * 0.001);
            } else if (workpiece.getThickness() > 4 && workpiece.getThickness() <= 8){
                gas[0] = cutTime * (float) (inputData[2] * 1.5 * 0.001);
                gas[1] = cutTime * (float) (31 * 1.5 * 0.001);
            } else if(workpiece.getThickness() > 12 && workpiece.getThickness() <= 25){
                gas[0] = cutTime * (float) (inputData[2] * 1.5 * 0.001);
                gas[2] = cutTime * (float) (26 * 1.5 * 0.001);
            }
        } else if (workpiece.getMaterial().equals(Material.ALUMINUM)){
            gas[0] = (float) 0.0;
        }
        return gas;
    }

    public float getCutTime() {
        return cutTime;
    }
}
