# vMenu | Information
 **MenuAPI** | *[Click here](https://discord.vnco.club)*

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

