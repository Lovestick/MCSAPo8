/*    */ import com.aegamesi.mc.po8.Po8;
/*    */ import com.aegamesi.mc.po8.Po8Player;
/*    */ import com.aegamesi.mc.po8.support.SLAPI;
/*    */ import java.io.PrintStream;
/*    */ import java.util.HashMap;
/*    */ 
/*    */ public class Migrate
/*    */ {
/*    */   public static void main(String[] args)
/*    */   {
/*  9 */     String[] accounts = { "Jeffysun,0", "TheHazz,0", "M4D_H4tt3r,100", "Ramzea,0", "Theseventh5tun,0", "Microsoftt,0", "Spokensquid,263.38", "JudgeAnderson,11.49", "LinglingDreyer,0", "Atlasrune,4320", "Tana64,0", "Cdye,0", "Osamabinsteve,0", "Hamm5,0", "Spacecatapult,0", "Universe7242,4560.63", "Octavian0,2774.07", "ridddle,0", "geo54321,0", "Shmitzy,0", "Altimeter,0", "DuckyMQ,0", "OsamaBinJacob,0", "dotlizard,38314.83", "StealMyUserName,0", "Olarin,4432.79", "fa113nstar,564.48", "THAGUY115,0", "FlyswatterNBR1,0", "Nsanidy,0", "FuzzyWuzzie,138.73", "jamesarcher,0", "TheNarwhal9,0", "Nadarekyle,0", "rape_soda,0", "shrood,139.29", "Tehcmc,0", "Stonewall,0", "A_Fleming,2586.33", "Fulker,0", "geekton,0", "blackrosebronwen,0", "mattmoo60,313.53", "Hammy,0", "cdye011,0", 
/* 10 */       "nightmare5463,0", "TheRedHulk,21912.7", "LeMendiant,0", "illblew,0", "Staar003,0", "biggergeek,0", "gilligan2011,0", "cmspano,1324.24", "KorusRay,339.38", "gravehorrorvacui,0", "LeotCol,0", "whysoomayo,0", "BlackDawn,0", "BlackDawn12,0", "TestEgg,0", "RowanSpirit,0", "ZachHinnergardt,0", "zerpa,1002.24", "CrunchyBeefTaco,0", "will3001,77.35", "Delta_Com,0", "celtic7,0", "WatchMyStun,0", "10wcrete1,0", "recycle0rdie,0", "TMinus13,0", "Coffeeworld,0", "diahdude,0", "Ereh_Dogon,0", "Quantum0700,0", "rohan1121,0", "kaykat21,0", "vacguy99,0", "Alderos,0", "Young_Maker,97.52", "williamg,0", "SkeletonCakes,0", "DevilsDan,0", "fusty,0", "Vaniteux,0", "djftl,0", "Leonheart515,0", "Cronos72,0", "benefit1970,314.42", "euanbrunton,0", "jmankile,10.95", "lizboo,0", "gen_erator,0", 
/* 11 */       "smokeycat25,0", "Smooth_Zeek,0", "Corp_Chem,0", "Tengoku_Furasshu,19714.76", "zztzed,692.25", "meccano300,0", "_SgtSarcasm_,0", "Hammi_Teh_Noob,0", "MacAulayS,0", "wigginman,344", "MrMaffoo,0", "Katantunoro,937.84", "Geweldigheid,0", "TheWashout,0", "Crunchybeeftac0,0", "WazabiNut,0", "SpinnerMaster,0", "sam_c,0", "nathanielwise,0", "Variance,0", "L7Lynx,0", "Amras_Telrunya,0", "zazer98,0", "adamajor,0", "Polarisknife,2700.26", "Poopdapoop,0", "darkmatter010,0", "comrief14,0", "bones9302,2360.36", "LinkOcarinaTime,0", "The_Lorax,3118.24", "th3saget,0", "stormcrow1,0", "quick_demon,13.92", "Dogon11,0", "Woparsons,0", "7411hihi,0", "WhiteGoodman,0", "sigilance,0", "xJayr,0", "CoolWaves,0", "Tha_Zodiac,0", "The_big_builds,0", "SuburbanRiot,0", "Butlerlog,2389", 
/* 12 */       "Sinjin127,0", "johnasmith,0", "Arectaris,0", "brianmccarthy989,0", "joshnclank,0", "DiamondDragon26,0", "Dazmic,0", "sev6565,0", "niebe,0", "travb1972,0", "prismchan,0", "quantumkitty,0", "nachtegaal,236.58", "always_molly,0", "Tropez,0", "puppies597,425.98", "derkonig7,0", "Tamerz,0", "rpresh,0", "TheFreeWind,0", "Demonshin777,176", "ManicMonday,0", "schi,0", "schil,0", "bobishere123456,0", "MrNowYouSeeMe,0", "JoshTheGoat,0", "sharkbait314,0", "mintytreasure,0", "overseas,0", "Nikkisweety,624.01", "KODY24,0", "Lazylion,0", "Lugia101101,1138.64", "jitske,0", "lazyguy64,0", "Adrast0s,0", "Okiboy,905.3", "clutz509,0", "thetastypoptart,361.68", "elcoyotl,0", "pear1,0", "racefreak265,0", "Slayer_Arianna,0", "Mission107,0", "Feldmanking,0", "jareth86,0", "jman0504,500", 
/* 13 */       "TheCarperdiem,0", "midnighttown,0", "GIRsPigOfDoom,0", "SenorMcBean,0", "sirkoffie,0", "van_awesome,1382.51", "CrazyElmoo123,0", "aspadedace,0", "Steveplayshorn,0", "Coolman045,0", "abroark,0", "noob__R3D8U11,0", "marcoaslak,0", "just00111,0", "Dastete,0", "Cloudfire36,0", "unluckilee,408.42", "franlk,75.67", "blobbosezhi2,0", "dunsparce,0", "Tharak,0", "Landononious,0", "soip,0", "Th3jinxster,0", "kam975,0", "BainsyLive,0", "KoolKatDom,0", "DEFCON000,796.23", "away8907,0", "like_a_bau5,0", "n1njapanda,0", "Raynomd,0", "Amorassa,0", "sam_da_man_2010,0", "Doopz479,0", "magic3465,0", "wastedkila,0", "Rekomaged,811.44", "andreicek,0", "PatHeist,2812.18", "bbeauclair,0", "Farblade,479.54", "Warchamp7,0", "chispire,2606.5", "Boncottled,125", "TerranUpTheNight,0", "PhilKenS,0", 
/* 14 */       "MrCow97,0", "node_n,0", "MarionetaAmanta,0", "TheZoranhero,0", "Andrewd183,2236.28", "Hungrybadger,0", "mruberepic,0", "DIRTY_DAN_941,0", "channeleaton,0", "Bostblack,0", "Omago,0", "ibot140,0", "woj75,3643", "MIMICx,1020", "gonnacreep467,38.27", "jcll,0", "trogdor121,0", "danyeissocool,0", "Farmer_Kru,0", "kcpbjp,0", "bowninja,0", "ClevengerSC,0", "MoreFish,0", "MadMan12417,0", "kbessey,0", "jwm22222,783.79", "sarenboja,0", "matt51096,0", "MechanicalYeti,0", "Alindral,0", "PlainJake,0", "Hundriel,206.72", "111arian111,0", "eth4444,0", "mcnsa,0", "kaldgeron,0", "poisonedzero21,0", "DaPhinoXX,0", "Myrto,4936", "woowoo11,0", "Fulker55,0", "MaxMurmaider92,0", "minimario657,0", "thethingisepic,1068.36", "Sharkd,904.32", "YogaNick,0", "Superburke,0", "ChocolateMooCow,0", 
/* 15 */       "0scarWildex,0", "Talleybear,0", "chokladio,4763.48", "madmcman,1043.38", "highgnosis,0", "Brendent,1069.24", "hawkiscool,0", "denver12345,0", "johnandjason,0", "Aleatoris,0", "g0dsl4yer,1881.93", "jkeyfiggy,0", "ryan5w4,0", "Dell_the_Engie,12033.74", "Sierra_G_117,1072.38", "BFloop,83.2", "kreigg,0", "Sparacus,18664.2", "snowypure,0", "Unprovidedxzx,0", "shansen,0", "manout102,0", "KingPaco,0", "m0nkeypill0w,0", "Borgaz,0", "blazyboy,0", "skyhawk2000,0", "augieCRAFT,0", "komora,0", "thegreatestfail,0", "volitus300,0", "star91143,9254.77", "Casper3641,482.96", "Kenix,0", "cyberman220,0", "Doc_Harris,0", "rishicobra,0", "mmine1,0", "detonatorman,152.47", "thekylander,0", "FadeJunior,0", "AttoSama,0", "_dragonmandan,0", "toursk8s,0", "Aoxodus,0", "michael122334,0", 
/* 16 */       "Pyrotek,0.01", "ILackPants,0", "plumbkru,0", "BigArnsky,0", "RyanX70,0", "mushdakush1,0", "PosieRosie,0", "RedlineBrass,0", "Justspazed,63851.98", "chazite,0", "rdk2001,0", "psyanara,0", "mike404,0", "phobos2deimos,0", "Pushsnik,0", "Shaunisme,0", "poezer333,630.39", "Zantores,0", "Zar14,730.89", "boonami,0", "Drakin728505,1.1", "huskysam,0", "richX3,0", "MikeMister23,0", "Subrava,0", "gullipud,0", "Iksvelhcram,0", "The_Kozo,74.36", "dpjohnson172,0", "Levantine,1000", "Briet,0", "ngrnlol,0", "TS_kids,0", "entezflare,0", "dhd_zeke_,0", "Lil_Simpsonian,0", "iceshot101,0", "pizzadudecook,0", "crayolaxboy,0", "samlynn79,0", "Ryan98391,0", "sugarmuffin,0", "the_narwhal_king,2.03", "Yrmalope,0", "Mr_trollington3,0", "kontroll,0", "Megleris,198403.42", "balluka,0", "Ruku178,0", 
/* 17 */       "lifegrasp,0", "bratmok12,0", "afdsf,0", "racr1425,0", "arandomu,0", "foveo,0", "re182,0", "Kason419,0", "fabianpulido,0", "jakulern,0", "Armadildo,0", "rylyxys,0", "bob313,0", "Rhinoman1993,0", "cheese852,0", "Stagen,2440.19", "avo,0", "TheTJ,0", "Twitch89,0", "nickpartlin,0", "delta214,0", "secretwep,0", "102438,36", "cubeman27,0", "dgamble,0", "spinvis2627,0", "jessmanboo22,0", "nathanleese,0", "dragondyl21,0", "totallynotcolin,0", "Necrowulf12,0", "tehsusenoh,0", "Charcoal_Pie,0", "iKeirNez,0", "PortalMaster1000,0", "fluffy916,0", "demonjoe,0", "littlevoecks,0", "RockMuncher,0", "Lenxaid,128.8", "jeffbuscus_33,640", "Kogan_of_orc,0", "clarkyboy898,0", "bobby16may,0", "miceiken,0", "Lottery,0", "conman7613,766.74", "Admin,-1350", "Ithurtsbad,11488.07", "PedalTone,0", 
/* 18 */       "Golden_Kumquat,0", "dads321,0", "Xmodrelic ,0", "Av0vo,0", "garylol,0", "TheDesiree,1297.92", "Stimpalicious,0", "kevv,0", "Lotterytest,0", "beavergus,0", "djlawromuz,0", "desertfox444,3329.94", "johngohn,0", "ves37,0", "rottenfartblast,251.68", "JoeDusk,0", "romanstennine,0", "SM0K3SCR33N,0", "lnxfan,0", "PickleMan,16405.79", "Slotmachine,0", "nmann91,0", "twister896,0", "lilliu97,0", "MicahJReid,0", "burgviking2012,0", "swiftcashew,0", "dantao,0", "wad828,0", "Swigelf,0", "cubemylife,777.97", "PeebleTheory,391.94", "mrfrenchman1,0", "alienalf,0", "Dr_ka_pow,0", "cootoopro,214.08", "ChrisloriousHD,0", "Unigames,0", "flyboy1000,0", "Wyattsb,0", "timz84,0", "LoganDewitt,0", "IAmRebel27,0", "mattzee54,0", "Danandbass,0", "td260,0", "Calypsa23,2629.52", "askem_,664.87", 
/* 19 */       "beastleyj,0", "Kswix07,0", "riegel2rebuild,12.9", "oldold,0", "MikeyRyuLBK,0", "pubpara,0", "Zorba12,0", "Hyper_Volt,0", "Demacius,7592.77", "19sban,0", "TehNoodle,286.45", "monreoshton,880.9", "monroeshton,0", "xKYLERxx,0", "Falsh,0", "bigtuna_b,24914.9", "Vaedorr,1013.25", "Evil_K9,51853.33", "thevanish,10228.73", "VerifedCupcake,5216.74", "DarkAngelsBlood1,16013.97", "Lemon_Monger,57.9", "Trolling1856,5928.86", "thurdog,769.02", "fullmetalcylon,641.18", "ipickedscout,3305.1", "ajvpot,2578.03", "Chosen15,3707.44", "Asheu,0.17", "JordiUp,0", "DarkVivi,407.78", "flamrod,3215.05", "BlazeDozer,1463.2", "rakiru,19296.21", "Nomadic_Penguin,1422.3", "Sola_Rex,4724.99", "lucky257,21.5", "R2D2,6.06", "roostergoblin,6300.98", "minecontemp8,10295.73", "ZirixZero,3221.18", 
/* 20 */       "pepsidude2015,81.07", "bob4786,14172.17", "Duer,1617.85", "Sekou,19.2", "Mr_funny_727,683.77", "mrbananabird,1785.11", "Lastsheep,2300.02", "NoosphereKerouac,5016.37", "JuliaRose430,153.78", "Mush12345,1647.79", "lizantie,0", "lizantio,50.29", "hideyoshi07,39.46", "DrunkenJawas,2008.43", "jmaboard,1315.41", "mostfunguy,106.23", "hiamsam,1533.97", "Hax_to_the_Max,32.06", "KidParkinson,1864.64", "arjunyg,348.21", "mythrical78,3494.45", "owenebs,1897.62", "Pyrovx,1664.82", "AloxGaming,2848.21", "Ansune,0", "simoni94,13.5", "Gallen101,7627.61", "thekurtwood,0", "Cakerawr,87.75", "rogerg1990,3.14", "Bmxmatt,847.02", "abeautifulmine,788.23", "Chockrit,2133.24", "MikeMFrank,0", "hockeybob13,2858.55", "n30nex,0", "Sinkip,6912", "XxAonoTsukune,0", "cryst4r,2876.09", 
/* 21 */       "killlergerbil,153.54", "kolika,2900.8", "JimmyD1,54.2", "jccm122,7422.09", "conorwatches,1175.35", "Harry225,0.92", "Noahsmiley,0", "maboughey,2229.07", "Kwinno,0", "Player,0", "SlicedLizard,0", "krepta42,3046.25", "Agents9526,700.35", "greypo,168.54", "Spl0ding,24.23", "lolrotflmao113,1548.36", "Sparky_Aftermath,1808.65", "Antigrate,23", "zswaggersmurf,816.12", "rihawk10,86.71", "GravyKitty,0", "Taogaha,466.61", "collin159,2.66", "Silent6,0", "daesoph,1921.13", "QuidProQuoth,1195.56", "Basicthoughts,0", "Xerophye,0", "Fronds73,2051.02", "K1dn3ypunch,0", "nint22,400.28", "Kaladin_bridge4,1361.34", "hazelnut20,0", "Halt12,74.8", "netjunky,1344.48", "Verditure,0", "thr3ddy,3032.9", "rawdog66,4852.43", "rawdog6,0", "ACRYLIC0368,81.7", "Howl_Kohaku,412.51", "Ulcano,1987.49", 
/* 22 */       "theunfatguy,536.88", "zciryle,1.14", "capzo,395.96", "jasonmog,983.39", "PsYC0t1k,11227.86", "Anrahgra,1222.95", "TemperD_Xaon,908.97", "Mike5357,0", "xopher55,134.78", "invadersen,26.88", "big_board,0", "summerchick,45.78", "MithrasInvictus,0", "ChapelGentry,1300.12", "JoeOddy,0", "Flashplayerman,30.66", "mellinox,0", "Stanhopes,22.53", "LewisGravy,0", "Sendel,0", "Demonicblade,0", "CertifedCupcake,59.36", "SirenFails,0", "KosteonLink,180.77", "GreatRevival,125.58", "RogueHaven,620.01", "rabi,0", "blacknbluedragon,14494.06", "Nixonat0r,213.6", "DasHuhn,0", "omnomnom18,0", "C00LBR0,0", "RedAgito,0" };
/*    */ 
/* 24 */     String[] items = { "1,26303", "2,6735", "3,27801", "4,53740", "5,0", "6,2933", "7,0", "12,3438", "13,16984", "14,0", "15,0", "16,0", "17,689", "18,0", "19,0", "20,4110", "21,0", "22,5", "23,0", "24,1289", "25,0", "27,0", "28,0", "29,0", "30,0", "31,0", "32,0", "33,0", "35,585", "37,770", "38,491", "39,514", "40,1436", "41,0", "42,0", "44,0", "45,0", "46,0", "47,0", "48,5034", "49,4174", "50,0", "53,0", "54,0", "56,0", "57,0", "58,0", "61,0", "65,0", "66,90", "67,0", "69,0", "70,0", "72,0", "73,0", "76,0", "77,0", "79,0", "80,1258", "81,3744", "82,0", "84,3", "85,0", "86,1828", "87,38913", "88,2098", "89,783", "91,0", "354,0", "96,0", "98,0", "101,0", "102,0", "103,0", "106,0", "107,0", "108,0", "109,0", "110,2604", "111,0", "112,3062", "113,0", "114,0", "116,0", "119,0", "121,0", 
/* 25 */       "123,0", "126,0", "128,0", "129,0", "130,0", "131,0", "133,0", "134,0", "135,0", "136,0", "256,0", "257,0", "258,0", "260,244", "261,0", "262,2721", "263,7076", "264,1286", "265,1679", "266,0", "267,0", "268,0", "269,0", "270,0", "271,0", "272,0", "273,0", "274,0", "275,0", "276,0", "277,0", "278,0", "279,0", "280,0", "281,0", "282,0", "283,0", "284,0", "285,0", "286,0", "287,7039", "288,4715", "289,6", "290,0", "291,0", "292,0", "293,0", "294,3", "295,18322", "296,6663", "297,3151", "298,0", "299,0", "300,0", "301,0", "302,0", "303,0", "304,0", "305,0", "306,0", "307,0", "308,0", "309,0", "310,0", "311,0", "312,0", "313,0", "314,0", "315,0", "316,0", "317,0", "318,3298", "319,147", "320,0", "321,0", "322,0", "323,0", "324,0", "325,0", "326,0", "327,0", "328,0", "329,0", 
/* 26 */       "330,0", "331,43238", "332,0", "333,0", "334,0", "335,0", "335,0", "336,0", "337,112", "338,14774", "339,0", "340,0", "341,4796", "342,0", "343,0", "344,4833", "345,0", "346,0", "347,0", "348,0", "349,4", "350,0", "351,0", "353,0", "356,0", "357,0", "358,0", "259,0", "359,0", "360,12031", "361,0", "362,0", "363,2944", "364,0", "365,120", "366,0", "367,22214", "368,23056", "369,5827", "370,0", "371,0", "372,6231", "373,0", "373,0", "373,0", "373,0", "373,0", "374,0", "375,1314", "376,0", "377,0", "378,0", "379,0", "380,0", "381,0", "382,0", "384,0", "385,0", "386,0", "388,0", "2256,0", "2257,0", "2258,0", "2259,0", "2260,0", "2261,0", "2262,0", "2263,0", "2264,0", "2265,0", "2266,0", "383t120,0", "383t52,0", "383t54,0", "383t55,0", "383t56,0", "383t57,0", "383t58,0", 
/* 27 */       "383t59,0", "383t60,0", "383t61,0", "383t62,0", "383t90,0", "383t91,0", "383t92,0", "383t93,0", "383t94,0", "383t95,0", "383t96,0", "383t98,0", "126t1,0", "126t2,0", "126t3,0", "17t1,0", "17t2,105", "17t3,548", "18t1,806", "18t2,1164", "18t3,1092", "24t1,0", "24t2,0", "263t1,0", "322t1,0", "351t1,0", "351t10,0", "351t11,0", "351t12,0", "351t13,0", "351t14,0", "351t15,0", "351t2,0", "351t3,12462", "351t4,2934", "351t5,0", "351t6,0", "351t7,0", "351t8,0", "351t9,0", "373t16385,0", "373t16386,0", "373t16387,0", "373t16388,0", "373t16392,0", "373t16393,0", "373t16394,0", "373t16471,0", "373t16418,0", "373t16420,0", "373t16425,0", "373t16449,0", "373t16450,0", "373t16451,0", "373t16452,0", "373t16456,0", "373t16457,0", "373t16458,0", "373t8193,0", "373t8194,0", "373t8195,0", 
/* 28 */       "373t8196,0", "373t8197,0", "373t8200,0", "373t8201,0", "373t8202,0", "373t8204,0", "373t8225,0", "373t8226,0", "373t8228,0", "373t8229,0", "373t8233,0", "373t8236,0", "373t8257,0", "373t8258,0", "373t8259,0", "373t8260,0", "373t8264,0", "373t8265,0", "373t8266,0", "383t50,0", "383t51,0", "44t1,0", "44t2,0", "44t4,0", "44t5,0", "5t1,0", "5t2,0", "5t3,0", "6t1,123", "6t2,295", "6t3,0", "97t0,0", "97t1,0", "97t2,0", "98t1,0", "98t2,0", "98t3,0", "35t1,729", "35t10,958", "35t11,1346", "35t12,956", "35t13,917", "35t14,733", "35t15,712", "35t2,776", "35t3,801", "35t4,942", "35t5,152", "35t6,1688", "35t7,357", "35t8,632", "35t9,1306", "352,9022" };
/*    */ 
/* 33 */     Po8.stockMap = new HashMap();
/* 34 */     for (String item : items) {
/* 35 */       System.out.println(item);
/* 36 */       String[] split = item.split(",");
/* 37 */       Po8.stockMap.put(split[0], Integer.valueOf(Integer.parseInt(split[1])));
/*    */     }
/* 39 */     System.out.println("---");
/* 40 */     Po8.playerMap = new HashMap();
/* 41 */     for (String acc : accounts) {
/* 42 */       System.out.println(acc);
/* 43 */       String[] split = acc.split(",");
/* 44 */       Po8Player p = new Po8Player();
/* 45 */       System.out.println(split[1]);
/* 46 */       p.balance = Double.parseDouble(split[1]);
/* 47 */       Po8.playerMap.put(split[0], p);
/*    */     }
/*    */     try
/*    */     {
/* 51 */       SLAPI slapi = new SLAPI();
/* 52 */       slapi.save(Po8.stockMap, "stockmap.bin");
/* 53 */       slapi.save(Po8.playerMap, "playermap.bin");
/*    */     } catch (Exception e) {
/* 55 */       e.printStackTrace();
/*    */     }
/*    */   }
/*    */ }

/* Location:           /home/casper/craftbukkit/plugins/MCNSAPo8.jar
 * Qualified Name:     Migrate
 * JD-Core Version:    0.6.2
 */