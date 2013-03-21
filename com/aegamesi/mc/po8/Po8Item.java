/*    */ package com.aegamesi.mc.po8;
/*    */ 
/*    */ public class Po8Item
/*    */ {
/*    */   public boolean active;
/*    */   public String id;
/*    */   public double maxPrice;
/*    */   public double minPrice;
/*    */   public String name;
/*    */   public int stackSize;
/*    */ 
/*    */   public Po8Item(boolean active, String id, double minPrice, double maxPrice, String name, int stackSize)
/*    */   {
/* 12 */     this.active = active;
/* 13 */     this.id = id;
/* 14 */     this.maxPrice = maxPrice;
/* 15 */     this.minPrice = minPrice;
/* 16 */     this.name = name;
/* 17 */     this.stackSize = stackSize;
/*    */   }
/*    */ }

/* Location:           /home/casper/craftbukkit/plugins/MCNSAPo8.jar
 * Qualified Name:     com.aegamesi.mc.po8.Po8Item
 * JD-Core Version:    0.6.2
 */