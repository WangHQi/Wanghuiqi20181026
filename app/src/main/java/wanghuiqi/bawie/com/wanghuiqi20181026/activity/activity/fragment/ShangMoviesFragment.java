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
import android.widget.Toast;

import com.bwie.xlistview.XListView;
import com.google.gson.Gson;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import wanghuiqi.bawie.com.wanghuiqi20181026.R;
import wanghuiqi.bawie.com.wanghuiqi20181026.activity.activity.adapter.ShangMoviesAdapter;
import wanghuiqi.bawie.com.wanghuiqi20181026.activity.activity.bean.Movies;
import wanghuiqi.bawie.com.wanghuiqi20181026.activity.activity.utils.HttpUtils;

public class ShangMoviesFragment extends Fragment {

    private XListView xlv;
    private List<Movies.ResultBean> list;
    private ShangMoviesAdapter shangMoviesAdapter;
    private int page = 1;
    private String url = "http://172.17.8.100/movieApi/movie/v1/findHotMovieList?count=3&page=";
    private Handler handler = new Handler();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = View.inflate(getActivity(), R.layout.fragment_shang_movies, null);

        //获取控件id
        xlv = view.findViewById(R.id.xlv);

        list = new ArrayList<>();
        //适配器
        shangMoviesAdapter = new ShangMoviesAdapter(getActivity(), list);
        xlv.setAdapter(shangMoviesAdapter);

        //设置图片缓存
        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .cacheOnDisk(true)//sd卡缓存
                .cacheInMemory(true)//内存缓存
                .bitmapConfig(Bitmap.Config.RGB_565)//色彩
                .build();

        //刷新  加载
        xlv.setPullRefreshEnable(true);
        xlv.setPullLoadEnable(true);

        getDataUtils(1, false);

        //xlv设置监听
        xlv.setXListViewListener(new XListView.IXListViewListener() {
            @Override
            public void onRefresh() {
                page = 1;
                getDataUtils(page, false);
                Toast.makeText(getActivity(),"拉到头了",Toast.LENGTH_SHORT).show();
        }

            @Override
            public void onLoadMore() {
                page++;
                getDataUtils(page, true);
                Toast.makeText(getActivity(),"没有数据",Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }

    @SuppressLint("StaticFieldLeak")
    private void getDataUtils(int i, final boolean b) {
        new AsyncTask<String, Integer, String>() {
            @Override
            protected String doInBackground(String... strings) {
                return HttpUtils.getFromString(strings[0]);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                Gson gson = new Gson();
                Movies movies = gson.fromJson(s, Movies.class);
                if (movies != null) {
                    List<Movies.ResultBean> result = movies.getResult();
                    if (result != null) {
                        list.clear();
                        list.addAll(result);
                        shangMoviesAdapter.notifyDataSetChanged();
                    }

                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            if (b) {
                                xlv.stopLoadMore();
                            } else {
                                xlv.stopRefresh();
                                xlv.setRefreshTime("刚刚");
                            }
                        }
                    }, 2000);
                }
            }
        }.execute(url + page);
    }

}
