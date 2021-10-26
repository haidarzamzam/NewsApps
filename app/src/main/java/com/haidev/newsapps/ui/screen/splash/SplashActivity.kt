package com.haidev.newsapps.ui.screen.splash

import android.content.Intent
import android.os.Bundle
import com.haidev.newsapps.R
import com.haidev.newsapps.databinding.ActivitySplashBinding
import com.haidev.newsapps.ui.base.BaseActivity
import com.haidev.newsapps.ui.screen.news.NewsActivity
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class SplashActivity : BaseActivity<ActivitySplashBinding, SplashViewModel>(),
    SplashNavigator {
    private val splashViewModel: SplashViewModel by inject()
    private var _binding: ActivitySplashBinding? = null
    private val binding get() = _binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = getViewDataBinding()
        binding?.lifecycleOwner = this
        splashViewModel.navigator = this
    }

    override fun setLayout() = R.layout.activity_splash

    override fun getViewModels() = splashViewModel

    override fun onResume() {
        super.onResume()
        launch {
            splashViewModel.displaySplashAsync().await()
        }
    }

    override fun onReadyAction() {}

    override fun navigateToNews() {
        val intent = Intent(this@SplashActivity, NewsActivity::class.java)
        startActivity(intent)
        finish()
    }
}