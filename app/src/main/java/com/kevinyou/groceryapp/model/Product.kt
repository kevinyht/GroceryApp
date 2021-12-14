package com.kevinyou.groceryapp.model

import java.io.Serializable

data class Product(var product_id: Int?,
                 var product_name: String?,
                 var aisle_id: Int?,
                 var department_id: Int?,
                 var price: Double?,
                 var url: String?
                 ): Serializable