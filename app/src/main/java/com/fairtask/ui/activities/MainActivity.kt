package com.fairtask.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.fairtask.R
import com.fairtask.viewmodels.DummyDataViewModel
import com.fairtask.viewmodels.InterfaceForViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity(), InterfaceForViewModel {

    private val viewModel by viewModel<DummyDataViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun allFunctions() {
        
    }
}