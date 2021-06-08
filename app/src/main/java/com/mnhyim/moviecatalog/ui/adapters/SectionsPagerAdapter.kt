package com.mnhyim.moviecatalog.ui.adapters

import android.content.Context
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.mnhyim.moviecatalog.R
import com.mnhyim.moviecatalog.ui.fragments.MoviesFragment
import com.mnhyim.moviecatalog.ui.fragments.ShowsFragment

class SectionsPagerAdapter(
    private val favoriteScreen: Boolean,
    private val mContext: Context,
    fm: FragmentManager
) :
    FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    companion object {
        @StringRes
        private val TAB_TITLES = intArrayOf(R.string.label_movies, R.string.label_tvShow)
    }

    override fun getItem(position: Int): Fragment =
        when (position) {
            0 -> {
                if (!favoriteScreen) {
                    MoviesFragment(favoriteScreen = false)
                } else {
                    MoviesFragment(favoriteScreen = true)
                }
            }
            1 -> if (!favoriteScreen) {
                ShowsFragment(favoriteScreen = false)
            } else {
                ShowsFragment(favoriteScreen = true)
            }
            else -> Fragment()
        }

    override fun getPageTitle(position: Int): CharSequence =
        mContext.resources.getString(TAB_TITLES[position])

    override fun getCount(): Int = 2
}