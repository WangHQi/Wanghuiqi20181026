package wanghuiqi.bawie.com.wanghuiqi20181026.activity.activity.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import wanghuiqi.bawie.com.wanghuiqi20181026.R;
import wanghuiqi.bawie.com.wanghuiqi20181026.activity.activity.ThreeActivity;
import wanghuiqi.bawie.com.wanghuiqi20181026.activity.activity.TwoActivity;

public class NaiFragment extends Fragment {

    private TextView text1;
    private TextView text2;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=View.inflate(getActivity(),R.layout.fragment_nai,null);
        text1 = view.findViewById(R.id.text1);
        text2 = view.findViewById(R.id.text2);

        text1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TwoActivity.DrawClose();
            }
        });
        text2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TwoActivity.DrawClose();
            }
        });
        return view;
    }

}
