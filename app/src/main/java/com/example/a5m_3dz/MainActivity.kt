package com.example.a5m_3dz

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.a5m_3dz.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var adapter = PixaAdapter(arrayListOf())
    private var page = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initClickers()
    }



    private fun initClickers() {
        with(binding) {
            imageRecycler.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                    super.onScrollStateChanged(recyclerView, newState)
                    if (!recyclerView.canScrollVertically(1)) {
                        binding.doRequest(++page)
                    }
                }
            })

            btnSearchPage.setOnClickListener {
                doRequest(++page)
            }
            btnEnter.setOnClickListener {
                doRequest(page = 1)
            }
        }
    }

    private fun ActivityMainBinding.doRequest(page : Int) {
        PixaService().api.getImages(pictureWord = edSearch.text.toString(), page = page).enqueue(object : retrofit2.Callback<PixaModel>{
            override fun onResponse(call: Call<PixaModel>, response: Response<PixaModel>) {
                if (response.isSuccessful) {
                    adapter.addData(response.body()?.hits!!)
                    imageRecycler.adapter = adapter
                }
            }

            override fun onFailure(call: Call<PixaModel>, t: Throwable) {
                Log.e("ololo", "onFailure: ${t.message}" )
            }

        })
    }
}
