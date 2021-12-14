package com.kevinyou.groceryapp.activities

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kevinyou.groceryapp.R
import com.kevinyou.groceryapp.model.Product
import com.kevinyou.groceryapp.retrofit.SearchService
import kotlinx.android.synthetic.main.fragment_shop.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ShopFragment : Fragment() {
    private lateinit var callback: MainActivityCallback

    companion object {
        const val TAG : String = "로그"

        fun newInstance(callback: MainActivityCallback) : ShopFragment {
            val shopFragment = ShopFragment()
            shopFragment.setCallback(callback)
            return shopFragment
        }
    }

    // 데이터를 담을 그릇 즉 배열
    private lateinit var myRecyclerAdapter: ShopAdapter
    var wishList = arrayListOf<Product>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    // 메모리에 올라갔을때
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "ShopFragment - onCreate() called")

//        //레트로핏
//        val retrofit = Retrofit.Builder()
//            .baseUrl("https://kevinyou.blob.core.windows.net")
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()
//
//        val service: SearchService = retrofit.create(SearchService::class.java)
//        btn_api.setOnClickListener{
//            service.getRequest().enqueue(object: Callback<List<Product>> {
//                override fun onFailure(call: Call<List<Product>>, t: Throwable?) {
//                }
//                override fun onResponse(call: Call<List<Product>>, response: Response<List<Product>>) {
//                    if (response.isSuccessful) {
//                        println(response.body().toString())
//                        response.body()?.let {
//                            myRecyclerAdapter.submitList(it)}
//                    }
//                }
//            })
//        }
//
//        // 어댑터 인스턴스 생성
//        myRecyclerAdapter = SearchAdapter(App.instance) //context인 this@MainActivity 대신 넣음
//
//        // 리싸이클러 뷰 설정
//        my_recycler_view.apply {
//            // 리싸이클러 뷰 방향 등 설정
//            layoutManager = LinearLayoutManager(
//                App.instance, LinearLayoutManager.VERTICAL, false)
//
//            //어댑터 장착
//            adapter = myRecyclerAdapter
//        }
    }

    // 프레그먼트를 안고 있는 액티비티에 붙었을 때
    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.d(TAG, "ShopFragment - onAttach() called")
    }

    // 뷰가 생성되었을 때
    // 프레그먼트와 레이아웃을 연결시켜주는 부분이다.
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {

        Log.d(TAG, "ShopFragment - onCreateView() called")

        val view = inflater.inflate(R.layout.fragment_shop, container, false)
        //레트로핏
        val retrofit = Retrofit.Builder()
            .baseUrl("https://kevinyht-bucket-source.s3.us-east-2.amazonaws.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service: SearchService = retrofit.create(SearchService::class.java)

        service.getRequest().enqueue(object: Callback<List<Product>> {
            override fun onFailure(call: Call<List<Product>>, t: Throwable?) {
            }
            override fun onResponse(call: Call<List<Product>>, response: Response<List<Product>>) {
                if (response.isSuccessful) {
                    println(response.body().toString())
                    response.body()?.let {
                        myRecyclerAdapter.submitList(it)}
                }
            }
        })

        // 어댑터 인스턴스 생성
        myRecyclerAdapter = ShopAdapter(App.instance, callback) //context인 this@MainActivity 대신 넣음

        // 리싸이클러 뷰 설정
        view.findViewById<RecyclerView>(R.id.my_recycler_view).apply {
            // 리싸이클러 뷰 방향 등 설정
            layoutManager = LinearLayoutManager(
                App.instance, LinearLayoutManager.HORIZONTAL, false)

            //어댑터 장착
            adapter = myRecyclerAdapter
        }

        return view
    }

    private fun setCallback(callback: MainActivityCallback) {
        this.callback = callback
    }
}
