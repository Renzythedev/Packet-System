package tcp.server.packet.packets;

import tcp.server.packet.Packet;
import tcp.server.packet.PacketBuffer;

public class TestPacket extends Packet {

    private String message = "merhaba";

    @Override
    public void write(PacketBuffer packetBuffer) {
    }

    @Override
    public void read(PacketBuffer packetBuffer) {

    }

    @Override
    public void handle() {
        message = "sea";
    }

    public String getMessage() {
        return message;
    }
}
