package com.pollogamer.superffa;

import com.pollogamer.superffa.commands.*;
import com.pollogamer.superffa.gui.Guis;
import com.pollogamer.superffa.inventory.PlayerInventoryManager;
import com.pollogamer.superffa.listener.EventListener;
import com.pollogamer.superffa.manager.PlayerManager;
import com.pollogamer.superffa.manager.ScoreboardMan;
import com.pollogamer.superffa.stats.mysql.MySQLManager;
import com.pollogamer.superffa.stats.mysql.StatsManagerMySQL;
import com.pollogamer.superffa.stats.yml.StatsManagerYML;
import com.pollogamer.superffa.utils.Lang;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.sql.SQLException;
import java.sql.Statement;

public class Principal extends JavaPlugin{

    public static Principal plugin;
    private static FileConfiguration config,playerinventories,stats;
    PlayerManager PM;
    MySQLManager MySQLM;
    ScoreboardMan SM;
    Guis GUI;
    Lang lang;
    StatsManagerYML StatsYML;
    Boolean statsyml;
    PlayerInventoryManager InvManager;
    StatsManagerMySQL StatsMySQL;

    public Principal(){
        this.PM = new PlayerManager(this);
        this.SM = new ScoreboardMan(this);
        this.StatsYML = new StatsManagerYML(this);
        this.GUI = new Guis(this);
        this.lang = new Lang(this);
        this.MySQLM = new MySQLManager(this);
        this.InvManager = new PlayerInventoryManager(this);
        this.StatsMySQL = new StatsManagerMySQL(this);
    }

    @Override
    public void onEnable(){
        plugin = this;
        loadConfigs();
        if(getConfig().getString("TYPE").equalsIgnoreCase("YML")){
            statsyml = true;
        }else if(getConfig().getString("TYPE").equalsIgnoreCase("MySQL")){
            statsyml = false;
            getLogger().info("Connecting to MySQL...");
            try {
                this.getMySQL().openConnection();
                Statement statement = this.getMySQL().connection.createStatement();
                statement.executeUpdate("CREATE TABLE IF NOT EXISTS `Stats` (`UUID` VARCHAR(36), `username` VARCHAR(16),`kills` INT,`deaths` INT)");
                getLogger().info("Connected to MySQL!");
            } catch (ClassNotFoundException e) {
                getLogger().info("ERROR! Please report");
            } catch (SQLException e) {
                e.printStackTrace();
                getLogger().info("Check your MySQL Config");
            }
        }else{
            setEnabled(false);
            getLogger().info("Type is invalid... Usage MySQL or YML");
            return;
        }
        registerEvents();
        registerCommands();
    }

    public void registerEvents(){
        Bukkit.getPluginManager().registerEvents(new EventListener(this),this);
    }

    public void loadConfigs(){
           if(!getDataFolder().exists()){
               getDataFolder().mkdirs();
           }
               File configfile = new File(getDataFolder(),"config.yml");
               File statsfile = new File(getDataFolder(),"stats.yml");
               File inventoriesfile = new File(getDataFolder(),"player_inventories.yml");
               if(!configfile.exists()){
                   getLogger().info("Config file created!");
                   configfile.getParentFile().mkdirs();
                   saveResource("config.yml",false);
               }
               if(!statsfile.exists()){
                   statsfile.getParentFile().mkdirs();
                   saveResource("stats.yml",false);
                   getLogger().info("Stats file created!");
               }
               if(!inventoriesfile.exists()){
                   inventoriesfile.getParentFile().mkdirs();
                   saveResource("player_inventories.yml",false);
                   getLogger().info("Inventories file created!");
               }
               playerinventories = new YamlConfiguration();
               config = new YamlConfiguration();
               stats = new YamlConfiguration();
               try{
                   config.load(configfile);
                   getLogger().info("Config file loaded");
                   playerinventories.load(inventoriesfile);
                   getLogger().info("Inventories file loaded");
                   stats.load(statsfile);
                   getLogger().info("Stats file loaded");
               }catch (Exception e){
                   getLogger().info("An error ocurred loading the configs files! :/");
               }
        }

        public void saveStats(){
            File statsfile = new File(getDataFolder(),"stats.yml");
            try{
                getStats().save(statsfile);
            }catch (Exception e){
                e.printStackTrace();
                getLogger().info("Could not save the stats file!");
            }
        }
    public void saveInventories(){
        File inventoriesfile = new File(getDataFolder(),"player_inventories.yml");
        try{
            getInventories().save(inventoriesfile);
        }catch (Exception e){
            e.printStackTrace();
            getLogger().info("Could not save the inventories file!");
        }
    }

    public void registerCommands(){
        getCommand("ping").setExecutor(new CMDPing(this));
        getCommand("superffa").setExecutor(new CMDFFA(this));
        getCommand("ffamenu").setExecutor(new CMDFFAMenu(this));
        getCommand("stats").setExecutor(new CMDStats(this));
        getCommand("editkit").setExecutor(new CMDEditkit(this));
    }

    public static FileConfiguration getInventories(){return playerinventories;}

    public static FileConfiguration getStats(){ return stats;}

    public PlayerManager getPlayerManager(){
        return PM;
    }

    public ScoreboardMan getScoreboardManager(){
        return SM;
    }

    public Guis getGUI(){return GUI;}

    public Lang getMessages(){return lang;}

    public MySQLManager getMySQL(){return  MySQLM;}

    public StatsManagerYML getManagerStats(){return StatsYML;}

    public boolean getStatsBoolean(){return statsyml;}

    public PlayerInventoryManager getInventoryManager(){return InvManager;}

    public StatsManagerMySQL getManagerStatsSQL(){return StatsMySQL;}
}
