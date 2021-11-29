package suriyadi15.github.io.springrediscache.interceptor

import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.method.HandlerMethod
import org.springframework.web.servlet.HandlerInterceptor
import org.springframework.web.servlet.ModelAndView
import suriyadi15.github.io.springrediscache.adapter.cacheurl.Cache
import suriyadi15.github.io.springrediscache.adapter.cacheurl.CacheUrl
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class CacheInterceptor(
    private val cacheUrl: CacheUrl
) : HandlerInterceptor {

    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
        val cacheAnnotation = getCacheAnnotation(handler)
        if (cacheAnnotation != null) {
            val cacheData = cacheUrl.get(request) //Get Cache Data
            if (cacheData != null) {
                val data = cacheData.toByteArray()

                response.outputStream.write(data)
                response.status = HttpStatus.OK.value()
                response.contentType = MediaType.APPLICATION_JSON_VALUE

                return false
            }
        }
        return true
    }

    override fun postHandle(
        request: HttpServletRequest,
        response: HttpServletResponse,
        handler: Any,
        modelAndView: ModelAndView?
    ) {
        request.setAttribute("mustCache", true)
    }

    private fun getCacheAnnotation(handler: Any): Cache? {
        if (handler is HandlerMethod) {
            return handler.getMethodAnnotation(Cache::class.java)
        }
        return null
    }
}