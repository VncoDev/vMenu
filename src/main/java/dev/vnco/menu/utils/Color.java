package dev.vnco.menu.utils;

import lombok.experimental.UtilityClass;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

@UtilityClass
public class Color {

    public static String translate(String text){
        return ChatColor.translateAlternateColorCodes('&', text);
    }

    public static void toPlayer(Player player, String text){
        player.sendMessage(translate(text));
    }

    public static void toSender(CommandSender sender, String text){
        sender.sendMessage(translate(text));
    }

    public static List<String> translateFromArrayList(List<String> stringList){
        List<String> arrayList = new ArrayList<>();
        for (String list : stringList){
            arrayList.add(translate(list));
        }
        return arrayList;
    }

}
