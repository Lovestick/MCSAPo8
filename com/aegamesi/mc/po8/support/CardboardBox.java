/*    */ package com.aegamesi.mc.po8.support;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import java.util.HashMap;
/*    */ import java.util.Map;
/*    */ import org.bukkit.enchantments.Enchantment;
/*    */ import org.bukkit.inventory.ItemStack;
/*    */ import org.bukkit.material.MaterialData;
/*    */ 
/*    */ public class CardboardBox
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 729890133797629668L;
/*    */   private final int type;
/*    */   private final int amount;
/*    */   private final short damage;
/*    */   private final byte data;
/*    */   private final boolean isNull;
/*    */   private final HashMap<CardboardEnchantment, Integer> enchants;
/*    */ 
/*    */   public CardboardBox(ItemStack item)
/*    */   {
/* 25 */     if (item == null) {
/* 26 */       this.isNull = true;
/* 27 */       this.type = 0;
/* 28 */       this.amount = 0;
/* 29 */       this.damage = 0;
/* 30 */       this.data = 0;
/* 31 */       this.enchants = null;
/*    */     } else {
/* 33 */       this.isNull = false;
/* 34 */       this.type = item.getTypeId();
/* 35 */       this.amount = item.getAmount();
/* 36 */       this.damage = item.getDurability();
/* 37 */       this.data = item.getData().getData();
/*    */ 
/* 39 */       HashMap map = new HashMap();
/*    */ 
/* 41 */       Map enchantments = item.getEnchantments();
/*    */ 
/* 43 */       for (Enchantment enchantment : enchantments.keySet()) {
/* 44 */         map.put(new CardboardEnchantment(enchantment), (Integer)enchantments.get(enchantment));
/*    */       }
/*    */ 
/* 47 */       this.enchants = map;
/*    */     }
/*    */   }
/*    */ 
/*    */   public ItemStack unbox() {
/* 52 */     if (this.isNull) {
/* 53 */       return null;
/*    */     }
/* 55 */     ItemStack item = new ItemStack(this.type);
/* 56 */     item.setAmount(this.amount);
/* 57 */     item.setDurability(this.damage);
/* 58 */     item.setData(new MaterialData(this.type, this.data));
/*    */ 
/* 61 */     HashMap map = new HashMap();
/*    */ 
/* 63 */     for (CardboardEnchantment cEnchantment : this.enchants.keySet()) {
/* 64 */       map.put(cEnchantment.unbox(), (Integer)this.enchants.get(cEnchantment));
/*    */     }
/*    */ 
/* 67 */     item.addUnsafeEnchantments(map);
/*    */ 
/* 69 */     return item;
/*    */   }
/*    */ }

/* Location:           /home/casper/craftbukkit/plugins/MCNSAPo8.jar
 * Qualified Name:     com.aegamesi.mc.po8.support.CardboardBox
 * JD-Core Version:    0.6.2
 */