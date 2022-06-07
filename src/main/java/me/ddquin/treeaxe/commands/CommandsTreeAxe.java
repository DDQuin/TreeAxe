package me.ddquin.treeaxe.commands;

import me.ddquin.treeaxe.Main;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandsTreeAxe implements CommandExecutor {

    private Main main;

    public CommandsTreeAxe(Main main) {
        this.main = main;
    }


    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (!(commandSender instanceof Player)) {
            commandSender.sendMessage(ChatColor.RED + "Only players can use items!");
            return false;
        }
        Player player = (Player) commandSender;
        player.getInventory().addItem(main.getTreeAxe());
        return false;
    }
}
