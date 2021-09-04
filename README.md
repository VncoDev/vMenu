# vMenu | Information
 **MenuAPI** | *[Click here](https://discord.vnco.club)*

# Important

After implementing the **API** you need to register the **MenuManager** in the class that is extending the **JavaPlugin**

```java

public class ExampleJavaPlugin extends JavaPlugin {

    @Override public void onEnable() {
        new MenuManager(this);
    }

}

```

# Example

``` java
public class ExampleMenu extends Menu {

    public ExampleMenu() {
        super("&c&lExample Menu", 3);
    }

    @Override public Set<Button> getButtons(Player player) {
        Set<Button> buttons = new HashSet<>();

        buttons.add(new Button(14) {

            @Override public void onClick(InventoryClickEvent event) {
                if (event.getClick().isLeftClick()){
                    player.sendMessage("Hello");
                } else {
                    player.sendMessage("Bye");
                }

                player.closeInventory();
            }

            @Override public ItemStack getButtonItem() {
                ItemStack itemStack = new ItemStack(Material.APPLE);
                ItemMeta itemMeta = itemStack.getItemMeta();

                itemMeta.setDisplayName("Example Button");

                itemStack.setItemMeta(itemMeta);

                return itemStack;
            }

        });

        return buttons;
    }
}
```
