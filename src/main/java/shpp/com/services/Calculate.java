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
    float gasConsumption;
    float cutTime;
    private final Logger logger = LoggerFactory.getLogger(Calculate.class);

    public void operation(Material material, Workpiece workpiece) {
        // аналіз і фільтр вхідних даних;
        Float[] inputData = choiceDataFromList(getDataList(material), workpiece);
        logger.info("input data is : {}, {}, {}", inputData[0], inputData[1], inputData[2]);
        // розрахунок часу різання
        this.cutTime = calculateCutTime(inputData, workpiece);


        // розрахунок норми витрати газу
        // потрібна логіка розрахунку
        this.gasConsumption = calculateGasConsumption(material, cutTime);

    }

    private Float[] choiceDataFromList(List<Float[]> dataList, Workpiece workpiece) {
        float thickness = workpiece.getThickness();
        if (thickness > 0 && thickness < dataList.get(dataList.size() - 1)[0]) {
            for (int i = 0; i < dataList.size() - 1; i++) {
                float first = dataList.get(i)[0];
                float second = dataList.get(i+1)[0];
                if (thickness > dataList.get(i)[0] && thickness < dataList.get(i + 1)[0]) {
                    return dataList.get(i + 1);
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
        float cutSpeed = inputData[0];
        float cutLength = getCuttingLength(workpiece);
        return cutLength / cutSpeed;
    }

    private float getCuttingLength(Workpiece workpiece) {
        if (workpiece.getCuttingLength() != null) {
            return workpiece.getCuttingLength();
        } else {
            return workpiece.getLength() * workpiece.getWidth() * 2;
        }
    }


    private float calculateGasConsumption(Material material, float cutTime) {
        return 0;
    }

    public float getGasConsumption() {
        return gasConsumption;
    }

    public float getCutTime() {
        return cutTime;
    }
}
