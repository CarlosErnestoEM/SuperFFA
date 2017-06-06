package com.pollogamer.superffa.commands;


import com.pollogamer.superffa.Principal;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CMDStats implements CommandExecutor{

    private Principal plugin;

    public CMDStats(Principal plugin){
        this.plugin = plugin;
    }


    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
        if(!(sender instanceof Player)){
            sender.sendMessage("The command cannot be executed by the console!");
            return true;
        }
        Player p = (Player)sender;
        String usagestats = plugin.getConfig().getString("stats-usage");
        String prefix = plugin.getConfig().getString("prefix");
        usagestats = usagestats.replaceAll("&", "§");
        prefix = prefix.replaceAll("&", "§");
        if(!p.hasPermission("superffa.stats")){
            String noperm = plugin.getConfig().getString("no-permission");
            noperm = noperm.replaceAll("&", "§");
            p.sendMessage(prefix+noperm);
            return true;
        }
        if(args.length == 0){
            if(plugin.getStatsBoolean()){
                for (String s : plugin.getConfig().getStringList("stats")) {
                    s = s.replaceAll("%kills%", plugin.getManagerStats().getKills(p)+"");
                    s = s.replaceAll("%deaths%", plugin.getManagerStats().getDeaths(p)+"");
                    s = s.replaceAll("%player%", p.getName());
                    s = s.replaceAll("&", "§");
                    p.sendMessage(s);
                }
            }else{
                //Put stats per mysql
            }
        }else if(args.length == 1){
            Player obj = Bukkit.getPlayer(args[0]);
            if(obj != null){
                if(plugin.getStatsBoolean()){
                    for (String s : plugin.getConfig().getStringList("stats")) {
                        s = s.replaceAll("%kills%", plugin.getManagerStats().getKills(obj)+"");
                        s = s.replaceAll("%deaths%", plugin.getManagerStats().getDeaths(obj)+"");
                        s = s.replaceAll("%player%", obj.getName());
                        s = s.replaceAll("&", "§");
                        p.sendMessage(s);
                    }
                }else{
                    //Put stats per mysql
                }
            }else{
                OfflinePlayer obj2 = Bukkit.getOfflinePlayer(args[0]);
                if(plugin.getStatsBoolean()){
                    for (String s : plugin.getConfig().getStringList("stats")) {
                        s = s.replaceAll("%kills%", plugin.getManagerStats().getKillsOffline(obj2)+"");
                        s = s.replaceAll("%deaths%", plugin.getManagerStats().getDeathsOffline(obj2)+"");
                        s = s.replaceAll("%player%", obj2.getName());
                        s = s.replaceAll("&", "§");
                        p.sendMessage(s);
                    }
                }else{
                    //Put stats per mysql
                }
            }
        }else{
            p.sendMessage(prefix+usagestats);
        }
        return true;
    }
}
