package me.aetheria.playerdescriptions.commands;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;

public class Description implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            System.out.println("Please run this as a player.");
            return false;
        }
        Player player = (Player) sender;
        player.sendMessage("Message received boss!");
        int length = args.length;
        if (length>0){
            String uuid = ((Player) sender).getUniqueId().toString();
            switch(args[0].toLowerCase()){
                case "set":
                    if (length<2){
                        player.sendMessage("You need to specify what to set!");
                        return true;
                    }
                    else {
                        String data = "";
                        int i = 1;
                        for (i = 1; i<length-1; i++){
                            data+=args[i]+" ";
                        }
                        data+=args[i];
                        setDesc(uuid, data);
                        player.sendMessage("Character Description set!");
                    }
                    break;
                case "read":
                    player.sendMessage(readDesc(uuid));
                    break;
                case "add":
                    String data = "";
                    int i = 1;
                    for (i = 1; i<length-1; i++){
                        data+=args[i]+" ";
                    }
                    data+=args[i];
                    addDesc(uuid, data);
                    player.sendMessage("Character Description appended!");
                    break;
                default:
                    break;
            }
        }
        return true;
    }
    private void setDesc(String uuid, String data){
        File file = new File("plugins"+File.separator+"CharacterSheets"+File.separator+"Players"+File.separator+uuid+".yml");
        FileConfiguration fc = YamlConfiguration.loadConfiguration(file);
        fc.set("Player.Desc", data);
        try {
            fc.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void addDesc(String uuid, String data){
        String original = readDesc(uuid);
        original += " "+data;
        setDesc(uuid, original);
    }
    private String readDesc(String uuid){
        File file = new File("plugins"+File.separator+"CharacterSheets"+File.separator+"Players"+File.separator+uuid+".yml");
        FileConfiguration fc = YamlConfiguration.loadConfiguration(file);
        String data = fc.getString("Player.Desc");
        return data;
    }
}
