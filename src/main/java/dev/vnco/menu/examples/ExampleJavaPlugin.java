package dev.vnco.menu.examples;

import dev.vnco.menu.api.MenuManager;
import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;

@Getter
public class ExampleJavaPlugin extends JavaPlugin {

    @Getter private static ExampleJavaPlugin instance;

    private MenuManager menuManager;

    @Override public void onEnable() {
        instance = this;

        new ItemUtils();
        this.menuManager = new MenuManager(this);
        this.getCommand("example").setExecutor(new ExampleCommand());

        this.getLogger().info("Successfully enabled!");
    }

    @Override public void onDisable() {
        instance = null;
    }
}
