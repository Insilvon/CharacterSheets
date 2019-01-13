package me.aetheria.playerdescriptions;

import me.aetheria.playerdescriptions.commands.CharacterSheet;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public final class PlayerDescriptions extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        System.out.println("[Character Sheets] Starting up!");
        getCommand("CharacterSheet").setExecutor(new CharacterSheet());

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

}
