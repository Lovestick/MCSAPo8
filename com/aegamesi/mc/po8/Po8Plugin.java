/*     */ package com.aegamesi.mc.po8;
/*     */ 
/*     */ import com.aegamesi.mc.po8.support.CardboardBox;
/*     */ import com.aegamesi.mc.po8.support.SLAPI;
/*     */ import com.aegamesi.mc.po8.support.SerializedLocation;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import org.bukkit.Server;
/*     */ import org.bukkit.block.Chest;
/*     */ import org.bukkit.command.CommandSender;
/*     */ import org.bukkit.command.PluginCommand;
/*     */ import org.bukkit.entity.HumanEntity;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.event.EventHandler;
/*     */ import org.bukkit.event.Listener;
/*     */ import org.bukkit.event.inventory.InventoryCloseEvent;
/*     */ import org.bukkit.event.inventory.InventoryOpenEvent;
/*     */ import org.bukkit.event.inventory.InventoryType;
/*     */ import org.bukkit.event.player.PlayerJoinEvent;
/*     */ import org.bukkit.event.player.PlayerQuitEvent;
/*     */ import org.bukkit.inventory.Inventory;
/*     */ import org.bukkit.inventory.InventoryView;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ import org.bukkit.plugin.PluginManager;
/*     */ import org.bukkit.plugin.java.JavaPlugin;
/*     */ 
/*     */ public final class Po8Plugin extends JavaPlugin
/*     */   implements Listener
/*     */ {
/*     */   public void onEnable()
/*     */   {
/*  22 */     getServer().getPluginManager().registerEvents(this, this);
/*  23 */     getCommand("po8").setExecutor(new Po8CommandExecutor(this));
/*     */ 
/*  25 */     Po8.slapi = new SLAPI();
/*  26 */     saveDefaultConfig();
/*  27 */     Po8.load(getDataFolder(), getConfig());
/*     */ 
/*  29 */     Po8.init(this);
/*     */   }
/*     */ 
/*     */   public void onDisable() {
/*  33 */     Po8.save(getDataFolder());
/*     */   }
/*     */ 
/*     */   @EventHandler
/*     */   public void onPlayerJoin(PlayerJoinEvent evt) {
/*  38 */     if (!Po8.playerMap.containsKey(evt.getPlayer().getName())) {
/*  39 */       Po8.playerMap.put(evt.getPlayer().getName(), new Po8Player());
/*     */     }
/*  41 */     if (evt.getPlayer().hasPermission("po8.review")) {
/*  42 */       Po8Util.message(evt.getPlayer(), "&dThere are&5 " + Po8.orderList.size() + " &dnew Po8 orders to be reviewed.");
/*     */     }
/*  44 */     ((Po8Player)Po8.playerMap.get(evt.getPlayer().getName())).isReviewingOrder = false;
/*     */   }
/*     */ 
/*     */   @EventHandler
/*     */   public void onPlayerQuit(PlayerQuitEvent evt)
/*     */   {
/*  56 */     if (((Po8Player)Po8.playerMap.get(evt.getPlayer().getName())).reviewOrder != null) {
/*  57 */       Po8.orderList.add(((Po8Player)Po8.playerMap.get(evt.getPlayer().getName())).reviewOrder);
/*  58 */       ((Po8Player)Po8.playerMap.get(evt.getPlayer().getName())).reviewOrder = null;
/*     */     }
/*     */   }
/*     */ 
/*     */   @EventHandler
/*     */   public void onInventoryOpen(InventoryOpenEvent evt) {
/*  64 */     if ((evt.getView().getType() == InventoryType.CHEST) && ((evt.getInventory().getHolder() instanceof Chest))) {
/*  65 */       Chest cc = (Chest)evt.getInventory().getHolder();
/*  66 */       if (Po8.chestMap.containsKey(new SerializedLocation(cc.getLocation()))) {
/*  67 */         int type = ((Integer)Po8.chestMap.get(new SerializedLocation(cc.getLocation()))).intValue();
/*  68 */         evt.setCancelled(true);
/*  69 */         ItemStack[] inv = ((Po8Player)Po8.playerMap.get(evt.getPlayer().getName())).getInventory(type);
/*  70 */         Po8InventoryHolder holder = new Po8InventoryHolder(type, evt.getPlayer().getName(), inv);
/*  71 */         evt.getPlayer().openInventory(holder.getInventory());
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   @EventHandler
/*     */   public void onInventoryClose(InventoryCloseEvent evt) {
/*  78 */     if ((evt.getInventory().getHolder() instanceof Po8InventoryHolder))
/*     */     {
/*  80 */       Po8InventoryHolder hold = (Po8InventoryHolder)evt.getInventory().getHolder();
/*  81 */       Po8Player player = (Po8Player)Po8.playerMap.get(hold.player);
/*  82 */       ItemStack[] stacks = evt.getInventory().getContents();
/*  83 */       if (hold.type == 1) {
/*  84 */         if (player.extendedInv.size() > 0)
/*  85 */           Po8Util.message((CommandSender)evt.getPlayer(), "&dYou have items pending placement in your buy chest. Clear some space to receive them");
/*  86 */         for (int i = 0; i < stacks.length; i++) {
/*  87 */           if (player.extendedInv.size() <= 0)
/*     */             break;
/*  89 */           if (stacks[i] == null)
/*  90 */             stacks[i] = ((CardboardBox)player.extendedInv.remove(0)).unbox();
/*     */         }
/*     */       }
/*  93 */       ((Po8Player)Po8.playerMap.get(hold.player)).setInventory(hold.type, stacks);
/*     */     }
/*  95 */     ((Po8Player)Po8.playerMap.get(evt.getPlayer().getName())).isReviewingOrder = false;
/*     */   }
/*     */ 
/*     */   @EventHandler
/*     */   public void onInventoryClick(InventoryOpenEvent evt) {
/* 100 */     if (evt != null) {
/* 101 */       if (((Po8Player)Po8.playerMap.get(evt.getPlayer().getName())).isReviewingOrder);
/* 102 */       evt.setCancelled(true);
/*     */     }
/*     */   }
/*     */ 
/*     */   public double balanceAdd(String name, double amount)
/*     */   {
/* 110 */     if (!Po8.playerMap.containsKey(name))
/* 111 */       return -1.0D;
/* 112 */     ((Po8Player)Po8.playerMap.get(name)).balance += amount;
/* 113 */     return ((Po8Player)Po8.playerMap.get(name)).balance;
/*     */   }
/*     */ }

/* Location:           /home/casper/craftbukkit/plugins/MCNSAPo8.jar
 * Qualified Name:     com.aegamesi.mc.po8.Po8Plugin
 * JD-Core Version:    0.6.2
 */