package com.catnip.appfood_rohit.presentation.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.catnip.appfood_rohit.R
import com.catnip.appfood_rohit.data.datasource.category.DummyCategoryDataSource
import com.catnip.appfood_rohit.data.datasource.product.DummyProductDataSource
import com.catnip.appfood_rohit.data.datasource.product.ProductDataSource
import com.catnip.appfood_rohit.data.model.Category
import com.catnip.appfood_rohit.data.model.Product
import com.catnip.appfood_rohit.data.repository.CategoryRepository
import com.catnip.appfood_rohit.data.repository.CategoryRepositoryImpl
import com.catnip.appfood_rohit.data.repository.ProductRepository
import com.catnip.appfood_rohit.data.repository.ProductRepositoryImpl
import com.catnip.appfood_rohit.databinding.FragmentCartBinding
import com.catnip.appfood_rohit.databinding.FragmentHomeBinding
import com.catnip.appfood_rohit.presentation.detailproduct.DetailProductActivity
import com.catnip.appfood_rohit.presentation.home.adapter.CategoryListAdapter
import com.catnip.appfood_rohit.presentation.home.adapter.OnItemClickedListener
import com.catnip.appfood_rohit.presentation.home.adapter.ProductListAdapter
import com.catnip.appfood_rohit.utils.GenericViewModelFactory
import com.catnip.appfood_rohit.utils.GridSpacingItemDecoration

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private var isUsingGridMode: Boolean = false
    private var menuadapter: ProductListAdapter? = null
    private val viewModel: HomeViewModel by viewModels {
        val productDataSource = DummyProductDataSource()
        val productRepository: ProductRepository = ProductRepositoryImpl(productDataSource)
        val categoryDataSource = DummyCategoryDataSource()
        val categoryRepository: CategoryRepository = CategoryRepositoryImpl(categoryDataSource)
        GenericViewModelFactory.create(HomeViewModel(categoryRepository, productRepository))
    }

    private val categoryAdapter: CategoryListAdapter by lazy {
        CategoryListAdapter {

        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindCategoryList(viewModel.getCategories())
        bindProductList(isUsingGridMode)
        setButtonText(isUsingGridMode)
        setClickAction()
        viewModel.getProducts()
        viewModel.getCategories()
    }

    private fun setClickAction() {
        binding.btnChangeListMode.setOnClickListener {
            isUsingGridMode = !isUsingGridMode
            setButtonText(isUsingGridMode)
            bindProductList(isUsingGridMode)
        }

    }
    private fun setButtonText(usingGridMode: Boolean) {
        val drawableResId = if (usingGridMode) R.drawable.ic_vertikal else R.drawable.ic_horizontal
        val drawable = ContextCompat.getDrawable(requireContext(), drawableResId)
        binding.btnChangeListMode.setImageDrawable(drawable)
    }
    private fun navigateToDetail(item: Product) {
        val navController = findNavController()
        val bundle = bundleOf(Pair(DetailProductActivity.EXTRA_PRODUCT, item))
        navController.navigate(R.id.action_menu_tab_home_to_detailProductActivity, bundle)
    }

    private fun bindProductList(isUsingGrid: Boolean) {
        val listMode = if (isUsingGrid) ProductListAdapter.MODE_GRID else ProductListAdapter.MODE_LIST
        menuadapter = ProductListAdapter(
            listMode = listMode,
            listener = object : OnItemClickedListener<Product> {
                override fun onItemClicked(item: Product) {
                    navigateToDetail(item)
                } })
        binding.rvProductList.apply {
            adapter = this@HomeFragment.menuadapter
            layoutManager = GridLayoutManager(requireContext(), if (isUsingGrid) 2 else 1)
        }
        menuadapter?.submitData(viewModel.getProducts())
    }
    private fun bindCategoryList(data: List<Category>) {
        binding.rvCategory.apply {
            adapter = categoryAdapter
        }
        categoryAdapter.submitData(data)
    }
}
