package potatoslayer56.macutils.client.config;

import java.util.HashMap;
import java.util.Map;

public class Variables {

  public static boolean AUTOFEED = false;
  public static boolean AUTOFEEDNOKICK = false;
  public static int AUTOFEEDTHRESHOLD = 18;

  public static boolean PITCHLOCKED = false;
  public static float PITCHLOCK = 0;
  public static boolean YAWLOCKED = false;
  public static float YAWLOCK = 0;

  public static int MININGFORTUNETIMER = 0;
  public static int TIMBERFORTUNETIMER = 0;

  public static boolean SLAYERACTIVE = false;
  public static int SLAYERTIME = 0;

  public static Map<String, String> customCommands = new HashMap<>();

  public static void initialise() {
    customCommands.put("customCommand1", "");
    customCommands.put("customCommand2", "");
    customCommands.put("customCommand3", "");
    customCommands.put("customCommand4", "");
    customCommands.put("customCommand5", "");
  }
}