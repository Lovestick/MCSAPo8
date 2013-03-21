/*     */ package com.aegamesi.mc.po8;
/*     */ 
/*     */ import com.aegamesi.mc.po8.support.SLAPI;
/*     */ import com.aegamesi.mc.po8.support.SerializedLocation;
/*     */ import java.io.BufferedWriter;
/*     */ import java.io.File;
/*     */ import java.io.FileWriter;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.Server;
/*     */ import org.bukkit.configuration.file.FileConfiguration;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.scheduler.BukkitScheduler;
/*     */ 
/*     */ public class Po8
/*     */ {
/*     */   public static HashMap<SerializedLocation, Integer> chestMap;
/*     */   public static HashMap<String, Po8Player> playerMap;
/*     */   public static HashMap<String, Integer> stockMap;
/*     */   public static HashMap<String, Po8Item> itemMap;
/*     */   public static ArrayList<Po8Order> orderList;
/*     */   public static SLAPI slapi;
/*     */   public static double commission;
/*     */   public static BufferedWriter writer;
/*     */   public static final int BUY = 1;
/*     */   public static final int SELL = 2;
/*     */   public static final int REVIEW = 3;
/*     */ 
/*     */   public static void load(File dataFolder, FileConfiguration config)
/*     */   {
/*     */     try
/*     */     {
/*  37 */       if (!dataFolder.isDirectory()) {
/*  38 */         dataFolder.mkdirs();
/*     */       }
/*     */ 
/*  42 */       boolean exists = new File(dataFolder + File.separator + "log.csv").exists();
/*  43 */       writer = new BufferedWriter(new FileWriter(new File(dataFolder + File.separator + "log.csv"), true));
/*  44 */       if (!exists) {
/*  45 */         writer.write("from,to,time,amount,type,notes");
/*  46 */         writer.newLine();
/*  47 */         writer.flush();
/*     */       }
/*     */ 
/*  51 */       String path = dataFolder + File.separator + "chestmap.bin";
/*  52 */       File file = new File(path);
/*  53 */       if (file.exists())
/*  54 */         chestMap = (HashMap)slapi.load(path);
/*     */       else {
/*  56 */         chestMap = new HashMap();
/*     */       }
/*     */ 
/*  59 */       path = dataFolder + File.separator + "stockmap.bin";
/*  60 */       file = new File(path);
/*  61 */       if (file.exists())
/*  62 */         stockMap = (HashMap)slapi.load(path);
/*     */       else {
/*  64 */         stockMap = new HashMap();
/*     */       }
/*     */ 
/*  67 */       path = dataFolder + File.separator + "playermap.bin";
/*  68 */       file = new File(path);
/*  69 */       playerMap = new HashMap();
/*  70 */       if (file.exists())
/*  71 */         playerMap = (HashMap)slapi.load(path);
/*     */       else {
/*  73 */         playerMap = new HashMap();
/*     */       }
/*     */ 
/*  76 */       path = dataFolder + File.separator + "orderlist.bin";
/*  77 */       file = new File(path);
/*  78 */       orderList = new ArrayList();
/*  79 */       if (file.exists())
/*  80 */         orderList = (ArrayList)slapi.load(path);
/*     */       else {
/*  82 */         orderList = new ArrayList();
/*     */       }
/*     */ 
/*  85 */       itemMap = new HashMap();
/*  86 */       List items = config.getMapList("items");
/*  87 */       for (int i = 0; i < items.size(); i++) {
/*  88 */         Map map = (Map)items.get(i);
/*  89 */         String id = (String)map.get("id");
/*  90 */         boolean active = Integer.parseInt(map.get("active").toString()) == 1;
/*  91 */         Object minPrice = map.get("min_price");
/*  92 */         Object maxPrice = map.get("max_price");
/*  93 */         itemMap.put(id, new Po8Item(active, id, (minPrice instanceof Integer) ? Integer.parseInt(minPrice.toString()) : Double.parseDouble(minPrice.toString()), (maxPrice instanceof Integer) ? Integer.parseInt(maxPrice.toString()) : Double.parseDouble(maxPrice.toString()), (String)map.get("name"), Integer.parseInt(map.get("stackSize").toString())));
/*  94 */         if (!stockMap.containsKey(id))
/*  95 */           stockMap.put(id, Integer.valueOf(0));
/*     */       }
/*     */     } catch (Exception e) {
/*  98 */       e.printStackTrace();
/*     */     }
/*     */ 
/* 101 */     commission = config.getDouble("commission");
/*     */   }
/*     */ 
/*     */   public static void save(File dataFolder) {
/*     */     try {
/* 106 */       slapi.save(chestMap, dataFolder + File.separator + "chestmap.bin");
/* 107 */       slapi.save(stockMap, dataFolder + File.separator + "stockmap.bin");
/* 108 */       slapi.save(playerMap, dataFolder + File.separator + "playermap.bin");
/* 109 */       slapi.save(orderList, dataFolder + File.separator + "orderlist.bin");
/* 110 */       writer.close();
/*     */     } catch (Exception e) {
/* 112 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */ 
/*     */   public static void init(Po8Plugin plugin) {
/* 117 */     Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {
/*     */       public void run() {
/* 119 */         if (Po8.orderList.size() <= 0) {
/* 120 */           return;
/*     */         }
/* 122 */         Player[] players = Bukkit.getServer().getOnlinePlayers();
/* 123 */         for (Player player : players)
/* 124 */           if (player.hasPermission("po8.review"))
/* 125 */             Po8Util.message(player, "&dThere are&5 " + Po8.orderList.size() + " &dnew Po8 orders to be reviewed.");
/*     */       }
/*     */     }
/*     */     , 0L, 72000L);
/*     */   }
/*     */ 
/*     */   public static int stockCheck(String id) {
/* 132 */     if ((stockMap.containsKey(id)) && (itemMap.containsKey(id)) && (((Po8Item)itemMap.get(id)).active))
/* 133 */       return ((Integer)stockMap.get(id)).intValue();
/* 134 */     return -1;
/*     */   }
/*     */ 
/*     */   public static int stockAdd(String id, int amt) {
/* 138 */     int stock = stockCheck(id);
/* 139 */     if (stock >= 0) {
/* 140 */       stockMap.put(id, Integer.valueOf(stock + amt));
/* 141 */       return stock + amt;
/*     */     }
/* 143 */     return stock;
/*     */   }
/*     */ }

/* Location:           /home/casper/craftbukkit/plugins/MCNSAPo8.jar
 * Qualified Name:     com.aegamesi.mc.po8.Po8
 * JD-Core Version:    0.6.2
 */