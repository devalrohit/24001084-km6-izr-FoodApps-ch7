package com.catnip.appfood_rohit.data.datasource.product

import com.catnip.appfood_rohit.data.model.Product

/**
Written with love by Muhammad Hermas Yuda Pamungkas
Github : https://github.com/hermasyp
 **/
class DummyProductDataSource : ProductDataSource {
    override fun getProducts(): List<Product> {
        return mutableListOf(
            Product(
                name = "Ayam Goreng",
                price = 20000.0,
                desc = "Ayam goreng ini merupakan salah satu menu favorit kami karena kami memilih daging yang berkualitas tinggi dan mengolahnya dengan rempah-rempah pilihan, sehingga menghasilkan cita rasa yang gurih dan nikmat libero volutpat sagittis. Praesent id feugiat dui. Pellentesque tincidunt, urna non consequat accumsan, lacus enim tristique orci, egestas aliquam neque lectus vel eros. Aliquam sed congue enim. Donec aliquam lorem eu velit tincidunt elementum. Nunc quis eleifend enim. Cras porttitor vulputate finibus. Donec fermentum tincidunt vulputate. Proin euismod nunc in orci tempor, eget rutrum est feugiat. Donec luctus, nibh ut congue dapibus, est purus commodo erat, quis ornare quam enim non justo.",
                imgUrl = "https://img.freepik.com/photos-premium/cuisses-poulet-frit-isoles-fond-blanc_79161-405.jpg?w=826",
                location = "Lokasi",
                detailLocation = "Jl. BSD Green Office Park Jl. BSD Grand Boulevard, Sampora, BSD, Kabupaten Tangerang, Banten 15345",
                locationPictUrl = "https://maps.app.goo.gl/h4wQKqaBuXzftGK77"
            ),
            Product(
                name = "Burger",
                price = 15000.0,
                desc = "Burger kami disajikan dengan patty daging sapi segar yang dipanggang sempurna, disertai dengan keju leleh, sayuran segar, dan saus spesial, menciptakan pengalaman makan yang memuaskan libero volutpat sagittis. Praesent id feugiat dui. Pellentesque tincidunt, urna non consequat accumsan, lacus enim tristique orci, egestas aliquam neque lectus vel eros. Aliquam sed congue enim. Donec aliquam lorem eu velit tincidunt elementum. Nunc quis eleifend enim. Cras porttitor vulputate finibus. Donec fermentum tincidunt vulputate. Proin euismod nunc in orci tempor, eget rutrum est feugiat. Donec luctus, nibh ut congue dapibus, est purus commodo erat, quis ornare quam enim non justo..",
                imgUrl = "https://i.pinimg.com/originals/01/a8/b2/01a8b20022d3ac8d1c0ad960e7b67466.jpg",
                location = "Lokasi",
                detailLocation = "Jl. BSD Green Office Park Jl. BSD Grand Boulevard, Sampora, BSD, Kabupaten Tangerang, Banten 15345",
                locationPictUrl = "https://maps.app.goo.gl/h4wQKqaBuXzftGK77"
            ),
            Product(
                name = "Pizza",
                price = 30000.0,
                desc = "Pizza kami dibuat dengan adonan yang lembut dan renyah, lalu dipadukan dengan beragam topping berkualitas tinggi seperti pepperoni, jamur segar, dan keju mozzarella, memberikan cita rasa yang khas dan menggugah selera libero volutpat sagittis. Praesent id feugiat dui. Pellentesque tincidunt, urna non consequat accumsan, lacus enim tristique orci, egestas aliquam neque lectus vel eros. Aliquam sed congue enim. Donec aliquam lorem eu velit tincidunt elementum. Nunc quis eleifend enim. Cras porttitor vulputate finibus. Donec fermentum tincidunt vulputate. Proin euismod nunc in orci tempor, eget rutrum est feugiat. Donec luctus, nibh ut congue dapibus, est purus commodo erat, quis ornare quam enim non justo..",
                imgUrl = "https://img.taste.com.au/fRe6DyVS/w720-h480-cfill-q80/taste/2019/08/zucchini-and-ricotta-pizza-152910-1.jpg",
                location = "Lokasi",
                detailLocation = "Jl. BSD Green Office Park Jl. BSD Grand Boulevard, Sampora, BSD, Kabupaten Tangerang, Banten 15345",
                locationPictUrl = "https://maps.app.goo.gl/h4wQKqaBuXzftGK77"
            ),
            Product(
                name = "Ramen",
                price = 25000.0,
                desc = "Ramen kami terkenal dengan kuah kaldu yang kaya rasa dan mie yang kenyal. Disajikan dengan irisan daging ayam atau babi, telur rebus, dan sayuran segar, menciptakan hidangan yang lezat dan menghangatkan libero volutpat sagittis. Praesent id feugiat dui. Pellentesque tincidunt, urna non consequat accumsan, lacus enim tristique orci, egestas aliquam neque lectus vel eros. Aliquam sed congue enim. Donec aliquam lorem eu velit tincidunt elementum. Nunc quis eleifend enim. Cras porttitor vulputate finibus. Donec fermentum tincidunt vulputate. Proin euismod nunc in orci tempor, eget rutrum est feugiat. Donec luctus, nibh ut congue dapibus, est purus commodo erat, quis ornare quam enim non justo..",
                imgUrl = "https://www.halfbakedharvest.com/wp-content/uploads/2021/01/30-Minute-Spicy-Miso-Chicken-Katsu-Ramen-1-1024x1536.jpg",
                location = "Lokasi",
                detailLocation = "Jl. BSD Green Office Park Jl. BSD Grand Boulevard, Sampora, BSD, Kabupaten Tangerang, Banten 15345",
                locationPictUrl = "https://maps.app.goo.gl/h4wQKqaBuXzftGK77"
            ),
            Product(
                name = "Sushi",
                price = 20000.0,
                desc = "Sushi kami disiapkan dengan teliti oleh chef berpengalaman menggunakan bahan-bahan segar terbaik. Tiap gigitannya memberikan perpaduan rasa yang unik antara nasi lembut dan isian yang beragam, memberikan pengalaman makan yang memuaskan libero volutpat sagittis. Praesent id feugiat dui. Pellentesque tincidunt, urna non consequat accumsan, lacus enim tristique orci, egestas aliquam neque lectus vel eros. Aliquam sed congue enim. Donec aliquam lorem eu velit tincidunt elementum. Nunc quis eleifend enim. Cras porttitor vulputate finibus. Donec fermentum tincidunt vulputate. Proin euismod nunc in orci tempor, eget rutrum est feugiat. Donec luctus, nibh ut congue dapibus, est purus commodo erat, quis ornare quam enim non justo..",
                imgUrl = "https://jbimagery1.files.wordpress.com/2022/12/sushi-club-mk-19-bhavisha-surti-food-photographer-and-stylist-milton-keynes.jpg?w=768",
                location = "Lokasi",
                detailLocation = "Jl. BSD Green Office Park Jl. BSD Grand Boulevard, Sampora, BSD, Kabupaten Tangerang, Banten 15345",
                locationPictUrl = "https://maps.app.goo.gl/h4wQKqaBuXzftGK77"
            ),
            Product(
                name = "Nasi Padang",
                price = 10000.0,
                desc = "Nasi Padang kami terkenal dengan ragam hidangan khas Minang yang lezat, seperti rendang, ayam goreng, sambalado, dan gulai cubadak. Setiap suapannya menghadirkan cita rasa yang autentik dan menggugah selera libero volutpat sagittis. Praesent id feugiat dui. Pellentesque tincidunt, urna non consequat accumsan, lacus enim tristique orci, egestas aliquam neque lectus vel eros. Aliquam sed congue enim. Donec aliquam lorem eu velit tincidunt elementum. Nunc quis eleifend enim. Cras porttitor vulputate finibus. Donec fermentum tincidunt vulputate. Proin euismod nunc in orci tempor, eget rutrum est feugiat. Donec luctus, nibh ut congue dapibus, est purus commodo erat, quis ornare quam enim non justo..",
                imgUrl = "https://64.media.tumblr.com/5126d534ccdc304a73e0ab3324aa3075/tumblr_pex49heqKH1v32ipio1_640.jpg",
                location = "Lokasi",
                detailLocation = "Jl. BSD Green Office Park Jl. BSD Grand Boulevard, Sampora, BSD, Kabupaten Tangerang, Banten 15345",
                locationPictUrl = "https://maps.app.goo.gl/h4wQKqaBuXzftGK77"
            ),
            Product(
                name = "Bubur Ayam",
                price = 15000.0,
                desc = "Bubur ayam kami disajikan dengan ayam suwir yang lembut, telur rebus, bawang goreng, dan kerupuk, disiram dengan kuah kaldu yang gurih. Cocok disantap sebagai sarapan atau camilan di segala waktu libero volutpat sagittis. Praesent id feugiat dui. Pellentesque tincidunt, urna non consequat accumsan, lacus enim tristique orci, egestas aliquam neque lectus vel eros. Aliquam sed congue enim. Donec aliquam lorem eu velit tincidunt elementum. Nunc quis eleifend enim. Cras porttitor vulputate finibus. Donec fermentum tincidunt vulputate. Proin euismod nunc in orci tempor, eget rutrum est feugiat. Donec luctus, nibh ut congue dapibus, est purus commodo erat, quis ornare quam enim non justo..",
                imgUrl = "https://i0.wp.com/rasabunda.com/wp-content/uploads/2021/03/Bubur-Ayam.jpg?fit=449%2C600&ssl=1",
                location = "Lokasi",
                detailLocation = "Jl. BSD Green Office Park Jl. BSD Grand Boulevard, Sampora, BSD, Kabupaten Tangerang, Banten 15345",
                locationPictUrl = "https://maps.app.goo.gl/h4wQKqaBuXzftGK77"
            )
        )
    }
}