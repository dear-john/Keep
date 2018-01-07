package com.spring_ballet.keep;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.spring_ballet.keep.CommonUtils.ApiList;
import com.spring_ballet.keep.CommonUtils.IntentUtil;
import com.spring_ballet.keep.databinding.ActivitySplashBinding;

public class SplashActivity extends AppCompatActivity {

    private boolean isInMain = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final ActivitySplashBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_splash);
        binding.ivDeflault.setImageResource(R.drawable.splash_default);
        Glide.with(this)
                .setDefaultRequestOptions(new RequestOptions()
                        .error(R.drawable.splash_default))
                .load(ApiList.BingDayPic)
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
                toMainActivity();
            }
        }, 3500);
        binding.tvJump.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toMainActivity();
            }
        });
    }

    private void toMainActivity() {
        if (isInMain)
            return;
        IntentUtil.startIntent(SplashActivity.this, MainActivity.class);
        finish();
        isInMain = true;
    }
}
