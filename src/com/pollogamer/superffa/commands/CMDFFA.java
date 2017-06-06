package com.pollogamer.superffa.commands;

import com.pollogamer.superffa.Principal;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CMDFFA implements CommandExecutor {

    private Principal plugin;

    public CMDFFA(Principal plugin){
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(!(sender instanceof Player)){
            sender.sendMessage("The command cannot be executed by the console!");
            return true;
        }
        String prefix = plugin.getConfig().getString("prefix");
        prefix = prefix.replaceAll("&", "§");
        Player p = (Player)sender;
        if(!p.hasPermission("superffa.admin")){
            String noperm = plugin.getConfig().getString("no-permission");
            noperm = noperm.replaceAll("&", "§");
            p.sendMessage(prefix+noperm);
            return  true;
        }
        if(args.length == 0){
            plugin.getMessages().sendhelppage(p);
            return true;
        }else if(args.length == 1){
            if(args[0].equalsIgnoreCase("setffaspawn")){
                if(p.getWorld() != Bukkit.getWorld("FFA")){
                    p.sendMessage(prefix+"You don´t in the FFA world!");
                    return true;
                }
                int x = p.getLocation().getBlockX();
                int y = p.getLocation().getBlockY();
                int z = p.getLocation().getBlockZ();
                float yaw = p.getLocation().getYaw();
                float pitch = p.getLocation().getPitch();
                plugin.getConfig().set("FFA.x",x);
                plugin.getConfig().set("FFA.y",y);
                plugin.getConfig().set("FFA.z",z);
                plugin.getConfig().set("FFA.yaw",yaw);
                plugin.getConfig().set("FFA.pitch",pitch);
                plugin.saveConfig();
                plugin.reloadConfig();
                String set = plugin.getConfig().getString("set-spawn");
                set = set.replaceAll("&","§");
                p.sendMessage(prefix+set);
                return true;
            }else if(args[0].equalsIgnoreCase("setbuildffaspawn")){
                if(p.getWorld() != Bukkit.getWorld("BuildFFA")){
                    p.sendMessage(prefix+"You don´t in the BuildFFA world!");
                    return true;
                }
                int x = p.getLocation().getBlockX();
                int y = p.getLocation().getBlockY();
                int z = p.getLocation().getBlockZ();
                float yaw = p.getLocation().getYaw();
                float pitch = p.getLocation().getPitch();
                plugin.getConfig().set("BuildFFA.x",x);
                plugin.getConfig().set("BuildFFA.y",y);
                plugin.getConfig().set("BuildFFA.z",z);
                plugin.getConfig().set("BuildFFA.yaw",yaw);
                plugin.getConfig().set("BuildFFA.pitch",pitch);
                plugin.saveConfig();
                plugin.reloadConfig();
                String set = plugin.getConfig().getString("set-spawn");
                set = set.replaceAll("&","§");
                p.sendMessage(prefix+set);
                return true;
            }else if(args[0].equalsIgnoreCase("setcomboffaspawn")){
                if(p.getWorld() != Bukkit.getWorld("ComboFFA")){
                    p.sendMessage(prefix+"You don´t in the ComboFFA world!");
                    return true;
                }
                int x = p.getLocation().getBlockX();
                int y = p.getLocation().getBlockY();
                int z = p.getLocation().getBlockZ();
                float yaw = p.getLocation().getYaw();
                float pitch = p.getLocation().getPitch();
                plugin.getConfig().set("ComboFFA.x",x);
                plugin.getConfig().set("ComboFFA.y",y);
                plugin.getConfig().set("ComboFFA.z",z);
                plugin.getConfig().set("ComboFFA.yaw",yaw);
                plugin.getConfig().set("ComboFFA.pitch",pitch);
                plugin.saveConfig();
                plugin.reloadConfig();
                String set = plugin.getConfig().getString("set-spawn");
                set = set.replaceAll("&","§");
                p.sendMessage(prefix+set);
                return true;
            }else if(args[0].equalsIgnoreCase("reload")){
                try {
                    plugin.reloadConfig();
                    String reloaded  = plugin.getConfig().getString("plugin-reloaded");
                    reloaded = reloaded.replaceAll("&","§");
                    p.sendMessage(prefix+reloaded);
                }catch (Exception e){
                    e.printStackTrace();
                    String configerror = plugin.getConfig().getString("plugin-reloaded-error");
                    configerror = configerror.replaceAll("&","§");
                    p.sendMessage(prefix+configerror);
                }

                return true;
            }else{
                plugin.getMessages().sendhelppage(p);
            }
        }else{
            plugin.getMessages().sendhelppage(p);
            return true;
        }






        return true;
    }
}
