package dev.vnco.menu.examples;

import dev.vnco.menu.examples.menu.ExampleMenu;
import dev.vnco.menu.utils.Color;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ExampleCommand implements CommandExecutor {

    @Override public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (!(commandSender instanceof Player)){
            Color.toSender(commandSender, "&c&cYou cannot run this command from the console!");
            return true;
        }

        new ExampleMenu().openMenu((Player) commandSender);
        return false;
    }
}
