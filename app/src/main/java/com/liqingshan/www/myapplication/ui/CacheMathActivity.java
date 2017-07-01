package com.liqingshan.www.myapplication.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.liqingshan.www.myapplication.R;
import com.liqingshan.www.myapplication.simplecache.ACache;

import static com.liqingshan.www.myapplication.simplecache.DataCleanManager.clearAllCache;
import static com.liqingshan.www.myapplication.simplecache.DataCleanManager.getTotalCacheSize;

public class CacheMathActivity extends AppCompatActivity implements View.OnClickListener {
    TextView mTVsave, mTVread, mTVclean, mTVwrite, mTVjisuan, mTVqingchu;
    EditText mEditText;
    private ACache mCache;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        setContentView(R.layout.activity_cache_math);
        //初始化控件
        initView();
        mCache = ACache.get(this);
    }

    void initView() {
        mTVsave = (TextView) findViewById(R.id.save);
        mTVclean = (TextView) findViewById(R.id.clean);
        mTVread = (TextView) findViewById(R.id.read);
        mTVwrite = (TextView) findViewById(R.id.write);
        mTVjisuan = (TextView) findViewById(R.id.jisuan_cache);
        mTVqingchu = (TextView) findViewById(R.id.qingchu_cache);
        mEditText = (EditText) findViewById(R.id.et_number);
        mTVread.setOnClickListener(this);
        mTVclean.setOnClickListener(this);
        mTVsave.setOnClickListener(this);
        mTVjisuan.setOnClickListener(this);
        mTVqingchu.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.save:
                if (mEditText.getText().toString().trim().length() == 0) {
                    Toast.makeText(this, "输入不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                mCache.put("testString", mEditText.getText().toString());
                break;
            case R.id.read:
                String testString = mCache.getAsString("testString");
                if (testString == null) {
                    Toast.makeText(this, "存储为空", Toast.LENGTH_SHORT)
                            .show();
                    mTVwrite.setText(null);
                    return;
                }
                mTVwrite.setText(testString);
                break;
            case R.id.clean:
                mCache.remove("testString");
                break;
            //计算
            case R.id.jisuan_cache:
                try {
                    mTVwrite.setText(getTotalCacheSize(getApplicationContext()));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            //清除
            case R.id.qingchu_cache:
                try {
                    clearAllCache(getApplicationContext());
                    mTVwrite.setText(getTotalCacheSize(getApplicationContext()));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
        }

    }
}
