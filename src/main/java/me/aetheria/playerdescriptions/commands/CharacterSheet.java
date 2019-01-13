package me.aetheria.playerdescriptions.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CharacterSheet implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player)) System.out.println("Please run this as a player.");
        Player player = (Player) sender;
        if (args.length>0){
            player.sendMessage(args[0]);
        }
        else {
            player.sendMessage("Please specify an arg");
        }


        return false;
    }
}
