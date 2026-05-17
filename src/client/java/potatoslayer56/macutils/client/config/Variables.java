package potatoslayer56.macutils.client.config;

import java.util.HashMap;
import java.util.Map;

public class Variables {

  public static boolean AUTOFEEDNOKICK = false;

  public static boolean PITCHLOCKED = false;
  public static boolean YAWLOCKED = false;

  public static int MININGFORTUNETIMER = 0;
  public static int TIMBERFORTUNETIMER = 0;

  public static boolean SLAYERACTIVE = false;
  public static int SLAYERTIME = 0;

  public static int FRYTIMER = 0;
  public static int MOLTENBLADETIMER = 0;
  public static int BLOODDRINKTIMER = 0;

  public static Map<String, Map<String, ?>> config = new HashMap<>();
  public static Map<String, String> customCommands = new HashMap<>();
  public static Map<String, Map<String, Boolean>> toggles = new HashMap<>();
  public static Map<String, Map<String, Float>> values = new HashMap<>();

  public static Map<String, Map<String, ?>> defaultConfig = new HashMap<>();
  public static Map<String, String> defaultCustomCommands = new HashMap<>();
  public static Map<String, Map<String, Boolean>> defaultToggles = new HashMap<>();
  public static Map<String, Map<String, Float>> defaultValues = new HashMap<>();

  public static void initialise() {
    defaultCustomCommands.put("customCommand1", "");
    defaultCustomCommands.put("customCommand2", "");
    defaultCustomCommands.put("customCommand3", "");
    defaultCustomCommands.put("customCommand4", "");
    defaultCustomCommands.put("customCommand5", "");

    defaultToggles.put("autofeed", new HashMap<String, Boolean>(){{
      put("enabled", false);
    }});
    defaultToggles.put("fortune", new HashMap<String, Boolean>(){{
      put("enabled", false);
    }});
    defaultToggles.put("slayer", new HashMap<String, Boolean>(){{
      put("timerenabled", false);
      put("messagesenabled", false);
    }});
    defaultToggles.put("copyrng", new HashMap<String, Boolean>(){{
      put("enabled", false);
    }});
    defaultToggles.put("fry", new HashMap<String, Boolean>(){{
      put("enabled", false);
    }});
    defaultToggles.put("moltenblade", new HashMap<String, Boolean>(){{
      put("enabled", false);
    }});
    defaultToggles.put("blooddrink", new HashMap<String, Boolean>(){{
      put("enabled", false);
    }});

    defaultValues.put("autofeed", new HashMap<String, Float>(){{
      put("threshold", 18.0f);
    }});
    defaultValues.put("pitchlock", new HashMap<String, Float>(){{
      put("angle", 0.0f);
    }});
    defaultValues.put("yawlock", new HashMap<String, Float>(){{
      put("angle", 0.0f);
    }});

    defaultConfig.put("customcommands", defaultCustomCommands);
    defaultConfig.put("toggles", defaultToggles);
    defaultConfig.put("values", defaultValues);
  }
}