package com.wassimbh.exam2021.utilities;

import android.os.Parcelable;

public interface OnRecycleItemClicked <T> {


    void onRecyclerItemClicked(int pos, T entity);
    void onRemoveClicked(int pos, T entity);
}
