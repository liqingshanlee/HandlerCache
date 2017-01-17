package com.liqingshan.www.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class SecondActivity extends AppCompatActivity {
    View layoutMain = null;
    View layoutSecond = null;
    TextView btn_mainActivity = null;
    TextView btn_secondActivity;
    boolean firstFlag = true;
    long time = (long)0.0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater inflater = this.getLayoutInflater();
        layoutMain = inflater.inflate(R.layout.activity_second, null);
        layoutSecond = inflater.inflate(R.layout.three_layout, null);
        setContentView(layoutMain);
        btn_mainActivity  = (TextView)findViewById(R.id.textView);
        btn_mainActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Jump2Second();
            }
        });
    }

    public void Jump2Second(){
        setContentView(layoutSecond);
        long currTime = System.currentTimeMillis();
        Toast.makeText(SecondActivity.this,
                "切换耗时：" +String.valueOf(currTime-time)+"毫秒",
                Toast.LENGTH_SHORT).show();
        if(firstFlag){
            btn_secondActivity = (TextView)findViewById(R.id.textView);
            btn_secondActivity.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Jump2Main();
                }
            });
            firstFlag = false;
        }

    }
    public void Jump2Main(){
        setContentView(layoutMain);
        long currTime = System.currentTimeMillis();
        Toast.makeText(SecondActivity.this,
                "切换耗时：" +String.valueOf(currTime-time)+"毫秒",
                Toast.LENGTH_SHORT).show();
    }
}
