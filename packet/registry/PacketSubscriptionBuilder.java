package tcp.server.packet.registry;

import tcp.server.packet.Packet;

import javax.annotation.Nonnull;

public interface PacketSubscriptionBuilder<T extends Packet> {

    static <T extends Packet> PacketSubscriptionBuilderImpl<T> newBuilder(int id,Class<T> packetClass) {
        return new PacketSubscriptionBuilderImpl<T>(packetClass,id);
    }

    @Nonnull
    PacketSubscriptionBuilder<T> listener(@Nonnull PacketListener<T> listener);

    @Nonnull
    PacketHandlerList getHandlerList();

    void register();



}
