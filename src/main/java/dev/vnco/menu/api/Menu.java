package dev.vnco.menu.api;

import dev.vnco.menu.api.button.Button;
import dev.vnco.menu.api.filling.FillingType;
import dev.vnco.menu.api.filling.Filling;
import dev.vnco.menu.api.type.MenuType;
import dev.vnco.menu.examples.ExampleJavaPlugin;
import dev.vnco.menu.utils.Color;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitTask;

import java.util.*;

@Getter @Setter
public abstract class Menu {

    private ExampleJavaPlugin plugin;

    private Inventory inventory;

    private String title;
    private int size;
    private MenuType menuType;

    private boolean fillEnabled;
    private ItemStack fillItemStack;

    private Filling filling;
    private FillingType fillingType;

    private BukkitTask task;

    public Set<Button> buttons;

    public Menu(String title, int size, MenuType menuType){
        this.plugin = ExampleJavaPlugin.getInstance();

        this.title = Color.translate(title);

        if (this.title.length() > 32){
            this.title = null;
        }

        this.menuType = menuType;

        if (menuType == MenuType.DEFAULT) {
            this.size = size * 9;
        }

        this.fillEnabled = false;
        this.fillItemStack = null;

        this.fillingType = null;

        this.inventory = menuType.createMenu(this);

        this.buttons = new HashSet<>();
    }

    public Menu(String title, int size){
        this(title, size, MenuType.DEFAULT);
    }

    public void openMenu(Player player){
        this.plugin.getMenuManager().addPlayerToCache(player, this);

        for (Button button : this.getButtons()){
            this.inventory.setItem(button.getSlot(), button.getItemStack());
        }

        if (this.isFillEnabled()){
            if (this.fillingType == null){
                return;
            }

            this.filling = new Filling(this, this.fillingType, this.fillItemStack);
        }

        this.onOpen(player);
        player.openInventory(this.inventory);
    }

    public void onOpen(Player player){
    }

    public void onClose(Player player){
        this.plugin.getMenuManager().removePlayerFromCache(player, this);
    }

    public abstract Set<Button> getButtons();

}
