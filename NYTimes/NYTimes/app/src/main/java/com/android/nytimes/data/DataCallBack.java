package com.android.nytimes.data;

/**
 * Created by sathish on 22-02-2018.
 */

public interface DataCallBack<T> {
    public void success(T T);

    public void failure(String message);
}
