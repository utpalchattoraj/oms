package config;

import java.util.HashSet;
import java.util.Set;

public class ConfigManager {


    private final static Set<String> _instruments = new HashSet<>();
    private final static Set<String> _trades = new HashSet<>();
    private final static Set<String> _expired = new HashSet<>();


    public static ConfigManager INSTANCE = new ConfigManager();

    public static ConfigManager getInstance() {
        return INSTANCE;
    }

    // private constructor Singleton class
    private ConfigManager() {
        _instruments.add("SIAL.SI");
        _instruments.add("STEL.SI");
        _instruments.add("OCBC.SI");
        _instruments.add("DBSM.SI");

        _trades.add("SIAL.SI");
        _expired.add("STEL.SI");
    }

    public boolean isValidInstrument (String symbol) {
        return _instruments.contains(symbol);
    }

    public boolean isLiquidInstrument (String symbol) {
        return _trades.contains(symbol);
    }
}
