package com.pollogamer.superffa.manager;

import com.pollogamer.superffa.Principal;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.regex.Pattern;

public class PlayerManager {

    private Principal plugin;

    public PlayerManager(Principal plugin){
        this.plugin = plugin;
    }

    public void setffakit(Player p){
        p.getInventory().clear();
        if(plugin.getInventoryManager().CustomFFAKitExist(p)){
            plugin.getInventoryManager().setCustomFFAKit(p);
        }else{
            int selectorid = plugin.getConfig().getInt("modeselector-id");
            String selectorname = plugin.getConfig().getString("modeselector");
            selectorname = selectorname.replaceAll("&","§");
            ItemStack selectmode = new ItemStack(Material.getMaterial(selectorid));
            ItemMeta selectmeta = selectmode.getItemMeta();
            selectmeta.setDisplayName(selectorname);
            selectmode.setItemMeta(selectmeta);
            ItemStack flint = new ItemStack(Material.FLINT_AND_STEEL);
            flint.setDurability((short)0);
            ItemStack arrow = new ItemStack(Material.ARROW);
            arrow.setAmount(64);
            p.getInventory().addItem(new ItemStack(Material.STONE_SWORD));
            p.getInventory().addItem(new ItemStack(Material.FISHING_ROD));
            p.getInventory().addItem(new ItemStack(Material.BOW));
            p.getInventory().addItem(flint);
            p.getInventory().setItem(8,selectmode);
            p.getInventory().setItem(9, arrow);
        }
        p.getInventory().setHelmet(new ItemStack(Material.IRON_HELMET));
        p.getInventory().setChestplate(new ItemStack(Material.IRON_CHESTPLATE));
        p.getInventory().setLeggings(new ItemStack(Material.IRON_LEGGINGS));
        p.getInventory().setBoots(new ItemStack(Material.IRON_BOOTS));
    }
    public void setbuildffakit(Player p){
        p.getInventory().clear();
        if(plugin.getInventoryManager().CustomBuildFFAKitExist(p)){
            plugin.getInventoryManager().setCustomBuildFFAKit(p);
        }else{
            int selectorid = plugin.getConfig().getInt("modeselector-id");
            String selectorname = plugin.getConfig().getString("modeselector");
            selectorname = selectorname.replaceAll("&","§");
            ItemStack selectmode = new ItemStack(Material.getMaterial(selectorid));
            ItemMeta selectmeta = selectmode.getItemMeta();
            selectmeta.setDisplayName(selectorname);
            selectmode.setItemMeta(selectmeta);
            String goldenheadname = plugin.getConfig().getString("golden-head");
            goldenheadname = goldenheadname.replaceAll("&", "§");
            ItemStack sword = new ItemStack(Material.DIAMOND_SWORD);
            sword.addEnchantment(Enchantment.DAMAGE_ALL,3);
            ItemStack bow = new ItemStack(Material.BOW);
            bow.addEnchantment(Enchantment.ARROW_DAMAGE,3);
            ItemStack goldenhead = new ItemStack(Material.GOLDEN_APPLE);
            ItemMeta goldenheadmeta = goldenhead.getItemMeta();
            goldenhead.setAmount(3);
            goldenheadmeta.setDisplayName(goldenheadname);
            goldenhead.setItemMeta(goldenheadmeta);
            ItemStack goldenapple = new ItemStack(Material.GOLDEN_APPLE);
            ItemStack food = new ItemStack(Material.COOKED_BEEF);
            food.setAmount(16);
            goldenapple.setAmount(6);
            ItemStack flint = new ItemStack(Material.FLINT_AND_STEEL);
            flint.setDurability((short)0);
            ItemStack wood = new ItemStack(Material.WOOD);
            ItemStack arrow = new ItemStack(Material.ARROW);
            arrow.setAmount(64);
            wood.setAmount(64);
            ItemStack stone = new ItemStack(Material.COBBLESTONE);
            stone.setAmount(64);
            p.getInventory().addItem(sword);
            p.getInventory().addItem(new ItemStack(Material.FISHING_ROD));
            p.getInventory().addItem(bow);
            p.getInventory().addItem(food);
            p.getInventory().addItem(flint);
            p.getInventory().addItem(wood);
            p.getInventory().addItem(goldenapple);
            p.getInventory().addItem(goldenhead);
            p.getInventory().setItem(8,selectmode);
            p.getInventory().setItem(9,arrow);
            p.getInventory().setItem(10,stone);
        }
        ItemStack helmet = new ItemStack(Material.DIAMOND_HELMET);
        helmet.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL,2);
        helmet.addEnchantment(Enchantment.DURABILITY,2);
        ItemStack chestplate = new ItemStack(Material.DIAMOND_CHESTPLATE);
        chestplate.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL,2);
        chestplate.addEnchantment(Enchantment.DURABILITY,2);
        ItemStack leggins = new ItemStack(Material.DIAMOND_LEGGINGS);
        leggins.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL,2);
        leggins.addEnchantment(Enchantment.DURABILITY,2);
        ItemStack boots = new ItemStack(Material.DIAMOND_BOOTS);
        boots.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL,2);
        boots.addEnchantment(Enchantment.DURABILITY,2);
        p.getInventory().setArmorContents(null);
        p.getInventory().setHelmet(helmet);
        p.getInventory().setChestplate(chestplate);
        p.getInventory().setLeggings(leggins);
        p.getInventory().setBoots(boots);
    }
    public void setcombokit(Player p){
        p.getInventory().clear();
        if(plugin.getInventoryManager().CustomComboFFAKitExist(p)){
            plugin.getInventoryManager().setCustomComboFFAKit(p);
        }else{
            ItemStack helmet = new ItemStack(Material.DIAMOND_HELMET);
            helmet.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL,2);
            helmet.addEnchantment(Enchantment.DURABILITY,2);
            ItemStack chestplate = new ItemStack(Material.DIAMOND_CHESTPLATE);
            chestplate.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL,2);
            chestplate.addEnchantment(Enchantment.DURABILITY,2);
            ItemStack leggins = new ItemStack(Material.DIAMOND_LEGGINGS);
            leggins.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL,2);
            leggins.addEnchantment(Enchantment.DURABILITY,2);
            ItemStack boots = new ItemStack(Material.DIAMOND_BOOTS);
            boots.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL,2);
            boots.addEnchantment(Enchantment.DURABILITY,2);
            int selectorid = plugin.getConfig().getInt("modeselector-id");
            String selectorname = plugin.getConfig().getString("modeselector");
            selectorname = selectorname.replaceAll("&","§");
            ItemStack selectmode = new ItemStack(Material.getMaterial(selectorid));
            ItemMeta selectmeta = selectmode.getItemMeta();
            selectmeta.setDisplayName(selectorname);
            selectmode.setItemMeta(selectmeta);
            ItemStack sword = new ItemStack(Material.DIAMOND_SWORD);
            sword.addEnchantment(Enchantment.DAMAGE_ALL,3);
            ItemStack goldenapple = new ItemStack(Material.GOLDEN_APPLE);
            goldenapple.setDurability((short)1);
            goldenapple.setAmount(64);
            p.getInventory().clear();
            p.getInventory().setArmorContents(null);
            p.getInventory().setHelmet(helmet);
            p.getInventory().setChestplate(chestplate);
            p.getInventory().setLeggings(leggins);
            p.getInventory().setBoots(boots);
            p.getInventory().setItem(0,sword);
            p.getInventory().setItem(1,goldenapple);
            p.getInventory().setItem(2,helmet);
            p.getInventory().setItem(3,chestplate);
            p.getInventory().setItem(4,leggins);
            p.getInventory().setItem(5,boots);
            p.getInventory().setItem(8,selectmode);
        }
        ItemStack helmet = new ItemStack(Material.DIAMOND_HELMET);
        helmet.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL,2);
        helmet.addEnchantment(Enchantment.DURABILITY,2);
        ItemStack chestplate = new ItemStack(Material.DIAMOND_CHESTPLATE);
        chestplate.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL,2);
        chestplate.addEnchantment(Enchantment.DURABILITY,2);
        ItemStack leggins = new ItemStack(Material.DIAMOND_LEGGINGS);
        leggins.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL,2);
        leggins.addEnchantment(Enchantment.DURABILITY,2);
        ItemStack boots = new ItemStack(Material.DIAMOND_BOOTS);
        boots.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL,2);
        boots.addEnchantment(Enchantment.DURABILITY,2);
        p.getInventory().setHelmet(helmet);
        p.getInventory().setChestplate(chestplate);
        p.getInventory().setLeggings(leggins);
        p.getInventory().setBoots(boots);
    }

    public int getPing(Player player) {
        try {
            int ping = 0;
            Class<?> craftPlayer = Class.forName("org.bukkit.craftbukkit." + getServerVersion() + ".entity.CraftPlayer");
            Object converted = craftPlayer.cast(player);
            Method handle = converted.getClass().getMethod("getHandle", new Class[0]);
            Object entityPlayer = handle.invoke(converted, new Object[0]);
            Field pingField = entityPlayer.getClass().getField("ping");
            ping = pingField.getInt(entityPlayer);
            return ping;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    private String getServerVersion() {
        Pattern brand = Pattern.compile("(v|)[0-9][_.][0-9][_.][R0-9]*");
        String pkg = Bukkit.getServer().getClass().getPackage().getName();
        String version = pkg.substring(pkg.lastIndexOf('.') + 1);
        if (!brand.matcher(version).matches()) {
            version = "";
        }
        return version;
    }

    public void setcompleteffakit(Player p){
        p.getInventory().clear();
        int selectorid = plugin.getConfig().getInt("modeselector-id");
        String selectorname = plugin.getConfig().getString("modeselector");
        selectorname = selectorname.replaceAll("&","§");
        ItemStack selectmode = new ItemStack(Material.getMaterial(selectorid));
        ItemMeta selectmeta = selectmode.getItemMeta();
        selectmeta.setDisplayName(selectorname);
        selectmode.setItemMeta(selectmeta);
        ItemStack flint = new ItemStack(Material.FLINT_AND_STEEL);
        flint.setDurability((short)0);
        ItemStack arrow = new ItemStack(Material.ARROW);
        arrow.setAmount(64);
        p.getInventory().addItem(new ItemStack(Material.STONE_SWORD));
        p.getInventory().addItem(new ItemStack(Material.FISHING_ROD));
        p.getInventory().addItem(new ItemStack(Material.BOW));
        p.getInventory().addItem(flint);
        p.getInventory().setItem(8,selectmode);
        p.getInventory().setItem(9, arrow);
    }
    public void setcompletebuildffakit(Player p){
        int selectorid = plugin.getConfig().getInt("modeselector-id");
        String selectorname = plugin.getConfig().getString("modeselector");
        selectorname = selectorname.replaceAll("&","§");
        ItemStack selectmode = new ItemStack(Material.getMaterial(selectorid));
        ItemMeta selectmeta = selectmode.getItemMeta();
        selectmeta.setDisplayName(selectorname);
        selectmode.setItemMeta(selectmeta);
        String goldenheadname = plugin.getConfig().getString("golden-head");
        goldenheadname = goldenheadname.replaceAll("&", "§");
        ItemStack sword = new ItemStack(Material.DIAMOND_SWORD);
        sword.addEnchantment(Enchantment.DAMAGE_ALL,3);
        ItemStack bow = new ItemStack(Material.BOW);
        bow.addEnchantment(Enchantment.ARROW_DAMAGE,3);
        ItemStack goldenhead = new ItemStack(Material.GOLDEN_APPLE);
        ItemMeta goldenheadmeta = goldenhead.getItemMeta();
        goldenhead.setAmount(3);
        goldenheadmeta.setDisplayName(goldenheadname);
        goldenhead.setItemMeta(goldenheadmeta);
        ItemStack goldenapple = new ItemStack(Material.GOLDEN_APPLE);
        ItemStack food = new ItemStack(Material.COOKED_BEEF);
        food.setAmount(16);
        goldenapple.setAmount(6);
        ItemStack flint = new ItemStack(Material.FLINT_AND_STEEL);
        flint.setDurability((short)0);
        ItemStack wood = new ItemStack(Material.WOOD);
        ItemStack arrow = new ItemStack(Material.ARROW);
        arrow.setAmount(64);
        wood.setAmount(64);
        ItemStack stone = new ItemStack(Material.COBBLESTONE);
        stone.setAmount(64);
        p.getInventory().clear();
        p.getInventory().addItem(sword);
        p.getInventory().addItem(new ItemStack(Material.FISHING_ROD));
        p.getInventory().addItem(bow);
        p.getInventory().addItem(food);
        p.getInventory().addItem(flint);
        p.getInventory().addItem(wood);
        p.getInventory().addItem(goldenapple);
        p.getInventory().addItem(goldenhead);
        p.getInventory().setItem(8,selectmode);
        p.getInventory().setItem(9,arrow);
        p.getInventory().setItem(10,stone);
    }
    public void setcompletecombokit(Player p){
        ItemStack helmet = new ItemStack(Material.DIAMOND_HELMET);
        helmet.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL,2);
        helmet.addEnchantment(Enchantment.DURABILITY,2);
        ItemStack chestplate = new ItemStack(Material.DIAMOND_CHESTPLATE);
        chestplate.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL,2);
        chestplate.addEnchantment(Enchantment.DURABILITY,2);
        ItemStack leggins = new ItemStack(Material.DIAMOND_LEGGINGS);
        leggins.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL,2);
        leggins.addEnchantment(Enchantment.DURABILITY,2);
        ItemStack boots = new ItemStack(Material.DIAMOND_BOOTS);
        boots.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL,2);
        boots.addEnchantment(Enchantment.DURABILITY,2);
        int selectorid = plugin.getConfig().getInt("modeselector-id");
        String selectorname = plugin.getConfig().getString("modeselector");
        selectorname = selectorname.replaceAll("&","§");
        ItemStack selectmode = new ItemStack(Material.getMaterial(selectorid));
        ItemMeta selectmeta = selectmode.getItemMeta();
        selectmeta.setDisplayName(selectorname);
        selectmode.setItemMeta(selectmeta);
        ItemStack sword = new ItemStack(Material.DIAMOND_SWORD);
        sword.addEnchantment(Enchantment.DAMAGE_ALL,3);
        ItemStack goldenapple = new ItemStack(Material.GOLDEN_APPLE);
        goldenapple.setDurability((short)1);
        goldenapple.setAmount(64);
        p.getInventory().clear();
        p.getInventory().setItem(0,sword);
        p.getInventory().setItem(1,goldenapple);
        p.getInventory().setItem(2,helmet);
        p.getInventory().setItem(3,chestplate);
        p.getInventory().setItem(4,leggins);
        p.getInventory().setItem(5,boots);
        p.getInventory().setItem(8,selectmode);
    }
}
