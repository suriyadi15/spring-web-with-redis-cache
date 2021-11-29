package suriyadi15.github.io.springrediscache.filter

import org.springframework.stereotype.Component
import org.springframework.web.util.ContentCachingResponseWrapper
import suriyadi15.github.io.springrediscache.adapter.cacheurl.CacheUrl
import java.nio.charset.Charset
import javax.servlet.Filter
import javax.servlet.FilterChain
import javax.servlet.ServletRequest
import javax.servlet.ServletResponse
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse


@Component
class CacheFilter(
    private val cacheUrl: CacheUrl
) : Filter {
    override fun doFilter(request: ServletRequest?, response: ServletResponse?, chain: FilterChain?) {
        var cacheData: String? = null
        if (response is HttpServletResponse) {
            val responseCacheWrapperObject = ContentCachingResponseWrapper((response))
            chain?.doFilter(request, responseCacheWrapperObject)
            cacheData = String(
                responseCacheWrapperObject.contentAsByteArray,
                Charset.forName(responseCacheWrapperObject.characterEncoding)
            )

            responseCacheWrapperObject.copyBodyToResponse()
        } else {
            chain?.doFilter(request, response)
        }


        val mustCache = request?.getAttribute("mustCache") as Boolean?
        if (mustCache == true && cacheData != null) {
            cacheUrl.set(request as HttpServletRequest, cacheData)
        }
    }
}