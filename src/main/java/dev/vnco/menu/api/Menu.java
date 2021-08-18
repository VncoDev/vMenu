package dev.vnco.menu.api;

import dev.vnco.menu.api.button.Button;
import dev.vnco.menu.api.type.FillType;
import dev.vnco.menu.api.type.MenuType;
import dev.vnco.menu.examples.ExampleJavaPlugin;
import dev.vnco.menu.utils.Color;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.*;

@Getter @Setter
public abstract class Menu {

    private MenuManager menuManager;

    private Inventory inventory;

    private String title;
    private int size;
    private MenuType menuType;

    private boolean autoUpdate;

    private boolean fillEnabled;
    private ItemStack fillItemStack;

    private FillType fillType;

    private Set<Player> players;

    /**
     * Builder to make a menu
     *
     * @param title - The title of the menu to be shown
     * @param size - The number of slots to be added to the menu (if Default)
     *               (Multiply by "9" as this is the minimum number of slots that
     *               a normal inventory can have).
     *
     * @param menuType - The type of menu you want to open
     */

    public Menu(String title, int size, MenuType menuType){
        this.menuManager = ExampleJavaPlugin.getInstance().getMenuManager();

        this.title = Color.translate(title);

        if (this.title.length() > 32){
            this.title = null;
        }

        this.menuType = menuType;

        if (menuType == MenuType.DEFAULT) {
            this.size = size * 9;
        }

        this.autoUpdate = false;

        this.fillEnabled = false;
        this.fillItemStack = null;

        this.fillType = null;

        this.inventory = menuType.createMenu(this);

        this.players = new HashSet<>();
    }

    /**
     * Another constructor to make a menu, but without being able to set the type of menu you want (Default Menu).
     */

    public Menu(String title, int size){
        this(title, size, MenuType.DEFAULT);
    }

    /**
     * Open the menu to a player
     *
     * @param player - The player to whom the menu will be opened
     */

    public void openMenu(Player player){
        this.menuManager.addPlayerToMenu(player, this);

        for (Button button : this.getButtons(player)){
            this.inventory.setItem(button.getSlot(), button.getItemStack());
        }

        if (this.isFillEnabled()){
            if (this.fillType == null){
                return;
            }

            this.fillType.applyFill(this);
        }

        this.onOpen(player);
        player.openInventory(this.inventory);
    }

    /**
     * Opening the menu to a player will play what is in this method (If you are calling it) (Optional)
     *
     * @param player - The player to whom the contents of the method will be played back
     */

    public void onOpen(Player player){
    }

    /**
     * When closing the menu, the content of this method will be reproduced.
     *
     * @param player - The player to whom the content of the method is to be played back
     */

    public void onClose(Player player){
        this.menuManager.removePlayerFromMenu(player, this);
    }

    /**
     * This is the abstract method to be used to add buttons to the menu (if there are any)
     */

    public abstract Set<Button> getButtons(Player player);

    public boolean contains(Player player){
        return this.players.contains(player);
    }

    public void addPlayer(Player player){
        this.players.add(player);
    }

    public void removePlayer(Player player){
        this.players.remove(player);
    }

}
