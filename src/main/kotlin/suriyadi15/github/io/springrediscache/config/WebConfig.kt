package suriyadi15.github.io.springrediscache.config

import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer
import suriyadi15.github.io.springrediscache.interceptor.CacheInterceptor

@Configuration
class WebConfig(
    private val cacheInterceptor: CacheInterceptor
) : WebMvcConfigurer {

    override fun addInterceptors(registry: InterceptorRegistry) {
        registry.addInterceptor(cacheInterceptor)
    }
}