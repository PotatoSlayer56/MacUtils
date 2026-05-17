package potatoslayer56.macutils.client.config;

import com.google.gson.Gson;
import potatoslayer56.macutils.MacUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SaveLoad {

  public static String configPath = "config/macutils.config";

  public static void saveConfig() {
    Gson gson = new Gson();
    try {
      FileWriter fileWriter = new FileWriter(configPath);

      fileWriter.write(gson.toJson(Variables.config));

      fileWriter.close();
    }
    catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  public static void loadConfig() {
    try {
      File configFile = new File(configPath);
      BufferedReader bufferedReader = new BufferedReader(new FileReader(configFile));
      List<String> data = new ArrayList<String>();
      String st;

      while ((st = bufferedReader.readLine()) != null){
        data.add(st);
      }

      Gson gson = new Gson();

      Variables.config = gson.fromJson(data.getFirst(), HashMap.class);
    }
    catch (IOException e) {
      throw new RuntimeException(e);
    }
    if(Variables.config.get("customcommands") == null){
      Variables.config = Variables.defaultConfig;
    }
    Variables.customCommands = (java.util.Map<String, String>) Variables.config.get("customcommands");
    Variables.toggles = (java.util.Map<String, java.util.Map<String, Boolean>>) Variables.config.get("toggles");
    Variables.values = (java.util.Map<String, java.util.Map<String, Float>>) Variables.config.get("values");
  }

  public static void createConfig() {
    try {
      File configFile = new File(configPath);
      if (configFile.createNewFile()) {
        saveConfig();
        MacUtils.LOGGER.info("Config created");
      }
      else {
        MacUtils.LOGGER.info("Config exists");
      }
    }
    catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
