package com.judahben149.retrofitcp.view.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.ActionBar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import com.judahben149.retrofitcp.R
import com.judahben149.retrofitcp.databinding.ActivityMainBinding
import com.judahben149.retrofitcp.view.viewmodel.MainViewModel


const val BASE_URL = "https://cat-fact.herokuapp.com"

class MainActivity : AppCompatActivity() {

    private var TAG = "mineee"
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //set up custom action bar
        supportActionBar!!.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM)
        supportActionBar!!.setDisplayShowCustomEnabled(true)
        supportActionBar!!.setCustomView(R.layout.custom_action_bar)

        //instantiate viewmodel
        val viewModel = ViewModelProvider(this).get(MainViewModel::class.java)


        viewModel.getCurrentData()

        binding.layoutGenerateNewFact.setOnClickListener {
            binding.tvCatFact.visibility = View.GONE
            binding.progressBar.visibility = View.VISIBLE
            viewModel.getCurrentData()
        }

        viewModel.catFact.observe(this, Observer {
            binding.progressBar.visibility = View.GONE
            binding.tvCatFact.visibility = View.VISIBLE
            binding.tvCatFact.text = it
        })

        viewModel.isResponseSuccessful.observe(this, Observer {
            if (it == false) {
                Snackbar.make(binding.root, "An error occurred", Snackbar.LENGTH_LONG).show()
            }
        })

    }


}