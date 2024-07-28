package tcp.server.packet;

import io.netty.channel.Channel;
import tcp.server.packet.registry.PacketHandlerList;
import tcp.server.packet.registry.PacketListener;
import tcp.server.packet.registry.PacketSubscriptionBuilder;

import javax.annotation.Nonnull;

public final class Packets {

    private Packets(){}

    public static final PacketHandlerList DEFAULT_HANDLER_LIST = PacketHandlerList.newHandlerList();
    public static final PacketSender<Channel> DEFAULT_PACKET_SENDER = (to, packet) -> {
        to.writeAndFlush(packet);
    };

    public static <T extends Packet>  PacketSubscriptionBuilder<T> subscribe(int id, @Nonnull Class<T> packetClass) {
        return PacketSubscriptionBuilder.newBuilder(id,packetClass);
    }

    @SuppressWarnings("unchecked")
    public static <T extends Packet> void callPacket(@Nonnull Class<T> packetClass, @Nonnull Packet packet) {
        if (!packet.getClass().isAssignableFrom(packetClass))
            throw new PacketException(packetClass.getSimpleName() + " and "+packet.getClass().getSimpleName()+" are not the same packet.");
        PacketListener<T> packetListener = DEFAULT_HANDLER_LIST.getListener(packetClass);
        packetListener.onReceivePacket((T) packet);
    }

    public static <T extends Packet> void callPacketAsync(@Nonnull Class<T> packetClass, @Nonnull Packet packet) {
        new Thread(() -> callPacket(packetClass,packet)).start();
    }

    public static void sendPacket(Channel to,Packet packet) {
        DEFAULT_PACKET_SENDER.sendPacket(to,packet);
    }

    public static <T> void sendPacket(T to,Packet packet, PacketSender<T> sender) {
        sender.sendPacket(to,packet);
    }

}
