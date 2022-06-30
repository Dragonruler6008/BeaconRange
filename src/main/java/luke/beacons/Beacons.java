package luke.beacons;

import luke.beacons.commands.CommandManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashSet;
import java.util.Objects;
import com.destroystokyo.paper.event.block.BeaconEffectEvent;
import org.bukkit.block.Beacon;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;
public class Beacons extends JavaPlugin implements Listener {
    private static Beacons Beacons;
    BukkitTask beaconTask;


    @Override
    public void onEnable() {
        Beacons = this;
        saveDefaultConfig();
        getServer().getPluginManager().registerEvents(this, this);
        Objects.requireNonNull(getCommand("BeaconRange")).setExecutor(new CommandManager());
        beaconAsyncChecker();


    }

    @Override
    public void onDisable() {
        if(Beacons != null) Beacons.shutdown();
    }

    public void shutdown() {
        getServer().getPluginManager().disablePlugin(this);
    }

    public static Beacons getPlugin () {
        return Beacons;
    }

    HashSet<Block> beaconBlocks = new HashSet<>();
    HashSet<Beacon> beaconsToCheck = new HashSet<>();

    public void beaconAsyncChecker() {
        this.beaconTask = (new BukkitRunnable () {
            public void run() {
                if(Beacons.this.beaconsToCheck.size() > 0)
                    Beacons.this.beaconsToCheck.forEach(beacon -> {
                        int tier = beacon.getTier();

                        if (tier == 1){
                            final double setRange = Config.getBeaconRange1();
                            (new BukkitRunnable() {
                                public void run() {
                                    beacon.setEffectRange(setRange);
                                    beacon.update();
                                    cancel();
                                }
                             }) .runTask(Beacons);
                        }
                        else if (tier == 2){
                            final double setRange = Config.getBeaconRange2();
                            (new BukkitRunnable() {
                                public void run() {
                                    beacon.setEffectRange(setRange);
                                    beacon.update();
                                    cancel();
                                }
                            }) .runTask(Beacons);
                        }
                        else if (tier == 3){
                            final double setRange = Config.getBeaconRange3();
                            (new BukkitRunnable() {
                                public void run() {
                                    beacon.setEffectRange(setRange);
                                    beacon.update();
                                    cancel();
                                }
                            }) .runTask(Beacons);
                        }
                        else if (tier == 4){
                            final double setRange = Config.getBeaconRange4();
                            (new BukkitRunnable() {
                                public void run() {
                                    beacon.setEffectRange(setRange);
                                    beacon.update();
                                    cancel();
                                }
                            }) .runTask(Beacons);
                        }
                    });
                Beacons.this.beaconsToCheck.clear();
                Beacons.this.beaconBlocks.clear();
            }
        }).runTaskTimer(Beacons, 0L, 20L);
    }


    @EventHandler
    public void onBeaconEffect(BeaconEffectEvent event) {
        Block block = event.getBlock();
        if (!this.beaconBlocks.contains(block)) {
            this.beaconBlocks.add(block);
            this.beaconsToCheck.add((Beacon)block.getState());
        }
    }







}
