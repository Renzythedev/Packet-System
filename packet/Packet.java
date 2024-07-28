package tcp.server.packet;

public abstract class Packet {

    public Packet() {}

    public abstract void write(PacketBuffer packetBuffer);
    public abstract void read(PacketBuffer packetBuffer);
    public abstract void handle();
}
