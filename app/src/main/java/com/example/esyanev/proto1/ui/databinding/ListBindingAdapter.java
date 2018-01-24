package com.example.esyanev.proto1.ui.databinding;

import android.databinding.BindingAdapter;
import android.support.v7.widget.RecyclerView;

import com.example.esyanev.proto1.data.Resource;
import com.example.esyanev.proto1.ui.BaseAdapter;

import java.util.List;

public final class ListBindingAdapter {
    @BindingAdapter(value = "resource")
    public static void setResource(RecyclerView recyclerView, Resource resource){
        RecyclerView.Adapter adapter = recyclerView.getAdapter();
        if(adapter == null)
            return;

        if(resource == null || resource.data == null)
            return;

        if(adapter instanceof BaseAdapter){
            ((BaseAdapter)adapter).setData((List) resource.data);
        }
    }
}
