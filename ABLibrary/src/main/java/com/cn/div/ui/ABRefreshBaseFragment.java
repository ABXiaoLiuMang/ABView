package com.cn.div.ui;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.cn.div.adapter_rc.ABRVBaseAdapter;
import com.cn.div.adapter_rc.BaseViewHolder;
import com.cn.div.baseui.R;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshRecyclerView;


public abstract class ABRefreshBaseFragment<T> extends BaseFragment implements PullToRefreshBase.OnRefreshListener2<RecyclerView>,
        ABRVBaseAdapter.OnItemClickListener{
    protected PullToRefreshRecyclerView refreshView;
    protected RecyclerView recyclerView;
    protected ABRVBaseAdapter<T,BaseViewHolder> listAdapter;
    /**
     * @return 布局id
     */
    protected int getLayoutId() {
        return R.layout.pulltorefresh_fragment;
    }


    /**
     * 事件
     */
    protected void eventOnClick(){
        refreshView = view.findViewById(R.id.mRecyclerView);
        refreshView.setMode(setPullToRefreshBase());
        recyclerView = refreshView.getRefreshableView();
        recyclerView.setLayoutManager(getLayoutManager());
        RecyclerView.ItemDecoration itemDecoration = getItemDecoration();
        listAdapter = getListAdapter();
        if(itemDecoration != null){
            recyclerView.addItemDecoration(itemDecoration);
        }
        View headView = getHeadView();
        View footView = getFootView();
        View emptyView = getEmptyView();
        if(headView != null){
            listAdapter.addHeaderView(headView);
        }
        if(footView != null){
            listAdapter.addFooterView(footView);
        }

        if(emptyView != null){
            listAdapter.setEmptyView(emptyView);
        }

        recyclerView.setAdapter(listAdapter);
        listAdapter.setOnItemClickListener(this);
        refreshView.setOnRefreshListener(this);
    }

    // 底部上拉
    @Override
    public void onPullUpToRefresh(PullToRefreshBase<RecyclerView> refreshView) {
        onLoadMore();
    }

    // 顶部下拉
    @Override
    public void onPullDownToRefresh(PullToRefreshBase<RecyclerView> refreshView) {
        onRefresh();
    }

    /**
     * 可选以下参数 PullToRefreshBase.Mode.DISABLED 上下都不刷新
     * PullToRefreshBase.Mode.PULL_FROM_START 顶部下拉刷新
     * PullToRefreshBase.Mode.PULL_FROM_END 底部上拉刷新
     * PullToRefreshBase.Mode.BOTH 上下都刷新
     * PullToRefreshBase.Mode.MANUAL_REFRESH_ONLY禁用下拉刷新的手势操作，但允许手动设置刷新状态通过
     */
    public abstract PullToRefreshBase.Mode setPullToRefreshBase();

    public abstract @NonNull RecyclerView.LayoutManager getLayoutManager();

    public abstract RecyclerView.ItemDecoration getItemDecoration();

    /**
     * @return ListView适配器
     */
    public abstract ABRVBaseAdapter<T,BaseViewHolder> getListAdapter();

    /**
     * 需要的时候重写
     *
     * @return ListView添加头
     */
    public View getHeadView() {
        return null;
    }

    /**
     * 需要的时候重写
     *
     * @return ListView添加底
     */
    public View getFootView() {
        return null;
    }

    /**
     * 需要的时候重写
     * @return ListView添加没有数据时显示
     */
    public View getEmptyView(){
        return null;
    }

    /**
     * 下拉刷新加载
     */
    public abstract void onRefresh();

    /**
     * 加载更多
     */
    public abstract void onLoadMore();


}
