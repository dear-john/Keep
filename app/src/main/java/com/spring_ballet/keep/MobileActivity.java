package com.spring_ballet.keep;

import android.animation.ObjectAnimator;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.spring_ballet.keep.CommonUtils.ToastUtil;
import com.spring_ballet.keep.base.BaseActivity;
import com.spring_ballet.keep.databinding.ActivityMobileBinding;

public class MobileActivity extends BaseActivity<ActivityMobileBinding> {

    private String url;
    private String name;
    private String typeName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding.ivMovieMobileBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        Intent intent = getIntent();
        String type = intent.getStringExtra("data1");
        url = getIntent().getStringExtra("data2");
        name = getIntent().getStringExtra("data3");
        if (type.equals("MOVIE")) {
            typeName = "电影";
            binding.tvMovieMobileName.setText(String.format("%s - 电影 - 豆瓣 ", name));
        } else if (type.equals("BOOK")) {
            typeName = "图书";
            binding.tvMovieMobileName.setText(String.format("%s - 图书 - 豆瓣 ", name));
        }
        ObjectAnimator animator = ObjectAnimator.ofFloat(binding.tvMovieMobileName, "translationX", 500f, 0f);
        animator.setDuration(5000);
        animator.start();
        binding.pbMovieMobile.setMax(100);
        binding.webViewMovieMobile.loadUrl(url);
        binding.webViewMovieMobile.setWebViewClient(new WebViewClient());
        binding.webViewMovieMobile.getSettings().setJavaScriptEnabled(true);
        binding.webViewMovieMobile.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                if (newProgress < 100) {
                    binding.pbMovieMobile.setVisibility(View.VISIBLE);
                    binding.pbMovieMobile.setProgress(newProgress);
                } else if (newProgress >= 100) {
                    binding.pbMovieMobile.setVisibility(View.GONE);
                }
            }
        });
    }

    @Override
    protected Toolbar getToolBar() {
        return binding.toolbarMovieMobile;
    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_mobile;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mobile_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item_share:
                Intent shareIntent = new Intent();
                shareIntent.setAction(Intent.ACTION_SEND);
                String shareData = name + " - " + typeName + " - 豆瓣 " + url + "（分享自" + getString(R.string.app_name) + "）";
                shareIntent.putExtra(Intent.EXTRA_TEXT, shareData);
                shareIntent.setType("text/plain");
                if (shareIntent.resolveActivity(getPackageManager()) != null) {
                    startActivity(Intent.createChooser(shareIntent, "分享到"));
                }
                break;
            case R.id.item_copy:
                ClipboardManager clipboardManager = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clipData = ClipData.newPlainText("label", url);
                if (clipboardManager != null) {
                    clipboardManager.setPrimaryClip(clipData);
                    ToastUtil.showToast(MobileActivity.this, "复制成功");
                }
                break;
            case R.id.item_open:
                Intent openIntent = new Intent(Intent.ACTION_VIEW);
                openIntent.setData(Uri.parse(url));
                if (openIntent.resolveActivity(getPackageManager()) != null) {
                    startActivity(Intent.createChooser(openIntent, "使用以下方式打开"));
                }
                break;
            default:
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && binding.webViewMovieMobile.canGoBack()) {
            binding.webViewMovieMobile.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
