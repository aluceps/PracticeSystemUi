package me.aluceps.practicesystemui

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.ViewGroup
import me.aluceps.practicesystemui.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setSupportActionBar(binding.toolbar)
        supportFragmentManager.beginTransaction()
                .replace(binding.container.id, MainFragment.newInstance())
                .commitNow()
    }

    fun setBottomBarMargin(bottomMargin: Int) {
        (binding.bottomBar.layoutParams as? ViewGroup.MarginLayoutParams)?.let {
            it.bottomMargin = bottomMargin
        }
    }
}
