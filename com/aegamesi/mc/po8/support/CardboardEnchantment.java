/*    */ package com.aegamesi.mc.po8.support;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import org.bukkit.enchantments.Enchantment;
/*    */ 
/*    */ public class CardboardEnchantment
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 8973856768102665381L;
/*    */   private final int id;
/*    */ 
/*    */   public CardboardEnchantment(Enchantment enchantment)
/*    */   {
/* 16 */     this.id = enchantment.getId();
/*    */   }
/*    */ 
/*    */   public Enchantment unbox() {
/* 20 */     return Enchantment.getById(this.id);
/*    */   }
/*    */ }

/* Location:           /home/casper/craftbukkit/plugins/MCNSAPo8.jar
 * Qualified Name:     com.aegamesi.mc.po8.support.CardboardEnchantment
 * JD-Core Version:    0.6.2
 */