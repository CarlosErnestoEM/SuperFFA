package com.pollogamer.superffa.gui;

import com.pollogamer.superffa.Principal;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class Guis {

    private Principal plugin;
    public String type;

    public Guis(Principal plugin) {
        this.plugin = plugin;
    }

    public void setmenuffa(Player p) {
        int size = plugin.getConfig().getInt("GUI.MenuModes.size");
        String name = plugin.getConfig().getString("GUI.MenuModes.title");
        name = name.replaceAll("&", "§");
        Inventory modes = this.plugin.getServer().createInventory(null, size, name);
        //FFA Item
        int ffaid = plugin.getConfig().getInt("GUI.MenuModes.FFA.id");
        int ffaslot = plugin.getConfig().getInt("GUI.MenuModes.FFA.slot");
        int ffaplayers = Bukkit.getServer().getWorld("FFA").getPlayers().size();
        List<String> ffalore = new ArrayList<>();
        for (String s : plugin.getConfig().getStringList("GUI.MenuModes.FFA.lore")) {
            ffalore.add(ChatColor.translateAlternateColorCodes('&', s.replaceAll("%players%", ffaplayers + "")));
        }
            String ffaname = plugin.getConfig().getString("GUI.MenuModes.FFA.displayname");
            ffaname = ffaname.replaceAll("&", "§");
            ItemStack FFA = new ItemStack(Material.getMaterial(ffaid));
            ItemMeta ffameta = FFA.getItemMeta();
            ffameta.setDisplayName(ffaname);
            ffameta.setLore(ffalore);
            FFA.setItemMeta(ffameta);
            //BuildFFA Item
            int buildffaid = plugin.getConfig().getInt("GUI.MenuModes.BuildFFA.id");
            int buildffaslot = plugin.getConfig().getInt("GUI.MenuModes.BuildFFA.slot");
            int buildffaplayers = Bukkit.getServer().getWorld("BuildFFA").getPlayers().size();
            List<String> buildffalore = new ArrayList<>();
            for (String s : plugin.getConfig().getStringList("GUI.MenuModes.BuildFFA.lore")) {
            buildffalore.add(ChatColor.translateAlternateColorCodes('&', s.replaceAll("%players%", buildffaplayers + "")));
            }
            String buildffaname = plugin.getConfig().getString("GUI.MenuModes.BuildFFA.displayname");
            buildffaname = buildffaname.replaceAll("&", "§");
            ItemStack BuildFFA = new ItemStack(Material.getMaterial(buildffaid));
            ItemMeta buildmeta = FFA.getItemMeta();
            buildmeta.setDisplayName(buildffaname);
            buildmeta.setLore(buildffalore);
            BuildFFA.setItemMeta(buildmeta);
            //Combo Item
            int comboid = plugin.getConfig().getInt("GUI.MenuModes.ComboFFA.id");
            int comboslot = plugin.getConfig().getInt("GUI.MenuModes.ComboFFA.slot");
            int comboplayers = Bukkit.getServer().getWorld("ComboFFA").getPlayers().size();
            List<String> combolore = new ArrayList<>();
            for (String s : plugin.getConfig().getStringList("GUI.MenuModes.ComboFFA.lore")) {
            combolore.add(ChatColor.translateAlternateColorCodes('&', s.replaceAll("%players%", comboplayers + "")));
            }
            String comboname = plugin.getConfig().getString("GUI.MenuModes.ComboFFA.displayname");
            comboname = comboname.replaceAll("&", "§");
            ItemStack ComboFFA = new ItemStack(Material.getMaterial(comboid));
            ItemMeta combometa = FFA.getItemMeta();
            combometa.setDisplayName(comboname);
            combometa.setLore(combolore);
            ComboFFA.setItemMeta(combometa);
            //Set Menu Items
            modes.setItem(ffaslot, FFA);
            modes.setItem(buildffaslot, BuildFFA);
            modes.setItem(comboslot, ComboFFA);
            p.openInventory(modes);
    }

    public void setmenuselectkit(Player p){
        int size = plugin.getConfig().getInt("GUI.EditKit.size");
        String name = plugin.getConfig().getString("GUI.EditKit.title");
        name = name.replaceAll("&", "§");
        Inventory kitselect = this.plugin.getServer().createInventory(null, size, name);
        int ffaid = plugin.getConfig().getInt("GUI.EditKit.FFA.id");
        int ffaslot = plugin.getConfig().getInt("GUI.EditKit.FFA.slot");
        String ffaname = plugin.getConfig().getString("GUI.EditKit.FFA.displayname");
        ffaname = ffaname.replaceAll("&", "§");
        ItemStack FFA = new ItemStack(Material.getMaterial(ffaid));
        ItemMeta ffameta = FFA.getItemMeta();
        ffameta.setDisplayName(ffaname);
        FFA.setItemMeta(ffameta);
        int buildffaid = plugin.getConfig().getInt("GUI.EditKit.BuildFFA.id");
        int buildffaslot = plugin.getConfig().getInt("GUI.EditKit.BuildFFA.slot");
        String buildffaname = plugin.getConfig().getString("GUI.EditKit.BuildFFA.displayname");
        buildffaname = buildffaname.replaceAll("&", "§");
        ItemStack BuildFFA = new ItemStack(Material.getMaterial(buildffaid));
        ItemMeta buildmeta = FFA.getItemMeta();
        buildmeta.setDisplayName(buildffaname);
        BuildFFA.setItemMeta(buildmeta);
        int comboid = plugin.getConfig().getInt("GUI.EditKit.ComboFFA.id");
        int comboslot = plugin.getConfig().getInt("GUI.EditKit.ComboFFA.slot");
        String comboname = plugin.getConfig().getString("GUI.EditKit.ComboFFA.displayname");
        comboname = comboname.replaceAll("&", "§");
        ItemStack ComboFFA = new ItemStack(Material.getMaterial(comboid));
        ItemMeta combometa = FFA.getItemMeta();
        combometa.setDisplayName(comboname);
        ComboFFA.setItemMeta(combometa);
        this.type = "In_Menu";
        //Set Menu Items
        kitselect.setItem(ffaslot, FFA);
        kitselect.setItem(buildffaslot, BuildFFA);
        kitselect.setItem(comboslot, ComboFFA);
        p.openInventory(kitselect);
    }
    public void setmenusavekit(Player p,String type){
        this.type = type;
        int size = plugin.getConfig().getInt("GUI.SaveKit.size");
        String name = plugin.getConfig().getString("GUI.SaveKit.title");
        name = name.replaceAll("&", "§");
        Inventory savekit = this.plugin.getServer().createInventory(null, size, name);
        //Item Cancel
        int cancelid = plugin.getConfig().getInt("GUI.SaveKit.CancelKit.id");
        int cancelslot = plugin.getConfig().getInt("GUI.SaveKit.CancelKit.slot");
        int canceldata = plugin.getConfig().getInt("GUI.SaveKit.CancelKit.data");
        String cancelname = plugin.getConfig().getString("GUI.SaveKit.CancelKit.displayname");
        cancelname = cancelname.replaceAll("&", "§");
        ItemStack cancel = new ItemStack(Material.getMaterial(cancelid));
        ItemMeta cancelmeta =  cancel.getItemMeta();
        cancel.setDurability((short)canceldata);
        cancelmeta.setDisplayName(cancelname);
        cancel.setItemMeta(cancelmeta);
        //Item Save
        int saveid = plugin.getConfig().getInt("GUI.SaveKit.SaveKit.id");
        int saveslot = plugin.getConfig().getInt("GUI.SaveKit.SaveKit.slot");
        int savedata = plugin.getConfig().getInt("GUI.SaveKit.SaveKit.data");
        String savename = plugin.getConfig().getString("GUI.SaveKit.SaveKit.displayname");
        savename = savename.replaceAll("&", "§");
        ItemStack save = new ItemStack(Material.getMaterial(saveid));
        ItemMeta savemeta =  cancel.getItemMeta();
        save.setDurability((short)savedata);
        savemeta.setDisplayName(savename);
        save.setItemMeta(savemeta);
        //Set Menu Items
        savekit.setItem(cancelslot,cancel);
        savekit.setItem(saveslot,save);
        //Type
        if(type.equals("FFA")){
            plugin.getPlayerManager().setcompleteffakit(p);
        }else if(type.equals("BuildFFA")){
            plugin.getPlayerManager().setcompletebuildffakit(p);
        }else if(type.equals("ComboFFA")){
            plugin.getPlayerManager().setcompletecombokit(p);
        }else{
            p.sendMessage("Invalid type!");
        }
        p.openInventory(savekit);
    }
}
