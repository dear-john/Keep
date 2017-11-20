package com.spring_ballet.keep;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.spring_ballet.keep.CommonUtils.IntentUtil;
import com.spring_ballet.keep.databinding.ActivitySplashBinding;

public class SplashActivity extends AppCompatActivity {

    boolean isInMain = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final ActivitySplashBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_splash);
        binding.ivDeflault.setImageResource(R.drawable.splash_default);
        Glide.with(SplashActivity.this)
                .setDefaultRequestOptions(new RequestOptions()
                        .error(R.drawable.splash_default)
                        .placeholder(R.drawable.splash_default))
                .load("http://api.dujin.org/bing/1366.php")
                .into(binding.ivSplash);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                binding.ivDeflault.setVisibility(View.GONE);
            }
        }, 1500);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                isInMain = true;
                IntentUtil.startIntent(SplashActivity.this, MainActivity.class);
                finish();
            }
        }, 3500);
        binding.tvJump.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isInMain)
                    return;
                isInMain = true;
                IntentUtil.startIntent(SplashActivity.this, MainActivity.class);
                finish();
            }
        });
    }
}
