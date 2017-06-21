package myapplication.p2p.activity;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import myapplication.p2p.R;
import myapplication.p2p.common.AppManager;
import myapplication.p2p.utils.UIUtils;

public class SplashActivity extends AppCompatActivity {

    @Bind(R.id.iv_welcome_icon)
    ImageView ivWelcomeIcon;
    @Bind(R.id.splash_tv_version)
    TextView splashTvVersion;
    @Bind(R.id.activity_splash)
    RelativeLayout activitySplash;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);
        AppManager.getInstance().addActivity(this);
        initView();
        initData();
        initListener();
    }

    @Override
    protected void onDestroy() {
        AppManager.getInstance().removeActivity(this);
        //AppManager.getInstance().removeActivity(this);
        super.onDestroy();
    }

    private void initListener() {

    }

    private void initData() {
        AlphaAnimation animation = new AlphaAnimation(0, 1);
        animation.setDuration(500);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                if (islogin()){
                    startActivity(new Intent(SplashActivity.this,MainActivity.class));
                }else{
                    startActivity(new Intent(SplashActivity.this,LoginActivity.class));
                }
                ivWelcomeIcon.clearAnimation();
                finish();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        ivWelcomeIcon.startAnimation(animation);
    }

    private boolean islogin() {
        return true;
    }

    private void initView() {
            splashTvVersion.setText(UIUtils.stringFormat(R.string.splash_version,getVersionCode()));
    }

    private String getVersionCode() {
        try {
            PackageInfo packageInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
            int versionCode = packageInfo.versionCode;
            String versionName = packageInfo.versionName;
            return versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return "3";
    }
}
