package com.pollogamer.superffa.commands;

import com.pollogamer.superffa.Principal;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CMDFFAMenu implements CommandExecutor {

    private Principal  plugin;

    public CMDFFAMenu(Principal plugin){
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
        if(!(sender instanceof Player)){
            sender.sendMessage("The command cannot be executed by the console!");
            return true;
        }
        Player p = (Player)sender;
        String prefix = plugin.getConfig().getString("prefix");
        prefix = prefix.replaceAll("&", "§");
        if(!p.hasPermission("superffa.menu")){
            String noperm = plugin.getConfig().getString("no-permission");
            noperm = noperm.replaceAll("&", "§");
            p.sendMessage(prefix+noperm);
            return true;
        }
        if(Bukkit.getWorld("FFA") == null){
            p.sendMessage(prefix+"ERROR! The world FFA not found");
            return true;
        }
        if(Bukkit.getWorld("BuildFFA") == null){
            p.sendMessage(prefix+"ERROR! The world BuildFFA not found");
            return true;
        }
        if(Bukkit.getWorld("ComboFFA") == null){
            p.sendMessage(prefix+"ERROR! The world ComboFFA not found");
            return true;
        }
        plugin.getGUI().setmenuffa(p);
        return true;
    }
}
