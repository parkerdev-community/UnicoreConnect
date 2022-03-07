package uno.unicore.unicoreconnect.common.events

import uno.unicore.unicoreconnect.common.types.UserDonate

class SocketEvent {
    class CONNECT(val message: String)
    class RECONNECT(val message: String)
    class CLOSE(val message: String)
    class ERROR(val message: String)
    class BUY_DONATE(val payload: UserDonate)
}