package com.example.administrator.tablayout;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.example.administrator.adapter.MyFragmentPagerAdapter;
import com.example.administrator.com.example.administrator.fragment.MyFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TabLayout tablayout;
    private ViewPager viewPager;
    private FragmentManager fragmentManager;
    private List<Fragment> list;
    private Fragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        fragmentManager = getSupportFragmentManager();

        intiFragment();
        intiViewPager();
        intiLayout();


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    //初始化Fragment视图
    private void intiFragment() {

        String[] titles = {"头条","百科","资讯","经营","数据"};
        String[] urls = {"http://sns.maimaicha.com/api?apikey=b4f4ee31a8b9acc866ef2afb754c33e6&format=json&method=news.getHeadlines",
                "http://sns.maimaicha.com/api?apikey=b4f4ee31a8b9acc866ef2afb754c33e6&format=json&method=news.getListByType&type=16",
                "http://sns.maimaicha.com/api?apikey=b4f4ee31a8b9acc866ef2afb754c33e6&format=json&method=news.getListByType&type=52",
                "http://sns.maimaicha.com/api?apikey=b4f4ee31a8b9acc866ef2afb754c33e6&format=json&method=news.getListByType&type=53",
                "http://sns.maimaicha.com/api?apikey=b4f4ee31a8b9acc866ef2afb754c33e6&format=json&method=news.getListByType&type=54"};

        list = new ArrayList<Fragment>();

        for (int i = 0; i <titles.length;i++){
            fragment = new MyFragment();
            Bundle bundle = new Bundle();
            bundle.putString("title",titles[i]);
            bundle.putString("url",urls[i]);
            fragment.setArguments(bundle);
            list.add(fragment);
        }

    }

    //初始化ViewPager的方法
    private void intiViewPager() {

        viewPager = (ViewPager) findViewById(R.id.view_pager);
        MyFragmentPagerAdapter adapter = new MyFragmentPagerAdapter(fragmentManager,list);
        viewPager.setAdapter(adapter);

    }

    //初始化TabLayout的方法
    private void intiLayout() {

        tablayout = (TabLayout) findViewById(R.id.tabLayout);

        //设置TabLayout的模式：第一种模式是默认的，不会滚动的
        //第二种模式，内容超出了屏幕，支持水平滑动，可以根据点击item进行滑动，
        //tablayout.setTabMode(TabLayout.MODE_FIXED);
        tablayout.setTabMode(TabLayout.MODE_SCROLLABLE);

        //一般情况下TabLayout都是和ViewPager一起使用的
       tablayout.setupWithViewPager(viewPager);

        /*//设置TabLayout的item
        tablayout.addTab(tablayout.newTab().setText("体育新闻"));
        tablayout.addTab(tablayout.newTab().setText("娱乐新闻"));
        tablayout.addTab(tablayout.newTab().setText("社会新闻"));
        tablayout.addTab(tablayout.newTab().setText("生活新闻"));
        tablayout.addTab(tablayout.newTab().setText("财经新闻"));
        tablayout.addTab(tablayout.newTab().setText("武汉新闻"));
        tablayout.addTab(tablayout.newTab().setText("动物新闻"));
        tablayout.addTab(tablayout.newTab().setText("无聊新闻"));
        tablayout.addTab(tablayout.newTab().setText("笑话新闻"));
*/
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
