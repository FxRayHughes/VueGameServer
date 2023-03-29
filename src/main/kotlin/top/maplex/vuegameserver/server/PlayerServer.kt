package top.maplex.vuegameserver.server

import top.maplex.vuegameserver.entity.Player

interface PlayerServer {

    fun add(player: Player): Int
    fun update(player: Player): Int
    fun delete(id: Long): Int
    fun findPlayer(id: Long): Player?
    fun findPlayerList(): List<Player>
    
}