package com.liqingshan.www.myapplication;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button mButton;
    private ProgressBar mProgressbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mButton = (Button) findViewById(R.id.start);
        mProgressbar = (ProgressBar) findViewById(R.id.progress_bar);
        mButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.start:
                initProgressbar();
                break;
        }

    }

    void initProgressbar() {
        mProgressbar.setVisibility(View.VISIBLE);
        //将线程加入到hander的线程队列中
        mHandler.post(update_thread);
    }

    Runnable update_thread = new Runnable() {
        int i = 0;

        @Override
        public void run() {
            i += 10;
            Message message =mHandler.obtainMessage();
            message.arg1=i;
            //延迟一个小时
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //把消息发送到消息队列中
            mHandler.sendMessage(message);
            if(i==100){
                //把线程从线程队列中移除
                mHandler.removeCallbacks(update_thread);
            }
        }
    };
    //创建一个handler，内部完成处理消息方法
    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            //显示进度条
            mProgressbar.setProgress(msg.arg1);
            //重新把进程加入到进程队列中
            mHandler.post(update_thread);
        }
    };//不加这
}
