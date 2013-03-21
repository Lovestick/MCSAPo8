/*    */ package com.aegamesi.mc.po8;
/*    */ 
/*    */ import com.aegamesi.mc.po8.support.CardboardBox;
/*    */ import java.io.Serializable;
/*    */ import org.bukkit.inventory.ItemStack;
/*    */ 
/*    */ public class Po8Order
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 4766125679201998352L;
/*    */   public int type;
/*    */   public double value;
/*    */   public String owner;
/*    */   public CardboardBox[] items;
/*    */ 
/*    */   public Po8Order(int type, double value, String owner, ItemStack[] items)
/*    */   {
/* 17 */     this.type = type;
/* 18 */     this.value = value;
/* 19 */     this.owner = owner;
/* 20 */     this.items = new CardboardBox[items.length];
/* 21 */     for (int i = 0; i < items.length; i++)
/* 22 */       this.items[i] = new CardboardBox(items[i]);
/*    */   }
/*    */ 
/*    */   public ItemStack[] getItems() {
/* 26 */     ItemStack[] stacks = new ItemStack[this.items.length];
/* 27 */     for (int i = 0; i < this.items.length; i++)
/* 28 */       stacks[i] = this.items[i].unbox();
/* 29 */     return stacks;
/*    */   }
/*    */ }

/* Location:           /home/casper/craftbukkit/plugins/MCNSAPo8.jar
 * Qualified Name:     com.aegamesi.mc.po8.Po8Order
 * JD-Core Version:    0.6.2
 */