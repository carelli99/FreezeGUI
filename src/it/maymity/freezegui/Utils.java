package it.maymity.freezegui;

import java.util.ArrayList;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.configuration.file.FileConfiguration;

public class Utils {

    private static Utils instance;
    private ArrayList FreezeList = new ArrayList();
    private FileConfiguration config;
    private Inventory freeze;
    private Inventory sure;
    private Inventory freezeall;
    private boolean freezeallcheck = false;

    public static synchronized Utils getInstance() {
        if (instance == null) {
            instance = new Utils();
        }
        return instance;
    }

    public FileConfiguration getConfig() {
        config = Bukkit.getServer().getPluginManager().getPlugin("FreezeGUI").getConfig();
        return config;
    }

    public Inventory getFreeze() {
        return freeze;
    }

    public Inventory getSure() {
        return sure;
    }

    public Inventory getFreezeAll() {
        return freezeall;
    }

    public ArrayList getFreezeList() {
        return FreezeList;
    }

    public Boolean getFreezeallcheck() {
        return freezeallcheck;
    }

    public void setFreeze(Player p) {
        FreezeList.add(p);

    }

    public void removeFreeze(Player p) {
        FreezeList.remove(p);

    }

    public void setBoolFreezeAll(boolean b){
        freezeallcheck = b;
    }

    public static void setinvItem(Inventory inv, String invname, int numberitem) {

        Material material = Material.valueOf(getInstance().getConfig().getString(invname+".Items."+numberitem+".material").toUpperCase());
        int amount = getInstance().getConfig().getInt(invname+".Items."+numberitem+".amount");
        int intdamage = getInstance().getConfig().getInt(invname+".Items."+numberitem+".damage");
        short damage = (short) intdamage;

        ItemStack item = new ItemStack(material, amount,damage);
        String name = ChatColor.translateAlternateColorCodes('&', getInstance().getConfig().getString(invname+".Items."+numberitem+".name"));
        ArrayList lore = new ArrayList<String>();
        int slot = getInstance().getConfig().getInt(invname+".Items."+numberitem+".slot");

        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(name);

        for (String line : getInstance().getConfig().getStringList(invname+".Items."+numberitem+".lore")) {
            lore.add(ChatColor.translateAlternateColorCodes('&', line));
        }

        meta.setLore(lore);
        item.setItemMeta(meta);

        if(invname == "freezegui")
        getInstance().freeze.setItem(slot, item);
        else if(invname == "suregui")
            getInstance().sure.setItem(slot, item);
        else if(invname == "freezeallgui")
            getInstance().freezeall.setItem(slot, item);
    }

    public Inventory getFreezeInventory() {
        freeze = Bukkit.createInventory(null, 9, ChatColor.translateAlternateColorCodes('&', Utils.getInstance().getConfig().getString("freezegui.display_name")));
        setinvItem(freeze,"freezegui", 1);
        setinvItem(freeze, "freezegui",2);
        setinvItem(freeze,"freezegui", 3);
        return freeze;
    }

    public Inventory getSureInventory() {
        sure = Bukkit.createInventory(null, 9, ChatColor.translateAlternateColorCodes('&', Utils.getInstance().getConfig().getString("suregui.display_name")));
        setinvItem(sure, "suregui",1);
        setinvItem(sure, "suregui",2);
        return sure;
    }

    public Inventory getFreezeAllInventory() {
        freezeall = Bukkit.createInventory(null, 9, ChatColor.translateAlternateColorCodes('&', Utils.getInstance().getConfig().getString("freezeallgui.display_name")));
        setinvItem(freezeall,"freezeallgui", 1);
        return freezeall;
    }

}