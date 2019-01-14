package me.aetheria.playerdescriptions;

import me.aetheria.playerdescriptions.commands.CharacterSheet;
import me.aetheria.playerdescriptions.commands.Description;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

public final class CharacterSheets extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        // Plugin startup logic
        System.out.println("[Character Sheets] Starting up!");
        getCommand("CharacterSheet").setExecutor(new CharacterSheet());
        getCommand("Description").setExecutor(new Description());

        getConfig().options().copyDefaults();
        saveDefaultConfig();

        File testDir = new File(getDataFolder()+File.separator+"Players");
        if(!(testDir.exists())) testDir.mkdir();

        getServer().getPluginManager().registerEvents(this,this);
    }
    @EventHandler
    /**
     * Generates a new UUID.YML file for an incoming player.
     * Default items: Player: Name, Description. (FIND A WAY TO LOOK IN SKILLAPI?)
     */
    public void onJoin(PlayerJoinEvent event){
        File myFile = new File(getDataFolder()+ File.separator + "Players" + File.separator + event.getPlayer().getUniqueId().toString()+".yml");
        if (!myFile.exists()) {
            try {
                myFile.createNewFile();
                FileConfiguration fc = YamlConfiguration.loadConfiguration(myFile);
                //SET DEFAULT ITEMS HERE
                fc.set("Player.Name", event.getPlayer().getDisplayName());
                fc.set("Player.Desc", "Basic Description!");
                fc.save(myFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

//    @Override
//    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
//        if (command.toString().equals("char")){
//            if (sender instanceof Player){
//
//            }
//        }
//        return false;
//    }
}
