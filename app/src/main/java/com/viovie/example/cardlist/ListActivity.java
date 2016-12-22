package com.viovie.example.cardlist;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.viovie.example.R;
import com.viovie.example.cardlist.bean.Card;

import java.util.ArrayList;
import java.util.List;

public class ListActivity extends AppCompatActivity implements
        SwipeRefreshLayout.OnRefreshListener, EndlessRecyclerOnScrollListener.EndlessListener {

    private List<Card> cardList;

    private SwipeRefreshLayout refreshLayout;
    private RecyclerView recyclerView;
    private CardAdapter cardAdapter;
    private EndlessRecyclerOnScrollListener endlessScrollListener;

    private int mLimit = 10;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.card_list_activity_list);

        cardList = new ArrayList<>();

        refreshLayout = (SwipeRefreshLayout) findViewById(R.id.refresh_layout);
        refreshLayout.setOnRefreshListener(this);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view_list);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        cardAdapter = new CardAdapter(cardList);
        recyclerView.setAdapter(cardAdapter);

        endlessScrollListener = new EndlessRecyclerOnScrollListener(layoutManager, this);
        recyclerView.addOnScrollListener(endlessScrollListener);

        // initial load data
        onLoadMore(EndlessRecyclerOnScrollListener.START_PAGE);
    }

    private void buildSampleCardList(int size) {
        for (int i = 0 ; i<size ; i++) {
            cardList.add(new Card("This is title", "Hey, this is content, do you want more?"));
        }
    }

    @Override
    public void onRefresh() {
        onLoadMore(EndlessRecyclerOnScrollListener.START_PAGE);
    }

    @Override
    public void onLoadMore(int page) {
        if (page == EndlessRecyclerOnScrollListener.START_PAGE) {
            cardList.clear();
            endlessScrollListener.reset();
        }
        buildSampleCardList(mLimit);
        cardAdapter.notifyDataSetChanged();
        refreshLayout.setRefreshing(false);
        endlessScrollListener.loadMoreFinished();
    }
}
