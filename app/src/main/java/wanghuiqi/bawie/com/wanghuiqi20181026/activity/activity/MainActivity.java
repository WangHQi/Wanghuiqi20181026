package wanghuiqi.bawie.com.wanghuiqi20181026.activity.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import wanghuiqi.bawie.com.wanghuiqi20181026.R;

public class MainActivity extends AppCompatActivity {

    private TextView time,tiao;
    private ImageView image;
    private int start_time=3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //初始化控件
        initView();

        //倒计时跳转
        handler.sendEmptyMessageDelayed(0,1000);
        //点击跳过,跳转页面
        tiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,TwoActivity.class));
                finish();
                return;
            }
        });

    }
    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            start_time--;
            time.setText(start_time+"s");
            if (start_time==0){
                startActivity(new Intent(MainActivity.this,TwoActivity.class));
                finish();
            }else{
                handler.sendEmptyMessageDelayed(0,1000);
            }
        }
    };

    //初始化控件
    private void initView() {
        time = findViewById(R.id.time);
        tiao=findViewById(R.id.tiao);
        image = findViewById(R.id.image);
    }

    //销毁
    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacksAndMessages(null);
    }
}
