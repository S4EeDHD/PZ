package ir.s4eed_hd.pz.managers;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class PacketUtils {
    public static void writeVarInt(DataOutputStream out, int value) throws IOException {
        do {
            if ((value & -128) == 0) {
                out.writeByte(value);
                return;
            }
            out.writeByte(value & 127 | 128);
            value >>>= 7;
        } while (true);
    }

    public static void writeString(DataOutputStream out, String value) throws IOException {
        byte[] data = value.getBytes(StandardCharsets.UTF_8);
        byte[] after = new byte[data.length];
        PacketUtils.writeVarInt(out, data.length);
        out.write(data, 0, data.length);
    }

    public static void writeStringC(DataOutputStream out, String value) throws IOException {
        byte[] data = value.getBytes(StandardCharsets.UTF_8);
        byte[] after = new byte[data.length];
        int i = 0;
        do {
            if (i >= data.length) {
                PacketUtils.writeVarInt(out, after.length);
                out.write(after, 0, after.length);
                return;
            }
            after[i] = data[i];
            ++i;
        } while (true);
    }
    
    public static byte[] createLogin(String username) {
        try {
            ByteArrayOutputStream buffer = new ByteArrayOutputStream();
            DataOutputStream login = new DataOutputStream(buffer);
            //login.write(username.length() + 2);
            login.writeByte(0);
            login.write(username.length());
            login.write(username.getBytes());
           //PacketUtils.writeString(login, username, StandardCharsets.UTF_8);
            return buffer.toByteArray();
        }
        catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
    
    public static byte[] createPZFuckerLogin(String str) throws IOException{
    	ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        DataOutputStream out = new DataOutputStream(bytes);
        out.write(0);
        writeStringPZ(out, str);
        return bytes.toByteArray();
    }
    
    public static void writeString(DataOutputStream out, String string, Charset charset) {
        try {
            byte[] bytes = string.getBytes();
            PacketUtils.writeVarInt(out, bytes.length);
            out.write(bytes);
            return;
        }
        catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
    
    public static void sendPacket(byte[] packet, DataOutputStream out) {
        PacketUtils.writePacket(out, packet);
    }
    
    public static void writePacket(DataOutputStream out, byte[] packet) {
        try {
            PacketUtils.writeVarInt(out, packet.length);
            out.write(packet);
            return;
        }
        catch (IOException ex) {
        	
        }
    }
    
    public static void writeVarInt(ByteArrayOutputStream out, int paramInt) {
        do {
            if ((paramInt & -128) == 0) {
                out.write(paramInt);
                return;
            }
            out.write(paramInt & 127 | 128);
            paramInt >>>= 7;
        } while (true);
    }
    
    public static int readVarInt(DataInputStream in) throws IOException {
        byte k;
        int i = 0;
        int j = 0;
        do {
            k = in.readByte();
            i |= (k & 127) << j++ * 7;
            if (j <= 5) continue;
            throw new RuntimeException("VarInt too big");
        } while ((k & 128) == 128);
        return i;
    }

    public static String readString(DataInputStream in) throws IOException {
        int len = PacketUtils.readVarInt(in);
        byte[] data = new byte[len];
        in.readFully(data);
        return new String(data, 0, len, StandardCharsets.UTF_8);
    }

    public static byte[] readByteArray(DataInputStream in) throws IOException {
        int len = PacketUtils.readVarInt(in);
        byte[] data = new byte[len];
        in.readFully(data);
        return data;
    }

    public static void writeByteArray(DataOutputStream out, byte[] data) throws IOException {
        PacketUtils.writeVarInt(out, data.length);
        out.write(data, 0, data.length);
    }

    public static void writeHandshakePacket(DataOutputStream out, String ip, int port, int protocol, int state) throws IOException {
        PacketUtils.writeVarInt(out, 0);
        PacketUtils.writeVarInt(out, protocol);
        PacketUtils.writeString(out, ip);
        out.writeShort(port);
        PacketUtils.writeVarInt(out, state);
    }

    public static void writeQueryRequestPacket(DataOutputStream out) throws IOException {
        PacketUtils.writeVarInt(out, 0);
    }

    public static void writePingPacket(DataOutputStream out, long clientTime) throws IOException {
        PacketUtils.writeVarInt(out, 1);
        out.writeLong(clientTime);
    }

    public static void writePacket(byte[] packetData, DataOutputStream out) throws IOException {
        PacketUtils.writeVarInt(out, packetData.length);
        out.write(packetData);
    }

    public static void writePacketSomeTimes(byte[] packetData, DataOutputStream out, int times) throws IOException {
        ByteArrayOutputStream LoginOutPutStream = null;
        LoginOutPutStream = new ByteArrayOutputStream();
        DataOutputStream login = new DataOutputStream(LoginOutPutStream);
        PacketUtils.writeVarInt(login, packetData.length);
        login.write(packetData);
        byte[] bytes = LoginOutPutStream.toByteArray();
        int i = 0;
        while (i < times) {
            out.write(bytes);
            ++i;
        }
    }

    public static byte[] createLoginPacket() throws IOException {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        DataOutputStream out = new DataOutputStream(bytes);
        PacketUtils.writeVarInt(out, 0);
        PacketUtils.writeString(out, (Math.random() + "").replaceAll("\\.", ""));
        byte[] data = bytes.toByteArray();
        bytes.close();
        return data;
    }

    public static byte[] createPingPacket() throws IOException {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        DataOutputStream out = new DataOutputStream(bytes);
        PacketUtils.writeVarInt(out, 1);
        PacketUtils.writeString(out, (Math.random() + "").replaceAll("\\.", ""));
        byte[] data = bytes.toByteArray();
        bytes.close();
        return data;
    }
    
    public static byte[] createStatusPacket(String ip, int port, int protocol) throws IOException {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        DataOutputStream out = new DataOutputStream(bytes);
        PacketUtils.writeHandshakePacket(out, ip, port, protocol, 1);
        byte[] data = bytes.toByteArray();
        bytes.close();
        return data;
    }

    public static byte[] createHandshakePacket(String ip, int port, int protocol) throws IOException {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        DataOutputStream out = new DataOutputStream(bytes);
        PacketUtils.writeHandshakePacket(out, ip, port, protocol, 2);
        byte[] data = bytes.toByteArray();
        bytes.close();
        return data;
    }

    public static String readServerDataPacket(DataInputStream in) throws IOException {
        byte id = in.readByte();
        if (id == 0) return PacketUtils.readString(in);
        return null;
    }
    
    public static void writeStringPZ(DataOutputStream out, String value) throws IOException {
        byte[] data = value.getBytes(StandardCharsets.UTF_8);
        writeVarInt(out, data.length);
        out.write(data, 0, data.length);
     }

    
    public static long readPongPacket(DataInputStream in) throws IOException {
        byte id = in.readByte();
        if (id == 1) return in.readLong();
        return -1L;
    }
}

