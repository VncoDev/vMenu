# Maven

**Repository:**

```xml


	<repositories>
		<repository>
		    <id>jitpack.io</id>
		    <url>https://jitpack.io</url>
		</repository>
	</repositories>
	

```

**Dependency:**

```xml

	<dependencies>
	    <dependency>
	        <groupId>com.github.VncoDev</groupId>
	    	<artifactId>vMenu</artifactId>
	    	<version>8643c3852f</version>
	    </dependency>
	</dependencies>

```

# Gradle

**Repository:**

```gradle


	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
	

```

**Dependency:**

```gradle

	dependencies {
		implementation 'com.github.User:Repo:Tag'
	}

```

# Important

After implementing the **API** you need to register the **MenuHandler** in the class that is extending the **JavaPlugin**

```java

public class ExampleJavaPlugin extends JavaPlugin {

    @Override 
    public void onEnable() {
        new MenuHandler(this);
    }

}

```

# Example

``` java
public class ExampleMenu extends Menu {

    public ExampleMenu() {
        super("&c&lExample Menu", 3);
    }

    @Override
    public Set<Button> getButtons(Player player) {
        Set<Button> buttons = new HashSet<>();

        buttons.add(new Button(14) {

            @Override
	    public void onClick(InventoryClickEvent event) {
                if (event.getClick().isLeftClick()){
                    player.sendMessage("Hello");
                } else {
                    player.sendMessage("Bye");
                }

                player.closeInventory();
            }

            @Override 
	    public ItemStack getButtonItem() {
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
