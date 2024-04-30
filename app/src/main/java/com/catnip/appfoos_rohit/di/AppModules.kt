package com.catnip.appfoos_rohit.di

import android.content.SharedPreferences
import com.catnip.appfood_rohit.data.datasource.cart.CartDataSource
import com.catnip.appfood_rohit.data.datasource.cart.CartDatabaseDataSource
import com.catnip.appfood_rohit.data.datasource.category.CategoryApiDataSource
import com.catnip.appfood_rohit.data.datasource.category.CategoryDataSource
import com.catnip.appfood_rohit.data.datasource.product.ProductApiDataSource
import com.catnip.appfood_rohit.data.datasource.product.ProductDataSource
import com.catnip.appfood_rohit.data.datasource.user.UserDataSource
import com.catnip.appfood_rohit.data.datasource.user.UserDataSourceImpl
import com.catnip.appfood_rohit.data.repository.CartRepository
import com.catnip.appfood_rohit.data.repository.CartRepositoryImpl
import com.catnip.appfood_rohit.data.repository.CategoryRepository
import com.catnip.appfood_rohit.data.repository.CategoryRepositoryImpl
import com.catnip.appfood_rohit.data.repository.ProductRepository
import com.catnip.appfood_rohit.data.repository.ProductRepositoryImpl
import com.catnip.appfood_rohit.data.repository.UserRepository
import com.catnip.appfood_rohit.data.repository.UserRepositoryImpl
import com.catnip.appfood_rohit.data.source.local.database.AppDatabase
import com.catnip.appfood_rohit.data.source.local.database.dao.CartDao
import com.catnip.appfood_rohit.data.source.network.services.AppFoodRohitApiService
import com.catnip.appfood_rohit.presentation.cart.CartViewModel
import com.catnip.appfood_rohit.presentation.checkout.CheckoutViewModel
import com.catnip.appfood_rohit.presentation.detailproduct.DetailProductViewModel
import com.catnip.appfood_rohit.presentation.home.HomeViewModel
import com.catnip.appfood_rohit.presentation.login.LoginViewModel
import com.catnip.appfood_rohit.presentation.profile.ProfileViewModel
import com.catnip.appfood_rohit.presentation.register.RegisterViewModel
import com.catnip.appfood_rohit.utils.SharedPreferenceUtils
import com.catnip.appfoos_rohit.data.datasource.user.AuthDataSource
import com.catnip.appfoos_rohit.data.datasource.user.FirebaseAuthDataSource
import com.catnip.appfoos_rohit.data.repository.PreferenceRepository
import com.catnip.appfoos_rohit.data.repository.PreferenceRepositoryImpl
import com.catnip.appfoos_rohit.data.source.network.firebase.FirebaseService
import com.catnip.appfoos_rohit.data.source.network.firebase.FirebaseServiceImpl
import com.catnip.appfoos_rohit.presentation.main.MainViewModel
import com.catnip.firebaseauthexample.presentation.splashscreen.SplashViewModel
import com.example.foodapp.data.datasource.preference.PreferenceDataSource
import com.example.foodapp.data.datasource.preference.PreferenceDataSourceImpl
import com.google.firebase.auth.FirebaseAuth
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.Module
import org.koin.dsl.module

object AppModules {

    private val networkModule = module {
        single<AppFoodRohitApiService> { AppFoodRohitApiService.invoke() }
    }

    private val firebaseModule = module {
        single<FirebaseAuth> { FirebaseAuth.getInstance() }
        single<FirebaseService> { FirebaseServiceImpl(get()) }
    }

    val localModule = module {
        single<AppDatabase> { AppDatabase.createInstance(androidContext()) }
        single<CartDao> { get<AppDatabase>().cartDao() }
        single<SharedPreferences> {
            SharedPreferenceUtils.createPreference(
                androidContext(),
                PreferenceDataSourceImpl.PREF_NAME
            )
        }
        single<PreferenceDataSource> { PreferenceDataSourceImpl(get()) }
    }

    private val dataSource = module {
        single<CartDataSource> { CartDatabaseDataSource(get()) }
        single<CategoryDataSource> { CategoryApiDataSource(get()) }
        single<ProductDataSource> { ProductApiDataSource(get()) }
        single<UserDataSource> { UserDataSourceImpl() }
        single<AuthDataSource> { FirebaseAuthDataSource(get()) }
    }
    private val repository = module {
        single<CartRepository> { CartRepositoryImpl(get()) }
        single<CategoryRepository> { CategoryRepositoryImpl(get()) }
        single<ProductRepository> { ProductRepositoryImpl(get()) }
        single<UserRepository> { UserRepositoryImpl(get()) }
        single<PreferenceRepository> { PreferenceRepositoryImpl(get()) }
    }

    private val viewModelModule = module {
        viewModelOf(::HomeViewModel)
        viewModelOf(::CartViewModel)
        viewModelOf(::CheckoutViewModel)
        viewModel { params ->
            //assisted injection
            DetailProductViewModel(
                extras = params.get(),
                cartRepository = get()
            )
        }
        viewModelOf(::LoginViewModel)
        viewModelOf(::MainViewModel)
        viewModelOf(::ProfileViewModel)
        viewModelOf(::RegisterViewModel)
        viewModelOf(::SplashViewModel)
    }

    val modules = listOf<Module>(
        networkModule,
        localModule,
        dataSource,
        repository,
        viewModelModule,
        firebaseModule
    )
}

