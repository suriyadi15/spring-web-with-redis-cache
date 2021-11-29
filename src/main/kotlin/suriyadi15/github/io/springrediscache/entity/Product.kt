package suriyadi15.github.io.springrediscache.entity

import java.util.*

data class Product(
    val id: UUID = UUID.randomUUID(),
    var name: String,
    var price: Int
)