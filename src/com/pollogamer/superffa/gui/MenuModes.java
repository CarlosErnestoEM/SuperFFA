package com.pollogamer.superffa.gui;

import com.minebone.gui.inventory.GUIPage;
import com.pollogamer.superffa.Principal;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

public class MenuModes extends GUIPage {

    private BukkitTask task;

    public MenuModes(Principal plugin, Player player) {
        super(plugin, player, title, sizee);
        build();
        task = new BukkitRunnable(){
            @Override
            public void run() {
            refresh();
            }
        }.runTaskTimer(Principal.plugin,10,10);
    }

    @Override
    public void buildPage() {

    }

    @Override
    public void destroy() {

    }
}
