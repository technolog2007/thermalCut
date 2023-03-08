package shpp.com.util;

import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Parser {

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
        for (String[] strings : parseList) {
            for (int j = 0; j < strings.length; j++) {
                arr[j] = Float.parseFloat(strings[j]);
            }
            floatList.add(arr);
            arr = new Float[strings.length];
        }
        return floatList;
    }

}
