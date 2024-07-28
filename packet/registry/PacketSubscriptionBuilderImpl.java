package tcp.server.packet.registry;

import tcp.server.packet.Packet;
import tcp.server.packet.Packets;

import javax.annotation.Nonnull;
import java.util.List;

class PacketSubscriptionBuilderImpl<T extends Packet> implements PacketSubscriptionBuilder<T> {

    private final Class<T> packetClass;
    private final int id;
    private PacketListener<T> listener;

    PacketSubscriptionBuilderImpl(Class<T> packetClass, int id) {
        this.packetClass = packetClass;
        this.id = id;
    }

    @Nonnull
    @Override
    public PacketSubscriptionBuilder<T> listener(@Nonnull PacketListener<T> listener) {
        this.listener = listener;
        return this;
    }

    @Nonnull
    @Override
    public PacketHandlerList getHandlerList() {
        return Packets.DEFAULT_HANDLER_LIST;
    }

    @Override
    public void register() {
        getHandlerList().register(id,packetClass,listener);
    }

    public int getId() {
        return id;
    }

    public Class<T> getPacketClass() {
        return packetClass;
    }
}
