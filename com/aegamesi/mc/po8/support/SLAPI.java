/*    */ package com.aegamesi.mc.po8.support;
/*    */ 
/*    */ import java.io.FileInputStream;
/*    */ import java.io.FileOutputStream;
/*    */ import java.io.ObjectInputStream;
/*    */ import java.io.ObjectOutputStream;
/*    */ 
/*    */ public class SLAPI
/*    */ {
/*    */   public <T> void save(T obj, String path)
/*    */     throws Exception
/*    */   {
/* 10 */     ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(path));
/* 11 */     oos.writeObject(obj);
/* 12 */     oos.flush();
/* 13 */     oos.close();
/*    */   }
/*    */ 
/*    */   public <T> T load(String path) throws Exception {
/* 17 */     ObjectInputStream ois = new ObjectInputStream(new FileInputStream(path));
/*    */ 
/* 19 */     Object result = ois.readObject();
/* 20 */     ois.close();
/* 21 */     return result;
/*    */   }
/*    */ }

/* Location:           /home/casper/craftbukkit/plugins/MCNSAPo8.jar
 * Qualified Name:     com.aegamesi.mc.po8.support.SLAPI
 * JD-Core Version:    0.6.2
 */