package com.core.util;

import android.support.v4.app.Fragment;

import com.core.presentation.activity.BaseFragmentActivity;
import com.core.presentation.fragment.BaseFragment;
import com.core.util.AndroidUtils;
import com.roughike.bottombar.OnTabReselectListener;
import com.roughike.bottombar.OnTabSelectListener;
import java.util.Arrays;
import java.util.List;

/**
 * Created by jhonnybarrios on 12/22/17.
 */

public class TabNavigationController implements OnTabSelectListener, OnTabReselectListener {
    private List<BaseFragment> fragments;
    private BaseFragmentActivity activity;
    private TabToIndex tabToIndex;

    public void setFragments(BaseFragmentActivity activity,BaseFragment... fragments) {
        this.activity=activity;
        this.fragments= Arrays.asList(fragments);
        for (BaseFragment fragment : fragments) {
            activity.addFragment(fragment,false);
        }
    }

    public void setTabToIndex(TabToIndex tabToIndex) {
        this.tabToIndex = tabToIndex;
    }

    @Override public void onTabSelected(int tabId) {
        navigateToTab(tabToIndex.getTabIndex(tabId));
    }

    @Override public void onTabReSelected(int tabId) {
        resetTabBackStack(tabToIndex.getTabIndex(tabId));
    }

    private void navigateToTab(int position) {
        AndroidUtils.hideSoftKeyboard(activity);
        activity.showFragment(getRootFragment(position));
    }

    private void resetTabBackStack(int newIndex) {
        getRootFragment(newIndex).clearBackStack();
    }

    private BaseFragment getRootFragment(int index) {
        if(index<0) throw new IllegalStateException("Need to send an index that we know");
        return fragments.get(index);
    }

    public boolean allowBackPressed() {
        return ((BaseFragment)activity.getTopFragment()).allowBackPressed();
    }

    public interface TabToIndex {
        int getTabIndex(int tabId);
    }
}