package com.android.nytimes.view.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.nytimes.NYTimesMainActivity;
import com.android.nytimes.R;
import com.android.nytimes.data.ApiServiceInteractorImpl;
import com.android.nytimes.data.DataCallBack;
import com.android.nytimes.model.Artical;
import com.android.nytimes.model.Result;
import com.android.nytimes.presenter.ArticlesListFragmentPresenter;
import com.android.nytimes.presenter.NYTimesMainActivityPresenter;
import com.android.nytimes.view.adapters.ArticlesAdapter;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class ArticalsListFragment extends Fragment implements ArticlesListFragmentPresenter {

    RecyclerView mArticalsList;
    ArticlesAdapter mArticalsAdapter;
    View mView;
    private ArrayList<Artical> mArticleList = new ArrayList<>();
    private NYTimesMainActivityPresenter nyTimesMainActivityPresenter;

    public ArticalsListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (mView == null) {

            mView = inflater.inflate(R.layout.fragment_articals_list, container, false);
        }
        getArticalsList();
        mArticalsList = (RecyclerView) mView.findViewById(R.id.articalsList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        mArticalsList.setLayoutManager(mLayoutManager);
        mArticalsAdapter = new ArticlesAdapter(getActivity(), mArticleList);
        mArticalsList.setAdapter(mArticalsAdapter);
        return mView;
    }

    @Override
    public void updateList() {
        mArticalsAdapter = new ArticlesAdapter(getActivity(), mArticleList);
        mArticalsList.setAdapter(mArticalsAdapter);
    }

    @Override
    public void getArticalsList() {
        mArticleList.clear();
        nyTimesMainActivityPresenter.showProgressDialog();
        ApiServiceInteractorImpl apiServiceInteractor = new ApiServiceInteractorImpl();

        apiServiceInteractor.getArticles(new DataCallBack<List<Result>>() {
            @Override
            public void success(List<Result> results) {
                nyTimesMainActivityPresenter.dismissProgressDialog();
                for (Result result : results) {
                    Artical artical = new Artical();
                    artical.setTitle(result.getTitle());
                    artical.setAuthor(result.getByline());
                    artical.setPublished_date(result.getPublishedDate());
                    artical.setUrl(result.getUrl());
                    if (result.getMedia().size() > 0 && result.getMedia().get(0).getMediaMetadata().size() > 0)
                        artical.setImageurl(result.getMedia().get(0).getMediaMetadata().get(0).getUrl());
                    mArticleList.add(artical);
                    updateList();
                }
            }

            @Override
            public void failure(String message) {
                if (message != null)
                    Toast.makeText(getActivity(),
                            message, Toast.LENGTH_SHORT).show();
            }
        });
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        nyTimesMainActivityPresenter = (NYTimesMainActivity) context;
    }
}
