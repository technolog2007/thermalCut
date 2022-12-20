package shpp.com.util;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class CSVLoader {
    private static final Logger logger = LoggerFactory.getLogger(CSVLoader.class);
    private List<String[]> list;
    public void load(String fileName) {
        logger.info("Start load csv file !");
        try (CSVReader reader = new CSVReader(new FileReader(fileName))) {
            list = reader.readAll();
            logger.info("CSV file load successful!");
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (CsvException ex) {
            throw new RuntimeException(ex);
        }
    }

    public List<String[]> getList() {
        return list;
    }
}
