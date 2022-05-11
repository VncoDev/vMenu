package dev.vnco.menu;

import dev.vnco.menu.button.Button;
import dev.vnco.menu.type.FillType;
import dev.vnco.menu.type.MenuType;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.*;

@Getter @Setter
public abstract class Menu {

    private MenuHandler menuHandler = MenuHandler.getInstance();

    private Inventory inventory;

    private String title;
    private int size;
    private MenuType menuType;

    private boolean cancelClick, fillEnabled;
    private ItemStack fillItemStack;

    private FillType fillType;

    /**
     * Another constructor to make a menu, but without being able to set the type of menu you want (Default Menu).
     */

    public Menu(String title, int size) {
        this(title, MenuType.DEFAULT, size);
    }

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

    public Menu(String title, MenuType menuType, int size) {
        this.title = ChatColor.translateAlternateColorCodes('&', title);

        if (this.title.length() > 32){
            this.title = title.substring(0, 16);
        }

        this.menuType = menuType;

        if (menuType == MenuType.DEFAULT) {
            this.size = size * 9;
        }

        this.cancelClick = true;
        this.fillEnabled = false;
        this.fillItemStack = null;

        this.fillType = null;

        this.inventory = menuType.createMenu(this);
    }

    /**
     * Open the menu to a player
     *
     * @param player - The player to whom the menu will be opened
     */

    public void openMenu(Player player) {
        UUID uuid = player.getUniqueId();

        Optional<Menu> optionalMenu = menuHandler.findMenu(uuid);

        if (optionalMenu.isPresent()) {
            if (optionalMenu.get().equals(this)) {
                player.closeInventory();
            } else {
                menuHandler.unregisterMenu(uuid);
            }
        }

        Set<Button> buttons = getButtons(player);

        if (!buttons.isEmpty()) {
            for (Button button : buttons) {
                inventory.setItem(button.getSlot(), button.getButtonItem());
            }
        }

        if (isFillEnabled()) {
            if (fillType != null) {
                fillType.applyFill(this);
            }
        }

        onOpen(player);
        player.openInventory(inventory);

        menuHandler.registerMenu(uuid, this);
    }

    /**
     * Opening the menu to a player will play what is in this method (If you are calling it) (Optional)
     *
     * @param player - The player to whom the contents of the method will be played back
     */

    public void onOpen(Player player) {}

    /**
     * When closing the menu, the content of this method will be reproduced.
     *
     * @param player - The player to whom the content of the method is to be played back
     */

    public void onClose(Player player) {
        menuHandler.unregisterMenu(player.getUniqueId());
    }

    /**
     * This is the abstract method to be used to add buttons to the menu (if there are any)
     */

    public abstract Set<Button> getButtons(Player player);

}
