package shpp.com.services;

import java.io.File;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import shpp.com.app.MyApp;
import shpp.com.model.Material;
import shpp.com.util.CSVLoader;
import shpp.com.util.Parser;
import shpp.com.util.PropertiesLoader;

@Slf4j
public class Receiver {

  private static Receiver receiver;

  private static final String IDEA_FILE_PATH = "src/main/resources/";

  public static Receiver getReceiver() {
    if (receiver == null) {
      receiver = new Receiver();
    }
    return receiver;
  }

  public List<Float[]> getDataList(Material material) {
    log.info(" Start receive data for {}", material);
    CSVLoader loader = new CSVLoader();
    String fileName = new PropertiesLoader().loadProperties().getProperty(material.getMaterial());
    try {
      loader.load(IDEA_FILE_PATH + fileName);
    } catch (Exception e) {
      File jarPath = new File(MyApp.class
          .getProtectionDomain()
          .getCodeSource()
          .getLocation()
          .getPath());
      String filePath = jarPath.getParentFile().getPath();
      log.warn("*****JAR PATH : {}", filePath + "/config/" + fileName);
      loader.load(filePath + "/config/" + fileName);
    }
    return new Parser().parserCSVToFloat(loader.getList());
  }
}
