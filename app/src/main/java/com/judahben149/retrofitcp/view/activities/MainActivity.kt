package com.judahben149.retrofitcp.view.activities

import android.app.Dialog
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.pm.ActivityInfo
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.Window
import android.widget.Button
import androidx.appcompat.app.ActionBar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import com.judahben149.retrofitcp.R
import com.judahben149.retrofitcp.databinding.ActivityMainBinding
import com.judahben149.retrofitcp.view.viewmodel.MainViewModel


class MainActivity : AppCompatActivity() {

    private var TAG = "mineee"
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel

    private lateinit var lastFacts: Array<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
        setContentView(binding.root)

        //set up custom action bar
        supportActionBar!!.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM)
        supportActionBar!!.setDisplayShowCustomEnabled(true)
        supportActionBar!!.setCustomView(R.layout.custom_action_bar)


        //This array holds two elements; last fact and current fact. Current fact is pushed onto the position of last fact whenever a new fact is gotten
        lastFacts = arrayOf("", "Click for new fact")

        //instantiate viewmodel
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        getNewFact()
        setupObservers()

        binding.layoutGenerateNewFact.setOnClickListener {
            getNewFact()
        }

        binding.btnSeeLastFact.setOnClickListener {
            setLastFact()
        }

        binding.btnCopyFact.setOnClickListener {
            copyFact()
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_action_bar, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
                R.id.menu_tips -> {
                    showDialog()
                    true
                }

            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun showDialog() {
        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)
        dialog.setCanceledOnTouchOutside(true)
        dialog.setContentView(R.layout.custom_dialog_box)
        val yesBtn = dialog.findViewById(R.id.closeInfoDialog) as Button

        yesBtn.setOnClickListener {
            dialog.dismiss()
        }
        dialog.show()
    }

    private fun setupObservers() {
        viewModel.catFact.observe(this, Observer {
            binding.progressBar.visibility = View.GONE
            binding.tvCatFact.visibility = View.VISIBLE
            binding.tvCatFact.text = it

            lastFacts[0] = lastFacts[1]
            lastFacts[1] = it
        })

        viewModel.isResponseSuccessful.observe(this, Observer {
            if (it == false) {
                Snackbar.make(binding.root, getString(R.string.an_error_occured), Snackbar.LENGTH_LONG).show()
                binding.tvCatFact.text = getString(R.string.click_to_retry)
            }
        })
    }


    private fun getNewFact() {
        binding.tvCatFact.visibility = View.GONE
        binding.progressBar.visibility = View.VISIBLE
        viewModel.getCurrentData()
    }

    private fun copyFact() {
        val copiedFact = binding.tvCatFact.text

        val clipboardManager = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val clipData = ClipData.newPlainText("text", copiedFact)
        clipboardManager.setPrimaryClip(clipData)

        Snackbar.make(binding.root, "Fact copied!", Snackbar.LENGTH_SHORT).show()
    }


    private fun setLastFact() {
        binding.progressBar.visibility = View.GONE
        binding.tvCatFact.visibility = View.VISIBLE
        Log.d(TAG, lastFacts[0])
        binding.tvCatFact.text = lastFacts[0]
    }


//    private fun checkForInternet(context: Context): Boolean {
//
//        // register activity with the connectivity manager service
//        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
//
//        // if the android version is equal to M
//        // or greater we need to use the
//        // NetworkCapabilities to check what type of
//        // network has the internet connection
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//
//            // Returns a Network object corresponding to
//            // the currently active default data network.
//            val network = connectivityManager.activeNetwork ?: return false
//
//            // Representation of the capabilities of an active network.
//            val activeNetwork = connectivityManager.getNetworkCapabilities(network) ?: return false
//
//            return when {
//                // Indicates this network uses a Wi-Fi transport,
//                // or WiFi has network connectivity
//                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
//
//                // Indicates this network uses a Cellular transport. or
//                // Cellular has network connectivity
//                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
//
//                // else return false
//                else -> false
//            }
//        } else {
//            // if the android version is below M
//            @Suppress("DEPRECATION") val networkInfo =
//                connectivityManager.activeNetworkInfo ?: return false
//            @Suppress("DEPRECATION")
//            return networkInfo.isConnected
//        }
//    }
}
