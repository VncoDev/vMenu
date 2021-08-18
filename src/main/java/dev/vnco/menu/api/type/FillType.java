package dev.vnco.menu.api.type;

import dev.vnco.menu.api.Menu;

public enum FillType {

    BORDERS {

        @Override public void applyFill(Menu menu) {

            for (int i = 0; i < menu.getSize(); i++) {
                if (i < 9 || i >= menu.getSize() - 9 || i % 9 == 0 || i % 9 == 8) {
                    menu.getInventory().setItem(i, menu.getFillItemStack());
                }
            }
        }

    },

    ALL {

        @Override public void applyFill(Menu menu) {
            for (int i = 0; i < menu.getSize(); i++){
                menu.getInventory().setItem(i, menu.getFillItemStack());
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
