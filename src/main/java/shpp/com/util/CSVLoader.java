package shpp.com.util;

import com.opencsv.CSVReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CSVLoader {

  private static final Logger logger = LoggerFactory.getLogger(CSVLoader.class);
  private List<String[]> list;

  @SneakyThrows
  public void load(String fileName) {
    logger.info("Start load csv file with name {}!", fileName);
    try (CSVReader reader = new CSVReader(new FileReader(fileName))) {
      list = reader.readAll();
      logger.info("CSV file load successful!");
    } catch (IOException e) {
      logger.info("ERROR! Please check CSV-FILE!");
      logger.info(e.getMessage());
    }
  }

  public List<String[]> getList() {
    return list;
  }
}
