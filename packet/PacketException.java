package tcp.server.packet;

public class PacketException extends RuntimeException{
    public PacketException(Throwable cause) {
        super(cause);
    }

    public PacketException(String message) {
        super(message);
    }

    public PacketException(String message, Throwable cause) {
        super(message, cause);
    }
}
