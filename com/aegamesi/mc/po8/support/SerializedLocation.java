/*    */ package com.aegamesi.mc.po8.support;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import org.bukkit.Bukkit;
/*    */ import org.bukkit.Location;
/*    */ import org.bukkit.World;
/*    */ 
/*    */ public class SerializedLocation
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = -9094035533656633605L;
/*    */   public final String WORLDNAME;
/*    */   public final double X;
/*    */   public final double Y;
/*    */   public final double Z;
/*    */   public final float YAW;
/*    */   public final float PITCH;
/*    */ 
/*    */   public SerializedLocation(Location location)
/*    */   {
/* 19 */     this.WORLDNAME = location.getWorld().getName();
/* 20 */     this.X = location.getX();
/* 21 */     this.Y = location.getY();
/* 22 */     this.Z = location.getZ();
/* 23 */     this.YAW = location.getYaw();
/* 24 */     this.PITCH = location.getPitch();
/*    */   }
/*    */ 
/*    */   public Location deserialize() {
/* 28 */     return new Location(Bukkit.getWorld(this.WORLDNAME), this.X, this.Y, this.Z, this.YAW, this.PITCH);
/*    */   }
/*    */ 
/*    */   public boolean equals(Object o) {
/* 32 */     if (!(o instanceof SerializedLocation))
/* 33 */       return false;
/* 34 */     SerializedLocation l = (SerializedLocation)o;
/* 35 */     return deserialize().equals(l.deserialize());
/*    */   }
/*    */ 
/*    */   public int hashCode() {
/* 39 */     return deserialize().hashCode();
/*    */   }
/*    */ }

/* Location:           /home/casper/craftbukkit/plugins/MCNSAPo8.jar
 * Qualified Name:     com.aegamesi.mc.po8.support.SerializedLocation
 * JD-Core Version:    0.6.2
 */