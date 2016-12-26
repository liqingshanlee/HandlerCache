package com.liqingshan.www.myapplication;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button mButton;
    private ProgressBar mProgressbar;
    private TextView mTextView;
    private Bean mBean=new Bean();
    int i = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mButton = (Button) findViewById(R.id.start);
        mProgressbar = (ProgressBar) findViewById(R.id.progress_bar);
        mTextView=(TextView)findViewById(R.id.textview1);
        mButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.start:
                i=0;
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
        @Override
        public void run() {
            i += 10;
            Message message =mHandler.obtainMessage();
            message.arg1=i;
            mBean.setName("名字是"+i);
            mBean.setTime("时间是"+i);
            message.obj=mBean;
            //延迟一个小时
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //把消息添加到消息队列中
            mHandler.sendMessage(message);
            if(i==100){
                //把线程从线程队列中移除
                mHandler.removeCallbacks(update_thread);
                return;
            }
        }
    };
    //创建一个handler，内部完成处理消息方法
    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            //显示进度条
            mProgressbar.setProgress(msg.arg1);
            Bean mBean=(Bean) msg.obj;
            mTextView.setText(mBean.getName()+mBean.getTime());
            //重新把进程加入到进程队列中
            mHandler.post(update_thread);
        }
    };//不加这
}
