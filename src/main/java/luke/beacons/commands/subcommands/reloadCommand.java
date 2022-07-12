package luke.beacons.commands.subcommands;

import luke.beacons.BeaconRange;
import luke.beacons.commands.SubCommand;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

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
        BeaconRange.getPlugin().reloadConfig();
        sender.sendMessage(ChatColor.AQUA + "BeaconRange Config Reloaded!");
    }
}

