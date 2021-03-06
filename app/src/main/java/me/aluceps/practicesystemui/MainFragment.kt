package me.aluceps.practicesystemui

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import me.aluceps.practicesystemui.databinding.FragmentMainBinding

class MainFragment : Fragment() {

    private lateinit var binding: FragmentMainBinding

    private val hideSystemUi by lazy {
        View.SYSTEM_UI_FLAG_IMMERSIVE +
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE +
                View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION +
                View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN +
                View.SYSTEM_UI_FLAG_HIDE_NAVIGATION +
                View.SYSTEM_UI_FLAG_FULLSCREEN
    }

    private val showSystemUi by lazy {
        View.SYSTEM_UI_FLAG_LAYOUT_STABLE +
                View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION +
                View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeSystemUi()
        binding.button.setOnClickListener {
            toggleSystemUi()
        }
    }

    private fun toggleSystemUi() {
        activity?.window?.decorView?.run {
            when (systemUiVisibility == showSystemUi) {
                true -> hideSystemUi()
                else -> showSystemUi()
            }
        }
    }

    private fun initializeSystemUi() {
        activity?.window?.decorView?.systemUiVisibility = showSystemUi
    }

    private fun hideSystemUi() {
        activity?.run {
            (this as MainActivity).supportActionBar?.hide()
            window?.decorView?.systemUiVisibility = hideSystemUi
            setBottomBarMargin(0)
        }
    }

    private fun showSystemUi() {
        activity?.run {
            (this as MainActivity).supportActionBar?.show()
            window?.decorView?.systemUiVisibility = showSystemUi
            resources?.getDimension(R.dimen.navigationbar_size)?.let {
                setBottomBarMargin(it.toInt())
            }
        }
    }

    companion object {
        fun newInstance(): Fragment = MainFragment()
    }
}