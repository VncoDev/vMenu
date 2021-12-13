package dev.vnco.menu.type;

import dev.vnco.menu.Menu;
import lombok.AllArgsConstructor;
import org.bukkit.Bukkit;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;

@AllArgsConstructor
public enum MenuType {

    DEFAULT(null) {

        @Override
        public Inventory createMenu(Menu menu) {
            return Bukkit.createInventory(null, menu.getSize(), menu.getTitle());
        }

    },
    DROPPER(InventoryType.DROPPER),
    CHEST(InventoryType.CHEST),
    HOPPER(InventoryType.HOPPER),
    FURNACE(InventoryType.FURNACE),
    CRAFTING(InventoryType.CRAFTING),
    WORKBENCH(InventoryType.WORKBENCH);

    private final InventoryType inventoryType;

    /**
     * Abstract method which will be created with the indicated menu type.
     *
     * @param menu - The menu to which the type will be set
     */

    public Inventory createMenu(Menu menu) {
        return Bukkit.createInventory(null, this.inventoryType, menu.getTitle());
    }

}
