package com.pollogamer.superffa.manager;

import com.pollogamer.superffa.Principal;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.*;

import java.util.Iterator;

public class ScoreboardMan {

    private Principal plugin;

    public ScoreboardMan(Principal plugin){
        this.plugin = plugin;
    }


    public void setScoreboard(Player player){
        ScoreboardManager manager = Bukkit.getScoreboardManager();
        Scoreboard board = manager.getNewScoreboard();
        Objective objective = board.registerNewObjective("Scoreboard","dummy");
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        int scoretoset = this.plugin.getConfig().getStringList("scoreboard.lines").size();
        Iterator iterator = plugin.getConfig().getStringList("scoreboard.lines").iterator();
        while(iterator.hasNext()){
            String string = (String)iterator.next();
            string = string.replaceAll("%player%",player.getName());
            string = string.replaceAll("%mode%", player.getWorld().getName());
            string = string.replaceAll("%online%", Bukkit.getOnlinePlayers().size()+"");
            if(plugin.getStatsBoolean()) {
                int kills = plugin.getManagerStats().getKills(player);
                int deaths = plugin.getManagerStats().getDeaths(player);
                if(kills == 0 || deaths == 0){
                    string = string.replaceAll("%kdr%",0+"");
                }else{
                    int kdr = kills/deaths;
                    string = string.replaceAll("%kdr%", kdr+"");
                }
                string = string.replaceAll("%kills%", kills+"");
                string = string.replaceAll("%deaths%", deaths+"");
            }else{
                        int kills = plugin.getManagerStatsSQL().getKills(player);
                        int deaths = plugin.getManagerStatsSQL().getDeaths(player);
                if(kills == 0 || deaths == 0){
                    string = string.replaceAll("%kdr%",0+"");
                }else{
                    int kdr = kills/deaths;
                    //String kdr2 = String.format("1$.1f",kdr);
                    string = string.replaceAll("%kdr%", kdr+"");
                }
                string = string.replaceAll("%kills%", kills+"");
                string = string.replaceAll("%deaths%", deaths+"");
            }
            string = string.replaceAll("%ping%", plugin.getPlayerManager().getPing(player)+"");
            string = string.replaceAll("%empty%", ChatColor.RESET.toString());
            string = string.replaceAll("&","§");
            Score score = objective.getScore(Bukkit.getOfflinePlayer((String)string));
            score.setScore(scoretoset);
            --scoretoset;
        }
        String scorename = plugin.getConfig().getString("scoreboard.name");
        scorename = scorename.replaceAll("&","§");
        objective.setDisplayName(scorename);
        player.setScoreboard(board);
    }
}
