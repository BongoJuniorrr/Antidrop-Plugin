package me.hirobi;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.inventory.ItemStack;

public class PlayerDrop implements Listener {
    int pressTime=0;
    Long lastTime=System.currentTimeMillis();
    Long currTime;
    private antidrop dp = antidrop.get();
    FileConfiguration config = dp.getConfigMethods();
    Long delayTime = config.getLong("DELAY");
    int currItem;
    @EventHandler
    public void onDrop(PlayerDropItemEvent event)
    {
        pressTime++;
        currTime=System.currentTimeMillis();
        Player player = event.getPlayer();
        int item = player.getInventory().getHeldItemSlot();
        if (pressTime==1)
            currItem=item;
        if (pressTime==2 && item!=currItem){
            String messeage = dp.getConfig().getString("diffMessage");
            player.sendMessage(ChatColor.RED + messeage);
            pressTime = 0;
            event.setCancelled(true);
            return;
        }
        if (pressTime==2)
        {
            if ((currTime-lastTime)/1000>delayTime) {
                pressTime = 0;
                String messeage = dp.getConfig().getString("cancelMessage");
                player.sendMessage(ChatColor.GREEN + messeage);
                event.setCancelled(true);
                return;
            }
        }
        if (pressTime==2){
            pressTime=0;
            return;
        }
        String messeage = dp.getConfig().getString("dropMessage");
        player.sendMessage(ChatColor.GREEN + messeage);
        lastTime = currTime;
        event.setCancelled(true);
    }
}
