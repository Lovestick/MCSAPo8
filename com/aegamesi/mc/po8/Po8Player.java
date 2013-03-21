/*    */ package com.aegamesi.mc.po8;
/*    */ 
/*    */ import com.aegamesi.mc.po8.support.CardboardBox;
/*    */ import java.io.Serializable;
/*    */ import java.util.ArrayList;
/*    */ import java.util.HashMap;
/*    */ import org.bukkit.inventory.ItemStack;
/*    */ 
/*    */ public class Po8Player
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = -4390628890938756777L;
/*    */   private CardboardBox[] buyInv;
/*    */   private CardboardBox[] sellInv;
/*    */   public HashMap<String, Integer> buyOrder;
/*    */   public ArrayList<CardboardBox> extendedInv;
/*    */   public Po8Order reviewOrder;
/*    */   public double balance;
/*    */   public boolean notify;
/*    */   public boolean isReviewingOrder;
/*    */ 
/*    */   public Po8Player()
/*    */   {
/* 28 */     this.buyInv = new CardboardBox[54];
/* 29 */     this.sellInv = new CardboardBox[54];
/* 30 */     this.balance = 0.0D;
/* 31 */     this.buyOrder = new HashMap();
/* 32 */     this.extendedInv = new ArrayList();
/* 33 */     this.reviewOrder = null;
/* 34 */     this.notify = false;
/* 35 */     this.isReviewingOrder = false;
/*    */   }
/*    */ 
/*    */   public ItemStack[] getInventory(int type) {
/* 39 */     CardboardBox[] from = type == 1 ? this.buyInv : this.sellInv;
/* 40 */     ItemStack[] to = new ItemStack[54];
/* 41 */     for (int i = 0; i < 54; i++) {
/* 42 */       to[i] = (from[i] == null ? null : from[i].unbox());
/*    */     }
/* 44 */     return to;
/*    */   }
/*    */ 
/*    */   public void setInventory(int type, ItemStack[] contents) {
/* 48 */     CardboardBox[] to = type == 1 ? this.buyInv : this.sellInv;
/* 49 */     for (int i = 0; i < 54; i++)
/* 50 */       to[i] = (contents[i] == null ? null : new CardboardBox(contents[i]));
/*    */   }
/*    */ }

/* Location:           /home/casper/craftbukkit/plugins/MCNSAPo8.jar
 * Qualified Name:     com.aegamesi.mc.po8.Po8Player
 * JD-Core Version:    0.6.2
 */