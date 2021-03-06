package com.example.lebai.qiuda;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TabHost;
import android.widget.TabWidget;
import android.widget.TextView;
import android.widget.LinearLayout.LayoutParams;

import com.baidu.mapapi.SDKInitializer;

import miscutils.CommonUtil;


public class MainActivity extends ActionBarActivity  {

    TabHost mTabhost = null;
    private Context mContext = null;
    private int guestItemNum = 8;
    private int hostItemNum = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mContext = this;
        mTabhost = (TabHost) this.findViewById(R.id.tabHostMain);
        updateTab();

        int screenWidth = CommonUtil.getWidth(mContext);
        int screenHeight = CommonUtil.getHeight(mContext);
        float screenDensity = CommonUtil.getDensity(mContext);

        Log.v("MainActivity", "Screen w h density" + Integer.toString(screenWidth) +
                " " + Integer.toString(screenHeight) + " " + Float.toString(screenDensity));

        updateTabHostPage();
        updateTabGuestPage();
        SDKInitializer.initialize(getApplicationContext());
    }

    private void updateTab() {
        String[] title = new String[]{"求人搭","我来搭"};
        View hostTab = null, guestTab = null;
        View[] tabs = new View[]{hostTab,guestTab};
        int[] tabIds = new int[]{R.id.tabHost,R.id.tabGuest};

        mTabhost.setup();

        for(int i=0;i<tabs.length;i++){
            tabs[i] = LayoutInflater.from(this).inflate(R.layout.tabstyle, null);
            tabs[i].setBackgroundColor(Color.WHITE);
            TextView faketext = (TextView) tabs[i].findViewById(R.id.tab_focused);
            faketext.setText("");
            mTabhost.addTab(mTabhost.newTabSpec(title[i]).setIndicator(tabs[i]).setContent(tabIds[i]));

            if (i != mTabhost.getCurrentTab()) {
                tabs[i].setBackgroundColor(tabs[i].getResources().getColor(R.color.white));
                faketext.setVisibility(View.INVISIBLE);
                TextView text = (TextView) tabs[i].findViewById(R.id.tab_label);
                text.setText(title[i]);
                text.setTextColor(tabs[i].getResources().getColor(R.color.slategray));
            }
            else {
                tabs[i].setBackgroundColor(tabs[i].getResources().getColor(R.color.slategray));
                faketext.setVisibility(View.VISIBLE);
                TextView text = (TextView) tabs[i].findViewById(R.id.tab_label);
                text.setText(title[i]);
                text.setTextColor(tabs[i].getResources().getColor(R.color.white));
            }
        }

        mTabhost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
                final TabWidget tabWidget = mTabhost.getTabWidget();
                tabWidget.setStripEnabled(false);
                for (int i = 0; i < tabWidget.getChildCount(); i++) {
                    View tabview = tabWidget.getChildAt(i);
                    TextView faketext = (TextView) tabview.findViewById(R.id.tab_focused);
                    if (i != mTabhost.getCurrentTab()) {
                        tabview.setBackgroundColor(tabview.getResources().getColor(R.color.white));
                        faketext.setVisibility(View.INVISIBLE);
                        TextView text = (TextView) tabview.findViewById(R.id.tab_label);
                        text.setTextColor(tabview.getResources().getColor(R.color.slategray));
                    }
                    else {
                        tabview.setBackgroundColor(tabview.getResources().getColor(R.color.slategray));
                        faketext.setVisibility(View.VISIBLE);
                        TextView text = (TextView) tabview.findViewById(R.id.tab_label);
                        text.setTextColor(tabview.getResources().getColor(R.color.white));
                    }
                }
            }
        });
    }

    public void updateTabHostPage() {
        int[] imageViewId = new int[hostItemNum];
        int[] textViewId = new int[hostItemNum];
        int[] lastId = new int[hostItemNum];
        int[] imageViewWidth = new int[hostItemNum];
        int[] imageViewHeight = new int[hostItemNum];
        int[] imageViewMarginTop = new int[hostItemNum];
        int[] imageViewPadding = new int[hostItemNum];
        int[] textViewWidth = new int[hostItemNum];
        int[] textViewHeight = new int[hostItemNum];
        int[] textViewMarginTop = new int[hostItemNum];
        int[] textViewMarginLeft = new int[hostItemNum];
        int[] textViewPadding = new int[hostItemNum];
        int[] lastViewHeight = new int[hostItemNum];
        int[] lastViewWidth = new int[hostItemNum];
        int[] lastViewMarginTop = new int[hostItemNum];
        int[] lastViewMarginLeft = new int[hostItemNum];
        int[] lastViewPadding = new int[hostItemNum];
        int screenWidth = CommonUtil.getWidth(mContext);
        int screenHeight = CommonUtil.getHeight(mContext);

        imageViewId[0] = R.id.imgItemType;
        imageViewId[1] = R.id.imgItemTime;
        imageViewId[2] = R.id.imgItemDuration;

        textViewId[0] = R.id.textItemType;
        textViewId[1] = R.id.textItemTime;
        textViewId[2] = R.id.textItemDuration;

        lastId[0] = R.id.spinnerType;
        lastId[1] = R.id.editTextTime;
        lastId[2] = R.id.spinnerDuration;

        for (int i=0;i<hostItemNum;i++) {
            imageViewWidth[i] = imageViewHeight[i] = screenWidth / 10;
            imageViewMarginTop[i] = CommonUtil.dip2px(mContext, 32)
                    + (imageViewHeight[i] + CommonUtil.dip2px(mContext, 16)) * i;
            imageViewPadding[i] = imageViewHeight[i] / 8;

            textViewWidth[i] = screenWidth * 2 / 10;
            textViewHeight[i] = imageViewHeight[i];
            textViewMarginTop[i] = imageViewMarginTop[i];
            textViewMarginLeft[i] = imageViewWidth[i];
            textViewPadding[i] = textViewHeight[i] / 8;

            ImageView iv = (ImageView)findViewById(imageViewId[i]);
            RelativeLayout.LayoutParams ivlp = new
                    RelativeLayout.LayoutParams(imageViewWidth[i], imageViewHeight[i]);
            ivlp.topMargin = imageViewMarginTop[i];
            iv.setLayoutParams(ivlp);
            iv.setPadding(imageViewPadding[i], imageViewPadding[i], imageViewPadding[i], imageViewPadding[i]);

            TextView tv = (TextView)findViewById(textViewId[i]);
            RelativeLayout.LayoutParams tvlp = new
                    RelativeLayout.LayoutParams(textViewWidth[i], textViewHeight[i]);
            tvlp.topMargin = textViewMarginTop[i];
            tvlp.leftMargin = textViewMarginLeft[i];
            tv.setLayoutParams(tvlp);
            tv.setPadding(textViewPadding[i], textViewPadding[i], textViewPadding[i], textViewPadding[i]);
            tv.setTextColor(getResources().getColor(R.color.black));
            tv.setTextSize(TypedValue.COMPLEX_UNIT_PX, textViewHeight[i]/2);


        }

        String[] listCategory = getResources().getStringArray(R.array.category_array);
        Spinner spinnerType = (Spinner)findViewById(R.id.spinnerType);
        SearchSpinnerAdapter adapterCategory = new SearchSpinnerAdapter(this, listCategory);
        adapterCategory.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerType.setAdapter(adapterCategory);
        spinnerType.setSelection(0, true);

        String[] listDuration = getResources().getStringArray(R.array.duration_array);
        Spinner spinnerDuration = (Spinner)findViewById(R.id.spinnerDuration);
        SearchSpinnerAdapter adapterDuration = new SearchSpinnerAdapter(this, listDuration);
        adapterCategory.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerDuration.setAdapter(adapterDuration);
        spinnerDuration.setSelection(0, true);

    }

    public void updateTabGuestPage() {
        int[] imageViewId = new int[guestItemNum];
        int[] textViewId = new int[guestItemNum];
        int[] picWidth = new int[guestItemNum];
        int[] picHeight = new int[guestItemNum];
        int[] picPaddingLeft = new int[guestItemNum];
        int[] picPaddingTop = new int[guestItemNum];
        int[] txtPaddingTop = new int[guestItemNum];
        int[] txtPaddingLeft = new int[guestItemNum];
        int[] txtWidth = new int[guestItemNum];
        int[] txtHeight = new int[guestItemNum];
        int[] heightCtn = new int[guestItemNum];
        int screenWidth = CommonUtil.getWidth(mContext);
        int screenHeight = CommonUtil.getHeight(mContext);

        imageViewId[0] = R.id.imgItemSoccer;
        imageViewId[1] = R.id.imgItemBasketball;
        imageViewId[2] = R.id.imgItemBadminton;
        imageViewId[3] = R.id.imgItemPingpong;
        imageViewId[4] = R.id.imgItemCards;
        imageViewId[5] = R.id.imgItemChess;
        imageViewId[6] = R.id.imgItemSnooker;
        imageViewId[7] = R.id.imgItemAll;

        textViewId[0] = R.id.textItemSoccer;
        textViewId[1] = R.id.textItemBasketball;
        textViewId[2] = R.id.textItemBadminton;
        textViewId[3] = R.id.textItemPingpong;
        textViewId[4] = R.id.textItemCards;
        textViewId[5] = R.id.textItemChess;
        textViewId[6] = R.id.textItemSnooker;
        textViewId[7] = R.id.textItemAll;

        for (int i=0;i<(guestItemNum/2);i++) {
            picWidth[i] = (int) (screenWidth / 5);
            picHeight[i] = picWidth[i];
            picPaddingLeft[i] = (int)(screenWidth / 30) * (i+1) + picWidth[i] * i;
            picPaddingTop[i] = CommonUtil.dip2px(mContext, 32);

            heightCtn[i] = 40;
            txtPaddingTop[i] = picPaddingTop[i] + picHeight[i] + 4;
            txtPaddingLeft[i] = picPaddingLeft[i];
            txtWidth[i] = picWidth[i];
            txtHeight[i] = CommonUtil.sp2px(mContext, heightCtn[i]) + 4;

            Log.v("MainActivity", Integer.toString(i));
            Log.v("MainActivity", "Image pos l t w h " + Integer.toString(picPaddingLeft[i])
                    + " " + Integer.toString(picPaddingTop[i]) + " " + Integer.toString(picWidth[i]) + " " + Integer.toString(picHeight[i]));
            Log.v("MainActivity", "Text pos l t w h " + Integer.toString(txtPaddingLeft[i])
                    + " " + Integer.toString(txtPaddingTop[i]) + " " + Integer.toString(txtWidth[i]) + " " + Integer.toString(txtHeight[i]));

            ImageView iv = (ImageView)findViewById(imageViewId[i]);
            RelativeLayout.LayoutParams ivlp = new RelativeLayout.LayoutParams(
                    picWidth[i], picHeight[i]);
            ivlp.leftMargin = picPaddingLeft[i];
            ivlp.topMargin = picPaddingTop[i];
            iv.setLayoutParams(ivlp);

            TextView tv = (TextView)findViewById(textViewId[i]);
            RelativeLayout.LayoutParams tvlp = new RelativeLayout.LayoutParams(
                    txtWidth[i], txtHeight[i]);
            tvlp.leftMargin = txtPaddingLeft[i];
            tvlp.topMargin = txtPaddingTop[i];
            tv.setLayoutParams(tvlp);
            tv.setTextSize(TypedValue.COMPLEX_UNIT_PX, heightCtn[i]);
        }

        for (int i=(guestItemNum/2);i<guestItemNum;i++) {
            picWidth[i] = (int) (screenWidth / 5);
            picHeight[i] = picWidth[i];
            picPaddingLeft[i] = (int)(screenWidth / 30) * (i+1-(guestItemNum/2)) + picWidth[i] * (i-(guestItemNum/2));
            picPaddingTop[i] = 24 + txtPaddingTop[i - (guestItemNum/2)] + txtHeight[i - (guestItemNum/2)];

            heightCtn[i] = 40;
            txtPaddingLeft[i] = picPaddingLeft[i];
            txtPaddingTop[i] = picPaddingTop[i] + picHeight[i] + 4;
            txtWidth[i] = picWidth[i];
            txtHeight[i] = CommonUtil.sp2px(mContext, heightCtn[i]) + 4;

            Log.v("MainActivity", Integer.toString(i));
            Log.v("MainActivity", "Image pos l t w h " + Integer.toString(picPaddingLeft[i])
                    + " " + Integer.toString(picPaddingTop[i]) + " " + Integer.toString(picWidth[i]) + " " + Integer.toString(picHeight[i]));
            Log.v("MainActivity", "Text pos l t w h " + Integer.toString(txtPaddingLeft[i])
                    + " " + Integer.toString(txtPaddingTop[i]) + " " + Integer.toString(txtWidth[i]) + " " + Integer.toString(txtHeight[i]));

            ImageView iv = (ImageView)findViewById(imageViewId[i]);
            RelativeLayout.LayoutParams ivlp = new RelativeLayout.LayoutParams(
                    picWidth[i], picHeight[i]);
            ivlp.leftMargin = picPaddingLeft[i];
            ivlp.topMargin = picPaddingTop[i];
            iv.setLayoutParams(ivlp);

            TextView tv = (TextView)findViewById(textViewId[i]);
            RelativeLayout.LayoutParams tvlp = new RelativeLayout.LayoutParams(
                    txtWidth[i], txtHeight[i]);
            tvlp.leftMargin = txtPaddingLeft[i];
            tvlp.topMargin = txtPaddingTop[i];
            tv.setLayoutParams(tvlp);
            tv.setTextSize(TypedValue.COMPLEX_UNIT_PX, heightCtn[i]);
        }

        for(int i=0;i<guestItemNum;i++) {
            ImageView iv = (ImageView)findViewById(imageViewId[i]);
            iv.setId(i);
            iv.setOnClickListener(new ButtonListener());
        }
   }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private class ButtonListener implements View.OnClickListener{

        public void onClick(View v) {
            // TODO Auto-generated method stub
            Bundle bun = new Bundle();
            Intent intent = new Intent();
            Log.v("MainActivity", "Click button" + v.getId());
            bun.putInt("index", v.getId());
            intent.putExtras(bun);
            intent.setClass(MainActivity.this, SearchListActivity.class);
            startActivity(intent);
        }

    }

}
