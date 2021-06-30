package com.ql.dev;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.ql.dev.library.SwitchView;
import com.ql.dev.library.TitleNavView;

/**
 * Created by Administrator on 2018-10-09 0009.
 */

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initMainView();
    }

    private void initMainView() {
        final TitleNavView  navView = findViewById(R.id.titleNav);
        navView.setTitleNavOnClickListener(new View.OnClickListener() {//点击事件
            @Override
            public void onClick(View v) {

            }
        });
        navView.setTitleNavOnClickListener(new TitleNavView.TitleNavSwitchViewCallback(){//
            @Override
            public void TitleNavSwitchViewOnCallback(SwitchView svStartPage) {
                super.TitleNavSwitchViewOnCallback(svStartPage);
            }

            @Override
            public void TitleNavSwitchViewOffCallback(SwitchView svStartPage) {
                super.TitleNavSwitchViewOffCallback(svStartPage);
            }
        });
        navView.getSwitchViewState();//1:关闭状态、 2:state prepare to on、3:state prepare to off、4:开启状态
        navView.setSwitchViewState(true);//设置SwitchView是否开启
        navView.setSwitchToggle(true);
        navView.setIconVisibility(true);//是否显示前面的图片
        navView.setIconMarginLift(10);//设置前面的图片的左间距
        navView.setIconMarginRight(10); // 设置前面的图片的右间距
        navView.setIconResource(R.mipmap.to_next); // 设置图片资源
        navView.setTitleText("按钮一");//设置标题文字
        navView.setCustomDotPic(R.mipmap.to_next); //设置自定义提示图片
        navView.isShowRedDot(true); //是否显示红点
        navView.setTitleTextSize(12f);//设置标题字体大小
        navView.setTitleTextMarginLeft(12);//设置标题的左间距
        navView.setTitleTextMarginRight(12);//设置文字标题的右间距
        navView.settitleTextColor("#ffffff");//设置标题文字的颜色
        navView.setArrowVisibility(false);//是否显示箭头
        navView.setArrowMarginLeft(12);//设置箭头的左间距
        navView.setArrowMarginRight(12);//设置箭头的右间距
        navView.setSubTitleVisibility(true);//设置附属标题的显示和隐藏
        navView.setSubTitleText("副标题");//设置附属标题的文字
        navView.setSubTitleTextSize(12);//设置附属标题的文字大小
        navView.setSubTitleTextColor("#ffffff");//设置文字颜色
        navView.setSubTitleMarginLeft(12);//设置附属标题的左边距
        navView.setSubTitleMarginRight(12);//设置附属标题的右间距
        navView.setExplainText("sssss");//设置功能性描述文字
        navView.setSubTitleOnClickListener(new View.OnClickListener() { //设置附属标题点击事件
            @Override
            public void onClick(View v) {

            }
        });
    }
}
