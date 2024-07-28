package tcp.server.packet.registry;

import tcp.server.packet.PacketException;
import tcp.server.packet.Packet;

import javax.annotation.Nonnull;
import java.util.*;

class PacketHandlerListImpl implements PacketHandlerList{

    private final Set<RegisteredPacket> packets;

    PacketHandlerListImpl() {
        packets = new HashSet<>();
    }

    @Override
    public <T extends Packet> void register(int id, @Nonnull Class<T> packet, @Nonnull PacketListener<T> listener) {
        packets.add(new RegisteredPacket(id,packet,listener));
    }

    @Override
    public <T extends Packet> void unregister(@Nonnull Class<T> packet) {
        Optional<RegisteredPacket> registeredPacket = packets.stream().filter(p -> p.getPacketClass().isAssignableFrom(packet)).findFirst();
        if (registeredPacket.isEmpty())
            throw new PacketException(packet.getSimpleName() + " packet not found.");
        packets.remove(registeredPacket.get());
    }

    @Override
    public void unregister(int id) {
        Optional<RegisteredPacket> registeredPacket = packets.stream().filter(p -> p.getId() == id).findFirst();
        if (registeredPacket.isEmpty())
            throw new PacketException("Packet with ID "+id+" not found");
        packets.remove(registeredPacket.get());
    }

    @Override
    public Packet getPacket(int id) {
        Optional<RegisteredPacket> registeredPacket = packets.stream().filter(p -> p.getId() == id).findFirst();
        if (registeredPacket.isEmpty())
            throw new PacketException("Packet with ID "+id+" not found");
        Class<? extends Packet> packetClass = registeredPacket.get().getPacketClass();
        try {
            return packetClass.getConstructor().newInstance();
        } catch (Exception e) {
            throw new PacketException(e);
        }
    }

    @Override
    public <T extends Packet> Packet getPacket(@Nonnull Class<T> packetClass) {
        Optional<RegisteredPacket> registeredPacket = packets.stream().filter(p -> p.getPacketClass().isAssignableFrom(packetClass)).findFirst();
        if (registeredPacket.isEmpty())
            throw new PacketException(packetClass.getSimpleName() + " packet not found.");
        try {
            return packetClass.getConstructor().newInstance();
        } catch (Exception e) {
            throw new PacketException(e);
        }
    }

    @Override
    public int getPacketID(@Nonnull Class<? extends Packet> packetClass) {
        Optional<RegisteredPacket> registeredPacket = packets.stream().filter(p -> p.getPacketClass().isAssignableFrom(packetClass)).findFirst();
        if (registeredPacket.isEmpty())
            throw new PacketException(packetClass.getSimpleName() + " packet not found.");
        return registeredPacket.get().getId();
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T extends Packet> PacketListener<T> getListener(Class<T> packetClass) {
        Optional<RegisteredPacket> registeredPacket = packets.stream().filter(p -> p.getPacketClass().isAssignableFrom(packetClass)).findFirst();
        if (registeredPacket.isEmpty())
            throw new PacketException(packetClass.getSimpleName() + " packet not found.");
        return (PacketListener<T>) registeredPacket.get().getListener();
    }

    @Override
    public Set<RegisteredPacket> getPackets() {
        return packets;
    }
}
