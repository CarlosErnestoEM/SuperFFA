package com.pollogamer.superffa.stats.mysql;

import com.pollogamer.superffa.Principal;
import org.bukkit.entity.Player;

import java.sql.ResultSet;
import java.util.UUID;

public class StatsManagerMySQL{

    private Principal plugin;

    public StatsManagerMySQL(Principal plugin){
        this.plugin = plugin;
    }

    public  void addKill(Player p) {
        UUID puuid = p.getUniqueId();
        int kills = getKills(p);
        kills += 1;
        plugin.getMySQL().Update("UPDATE `Stats` SET `kills`='" + kills + "' WHERE `UUID`='" + puuid + "'");
    }
        public int getKills(Player p){
            UUID puuid = p.getUniqueId();
            int kills = 0;
            try{
                ResultSet rs = plugin.getMySQL().Query("SELECT `kills` FROM `Stats` WHERE `UUID`='" + puuid + "'");
                while (rs.next()) {
                    kills = rs.getInt(1);
                }
            }
            catch (Exception err) {
                err.printStackTrace();
            }
            return kills;
        }

    public  void addDeath(Player p) {
        UUID puuid = p.getUniqueId();
        int deaths = getDeaths(p);
        deaths += 1;
        plugin.getMySQL().Update("UPDATE `Stats` SET `deaths`='" + deaths + "' WHERE `UUID`='" + puuid + "'");
    }
    public int getDeaths(Player p){
        UUID puuid = p.getUniqueId();
        int deaths = 0;
        try{
            ResultSet rs = plugin.getMySQL().Query("SELECT `deaths` FROM `Stats` WHERE `UUID`='" + puuid + "'");
            while (rs.next()) {
                deaths = rs.getInt(1);
            }
        }
        catch (Exception err) {
            err.printStackTrace();
        }
        return deaths;
    }
}
