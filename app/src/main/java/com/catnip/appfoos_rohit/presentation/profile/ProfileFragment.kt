package com.catnip.appfood_rohit.presentation.profile

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import coil.load
import coil.transform.CircleCropTransformation
import com.catnip.appfood_rohit.R
import com.catnip.appfood_rohit.data.repository.UserRepository
import com.catnip.appfood_rohit.data.repository.UserRepositoryImpl
import com.catnip.appfood_rohit.databinding.FragmentProfileBinding
import com.catnip.appfood_rohit.presentation.login.LoginActivity
import com.catnip.appfood_rohit.utils.GenericViewModelFactory
import com.catnip.appfoos_rohit.data.datasource.user.AuthDataSource
import com.catnip.appfoos_rohit.data.datasource.user.FirebaseAuthDataSource
import com.catnip.appfoos_rohit.data.source.network.firebase.FirebaseService
import com.catnip.appfoos_rohit.data.source.network.firebase.FirebaseServiceImpl


class ProfileFragment : Fragment() {

    private lateinit var binding: FragmentProfileBinding

    private val viewModel: ProfileViewModel by viewModels {
        val service: FirebaseService = FirebaseServiceImpl()
        val dataSource: AuthDataSource = FirebaseAuthDataSource(service)
        val repository: UserRepository = UserRepositoryImpl(dataSource)
        GenericViewModelFactory.create(ProfileViewModel(repository))
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProfileBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setClickListener()
        observeEditMode()
        observeProfileData()
        observeLoginStatus()
    }

    private fun setClickListener() {
        binding.btnEdit.setOnClickListener {
            viewModel.changeEditMode()
        }

        binding.btnLogout.setOnClickListener {
            viewModel.doLogout()
            navigateToLogin()
            val navController = findNavController()
            navController.navigate(R.id.menu_tab_home)
        }
    }

    private fun observeProfileData() {
        viewModel.getCurrentUser()?.let { user ->
            binding.nameEditText.setText(user.fullName)
            binding.emailEditText.setText(user.email)
        }
        viewModel.profileData.observe(viewLifecycleOwner) { profile ->
            profile?.let {
                binding.ivProfile.load(it.profileImg) {
                    crossfade(true)
                    error(R.drawable.ic_tab_profile)
                    transformations(CircleCropTransformation())
                }
            }
        }
    }


    private fun navigateToLogin() {
        startActivity(Intent(requireContext(), LoginActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        })
    }

    private fun observeEditMode() {
        viewModel.isEditMode.observe(viewLifecycleOwner) {
            binding.emailEditText.isClickable = it
            binding.nameEditText.isEnabled = it
        }
    }

    private fun observeLoginStatus() {
        if (!viewModel.isLoggedIn()) {
            navigateToLogin()
        }
    }

}