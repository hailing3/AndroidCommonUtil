package com.mtx.androidcommonutil.ui.fragment;

import com.mtx.androidcommonutil.R;
import com.mtx.androidcommonutil.ui.activity.TestActivity;
import com.mtx.androidcommonutil.util.LogUtil;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class FragmentA extends Fragment implements View.OnClickListener {
    private static final String TAG = "FragmentA";
    private TextView mTv;
    private Button mBtn;
    private static final int CODE_REQUEST = 0x001;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_a, null);
        mTv = (TextView) view.findViewById(R.id.tv);
        mBtn = (Button) view.findViewById(R.id.btnJump);
        mBtn.setOnClickListener(this);
        return view;
    }

//    @Override
//    public void onResume() {
//        super.onResume();
//        mTv.setText(getArguments().getString(FragmentActivity.KEY_DATA));
//    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnJump:
                startActivityForResult(new Intent(getContext(), TestActivity.class), CODE_REQUEST);
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        LogUtil.i(TAG, "intent=" + intent + ", requestCode = " + requestCode + ", resultCode=" + resultCode);
        if (resultCode == getActivity().RESULT_OK) {
            if (requestCode == CODE_REQUEST) {
                String data = intent.getStringExtra("data");
                LogUtil.i(TAG, "intent=" + intent + ", data = " + data);
                mTv.setText(data);
            }
        }
    }
}
