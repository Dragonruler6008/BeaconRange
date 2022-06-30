package luke.beacons.commands;

import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;

public abstract class SubCommand {

    public abstract  String getName();

    public  abstract String getDescription();

    public abstract  String getSyntax();

    public abstract void perform(CommandSender player, String[] args);

}