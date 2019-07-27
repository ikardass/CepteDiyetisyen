package com.gelecegiyazanlar.ceptediyetisyen.module;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;
import com.gelecegiyazanlar.ceptediyetisyen.R;
import com.gelecegiyazanlar.ceptediyetisyen.module.login.LoginActivity;
import com.gelecegiyazanlar.ceptediyetisyen.utils.Constants;
import com.gelecegiyazanlar.ceptediyetisyen.utils.PreferenceUtils;


public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(() -> {
            boolean isLogin = new PreferenceUtils(SplashActivity.this).getBoolean(Constants.PREF_IS_LOGIN);
            //isLogin = false;
            if (isLogin) {
                startActivity(new Intent(SplashActivity.this, MainActivity.class));
            } else {
                startActivity(new Intent(SplashActivity.this, LoginActivity.class));
            }
            finish();
        }, 2000);

    }
}
