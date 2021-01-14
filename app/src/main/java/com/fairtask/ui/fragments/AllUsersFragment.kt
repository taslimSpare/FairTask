package com.fairtask.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.fairtask.R
import com.fairtask.databinding.FragmentAllUsersBinding
import com.fairtask.models.State
import com.fairtask.viewmodels.DummyDataViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class AllUsersFragment : Fragment() {

    private lateinit var binding: FragmentAllUsersBinding
    private val viewModel by viewModel<DummyDataViewModel>()
    private lateinit var navController: NavController


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_all_users, container, false)
        navController = Navigation.findNavController(container!!)

        observe()
        viewModel.getUsersFromRemote()

        return binding.root
    }


    private fun observe() {

        with(viewModel) {

            getUsersFromRoom.observe(viewLifecycleOwner, {
                if(it.isEmpty()) {
                    binding.rvSavedContacts.visibility = View.GONE
                    binding.noSavedContact.visibility = View.VISIBLE
                }
                else {
                    feedToLocalRecyclerView()
                }
            })

            getRemoteUsersLiveData().observe(viewLifecycleOwner, {
                when(it.state) {

                    State.LOADING -> {

                    }
                    State.ERROR -> {

                    }
                    State.SUCCESS -> {
                        feedToRemoteRecyclerView()
                    }
                }
            })
        }
    }



    private fun feedToLocalRecyclerView() {

    }

    private fun feedToRemoteRecyclerView() {

    }
}