package com.nollpointer.dates_zno;


import android.app.Fragment;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class DatesFragment extends Fragment{
    private Cursor crs;
    private RecyclerView rc;
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rc = (RecyclerView) inflater.inflate(R.layout.fragment_dates, container, false);
        MainActivity ctx = (MainActivity) getActivity();
        rc.setLayoutManager(new LinearLayoutManager(ctx));
        crs = ctx.getCursor();
        crs.moveToFirst();
        DatesCardsAdapter d = new DatesCardsAdapter(crs,ctx.getMode(),(ctx.getMode() == 0) ? ctx.getResources().getStringArray(R.array.centuries) : ctx.getResources().getStringArray(R.array.centuries_easy));
        rc.setAdapter(d);
        return rc;
    }

    @Override
    public void onResume() {
        super.onResume();
        BottomNavigationView bn = getActivity().findViewById(R.id.navigation);
        ((MainActivity)getActivity()).getSupportActionBar().setTitle(Html.fromHtml("<font color='#ffffff'>" + getResources().getString(R.string.title_dates) + "</font>"));
        if(bn.getSelectedItemId() != R.id.navigetion_dates)
            bn.setSelectedItemId(R.id.navigetion_dates);
    }
    public void setStartPosition(){
        rc.scrollToPosition(0);
    }

    public void refresh() {
        MainActivity ctx = (MainActivity)getActivity();
        crs = ctx.getCursor();
        DatesCardsAdapter d = new DatesCardsAdapter(crs,ctx.getMode(),(ctx.getMode() == 0) ? ctx.getResources().getStringArray(R.array.centuries) : ctx.getResources().getStringArray(R.array.centuries_easy));
        rc.setAdapter(d);
    }
}
