package com.pollogamer.superffa.inventory;

import com.pollogamer.superffa.Principal;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.io.IOException;
import java.util.UUID;

public class PlayerInventoryManager {

    private Principal plugin;

    public PlayerInventoryManager(Principal plugin){
        this.plugin = plugin;
    }

    public void setCustomFFAKit(Player p){
        UUID puuid = p.getUniqueId();
        String inv64 = plugin.getInventories().getString("players."+puuid+".FFA");
        Inventory inv = null;
        try {
            inv = InventoryToBase64.fromBase64(inv64);
            p.getInventory().setContents(inv.getContents());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void setConfigCustomFFAKit(Player p){
        UUID puuid = p.getUniqueId();
        plugin.getInventories().set("players."+puuid+".FFA",InventoryToBase64.toBase64(p.getInventory()));
        plugin.saveInventories();
    }
    public boolean CustomFFAKitExist(Player p){
        UUID puuid = p.getUniqueId();
        if(plugin.getInventories().getString("players."+puuid+".FFA") != null){
            return true;
        }else{
            return false;
        }
    }



    public void setCustomBuildFFAKit(Player p){
        UUID puuid = p.getUniqueId();
        String inv64 = plugin.getInventories().getString("players."+puuid+".BuildFFA");
        Inventory inv = null;
        try {
            inv = InventoryToBase64.fromBase64(inv64);
            p.getInventory().setContents(inv.getContents());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void setConfigCustomBuildFFAKit(Player p){
        UUID puuid = p.getUniqueId();
        plugin.getInventories().set("players."+puuid+".BuildFFA",InventoryToBase64.toBase64(p.getInventory()));
        plugin.saveInventories();
    }
    public boolean CustomBuildFFAKitExist(Player p){
        UUID puuid = p.getUniqueId();
        if(plugin.getInventories().getString("players."+puuid+".BuildFFA") != null){
            return true;
        }else{
            return false;
        }
    }



    public void setCustomComboFFAKit(Player p){
        UUID puuid = p.getUniqueId();
        String inv64 = plugin.getInventories().getString("players."+puuid+".ComboFFA");
        Inventory inv = null;
        try {
            inv = InventoryToBase64.fromBase64(inv64);
            p.getInventory().setContents(inv.getContents());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void setConfigCustomComboFFAKit(Player p){
        UUID puuid = p.getUniqueId();
        plugin.getInventories().set("players."+puuid+".ComboFFA",InventoryToBase64.toBase64(p.getInventory()));
        plugin.saveInventories();
    }
    public boolean CustomComboFFAKitExist(Player p){
        UUID puuid = p.getUniqueId();
        if(plugin.getInventories().getString("players."+puuid+".ComboFFA") != null){
            return true;
        }else{
            return false;
        }
    }


}
