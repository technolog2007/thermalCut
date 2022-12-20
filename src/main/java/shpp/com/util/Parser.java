package shpp.com.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class Parser {

    private final Logger logger = LoggerFactory.getLogger(Parser.class);

    public List<String[]> parserCSV(List<String[]> list) {
        List<String[]> parsList = new ArrayList<>();
        for (int i = 1; i < list.size(); i++) {
            parsList.add(list.get(i)[0].split(";"));
        }
        return parsList;
    }

    public List<Float[]> parserCSVToFloat(List<String[]> list) {
        List<String[]> parseList = parserCSV(list);
        List<Float[]> floatList = new ArrayList<>();
        Float[] arr = new Float[parseList.get(0).length];
        for (int i = 1; i < parseList.size(); i++) {
            for (int j = 0; j < parseList.get(i).length; j++) {
                arr[j] = Float.parseFloat(parseList.get(i)[j]);
            }
            floatList.add(arr);
            arr = new Float[parseList.get(i).length];
        }
        return floatList;
    }
}
