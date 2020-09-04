package com.xuexiang.xpagedemo.fragment;

import androidx.annotation.NonNull;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.xuexiang.xpage.annotation.Page;
import com.xuexiang.xpage.base.XPageFragment;
import com.xuexiang.xpage.utils.TitleBar;
import com.xuexiang.xpagedemo.R;
import com.xuexiang.xutil.common.CollectionUtils;
import com.xuexiang.xutil.tip.ToastUtils;


/**
 * @author xuexiang
 * @since 2019-06-19 14:29
 */
@Page(name = "Tab主页联动使用")
public class BottomNavigationViewFragment extends XPageFragment implements ViewPager.OnPageChangeListener, BottomNavigationView.OnNavigationItemSelectedListener {


    Toolbar toolbar;

    ViewPager viewPager;

    FloatingActionButton fab;

    BottomNavigationView bottomNavigation;


    @Override
    protected void initArgs() {
        super.initArgs();
        toolbar = mRootView.findViewById(R.id.toolbar);
        viewPager = mRootView.findViewById(R.id.view_pager);
        fab = mRootView.findViewById(R.id.fab);
        bottomNavigation = mRootView.findViewById(R.id.bottom_navigation);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_bottom_navigationview;
    }

    @Override
    protected TitleBar initTitleBar() {
        toolbar.setNavigationIcon(R.drawable.ic_navigation_back_white);
        toolbar.setTitle("Tab主页联动使用");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popToBack();
            }
        });
        return null;
    }

    int[] menuItemIds = new int[]{R.id.item_dashboard, R.id.item_photo, R.id.item_music, R.id.item_movie};
    String[] titles = new String[]{"资讯", "照片", "音乐", "电影"};

    @Override
    protected void initViews() {
        FragmentAdapter<XPageFragment> adapter = new FragmentAdapter<>(getChildFragmentManager());
        for (String title : titles) {
            adapter.addFragment(SimpleListFragment.newInstance(title), title);
        }
        viewPager.setOffscreenPageLimit(titles.length - 1);
        viewPager.setAdapter(adapter);
    }

    @Override
    protected void initListeners() {
        viewPager.addOnPageChangeListener(this);
        bottomNavigation.setOnNavigationItemSelectedListener(this);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtils.toast("新建");
            }
        });
    }

    @Override
    public void onPageScrolled(int i, float v, int i1) {

    }

    @Override
    public void onPageSelected(int position) {
        bottomNavigation.getMenu().getItem(position).setChecked(true);
    }

    @Override
    public void onPageScrollStateChanged(int i) {

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        int index = CollectionUtils.arrayIndexOf(titles, menuItem.getTitle());
        if (index != -1) {
            viewPager.setCurrentItem(index, true);
            return true;
        }
        return false;
    }

}
