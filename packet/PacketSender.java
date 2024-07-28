package tcp.server.packet;

import javax.annotation.Nonnull;
import java.nio.channels.Channel;

@FunctionalInterface
public interface PacketSender<T> {

    void sendPacket(@Nonnull T to,@Nonnull Packet packet);
}
