package config;

import java.util.HashSet;
import java.util.Set;

public class ConfigManager {


    private final static Set<String> _accept = new HashSet<>();
    private final static Set<String> _reject = new HashSet<>();
    private final static Set<String> _trades = new HashSet<>();
    private final static Set<String> _expired = new HashSet<>();


    public static ConfigManager INSTANCE = new ConfigManager();

    // private constructor Singleton class
    private ConfigManager() {
        _accept.add("SIAL.SI");
        _accept.add("STEL.SI");
        _accept.add("OCBC.SI");

        _reject.add("DBSM.SI");

        _trades.add("SIAL.SI");
        _expired.add("STEL.SI");
    }

    public boolean isAccepted (String symbol) {
        return _accept.contains(symbol);
    }

}
