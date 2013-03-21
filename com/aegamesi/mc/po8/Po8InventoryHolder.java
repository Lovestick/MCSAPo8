/*    */ package com.aegamesi.mc.po8;
/*    */ 
/*    */ import org.bukkit.Bukkit;
/*    */ import org.bukkit.Server;
/*    */ import org.bukkit.inventory.Inventory;
/*    */ import org.bukkit.inventory.InventoryHolder;
/*    */ import org.bukkit.inventory.ItemStack;
/*    */ 
/*    */ public class Po8InventoryHolder
/*    */   implements InventoryHolder
/*    */ {
/*    */   public Inventory inventory;
/*    */   public int type;
/*    */   public String player;
/*    */ 
/*    */   public Inventory getInventory()
/*    */   {
/* 14 */     return this.inventory;
/*    */   }
/*    */ 
/*    */   public Po8InventoryHolder(int type, String player, ItemStack[] contents) {
/* 18 */     this.player = player;
/* 19 */     this.type = type;
/*    */ 
/* 21 */     this.inventory = Bukkit.getServer().createInventory(this, 54, player + "'s " + (type == 2 ? "Sell Chest" : "Buy Chest"));
/* 22 */     if (contents != null)
/* 23 */       this.inventory.setContents(contents);
/*    */   }
/*    */ }

/* Location:           /home/casper/craftbukkit/plugins/MCNSAPo8.jar
 * Qualified Name:     com.aegamesi.mc.po8.Po8InventoryHolder
 * JD-Core Version:    0.6.2
 */