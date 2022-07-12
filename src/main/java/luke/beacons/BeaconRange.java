package luke.beacons;

import luke.beacons.commands.CommandManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Chunk;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
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
import java.io.File;
public class BeaconRange extends JavaPlugin implements Listener {
    private static BeaconRange BeaconRange;
    public File data;
    public FileConfiguration datac;
    BukkitTask beaconTask;


    @Override
    public void onEnable() {
        BeaconRange = this;
        saveDefaultConfig();
        getServer().getPluginManager().registerEvents(this, this);
        Objects.requireNonNull(getCommand("BeaconRange")).setExecutor(new CommandManager());
        createBeaconLocFile();
        beaconAsyncChecker();


    }

    @Override
    public void onDisable() {
        if(BeaconRange != null) BeaconRange.shutdown();
    }

    public void shutdown() {
        getServer().getPluginManager().disablePlugin(this);
    }

    public static BeaconRange getPlugin () {
        return BeaconRange;
    }

    HashSet<Block> beaconBlocks = new HashSet<>();
    HashSet<Beacon> beaconsToCheck = new HashSet<>();

    public void beaconAsyncChecker() {
        this.beaconTask = (new BukkitRunnable () {
            public void run() {
                if(BeaconRange.this.beaconsToCheck.size() > 0)
                    BeaconRange.this.beaconsToCheck.forEach(beacon -> {
                        int tier = beacon.getTier();

                        if (tier == 1){
                            final double setRange = Config.getBeaconRange1();
                            (new BukkitRunnable() {
                                public void run() {
                                    beacon.setEffectRange(setRange);
                                    beacon.update();
                                    cancel();
                                }
                             }) .runTask(BeaconRange);
                        }
                        else if (tier == 2){
                            final double setRange = Config.getBeaconRange2();
                            (new BukkitRunnable() {
                                public void run() {
                                    beacon.setEffectRange(setRange);
                                    beacon.update();
                                    cancel();
                                }
                            }) .runTask(BeaconRange);
                        }
                        else if (tier == 3){
                            final double setRange = Config.getBeaconRange3();
                            (new BukkitRunnable() {
                                public void run() {
                                    beacon.setEffectRange(setRange);
                                    beacon.update();
                                    cancel();
                                }
                            }) .runTask(BeaconRange);
                        }
                        else if (tier == 4){
                            final double setRange = Config.getBeaconRange4();
                            (new BukkitRunnable() {
                                public void run() {
                                    beacon.setEffectRange(setRange);
                                    beacon.update();
                                    cancel();
                                }
                            }) .runTask(BeaconRange);
                        }
                    });
                BeaconRange.this.beaconsToCheck.clear();
                BeaconRange.this.beaconBlocks.clear();
            }
        }).runTaskTimer(BeaconRange, 0L, 20L);
    }


    @EventHandler
    public void onBeaconEffect(BeaconEffectEvent event) {
        Block block = event.getBlock();
        if (!this.beaconBlocks.contains(block)) {
            this.beaconBlocks.add(block);
            this.beaconsToCheck.add((Beacon)block.getState());
        }
    }

    public void createBeaconLocFile() {
        data = new File(getDataFolder(), "data.yml");
        if(!data.exists()) {
            saveResource("data.yml", false);
            Bukkit.getConsoleSender().sendMessage(String.valueOf(BeaconRange) + ChatColor.DARK_GREEN + " Beacon location storage created!");
        }
        datac = new YamlConfiguration();
        try{
            datac.load(data);
        } catch (Exception e) {
            Bukkit.getConsoleSender().sendMessage(ChatColor.DARK_RED + " Error Creating Beacon Location file, beacons outside of render distance may not work properly (USE AT OWN RISK) ");
            e.printStackTrace();
        }
    }

    @EventHandler
    public void beaconStorageAdd(BlockPlaceEvent event) {
        Block block = event.getBlock();
        Material mat = block.getType();
        if(!mat.equals(Material.BEACON)) {
            Bukkit.getConsoleSender().sendMessage(String.valueOf(BeaconRange) + ChatColor.RED + " Im not a beacon!");
        }else {
            Bukkit.getConsoleSender().sendMessage(String.valueOf(BeaconRange) + ChatColor.DARK_GREEN + " I am a Beacon!");
            Chunk chunk = block.getChunk();

        }
        //Chunk chunk = block.getChunk();
        // What info is useful?
        // 1. position (X,Z)
        // 2. ...
    }

    @EventHandler
    public void beaconStorageRemove(BlockBreakEvent event) {
        Block block = event.getBlock();
        Material mat = block.getType();
        if(!mat.equals(Material.BEACON)) {
            Bukkit.getConsoleSender().sendMessage(String.valueOf(BeaconRange) + ChatColor.RED + " Im not a beacon! (Now Gone)");
        }
        else {
            Bukkit.getConsoleSender().sendMessage(String.valueOf(BeaconRange) + ChatColor.DARK_GREEN + " I am a Beacon! (Now gone)");
        }
    }






}
