# Packet-System

With this packet system, you can send and receive your packets over your TCP network. You can maintain a listener for each packet to simplify your operations.

**To register a packet:**

```
Packets.subscribe(1, TestPacket.class).listener((packet) ->{}).register();
```

**To send a packet:**
```
Packets.sendPacket(channel,packet);
```

**If you want to write your own sending algorithms when sending packets:**
```
Packets.sendPacket(channel,packet, (c,packet) -> {//your algorithm });
```
