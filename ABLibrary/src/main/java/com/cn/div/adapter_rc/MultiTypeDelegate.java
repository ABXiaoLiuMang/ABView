package com.cn.div.adapter_rc;

import android.support.annotation.LayoutRes;
import android.util.SparseIntArray;

import java.util.List;

import static com.cn.div.adapter_rc.ABBaseMultiAdapter.TYPE_NOT_FOUND;


public abstract class MultiTypeDelegate<T> {

    private static final int DEFAULT_VIEW_TYPE = -0xff;
    private SparseIntArray layouts;
    private boolean autoMode, selfMode;

    public MultiTypeDelegate(SparseIntArray layouts) {
        this.layouts = layouts;
    }

    public MultiTypeDelegate() {
    }

    public final int getDefItemViewType(List<T> data, int position) {
        T item = data.get(position);
        return item != null ? getItemType(item) : DEFAULT_VIEW_TYPE;
    }

    protected abstract int getItemType(T t);

    public final int getLayoutId(int viewType) {
        return this.layouts.get(viewType, TYPE_NOT_FOUND);
    }

    private void addItemType(int type, @LayoutRes int layoutResId) {
        if (this.layouts == null) {
            this.layouts = new SparseIntArray();
        }
        this.layouts.put(type, layoutResId);
    }

    public MultiTypeDelegate registerItemTypeAutoIncrease(@LayoutRes int... layoutResIds) {
        autoMode = true;
        checkMode(selfMode);
        for (int i = 0; i < layoutResIds.length; i++) {
            addItemType(i, layoutResIds[i]);
        }
        return this;
    }

    public MultiTypeDelegate registerItemType(int type, @LayoutRes int layoutResId) {
        selfMode = true;
        checkMode(autoMode);
        addItemType(type, layoutResId);
        return this;
    }

    private void checkMode(boolean mode) {
        if (mode) {
            throw new RuntimeException("Don't mess two register mode");
        }
    }
}
