package tcp.server.packet.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import tcp.server.packet.Packet;
import tcp.server.packet.PacketBuffer;
import tcp.server.packet.Packets;

public class PacketEncoder extends MessageToByteEncoder<Packet> {
    @Override
    protected void encode(ChannelHandlerContext channel, Packet packet, ByteBuf byteBuf) throws Exception {
        PacketBuffer packetBuffer = new PacketBuffer(byteBuf);
        int id = Packets.DEFAULT_HANDLER_LIST.getPacketID(packet.getClass());
        packetBuffer.writeInt(id);
        packet.write(packetBuffer);
    }
}
