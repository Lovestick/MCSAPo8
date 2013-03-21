/*     */ package com.aegamesi.mc.po8;
/*     */ 
/*     */ import com.aegamesi.mc.po8.support.CardboardBox;
/*     */ import com.aegamesi.mc.po8.support.SLAPI;
/*     */ import com.aegamesi.mc.po8.support.SerializedLocation;
/*     */ import java.io.BufferedReader;
/*     */ import java.io.File;
/*     */ import java.io.FileReader;
/*     */ import java.io.PrintStream;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map.Entry;
/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.Location;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.Server;
/*     */ import org.bukkit.block.Block;
/*     */ import org.bukkit.command.Command;
/*     */ import org.bukkit.command.CommandExecutor;
/*     */ import org.bukkit.command.CommandSender;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.inventory.Inventory;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ import org.bukkit.inventory.PlayerInventory;
/*     */ 
/*     */ public class Po8CommandExecutor
/*     */   implements CommandExecutor
/*     */ {
/*     */   public Po8Plugin plugin;
/*     */ 
/*     */   public Po8CommandExecutor(Po8Plugin plugin)
/*     */   {
/*  29 */     this.plugin = plugin;
/*     */   }
/*     */ 
/*     */   public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
/*     */   {
/*  34 */     boolean isPlayer = sender instanceof Player;
/*  35 */     Player p = null;
/*  36 */     if (isPlayer) {
/*  37 */       p = (Player)sender;
/*     */     }
/*     */ 
/*  40 */     if (!cmd.getName().equalsIgnoreCase("po8"))
/*  41 */       return false;
/*  42 */     if (!isPlayer) {
/*  43 */       Po8Util.message(sender, "&cYou can't use Po8 from the console!");
/*  44 */       return true;
/*     */     }
/*  46 */     if (args.length == 0) {
/*  47 */       sendHelp(sender, 1);
/*  48 */       return true;
/*     */     }
/*     */ 
/*  52 */     if (args[0].equalsIgnoreCase("help")) {
/*  53 */       int num = 0;
/*  54 */       if (args.length >= 2) {
/*     */         try {
/*  56 */           num = Integer.parseInt(args[1]);
/*     */         } catch (NumberFormatException e) {
/*  58 */           Po8Util.message(sender, "Invalid arguments for /po8 help");
/*  59 */           return true;
/*     */         }
/*     */       }
/*  62 */       sendHelp(sender, num);
/*  63 */       return true;
/*     */     }
/*     */     String from;
/*     */     String to;
/*     */     double amount;
/*  65 */     if (args[0].equalsIgnoreCase("fix")) {
/*  66 */       if (!sender.hasPermission("po8.grant")) {
/*  67 */         Po8Util.message(sender, "&cYou don't have permission to do that!");
/*  68 */         return true;
/*     */       }
/*  70 */       HashMap oldMap = null;
/*     */ 
/*  72 */       SLAPI slapi = new SLAPI();
/*     */       try {
/*  74 */         oldMap = (HashMap)slapi.load(this.plugin.getDataFolder() + File.separator + "defaults.bin");
/*     */       } catch (Exception e) {
/*  76 */         e.printStackTrace();
/*  77 */         Po8Util.message(sender, "Failed to read defaults.bin");
/*  78 */         return true;
/*     */       }
/*     */       try
/*     */       {
/*  82 */         br = new BufferedReader(new FileReader(this.plugin.getDataFolder() + File.separator + "log.csv"));
/*     */       }
/*     */       catch (Exception e)
/*     */       {
/*     */         BufferedReader br;
/*  84 */         e.printStackTrace();
/*  85 */         Po8Util.message(sender, "Failed to read log.csv");
/*  86 */         return true;
/*     */       }
/*     */       try
/*     */       {
/*     */         BufferedReader br;
/*  90 */         br.readLine();
/*  91 */         double commission = 0.02D;
/*     */         String line;
/*  92 */         while ((line = br.readLine()) != null)
/*     */         {
/*     */           String line;
/*  94 */           String[] split = line.split(",");
/*  95 */           from = split[0].substring(1, split[0].length() - 1);
/*  96 */           to = split[1].substring(1, split[1].length() - 1);
/*  97 */           amount = Double.parseDouble(split[3].substring(1, split[3].length() - 1));
/*  98 */           String type = split[4].substring(1, split[4].length() - 1);
/*  99 */           String notes = split[5].substring(1, split[5].length() - 1);
/*     */ 
/* 101 */           if (type.equals("SELL")) {
/* 102 */             if (!oldMap.containsKey(to))
/* 103 */               oldMap.put(to, new Po8Player());
/* 104 */             if (!oldMap.containsKey(notes))
/* 105 */               oldMap.put(notes, new Po8Player());
/* 106 */             ((Po8Player)oldMap.get(to)).balance += amount;
/* 107 */             if (notes != to)
/* 108 */               ((Po8Player)oldMap.get(notes)).balance += amount * commission;
/*     */           }
/* 110 */           if (type.equals("BUY")) {
/* 111 */             if (!oldMap.containsKey(from))
/* 112 */               oldMap.put(from, new Po8Player());
/* 113 */             if (!oldMap.containsKey(notes))
/* 114 */               oldMap.put(notes, new Po8Player());
/* 115 */             ((Po8Player)oldMap.get(from)).balance -= amount;
/* 116 */             if (notes != from)
/* 117 */               ((Po8Player)oldMap.get(notes)).balance += amount * commission;
/*     */           }
/* 119 */           if (type.equals("P2P_TRANSFER")) {
/* 120 */             if (!oldMap.containsKey(to))
/* 121 */               oldMap.put(to, new Po8Player());
/* 122 */             if (!oldMap.containsKey(from))
/* 123 */               oldMap.put(from, new Po8Player());
/* 124 */             ((Po8Player)oldMap.get(from)).balance -= amount;
/* 125 */             ((Po8Player)oldMap.get(to)).balance += amount;
/*     */           }
/* 127 */           if (type.equals("GRANT")) {
/* 128 */             if (!oldMap.containsKey(to))
/* 129 */               oldMap.put(to, new Po8Player());
/* 130 */             ((Po8Player)oldMap.get(to)).balance += amount;
/*     */           }
/*     */         }
/*     */ 
/* 134 */         for (Map.Entry entry : oldMap.entrySet()) {
/* 135 */           if (!Po8.playerMap.containsKey(entry.getKey()))
/* 136 */             Po8.playerMap.put((String)entry.getKey(), new Po8Player());
/* 137 */           ((Po8Player)Po8.playerMap.get(entry.getKey())).balance = ((Po8Player)entry.getValue()).balance;
/*     */         }
/*     */ 
/* 140 */         br.close();
/*     */       } catch (Exception e) {
/* 142 */         e.printStackTrace();
/* 143 */         Po8Util.message(sender, "Failed to do some stuff.");
/* 144 */         return true;
/*     */       }
/* 146 */       Po8Util.message(sender, "Fixed Po8...hopefully.");
/* 147 */       return true;
/*     */     }
/* 149 */     if (args[0].equalsIgnoreCase("balance")) {
/* 150 */       if (!sender.hasPermission("po8.balance")) {
/* 151 */         Po8Util.message(sender, "&cYou don't have permission to do that!");
/* 152 */         return true;
/*     */       }
/* 154 */       if (args.length != 1) {
/* 155 */         Po8Util.message(sender, "Invalid arguments for /po8 balance");
/* 156 */         return true;
/*     */       }
/* 158 */       Po8Util.message(sender, "You have &a" + Po8Util.round2(((Po8Player)Po8.playerMap.get(p.getName())).balance) + "&f Po8");
/* 159 */       return true;
/*     */     }
/* 161 */     if (args[0].equalsIgnoreCase("transfer")) {
/* 162 */       if (!sender.hasPermission("po8.transfer")) {
/* 163 */         Po8Util.message(sender, "&cYou don't have permission to do that!");
/* 164 */         return true;
/*     */       }
/* 166 */       if (args.length != 3) {
/* 167 */         Po8Util.message(sender, "Invalid arguments for /po8 transfer");
/* 168 */         return true;
/*     */       }
/*     */       try
/*     */       {
/* 172 */         amount = Double.parseDouble(args[2]);
/*     */       }
/*     */       catch (NumberFormatException e)
/*     */       {
/*     */         double amount;
/* 174 */         Po8Util.message(sender, "Invalid arguments for /po8 transfer");
/* 175 */         return true;
/*     */       }
/*     */       double amount;
/* 177 */       Player to = Bukkit.getPlayer(args[1]);
/* 178 */       if (to == null) {
/* 179 */         Po8Util.message(sender, "&c\"" + args[1] + "\" is not currently online");
/* 180 */         return true;
/*     */       }
/* 182 */       if ((amount < 0.0D) || (amount > ((Po8Player)Po8.playerMap.get(p.getName())).balance)) {
/* 183 */         Po8Util.message(sender, "&cYou don't have enough Po8");
/* 184 */         return true;
/*     */       }
/* 186 */       ((Po8Player)Po8.playerMap.get(p.getName())).balance -= amount;
/* 187 */       ((Po8Player)Po8.playerMap.get(to.getName())).balance += amount;
/* 188 */       Po8Util.message(p, "Transferred &a" + amount + "&f Po8 to &a" + to.getDisplayName());
/* 189 */       Po8Util.message(to, "You received &a" + amount + " Po8 from &a" + p.getDisplayName());
/* 190 */       Po8Util.log(p.getName(), to.getName(), amount, "P2P_TRANSFER", "--");
/* 191 */       return true;
/*     */     }
/* 193 */     if (args[0].equalsIgnoreCase("grant")) {
/* 194 */       if (!sender.hasPermission("po8.grant")) {
/* 195 */         Po8Util.message(sender, "&cYou don't have permission to do that!");
/* 196 */         return true;
/*     */       }
/* 198 */       if (args.length != 3) {
/* 199 */         Po8Util.message(sender, "Invalid arguments for /po8 grant");
/* 200 */         return true;
/*     */       }
/*     */       try
/*     */       {
/* 204 */         amount = Double.parseDouble(args[2]);
/*     */       }
/*     */       catch (NumberFormatException e)
/*     */       {
/*     */         double amount;
/* 206 */         Po8Util.message(sender, "Invalid arguments for /po8 grant");
/* 207 */         return true;
/*     */       }
/*     */       double amount;
/* 209 */       Player to = Bukkit.getPlayer(args[1]);
/* 210 */       if (to == null) {
/* 211 */         Po8Util.message(sender, "&c\"" + args[1] + "\" is not currently online");
/* 212 */         return true;
/*     */       }
/* 214 */       ((Po8Player)Po8.playerMap.get(to.getName())).balance += amount;
/* 215 */       Po8Util.message(p, "Granted &a" + amount + "&f Po8 to &a" + to.getDisplayName());
/* 216 */       if (amount >= 0.0D)
/* 217 */         Po8Util.message(to, "You have been granted &a" + amount + " Po8 by &a" + p.getDisplayName());
/*     */       else
/* 219 */         Po8Util.message(to, "&a" + amount + " Po8 has been removed from your account by &a" + p.getDisplayName());
/* 220 */       Po8Util.log(p.getName(), to.getName(), amount, "GRANT", "--");
/* 221 */       return true;
/*     */     }
/* 223 */     if (args[0].equalsIgnoreCase("version")) {
/* 224 */       Po8Util.message(sender, "&dPo8 Plugin by PickleMan (aegamesi) (admin.aegamesi@gmail.com) v1.2.9.0");
/* 225 */       if (args.length > 1)
/* 226 */         Po8Util.message(sender, Po8Util.combine(args, 1, 0));
/* 227 */       return true;
/*     */     }
/*     */     String item;
/* 229 */     if (args[0].equalsIgnoreCase("info")) {
/* 230 */       if ((!sender.hasPermission("po8.info")) && (isPlayer)) {
/* 231 */         Po8Util.message(sender, "&cYou don't have permission to do that!");
/* 232 */         return true;
/*     */       }
/* 234 */       if (args.length < 2) {
/* 235 */         Po8Util.message(sender, "Invalid arguments for /po8 info");
/* 236 */         return true;
/*     */       }
/* 238 */       int amount = 1;
/* 239 */       int temp = -1;
/* 240 */       if (args.length >= 3) {
/*     */         try {
/* 242 */           temp = Integer.parseInt(args[(args.length - 1)]);
/*     */         } catch (NumberFormatException e) {
/* 244 */           temp = -1;
/*     */         }
/* 246 */         if (temp != -1)
/* 247 */           amount = temp;
/*     */       }
/* 249 */       item = Po8Util.combine(args, 1, temp == -1 ? 0 : args.length - 2);
/* 250 */       String id = null;
/* 251 */       if (item.equalsIgnoreCase("held")) {
/* 252 */         ItemStack stack = p.getInventory().getItemInHand();
/* 253 */         if (stack != null)
/* 254 */           id = Po8Util.stockKey(stack.getData());
/*     */       } else {
/* 256 */         id = Po8Util.getBlock(item);
/*     */       }
/* 258 */       if ((id == null) || (Po8.stockCheck(id) < 0)) {
/* 259 */         Po8Util.message(sender, "&cCould not find item \"" + item + "\"");
/* 260 */         return true;
/*     */       }
/* 262 */       double price = Po8Util.getBasePrice(id) * amount;
/* 263 */       int stock = Po8.stockCheck(id);
/* 264 */       Po8Util.message(sender, "Po8 has &a" + stock + " " + ((Po8Item)Po8.itemMap.get(id)).name + " &f in stock");
/* 265 */       Po8Util.message(sender, "Po8 will buy &a" + amount + "x " + ((Po8Item)Po8.itemMap.get(id)).name + "&f for &a" + Po8Util.round2(price / 2.0D) + "&f Po8");
/* 266 */       Po8Util.message(sender, "Po8 will sell &a" + amount + "x " + ((Po8Item)Po8.itemMap.get(id)).name + "&f for &a" + Po8Util.round2(price) + "&f Po8");
/* 267 */       return true;
/*     */     }
/* 269 */     if (args[0].equalsIgnoreCase("spawnchest")) {
/* 270 */       if (!sender.hasPermission("po8.spawnchest")) {
/* 271 */         Po8Util.message(sender, "&cYou don't have permission to do that!");
/* 272 */         return true;
/*     */       }
/* 274 */       if (args.length != 2) {
/* 275 */         Po8Util.message(sender, "Invalid arguments for /po8 spawnchest");
/* 276 */         return true;
/*     */       }
/* 278 */       int type = 1;
/* 279 */       if ((args.length != 2) || ((!args[1].equalsIgnoreCase("buy")) && (!args[1].equalsIgnoreCase("sell")))) {
/* 280 */         Po8Util.message(sender, "Invalid arguments for /po8 spawnchest");
/* 281 */         return true;
/*     */       }
/* 283 */       type = args[1].equalsIgnoreCase("sell") ? 2 : 1;
/* 284 */       Location l = p.getLocation();
/* 285 */       l.setY(l.getBlockY() - 1);
/* 286 */       l.getBlock().setType(Material.CHEST);
/* 287 */       l.setX(l.getBlockX());
/* 288 */       l.setY(l.getBlockY());
/* 289 */       l.setZ(l.getBlockZ());
/* 290 */       l.setYaw(0.0F);
/* 291 */       l.setPitch(0.0F);
/* 292 */       Po8.chestMap.put(new SerializedLocation(l), Integer.valueOf(type));
/* 293 */       return true;
/*     */     }
/*     */     int type;
/* 295 */     if (args[0].equalsIgnoreCase("examine")) {
/* 296 */       if (!sender.hasPermission("po8.examine")) {
/* 297 */         Po8Util.message(sender, "&cYou don't have permission to do that!");
/* 298 */         return true;
/*     */       }
/* 300 */       if (args.length < 3) {
/* 301 */         Po8Util.message(sender, "Invalid arguments for /po8 examine");
/* 302 */         return true;
/*     */       }
/* 304 */       String newName = "";
/* 305 */       for (Map.Entry entry : Po8.playerMap.entrySet()) {
/* 306 */         String key = (String)entry.getKey();
/* 307 */         if (key.toLowerCase().equals(args[1].toLowerCase())) {
/* 308 */           newName = key;
/* 309 */           break;
/*     */         }
/*     */       }
/* 312 */       if (newName.length() <= 0) {
/* 313 */         Po8Util.message(sender, "&cPlayer not found. Be sure to use the exact name.");
/* 314 */         return true;
/*     */       }
/* 316 */       type = 1;
/* 317 */       if ((!args[2].equalsIgnoreCase("buy")) && (!args[2].equalsIgnoreCase("sell"))) {
/* 318 */         Po8Util.message(sender, "Invalid arguments for /po8 examine");
/* 319 */         return true;
/*     */       }
/* 321 */       type = args[2].equalsIgnoreCase("sell") ? 2 : 1;
/* 322 */       Po8Player player = (Po8Player)Po8.playerMap.get(newName);
/* 323 */       ItemStack[] inv = player.getInventory(type);
/* 324 */       Po8InventoryHolder holder = new Po8InventoryHolder(type, newName, inv);
/* 325 */       p.openInventory(holder.getInventory());
/* 326 */       Po8Util.message(sender, "&a" + newName + " &f has &a" + Po8Util.round2(((Po8Player)Po8.playerMap.get(newName)).balance) + " &fPo8");
/* 327 */       return true;
/*     */     }
/*     */     ItemStack[] inv;
/*     */     Player[] players;
/*     */     Player player;
/* 329 */     if (args[0].equalsIgnoreCase("sell")) {
/* 330 */       if (!sender.hasPermission("po8.sell")) {
/* 331 */         Po8Util.message(sender, "&cYou don't have permission to do that!");
/* 332 */         return true;
/*     */       }
/* 334 */       double totalValue = 0.0D;
/* 335 */       boolean notAllowed = false;
/* 336 */       ArrayList sellOrder = new ArrayList();
/* 337 */       inv = ((Po8Player)Po8.playerMap.get(sender.getName())).getInventory(2);
/* 338 */       for (int i = 0; i < inv.length; i++) {
/* 339 */         ItemStack stack = inv[i];
/* 340 */         if (stack != null)
/*     */         {
/* 342 */           if (Po8.stockCheck(Po8Util.stockKey(stack.getData())) >= 0) {
/* 343 */             totalValue += Po8Util.getBasePrice(Po8Util.stockKey(stack.getData())) / 2.0D * stack.getAmount();
/* 344 */             sellOrder.add(stack);
/* 345 */             inv[i] = null;
/*     */           } else {
/* 347 */             notAllowed = true;
/*     */           }
/*     */         }
/*     */       }
/* 350 */       if (totalValue > 0.0D) {
/* 351 */         ((Po8Player)Po8.playerMap.get(sender.getName())).setInventory(2, inv);
/* 352 */         Po8Util.message(sender, "&6Successfully Submitted Sell Order!");
/* 353 */         Po8Util.message(sender, "Please wait for processing. Value: &a" + Po8Util.round2(totalValue) + "&f Po8");
/* 354 */         Po8.orderList.add(new Po8Order(2, totalValue, sender.getName(), (ItemStack[])sellOrder.toArray(new ItemStack[0])));
/*     */       } else {
/* 356 */         Po8Util.message(sender, "&cError: Empty Sell Order");
/* 357 */         Player[] players = Bukkit.getServer().getOnlinePlayers();
/* 358 */         to = (amount = players).length; for (from = 0; from < to; from++) { Player player = amount[from];
/* 359 */           if ((player.hasPermission("po8.review")) && (((Po8Player)Po8.playerMap.get(player.getName())).notify))
/* 360 */             Po8Util.message(player, "&dNOTE: The following user submitted an empty sell order: " + sender.getName());
/*     */         }
/* 362 */         return true;
/*     */       }
/* 364 */       if (notAllowed) {
/* 365 */         Po8Util.message(sender, "&dWARNING: Your sell chest contains one or more unsellable items!");
/*     */       }
/* 367 */       players = Bukkit.getServer().getOnlinePlayers();
/* 368 */       to = (amount = players).length; for (from = 0; from < to; from++) { player = amount[from];
/* 369 */         if ((player.hasPermission("po8.review")) && (((Po8Player)Po8.playerMap.get(player.getName())).notify))
/* 370 */           Po8Util.message(player, "&dThere is a new Po8 order to be reviewed. Total: &5 " + Po8.orderList.size());
/*     */       }
/* 372 */       return true;
/*     */     }
/*     */     ItemStack stack;
/* 374 */     if (args[0].equalsIgnoreCase("value")) {
/* 375 */       if (!sender.hasPermission("po8.value")) {
/* 376 */         Po8Util.message(sender, "&cYou don't have permission to do that!");
/* 377 */         return true;
/*     */       }
/* 379 */       double totalValue = 0.0D;
/* 380 */       boolean notAllowed = false;
/* 381 */       players = (player = ((Po8Player)Po8.playerMap.get(sender.getName())).getInventory(2)).length; for (inv = 0; inv < players; inv++) { stack = player[inv];
/* 382 */         if (stack != null)
/*     */         {
/* 384 */           if (Po8.stockCheck(Po8Util.stockKey(stack.getData())) >= 0)
/* 385 */             totalValue += Po8Util.getBasePrice(Po8Util.stockKey(stack.getData())) / 2.0D * stack.getAmount();
/*     */           else
/* 387 */             notAllowed = true;
/*     */         } }
/* 389 */       Po8Util.message(sender, "The total value of your Po8 Sell Chest is &a" + Po8Util.round2(totalValue) + "&f Po8");
/* 390 */       if (notAllowed)
/* 391 */         Po8Util.message(sender, "&dWARNING: Your sell chest contains one or more unsellable items!");
/* 392 */       return true;
/*     */     }
/*     */     String id;
/*     */     String key;
/*     */     Player[] players;
/*     */     Player player;
/* 394 */     if (args[0].equalsIgnoreCase("order")) {
/* 395 */       if (!sender.hasPermission("po8.order")) {
/* 396 */         Po8Util.message(sender, "&cYou don't have permission to do that!");
/* 397 */         return true;
/*     */       }
/* 399 */       if (args.length < 2) {
/* 400 */         sendHelp(sender, 2);
/* 401 */         return true;
/*     */       }
/* 403 */       if (args[1].equalsIgnoreCase("new")) {
/* 404 */         Po8Util.message(sender, "&dCreated a new order");
/* 405 */         ((Po8Player)Po8.playerMap.get(sender.getName())).buyOrder.clear();
/* 406 */         return true;
/*     */       }
/* 408 */       if (args[1].equalsIgnoreCase("price")) {
/* 409 */         double totalValue = 0.0D;
/* 410 */         for (Map.Entry entry : ((Po8Player)Po8.playerMap.get(sender.getName())).buyOrder.entrySet()) {
/* 411 */           String key = (String)entry.getKey();
/* 412 */           int value = ((Integer)entry.getValue()).intValue();
/* 413 */           totalValue += Po8Util.getBasePrice(key) * value;
/*     */         }
/* 415 */         Po8Util.message(sender, "The total price of your Po8 Buy Order is &a" + Po8Util.round2(totalValue) + "&f Po8");
/* 416 */         return true;
/*     */       }
/* 418 */       if (args[1].equalsIgnoreCase("list")) {
/* 419 */         Po8Util.message(sender, "&6---- Current Po8 Order ----");
/* 420 */         for (Map.Entry entry : ((Po8Player)Po8.playerMap.get(sender.getName())).buyOrder.entrySet()) {
/* 421 */           String key = (String)entry.getKey();
/* 422 */           int value = ((Integer)entry.getValue()).intValue();
/* 423 */           Po8Util.message(sender, "&a" + value + "&fx&a " + ((Po8Item)Po8.itemMap.get(key)).name);
/*     */         }
/* 425 */         return true;
/*     */       }
/* 427 */       if (args[1].equalsIgnoreCase("add")) {
/* 428 */         if (args.length < 3) {
/* 429 */           Po8Util.message(sender, "Invalid arguments for /po8 order add");
/* 430 */           return true;
/*     */         }
/* 432 */         int amount = 1;
/* 433 */         int temp = -1;
/* 434 */         if (args.length >= 4) {
/*     */           try {
/* 436 */             temp = Integer.parseInt(args[(args.length - 1)]);
/*     */           } catch (NumberFormatException e) {
/* 438 */             temp = -1;
/*     */           }
/* 440 */           if (temp != -1)
/* 441 */             amount = temp;
/*     */         }
/* 443 */         String item = Po8Util.combine(args, 2, temp == -1 ? 0 : args.length - 3);
/* 444 */         System.out.println(item);
/* 445 */         String id = null;
/* 446 */         if (item.equalsIgnoreCase("held")) {
/* 447 */           ItemStack stack = p.getInventory().getItemInHand();
/* 448 */           if (stack != null)
/* 449 */             id = Po8Util.stockKey(stack.getData());
/*     */         } else {
/* 451 */           id = Po8Util.getBlock(item);
/*     */         }
/* 453 */         int stock = id == null ? -1 : Po8.stockCheck(id);
/* 454 */         if (stock < 0) {
/* 455 */           Po8Util.message(sender, "&cCould not find item \"" + item + "\"");
/* 456 */           return true;
/*     */         }
/* 458 */         int current = ((Po8Player)Po8.playerMap.get(sender.getName())).buyOrder.containsKey(id) ? ((Integer)((Po8Player)Po8.playerMap.get(sender.getName())).buyOrder.get(id)).intValue() : 0;
/* 459 */         current += amount;
/* 460 */         if (current > stock) {
/* 461 */           Po8Util.message(sender, "&cWARNING: Po8 only has " + stock + " of those. Your order has been reduced.");
/* 462 */           current = stock;
/*     */         }
/* 464 */         ((Po8Player)Po8.playerMap.get(sender.getName())).buyOrder.put(id, Integer.valueOf(current));
/* 465 */         Po8Util.message(sender, "Your order now contains &a" + current + " " + ((Po8Item)Po8.itemMap.get(id)).name);
/* 466 */         return true;
/*     */       }
/*     */       int current;
/* 468 */       if (args[1].equalsIgnoreCase("remove")) {
/* 469 */         if (args.length < 3) {
/* 470 */           Po8Util.message(sender, "Invalid arguments for /po8 order remove");
/* 471 */           return true;
/*     */         }
/* 473 */         int amount = 1;
/* 474 */         int temp = -1;
/* 475 */         if (args.length >= 4) {
/*     */           try {
/* 477 */             temp = Integer.parseInt(args[(args.length - 1)]);
/*     */           } catch (NumberFormatException e) {
/* 479 */             temp = -1;
/*     */           }
/* 481 */           if (temp != -1)
/* 482 */             amount = temp;
/*     */         }
/* 484 */         String item = Po8Util.combine(args, 2, temp == -1 ? 0 : args.length - 3);
/* 485 */         id = Po8Util.getBlock(item);
/* 486 */         int stock = id == null ? -1 : Po8.stockCheck(id);
/* 487 */         if (stock < 0) {
/* 488 */           Po8Util.message(sender, "&cCould not find item \"" + item + "\"");
/* 489 */           return true;
/*     */         }
/* 491 */         current = ((Po8Player)Po8.playerMap.get(sender.getName())).buyOrder.containsKey(id) ? ((Integer)((Po8Player)Po8.playerMap.get(sender.getName())).buyOrder.get(id)).intValue() : 0;
/* 492 */         current -= amount;
/* 493 */         if (current <= 0) {
/* 494 */           current = 0;
/* 495 */           ((Po8Player)Po8.playerMap.get(sender.getName())).buyOrder.remove(id);
/*     */         }
/* 497 */         ((Po8Player)Po8.playerMap.get(sender.getName())).buyOrder.put(id, Integer.valueOf(current));
/* 498 */         Po8Util.message(sender, "Your order now contains &a" + current + " " + ((Po8Item)Po8.itemMap.get(id)).name);
/* 499 */         return true;
/*     */       }
/* 501 */       if (args[1].equalsIgnoreCase("submit")) {
/* 502 */         if (((Po8Player)Po8.playerMap.get(sender.getName())).buyOrder.size() <= 0) {
/* 503 */           Po8Util.message(sender, "&cThe current buy order is empty!");
/* 504 */           return true;
/*     */         }
/* 506 */         ArrayList stack = new ArrayList();
/* 507 */         boolean reduced = false;
/* 508 */         double price = 0.0D;
/*     */         int stock;
/* 509 */         for (Map.Entry entry : ((Po8Player)Po8.playerMap.get(sender.getName())).buyOrder.entrySet()) {
/* 510 */           key = (String)entry.getKey();
/* 511 */           amt = ((Integer)entry.getValue()).intValue();
/* 512 */           stock = Po8.stockCheck(key);
/* 513 */           if (stock < amt) {
/* 514 */             reduced = true;
/* 515 */             amt = stock;
/*     */           }
/* 517 */           if (amt > 0)
/*     */           {
/* 519 */             price += Po8Util.getBasePrice(key) * amt;
/* 520 */             Collections.addAll(stack, Po8Util.splitStack(key, amt));
/*     */           }
/*     */         }
/* 522 */         if (price <= 0.0D) {
/* 523 */           Po8Util.message(sender, "&cThe current buy order is empty!");
/* 524 */           return true;
/*     */         }
/* 526 */         if (stack.size() > 54) {
/* 527 */           Po8Util.message(sender, "&cYou cannot buy more than 54 stacks of items at a time! Current order: " + stack.size());
/* 528 */           return true;
/*     */         }
/* 530 */         if (reduced)
/* 531 */           Po8Util.message(sender, "&dWARNING: Po8 does not have all of the items you have requested! Those items have been removed.");
/* 532 */         if (price > ((Po8Player)Po8.playerMap.get(sender.getName())).balance) {
/* 533 */           Po8Util.message(sender, "&cYou do not have enough Po8! Remove some items and try again.");
/* 534 */           Po8Util.message(sender, "&cAvailable balance: " + ((Po8Player)Po8.playerMap.get(sender.getName())).balance + ", Required: " + price);
/* 535 */           return true;
/*     */         }
/* 537 */         Po8Util.message(sender, "&6Successfully Submitted Buy Order!");
/* 538 */         Po8Util.message(sender, "Please wait for processing. Price: &a" + price + "&f Po8");
/* 539 */         ((Po8Player)Po8.playerMap.get(sender.getName())).buyOrder.clear();
/* 540 */         Po8.orderList.add(new Po8Order(1, price, sender.getName(), (ItemStack[])stack.toArray(new ItemStack[0])));
/* 541 */         for (ItemStack itemstack : stack)
/* 542 */           Po8.stockAdd(Po8Util.stockKey(itemstack.getData()), -itemstack.getAmount());
/* 543 */         ((Po8Player)Po8.playerMap.get(sender.getName())).balance -= price;
/*     */ 
/* 545 */         players = Bukkit.getServer().getOnlinePlayers();
/* 546 */         int amt = (stock = players).length; for (key = 0; key < amt; key++) { player = stock[key];
/* 547 */           if ((player.hasPermission("po8.review")) && (((Po8Player)Po8.playerMap.get(player.getName())).notify))
/* 548 */             Po8Util.message(player, "&dThere is a new Po8 order to be reviewed. Total: &5 " + Po8.orderList.size());
/*     */         }
/* 550 */         return true;
/*     */       }
/* 552 */       sendHelp(sender, 2);
/* 553 */       return true;
/*     */     }
/* 555 */     if (args[0].equalsIgnoreCase("review"))
/*     */     {
/* 557 */       if (!sender.hasPermission("po8.review")) {
/* 558 */         Po8Util.message(sender, "&cYou don't have permission to do that!");
/* 559 */         return true;
/*     */       }
/* 561 */       if (args.length < 2) {
/* 562 */         sendHelp(sender, 3);
/* 563 */         return true;
/*     */       }
/*     */ 
/* 568 */       if (args[1].equalsIgnoreCase("deny")) {
/* 569 */         if (((Po8Player)Po8.playerMap.get(sender.getName())).reviewOrder == null) {
/* 570 */           Po8Util.message(sender, "&cYou are not currently reviewing an order!");
/* 571 */           return true;
/*     */         }
/* 573 */         if (args.length < 3) {
/* 574 */           Po8Util.message(sender, "Invalid arguments for /po8 review deny");
/* 575 */           return true;
/*     */         }
/* 577 */         String reason = Po8Util.combine(args, 2, 0);
/* 578 */         Po8Order order = ((Po8Player)Po8.playerMap.get(sender.getName())).reviewOrder;
/* 579 */         ((Po8Player)Po8.playerMap.get(sender.getName())).reviewOrder = null;
/* 580 */         if (Bukkit.getPlayerExact(order.owner) != null) {
/* 581 */           if (order.type == 1)
/* 582 */             Po8Util.message(Bukkit.getPlayerExact(order.owner), "&cYour Po8 Buy order has been denied. Your Po8 has been returned to you.");
/* 583 */           if (order.type == 2)
/* 584 */             Po8Util.message(Bukkit.getPlayerExact(order.owner), "&cYour Po8 Sell order has been denied. Your items are in your Po8 Buy Chest");
/* 585 */           Po8Util.message(Bukkit.getPlayerExact(order.owner), "&cReason: &f" + reason);
/*     */         }
/* 587 */         if (order.type == 1) {
/* 588 */           ((Po8Player)Po8.playerMap.get(order.owner)).balance += order.value;
/* 589 */           players = (player = order.getItems()).length; for (id = 0; id < players; id++) { ItemStack stack = player[id];
/* 590 */             Po8.stockAdd(Po8Util.stockKey(stack.getData()), stack.getAmount()); }
/* 591 */           Po8Util.log(order.owner, "Po8", order.value, "BUY_DENIED", p.getName() + " | " + reason);
/*     */         }
/* 593 */         if (order.type == 2)
/*     */         {
/* 595 */           ArrayList stacks = new ArrayList();
/* 596 */           Collections.addAll(stacks, order.getItems());
/* 597 */           System.out.println(order.getItems().length + " " + stacks.size());
/* 598 */           ItemStack[] buyInv = ((Po8Player)Po8.playerMap.get(order.owner)).getInventory(1);
/* 599 */           for (int i = 0; i < buyInv.length; i++) {
/* 600 */             if (stacks.size() <= 0)
/*     */               break;
/* 602 */             if (buyInv[i] == null)
/* 603 */               buyInv[i] = ((ItemStack)stacks.remove(0));
/*     */           }
/* 605 */           ((Po8Player)Po8.playerMap.get(order.owner)).setInventory(1, buyInv);
/* 606 */           for (int i = 0; i < stacks.size(); i++)
/* 607 */             ((Po8Player)Po8.playerMap.get(order.owner)).extendedInv.add(new CardboardBox((ItemStack)stacks.get(i)));
/* 608 */           Po8Util.log("Po8", order.owner, order.value, "SELL_DENIED", p.getName() + " | " + reason);
/*     */         }
/* 610 */         Po8Util.message(sender, "&dOrder denied.");
/* 611 */         return true;
/*     */       }
/* 613 */       if (args[1].equalsIgnoreCase("accept")) {
/* 614 */         if (((Po8Player)Po8.playerMap.get(sender.getName())).reviewOrder == null) {
/* 615 */           Po8Util.message(sender, "&cYou are not currently reviewing an order!");
/* 616 */           return true;
/*     */         }
/* 618 */         Po8Order order = ((Po8Player)Po8.playerMap.get(sender.getName())).reviewOrder;
/* 619 */         ((Po8Player)Po8.playerMap.get(sender.getName())).reviewOrder = null;
/* 620 */         double commission = Po8Util.round2(order.value * (Po8.commission / 100.0D));
/* 621 */         if (!sender.getName().toLowerCase().equals(order.owner.toLowerCase())) {
/* 622 */           Po8Util.message(sender, "&a" + Po8Util.round2(commission) + "&f Po8 of commission has been sent to your Po8 account");
/* 623 */           ((Po8Player)Po8.playerMap.get(sender.getName())).balance += commission;
/*     */         }
/* 625 */         if (Bukkit.getPlayerExact(order.owner) != null) {
/* 626 */           if (order.type == 1)
/* 627 */             Po8Util.message(Bukkit.getPlayerExact(order.owner), "There are new items in your Po8 Buy Chest!");
/* 628 */           if (order.type == 2)
/* 629 */             Po8Util.message(Bukkit.getPlayerExact(order.owner), "You have successfully sold &a" + Po8Util.round2(order.value) + " &fPo8 worth of items!");
/*     */         }
/*     */         ItemStack[] buyInv;
/*     */         int i;
/* 631 */         if (order.type == 1)
/*     */         {
/* 633 */           ArrayList stacks = new ArrayList();
/* 634 */           Collections.addAll(stacks, order.getItems());
/* 635 */           System.out.println(order.getItems().length + " " + stacks.size());
/* 636 */           buyInv = ((Po8Player)Po8.playerMap.get(order.owner)).getInventory(1);
/* 637 */           for (int i = 0; i < buyInv.length; i++) {
/* 638 */             if (stacks.size() <= 0)
/*     */               break;
/* 640 */             if (buyInv[i] == null)
/* 641 */               buyInv[i] = ((ItemStack)stacks.remove(0));
/*     */           }
/* 643 */           ((Po8Player)Po8.playerMap.get(order.owner)).setInventory(1, buyInv);
/* 644 */           for (i = 0; i < stacks.size(); i++)
/* 645 */             ((Po8Player)Po8.playerMap.get(order.owner)).extendedInv.add(new CardboardBox((ItemStack)stacks.get(i)));
/* 646 */           Po8Util.log(order.owner, "Po8", order.value, "BUY", p.getName());
/*     */         }
/* 648 */         if (order.type == 2)
/*     */         {
/* 650 */           ((Po8Player)Po8.playerMap.get(order.owner)).balance += order.value;
/* 651 */           i = (key = order.getItems()).length; for (buyInv = 0; buyInv < i; buyInv++) { ItemStack stack = key[buyInv];
/* 652 */             Po8.stockAdd(Po8Util.stockKey(stack.getData()), stack.getAmount()); }
/* 653 */           Po8Util.log("Po8", order.owner, order.value, "SELL", p.getName());
/*     */         }
/*     */ 
/* 656 */         return true;
/*     */       }
/* 658 */       if (args[1].equalsIgnoreCase("status")) {
/* 659 */         Po8Util.message(sender, "&dThere are&5 " + Po8.orderList.size() + " &dnew Po8 orders to be reviewed.");
/* 660 */         return true;
/*     */       }
/* 662 */       if (args[1].equalsIgnoreCase("skip")) {
/* 663 */         if (((Po8Player)Po8.playerMap.get(sender.getName())).reviewOrder != null) {
/* 664 */           Po8.orderList.add(((Po8Player)Po8.playerMap.get(sender.getName())).reviewOrder);
/* 665 */           Po8Util.message(sender, "&dSkipping current review order");
/* 666 */           ((Po8Player)Po8.playerMap.get(sender.getName())).reviewOrder = null;
/* 667 */           return true;
/*     */         }
/* 669 */         Po8Util.message(sender, "&cYou are not currently reviewing an order!");
/* 670 */         return true;
/*     */       }
/* 672 */       if (args[1].equalsIgnoreCase("next")) {
/* 673 */         if (((Po8Player)Po8.playerMap.get(sender.getName())).reviewOrder != null) {
/* 674 */           Po8Util.message(sender, "&cYou must accept, deny, or skip your current review!");
/* 675 */           return true;
/*     */         }
/* 677 */         if (Po8.orderList.size() <= 0) {
/* 678 */           Po8Util.message(sender, "&dThere are&5 0 &dnew Po8 orders to be reviewed.");
/* 679 */           return true;
/*     */         }
/* 681 */         ((Po8Player)Po8.playerMap.get(sender.getName())).reviewOrder = ((Po8Order)Po8.orderList.remove(0));
/* 682 */         Po8Order review = ((Po8Player)Po8.playerMap.get(sender.getName())).reviewOrder;
/* 683 */         Inventory inv = Bukkit.getServer().createInventory(null, 54, review.owner + "'s " + (review.type == 2 ? "Sell Order" : "Buy Order"));
/* 684 */         inv.setContents(review.getItems());
/* 685 */         p.openInventory(inv);
/* 686 */         ((Po8Player)Po8.playerMap.get(sender.getName())).isReviewingOrder = true;
/* 687 */         return true;
/*     */       }
/* 689 */       if (args[1].equalsIgnoreCase("show")) {
/* 690 */         if (((Po8Player)Po8.playerMap.get(sender.getName())).reviewOrder == null) {
/* 691 */           Po8Util.message(sender, "&cYou are not currently reviewing any order!");
/* 692 */           return true;
/*     */         }
/* 694 */         Po8Order review = ((Po8Player)Po8.playerMap.get(sender.getName())).reviewOrder;
/* 695 */         Inventory inv = Bukkit.getServer().createInventory(null, 54, review.owner + "'s " + (review.type == 2 ? "Sell Order" : "Buy Order"));
/* 696 */         inv.setContents(review.getItems());
/* 697 */         p.openInventory(inv);
/* 698 */         ((Po8Player)Po8.playerMap.get(sender.getName())).isReviewingOrder = true;
/* 699 */         return true;
/*     */       }
/* 701 */       if (args[1].equalsIgnoreCase("togglenotify")) {
/* 702 */         ((Po8Player)Po8.playerMap.get(sender.getName())).notify = (!((Po8Player)Po8.playerMap.get(sender.getName())).notify);
/* 703 */         Po8Util.message(sender, "Toggled. New setting: " + ((Po8Player)Po8.playerMap.get(sender.getName())).notify);
/* 704 */         return true;
/*     */       }
/* 706 */       sendHelp(sender, 3);
/* 707 */       return true;
/*     */     }
/* 709 */     sendHelp(sender, 1);
/* 710 */     return true;
/*     */   }
/*     */ 
/*     */   public void sendHelp(CommandSender to, int page) {
/* 714 */     Po8Util.message(to, "&a---------- &2Po8 Help &a----------");
/* 715 */     page = Math.min(3, Math.max(1, page));
/*     */ 
/* 717 */     if (page == 1) {
/* 718 */       Po8Util.message(to, " &e/po8 help [page] - &7Shows help");
/* 719 */       if (to.hasPermission("po8.balance"))
/* 720 */         Po8Util.message(to, " &e/po8 balance - &7Checks your Po8 balance");
/* 721 */       if (to.hasPermission("po8.transfer"))
/* 722 */         Po8Util.message(to, " &e/po8 transfer [username] [amount] - &7Transfers some Po8 to another player");
/* 723 */       if (to.hasPermission("po8.info"))
/* 724 */         Po8Util.message(to, " &e/po8 info [item name|held|id:data] [amount] - &7Checks the buy/sell price and stock for a given item. Amount is optional");
/* 725 */       if (to.hasPermission("po8.sell"))
/* 726 */         Po8Util.message(to, " &e/po8 sell - &7Sells the contents of your sell chest");
/* 727 */       if (to.hasPermission("po8.value"))
/* 728 */         Po8Util.message(to, " &e/po8 value - &7Values the contents of your sell chest");
/* 729 */       Po8Util.message(to, "&dPo8 Help: Page (1/3)");
/*     */     }
/* 731 */     if (page == 2) {
/* 732 */       if (to.hasPermission("po8.order")) {
/* 733 */         Po8Util.message(to, " &e/po8 order new - &7Creates a new order, discarding the current one");
/* 734 */         Po8Util.message(to, " &e/po8 order add [item name|held|id:data] [amount] - &7Adds the specified item to the current order");
/* 735 */         Po8Util.message(to, " &e/po8 order remove [item name|held|id:data] [amount] - &7Removes the specified item the current order");
/* 736 */         Po8Util.message(to, " &e/po8 order price - &7Checks the price of current order");
/* 737 */         Po8Util.message(to, " &e/po8 order list - &7Lists the items in your current order");
/* 738 */         Po8Util.message(to, " &e/po8 order submit - &7Submits the order");
/*     */       }
/* 740 */       if (to.hasPermission("po8.examine")) {
/* 741 */         Po8Util.message(to, " &e/po8 examine [player] [type] - &7Views the given Po8 chest of a player. Must be exact.");
/* 742 */         Po8Util.message(to, " -------- &7Types: 'buy' or 'sell' ");
/*     */       }
/* 744 */       Po8Util.message(to, "&dPo8 Help: Page (2/3)");
/*     */     }
/* 746 */     if (page == 3) {
/* 747 */       if (to.hasPermission("po8.review")) {
/* 748 */         Po8Util.message(to, " &e/po8 review status - &7Checks how many orders are in the review queue");
/* 749 */         Po8Util.message(to, " &e/po8 review next - &7Shows you the next review in the queue.");
/* 750 */         Po8Util.message(to, " &e/po8 review skip - &7Skips the current review order");
/* 751 */         Po8Util.message(to, " &e/po8 review show - &7Shows you the current order to review if you closed it.");
/* 752 */         Po8Util.message(to, " &e/po8 review accept - &7Accepts the current review");
/* 753 */         Po8Util.message(to, " &e/po8 review deny [reason] - &7Denies the current review");
/* 754 */         Po8Util.message(to, " &e/po8 review togglenotify - &7Toggles notifying on every Po8 Order");
/*     */       }
/* 756 */       if (to.hasPermission("po8.spawnchest")) {
/* 757 */         Po8Util.message(to, " &e/po8 spawnchest [type] - &7Turns the block you're standing on into a special chest");
/* 758 */         Po8Util.message(to, "  -------- &7Types: 'buy' or 'sell' ");
/*     */       }
/* 760 */       if (to.hasPermission("po8.grant")) {
/* 761 */         Po8Util.message(to, " &e/po8 grant [username] [amount] - &7Grants some Po8 to a player");
/*     */       }
/* 763 */       Po8Util.message(to, "&dPo8 Help: Page (3/3)");
/*     */     }
/*     */   }
/*     */ }

/* Location:           /home/casper/craftbukkit/plugins/MCNSAPo8.jar
 * Qualified Name:     com.aegamesi.mc.po8.Po8CommandExecutor
 * JD-Core Version:    0.6.2
 */