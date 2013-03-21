/*    */ import com.aegamesi.mc.po8.Po8Player;
/*    */ import com.aegamesi.mc.po8.support.SLAPI;
/*    */ import java.io.BufferedReader;
/*    */ import java.io.FileReader;
/*    */ import java.io.IOException;
/*    */ import java.io.PrintStream;
/*    */ import java.util.HashMap;
/*    */ import java.util.Map.Entry;
/*    */ 
/*    */ public class CalculateTotals
/*    */ {
/*    */   public static void main(String[] args)
/*    */     throws IOException
/*    */   {
/* 12 */     HashMap oldMap = null;
/*    */ 
/* 14 */     SLAPI slapi = new SLAPI();
/*    */     try {
/* 16 */       oldMap = (HashMap)slapi.load("defaults.bin");
/*    */     } catch (Exception e) {
/* 18 */       e.printStackTrace();
/* 19 */       return;
/*    */     }
/*    */ 
/* 22 */     BufferedReader br = new BufferedReader(new FileReader("log.csv"));
/*    */ 
/* 24 */     br.readLine();
/* 25 */     double commission = 0.02D;
/*    */     String from;
/*    */     String line;
/* 26 */     while ((line = br.readLine()) != null)
/*    */     {
/*    */       String line;
/* 28 */       String[] split = line.split(",");
/* 29 */       from = split[0].substring(1, split[0].length() - 1);
/* 30 */       String to = split[1].substring(1, split[1].length() - 1);
/* 31 */       double amount = Double.parseDouble(split[3].substring(1, split[3].length() - 1));
/* 32 */       String type = split[4].substring(1, split[4].length() - 1);
/* 33 */       String notes = split[5].substring(1, split[5].length() - 1);
/*    */ 
/* 35 */       if (type.equals("SELL")) {
/* 36 */         if (!oldMap.containsKey(to))
/* 37 */           oldMap.put(to, new Po8Player());
/* 38 */         if (!oldMap.containsKey(notes))
/* 39 */           oldMap.put(notes, new Po8Player());
/* 40 */         ((Po8Player)oldMap.get(to)).balance += amount;
/* 41 */         if (notes != to)
/* 42 */           ((Po8Player)oldMap.get(notes)).balance += amount * commission;
/*    */       }
/* 44 */       if (type.equals("BUY")) {
/* 45 */         if (!oldMap.containsKey(from))
/* 46 */           oldMap.put(from, new Po8Player());
/* 47 */         if (!oldMap.containsKey(notes))
/* 48 */           oldMap.put(notes, new Po8Player());
/* 49 */         ((Po8Player)oldMap.get(from)).balance -= amount;
/* 50 */         if (notes != from)
/* 51 */           ((Po8Player)oldMap.get(notes)).balance += amount * commission;
/*    */       }
/* 53 */       if (type.equals("P2P_TRANSFER")) {
/* 54 */         if (!oldMap.containsKey(to))
/* 55 */           oldMap.put(to, new Po8Player());
/* 56 */         if (!oldMap.containsKey(from))
/* 57 */           oldMap.put(from, new Po8Player());
/* 58 */         ((Po8Player)oldMap.get(from)).balance -= amount;
/* 59 */         ((Po8Player)oldMap.get(to)).balance += amount;
/*    */       }
/* 61 */       if (type.equals("GRANT")) {
/* 62 */         if (!oldMap.containsKey(to))
/* 63 */           oldMap.put(to, new Po8Player());
/* 64 */         ((Po8Player)oldMap.get(to)).balance += amount;
/*    */       }
/*    */     }
/*    */ 
/* 68 */     for (Map.Entry entry : oldMap.entrySet()) {
/* 69 */       String key = (String)entry.getKey();
/* 70 */       Po8Player value = (Po8Player)entry.getValue();
/* 71 */       System.out.println(key + ": " + value.balance);
/*    */     }
/*    */ 
/* 74 */     br.close();
/*    */   }
/*    */ }

/* Location:           /home/casper/craftbukkit/plugins/MCNSAPo8.jar
 * Qualified Name:     CalculateTotals
 * JD-Core Version:    0.6.2
 */