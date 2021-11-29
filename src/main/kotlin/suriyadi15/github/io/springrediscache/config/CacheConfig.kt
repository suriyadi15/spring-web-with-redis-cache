package suriyadi15.github.io.springrediscache.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.core.RedisTemplate
import suriyadi15.github.io.springrediscache.adapter.cacheurl.CacheUrl

@Configuration
class CacheConfig(private val redisTemplate: RedisTemplate<String, Any>) {

    @Bean
    fun cacheUrl(): CacheUrl = CacheUrl(redisTemplate)
}