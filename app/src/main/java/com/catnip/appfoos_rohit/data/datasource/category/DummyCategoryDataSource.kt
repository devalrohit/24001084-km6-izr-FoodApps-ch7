package com.catnip.appfood_rohit.data.datasource.category

import com.catnip.appfood_rohit.data.model.Category

/**
Written with love by Muhammad Hermas Yuda Pamungkas
Github : https://github.com/hermasyp
 **/
class DummyCategoryDataSource : CategoryDataSource{
    override fun getCategories(): List<Category> {
        return listOf(
            Category(
                name = "French Fries",
                imgUrl = "https://github.com/devalrohit/AppFoodch4_assets/blob/main/category/ic_French%20fries.png?raw=true"
            ),
            Category(
                name = "Drink",
                imgUrl = "https://raw.githubusercontent.com/devalrohit/AppFoodch4_assets/bc27a0af92b59c7f880ca6621a04c3af76bb49a9/category/ic_drink.png"
            ),
            Category(
                name = "Noodles",
                imgUrl = "https://raw.githubusercontent.com/devalrohit/AppFoodch4_assets/bc27a0af92b59c7f880ca6621a04c3af76bb49a9/category/ic_noodles.png"
            ),
            Category(
                name = "Rise",
                imgUrl = "https://raw.githubusercontent.com/devalrohit/AppFoodch4_assets/bc27a0af92b59c7f880ca6621a04c3af76bb49a9/category/ic_rise.png"
            ),
            Category(
                name = "Sushi",
                imgUrl = "https://raw.githubusercontent.com/devalrohit/AppFoodch4_assets/bc27a0af92b59c7f880ca6621a04c3af76bb49a9/category/ic_sushi.png"
            )
        )
    }

}