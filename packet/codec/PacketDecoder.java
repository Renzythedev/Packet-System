package tcp.server.packet.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.handler.codec.DecoderException;
import tcp.server.packet.Packet;
import tcp.server.packet.PacketBuffer;
import tcp.server.packet.Packets;

import java.util.List;

public class PacketDecoder extends ByteToMessageDecoder {
    @Override
    protected void decode(ChannelHandlerContext channel, ByteBuf byteBuf, List<Object> list) throws Exception {
        PacketBuffer packetBuffer = new PacketBuffer(byteBuf);

        if (packetBuffer.readableBytes()<1)
            return;

        int packetID = packetBuffer.readInt();
        Packet packet = Packets.DEFAULT_HANDLER_LIST.getPacket(packetID);
        packet.read(packetBuffer);

        if (packetBuffer.readableBytes() > 0)
            throw new DecoderException("Packet  (" + packet.getClass().getSimpleName() + ") was larger than expected, found " + packetBuffer.readableBytes() + " bytes extra whilst reading packet " + packet);

        list.add(packet);
    }
}
