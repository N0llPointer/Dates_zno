package com.nollpointer.dates_zno.statistics;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.nollpointer.dates_zno.R;

public class StatisticsCardsAdapter extends RecyclerView.Adapter<StatisticsCardsAdapter.ViewHolder>{
    private StatisticsCardsAdapter.Listener mListener;

    public interface Listener {
        void onClick(int position);
    }

    public void setListener(StatisticsCardsAdapter.Listener listener) {
        mListener = listener;
    }


    public StatisticsCardsAdapter() {

    }

    @Override
    public StatisticsCardsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        CardView c = (CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.card_statistics, parent, false);
        return new StatisticsCardsAdapter.ViewHolder(c);
    }

    @Override
    public void onBindViewHolder(StatisticsCardsAdapter.ViewHolder holder, final int position) {


    }

    @Override
    public int getItemCount() {
        return 16;
    }

    public boolean onItemMove(int fromPosition, int toPosition) {
//        if (fromPosition < toPosition) {
//            for (int i = fromPosition; i < toPosition; i++) {
//                Collections.swap(mItems, i, i + 1);
//            }
//        } else {
//            for (int i = fromPosition; i > toPosition; i--) {
//                Collections.swap(mItems, i, i - 1);
//            }
//        }
        notifyItemMoved(fromPosition, toPosition);
        return true;
    }



    public class ViewHolder extends RecyclerView.ViewHolder {
        ViewHolder(CardView c) {
            super(c);
//            c.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    if (mListener != null)
//                        mListener.onClick(getAdapterPosition());
//                }
//            });
        }
    }
}