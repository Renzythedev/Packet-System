package tcp.server.packet.registry;

import tcp.server.packet.PacketException;
import tcp.server.packet.Packet;

import java.lang.reflect.Constructor;
import java.util.Arrays;
import java.util.List;

public class RegisteredPacket {

    private final int id;
    private final Class<? extends Packet> packetClass;
    private final PacketListener<?> listener;

    public RegisteredPacket(int id, Class<? extends Packet> packetClass, PacketListener<? extends Packet> listener) {
        this.id = id;
        List<Constructor<?>> emptyConstructor = Arrays.stream(packetClass.getConstructors()).filter(c -> c.getParameterCount()==0).toList();
        if (emptyConstructor.isEmpty())
            throw new PacketException("Packet missing no-args-constructor");
        this.packetClass = packetClass;
        this.listener = listener;
    }

    public Class<? extends Packet> getPacketClass() {
        return packetClass;
    }

    public PacketListener<? extends Packet> getListener() {
        return listener;
    }

    public int getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null)
            return false;
        return o instanceof RegisteredPacket && this.id == ((RegisteredPacket)o).id;
    }

    @Override
    public int hashCode() {
        return 17 * Integer.hashCode(id);
    }
}
