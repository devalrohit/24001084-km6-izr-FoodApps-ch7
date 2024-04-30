package com.catnip.appfood_rohit.presentation.checkout

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.catnip.appfood_rohit.R
import com.catnip.appfood_rohit.databinding.ActivityCheckoutBinding
import com.catnip.appfood_rohit.presentation.checkout.adapter.PriceListAdapter
import com.catnip.appfood_rohit.presentation.common.adapter.CartListAdapter
import com.catnip.appfood_rohit.presentation.login.LoginActivity
import com.catnip.appfood_rohit.utils.proceedWhen
import com.catnip.appfood_rohit.utils.toRupiahFormat
import org.koin.androidx.viewmodel.ext.android.viewModel

class CheckoutActivity : AppCompatActivity() {
    private val binding: ActivityCheckoutBinding by lazy {
        ActivityCheckoutBinding.inflate(layoutInflater)
    }
    private val chechkoutviewModel: CheckoutViewModel by viewModel()

    private val adapter: CartListAdapter by lazy {
        CartListAdapter()
    }
    private val priceItemAdapter: PriceListAdapter by lazy {
        PriceListAdapter {
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setupList()
        observeData()
        setClickListeners()
    }

    private fun observeData() {
        observeCartData()
        // observeCheckoutResult()
    }

/*    private fun observeCheckoutResult() {
        viewModel.checkoutResult.observe(this) {
            it.proceedWhen(
                doOnSuccess = {
                    binding.layoutState.root.isVisible = false
                    binding.layoutState.pbLoading.isVisible = false
                    showDialogCheckoutSuccess()
                },
                doOnError = {
                    binding.layoutState.root.isVisible = false
                    binding.layoutState.pbLoading.isVisible = false
                    Toast.makeText(this, "Checkout Error", Toast.LENGTH_SHORT).show()
                },
                doOnLoading = {
                    binding.layoutState.root.isVisible = true
                    binding.layoutState.pbLoading.isVisible = true

                }
            )
        }
    }*/
    private fun setClickListeners() {
        binding.btnCheckoutFinal.setOnClickListener {
            if (chechkoutviewModel.isLoggedIn()) {
                chechkoutviewModel.checkoutCart().observe(this) {
                    it.proceedWhen(
                        doOnSuccess = {
                            showDialogCheckoutSuccess()
                        },
                        doOnLoading = {
                            binding.layoutState.root.isVisible = true
                            binding.layoutState.pbLoading.isVisible = true
                            binding.layoutState.tvError.isVisible = false
                            binding.layoutContent.root.isVisible = false
                            binding.layoutContent.rvCart.isVisible = false
                        },
                        doOnError = {
                            Toast.makeText(
                                this,
                                getString(R.string.pemesanan_gagal),
                                Toast.LENGTH_SHORT,
                            ).show()
                        },
                    )
                }
            } else {
                navigateToLogin()
            }
        }
    }

    private fun showDialogCheckoutSuccess() {
        val dialogView = layoutInflater.inflate(R.layout.dialog_checkout_success, null)

        AlertDialog.Builder(this@CheckoutActivity)
            .setView(dialogView)
            .setPositiveButton(getString(R.string.kembali_ke_home)) { _, _ ->
                chechkoutviewModel.clearCart()
                finish()
            }
            .create()
            .show()
    }

    private fun navigateToLogin() {
        startActivity(Intent(this, LoginActivity::class.java))
    }

    private fun setupList() {
        binding.layoutContent.rvCart.adapter = adapter
        binding.layoutContent.rvShoppingSummary.adapter = priceItemAdapter
    }

    private fun observeCartData() {
        chechkoutviewModel.cartList.observe(this) { result ->
            result.proceedWhen(doOnSuccess = {
                binding.layoutState.root.isVisible = false
                binding.layoutState.pbLoading.isVisible = false
                binding.layoutState.tvError.isVisible = false
                binding.layoutContent.root.isVisible = true
                binding.layoutContent.rvCart.isVisible = true
                binding.cvSectionOrder.isVisible = true
                result.payload?.let { (carts, priceItems, totalPrice) ->
                    adapter.submitData(carts)
                    binding.tvTotalPrice.text = totalPrice.toRupiahFormat()
                    priceItemAdapter.submitData(priceItems)
                }
            }, doOnLoading = {
                binding.layoutState.root.isVisible = true
                binding.layoutState.pbLoading.isVisible = true
                binding.layoutState.tvError.isVisible = false
                binding.layoutContent.root.isVisible = false
                binding.layoutContent.rvCart.isVisible = false
                binding.cvSectionOrder.isVisible = false
            }, doOnError = {
                binding.layoutState.root.isVisible = true
                binding.layoutState.pbLoading.isVisible = false
                binding.layoutState.tvError.isVisible = true
                binding.layoutState.tvError.text = result.exception?.message.orEmpty()
                binding.layoutContent.root.isVisible = false
                binding.layoutContent.rvCart.isVisible = false
                binding.cvSectionOrder.isVisible = false
            }, doOnEmpty = { data ->
                binding.layoutState.root.isVisible = true
                binding.layoutState.pbLoading.isVisible = false
                binding.layoutState.tvError.isVisible = true
                binding.layoutState.tvError.text = getString(R.string.text_cart_is_empty)
                data.payload?.let { (_, _, totalPrice) ->
                    binding.tvTotalPrice.text = totalPrice.toRupiahFormat()
                }
                binding.layoutContent.root.isVisible = false
                binding.layoutContent.rvCart.isVisible = false
                binding.cvSectionOrder.isVisible = false
            })
        }
    }
}
