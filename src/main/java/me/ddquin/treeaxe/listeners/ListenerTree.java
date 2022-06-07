package me.ddquin.treeaxe.listeners;

import me.ddquin.treeaxe.Main;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ListenerTree implements Listener {

    private Main main;
    public ListenerTree(Main main) {
        this.main = main;
    }

    private static final Material[] BLOCK_LOGS = {Material.ACACIA_LOG, Material.BIRCH_LOG, Material.DARK_OAK_LOG, Material.JUNGLE_LOG, Material.OAK_LOG,
    Material.SPRUCE_LOG};

    private static final BlockFace[] BLOCK_FACES = {BlockFace.DOWN, BlockFace.UP, BlockFace.EAST, BlockFace.WEST, BlockFace.SOUTH, BlockFace.NORTH};

    @EventHandler
    public void onBreakLog(BlockBreakEvent e) {
        Block block = e.getBlock();
        Material m = block.getBlockData().getMaterial();
        if (!isLog(m)) return;
        ItemStack itemUsedToBreak = e.getPlayer().getInventory().getItemInMainHand();
        if (main.isTreeAxe(itemUsedToBreak.getItemMeta())) {
            breakConnections(block, block.getBlockData().getMaterial(), new ArrayList<>());
        }
    }

    private boolean isLog(Material m) {
        for (Material log: BLOCK_LOGS) {
            if (m.equals(log)) {
                return true;
            }
        }
        return false;
    }

    private boolean blockAlreadyBroken(Block b, List<Block> blocks) {
        for (Block block: blocks) {
            if (block.equals(b)) return true;
        }
        return false;
    }

    private void breakConnections(Block b, Material typeToBreak, List<Block> blocksToBreak) {
        boolean isCorrectLog = b.getBlockData().getMaterial().equals(typeToBreak);
        if (!isCorrectLog || blockAlreadyBroken(b, blocksToBreak)) return;
        blocksToBreak.add(b);
        for (BlockFace face: BLOCK_FACES) {
            Block nextBlock = b.getRelative(face);
            breakConnections(nextBlock, typeToBreak, blocksToBreak);
        }
        b.breakNaturally();
    }
}
