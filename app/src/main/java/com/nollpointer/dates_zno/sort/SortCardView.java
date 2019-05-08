package com.nollpointer.dates_zno.sort;

import android.view.View;
import android.widget.TextView;

import androidx.cardview.widget.CardView;

import com.nollpointer.dates_zno.R;

public class SortCardView {
    CardView card;

    TextView number;
    TextView event;

    SortCardView(CardView card) {
        this.card = card;
        number = card.findViewById(R.id.text1);
        event = card.findViewById(R.id.text2);
    }

    public void setBackgroundColor(int color) {
        card.setCardBackgroundColor(color);
    }

    public void setOnClickListener(View.OnClickListener listener) {
        card.setOnClickListener(listener);
    }

    public void setEvent(String text) {
        event.setText(text);
    }

    public void setNumber(int number) {
        this.number.setText(Integer.toString(number));
    }

    //public void setResultScreenText(String)


}
