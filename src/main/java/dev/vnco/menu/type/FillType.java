package dev.vnco.menu.type;

import dev.vnco.menu.Menu;
import org.bukkit.inventory.Inventory;

public enum FillType {

    BORDERS {

        @Override
        public void applyFill(Menu menu) {
            Inventory inventory = menu.getInventory();
            int size = menu.getSize();
            for (int i = 0; i < size; i++) {
                if (i < 9 || i >= size - 9 || i % 9 == 0 || i % 9 == 8) {
                    if (inventory.getItem(i) == null) {
                        inventory.setItem(i, menu.getFillItemStack());
                    }
                }
            }
        }

    },

    ALL_EMPTY_SLOTS {

        @Override
        public void applyFill(Menu menu) {
            Inventory inventory = menu.getInventory();
            for (int i = 0; i < menu.getSize(); i++) {
                if (inventory.getItem(i) == null) {
                    inventory.setItem(i, menu.getFillItemStack());
                }
            }
        }

    };

    /**
     * The method for applying a fill type to a menu
     *
     * @param menu - The menu to which the slots are to be obtained
     */

    public abstract void applyFill(Menu menu);

}
