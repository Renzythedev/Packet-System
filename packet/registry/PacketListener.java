package tcp.server.packet.registry;

import tcp.server.packet.Packet;

@FunctionalInterface
public interface PacketListener<T extends Packet> {

    void onReceivePacket(T packet);
}
