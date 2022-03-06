package uno.unicore.unicoreconnect.common.events

class SocketEvent {
    class CONNECT(val message: String)
    class RECONNECT(val message: String)
    class CLOSE(val message: String)
    class ERROR(val message: String)
}