package com.sousnein.lethaljokes

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_joke.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class JokeFragment : Fragment() {
    lateinit var recyclerView: RecyclerView
    lateinit var recyclerAdapter: JokeAdapter
    var btnClicked = false
    var staseRestored = false
    var BASE_URL = "http://api.icndb.com/jokes/random/"
    var restoreResponse: String? = ""
    private var apiUrl = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_joke, container, false)
        if (savedInstanceState != null) {

            btnClicked = savedInstanceState!!.getBoolean("MyBoolean")
            restoreResponse = savedInstanceState.getString("MyString")
            if (!restoreResponse.isNullOrEmpty())
                recyclerAdapter.setJokeListItems(restoreResponse!!)
        }
        this.retainInstance = true;
        // Inflate the layout for this fragment
        return view
    }


    override fun onResume() {
        super.onResume()
        recyclerView = recycler_view
        recyclerAdapter = JokeAdapter(APP_ACTIVITY)
        recyclerView.layoutManager = LinearLayoutManager(APP_ACTIVITY)
        recyclerView.adapter = recyclerAdapter

        reload_btn.setOnClickListener {
            if (!input.text.isNullOrEmpty()) {
                val bundle = Bundle()
                hideKeyboard()
                apiUrl = BASE_URL + input.text.toString() + "/"
                bundle.putString("URL", apiUrl)
                setFragmentResult("requestKey", bundleOf("URL" to apiUrl))
                val apiInterface = ApiInterface.create(apiUrl).getJoke()
                apiInterface.enqueue(object : Callback<String> {

                    override fun onResponse(
                        call: Call<String>?,
                        response: Response<String>?
                    ) {
                        restoreResponse = response?.body()
                        Log.d("response", response?.body().toString())
                        if (response?.body() != null) {
                            if (btnClicked) {
                                btnClicked = false
                                recyclerAdapter.cleanJokes()
                            }
                            btnClicked = true
                            recyclerAdapter.setJokeListItems(response.body()!!)

                        }
                    }

                    override fun onFailure(call: Call<String>?, t: Throwable?) {
                        Toast.makeText(APP_ACTIVITY, "myBad", Toast.LENGTH_SHORT).show()
                    }
                })
            } else {
                Toast.makeText(APP_ACTIVITY, "Без шуток не уйдёшь", Toast.LENGTH_SHORT).show()
            }
        }
    }


    override fun onSaveInstanceState(outState: Bundle) {
        outState.putBoolean("MyBoolean", btnClicked);
        outState.putString("MyString", restoreResponse);
        super.onSaveInstanceState(outState)

//        recyclerAdapter.cleanJokes()
    }

}