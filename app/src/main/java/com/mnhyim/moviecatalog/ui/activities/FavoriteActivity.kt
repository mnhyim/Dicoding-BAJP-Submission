package com.mnhyim.moviecatalog.ui.activities

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.mnhyim.moviecatalog.R
import com.mnhyim.moviecatalog.databinding.ActivityFavoriteBinding
import com.mnhyim.moviecatalog.ui.adapters.SectionsPagerAdapter

class FavoriteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFavoriteBinding
    private val TAG: String = this::class.java.simpleName


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        title = getText(R.string.label_favorite)

        val sectionsPagerAdapter =
            SectionsPagerAdapter(true, this@FavoriteActivity, supportFragmentManager)
        binding.viewPagerFavorite.adapter = sectionsPagerAdapter
        binding.tabsFavorite.setupWithViewPager(binding.viewPagerFavorite)

        supportActionBar?.elevation = 0f
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}