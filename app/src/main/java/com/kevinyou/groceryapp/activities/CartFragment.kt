package com.kevinyou.groceryapp.activities

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.kevinyou.groceryapp.R
import com.kevinyou.groceryapp.model.Product
import kotlinx.android.synthetic.main.fragment_shop.*

class CartFragment : Fragment() {

    companion object {
        const val TAG : String = "로그"

        fun newInstance() : CartFragment {
            return CartFragment()
        }
    }

    // 데이터를 담을 그릇 즉 배열
    private lateinit var myRecyclerAdapter: ShopAdapter

    // 메모리에 올라갔을때
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "CartFragment - onCreate() called")
    }

    // 프레그먼트를 안고 있는 액티비티에 붙었을 때
    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.d(TAG, "CartFragment - onAttach() called")
    }

    // 뷰가 생성되었을 때
    // 프레그먼트와 레이아웃을 연결시켜주는 부분이다.
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {

        Log.d(TAG, "CartFragment - onCreateView() called")


        val view = inflater.inflate(R.layout.fragment_cart, container, false)

        return view
    }

    fun onProductAdded(product: Product) {
//        recyclerViewAdapter.add(product)
    }
}
