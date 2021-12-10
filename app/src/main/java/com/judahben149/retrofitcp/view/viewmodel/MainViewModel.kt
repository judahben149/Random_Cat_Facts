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
    val minimumNumberOfCharactersReturnedFromAPI = 8


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

                //sometimes really short (one word) uninteresting facts which do not make sense are returned from the API
                //this condition checks the length and ensures it isn't less than the minimum set. if it is, it runs the function again
                //&& response.body()!!.text.length > minimumNumberOfCharactersReturnedFromAPI
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