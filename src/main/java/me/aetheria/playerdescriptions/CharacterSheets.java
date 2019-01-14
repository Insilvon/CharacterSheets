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
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

/**
 * Main class for the CharacterSheets plugin
 */
public final class CharacterSheets extends JavaPlugin implements Listener {

    @Override
    /**
     * Default Plugin Setup
     */
    public void onEnable() {
        System.out.println("[Character Sheets] Starting up!");

        //Get Commands
        getCommand("CharacterSheet").setExecutor(new CharacterSheet());
        getCommand("Description").setExecutor(new Description());

        //Setup default config
        getConfig().options().copyDefaults();
        saveDefaultConfig();

        //Setup the Players Directory, if none exists
        File testDir = new File(getDataFolder()+File.separator+"Players");
        if(!(testDir.exists())) testDir.mkdir();

        //Get Events
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
    @EventHandler
    /**
     * Fires when a player right-clicks another player
     */
    public void onSneakRC(PlayerInteractEntityEvent e){
        Player player = e.getPlayer();
        if (e.getRightClicked() instanceof Player){
            Player clicked = (Player) e.getRightClicked();
            String uuid = clicked.getUniqueId().toString();
            Description loadClass = new Description();
            player.sendMessage(loadClass.readDesc(uuid));
        }
    }
    @Override
    /**
     * Plugin Disable Shutdown Logic
     */
    public void onDisable() {
        System.out.println("[Character Sheets] Shutting down!");
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
