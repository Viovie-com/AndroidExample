package com.viovie.example.cardlist;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.viovie.example.R;
import com.viovie.example.cardlist.bean.Card;

import java.util.ArrayList;
import java.util.List;

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.CardViewHolder> {
    List<Card> cardList;

    public CardAdapter(List<Card> cardList) {
        this.cardList = cardList;
        if (this.cardList == null) {
            this.cardList = new ArrayList<>();
        }
    }

    @Override
    public CardViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_list_card, parent, false);
        return new CardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CardViewHolder holder, int position) {
        Card card = cardList.get(position);

        holder.getCardImage().setImageResource(R.mipmap.ic_launcher);
        holder.getCardTitle().setText(card.title);
        holder.getCardContent().setText(card.content);
    }

    @Override
    public int getItemCount() {
        return cardList.size();
    }

    class CardViewHolder extends RecyclerView.ViewHolder {
        private ImageView cardImage;
        private TextView cardTitle;
        private TextView cardContent;

        public CardViewHolder(View itemView) {
            super(itemView);

            cardImage = (ImageView) itemView.findViewById(R.id.card_image);
            cardTitle = (TextView) itemView.findViewById(R.id.card_title);
            cardContent = (TextView) itemView.findViewById(R.id.card_content);
        }

        public ImageView getCardImage() {
            return cardImage;
        }

        public TextView getCardTitle() {
            return cardTitle;
        }

        public TextView getCardContent() {
            return cardContent;
        }
    }
}
