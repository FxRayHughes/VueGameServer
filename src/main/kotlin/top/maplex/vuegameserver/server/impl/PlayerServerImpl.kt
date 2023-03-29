package top.maplex.vuegameserver.server.impl

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import top.maplex.vuegameserver.dao.PlayerDao
import top.maplex.vuegameserver.entity.Player
import top.maplex.vuegameserver.server.PlayerServer

@Service("playerService")
class PlayerServerImpl : PlayerServer {

    @Autowired
    private lateinit var playerDao: PlayerDao

    override fun update(player: Player): Int {
        return this.playerDao.update(player)
    }

    override fun add(player: Player): Int {
        return this.playerDao.add(player)
    }

    override fun delete(id: Long): Int {
        return this.playerDao.delete(id)
    }

    override fun findPlayer(id: Long): Player? {
        return this.playerDao.findPlayer(id)
    }

    override fun findPlayerList(): List<Player> {
        return this.playerDao.findPlayerList()
    }
}