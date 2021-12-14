package com.kevinyou.groceryapp.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.google.gson.Gson
import com.kevinyou.groceryapp.R
import com.kevinyou.groceryapp.model.Product
import com.kevinyou.groceryapp.retrofit.SearchService
import com.kevinyou.groceryapp.utils.Constants.TAG
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_account.*
import kotlinx.android.synthetic.main.fragment_shop.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import androidx.annotation.NonNull
import androidx.fragment.app.Fragment

import androidx.fragment.app.FragmentActivity

import androidx.viewpager2.adapter.FragmentStateAdapter

import com.google.android.material.tabs.TabLayout

import com.google.android.material.tabs.TabLayoutMediator
import com.google.android.material.tabs.TabLayoutMediator.TabConfigurationStrategy


class MainActivity : AppCompatActivity(), MainActivityCallback {

    private var mAdapter: MyAdapter? = null
    // 데이터를 담을 그릇 즉 배열

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //바텀네비게이션
//        bottom_nav.setOnItemSelectedListener(this)
//        shopFragment = ShopFragment.newInstance()
//        supportFragmentManager.beginTransaction().add(R.id.fragments_frame, shopFragment).commit()



        mAdapter = MyAdapter(this)
        pager.adapter = mAdapter
        TabLayoutMediator(
            tab_layout, pager
        ) { tab: TabLayout.Tab, position: Int ->
            if (position == 0) {
                tab.text = "Shop"
                tab.setIcon(R.drawable.ic_baseline_shopping_basket_24)
            } else if (position == 1) {
                tab.text = "Cart"
                tab.setIcon(R.drawable.ic_baseline_shopping_cart_24)
            } else {
                tab.text = "Account"
                tab.setIcon(R.drawable.ic_baseline_account_circle_24)
            }
        }.attach()
    }

    class MyAdapter(private val mainActivity: MainActivity) : FragmentStateAdapter(mainActivity) {
        private lateinit var cartFragment: CartFragment
        override fun createFragment(position: Int): Fragment {
            // Return a NEW fragment instance in createFragment(int)
            return if (position == 0) {
                ShopFragment.newInstance(mainActivity)
            } else if (position == 1) {
                cartFragment = CartFragment()
                cartFragment
            } else {
                AccountFragment()
            }
        }

        override fun getItemCount(): Int {
            return NUM_ITEMS
        }

        fun onProductAdded(product: Product) {
            cartFragment.onProductAdded(product)
        }
    }

    companion object {
        const val NUM_ITEMS = 3
    }

    override fun onProductAdded(product: Product) {
        Log.e("minmin", "Product Item: " + product.product_name)
        mAdapter?.onProductAdded(product)
    }
}

interface MainActivityCallback {
    fun onProductAdded(product: Product)
}

//class MainActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {
//
//    private lateinit var shopFragment: ShopFragment
//    private lateinit var cartFragment: CartFragment
//    private lateinit var accountFragment: AccountFragment
//
//    // 데이터를 담을 그릇 즉 배열
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
//
//        shopFragment = ShopFragment.newInstance()
//        cartFragment = CartFragment.newInstance()
//        accountFragment = AccountFragment.newInstance()
//
//        //바텀네비게이션
//        bottom_nav.setOnItemSelectedListener(this)
//        supportFragmentManager.beginTransaction().add(R.id.fragments_frame, shopFragment).commit()
//    }
//
//    override fun onNavigationItemSelected(item: MenuItem): Boolean {
//        Log.d(TAG, "MainActivity - onNavigationItemReselected() called")
//
//        when(item.itemId) {
//            R.id.menu_shop -> {
//                Log.d(TAG, "MainActivity - 홈버튼 클릭!")
//                supportFragmentManager.beginTransaction()
//                    .replace(R.id.fragments_frame, shopFragment).commit()
//            }
//            R.id.menu_cart -> {
//                Log.d(TAG, "MainActivity - 홈버튼 클릭!")
//                supportFragmentManager.beginTransaction()
//                    .replace(R.id.fragments_frame, cartFragment).commit()
//            }
//            R.id.menu_account -> {
//                Log.d(TAG, "MainActivity - 홈버튼 클릭!")
//                supportFragmentManager.beginTransaction()
//                    .replace(R.id.fragments_frame, accountFragment).commit()
//            }
//        }
//        return true
//    }
//}