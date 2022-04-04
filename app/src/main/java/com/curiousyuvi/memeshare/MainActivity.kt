package com.curiousyuvi.memeshare

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        loadMeme()
    }

    private val url="https://meme-api.herokuapp.com/gimme"

    private fun loadMeme() {

        val queue = Volley.newRequestQueue(this)

        val jsonObjectRequest = JsonObjectRequest(Request.Method.GET, url,null,
            { response ->
                val memeUrl=response.getString("url")
                memeTitle.text=response.getString("title")
                Glide.with(this)
                    .load(memeUrl)
                    .into(memeImage)
            },
            {
                Toast.makeText(this,"error",Toast.LENGTH_LONG).show()

            })

// Add the request to the RequestQueue.
        queue.add(jsonObjectRequest)
    }

    fun nextMeme(view: View) {
        loadMeme()
    }
    fun shareMeme(view: View) {}


}