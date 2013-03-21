/*     */ package com.aegamesi.mc.po8;
/*     */ 
/*     */ import java.io.BufferedWriter;
/*     */ import java.io.IOException;
/*     */ import java.text.SimpleDateFormat;
/*     */ import java.util.Date;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map.Entry;
/*     */ import org.bukkit.command.CommandSender;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ import org.bukkit.material.MaterialData;
/*     */ 
/*     */ public class Po8Util
/*     */ {
/*     */   public static String processColours(String str)
/*     */   {
/*  14 */     return str.replaceAll("(&([a-f0-9klmnor]))", "§$2");
/*     */   }
/*     */ 
/*     */   public static String stripColours(String str) {
/*  18 */     return str.replaceAll("(&([a-f0-9klmnor]))", "");
/*     */   }
/*     */ 
/*     */   public static void message(CommandSender sender, String message) {
/*  22 */     sender.sendMessage(processColours(message));
/*     */   }
/*     */ 
/*     */   public static double round2(double num) {
/*  26 */     double result = num * 100.0D;
/*  27 */     result = Math.round(result);
/*  28 */     result /= 100.0D;
/*  29 */     return result;
/*     */   }
/*     */ 
/*     */   public static double getBasePrice(String id) {
/*  33 */     Po8Item item = (Po8Item)Po8.itemMap.get(id);
/*  34 */     double stock = ((Integer)Po8.stockMap.get(id)).intValue();
/*  35 */     return round2(Math.max(Math.min(item.maxPrice * Math.pow(0.5D, stock / (item.stackSize * 27)), item.maxPrice), item.minPrice));
/*     */   }
/*     */ 
/*     */   public static String stockKey(MaterialData m) {
/*  39 */     return m.getItemTypeId() + (m.getData() != 0 ? "t" + m.getData() : "");
/*     */   }
/*     */ 
/*     */   public static MaterialData dataKey(String key) {
/*  43 */     String[] arr = key.split("t");
/*  44 */     if (arr.length == 1)
/*  45 */       return new MaterialData(Integer.parseInt(arr[0]), (byte)0);
/*  46 */     return new MaterialData(Integer.parseInt(arr[0]), Byte.parseByte(arr[1]));
/*     */   }
/*     */ 
/*     */   public static void log(String from, String to, double amount, String type, String notes)
/*     */   {
/*  51 */     String time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
/*  52 */     String line = String.format("\"%s\",\"%s\",\"%s\",\"%f\",\"%s\",\"%s\"", new Object[] { from, to, time, Double.valueOf(amount), type, notes });
/*     */     try {
/*  54 */       Po8.writer.write(line);
/*  55 */       Po8.writer.newLine();
/*  56 */       Po8.writer.flush();
/*     */     } catch (IOException e) {
/*  58 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */ 
/*     */   public static String pack(ItemStack[] stacks) {
/*  63 */     String pack = "";
/*  64 */     ItemStack[] arrayOfItemStack = stacks; int j = stacks.length; for (int i = 0; i < j; i++) { ItemStack stack = arrayOfItemStack[i];
/*  65 */       if (stack != null)
/*     */       {
/*  67 */         if (pack.length() > 0)
/*  68 */           pack = pack + "|";
/*  69 */         pack = pack + stack.getTypeId();
/*  70 */         pack = pack + "," + stack.getAmount();
/*  71 */         pack = pack + "," + stack.getData().getData();
/*     */       } }
/*  73 */     return pack;
/*     */   }
/*     */ 
/*     */   public static ItemStack[] unpack(String pack) {
/*  77 */     String[] pack2 = pack.split("|");
/*  78 */     ItemStack[] stacks = new ItemStack[pack2.length];
/*  79 */     for (int i = 0; i < pack2.length; i++) {
/*  80 */       String[] pack3 = pack2[i].split(",");
/*  81 */       stacks[i] = new ItemStack(Integer.parseInt(pack3[0]), Integer.parseInt(pack3[1]), 0);
/*  82 */       stacks[i].setData(new MaterialData(Integer.parseInt(pack3[0]), Byte.parseByte(pack3[2])));
/*     */     }
/*  84 */     return stacks;
/*     */   }
/*     */ 
/*     */   public static ItemStack[] splitStack(String key, int amt) {
/*  88 */     MaterialData dataKey = dataKey(key);
/*  89 */     ItemStack stack = new ItemStack(dataKey.getItemType(), amt, (short)0);
/*  90 */     stack.setDurability(dataKey.getData());
/*  91 */     double num = stack.getAmount();
/*  92 */     int size = (int)Math.ceil(num / stack.getMaxStackSize());
/*  93 */     ItemStack[] stacks = new ItemStack[size];
/*  94 */     for (int i = 0; i < size; i++) {
/*  95 */       ItemStack tempStack = new ItemStack(dataKey.getItemType(), amt, (short)0);
/*  96 */       tempStack.setDurability(dataKey.getData());
/*  97 */       tempStack.setAmount((int)Math.min(stack.getMaxStackSize(), num));
/*  98 */       stacks[i] = tempStack;
/*  99 */       num -= tempStack.getAmount();
/*     */     }
/* 101 */     return stacks;
/*     */   }
/*     */ 
/*     */   public static String combine(String[] args, int start, int num) {
/* 105 */     if (num == 0)
/* 106 */       num = args.length - start;
/* 107 */     String result = "";
/* 108 */     for (int i = start; i < start + num; i++)
/* 109 */       result = result + args[i] + (i == start + num - 1 ? "" : " ");
/* 110 */     return result;
/*     */   }
/*     */ 
/*     */   public static String getBlock(String blockName) {
/* 114 */     String check = blockName.toLowerCase().trim().replace(':', 't');
/* 115 */     if (Po8.itemMap.containsKey(check))
/* 116 */       return check;
/* 117 */     for (Map.Entry entry : Po8.itemMap.entrySet()) {
/* 118 */       String key = (String)entry.getKey();
/* 119 */       Po8Item value = (Po8Item)entry.getValue();
/* 120 */       if (value.name.toLowerCase().equals(check))
/* 121 */         return key;
/*     */     }
/* 123 */     return null;
/*     */   }
/*     */ }

/* Location:           /home/casper/craftbukkit/plugins/MCNSAPo8.jar
 * Qualified Name:     com.aegamesi.mc.po8.Po8Util
 * JD-Core Version:    0.6.2
 */