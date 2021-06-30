package com.ql.dev.library;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import static com.ql.dev.library.StringUtils.getHttpBitmap;


/**
 * 通用导航标题栏
 * Created by Administrator on 2017/3/22.
 */

public class TitleNavView extends LinearLayout {

    private Context context;
    private ImageView ivIcon;//前面的图片
    private TextView tvTitle;//主标题
    private TextView tvTitleAfter;// 跟在主标题后面的描述性文字
    private TextView tvSubTitle;//副标题
    private TextView tvExplain; // 功能说明
    private ImageView ivArrow;//箭头
    private LinearLayout llTitleNav;
    private LinearLayout llHorzontal;
    private LinearLayout llVertical;
    private ImageView ivVerticalIcon;
    private TextView tvVerticalTitle;
    private TextView tvVerticalSubTitle;
    //属性
    private boolean isShowIcon;//是否显示图片
    private boolean switchOpen; // 是否默认打开开关
    private float iconMarginLeft;//图片左边距
    private float iconMarginRight;//图片右边距
    private float iconMarginTop;
    private float iconMarginBottom;
    private int iconResource;//图片资源
    private String titlesText;//标题文字
    private String explainText; //功能说明汉字
    private float explainTextSize;// 功能说明文字的字体大小
    private String titleRightText;//右边的文字，在ShowType为font时有用
    private float titlesTextMarginLeft;//文字左间距
    private float titlesTextMarginTop; // 文字上间距
    private float titlesTextMarginBottom;// 文字下间距
    private float titlesTextMarginRight;//文字右间距
    private float titlesTextSize;//标题文字的大小
    private int titlesTextColor;//标题文字的颜色
    private int navBackageColor; // 导航的背景颜色
    private int isShowType;// 尾部是显示箭头还是显示开关
    private int showDescType; // 功能性描述显示的位置---参照物为主标题
    private float arrowMarginLeft;//箭头的左边距
    private float arrowMarginRight;//箭头的右边距
    private boolean isShowSubTitles;//是否显示附属标题
    private String subTitlesText;//设置附属标题的文字
    private float subTitlesTextSize;//设置副标题的文字大小
    private int subTitlesTextColor;//设置副标题的文字颜色
    private float subTitlesMarginLeft;//副标题的左边距
    private float subTitlesMarginTop; //副标题的上边距
    private float subTitlesMarginRight;//副标题的右边距
    private float subTitlesMarginBottom;//副标题的下边距
    private float explainTextMarginLeft;
    private float explainTextMarginRight;
    private SwitchView svStartPage; // 开关
    private TextView tvFont; // 文字
    private int navViewType;
    private float navHeight; // 总高度
    private int isShowDotType;
    private int customDotPic;
    private ImageView tvPicTips;
    private ImageView tvVerticalCusPicTips;
//    private float iconHeight;
//    private float iconWidth;

    private float iconPadding;
    private float iconPaddingLeft;
    private float iconPaddingRight;
    private float iconPaddingTop;
    private float iconPaddingBottom;
    private int scaleType;

    //(1：显示箭头，2：显示开关，3显示文字，4：都不显示）
    public static final int SHOWTYPE_ARRAW = 0;

    public static final int SHOWTYPE_SWITCH= 1;

    public static final int SHOWTYPE_FONT= 2;

    public static final int SHOWTYPE_NONE= 3;


    public TitleNavView(Context context) {
        super(context, null);
    }

