package tcp.server.packet;

public interface PacketHandler {

    PacketHandler DEFAULT = (packet) -> {
        packet.handle();
        Packets.callPacket(packet.getClass(),packet);
    };

    void handle(Packet packet);
}
