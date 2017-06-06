package com.pollogamer.superffa.utils;

import com.minebone.itemstack.ItemStackBuilder;
import com.pollogamer.superffa.Principal;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class Lang {

    public static String guimodesname;
    public static int guimodessize;

    public static ItemStackBuilder guimodesffa;
    public static int guimodesffaslot;

    public Lang(){
        init();
    }

    public void init(){
        guimodesname = getString("GUI.MenuModes.title");
        guimodessize = getInt("GUI.MenuModes.size");
        guimodesffaslot = getInt("GUI.MenuModes.FFA.slot");
        try {
            guimodesffa = new ItemStackBuilder(Material.getMaterial(getInt("GUI.MenuModes.FFA.id"))).setName(getString("GUI.MenuModes.FFA.displayname")).setStackData((short)getInt("GUI.MenuModes.FFA.data")).setLore(getListColorized("GUI.MenuModes.FFA.lore"));
        }catch (Exception e){}
    }

    public void sendhelppage(Player p){
        p.sendMessage("§7=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
        p.sendMessage("§6               SUPER FFA COMMANDS");
        p.sendMessage("");
        p.sendMessage("§eSet your ffa spawn with /ffa setffaspawn");
        p.sendMessage("§eSet your BuildFFA Spawn with /ffa setbuildffaspawn");
        p.sendMessage("§eSet your ComboFFA spawn with /ffa setcomboffaspawn");
        p.sendMessage("§eReload the config file with /ffa reload");
        p.sendMessage("");
        p.sendMessage("§7=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
    }

    public List<String> getListColorized(String msg){
        List<String> list = new ArrayList<>();
        for(String s : Principal.plugin.getConfig().getStringList(msg)){
            s = ChatColor.translateAlternateColorCodes('&',s);
            list.add(s);
        }
        return list;
    }

    public int getInt(String  msg){
        return Principal.plugin.getConfig().getInt(msg);
    }

    public String getString(String msg){
        return ChatColor.translateAlternateColorCodes('&',Principal.plugin.getConfig().getString(msg));
    }

}
