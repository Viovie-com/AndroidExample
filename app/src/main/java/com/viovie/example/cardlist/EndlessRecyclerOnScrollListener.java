package com.viovie.example.cardlist;

import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

public class EndlessRecyclerOnScrollListener extends RecyclerView.OnScrollListener {
    public final static int START_PAGE = 1;

    protected boolean isLoading = false;
    protected int firstVisibleItem;
    protected int visibleItemCount;
    protected int totalItemCount;

    protected int currentPage = START_PAGE;

    protected int visibleThreshold = 0; // The minimum amount of items to have below your current scroll position before loading more.

    private EndlessListener mEndlessListener;
    private LinearLayoutManager mLinearLayoutManager;

    public EndlessRecyclerOnScrollListener(@NonNull LinearLayoutManager linearLayoutManager,
                                           @NonNull EndlessListener endlessListener) {
        this.mLinearLayoutManager = linearLayoutManager;
        this.mEndlessListener = endlessListener;
    }

    public void setVisibleThreshold(int threshold) {
        this.visibleThreshold = threshold;
    }

    @Override
    public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
        super.onScrollStateChanged(recyclerView, newState);

        if (!isLoading && newState == RecyclerView.SCROLL_STATE_IDLE &&
                (visibleItemCount + firstVisibleItem + visibleThreshold) >= totalItemCount) {
            isLoading = true;
            this.mEndlessListener.onLoadMore(++currentPage);
        }
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);

        totalItemCount = mLinearLayoutManager.getItemCount();
        visibleItemCount = mLinearLayoutManager.getChildCount();
        firstVisibleItem = mLinearLayoutManager.findFirstVisibleItemPosition();
    }

    public void loadMoreFinished() {
        isLoading = false;
    }

    public void reset(int page) {
        currentPage = page;
        loadMoreFinished();
    }

    public void reset() {
        reset(START_PAGE);
    }

    interface EndlessListener {
        void onLoadMore(int page);
    }
}
