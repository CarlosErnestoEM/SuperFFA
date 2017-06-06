package com.pollogamer.superffa.commands;

import com.pollogamer.superffa.Principal;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CMDPing implements CommandExecutor {

    private Principal plugin;

    public CMDPing(Principal plugin){
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command  cmd, String label, String[] args) {
        if(!(sender instanceof Player)){
            sender.sendMessage("The command cannot be executed by the console!");
            return true;
        }
        String prefix = plugin.getConfig().getString("prefix");
        prefix = prefix.replaceAll("&", "§");
        Player p = (Player)sender;
        if(!p.hasPermission("superffa.ping")){
            String noperm = plugin.getConfig().getString("no-permission");
            noperm = noperm.replaceAll("&", "§");
            p.sendMessage(prefix+noperm);
            return true;
        }
        if(args.length == 0){
            String pingme = plugin.getConfig().getString("ping");
            pingme = pingme.replaceAll("%ping%",plugin.getPlayerManager().getPing(p)+"");
            pingme = pingme.replaceAll("&","§");
            p.sendMessage(prefix+pingme);
            return true;
        }else if(args.length == 1){
            Player obj = Bukkit.getPlayer(args[0]);
            if(obj == null){
                String pingnoonline = plugin.getConfig().getString("ping-no-online");
                pingnoonline = pingnoonline.replaceAll("%player%", args[0]);
                pingnoonline = pingnoonline.replaceAll("&","§");
                p.sendMessage(prefix+pingnoonline);
                return true;
            }
            String pingother = plugin.getConfig().getString("ping-other");
            pingother = pingother.replaceAll("%player%",obj.getName());
            pingother = pingother.replaceAll("%ping%",plugin.getPlayerManager().getPing(obj)+"");
            pingother = pingother.replaceAll("&","§");
            p.sendMessage(prefix+pingother);
        }else{
            String pingusage = plugin.getConfig().getString("ping-usage");
            pingusage = pingusage.replaceAll("&","§");
            p.sendMessage(prefix+pingusage);
        }





        return true;
    }
}
