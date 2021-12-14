package com.kevinyou.groceryapp.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.kevinyou.groceryapp.R
import kotlinx.android.synthetic.main.fragment_account.*

class AccountFragment : Fragment() {

    companion object {
        const val TAG : String = "로그"

        fun newInstance() : AccountFragment {
            return AccountFragment()
        }
    }



    // 메모리에 올라갔을때
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "AccountFragment - onCreate() called")

    }

    // 프레그먼트를 안고 있는 액티비티에 붙었을 때
    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.d(TAG, "AccountFragment - onAttach() called")
    }

    // 뷰가 생성되었을 때
    // 프레그먼트와 레이아웃을 연결시켜주는 부분이다.
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {

        Log.d(TAG, "AccountFragment - onCreateView() called")

        val view = inflater.inflate(R.layout.fragment_account, container, false)
        view.findViewById<Button>(R.id.btn_signOut).setOnClickListener {signOut()}

        return view
    }

    private fun signOut() { // 로그아웃

        val gso = GoogleSignInOptions
            .Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        val googleSignInClient = GoogleSignIn.getClient(requireActivity(), gso)
        // Firebase sign out
        Firebase.auth.signOut()

        // Google sign out

        googleSignInClient.signOut().addOnCompleteListener(requireActivity()) {
            startActivity(Intent(requireActivity(), LoginActivity::class.java))

        }
    }
}
