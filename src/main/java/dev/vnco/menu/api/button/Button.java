package dev.vnco.menu.api.button;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

@Getter @Setter
public abstract class Button {

    private ItemStack itemStack;
    private int slot;
    private boolean cancelClick;

    public Button(int slot, boolean cancelClick){
        this.itemStack = this.getButtonItem();
        this.slot = slot - 1;
        this.cancelClick = cancelClick;
    }

    public abstract void onClick(Player player, ClickType clickType);

    public abstract ItemStack getButtonItem();

}
