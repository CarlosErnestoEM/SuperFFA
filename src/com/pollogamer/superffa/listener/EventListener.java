package com.pollogamer.superffa.listener;

import com.pollogamer.superffa.Principal;
import org.bukkit.*;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.*;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.*;
import org.bukkit.event.weather.WeatherChangeEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import ru.tehkode.permissions.PermissionUser;
import ru.tehkode.permissions.bukkit.PermissionsEx;

import java.sql.ResultSet;
import java.util.UUID;

public class EventListener implements Listener {

    private Principal plugin;

    public EventListener(Principal plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void FoodChange(FoodLevelChangeEvent event) {
        if (plugin.getConfig().getBoolean("disablefoodlevel")) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void DropItem(PlayerDropItemEvent event) {
        if (event.getPlayer().hasPermission("superffa.bypass")) return;
        if (plugin.getConfig().getBoolean("disableitemdrop")) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent event) {
        event.getDrops().clear();
        if (plugin.getConfig().getBoolean("disabledeathmessage")) {
            event.setDeathMessage(null);
        }
        if(plugin.getConfig().getBoolean("dropGoldenApple")){
            if(event.getEntity().getWorld() == Bukkit.getWorld("BuildFFA")) return;
            ItemStack apple = new ItemStack(Material.GOLDEN_APPLE);
            event.getDrops().add(apple);
        }
        if(plugin.getConfig().getBoolean("dropGoldenHeadOnBuildFFA")){
            if(event.getEntity().getWorld() != Bukkit.getWorld("BuildFFA")) return;;
            ItemStack goldenhead = new ItemStack(Material.GOLDEN_APPLE);
            String goldenheadname = plugin.getConfig().getString("golden-head");
            goldenheadname = goldenheadname.replaceAll("&", "§");
            ItemMeta applemeta = goldenhead.getItemMeta();
            applemeta.setDisplayName(goldenheadname);
            goldenhead.setItemMeta(applemeta);
            event.getDrops().add(goldenhead);

        }
    }

    @EventHandler
    public void weather(WeatherChangeEvent event) {
        if (plugin.getConfig().getBoolean("disablerain")) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event){
        if (plugin.getConfig().getBoolean("disablejoinmessage")) {
            event.setJoinMessage(null);
            return;
        }
        String msgjoin = plugin.getConfig().getString("join");
        msgjoin = msgjoin.replaceAll("&", "§");
        msgjoin = msgjoin.replaceAll("%player%", event.getPlayer().getName());
        event.setJoinMessage(msgjoin);
    }

    @EventHandler
    public void onLeave(PlayerQuitEvent event){
        if (plugin.getConfig().getBoolean("disableleavemessage")) {
            event.setQuitMessage(null);
            return;
        }
        String msgleave = plugin.getConfig().getString("leave");
        msgleave = msgleave.replaceAll("&", "§");
        msgleave = msgleave.replaceAll("%player%", event.getPlayer().getName());
        event.setQuitMessage(msgleave);
    }

    @EventHandler
    public void onPickupItem(PlayerPickupItemEvent event){
        String goldenhead = plugin.getConfig().getString("golden-head");
        goldenhead = goldenhead.replaceAll("&", "§");
        if(event.getItem().getItemStack().getType() == Material.GOLDEN_APPLE){
            if(event.getItem().getItemStack().getItemMeta() == null) {
                event.getPlayer().getActivePotionEffects().clear();
                event.getItem().remove();
                event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.ABSORPTION,1200,1));
                event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION,80,1));
            } else if(event.getItem().getItemStack().getItemMeta().getDisplayName().equals(goldenhead)){
                event.getItem().remove();
                event.getPlayer().getActivePotionEffects().clear();
                event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.ABSORPTION,1200,1));
                event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION,180,2));
            }
        }
        if (event.getPlayer().hasPermission("superffa.bypass")) return;
        if (plugin.getConfig().getBoolean("disablepickupitem")){
            event.setCancelled(true);
        }
    }
    @EventHandler
    public void onJoin3(PlayerJoinEvent event){
        if(plugin.getStatsBoolean()){
        }else{
            Player p = event.getPlayer();
            UUID puuid = p.getUniqueId();
            boolean exist = false;
            try{
                ResultSet rs = plugin.getMySQL().Query("SELECT `kills` FROM `Stats` WHERE `UUID`='"+puuid+"'");
                while (rs.next()){
                    exist = true;
                }}catch (Exception e) {
                e.printStackTrace();
            }
            if(!exist){
                plugin.getMySQL().Update("INSERT INTO `Stats` (`UUID`, `username`,`kills`, `deaths`) VALUES ('"+puuid+"','"+p.getName()+"','"+0+"','"+0+"')");
            }
        }
    }
    @EventHandler
    public void eatGoldenHead(PlayerItemConsumeEvent event){
        if(!event.getItem().hasItemMeta()) return;
        String goldenhead = plugin.getConfig().getString("golden-head");
        goldenhead = goldenhead.replaceAll("&", "§");
        if(event.getItem().getItemMeta().getDisplayName().equals(goldenhead)){
            event.getPlayer().getActivePotionEffects().clear();
            event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION,180,2));
            event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.ABSORPTION,1200,2));
        }
    }

    @EventHandler
    public void disableFallDamage(EntityDamageEvent event) {
        if (plugin.getConfig().getBoolean("disablefalldamage")) {
            if (event.getCause() == EntityDamageEvent.DamageCause.FALL){
                event.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void BlockPlace(BlockPlaceEvent event) {
        if (event.getPlayer().getWorld() != Bukkit.getWorld("BuildFFA")) return;
        if (event.getPlayer().getGameMode() != GameMode.SURVIVAL) return;
        int seconds = plugin.getConfig().getInt("removeblocksinterval");
        new BukkitRunnable() {
            public void run() {
                Material replaced = event.getBlockReplacedState().getType();
                Location loc = event.getBlockPlaced().getLocation();
                loc.getBlock().setType(replaced);
            }
        }.runTaskLater(plugin, seconds * 20);
    }

    @EventHandler
    public void ChangeWorld(PlayerChangedWorldEvent event) {
        Player p = event.getPlayer();
        if (p.getWorld() == Bukkit.getWorld("BuildFFA")) {
            plugin.getPlayerManager().setbuildffakit(p);
            p.setMaximumNoDamageTicks(20);
        } else if (p.getWorld() == Bukkit.getWorld("FFA")) {
            plugin.getPlayerManager().setffakit(p);
            p.setMaximumNoDamageTicks(20);
        } else if (p.getWorld() == Bukkit.getWorld("ComboFFA")) {
            plugin.getPlayerManager().setcombokit(p);
            p.setMaximumNoDamageTicks(0);
        }else{
            p.setMaximumNoDamageTicks(20);
        }
    }
    @EventHandler
    public void HealthRegen(EntityRegainHealthEvent event){
        if(event.getEntityType() != EntityType.PLAYER) return;
        Player p = (Player) event.getEntity();
        World BuildFFA = Bukkit.getWorld("BuildFFA");
        if(p.getWorld() == BuildFFA && (event.getRegainReason() == EntityRegainHealthEvent.RegainReason.REGEN)){
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void Respawn(PlayerRespawnEvent event) {
        Player p = event.getPlayer();
        if (p.getWorld() == Bukkit.getWorld("BuildFFA")) {
            int x = plugin.getConfig().getInt("BuildFFA.x");
            int y = plugin.getConfig().getInt("BuildFFA.y");
            int z = plugin.getConfig().getInt("BuildFFA.z");
            float yaw = (float)plugin.getConfig().getDouble("BuildFFA.yaw");
            float pitch = (float)plugin.getConfig().getDouble("BuildFFA.pitch");
            Location BuildFFA = new Location(Bukkit.getWorld("BuildFFA"), x, y, z, yaw, pitch);
            event.setRespawnLocation(BuildFFA);
            plugin.getPlayerManager().setbuildffakit(p);
        } else if (p.getWorld() == Bukkit.getWorld("FFA")) {
            int x = plugin.getConfig().getInt("FFA.x");
            int y = plugin.getConfig().getInt("FFA.y");
            int z = plugin.getConfig().getInt("FFA.z");
            float yaw = (float)plugin.getConfig().getDouble("FFA.yaw");
            float pitch = (float)plugin.getConfig().getDouble("FFA.pitch");
            Location FFA = new Location(Bukkit.getWorld("FFA"), x, y, z, yaw, pitch);
            event.setRespawnLocation(FFA);
            plugin.getPlayerManager().setffakit(p);
        } else if (p.getWorld() == Bukkit.getWorld("ComboFFA")) {
            int x = plugin.getConfig().getInt("ComboFFA.x");
            int y = plugin.getConfig().getInt("ComboFFA.y");
            int z = plugin.getConfig().getInt("ComboFFA.z");
            float yaw = (float)plugin.getConfig().getDouble("ComboFFA.yaw");
            float pitch = (float)plugin.getConfig().getDouble("ComboFFA.pitch");
            Location ComboFFA = new Location(Bukkit.getWorld("ComboFFA"), x, y, z, yaw, pitch);
            event.setRespawnLocation(ComboFFA);
            plugin.getPlayerManager().setcombokit(p);
        }
    }

    @EventHandler
    public void onJoin2(PlayerJoinEvent event) {
        Player p = event.getPlayer();
        int x = plugin.getConfig().getInt("FFA.x");
        int y = plugin.getConfig().getInt("FFA.y");
        int z = plugin.getConfig().getInt("FFA.z");
        float yaw = (float)plugin.getConfig().getDouble("FFA.yaw");
        float pitch = (float)plugin.getConfig().getDouble("FFA.pitch");
        String prefix = plugin.getConfig().getString("prefix");
        prefix = prefix.replaceAll("&", "§");
        Location FFAClassic = new Location(Bukkit.getWorld("FFA"), x, y, z, yaw, pitch);
        //Check if exist world ffa,buildffa,combo and return
        if (Bukkit.getWorld("FFA") == null) {
            p.sendMessage(prefix + "The world FFA not found");
            return;
        } else {
            p.teleport(FFAClassic);
        }
        if (Bukkit.getWorld("BuildFFA") == null) {
            p.sendMessage(prefix + "The world BuildFFA not found");
            return;
        }
        if (Bukkit.getWorld("ComboFFA") == null) {
            p.sendMessage(prefix + "The world ComboFFA not found");
            return;
        }

        int seconds = plugin.getConfig().getInt("scoreboard.refresh-interval");
        //Set Scoreboard
        new BukkitRunnable() {
            public void run() {
                if (!p.isOnline()) {
                    cancel();
                    return;
                } else {
                    plugin.getScoreboardManager().setScoreboard(p);
                }
            }
        }.runTaskTimer(plugin, 0, 20 * seconds);
        plugin.getPlayerManager().setffakit(p);
    }

    @EventHandler
    public void BlockBreak(BlockBreakEvent event){
        if(plugin.getConfig().getBoolean("disableblockbreak: true")){
            if(event.getPlayer().hasPermission("superffa.bypass")) return;
            event.setCancelled(true);
        }
    }
    @EventHandler(priority = EventPriority.MONITOR)
    public void onChat2(AsyncPlayerChatEvent event) {
        if (plugin.getConfig().getBoolean("chat")) {
            String format = plugin.getConfig().getString("chat_format");
            PermissionUser user = PermissionsEx.getUser(event.getPlayer());
            String prefix = user.getPrefix();
            prefix = prefix.replaceAll("&", "§");
            format = format.replaceAll("%player%", event.getPlayer().getName());
            format = format.replaceAll("%prefix%",prefix);
            format = format.replaceAll("%message%", event.getMessage());
            event.setFormat(format);
        }
    }
    @EventHandler
    public void onClick(InventoryClickEvent event){
        String inventoryname = plugin.getConfig().getString("GUI.MenuModes.title");
        inventoryname = inventoryname.replaceAll("&", "§");
        String SelectKit = plugin.getConfig().getString("GUI.EditKit.title");
        SelectKit = SelectKit.replaceAll("&", "§");
        String SaveKit = plugin.getConfig().getString("GUI.SaveKit.title");
        SaveKit = SaveKit.replaceAll("&", "§");
        Inventory inv = event.getClickedInventory();
        if(inv.getName().equals(event.getWhoClicked().getInventory().getName())) return;
        ItemStack clicked = event.getCurrentItem();
        Player p = (Player)event.getWhoClicked();
        if(inv.getName().equals(inventoryname)){
            event.setCancelled(true);
            String ffaitem = plugin.getConfig().getString("GUI.MenuModes.FFA.displayname");
            ffaitem = ffaitem.replaceAll("&", "§");
            String builditem = plugin.getConfig().getString("GUI.MenuModes.BuildFFA.displayname");
            builditem = builditem.replaceAll("&", "§");
            String comboitem = plugin.getConfig().getString("GUI.MenuModes.ComboFFA.displayname");
            comboitem = comboitem.replaceAll("&", "§");
            if(clicked.getItemMeta().getDisplayName().equals(ffaitem)){
                int x = plugin.getConfig().getInt("FFA.x");
                int y = plugin.getConfig().getInt("FFA.y");
                int z = plugin.getConfig().getInt("FFA.z");
                float yaw = (float)plugin.getConfig().getDouble("FFA.yaw");
                float pitch = (float)plugin.getConfig().getDouble("FFA.pitch");
                Location FFALocation = new Location(Bukkit.getWorld("FFA"), x, y, z, yaw, pitch);
                p.teleport(FFALocation);
        }else if(clicked.getItemMeta().getDisplayName().equals(builditem)){
                int x = plugin.getConfig().getInt("BuildFFA.x");
                int y = plugin.getConfig().getInt("BuildFFA.y");
                int z = plugin.getConfig().getInt("BuildFFA.z");
                float yaw = (float)plugin.getConfig().getDouble("BuildFFA.yaw");
                float pitch = (float)plugin.getConfig().getDouble("BuildFFA.pitch");
                Location BuildFFALocation = new Location(Bukkit.getWorld("BuildFFA"), x, y, z, yaw, pitch);
                p.teleport(BuildFFALocation);
            } else if (clicked.getItemMeta().getDisplayName().equals(comboitem)){
                int x = plugin.getConfig().getInt("ComboFFA.x");
                int y = plugin.getConfig().getInt("ComboFFA.y");
                int z = plugin.getConfig().getInt("ComboFFA.z");
                float yaw = (float)plugin.getConfig().getDouble("ComboFFA.yaw");
                float pitch = (float)plugin.getConfig().getDouble("ComboFFA.pitch");
                Location ComboLocation = new Location(Bukkit.getWorld("ComboFFA"), x, y, z, yaw, pitch);
            p.teleport(ComboLocation);
            }
        }else if(inv.getName().equals(SelectKit)){
            event.setCancelled(true);
            String ffaitem = plugin.getConfig().getString("GUI.EditKit.FFA.displayname");
            ffaitem = ffaitem.replaceAll("&", "§");
            String builditem = plugin.getConfig().getString("GUI.EditKit.BuildFFA.displayname");
            builditem = builditem.replaceAll("&", "§");
            String comboitem = plugin.getConfig().getString("GUI.EditKit.ComboFFA.displayname");
            comboitem = comboitem.replaceAll("&", "§");
            if(clicked.getItemMeta().getDisplayName().equals(ffaitem)){
                plugin.getGUI().setmenusavekit(p,"FFA");
            }else if(clicked.getItemMeta().getDisplayName().equals(builditem)){
                plugin.getGUI().setmenusavekit(p,"BuildFFA");
            }else if(clicked.getItemMeta().getDisplayName().equals(comboitem)){
                plugin.getGUI().setmenusavekit(p,"ComboFFA");
            }
        }else if(inv.getName().equals(SaveKit)){
            String cancelname = plugin.getConfig().getString("GUI.SaveKit.CancelKit.displayname");
            cancelname = cancelname.replaceAll("&", "§");
            String savename = plugin.getConfig().getString("GUI.SaveKit.SaveKit.displayname");
            savename = savename.replaceAll("&", "§");
            if(clicked.getItemMeta().getDisplayName().equals(cancelname)){
                p.closeInventory();
            }else if(clicked.getItemMeta().getDisplayName().equals(savename)){
                if(plugin.getGUI().type.equals("FFA")){
                    plugin.getInventoryManager().setConfigCustomFFAKit(p);
                }else if(plugin.getGUI().type.equals("BuildFFA")){
                    plugin.getInventoryManager().setConfigCustomBuildFFAKit(p);
                }else if(plugin.getGUI().type.equals("ComboFFA")){
                    plugin.getInventoryManager().setConfigCustomComboFFAKit(p);
                }else{
                    p.sendMessage("Invalid type");
                }
                p.closeInventory();
                String prefix = plugin.getConfig().getString("prefix");
                prefix = prefix.replaceAll("&", "§");
                String kitsaved = plugin.getConfig().getString("editkit-saved");
                kitsaved = kitsaved.replaceAll("&", "§");
                p.sendMessage(prefix+kitsaved);
            }
        }
    }
    @EventHandler
    public void onCloseInventory(InventoryCloseEvent event){
        if(event.getInventory().getName().equalsIgnoreCase(event.getPlayer().getInventory().getName())) return;
        String SaveKit = plugin.getConfig().getString("GUI.SaveKit.title");
        SaveKit = SaveKit.replaceAll("&", "§");
        String SelectKit = plugin.getConfig().getString("GUI.EditKit.title");
        SelectKit = SelectKit.replaceAll("&", "§");
        Player p = (Player) event.getPlayer();
        if(event.getInventory().getName().equalsIgnoreCase(SelectKit)){
            new BukkitRunnable(){
                public void run(){
                    if(!(plugin.getGUI().type != "FFA"||plugin.getGUI().type != "BuildFFA"||plugin.getGUI().type != "ComboFFA")){
                        plugin.getGUI().type = "Waiting";
                    }
                }
            }.runTaskLater(plugin,10);
        }else if(event.getInventory().getName().equalsIgnoreCase(SaveKit)){
            if (p.getWorld() == Bukkit.getWorld("BuildFFA")) {
                plugin.getPlayerManager().setbuildffakit(p);
                p.updateInventory();
            } else if (p.getWorld() == Bukkit.getWorld("FFA")) {
                plugin.getPlayerManager().setffakit(p);
                p.updateInventory();
            } else if (p.getWorld() == Bukkit.getWorld("ComboFFA")) {
                plugin.getPlayerManager().setcombokit(p);
                p.updateInventory();
            }
            if((plugin.getGUI().type == "FFA"||plugin.getGUI().type == "BuildFFA"||plugin.getGUI().type == "ComboFFA")){
                plugin.getGUI().type = "Waiting";
            }
        }
        }
            @EventHandler
            public void onPlayerShot(EntityDamageByEntityEvent entity){
                Arrow arrow;
                if (entity.getDamager() instanceof Arrow && (arrow = (Arrow)entity.getDamager()).getShooter() instanceof Player){
                    Player player = (Player)arrow.getShooter();
                    Damageable obj = (Damageable)entity.getEntity();
                    if (obj instanceof Player){
                        Integer realHealth;
                    Player v = (Player)obj;
                    double ptviev = obj.getHealth();
                    Integer damage = (int)entity.getFinalDamage();
                    if (!obj.isDead() && (realHealth = Integer.valueOf((int)(ptviev - (double)damage.intValue()))) > 0){
                        String prefix = plugin.getConfig().getString("prefix");
                        prefix = prefix.replaceAll("&", "§");
                        String playershot = plugin.getConfig().getString("player-shot");
                        playershot = playershot.replaceAll("%player%",v.getName());
                        playershot = playershot.replaceAll("%health%",(float)realHealth/2+"");
                        playershot = playershot.replaceAll("&", "§");
                        player.sendMessage(prefix+playershot);
                    }
                    }
                }
            }
    @EventHandler
    public void AlMorir(PlayerDeathEvent event){
                Player player = event.getEntity();
                if (event.getEntity() instanceof Player && event.getEntity().getKiller() instanceof Player){
                    Player killer = player.getKiller();
                    String prefix = plugin.getConfig().getString("prefix");
                    prefix = prefix.replaceAll("&", "§");
                    double health = killer.getHealth()/2;
                    String life = String.format("%1$.1f",health);
                    String playerdeath = plugin.getConfig().getString("player-death");
                    playerdeath = playerdeath.replaceAll("%name%",killer.getName());
                    playerdeath = playerdeath.replaceAll("%health%",life+"");
                    playerdeath = playerdeath.replaceAll("&", "§");
                    String playerkill = plugin.getConfig().getString("player-killed");
                    playerkill = playerkill.replaceAll("%name%",player.getName());
                    playerkill = playerkill.replaceAll("&", "§");
                    player.sendMessage(prefix+playerdeath);
                    killer.sendMessage(prefix+playerkill);
                    if(plugin.getStatsBoolean()){
                        new BukkitRunnable(){
                            public void run(){
                                plugin.getManagerStats().addDeath(player);
                                plugin.getManagerStats().addKill(killer);
                            }
                        }.runTaskLaterAsynchronously(plugin,0);
                    }else{
                        new BukkitRunnable(){
                            public void run(){
                                plugin.getManagerStatsSQL().addDeath(player);
                                plugin.getManagerStatsSQL().addKill(killer);
                            }
                        }.runTaskLaterAsynchronously(plugin,0);
                    }
                }
    }
    @EventHandler
    public void ModeInteract(PlayerInteractEvent event){
        int selectorid = plugin.getConfig().getInt("modeselector-id");
        String selectorname = plugin.getConfig().getString("modeselector");
        selectorname = selectorname.replaceAll("&","§");
        if(!event.hasItem()) return;
        if(!event.getItem().hasItemMeta()) return;
        if(event.getItem().getType() != Material.getMaterial(selectorid)) return;
        if(event.getItem().getItemMeta().getDisplayName().equals(selectorname)){
            plugin.getGUI().setmenuffa(event.getPlayer());
        }
    }


}
