package com.pollogamer.superffa.stats.yml;

import com.pollogamer.superffa.Principal;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.util.UUID;

public class StatsManagerYML {

    private Principal plugin;

    public StatsManagerYML(Principal plugin){
        this.plugin = plugin;
    }

    public void addKill(Player p){
        UUID puuid = p.getUniqueId();
        int oldkills = plugin.getStats().getInt("players."+puuid+".kills");
        int newkills = oldkills+1;
        plugin.getStats().set("players."+puuid+".kills",newkills);
        plugin.saveStats();
    }
    public int getKills(Player p){
        UUID puuid = p.getUniqueId();
        int kills = plugin.getStats().getInt("players."+puuid+".kills");
        return kills;
    }
    public int getKillsOffline(OfflinePlayer p){
        UUID puuid = p.getUniqueId();
        int kills = plugin.getStats().getInt("players."+puuid+".kills");
        return kills;
    }
    public void addDeath(Player p){
        UUID puuid = p.getUniqueId();
        int olddeaths = plugin.getStats().getInt("players."+puuid+".deaths");
        int newdeaths = olddeaths+1;
        plugin.getStats().set("players."+puuid+".deaths", newdeaths);
        plugin.saveStats();
    }
    public int getDeaths(Player p){
        UUID puuid = p.getUniqueId();
        int deaths = plugin.getStats().getInt("players."+puuid+".deaths");
        return deaths;
    }
    public int getDeathsOffline(OfflinePlayer p){
        UUID puuid = p.getUniqueId();
        int deaths = plugin.getStats().getInt("players."+puuid+".deaths");
        return deaths;
    }
}
