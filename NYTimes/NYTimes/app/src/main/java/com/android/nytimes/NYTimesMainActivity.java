package com.android.nytimes;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.android.nytimes.presenter.NYTimesMainActivityPresenter;
import com.android.nytimes.view.fragments.ArticalDetailFragment;
import com.android.nytimes.view.fragments.ArticalsListFragment;

public class NYTimesMainActivity extends AppCompatActivity implements NYTimesMainActivityPresenter {


    private ProgressDialog mProgressDialog;
    private FragmentManager mManager;
    private ArticalsListFragment mArticalsListFragment;
    private static boolean IS_DETAIL_FRAGMENT = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nytimes_main);

        mManager = getSupportFragmentManager();
        loadListFragment();
    }


    @Override
    public void onBackPressed() {
        if (getFragmentManager().getBackStackEntryCount() > 0) {
            getFragmentManager().popBackStack();
            IS_DETAIL_FRAGMENT = false;
        } else {
            super.onBackPressed();
        }

    }

    @Override
    public void loadListFragment() {
        mArticalsListFragment = new ArticalsListFragment();
        FragmentTransaction transaction = mManager.beginTransaction();
        if (mManager.findFragmentByTag(getString(R.string.txt_listfragment)) == null)
            transaction.add(R.id.main_container, mArticalsListFragment, getString(R.string.txt_listfragment)).commit();

    }

    @Override
    public void showProgressDialog() {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(this);
            mProgressDialog.setCancelable(false);
            mProgressDialog.setMessage(getString(R.string.txt_loading));
        }
        mProgressDialog.show();
    }

    @Override
    public void dismissProgressDialog() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }

    @Override
    public void openDetailFragment(String url) {
        IS_DETAIL_FRAGMENT = true;
        ArticalDetailFragment mArticalDetailFragment = new ArticalDetailFragment();
        Bundle bundle = new Bundle();
        bundle.putString(getString(R.string.txt_key_url), url);
        mArticalDetailFragment.setArguments(bundle);
        mManager.beginTransaction().add(R.id.main_container, mArticalDetailFragment, getString(R.string.txt_detailfragment)).addToBackStack(null).commit();

    }

}
