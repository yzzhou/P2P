package myapplication.p2p.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.Bind;
import butterknife.ButterKnife;
import myapplication.p2p.R;
import myapplication.p2p.fragment.HomeFragment;
import myapplication.p2p.fragment.InvestFragment;
import myapplication.p2p.fragment.MoreFragment;
import myapplication.p2p.fragment.PropertyFragment;


public class MainActivity extends AppCompatActivity {
    @Bind(R.id.main_fl)
    FrameLayout mainFl;
    @Bind(R.id.rb_home)
    RadioButton rbHome;
    @Bind(R.id.rb_invest)
    RadioButton rbInvest;
    @Bind(R.id.rb_propert)
    RadioButton rbPropert;
    @Bind(R.id.rb_more)
    RadioButton rbMore;
    @Bind(R.id.main_rg)
    RadioGroup mainRg;
    @Bind(R.id.activity_main)
    LinearLayout activityMain;
    private HomeFragment homeFragment;
    private MoreFragment moreFragment;
    private PropertyFragment propertyFragment;
    private InvestFragment investFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        initdata();
        initView();
        initListener();
    }

    private void initListener() {
        mainRg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switchFragment(checkedId);
            }
        });
    }

    private void switchFragment(int checkedId) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        hedden(ft);
        switch (checkedId){
            case R.id.rb_home:
                if(homeFragment ==null) {
                    homeFragment = new HomeFragment();
                    ft.add(R.id.main_fl,homeFragment);
                }else{
                    ft.show(homeFragment);
                }
                break;
            case R.id.rb_invest:
                if(investFragment ==null) {
                    investFragment = new InvestFragment();
                    ft.add(R.id.main_fl,investFragment);
                }else{
                    ft.show(investFragment);
                }
                break;
            case R.id.rb_more:
                if(moreFragment ==null) {
                    moreFragment = new MoreFragment();
                    ft.add(R.id.main_fl,moreFragment);
                }else{
                    ft.show(moreFragment);
                }
                break;
            case R.id.rb_propert:
                if(propertyFragment ==null) {
                    propertyFragment = new PropertyFragment();
                    ft.add(R.id.main_fl,propertyFragment);
                }else{
                    ft.show(propertyFragment);
                }
                break;
        }
        ft.commit();
    }

    private void hedden(FragmentTransaction ft) {
            if(homeFragment !=null){
                ft.hide(homeFragment);
            }
        if(moreFragment !=null){
            ft.hide(moreFragment);
        }
        if(investFragment !=null){
            ft.hide(investFragment);
        }
        if(propertyFragment !=null){
            ft.hide(propertyFragment);
        }
    }

    private void initView() {
        switchFragment(R.id.rb_home);
    }



    private void initdata() {

    }
    private boolean isExit = false;
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode ==KeyEvent.KEYCODE_BACK){
            if(isExit){
                finish();
            }
            Toast.makeText(MainActivity.this, "再点一次退出", Toast.LENGTH_SHORT).show();
            isExit =true;
            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    isExit = false;
                }
            },2000);
            return  true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
