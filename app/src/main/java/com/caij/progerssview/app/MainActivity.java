package com.caij.progerssview.app;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.caij.progressview.ProgressView;
import com.caij.progressviewa.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ProgressView progressView = (ProgressView) findViewById(R.id.pv);
        progressView.setMax(100);
        progressView.setProgress(30);
    }
}
