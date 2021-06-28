# TitleNavigationView

[![Release](https://jitpack.io/v/com.gitee.quanguanzhou/TitleNavigationView.svg)](https://jitpack.io/v/com.gitee.quanguanzhou/TitleNavigationView.svg)

#### 简介
一款在我的页面与设备页面使用频率较高的组合控件，提高了开发的效率。

#### Gitee 地址：[TitleNavigationView](https://gitee.com/quanguanzhou/TitleNavigationView)
#### 先看预览图

<img src="./doc/QQ%E6%88%AA%E5%9B%BE20181009162952.png" alt="我是缩小后的图"/>

#### 用法项目级别 build.gradle：
```xml
    allprojects {
        repositories {
            maven {url'https://jitpack.io' }
        }
    }
```
#### 应用级别 build.gradle
```xml
    dependencies {
        implementation 'com.gitee.quanguanzhou:TitleNavigationView:v2.0'
    }
```
#### Maven
```xml
    <!-- <repositories> section of pom.xml -->
    <repository>
        <id>jitpack.io</id>
       <url>https://jitpack.io</url>
    </repository>


    <!-- <dependencies> section of pom.xml -->
    <dependency>
        <groupId>com.gitee.quanguanzhou</groupId>
        <artifactId>TitleNavigationView</artifactId>
        <version>v2.0</version>
    </dependency>
```
#### 布局上使用
```xml
    <com.ql.dev.library.TitleNavView
        android:id="@+id/titleNav"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_marginTop="@dimen/space_12"
        app:arrowMarginLeft="12sp"
        app:arrowMarginRight="12sp"
        app:explainText="sdf"
        app:explainTextMarginLeft="12dp"
        app:explainTextSize="12sp"
        app:isShowIcon="false"
        app:isShowSubTitles="false"
        app:isShowType="arrowType"
        app:titlesText="不带图标的按钮"
        app:titlesTextColor="@color/common_mine_text_color"
        app:titlesTextMarginLeft="12sp"
        app:titlesTextMarginRight="12sp"
        app:titlesTextSize="14sp" />
```
#### 代码支持的方法
```java
    TitleNavView navView = findViewById(R.id.titleNav);
    navView.setTitleNavOnClickListener(new View.OnClickListener() {//点击事件
        @Override
        public void onClick(View v) {

        }
    });
     navView.setTitleNavOnClickListener(new TitleNavView.TitleNavSwitchViewCallback(){//
                @Override
                public void TitleNavSwitchViewOnCallback() {
                    super.TitleNavSwitchViewOnCallback();
                }

                @Override
                public void TitleNavSwitchViewOffCallback() {
                    super.TitleNavSwitchViewOffCallback();
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
```

### 该导航的属性:

```xml

<declare-styleable name="titleNavView">
        <!-- 导航视图的显示方式， -->
        <attr name="navViewType" format="enum">
            <!-- 水平方式,长方型 ,默认为水平方式-->
            <enum name="horizontal" value="0" />
            <!--  垂直方向，方型-->
            <enum name="vertical" value="1" />
        </attr>
        <!-- 是否显示红点 -->
        <attr name="isShowDotType" format="enum">
            <!-- 红点 -->
            <enum name="dot" value="0" />
            <!-- 自定义提示图片，需要结合isShowDotPic属性共同使用 -->
            <enum name="custom" value="1" />
            <!-- 不显示 -->
            <enum name="none" value="2" />
        </attr>
        <!-- 自定义红点提示的图片 -->
        <attr name="isShowDotPic" format="integer|reference" />
        <!-- 设置总高度 -->
        <attr name="navHeight" format="dimension|reference" />
        <!-- 是否显示前面的图片-->
        <attr name="isShowIcon" format="boolean" />
        <!-- 设置图片的左边距-->
        <attr name="iconMarginLeft" format="dimension|reference" />
        <!-- 设置图片的上方距*****************************-->
        <attr name="iconMarginTop" format="dimension|reference" />
        <!-- 设置图片的底部距离**************************-->
        <attr name="iconMarginBottom" format="dimension|reference" />
        <!-- 设置图片的又边距-->
        <attr name="iconMarginRight" format="dimension|reference" />
        <!-- 设置图片资源-->
        <attr name="iconResource" format="integer|reference" />
        <!--设置标题的文字 -->
        <attr name="titlesText" format="string|reference" />
        <!-- 设置文字的大小-->
        <attr name="titlesTextSize" format="dimension" />
        <!-- 设置文字标题的左间距-->
        <attr name="titlesTextMarginLeft" format="dimension" />
        <!-- 设置文字标题的右间距-->
        <attr name="titlesTextMarginRight" format="dimension" />
        <!-- 设置文字标题的上间距***************************************-->
        <attr name="titlesTextMarginTop" format="dimension" />
        <!-- 设置文字标题的底部间距***************************************-->
        <attr name="titlesTextMarginBottom" format="dimension" />
        <!-- 设置文字颜色-->
        <attr name="titlesTextColor" format="color|reference" />
        <!-- 尾部是显示箭头还是显示开关 -->
        <attr name="isShowType" format="enum">
            <!-- 显示尾部箭头-->
            <enum name="arrowType" value="0" />
            <!-- 是否显示开关按钮，当显示开关按钮就不会显示尾部箭头 -->
            <enum name="switchType" value="1" />
            <!-- 显示文字 -->
            <enum name="fontType" value="2" />
            <!-- 什么都不显示。 -->
            <enum name="none" value="3" />
        </attr>
        <!-- 在显示开关的时候、是否默认打开开关 -->
        <attr name="switchOpen" format="boolean" />
        <!-- 功能性描述文字的显示方向 -->
        <attr name="showDescType" format="enum">
            <enum name="vertical" value="0" />
            <enum name="horizontal" value="1" />
        </attr>
        <!-- 功能说明的文字   跟在主标题的下面 或者 跟在主标题的后面 根据showDescType进行区分-->
        <attr name="explainText" format="string|reference" />
        <!-- 功能说明文字距离左边、右边的距离 -->
        <attr name="explainTextMarginLeft" format="dimension|reference" />
        <attr name="explainTextMarginRight" format="dimension|reference" />
        <!-- 功能说明文字的字体大小 -->
        <attr name="explainTextSize" format="dimension" />
        <!-- 右边的文字，当isShowType:为font时可用 -->
        <attr name="titleRightText" format="string|reference" />
        <!-- 设置尾部箭头的左边距-->
        <attr name="arrowMarginLeft" format="dimension|reference" />
        <!-- 设置尾部箭头的右边距-->
        <attr name="arrowMarginRight" format="dimension|reference" />
        <!-- 是否显示附属标题-->
        <attr name="isShowSubTitles" format="boolean" />
        <!-- 设置副标题的文字-->
        <attr name="subTitlesText" format="string|reference" />
        <!-- 设置副标题的文字大小-->
        <attr name="subTitlesTextSize" format="dimension" />
        <!-- 设置副标题的文字颜色-->
        <attr name="subTitlesTextColor" format="color|reference" />
        <!-- 设置副标题的左右上下边距-->
        <attr name="subTitlesMarginLeft" format="dimension|reference" />
        <attr name="subTitlesMarginRight" format="dimension|reference" />
        <attr name="subTitlesMarginTop" format="dimension|reference" />
        <attr name="subTitlesMarginBottom" format="dimension|reference" />
    </declare-styleable>
```

#### 支持的事件：
```java

    public void setTitleNavOnClickListener(OnClickListener onClickListener) {
        llTitleNav.setOnClickListener(onClickListener);
    }

    public static class TitleNavSwitchViewCallback {
        /**
         * SwitchView为打开时的回调
         */
        public void TitleNavSwitchViewOnCallback() {
        }

        /**
         * SwitchView为关闭时的回调
         */
        public void TitleNavSwitchViewOffCallback() {
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
```
#### 希望大家能给我星...

#### 首次更新Github，如果您觉得该库对您有帮助，可以考虑给点水钱
<img src="./doc/IMG_5514.PNG" width="340" height="450" alt="我是缩小后的图"/>
<img src="./doc/IMG_4845.JPG" width="340" height="400" alt="我是缩小后的图"/>
<img src="./doc/IMG_4846.JPG" width="350" height="500" alt="我是缩小后的图"/>



