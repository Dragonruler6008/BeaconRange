package luke.beacons;

import org.bukkit.plugin.Plugin;

public class Config {
    static Plugin plugin = Beacons.getPlugin();

    public static double getBeaconRange() {
        return plugin.getConfig().getDouble("Beacon Range");
    }
}
