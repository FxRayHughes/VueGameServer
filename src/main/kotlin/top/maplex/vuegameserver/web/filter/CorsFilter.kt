package top.maplex.vuegameserver.web.filter

import jakarta.servlet.*
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.stereotype.Component
import java.io.IOException


@Component
open class CorsFilter : Filter {

    @Throws(ServletException::class, IOException::class)
    override fun doFilter(request: ServletRequest?, response: ServletResponse?, chain: FilterChain?) {
        val res: HttpServletResponse = response as HttpServletResponse
        res.addHeader("Access-Control-Allow-Credentials", "true")
        res.addHeader("Access-Control-Allow-Origin", "*")
        res.addHeader("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
        res.addHeader("Access-Control-Allow-Headers", "Content-Type,X-CAF-Authorization-Token,sessionToken,X-TOKEN")
        if ((request as HttpServletRequest).method.equals("OPTIONS")) {
            response.writer.println("ok")
            return
        }
        chain!!.doFilter(request, response)
    }

    override fun destroy() {}

    @Throws(ServletException::class)
    override fun init(filterConfig: FilterConfig?) {}


}