package ru.unicorecms.unicoreconnect.common.events

import ru.unicorecms.unicoreconnect.common.types.UserDonate

class SocketEvent {
    class BUY_DONATE(val payload: UserDonate)
}