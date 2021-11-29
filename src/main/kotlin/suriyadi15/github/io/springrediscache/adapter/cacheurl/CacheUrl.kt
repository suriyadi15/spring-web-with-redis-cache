package suriyadi15.github.io.springrediscache.adapter.cacheurl

import org.springframework.data.redis.core.RedisTemplate
import java.time.Duration
import javax.servlet.http.HttpServletRequest

class CacheUrl(private val template: RedisTemplate<String, Any>) {
    fun get(request: HttpServletRequest): String? {
        return template.opsForValue().get(getKey(request))?.toString()
    }

    fun set(request: HttpServletRequest, data: String) {
        try {
            template.opsForValue().set(
                getKey(request),
                data,
                Duration.ofMinutes(10)
            )
        } catch (ex: Exception) {
        }
    }

    private fun getKey(request: HttpServletRequest): String {
        val queryString = if (request.queryString == null)
            ""
        else
            "?${request.queryString}"

        return "${request.requestURL}${queryString}"
    }
}