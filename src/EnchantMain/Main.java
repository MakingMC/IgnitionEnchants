package EnchantMain;

import com.sun.xml.internal.ws.api.addressing.WSEndpointReference;
import org.bukkit.Bukkit;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.enchantment.PrepareItemEnchantEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.material.Dye;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Listener {
    @Override
    public void onEnable(){
        Bukkit.getPluginManager().registerEvents(this, this);
    }
    @Override
    public void onDisable(){

    }
    ItemStack air = new ItemStack(Material.AIR);

    @EventHandler
    public void onEnchantOpen(InventoryOpenEvent e){
        Dye dye = new Dye(Material.INK_SACK);
        dye.setColor(DyeColor.BLUE);
        ItemStack lapis = dye.toItemStack();
        lapis.setAmount(64);

        if (e.getInventory().getType().equals(InventoryType.ENCHANTING)){
            e.getInventory().setItem(1, lapis);
        }
    }
    @EventHandler
    public void onEnchantClose(InventoryCloseEvent e){
        if(e.getInventory().getType().equals(InventoryType.ENCHANTING)) {
            e.getInventory().setItem(1, air);
        }
    }
    @EventHandler
    public void lapisClick(InventoryClickEvent e){
        if(e.getInventory().getType().equals(InventoryType.ENCHANTING)) {
            if (e.getCurrentItem().getType().equals(Material.INK_SACK)) {
                e.setCancelled(true);
            }
        }
    }
    @EventHandler

    public void onPreEnchantTest(PrepareItemEnchantEvent e) {
        Player p = e.getEnchanter().getPlayer();

        if (p.hasPermission("GTA.EnchantOne")) {
            e.getExpLevelCostsOffered()[0] = 1;
            e.getExpLevelCostsOffered()[1] = 3;
            e.getExpLevelCostsOffered()[2] = 5;
        }
        if (p.hasPermission("GTA.EnchantTwo")) {
            e.getExpLevelCostsOffered()[0] = 2;
            e.getExpLevelCostsOffered()[1] = 10;
            e.getExpLevelCostsOffered()[2] = 15;
        }
        if (p.hasPermission("GTA.EnchantThree")) {
            e.getExpLevelCostsOffered()[0] = 1;
            e.getExpLevelCostsOffered()[1] = 15;
            e.getExpLevelCostsOffered()[2] = 30;
        }
        else{
            e.getExpLevelCostsOffered()[0] = 0;
            e.getExpLevelCostsOffered()[1] = 0;
            e.getExpLevelCostsOffered()[2] = 0;
        }
    }
}
