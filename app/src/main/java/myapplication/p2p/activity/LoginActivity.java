package myapplication.p2p.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import myapplication.p2p.R;
import myapplication.p2p.common.AppManager;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        AppManager.getInstance().addActivity(this);
    }

    @Override
    protected void onDestroy() {
        AppManager.getInstance().removeActivity(this);
        super.onDestroy();
    }
}
