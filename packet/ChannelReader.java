package tcp.server.packet;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class ChannelReader extends SimpleChannelInboundHandler<Packet> {

    private final PacketHandler packetHandler;

    public ChannelReader(PacketHandler packetHandler){
        this.packetHandler = packetHandler;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channel, Packet packet) throws Exception {
        packetHandler.handle(packet);
    }

    public PacketHandler getPacketHandler() {
        return packetHandler;
    }
}
