package suriyadi15.github.io.springrediscache.controller

import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import suriyadi15.github.io.springrediscache.adapter.cacheurl.Cache
import suriyadi15.github.io.springrediscache.entity.Product
import suriyadi15.github.io.springrediscache.service.ProductService


@RestController
class ProductController(
    private val productService: ProductService
) {

    @GetMapping(
        value = [""],
        produces = [MediaType.APPLICATION_JSON_VALUE]
    )
    @Cache
    fun list(): List<Product> {
        return productService.list()
    }
}