package tcp.server.packet.registry;

import tcp.server.packet.Packet;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.Set;

public interface PacketHandlerList {

    static PacketHandlerList newHandlerList() {
        return new PacketHandlerListImpl();
    }

    <T extends Packet> void register(int id,@Nonnull  Class<T> packet, @Nonnull PacketListener<T> listener);
    <T extends Packet> void unregister(@Nonnull  Class<T> packet);
    void unregister(int id);
    Packet getPacket(int id);
    <T extends Packet> Packet getPacket(@Nonnull  Class<T> packetClass);
    int getPacketID(@Nonnull Class<? extends Packet> packetClass);
    <T extends Packet> PacketListener<T> getListener(Class<T> packetClass);

    Set<RegisteredPacket> getPackets();
}
