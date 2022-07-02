package com.vilocorp.appdemo

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import com.microsoft.appcenter.AppCenter
import com.microsoft.appcenter.analytics.Analytics
import com.microsoft.appcenter.crashes.Crashes
import com.vilocorp.appdemo.databinding.ActivityMainBinding
import com.vilocorp.appdemo.demojson.FirebaseItemAdapter
import com.vilocorp.appdemo.objetos.DataProgram
import com.vilocorp.appdemo.objetos.FireBaseItem
import com.vilocorp.appdemo.objetos.FireBaseItems
import okhttp3.*
import java.io.IOException


class MainActivity : AppCompatActivity() {
    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        if (AppCenter.isConfigured()) {
            AppCenter.start(Analytics::class.java)
            AppCenter.start(Crashes::class.java)
        }

        getFireBAseItems()
    }

    private fun fillAdapter(item: FireBaseItems) {
        binding.itemList.apply {
            adapter =
                FirebaseItemAdapter(item.items) { item: FireBaseItem ->
                    if (item.private == "0")
                        Toast.makeText(
                            applicationContext,
                            "Lo sentimos, no hay contenido que mostrar",
                            Toast.LENGTH_SHORT
                        ).show()
                    else getProgramItem(item.programa)
                }
            layoutManager = LinearLayoutManager(context)
        }
    }

    private val client = OkHttpClient()

    private fun getFireBAseItems() {
        val request = Request.Builder()
            .url("https://tiradodev.github.io/appLite/pruebaAzteca.json")
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                Toast.makeText(
                    applicationContext,
                    "Erro al obtener las coleccion",
                    Toast.LENGTH_SHORT
                ).show()
            }

            override fun onResponse(call: Call, response: Response) {
                try {
                    val resp = response.body?.string()
                    val itemType = object : TypeToken<FireBaseItems>() {}.type
                    runOnUiThread { fillAdapter(GsonBuilder().create().fromJson(resp, itemType)) }
                } catch (ex: Exception) {
                    Log.d("ErrorDemo", ex.toString())
                }
            }
        })
    }

    private fun getProgramItem(url: String) {
        val request = Request.Builder()
            .url(url)
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                Toast.makeText(
                    applicationContext,
                    "Erro al obtener las coleccion",
                    Toast.LENGTH_SHORT
                ).show()
            }

            override fun onResponse(call: Call, response: Response) {
                try {
                    val resp = response.body?.string()
                    val itemType = object : TypeToken<DataProgram>() {}.type
                    val programs: DataProgram =
                        GsonBuilder().create().fromJson(resp, itemType)
                    runOnUiThread {
                        val webintent = Intent(applicationContext, WebPage::class.java)
                        webintent.putExtra("url", programs.data[0].url)
                        webintent.putExtra("title", programs.data[0].title)
                        startActivity(webintent)
                    }
                } catch (ex: Exception) {
                    Log.d("ErrorDemo", ex.toString())
                }
            }
        })
    }
}