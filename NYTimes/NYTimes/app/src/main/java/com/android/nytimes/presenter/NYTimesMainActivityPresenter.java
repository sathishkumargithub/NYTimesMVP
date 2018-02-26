package com.android.nytimes.presenter;

/**
 * Created by sathish on 26-02-2018.
 */

public interface NYTimesMainActivityPresenter {
    void loadListFragment();
    void showProgressDialog();
    void dismissProgressDialog();
    void openDetailFragment(String url );
}
