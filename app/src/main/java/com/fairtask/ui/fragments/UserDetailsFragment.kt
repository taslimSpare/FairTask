package com.fairtask.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.bumptech.glide.Glide
import com.fairtask.R
import com.fairtask.databinding.FragmentUserDetailsBinding
import com.fairtask.fn.getProfile
import com.fairtask.fn.toast
import com.fairtask.models.User
import com.fairtask.viewmodels.DummyDataViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class UserDetailsFragment : Fragment() {

    private lateinit var binding: FragmentUserDetailsBinding
    private lateinit var navController: NavController
    private val viewModel by viewModel<DummyDataViewModel>()
    private var user = User()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_user_details, container, false)
        navController = Navigation.findNavController(container!!)

        setupViews()
        setOnClickListeners()

        return binding.root
    }


    private fun setupViews() {

        user = getProfile(requireContext())
        binding.profile = user

        Glide.with(requireContext())
            .load(user.picture)
            .placeholder(R.drawable.ic_person_purple)
            .into(binding.ivDisplayPicture)
    }


    private fun setOnClickListeners() {
        binding.ivDisplayPicture.setOnClickListener { navController.navigate(R.id.action_userDetailsFragment_to_photoFragment) }
        binding.ibBack.setOnClickListener { navController.navigate(R.id.action_userDetailsFragment_to_allUsersFragment) }
        binding.cvToggleFav.setOnClickListener {
            user.saved = !user.saved
            if(user.saved) { viewModel.addUserToRoom(user) } else { viewModel.deleteUserFromRoom(user.id) }
            requireContext().toast(if(user.saved) "User saved to local storage" else "User removed from local storage")
            binding.profile = user
        }
    }

}