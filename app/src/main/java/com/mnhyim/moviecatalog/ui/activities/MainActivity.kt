package com.mnhyim.moviecatalog.ui.activities

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.mnhyim.moviecatalog.R
import com.mnhyim.moviecatalog.databinding.ActivityMainBinding
import com.mnhyim.moviecatalog.ui.adapters.SectionsPagerAdapter

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val TAG: String = this::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sectionsPagerAdapter =
            SectionsPagerAdapter(false, this@MainActivity, supportFragmentManager)
        binding.viewPagerHome.adapter = sectionsPagerAdapter
        binding.tabsHome.setupWithViewPager(binding.viewPagerHome)

        supportActionBar?.elevation = 0f
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.option_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_toFavorite -> {
                val mIntent = Intent(this@MainActivity, FavoriteActivity::class.java)
                startActivity(mIntent)
            }
        }

        return super.onOptionsItemSelected(item)
    }
}