package com.xuexiang.xpagedemo;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.xuexiang.xpage.base.XPageActivity;
import com.xuexiang.xpagedemo.fragment.TabAFragment;
import com.xuexiang.xpagedemo.fragment.TabBFragment;
import com.xuexiang.xutil.tip.ToastUtils;



/**
 * @author xuexiang
 * @since 2018/5/30 下午5:41
 */
public class ComplexActivity extends XPageActivity {


    Button btn1;

    Button btn2;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_complex;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        btn1 = findViewById(R.id.btn_1);
        btn2 = findViewById(R.id.btn_2);
        changePage(TabAFragment.class, null);
        btn1.setOnClickListener(onClicked);
        btn2.setOnClickListener(onClicked);
        ((Button)findViewById(R.id.btn_3)).setOnClickListener(onClicked);
        ((Button)findViewById(R.id.btn_4)).setOnClickListener(onClicked);
    }

    View.OnClickListener onClicked = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.btn_1:
                    changePage(TabAFragment.class, null);
                    break;
                case R.id.btn_2:
                    changePage(TabBFragment.class, null);
                    break;
                case R.id.btn_3:
                    TabAFragment tabAFragment = getPage(TabAFragment.class);
                    if (tabAFragment != null) {
                        ToastUtils.toast(tabAFragment.getData());
                    } else {
                        ToastUtils.toast("页面还未加载！");
                    }
                    break;
                case R.id.btn_4:
                    TabBFragment tabBFragment = getPage(TabBFragment.class);
                    if (tabBFragment != null) {
                        ToastUtils.toast(tabBFragment.getData());
                    } else {
                        ToastUtils.toast("页面还未加载！");
                    }
                    break;
            }
        }
    };


}
