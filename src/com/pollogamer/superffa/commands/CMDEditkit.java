package com.pollogamer.superffa.commands;

import com.pollogamer.superffa.Principal;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CMDEditkit implements CommandExecutor{

    private Principal plugin;

    public CMDEditkit(Principal plugin){
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
        if(!p.hasPermission("superffa.editkit")){
            String noperm = plugin.getConfig().getString("no-permission");
            noperm = noperm.replaceAll("&", "§");
            p.sendMessage(prefix+noperm);
            return true;
        }
        String playerediting = plugin.getConfig().getString("editkit-editing");
        playerediting = playerediting.replaceAll("&", "§");
        if(plugin.getGUI().type == "In_Menu"){
            p.sendMessage(prefix+playerediting);
            return true;
        }
        if(plugin.getGUI().type == "FFA"){
            p.sendMessage(prefix+playerediting);
            return true;
        }
        if(plugin.getGUI().type == "BuildFFA"){
            p.sendMessage(prefix+playerediting);
            return true;
        }
        if(plugin.getGUI().type == "ComboFFA"){
            p.sendMessage(prefix+playerediting);
            return true;
        }
        String usage = plugin.getConfig().getString("editkit-usage");
        usage = usage.replaceAll("&", "§");
        if(args.length == 0){
            p.sendMessage(prefix+usage);
            return true;
        }else if(args.length == 1){
            if(args[0].equalsIgnoreCase("GUI")){
                plugin.getGUI().setmenuselectkit(p);
                return true;
            }else if(args[0].equalsIgnoreCase("FFA")){
                plugin.getGUI().setmenusavekit(p,"FFA");
            }else if(args[0].equalsIgnoreCase("BuildFFA")){
                plugin.getGUI().setmenusavekit(p,"BuildFFA");
            }else if(args[0].equalsIgnoreCase("ComboFFA")){
                plugin.getGUI().setmenusavekit(p,"ComboFFA");
            }else{
                p.sendMessage(prefix+usage);
            }
        }else{
            p.sendMessage(prefix+usage);
        }
        return true;
    }
}
