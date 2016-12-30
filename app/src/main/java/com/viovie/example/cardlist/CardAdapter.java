package com.viovie.example.cardlist;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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
    public void onBindViewHolder(final CardViewHolder holder, int position) {
        Card card = cardList.get(position);

        holder.getCardImage().setImageResource(R.mipmap.ic_launcher);
        holder.getCardTitle().setText(card.title);
        holder.getCardContent().setText(card.content);
        holder.getMoreButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String msg = "Collapse";
                if (holder.getDefaultHeight() == holder.getCardImage().getHeight()) {
                    msg = "Collapse";
                    Tool.toggleView(holder.getCardImage(), 100, 0);
                } else {
                    msg = "Expand";
                    Tool.toggleView(holder.getCardImage(), 100, holder.getDefaultHeight());
                }
                ((Button) view).setText(msg);
            }
        });
        holder.getRotateButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Tool.rotateView(holder.getCardImage(), 0, 1000);
            }
        });
    }

    @Override
    public int getItemCount() {
        return cardList.size();
    }

    class CardViewHolder extends RecyclerView.ViewHolder {
        private ImageView cardImage;
        private TextView cardTitle;
        private TextView cardContent;
        private Button moreButton;
        private Button rotateButton;

        private int defaultHeight;

        public CardViewHolder(View itemView) {
            super(itemView);

            cardImage = (ImageView) itemView.findViewById(R.id.card_image);
            cardTitle = (TextView) itemView.findViewById(R.id.card_title);
            cardContent = (TextView) itemView.findViewById(R.id.card_content);
            moreButton = (Button) itemView.findViewById(R.id.toggle);
            rotateButton = (Button) itemView.findViewById(R.id.rotate);

            defaultHeight = 240;
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

        public Button getMoreButton() {
            return moreButton;
        }

        public int getDefaultHeight() {
            return defaultHeight;
        }

        public Button getRotateButton() {
            return rotateButton;
        }
    }
}
