package wanghuiqi.bawie.com.wanghuiqi20181026.activity.activity.fragment;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import wanghuiqi.bawie.com.wanghuiqi20181026.R;
import wanghuiqi.bawie.com.wanghuiqi20181026.activity.activity.adapter.MoviesFragmentAdapter;

/**
 * 影片Fragment
 */
public class YingPianFragment extends Fragment {

    private TabLayout tab;
    private ViewPager yingPager;
    private ArrayList<Fragment> fragments;
    private MoviesFragmentAdapter moviesFragmentAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=View.inflate(getActivity(),R.layout.fragment_ying_pian,null);
        //获取控件id
        tab = view.findViewById(R.id.tab);
        yingPager = view.findViewById(R.id.ying_pager);

        //TabLayout  Fragment
        initTab();
        return view;
    }

    private void initTab() {
        fragments = new ArrayList<>();
        fragments.add(new HotMoviesFragment());
        fragments.add(new ShangMoviesFragment());
        fragments.add(new JiMoviesFragment());

        moviesFragmentAdapter = new MoviesFragmentAdapter(getChildFragmentManager());
        moviesFragmentAdapter.setFragments(fragments);
        yingPager.setAdapter(moviesFragmentAdapter);

        //Tab指示器有几个就创建几个
        tab.addTab(tab.newTab());
        tab.addTab(tab.newTab());
        tab.addTab(tab.newTab());

        tab.setupWithViewPager(yingPager);

        //设置文字
        tab.getTabAt(0).setText("热门影片");
        tab.getTabAt(1).setText("正在上映");
        tab.getTabAt(2).setText("即将上映");
    }

}
