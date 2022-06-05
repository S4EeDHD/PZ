package ir.s4eed_hd.pz.managers;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonWriter;

import ir.s4eed_hd.pz.ProxyType;

public class config {
	
	private static config c;
	private config(){}
	public static config getConfig(){
		if(c== null)
			c = new config();
		return c;
	}
	
	public void dropConfig(){
		try {
		      File myObj = new File("config.json");
		      if (myObj.createNewFile()) {
		    	  myObj.createNewFile();
		    	  FileWriter file = new FileWriter("config.json");
		    	  JsonWriter writer = new JsonWriter(file);
		    	  writer.beginObject();
		    	  writer.setIndent(" ");
		    	  writer.name("IP").value("127.0.0.1");
		    	  writer.name("Port").value(25565);
		    	  writer.name("Delay").value(1);
		    	  writer.name("Threads").value(75);
		    	  writer.name("Loop").value(5);
		    	  writer.name("Protocol").value(340);
		    	  writer.name("NickSize").value(7);
		    	  writer.name("ProxyType").value(ProxyType.DIRECT.toString());
		    	  writer.name("ProxyFile").value("proxy.txt");
		    	  writer.name("BotPrefix").value("BOT_");
		    	  writer.name("BotSuffix").value("_lol");
		    	  writer.name("Method").value("join");
		    	  writer.endObject();
		    	  System.out.println("Config Has Been Droped ! ");
		    	  System.out.println("Please Config The Attacker and try again.");
		    	  file.close();
		    	  System.exit(1);
		      } else {
		        System.out.println(" Loading ...");
		      }
		    } catch (IOException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }
	}
	
	public Object getValue(String Setting){
		JsonParser parser = new JsonParser();
		try {
	         Object obj = parser.parse(new FileReader("config.json"));
	         JsonObject jsonObject = (JsonObject)obj;
	         JsonElement IP = jsonObject.get("IP");
	         JsonElement Port = jsonObject.get("Port");
	         JsonElement Threads = jsonObject.get("Threads");
	         JsonElement Loop = jsonObject.get("Loop");
	         JsonElement Delay = jsonObject.get("Delay");
	         JsonElement Protocol = jsonObject.get("Protocol");
	         JsonElement NickSize = jsonObject.get("NickSize");
	         JsonElement ProxyFile = jsonObject.get("ProxyFile");
	         JsonElement Proxytype = jsonObject.get("ProxyType");
	         JsonElement BotPrefix = jsonObject.get("BotPrefix");
	         JsonElement BotSuffix = jsonObject.get("BotSuffix");
	         JsonElement Method = jsonObject.get("Method");
	         if ( Setting.equals("IP") )
	        	 return IP.getAsString();
	         if ( Setting.equals("Port") )
	        	 return Port.getAsNumber().intValue();
	         if ( Setting.equals("Threads") )
	        	 return Threads.getAsNumber().intValue();
	         if ( Setting.equals("Loop") )
	        	 return Loop.getAsNumber().intValue();
	         if ( Setting.equals("Delay") )
	        	 return Delay.getAsNumber().intValue();
	         if ( Setting.equals("Protocol") )
	        	 return Protocol.getAsNumber().intValue();
	         if ( Setting.equals("NickSize") )
	        	 return NickSize.getAsNumber().intValue();
	         if ( Setting.equals("ProxyFile") )
	        	 return ProxyFile.getAsString();
	         if ( Setting.equals("ProxyType") ){
	        	 return ProxyType.valueOf(Proxytype.getAsString());
	         }
	         if ( Setting.equals("BotPrefix") )
	        	 return BotPrefix.getAsString();
	         if ( Setting.equals("BotSuffix") )
	        	 return BotSuffix.getAsString();
	         if ( Setting.equals("Method") )
	        	 return Method.getAsString();
	      } catch(Exception e) {
	         e.printStackTrace();
	      }
		return null;
	}
	
}
