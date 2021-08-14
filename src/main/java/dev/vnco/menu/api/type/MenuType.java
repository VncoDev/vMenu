package dev.vnco.menu.api.type;

import dev.vnco.menu.api.Menu;
import org.bukkit.Bukkit;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;

public enum MenuType {

    DEFAULT {

        @Override public Inventory createMenu(Menu menu) {
            return Bukkit.createInventory(null, menu.getSize(), menu.getTitle());
        }

    },

    CHEST {

        @Override public Inventory createMenu(Menu menu) {
            return Bukkit.createInventory(null, InventoryType.CHEST, menu.getTitle());
        }

    },

    HOPPER {

        @Override public Inventory createMenu(Menu menu) {
            return Bukkit.createInventory(null, InventoryType.HOPPER, menu.getTitle());
        }

    },

    FURNACE {

        @Override public Inventory createMenu(Menu menu) {
            return Bukkit.createInventory(null, InventoryType.FURNACE, menu.getTitle());
        }

    },

    CRAFTING {

        @Override public Inventory createMenu(Menu menu) {
            return Bukkit.createInventory(null, InventoryType.CRAFTING, menu.getTitle());
        }

    },

    WORKBENCH {

        @Override public Inventory createMenu(Menu menu) {
            return Bukkit.createInventory(null, InventoryType.WORKBENCH, menu.getTitle());
        }

    };

    public abstract Inventory createMenu(Menu menu);

}