    public TitleNavView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        //加载试图布局
        LayoutInflater.from(context).inflate(R.layout.custom_title_nav, this, true);
        //加载自定义属性
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.titleNavView);
        if (typedArray != null) {
            navHeight = typedArray.getDimension(R.styleable.titleNavView_navHeight,dp2px(context,50f));
            navViewType = typedArray.getInt(R.styleable.titleNavView_navViewType,0);
            isShowDotType = typedArray.getInt(R.styleable.titleNavView_isShowDotType,2);
            scaleType = typedArray.getInt(R.styleable.titleNavView_scale_Type,-1);
            customDotPic =typedArray.getResourceId(R.styleable.titleNavView_isShowDotPic,0);
            isShowIcon = typedArray.getBoolean(R.styleable.titleNavView_isShowIcon, true);//是否显示图片默认为true显示
//            iconHeight = typedArray.getDimension(R.styleable.titleNavView_icon_height, LayoutParams.WRAP_CONTENT);
//            iconWidth = typedArray.getDimension(R.styleable.titleNavView_icon_width, LayoutParams.WRAP_CONTENT);
            iconMarginLeft = typedArray.getDimension(R.styleable.titleNavView_iconMarginLeft, 12f);
            iconMarginRight = typedArray.getDimension(R.styleable.titleNavView_iconMarginRight, 12f);
            iconMarginTop = typedArray.getDimension(R.styleable.titleNavView_iconMarginTop,0f);
            iconMarginBottom = typedArray.getDimension(R.styleable.titleNavView_iconMarginBottom,0f);
            iconResource = typedArray.getResourceId(R.styleable.titleNavView_iconResource, 0);
            iconPadding = typedArray.getDimension(R.styleable.titleNavView_iconPadding,0f);
            iconPaddingLeft = typedArray.getDimension(R.styleable.titleNavView_iconPaddingLeft,0f);
            iconPaddingRight = typedArray.getDimension(R.styleable.titleNavView_iconPaddingRight,0f);
            iconPaddingTop = typedArray.getDimension(R.styleable.titleNavView_iconPaddingTop,0f);
            iconPaddingBottom = typedArray.getDimension(R.styleable.titleNavView_iconPaddingBottom,0f);
            titlesText = typedArray.getString(R.styleable.titleNavView_titlesText);
            explainText = typedArray.getString(R.styleable.titleNavView_explainText);
            explainTextSize = typedArray.getDimension(R.styleable.titleNavView_explainTextSize,sp2px(context, 14f));
            titlesTextSize = typedArray.getDimension(R.styleable.titleNavView_titlesTextSize, sp2px(context, 14f));
            titlesTextColor = typedArray.getColor(R.styleable.titleNavView_titlesTextColor, Color.parseColor("#333333"));
            navBackageColor = typedArray.getColor(R.styleable.titleNavView_navBackageColor, Color.parseColor("#ffffff"));
            isShowType = typedArray.getInt(R.styleable.titleNavView_isShowType, 0);
            showDescType = typedArray.getInt(R.styleable.titleNavView_showDescType,0);
            switchOpen = typedArray.getBoolean(R.styleable.titleNavView_switchOpen,false);
            titleRightText = typedArray.getString(R.styleable.titleNavView_titleRightText);
            arrowMarginLeft = typedArray.getDimension(R.styleable.titleNavView_arrowMarginLeft, 12f);
            arrowMarginRight = typedArray.getDimension(R.styleable.titleNavView_arrowMarginRight, 12f);
            isShowSubTitles = typedArray.getBoolean(R.styleable.titleNavView_isShowSubTitles, false);
            subTitlesText = typedArray.getString(R.styleable.titleNavView_subTitlesText);
            subTitlesTextSize = typedArray.getDimension(R.styleable.titleNavView_subTitlesTextSize, sp2px(context, 12f));
            subTitlesTextColor = typedArray.getColor(R.styleable.titleNavView_subTitlesTextColor, Color.parseColor("#7f8c8d"));
            subTitlesMarginLeft = typedArray.getDimension(R.styleable.titleNavView_subTitlesMarginLeft, 0);
            subTitlesMarginRight = typedArray.getDimension(R.styleable.titleNavView_subTitlesMarginRight, 0);
            subTitlesMarginTop = typedArray.getDimension(R.styleable.titleNavView_subTitlesMarginTop, 0);
            subTitlesMarginBottom = typedArray.getDimension(R.styleable.titleNavView_subTitlesMarginBottom, 0);
            titlesTextMarginLeft = typedArray.getDimension(R.styleable.titleNavView_titlesTextMarginLeft, 0);
            titlesTextMarginRight = typedArray.getDimension(R.styleable.titleNavView_titlesTextMarginRight, 0);
            titlesTextMarginTop = typedArray.getDimension(R.styleable.titleNavView_titlesTextMarginTop, 0);
            titlesTextMarginBottom = typedArray.getDimension(R.styleable.titleNavView_titlesTextMarginBottom, 0);
            explainTextMarginLeft = typedArray.getDimension(R.styleable.titleNavView_explainTextMarginLeft,0);
            explainTextMarginRight = typedArray.getDimension(R.styleable.titleNavView_explainTextMarginRight,0);
            //回收资源
            typedArray.recycle();
        }
    }

    /**
     * 此方法会在所有的控件都从xml文件中加载完成后调用
     * 网上说这个方法比较好，在xml加载完之后再设置数据
     */
    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        //获取控件
        initWidget();
        //初始化属性
        initProperty();
    }

    private void initWidget() {
        ivIcon =  findViewById(R.id.ivIcon);
        tvTitle =  findViewById(R.id.tvTitle);
        tvExplain =  findViewById(R.id.tvExplain);
        tvSubTitle =  findViewById(R.id.tvSubTitle);
        ivArrow =  findViewById(R.id.ivArrow);
        svStartPage =  findViewById(R.id.svStartPage);
        tvFont =  findViewById(R.id.tvFont);
        llTitleNav =  findViewById(R.id.llTitleNav);
        llHorzontal =  findViewById(R.id.llHorzontal);
        llVertical =  findViewById(R.id.llVertical);
        ivVerticalIcon =  findViewById(R.id.ivVerticalIcon);
        tvVerticalTitle =  findViewById(R.id.tvVerticalTitle);
        tvVerticalSubTitle =  findViewById(R.id.tvVerticalSubTitle);
        tvVerticalCusPicTips =  findViewById(R.id.tvVerticalCusPicTips);
        tvPicTips =  findViewById(R.id.tvPicTips);
        tvTitleAfter = findViewById(R.id.tvTitleAfter);
    }

    private void initProperty() {
        llTitleNav.setBackgroundColor(navBackageColor);
        if (navViewType == 0) {
            LayoutParams navHeightlayoutParams = new LayoutParams(LayoutParams.MATCH_PARENT,(int) navHeight);
            llTitleNav.setLayoutParams(navHeightlayoutParams);
            llVertical.setVisibility(GONE);
            llHorzontal.setVisibility(VISIBLE);
            //设置是否显示图片
            if (isShowIcon) {
                ivIcon.setVisibility(VISIBLE);
            } else {
                ivIcon.setVisibility(GONE);
            }
            if(iconPadding!=0){
                ivIcon.setPadding((int)iconPadding,(int)iconPadding,(int)iconPadding,(int)iconPadding);
            }else{
                ivIcon.setPadding((int)iconPaddingLeft,(int)iconPaddingTop,(int)iconPaddingRight,(int)iconPaddingBottom);
            }
            if(scaleType != -1) {
                setScaleType(scaleType);
            }

            if(isShowDotType == 0){ // 显示红点
                tvPicTips.setVisibility(VISIBLE);
                tvPicTips.setImageResource(R.drawable.red_dot);
            }else if(isShowDotType ==1){ // 显示自定义
                tvPicTips.setVisibility(VISIBLE);
                tvPicTips.setImageResource(customDotPic);
            }else if(isShowDotType ==2){ // 不显示
                tvPicTips.setVisibility(GONE);
            }
            //设置图片的左右边距
            LayoutParams params = (LayoutParams) ivIcon.getLayoutParams();
//            if(iconHeight!=LayoutParams.WRAP_CONTENT){
//                params.height = dp2px(context,iconHeight);
//            }
//            if(iconWidth != LayoutParams.WRAP_CONTENT){
//                params.weight = dp2px(context,iconWidth);
//            }
            params.setMargins((int) iconMarginLeft, 0, (int) iconMarginRight, 0);
            ivIcon.setLayoutParams(params);
            //设置图片资源
            if (iconResource != 0) {
                ivIcon.setImageResource(iconResource);
            }
            //设置标题文字
            if (!StringUtils.isEmpty(titlesText)) {
                tvTitle.setText(titlesText);
            } else {
                tvTitle.setText("");
            }
            if(showDescType == 0){
                if(!StringUtils.isEmpty(explainText)){
                    tvExplain.setText(explainText);
                    tvExplain.setVisibility(VISIBLE);
                    explainTextSize = px2sp(context, explainTextSize);
                    tvExplain.setTextSize(explainTextSize);
                }else{
                    tvExplain.setText("");
                    tvExplain.setVisibility(GONE);
                }
                //设置文字左间距/右间距
                RelativeLayout.LayoutParams explainLeftlayoutParams = (RelativeLayout.LayoutParams) tvExplain.getLayoutParams();
                explainLeftlayoutParams.setMargins((int) explainTextMarginLeft, 0, (int) explainTextMarginRight, 0);
                tvExplain.setLayoutParams(explainLeftlayoutParams);
                tvTitleAfter.setVisibility(GONE);
            }else if(showDescType == 1){
                if(!StringUtils.isEmpty(explainText)){
                    tvTitleAfter.setText(explainText);
                    tvTitleAfter.setVisibility(VISIBLE);
                    explainTextSize = px2sp(context, explainTextSize);
                    tvTitleAfter.setTextSize(explainTextSize);
                }else{
                    tvTitleAfter.setText("");
                    tvTitleAfter.setVisibility(GONE);
                }
                //设置文字左间距/右间距
                LayoutParams explainLeftlayoutParams = (LayoutParams) tvTitleAfter.getLayoutParams();
                explainLeftlayoutParams.setMargins((int) explainTextMarginLeft, 0, (int) explainTextMarginRight, 0);
                tvTitleAfter.setLayoutParams(explainLeftlayoutParams);
                tvExplain.setVisibility(GONE);
            }

            //标题文字的大小
            if (titlesTextSize > 0) {
                titlesTextSize = px2sp(context, titlesTextSize);
                tvTitle.setTextSize(titlesTextSize);
            }
            //设置文字左间距/右间距
            LayoutParams layoutParams = (LayoutParams) tvTitle.getLayoutParams();
            layoutParams.setMargins((int) titlesTextMarginLeft, 0, (int) titlesTextMarginRight, 0);
            tvTitle.setLayoutParams(layoutParams);
            //设置标题文字颜色
            tvTitle.setTextColor(titlesTextColor);
            setShowType(isShowType);
            //是否显示副标题
            if (isShowSubTitles) {
                tvSubTitle.setVisibility(VISIBLE);
            } else {
                tvSubTitle.setVisibility(GONE);
            }
            //设置副标题的文字
            if (!StringUtils.isEmpty(subTitlesText)) {
                tvSubTitle.setText(subTitlesText);
            }
            //设置附属标题的文字大小
            if (subTitlesTextSize > 0) {
                subTitlesTextSize = px2sp(context, subTitlesTextSize);
                tvSubTitle.setTextSize(subTitlesTextSize);
            }
            //设置附属标题的文字颜色
            tvSubTitle.setTextColor(subTitlesTextColor);
            //设置附属标题的间距
            LayoutParams layoutP = (LayoutParams) tvSubTitle.getLayoutParams();
            layoutP.setMargins((int) subTitlesMarginLeft, 0, (int) subTitlesMarginRight, 0);
            tvSubTitle.setLayoutParams(layoutP);
        }else if(navViewType == 1){ // 垂直方式的显示的特有属性，
            llVertical.setVisibility(VISIBLE);
            llHorzontal.setVisibility(GONE);
            LayoutParams navHeightlayoutParams = new LayoutParams(LayoutParams.MATCH_PARENT,(int) navHeight);
            llTitleNav.setLayoutParams(navHeightlayoutParams);
            //设置是否显示图片
            if (isShowIcon) {
                ivVerticalIcon.setVisibility(VISIBLE);
            } else {
                ivVerticalIcon.setVisibility(GONE);
            }
            if(isShowDotType == 0){
                tvVerticalCusPicTips.setVisibility(VISIBLE);
                tvVerticalCusPicTips.setImageResource(R.drawable.red_dot);
            }else if(isShowDotType == 1){
                tvVerticalCusPicTips.setVisibility(VISIBLE);
                tvVerticalCusPicTips.setImageResource(customDotPic);
            }else if(isShowDotType == 2){
                tvVerticalCusPicTips.setVisibility(GONE);
            }
            if(iconPadding!=0){
                ivVerticalIcon.setPadding((int)iconPadding,(int)iconPadding,(int)iconPadding,(int)iconPadding);
            }else{
                ivVerticalIcon.setPadding((int)iconPaddingLeft,(int)iconPaddingTop,(int)iconPaddingRight,(int)iconPaddingBottom);
            }
            if(scaleType != -1) {
                setScaleType(scaleType);
            }
            //设置图片的左右边距
            RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) ivVerticalIcon.getLayoutParams();
            params.setMargins(0, (int)iconMarginTop, 0, (int)iconMarginBottom);
            ivVerticalIcon.setLayoutParams(params);
            //设置图片资源
            if (iconResource != 0) {
                ivVerticalIcon.setImageResource(iconResource);
            }

            //设置标题文字
            if (!StringUtils.isEmpty(titlesText)) {
                tvVerticalTitle.setText(titlesText);
            } else {
                tvVerticalTitle.setText("");
            }
            //标题文字的大小
            if (titlesTextSize > 0) {
                titlesTextSize = px2sp(context, titlesTextSize);
                tvVerticalTitle.setTextSize(titlesTextSize);
            }
            //设置文字左间距/右间距
            LayoutParams titleLayoutParams = (LayoutParams) tvVerticalTitle.getLayoutParams();
            titleLayoutParams.setMargins(0,(int) titlesTextMarginTop, 0, (int) titlesTextMarginBottom);
            tvVerticalTitle.setLayoutParams(titleLayoutParams);
            //设置标题文字颜色
            tvVerticalTitle.setTextColor(titlesTextColor);

            //是否显示副标题
            if (isShowSubTitles) {
                tvVerticalSubTitle.setVisibility(VISIBLE);
            } else {
                tvVerticalSubTitle.setVisibility(GONE);
            }
            //设置副标题的文字
            if (!StringUtils.isEmpty(subTitlesText)) {
                tvVerticalSubTitle.setText(subTitlesText);
            }
            //设置附属标题的文字大小
            if (subTitlesTextSize > 0) {
                subTitlesTextSize = px2sp(context, subTitlesTextSize);
                tvVerticalSubTitle.setTextSize(subTitlesTextSize);
            }
            //设置附属标题的文字颜色
            tvVerticalSubTitle.setTextColor(subTitlesTextColor);
            //设置附属标题的间距
            LayoutParams layoutP = (LayoutParams) tvVerticalSubTitle.getLayoutParams();
            layoutP.setMargins(0,(int) subTitlesMarginTop, 0, (int) subTitlesMarginBottom);
            tvVerticalSubTitle.setLayoutParams(layoutP);
        }
    }

    public static float sp2px(Context context, float spValue) {
        if (spValue <= 0) return 0;
        final float scale = context.getResources().getDisplayMetrics().scaledDensity;
        return spValue * scale;
    }

    public static float px2sp(Context context, float pxVal) {
        return (pxVal / context.getResources().getDisplayMetrics().scaledDensity);
    }

    public static float px2dp(Context context, float pxVal) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (pxVal / scale);
    }

    public static int dp2px(Context context, float dpVal) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                dpVal, context.getResources().getDisplayMetrics());
    }

    /**
     * 获取显示的类型
     * @return （1：显示箭头，2：显示开关，3显示文字，4：都不显示）
     */
    public int getShowType(){
        return isShowType;
    }

    /**
     * 设置显示类型
     * @param isShowType （1：显示箭头，2：显示开关，3显示文字，4：都不显示）
     */
    public void setShowType(int isShowType){
        this.isShowType = isShowType;
        //是否显示箭头
        if (isShowType == SHOWTYPE_ARRAW) { // 显示箭头
            ivArrow.setVisibility(VISIBLE);
            svStartPage.setVisibility(GONE);
            tvFont.setVisibility(GONE);
            //设置箭头的边距
            LayoutParams lp = (LayoutParams) ivArrow.getLayoutParams();
            lp.setMargins((int) arrowMarginLeft, 0, (int) arrowMarginRight, 0);
            ivArrow.setLayoutParams(lp);
        } else if (isShowType == SHOWTYPE_SWITCH) { // 显示开关
            ivArrow.setVisibility(GONE);
            svStartPage.setVisibility(VISIBLE);
            tvFont.setVisibility(GONE);
            if(switchOpen){
                svStartPage.setState(true);
            }
            //设置开关的边距
            LayoutParams lp = (LayoutParams) svStartPage.getLayoutParams();
            lp.setMargins((int) arrowMarginLeft, 0, (int) arrowMarginRight, 0);
            svStartPage.setLayoutParams(lp);
            switchViewListener();
        } else if (isShowType == SHOWTYPE_FONT) { // 显示文字
            ivArrow.setVisibility(GONE);
            svStartPage.setVisibility(GONE);
            tvFont.setVisibility(VISIBLE);
            tvFont.setText(titleRightText);
            //设置开关的边距
            LayoutParams lp = (LayoutParams) tvFont.getLayoutParams();
            lp.setMargins((int) arrowMarginLeft, 0, (int) arrowMarginRight, 0);
            tvFont.setLayoutParams(lp);
        } else if (isShowType == SHOWTYPE_NONE) { // 都不显示
            ivArrow.setVisibility(GONE);
            svStartPage.setVisibility(GONE);
            tvFont.setVisibility(GONE);
        }
    }

    /**
     * 点击事件
     */
    public void setTitleNavOnClickListener(OnClickListener onClickListener) {
        llTitleNav.setOnClickListener(onClickListener);
    }

    public static class TitleNavSwitchViewCallback {
        /**
         * SwitchView为打开时的回调
         * @param svStartPage
         */
        public void TitleNavSwitchViewOnCallback(SwitchView svStartPage) {
            svStartPage.toggleSwitch(true);
        }

        /**
         * SwitchView为关闭时的回调
         * @param svStartPage
         */
        public void TitleNavSwitchViewOffCallback(SwitchView svStartPage) {
            svStartPage.toggleSwitch(false);
        }
    }

    private TitleNavSwitchViewCallback titleNavSwitchViewCallback;

    /**
     * 设置SwitchView的事件
     *
     * @param titleNavOnClickListener
     */
    public void setTitleNavOnClickListener(TitleNavSwitchViewCallback titleNavOnClickListener) {
        this.titleNavSwitchViewCallback = titleNavOnClickListener;
    }

    private void switchViewListener() {
        try {
            svStartPage.setOnStateChangedListener(new SwitchView.OnStateChangedListener() {
                @Override
                public void toggleToOn() { //开关打开表示打开夜间模式
                    if (titleNavSwitchViewCallback == null) {
                        throw new NullPointerException("titleNavSwitchViewCallback is not null,please call setTitleNavOnClickListener() before");
                    }else {
                        titleNavSwitchViewCallback.TitleNavSwitchViewOnCallback(svStartPage);
                    }
                }

                @Override
                public void toggleToOff() {  //开关关闭表示关闭夜间模式
                    if (titleNavSwitchViewCallback == null) {
                        throw new NullPointerException("titleNavSwitchViewCallback is not null,please call setTitleNavOnClickListener() before");
                    }else {
                        titleNavSwitchViewCallback.TitleNavSwitchViewOffCallback(svStartPage);
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @param rightText
     */
    public void setRightText(String rightText){
        tvFont.setText(rightText);
    }

    /**
     * 设置SwitchView是否开启
     * @param onOff
     */
    public void setSwitchViewState(boolean onOff){
        svStartPage.setState(onOff);
    }

    /**
     * getState为
     *          1:关闭状态
     *          2:state prepare to on
     *          3:state prepare to off
     *          4:开启状态
     * @return
     */
    public int getSwitchViewState(){
        return svStartPage.getState();
    }

    public void setSwitchToggle(boolean onOff){
        svStartPage.toggleSwitch(onOff);
    }

    /**
     * 是否显示前面的图片
     */
    public void setIconVisibility(boolean isVisibility) {
        if(navViewType == 0) {
            if (isVisibility) {
                ivIcon.setVisibility(VISIBLE);
            } else {
                ivIcon.setVisibility(GONE);
            }
        }else{
            if (isVisibility) {
                ivVerticalIcon.setVisibility(VISIBLE);
            } else {
                ivVerticalIcon.setVisibility(GONE);
            }
        }
    }

    /**
     * 设置前面的图片的左间距
     */
    public void setIconMarginLift(int marginLeft) {
        if(navViewType == 0) {
            LayoutParams params = (LayoutParams) ivIcon.getLayoutParams();
            params.setMargins(marginLeft, 0, 0, 0);
            ivIcon.setLayoutParams(params);
        }else{
            LayoutParams params = (LayoutParams) ivVerticalIcon.getLayoutParams();
            params.setMargins(marginLeft, 0, 0, 0);
            ivVerticalIcon.setLayoutParams(params);
        }
    }

    /**
     * 设置前面的图片的右间距
     */
    public void setIconMarginRight(int marginRight) {
        if(navViewType == 0) {
            LayoutParams params = (LayoutParams) ivIcon.getLayoutParams();
            params.setMargins(0, 0, marginRight, 0);
            ivIcon.setLayoutParams(params);
        }else{
            LayoutParams params = (LayoutParams) ivVerticalIcon.getLayoutParams();
            params.setMargins(0, 0, marginRight, 0);
            ivVerticalIcon.setLayoutParams(params);
        }
    }

    /**
     * 设置图片资源
     */
    public void setIconResource(int resource) {
        if (resource == 0) {
            return;
        }
        if(navViewType == 0) {
            ivIcon.setImageResource(resource);
        }else {
            ivVerticalIcon.setImageResource(resource);
        }
    }

    public void setIconResource(String resource){
        if(!StringUtils.isEmpty(resource)){
            return;
        }
        Bitmap bitmap=getHttpBitmap(resource);
        if(navViewType == 0){
            ivIcon.setImageBitmap(bitmap);
        }else{
            ivVerticalIcon.setImageBitmap(bitmap);
        }
    }

    /**
     * 设置标题文字
     */
    public void setTitleText(String text) {
        if (StringUtils.isEmpty(text)) {
            return;
        }
        if(navViewType == 0) {
            tvTitle.setText(text);
        }else{
            tvVerticalTitle.setText(text);
        }
    }

    /**
     * 设置自定义提示图片
     * @param resource
     */
    public void setCustomDotPic(int resource){
        if (resource == 0) {
            return;
        }
        if(navViewType == 1) {
            tvVerticalCusPicTips.setImageResource(resource);
        }
    }

    /**
     * 是否显示红点
     * @param isShow
     */
    public void isShowRedDot(boolean isShow){
        if(isShow){
            if (navViewType == 0) { //水平方式
                tvPicTips.setVisibility(VISIBLE);
                if(customDotPic!=0) {
                    tvPicTips.setImageResource(customDotPic);
                }else{
                    tvPicTips.setImageResource(R.drawable.red_dot);
                }
            }else if(navViewType == 1){//垂直方向
                tvVerticalCusPicTips.setVisibility(VISIBLE);
                if(customDotPic!=0) {
                    tvVerticalCusPicTips.setImageResource(customDotPic);
                }else{
                    tvVerticalCusPicTips.setImageResource(R.drawable.red_dot);
                }
            }
        }else{
            if (navViewType == 0) {
                tvPicTips.setVisibility(GONE);
            }else if(navViewType == 1){
                tvVerticalCusPicTips.setVisibility(GONE);
            }
        }
    }

    /**
     * 设置标题文字大小
     */
    public void setTitleTextSize(float textSize) {
        if (textSize <= 0) {
            return;
        }
        if(navViewType == 0) {
            tvTitle.setTextSize(textSize);
        }else{
            tvVerticalTitle.setTextSize(textSize);
        }
    }

    /**
     * 设置标题的左间距
     */
    public void setTitleTextMarginLeft(int marginLeftDP) {
        if(navViewType == 0) {
            int marginLeft = dp2px(context, marginLeftDP);
            LayoutParams params = (LayoutParams) tvTitle.getLayoutParams();
            params.setMargins(marginLeft, 0, 0, 0);
            tvTitle.setLayoutParams(params);
        }else{
            int marginLeft = dp2px(context, marginLeftDP);
            LayoutParams params = (LayoutParams) tvVerticalTitle.getLayoutParams();
            params.setMargins(marginLeft, 0, 0, 0);
            tvVerticalTitle.setLayoutParams(params);
        }
    }

    /**
     * 设置文字标题的右间距
     */
    public void setTitleTextMarginRight(int marginRightDP) {
        if(navViewType == 0) {
            int marginRight = dp2px(context, marginRightDP);
            LayoutParams params = (LayoutParams) tvTitle.getLayoutParams();
            params.setMargins(0, 0, marginRight, 0);
            tvTitle.setLayoutParams(params);
        }else{
            int marginRight = dp2px(context, marginRightDP);
            LayoutParams params = (LayoutParams) tvVerticalTitle.getLayoutParams();
            params.setMargins(0, 0, marginRight, 0);
            tvVerticalTitle.setLayoutParams(params);
        }
    }

    /**
     * 设置标题文字的颜色
     */
    public void settitleTextColor(String argb) {
        if(navViewType == 0) {
            tvTitle.setTextColor(Color.parseColor(argb));
        }else{
            tvVerticalTitle.setTextColor(Color.parseColor(argb));
        }
    }

    /**
     * 是否显示箭头
     */
    public void setArrowVisibility(boolean isVisibility) {
        if (isVisibility) {
            ivArrow.setVisibility(VISIBLE);
        } else {
            ivArrow.setVisibility(GONE);
        }
    }

    /**
     * 设置箭头的左间距
     */
    public void setArrowMarginLeft(int marginLeftDP) {
        int marginLeft = dp2px(context, marginLeftDP);
        LayoutParams params = (LayoutParams) ivArrow.getLayoutParams();
        params.setMargins(marginLeft, 0, 0, 0);
        ivArrow.setLayoutParams(params);
    }

    /**
     * 设置箭头的右间距
     */
    public void setArrowMarginRight(int marginRightDP) {
        int marginRight = dp2px(context, marginRightDP);
        LayoutParams params = (LayoutParams) ivArrow.getLayoutParams();
        params.setMargins(0, 0, marginRight, 0);
        ivArrow.setLayoutParams(params);
    }

    /**
     * 设置附属标题的显示和隐藏
     */
    public void setSubTitleVisibility(boolean isVisibility) {
        if(navViewType ==0) {
            if (isVisibility) {
                tvSubTitle.setVisibility(VISIBLE);
            } else {
                tvSubTitle.setVisibility(GONE);
            }
        }else{
            if (isVisibility) {
                tvVerticalSubTitle.setVisibility(VISIBLE);
            } else {
                tvVerticalSubTitle.setVisibility(GONE);
            }
        }
    }

    /**
     * 获取附属标题是否显示和隐藏
     * @return
     */
    public int getSubTitleVisibility(){
        int visibility = 0;
        if(navViewType ==0) {
            visibility = tvSubTitle.getVisibility();
        }else{
            visibility = tvVerticalSubTitle.getVisibility();
        }
        return visibility;
    }

    /**
     * 设置附属标题的文字
     */
    public void setSubTitleText(String text) {
        this.subTitlesText = text;
        if (StringUtils.isEmpty(text)) {
            return;
        }
        if(navViewType == 0) {
            tvSubTitle.setText(text);
        }else{
            tvVerticalSubTitle.setText(text);
        }
    }

    /**
     * 获取附属标题的文字
     * @return
     */
    public String getSubTitleText(){
        return subTitlesText;
    }

    /**
     * 设置附属标题的文字大小
     */
    public void setSubTitleTextSize(int textSize) {
        if (textSize <= 0) {
            return;
        }
        if(navViewType == 0) {
            tvSubTitle.setTextSize(textSize);
        }else{
            tvVerticalSubTitle.setTextSize(textSize);
        }
    }

    /**
     * 设置文字颜色
     */
    public void setSubTitleTextColor(String argb) {
        if (StringUtils.isEmpty(argb)) {
            return;
        }
        if(navViewType == 0) {
            tvSubTitle.setTextColor(Color.parseColor(argb));
        }else{
            tvVerticalSubTitle.setTextColor(Color.parseColor(argb));
        }
    }

    /**
     * 设置附属标题的左边距
     */
    public void setSubTitleMarginLeft(int marginLeftDP) {
        if(navViewType == 0 ) {
            int marginLeft = dp2px(context, marginLeftDP);
            LayoutParams params = (LayoutParams) tvSubTitle.getLayoutParams();
            params.setMargins(marginLeft, 0, 0, 0);
            tvSubTitle.setLayoutParams(params);
        }else{
            int marginLeft = dp2px(context, marginLeftDP);
            LayoutParams params = (LayoutParams) tvVerticalSubTitle.getLayoutParams();
            params.setMargins(marginLeft, 0, 0, 0);
            tvVerticalSubTitle.setLayoutParams(params);
        }
    }

    /**
     * 设置附属标题的右间距
     */
    public void setSubTitleMarginRight(int marginRightDP) {
        if(navViewType == 0 ) {
            int marginRight = dp2px(context, marginRightDP);
            LayoutParams params = (LayoutParams) tvSubTitle.getLayoutParams();
            params.setMargins(0, 0, marginRight, 0);
            tvSubTitle.setLayoutParams(params);
        }else{
            int marginRight = dp2px(context, marginRightDP);
            LayoutParams params = (LayoutParams) tvVerticalSubTitle.getLayoutParams();
            params.setMargins(0, 0, marginRight, 0);
            tvVerticalSubTitle.setLayoutParams(params);
        }
    }

    /**
     * 设置功能性描述文字
     * @param explainText
     */
    public void setExplainText(String explainText) {
        this.explainText = explainText;
        if(showDescType == 0){
            if(!StringUtils.isEmpty(explainText)){
                tvExplain.setText(explainText);
                tvExplain.setVisibility(VISIBLE);
            }else{
                tvExplain.setText("");
                tvExplain.setVisibility(GONE);
            }
            tvTitleAfter.setVisibility(GONE);
        }else if(showDescType == 1){
            if(!StringUtils.isEmpty(explainText)){
                tvTitleAfter.setText(explainText);
                tvTitleAfter.setVisibility(VISIBLE);
            }else{
                tvTitleAfter.setText("");
                tvTitleAfter.setVisibility(GONE);
            }
            tvExplain.setVisibility(GONE);
        }
    }

    /**
     * 获取功能性描述文字
     * @return
     */
    public String getExplainText(){
        return explainText;
    }

    /**
     * 设置图标的Padding
     * @param iconPadding
     */
    public void setIconPadding(float iconPadding) {
        if(navViewType == 0 ) {
            ivIcon.setPadding((int)iconPadding,(int)iconPadding,(int)iconPadding,(int)iconPadding);
        }else{
            ivVerticalIcon.setPadding((int)iconPadding,(int)iconPadding,(int)iconPadding,(int)iconPadding);
        }
    }

    /**
     * 设置图片Padding上下左右
     * @param iconPaddingLeft
     * @param iconPaddingRight
     * @param iconPaddingTop
     * @param iconPaddingBottom
     */
    public void setIconPaddingLeft(float iconPaddingLeft,float iconPaddingRight,float iconPaddingTop,float iconPaddingBottom) {
        if(navViewType == 0 ) {
            ivIcon.setPadding((int)iconPaddingLeft,(int)iconPaddingTop,(int)iconPaddingRight,(int)iconPaddingBottom);
        }else{
            ivVerticalIcon.setPadding((int)iconPaddingLeft,(int)iconPaddingTop,(int)iconPaddingRight,(int)iconPaddingBottom);
        }
    }

    public void setScaleType(int scaleType) {
        if(navViewType == 0 ) {
            if(scaleType == 0){
                ivIcon.setScaleType(ImageView.ScaleType.FIT_CENTER);
            }else if(scaleType ==1){
                ivIcon.setScaleType(ImageView.ScaleType.FIT_START);
            }else if(scaleType ==2){
                ivIcon.setScaleType(ImageView.ScaleType.FIT_XY);
            }else if(scaleType ==3){
                ivIcon.setScaleType(ImageView.ScaleType.FIT_END);
            }else if(scaleType ==4){
                ivIcon.setScaleType(ImageView.ScaleType.MATRIX);
            }else if(scaleType ==5){
                ivIcon.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
            }else if(scaleType ==6){
                ivIcon.setScaleType(ImageView.ScaleType.CENTER);
            }else if(scaleType ==7){
                ivIcon.setScaleType(ImageView.ScaleType.CENTER_CROP);
            }
        }else{
            if(scaleType == 0){
                ivVerticalIcon.setScaleType(ImageView.ScaleType.FIT_CENTER);
            }else if(scaleType ==1){
                ivVerticalIcon.setScaleType(ImageView.ScaleType.FIT_START);
            }else if(scaleType ==2){
                ivVerticalIcon.setScaleType(ImageView.ScaleType.FIT_XY);
            }else if(scaleType ==3){
                ivVerticalIcon.setScaleType(ImageView.ScaleType.FIT_END);
            }else if(scaleType ==4){
                ivVerticalIcon.setScaleType(ImageView.ScaleType.MATRIX);
            }else if(scaleType ==5){
                ivVerticalIcon.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
            }else if(scaleType ==6){
                ivVerticalIcon.setScaleType(ImageView.ScaleType.CENTER);
            }else if(scaleType ==7){
                ivVerticalIcon.setScaleType(ImageView.ScaleType.CENTER_CROP);
            }
        }
    }

    /**
     * 设置右边的文字点击事件
     * @param onClickListener
     */
    public void setSubTitleOnClickListener(OnClickListener onClickListener){
        tvSubTitle.setOnClickListener(onClickListener);
    }
}
