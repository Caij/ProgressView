package com.caij.progerssview.app;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.caij.progressview.ProgressView;
import com.caij.progressviewa.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final ProgressView progressView = (ProgressView) findViewById(R.id.pv);
        progressView.setMax(100);
//        progressView.setProgress(30);
        new Handler(){
            int time = 0;
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                time ++;
                progressView.setProgress(time);

                sendEmptyMessageDelayed(1, 500);
            }
        }.sendEmptyMessage(1);
    }
}
