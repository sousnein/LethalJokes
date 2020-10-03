package com.sousnein.lethaljokes

import WebViewFragment
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        APP_ACTIVITY = this
        supportFragmentManager
            .beginTransaction()
            .replace(container.id, JokeFragment())
            .commit()

        bottomNav.setOnNavigationItemSelectedListener {
            var fragment: Fragment? = null
            fragment = when (it.itemId) {
                R.id.item_joke -> JokeFragment()
                else -> WebViewFragment()
            }
            supportFragmentManager.beginTransaction()
                .replace(container.id, fragment!!)
                .commit()
            true
        }
    }
}