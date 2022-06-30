package luke.beacons.commands.subcommands;

import luke.beacons.Beacons;
import luke.beacons.commands.SubCommand;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import java.util.List;

public class reloadCommand extends SubCommand {
    @Override
    public String getName() {
        return "reload";
    }

    @Override
    public String getDescription() {
        return "Reloads the beacons Plugin";
    }

    @Override
    public String getSyntax() {
        return "/beacons reload";
    }

    @Override
    public void perform(CommandSender sender, String[] args) {
        Beacons.getPlugin().reloadConfig();
        sender.sendMessage(ChatColor.AQUA + "BeaconRange Config Reloaded!");
    }
}

