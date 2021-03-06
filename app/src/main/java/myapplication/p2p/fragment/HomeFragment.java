package myapplication.p2p.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.squareup.picasso.Picasso;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import myapplication.p2p.R;
import myapplication.p2p.bean.IndexBean;
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
        loadNet();
        initBanner();
    }

    private void initBanner() {
        list.add(AppNetConfig.BASE_URL+"images/index02.png");
        //设置图片加载器
        banner.setImageLoader(new GlideImageLoader());
        //设置图片集合
        banner.setImages(list);
        //banner设置方法全部调用完毕时最后调用
        banner.start();
    }

    private void parseJson(String content) throws JSONException {
        IndexBean bean = new IndexBean();
        List<IndexBean.ImageArrBean> list = new ArrayList<>();
        JSONObject object = new JSONObject(content);
        JSONArray imageArr = object.getJSONArray("imageArr");
        for(int i= 0 ; i< imageArr.length();i++){
            IndexBean.ImageArrBean imageArrBean = new IndexBean.ImageArrBean();
            JSONObject jsonObject = imageArr.getJSONObject(i);
            String id = jsonObject.getString("ID");
            imageArrBean.setID(id);
            String imapaurl = jsonObject.getString("IMAPAURL");
            imageArrBean.setIMAPAURL(imapaurl);
            String imaurl = jsonObject.getString("IMAURL");
            imageArrBean.setIMAURL(imaurl);
            list.add(imageArrBean);
        }
        bean.setImageArr(list);
        JSONObject proInfo = object.getJSONObject("proInfo");
        String name = proInfo.getString("name");
        int id = proInfo.getInt("id");
        Log.d("json", "parseJson: "+name);
    }

    private void loadNet() {
        AsyncHttpClient client = new AsyncHttpClient();
        client.get(AppNetConfig.INDEX,new AsyncHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, String content) {
                super.onSuccess(statusCode, content);
                try {
                    parseJson(content);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                //解析数据
//                IndexBean indexBean = JSON.parseObject(content, IndexBean.class);
//                Log.d("content", "onSuccess: "+indexBean.getProInfo().getName());
            }

            @Override
            public void onFailure(Throwable error, String content) {
                super.onFailure(error, content);
            }
        });
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
