package wanghuiqi.bawie.com.wanghuiqi20181026.activity.activity.fragment;


import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bwie.xlistview.XListView;
import com.google.gson.Gson;
import com.nostra13.universalimageloader.core.DisplayImageOptions;

import java.util.ArrayList;
import java.util.List;

import wanghuiqi.bawie.com.wanghuiqi20181026.R;
import wanghuiqi.bawie.com.wanghuiqi20181026.activity.activity.adapter.JiMoviesAdapter;
import wanghuiqi.bawie.com.wanghuiqi20181026.activity.activity.bean.MoviesBean;
import wanghuiqi.bawie.com.wanghuiqi20181026.activity.activity.utils.HttpUtils;


public class JiMoviesFragment extends Fragment {

    private XListView xListView;
    private String Url = "http://172.17.8.100/movieApi/movie/v1/findReleaseMovieList?count=5&page=";
    private int page = 1;
    private List<MoviesBean.ResultBean> mlist;
    private JiMoviesAdapter jiMoviesAdapter;
    private Handler handler = new Handler();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = View.inflate(getActivity(), R.layout.fragment_ji_movies, null);
        //获取控件id
        xListView = view.findViewById(R.id.xlist);

        mlist = new ArrayList<>();
        //适配器
        jiMoviesAdapter = new JiMoviesAdapter(getActivity(), mlist);
        xListView.setAdapter(jiMoviesAdapter);

        //设置图片缓存
        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .cacheOnDisk(true)//sd卡缓存
                .cacheInMemory(true)//内存缓存
                .bitmapConfig(Bitmap.Config.RGB_565)//色彩
                .build();

        xListView.setPullLoadEnable(true);
        xListView.setPullRefreshEnable(true);
        getDataUtil(1, false);

        xListView.setXListViewListener(new XListView.IXListViewListener() {
            @Override
            public void onRefresh() {
                page = 1;
                getDataUtil(page, false);
                Toast.makeText(getActivity(),"拉到头了",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLoadMore() {
                page++;
                getDataUtil(page, true);
            }
        });
        return view;
    }

    @SuppressLint("StaticFieldLeak")
    private void getDataUtil(int i, final boolean b) {
        new AsyncTask<String, Integer, String>() {
            @Override
            protected String doInBackground(String... strings) {
                return HttpUtils.getFromString(strings[0]);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                Gson gson = new Gson();
                MoviesBean moviesBean = gson.fromJson(s, MoviesBean.class);
                if (moviesBean != null) {
                    List<MoviesBean.ResultBean> resultb = moviesBean.getResult();
                    if (resultb != null) {
                        mlist.clear();
                        mlist.addAll(resultb);
                        jiMoviesAdapter.notifyDataSetChanged();
                    }

                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            if (b) {
                                xListView.stopLoadMore();
                            } else {
                                xListView.stopRefresh();
                                xListView.setRefreshTime("刚刚");
                            }
                        }
                    }, 2000);
                }
            }
        }.execute(Url + page);
    }

}
