package dev.vnco.menu.api.filling;

import dev.vnco.menu.api.Menu;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

@Getter @Setter
public class Filling {

    private Menu menu;

    private FillingType fillingType;
    private ItemStack itemStack;
    private int slots;

    public Filling(Menu menu, FillingType fillingType, ItemStack itemStack){
        this.menu = menu;
        this.fillingType = fillingType;
        this.itemStack = itemStack;

        this.setupFilling();
    }

    private void setupFilling(){
        Inventory inventory = this.menu.getInventory();

        if (this.fillingType == FillingType.ALL){
            for (int i = 0; i < this.menu.getSize(); i++){
                this.slots = i;
                inventory.setItem(i, this.itemStack);
            }
        }

        if (this.fillingType == FillingType.BORDERS){
            for (int i = 0; i < this.menu.getSize(); i++) {
                if (i < 9 || i >= this.menu.getSize() - 9 || i % 9 == 0 || i % 9 == 8) {
                    this.slots = i;
                    inventory.setItem(i, this.itemStack);
                }
            }
        }
    }

}
