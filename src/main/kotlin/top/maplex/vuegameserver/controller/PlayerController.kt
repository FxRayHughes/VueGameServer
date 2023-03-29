package top.maplex.vuegameserver.controller

import com.alibaba.fastjson2.JSONObject
import jakarta.servlet.http.HttpServletRequest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import top.maplex.vuegameserver.entity.Player
import top.maplex.vuegameserver.server.PlayerServer

@CrossOrigin
@RestController
@RequestMapping(value = ["/api/player"])
class PlayerController {

    @Autowired
    private lateinit var playerService: PlayerServer


    /**
     * 查询用户列表
     */
    @RequestMapping(method = [RequestMethod.GET])
    fun getPlayerList(request: HttpServletRequest): Map<String, Any> {
        val playerList = this.playerService.findPlayerList()
        val param = HashMap<String, Any>()
        param["total"] = playerList.size
        param["rows"] = playerList
        return param
    }

    /**
     * 查询用户信息
     */
    @RequestMapping(value = ["/{userId:\\d+}"], method = [RequestMethod.GET])
    fun getPlayer(@PathVariable userId: Long, request: HttpServletRequest): Player {
        return playerService.findPlayer(userId) ?: throw RuntimeException("查询错误")
    }

    /**
     * 新增方法
     */
    @RequestMapping(method = [RequestMethod.POST])
    fun add(@RequestBody jsonObject: JSONObject) {
        val userId = jsonObject.getString("user_id")
        val name = jsonObject.getString("name")
        val year = jsonObject.getString("year")

        val player = Player()
        player.id = userId.toLongOrNull() ?: error("ID错误")
        player.name = name
        player.year = year.toLongOrNull() ?: 0
        try {
            this.playerService.add(player)
        } catch (e: Exception) {
            throw RuntimeException("新增错误")
        }
    }

    /**
     * 更新方法
     */
    @RequestMapping(value = ["/{userId:\\d+}"], method = [RequestMethod.PUT])
    fun update(@PathVariable userId: Long, @RequestBody jsonObject: JSONObject) {
        val player = this.playerService.findPlayer(userId)
        val name = jsonObject.getString("name")
        val year = jsonObject.getString("year")
        try {
            if (player != null) {
                player.name = name
                player.year = year.toLongOrNull() ?: 0
                this.playerService.update(player)
            }
        } catch (e: Exception) {
            throw RuntimeException("更新错误")
        }

    }

    /**
     * 删除方法
     */
    @RequestMapping(value = ["/{userId:\\d+}"], method = [RequestMethod.DELETE])
    fun delete(@PathVariable userId: Long) {
        try {
            this.playerService.delete(userId)
        } catch (e: Exception) {
            throw RuntimeException("删除错误")
        }
    }

}