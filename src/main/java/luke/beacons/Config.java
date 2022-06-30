package luke.beacons;

import org.bukkit.plugin.Plugin;

public class Config {
    static Plugin plugin = Beacons.getPlugin();
    public static double getBeaconRange1() {
        return plugin.getConfig().getDouble("Tier 1 Beacon Range");
    }
    public static double getBeaconRange2() {
        return plugin.getConfig().getDouble("Tier 2 Beacon Range");
    }
    public static double getBeaconRange3() {
        return plugin.getConfig().getDouble("Tier 3 Beacon Range");
    }
    public static double getBeaconRange4() {
        return plugin.getConfig().getDouble("Tier 4 Beacon Range");
    }
}
