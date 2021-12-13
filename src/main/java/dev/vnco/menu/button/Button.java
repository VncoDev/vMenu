package dev.vnco.menu.button;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

@Getter @Setter
public abstract class Button {

    private int slot;

    /**
     * This is the main constructor for make a button, for now it will only be to assign the slot
     *
     * @param slot - Slot where the button will be assigned
     */
    public Button(int slot){
        this.slot = slot - 1;
    }

    /**
     * The button click will play all actions within this abstract method.
     *
     * @param event - Inventory click event
     */

    public abstract void onClick(InventoryClickEvent event);

    /**
     * Get the ItemStack of the button
     */

    public abstract ItemStack getButtonItem();

}
