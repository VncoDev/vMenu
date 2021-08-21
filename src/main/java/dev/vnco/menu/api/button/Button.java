package dev.vnco.menu.api.button;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

@Getter @Setter
public abstract class Button {

    private int slot;
    private boolean cancelClick;

    public Button(int slot, boolean cancelClick){
        this.slot = slot - 1;
        this.cancelClick = cancelClick;
    }

    /**
     * The button click will play all actions within this abstract method.
     *
     * @param player - The player to whom the actions will be replayed
     * @param clickType - The type of click when touching the button
     */

    public abstract void onClick(Player player, ClickType clickType);

    /**
     * Get the ItemStack of the button
     */

    public abstract ItemStack getButtonItem();

}
