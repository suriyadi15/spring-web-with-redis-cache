package suriyadi15.github.io.springrediscache.service

import suriyadi15.github.io.springrediscache.entity.Product

interface ProductService {
    fun list(): List<Product>
}