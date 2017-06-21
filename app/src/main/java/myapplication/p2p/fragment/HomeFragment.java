package myapplication.p2p.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import myapplication.p2p.R;
import myapplication.p2p.common.AppNetConfig;
import myapplication.p2p.utils.UIUtils;

/**
 * Created by zhouzhou on 2017/6/20.
 */

public class HomeFragment extends Fragment {
    @Bind(R.id.base_title)
    TextView baseTitle;
    @Bind(R.id.base_back)
    ImageView baseBack;
    @Bind(R.id.base_setting)
    ImageView baseSetting;
    @Bind(R.id.banner)
    Banner banner;
    @Bind(R.id.tv_home_product)
    TextView tvHomeProduct;
    @Bind(R.id.tv_home_yearrate)
    TextView tvHomeYearrate;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = UIUtils.Infelat(R.layout.fragment_home);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        initData();
        initListance();
    }

    private void initListance() {

    }
    private List<String> list = new ArrayList<>();
    private void initData() {
        list.add(AppNetConfig.BASE_URL+"images/index02.png");
        banner.setImageLoader( new GlideImageLoader());
        banner.setImages(list);
        banner.start();
    }

    private void initView() {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }



    public class GlideImageLoader extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {

            //Picasso 加载图片简单用法
            Picasso.with(context).load((String) path).into(imageView);
        }
    }
}
