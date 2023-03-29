package top.maplex.vuegameserver.dao.impl

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jdbc.core.BeanPropertyRowMapper
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Repository
import top.maplex.vuegameserver.dao.PlayerDao
import top.maplex.vuegameserver.entity.Player

@Repository
open class PlayerDaoImpl : PlayerDao {

    @Autowired
    private lateinit var jdbcTemplate: JdbcTemplate

    override fun add(player: Player): Int {
        return jdbcTemplate.update(
            "insert into user(name, year) values(?, ?)",
            player.name, player.year
        )
    }

    override fun update(player: Player): Int {
        return jdbcTemplate.update(
            "update user set name = ?, year = ? where id = ?",
            *arrayOf(player.name, player.year, player.id)
        )
    }

    override fun delete(id: Long): Int {
        return jdbcTemplate.update("delete from user where id = ?", id)
    }

    override fun findPlayer(id: Long): Player? {
        val list = jdbcTemplate.query(
            "select * from user where id = ?",
            arrayOf<Any>(id),
            BeanPropertyRowMapper(Player::class.java)
        )
        return list.get(0);
    }

    override fun findPlayerList(): List<Player> {
        return jdbcTemplate.query("select * from user", arrayOf(), BeanPropertyRowMapper(Player::class.java))
    }
}