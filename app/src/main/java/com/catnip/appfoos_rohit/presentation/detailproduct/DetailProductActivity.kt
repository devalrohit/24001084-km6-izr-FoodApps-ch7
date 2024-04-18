package com.catnip.appfood_rohit.presentation.detailproduct

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import coil.load
import com.catnip.appfood_rohit.R
import com.catnip.appfood_rohit.data.datasource.cart.CartDataSource
import com.catnip.appfood_rohit.data.datasource.cart.CartDatabaseDataSource
import com.catnip.appfood_rohit.data.model.Product
import com.catnip.appfood_rohit.data.repository.CartRepository
import com.catnip.appfood_rohit.data.repository.CartRepositoryImpl
import com.catnip.appfood_rohit.data.source.local.database.AppDatabase
import com.catnip.appfood_rohit.databinding.ActivityDetailProductBinding
import com.catnip.appfood_rohit.utils.GenericViewModelFactory
import com.catnip.appfood_rohit.utils.proceedWhen
import com.catnip.appfood_rohit.utils.toRupiahFormat

class DetailProductActivity : AppCompatActivity() {
    private val binding: ActivityDetailProductBinding by lazy {
        ActivityDetailProductBinding.inflate(layoutInflater)
    }
    private val viewModel: DetailProductViewModel by viewModels {
        val db = AppDatabase.getInstance(this)
        val ds: CartDataSource = CartDatabaseDataSource(db.cartDao())
        val rp: CartRepository = CartRepositoryImpl(ds)
        GenericViewModelFactory.create(
            DetailProductViewModel(intent?.extras, rp)
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        bindProduct(viewModel.product)
        setClickListener()
        observeData()
        val product = intent.getParcelableExtra<Product>(EXTRA_PRODUCT)
        product?.let { displayProductDetails(it) }
    }

    private fun setClickListener() {
        binding.ivBack.setOnClickListener {
            onBackPressed()
        }
        binding.ivMinus.setOnClickListener {
            viewModel.minus()
        }
        binding.ivPlus.setOnClickListener {
            viewModel.add()
        }
        binding.btnAddToCart.setOnClickListener {
            addProductToCart()
        }
        binding.layoutDetailLocation.tvDetailLocation.setOnClickListener {
            viewModel.product?.let { product ->
                openLocationOnMap(product.locationPictUrl)
            }
        }
    }

    private fun displayProductDetails(product: Product) {
        binding.layoutDetailLocation.apply {
            tvDetailLocation.text = product.location
        }
    }

    private fun bindProduct(product: Product?) {
        product?.let { item ->
            binding.ivMenuImage.load(item.imgUrl) {
                crossfade(true)
            }
            binding.tvMenuNameList.text = item.name
            binding.tvProductDesc.text = item.desc
            binding.tvMenuPrice.text = item.price.toRupiahFormat()
        }
    }

    private fun addProductToCart() {
        viewModel.addToCart().observe(this) {
            it.proceedWhen(
                doOnSuccess = {
                    Toast.makeText(
                        this,
                        getString(R.string.text_add_to_cart_success), Toast.LENGTH_SHORT
                    ).show()
                    finish()
                },
                doOnError = {
                    Toast.makeText(
                        this, getString(R.string.add_to_cart_failed), Toast.LENGTH_SHORT
                    ).show()
                },
                doOnLoading = {
                    Toast.makeText(this, getString(R.string.loading), Toast.LENGTH_SHORT).show()
                }
            )
        }
    }

    private fun observeData() {
        viewModel.priceLiveData.observe(this) {
            binding.btnAddToCart.isEnabled = it != 0.0
            binding.tvCalculatedProductPrice.text = it.toRupiahFormat()
        }
        viewModel.productCountLiveData.observe(this) {
            binding.tvProductCount.text = it.toString()
        }
    }

    private fun openLocationOnMap(locationUrl: String) {
        if (locationUrl.isNotBlank()) {
            val mapIntent = Intent(Intent.ACTION_VIEW, Uri.parse(locationUrl))
            if (mapIntent.resolveActivity(packageManager) != null) {
                startActivity(mapIntent)
            } else {
                Toast.makeText(this, "No application can handle this action", Toast.LENGTH_SHORT).show()
            }
        } else {
            Toast.makeText(this, "Location URL is empty", Toast.LENGTH_SHORT).show()
        }
    }

    companion object {
        const val EXTRA_PRODUCT = "EXTRA_PRODUCT"
        fun startActivity(context: Context, product: Product) {
            val intent = Intent(context, DetailProductActivity::class.java)
            intent.putExtra(EXTRA_PRODUCT, product)
            context.startActivity(intent)
        }
    }
}
