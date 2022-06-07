package me.ddquin.treeaxe;

import me.ddquin.treeaxe.commands.CommandsTreeAxe;
import me.ddquin.treeaxe.listeners.ListenerTree;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    private NamespacedKey treeAxeKey;

    @Override
    public void onEnable() {
        treeAxeKey = new NamespacedKey(this, "treeaxe");
        getCommand("treeaxe").setExecutor(new CommandsTreeAxe(this));
        getServer().getPluginManager().registerEvents(new ListenerTree(this), this);
    }

    public ItemStack getTreeAxe() {
        ItemStack treeAxe = new ItemStack(Material.DIAMOND_AXE, 1);
        ItemMeta i = treeAxe.getItemMeta();
        i.setDisplayName(ChatColor.GREEN + "Tree Axe");
        i.getPersistentDataContainer().set(treeAxeKey, PersistentDataType.STRING, "treeaxe");
        treeAxe.setItemMeta(i);
        return treeAxe;
    }

    public boolean isTreeAxe(ItemMeta meta) {
        PersistentDataContainer pdc = meta.getPersistentDataContainer();
        return pdc.has(treeAxeKey, PersistentDataType.STRING);
    }






}
