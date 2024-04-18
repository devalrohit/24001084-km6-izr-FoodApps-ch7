package com.catnip.appfood_rohit.presentation.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.catnip.appfood_rohit.R
import com.catnip.appfood_rohit.data.datasource.category.CategoryApiDataSource
import com.catnip.appfood_rohit.data.datasource.category.CategoryDataSource
import com.catnip.appfood_rohit.data.datasource.product.ProductApiDataSource
import com.catnip.appfood_rohit.data.datasource.product.ProductDataSource
import com.catnip.appfood_rohit.data.model.Category
import com.catnip.appfood_rohit.data.model.Product
import com.catnip.appfood_rohit.data.repository.CategoryRepository
import com.catnip.appfood_rohit.data.repository.CategoryRepositoryImpl
import com.catnip.appfood_rohit.data.repository.ProductRepository
import com.catnip.appfood_rohit.data.repository.ProductRepositoryImpl
import com.catnip.appfood_rohit.data.source.local.pref.UserPreferenceImpl
import com.catnip.appfood_rohit.data.source.network.services.AppFoodRohitApiService
import com.catnip.appfood_rohit.databinding.FragmentHomeBinding
import com.catnip.appfood_rohit.presentation.detailproduct.DetailProductActivity
import com.catnip.appfood_rohit.presentation.home.adapter.CategoryListAdapter
import com.catnip.appfood_rohit.presentation.home.adapter.OnItemClickedListener
import com.catnip.appfood_rohit.presentation.home.adapter.ProductListAdapter
import com.catnip.appfood_rohit.utils.GenericViewModelFactory
import com.catnip.appfood_rohit.utils.GridSpacingItemDecoration
import com.catnip.appfood_rohit.utils.proceedWhen


    class HomeFragment : Fragment() {

        private lateinit var binding: FragmentHomeBinding
        private val viewModel: HomeViewModel by viewModels {
            val service = AppFoodRohitApiService.invoke()
            val userPreference = UserPreferenceImpl(requireContext())
            val menuDataSource = ProductApiDataSource(service)
            val menuRepository: ProductRepository = ProductRepositoryImpl(menuDataSource)
            val categoryDataSource = CategoryApiDataSource(service)
            val categoryRepository: CategoryRepository = CategoryRepositoryImpl(categoryDataSource)
            GenericViewModelFactory.create(
                HomeViewModel(
                    categoryRepository,
                    menuRepository,
                    userPreference
                )
            )
        }
        private val categoryAdapter: CategoryListAdapter by lazy {
            CategoryListAdapter {
                getMenuData(it.name)
            }
        }
        private val menuAdapter: ProductListAdapter by lazy {
            ProductListAdapter(viewModel.getListMode()) {
                navigateToDetail(it)
            }
        }

        private fun getMenuData(name: String? = null) {
            viewModel.getMenu(name).observe(viewLifecycleOwner) {
                it.proceedWhen(
                    doOnSuccess = {
                        it.payload?.let { data ->
                            bindMenuList(data)
                        }
                    }
                )
            }
        }

        private fun setupMenu() {
            binding.rvMenu.apply {
                adapter = menuAdapter
            }
        }

        private fun getCategoryData() {
            viewModel.getCategory().observe(viewLifecycleOwner) {
                it.proceedWhen(
                    doOnSuccess = {
                        it.payload?.let { data -> bindCategory(data) }
                    }
                )
            }
        }

        private fun bindCategory(data: List<Category>) {
            categoryAdapter.submitData(data)
        }

        private fun setupCategory() {
            binding.rvCategory.apply {
                adapter = categoryAdapter
            }
        }

        private fun observeGridMode() {
            viewModel.isUsingGridMode.observe(viewLifecycleOwner) { isUsingGridMode ->
                changeBtnIcon(isUsingGridMode)
                changeLayout(isUsingGridMode)
            }
        }

        private fun changeLayout(usingGridMode: Boolean) {
            val listMode =
                if (usingGridMode) ProductListAdapter.MODE_GRID else ProductListAdapter.MODE_LIST
            menuAdapter.updateListMode(listMode)
            setupMenu()
            binding.rvMenu.apply {
                layoutManager = GridLayoutManager(requireContext(), if (usingGridMode) 2 else 1)
            }
        }


        private fun setClickAction() {
            binding.btnChangeListMode.setOnClickListener {
                viewModel.changeListMode()
            }
        }


        private fun changeBtnIcon(usingGridMode: Boolean) {
            binding.btnChangeListMode.setImageResource(if (usingGridMode) R.drawable.ic_vertikal else R.drawable.ic_horizontal)
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
            setupCategory()
            setupMenu()
            observeGridMode()
            getCategoryData()
            getMenuData(null)
            setClickAction()
        }

        private fun bindCategoryList(data: List<Category>) {
            binding.rvCategory.apply {
                adapter = this@HomeFragment.categoryAdapter
            }
            categoryAdapter.submitData(data)
        }

        private fun bindMenuList(data: List<Product>) {
            menuAdapter.submitData(data)
        }

        private fun navigateToDetail(item: Product) {
            val navController = findNavController()
            val bundle = bundleOf(Pair(DetailProductActivity.EXTRA_PRODUCT, item))
            navController.navigate(R.id.action_menu_tab_home_to_detailProductActivity, bundle)
        }
    }
