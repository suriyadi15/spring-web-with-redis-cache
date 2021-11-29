package suriyadi15.github.io.springrediscache.service.impl

import org.springframework.stereotype.Service
import suriyadi15.github.io.springrediscache.entity.Product
import suriyadi15.github.io.springrediscache.service.ProductService

@Service
class ProductServiceImpl : ProductService {
    private val products = mutableListOf(
        Product(
            name = "Donat",
            price = 2000
        ),
        Product(
            name = "Nasgor",
            price = 10000
        ),
        Product(
            name = "Indomie",
            price = 12000
        )
    )

    override fun list(): List<Product> {
        return products
    }
}