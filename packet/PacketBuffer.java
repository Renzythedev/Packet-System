package tcp.server.packet;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.SwappedByteBuf;

import javax.annotation.Nonnull;
import java.nio.charset.StandardCharsets;

public class PacketBuffer extends SwappedByteBuf {

    public PacketBuffer(ByteBuf buf) {
        super(buf);
    }

    public void writeString(String s) {
        if (s == null)
            s = "null";
        writeInt(s.getBytes(StandardCharsets.UTF_8).length);
        writeBytes(s.getBytes(StandardCharsets.UTF_8));
    }

    public String readString() {
        byte[] bytes = new byte[readInt()];

        for (int i = 0; i<bytes.length; i++)
            bytes[i] = readByte();


        return new String(bytes,StandardCharsets.UTF_8);
    }

    public void writeContent(byte[] content) {
        if (content == null)
            return;
        writeInt(content.length);
        writeBytes(content);
    }

    public byte[] readContent() {
        byte[] content = new byte[readInt()];

        for (int i = 0; i < content.length; i++)
            content[i] = readByte();

        return content;
    }
}
