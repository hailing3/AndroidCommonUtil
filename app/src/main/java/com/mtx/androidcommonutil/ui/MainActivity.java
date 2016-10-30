package com.mtx.androidcommonutil.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.mtx.androidcommonutil.R;

/**
 * Created by lishaoming on 16/10/30.
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView() {

    }

    public void goNaH5Activity(View view) {
        startActivity(new Intent(MainActivity.this, NaH5Activity.class));
    }
}
