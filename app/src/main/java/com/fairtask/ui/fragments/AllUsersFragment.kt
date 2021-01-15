package com.fairtask.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.fairtask.R
import com.fairtask.databinding.FragmentAllUsersBinding
import com.fairtask.models.State
import com.fairtask.models.User
import com.fairtask.ui.adapters.AllUsersAdapter
import com.fairtask.ui.adapters.SavedUsersAdapter
import com.fairtask.viewmodels.DummyDataViewModel
import com.fairtask.viewmodels.InterfaceForViewModel
import com.google.android.material.snackbar.Snackbar


class AllUsersFragment : Fragment() {

    private lateinit var binding: FragmentAllUsersBinding
    private lateinit var navController: NavController
    private var localList = mutableListOf<User>()
    private var remoteList = mutableListOf<User>()
    private val localAdapter by lazy { SavedUsersAdapter(requireContext(), localList) { user -> openUserDetails(
        user
    )} }
    private val remoteAdapter by lazy { AllUsersAdapter(requireContext(), remoteList) { user -> openUserDetails(
        user
    )} }


    private var interfaceCallBack: InterfaceForViewModel? = null
    private val sharedVM: DummyDataViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_all_users, container, false)
        navController = Navigation.findNavController(container!!)

        interfaceCallBack = requireActivity() as? InterfaceForViewModel

        setupRecyclerViews()
        observe()
        sharedVM.getUsersFromRemote()

        return binding.root
    }


    private fun observe() {

        with(sharedVM) {

            getUsersFromRoom.observe(viewLifecycleOwner, {
                if (it.isEmpty()) {
                    binding.rvSavedContacts.visibility = View.GONE
                    binding.noSavedContact.visibility = View.VISIBLE
                } else {
                    localList.clear()
                    it.forEach { _user ->
                        _user.saved = true
                        localList.add(_user)
                    }
                    localAdapter.notifyDataSetChanged()
                }
            })

            getRemoteUsersLiveData().observe(viewLifecycleOwner, {
                when (it.state) {

                    State.LOADING -> binding.pbProgressBar.visibility = View.VISIBLE

                    State.ERROR -> {
                        binding.pbProgressBar.visibility = View.GONE
                        Snackbar.make(
                            binding.rvAllContacts,
                            it.message as String,
                            Snackbar.LENGTH_LONG
                        ).show()
                    }
                    State.SUCCESS -> {
                        binding.pbProgressBar.visibility = View.GONE
                        remoteList.clear()
                        it.data?.forEach { _user ->
                            if (_user.idExistentInList(localList)) _user.saved = true
                            remoteList.add(_user)
                        }
                        remoteAdapter.notifyDataSetChanged()
                    }
                }
            })
        }
    }


    private fun setupRecyclerViews() {
        binding.rvSavedContacts.layoutManager = LinearLayoutManager(
            requireContext(),
            RecyclerView.HORIZONTAL,
            false
        )
        binding.rvSavedContacts.adapter = localAdapter
        binding.rvAllContacts.layoutManager = LinearLayoutManager(
            requireContext(),
            RecyclerView.VERTICAL,
            false
        )
        binding.rvAllContacts.adapter = remoteAdapter
    }

    private fun openUserDetails(user: User) {
        sharedVM.savedUser = user
        navController.navigate(R.id.action_allUsersFragment_to_userDetailsFragment)
    }

}