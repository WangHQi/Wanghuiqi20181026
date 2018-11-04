package wanghuiqi.bawie.com.wanghuiqi20181026.activity.activity;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;

import wanghuiqi.bawie.com.wanghuiqi20181026.R;
import wanghuiqi.bawie.com.wanghuiqi20181026.activity.activity.adapter.MyFragmentAdapter;
import wanghuiqi.bawie.com.wanghuiqi20181026.activity.activity.fragment.HuiYuanFragment;
import wanghuiqi.bawie.com.wanghuiqi20181026.activity.activity.fragment.SheZhiFragment;
import wanghuiqi.bawie.com.wanghuiqi20181026.activity.activity.fragment.YingPianFragment;
import wanghuiqi.bawie.com.wanghuiqi20181026.activity.activity.fragment.YingYuanFragment;

public class TwoActivity extends AppCompatActivity implements View.OnClickListener {

    private static DrawerLayout draw;
    private ViewPager pager;
    private ImageView yingpian, yingyuan, huiyuan, shezhi;
    private MyFragmentAdapter myFragmentAdapter;
    private ArrayList<Fragment> fragments;
    private ActionBarDrawerToggle toggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two);
        //初始化控件
        initView();

        //fragment集合
        fragments = new ArrayList<>();
        //存到集合中
        fragments.add(new YingPianFragment());
        fragments.add(new YingYuanFragment());
        fragments.add(new HuiYuanFragment());
        fragments.add(new SheZhiFragment());

        myFragmentAdapter = new MyFragmentAdapter(getSupportFragmentManager());
        myFragmentAdapter.setFragments(fragments);
        pager.setAdapter(myFragmentAdapter);


        //pager设置监听,滑动相应的fragment
        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                switch (i){
                    case 0:
                        yingpian.setImageResource(R.drawable.ac1);
                        yingyuan.setImageResource(R.drawable.abw);
                        huiyuan.setImageResource(R.drawable.ac2);
                        shezhi.setImageResource(R.drawable.abw);
                        break;
                    case 1:
                        yingpian.setImageResource(R.drawable.ac0);
                        yingyuan.setImageResource(R.drawable.abx);
                        huiyuan.setImageResource(R.drawable.ac2);
                        shezhi.setImageResource(R.drawable.abw);
                        break;
                    case 2:
                        yingpian.setImageResource(R.drawable.ac0);
                        yingyuan.setImageResource(R.drawable.abw);
                        huiyuan.setImageResource(R.drawable.ac3);
                        shezhi.setImageResource(R.drawable.abw);
                        break;
                    case 3:
                        yingpian.setImageResource(R.drawable.ac0);
                        yingyuan.setImageResource(R.drawable.abw);
                        huiyuan.setImageResource(R.drawable.ac2);
                        shezhi.setImageResource(R.drawable.abx);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });

        //ActionBar左侧边栏
        initBar();
    }

    private void initBar() {
        //创建ActionBar
        ActionBar actionBar = getSupportActionBar();
        //设置一张图片
        actionBar.setDisplayHomeAsUpEnabled(true);
        toggle = new ActionBarDrawerToggle(this,draw,R.string.open,R.string.close);
        //同步状态
        toggle.syncState();
        draw.addDrawerListener(toggle);
    }

    //给左角设置点击事件
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (toggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    //控件id
    private void initView() {
        draw = findViewById(R.id.draw);
        pager = findViewById(R.id.pager);
        yingpian = findViewById(R.id.yingpian);
        yingyuan = findViewById(R.id.yingyuan);
        huiyuan = findViewById(R.id.huiyuan);
        shezhi = findViewById(R.id.shezhi);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.yingpian:
                pager.setCurrentItem(0);
                break;
            case R.id.yingyuan:
                pager.setCurrentItem(1);
                break;
            case R.id.huiyuan:
                pager.setCurrentItem(2);
                break;
            case R.id.shezhi:
                pager.setCurrentItem(3);
                break;
        }
    }

    public static void DrawClose(){
        draw.closeDrawers();
    }
}
