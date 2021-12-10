package com.judahben149.retrofitcp.view.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.judahben149.retrofitcp.ApiRequests
import com.judahben149.retrofitcp.view.activities.BASE_URL
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.awaitResponse
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.Exception

class MainViewModel: ViewModel() {

    private var TAG = "mineee"
    val catFact = MutableLiveData<String>()
    var isResponseSuccessful = MutableLiveData<Boolean>(true)

    init {
        Log.d(TAG, "Instantiated view model")
    }

    fun getCurrentData() {

        Log.d(TAG, "getting current data")

        val api = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiRequests::class.java)


        viewModelScope.launch(Dispatchers.IO) {

//            withContext(Dispatchers.Main) {
//                isResponseSuccessful.value = true
//            }

            try {
                val response = api.getCatFacts().awaitResponse()

                if (response.isSuccessful) {

                    val data = response.body()!!
                    withContext(Dispatchers.Main) {
                        catFact.value = data.text
                        Log.d(TAG, catFact.value?:"well, null")
                    }
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    isResponseSuccessful.value = false
                }
            }
        }
    }
}