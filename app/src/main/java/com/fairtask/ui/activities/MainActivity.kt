package com.fairtask.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.fairtask.R
import com.fairtask.viewmodels.DummyDataViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

}