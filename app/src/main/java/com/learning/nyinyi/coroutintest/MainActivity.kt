package com.learning.nyinyi.coroutintest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.learning.nyinyi.coroutintest.model.Data
import com.learning.nyinyi.coroutintest.network.RetrofitFactory
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.car_information_detail.*
import kotlinx.coroutines.*
import retrofit2.HttpException
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val service = RetrofitFactory.makeRetrofitService()

        btnShowInfo.setOnClickListener {
            if (edQuery.text.isNullOrEmpty()) {
                toast("Enter your car number!!!")
            }else {

                progressBar.visibility = View.VISIBLE
                CoroutineScope(Dispatchers.IO).launch {
                    val response = service.getCarNumberData("😏😒😏😒😏😒😏😒",edQuery.text.toString())
                    withContext(Dispatchers.Main) {
                        try {
                            if (response.isSuccessful) {
                                response.body()?.let {
                                    it.data.let { data ->
                                        bindOwnerData(data)
                                    }

                                }
                            }else {
                                toast("Error : ${response.message()}")
                            }
                        } catch (e: HttpException) {
                            toast("Exception ${e.message}")
                        } catch (e: Throwable) {
                            toast("Ooops: Something else went wrong")
                        }

                        progressBar.visibility = View.GONE

                    }
                }
            }
        }

    }

    private fun bindOwnerData(data : Data) {
        regNoData.text = data.REG_NO
        modelData.text = data.MAKE_MODEL
        typeData.text = data.TYPE
        colorData.text = data.COLOUR
        enginNumberData.text = data.ENGINE_NO
        deData.text = data.D_E
        ownerData.text = data.OWNER
        nameData.text = data.NAME
        houseNoData.text = data.HOUSE_NO
        rtStData.text = data.RD_ST
        qtrData.text = data.QTR
        townshipData.text = data.TSP
        locaitonData.text = data.LOCATION
    }

    private fun toast(msg: String)   {
        Toast.makeText(this,msg,Toast.LENGTH_LONG).show()
    }
}
