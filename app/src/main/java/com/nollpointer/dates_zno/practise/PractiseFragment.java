package com.nollpointer.dates_zno.practise;

import android.Manifest;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.nollpointer.dates_zno.R;
import com.nollpointer.dates_zno.activity.MainActivity;
import com.nollpointer.dates_zno.other.PractiseHelpDialog;

import static com.nollpointer.dates_zno.practise.PractiseConstants.VOICE;


public class PractiseFragment extends Fragment implements PractiseCellView.OnClickListener {

    private static final String TAG = "PractiseFragment";

    private TabLayout tabLayout;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View mainView = inflater.inflate(R.layout.fragment_practise, container, false);

        Toolbar toolbar = mainView.findViewById(R.id.practise_toolbar);
        toolbar.inflateMenu(R.menu.practise_menu);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                if (menuItem.getItemId() == R.id.practise_help) {
                    PractiseHelpDialog helpDialog = PractiseHelpDialog.newInstance(R.string.help_practise);
                    helpDialog.show(getActivity().getSupportFragmentManager(), null);
                }


                return true;
            }
        });
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().popBackStack();
            }
        });


        ViewPager viewPager = mainView.findViewById(R.id.practise_view_pager);
        viewPager.setAdapter(new PractiseCellAdapter());


        tabLayout = mainView.findViewById(R.id.practise_tabs);
        tabLayout.setupWithViewPager(viewPager, true);


        return mainView;
    }

    private int[] getLastMarks() {

        String[] markTitles = getResources().getStringArray(R.array.practise_marks_titles);
        int[] marks = new int[markTitles.length];
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getContext());

        for (int i = 0; i < marks.length; i++)
            marks[i] = preferences.getInt(markTitles[i], -1);

        return marks;
    }

    @Override
    public void onStart() {
        super.onStart();
        ((MainActivity) getActivity()).showBottomNavigationView();
    }

    @Override
    public void onClicked(String practise, int practiseMode) {

        MainActivity mainActivity = ((MainActivity) getActivity());
        int permissionRecord = ContextCompat.checkSelfPermission(mainActivity, Manifest.permission.RECORD_AUDIO);

        if (practise.equals(VOICE) && permissionRecord != PackageManager.PERMISSION_GRANTED)
            ActivityCompat.requestPermissions(mainActivity, new String[]{Manifest.permission.RECORD_AUDIO}, 1);
        else
            mainActivity.getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, PractiseDetailsPickerFragment.newInstance(practise, practiseMode == 1, mainActivity.getMode())).addToBackStack(null).commit();


    }


    public class PractiseCellAdapter extends PagerAdapter {

        @Override
        public Object instantiateItem(ViewGroup collection, int position) {
            PractiseCellView recyclerView = new PractiseCellView(getContext());
            recyclerView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            recyclerView.setListener(PractiseFragment.this);
            //if(position == 1)
            recyclerView.setPractiseMode(position);
            if(position == 1)
                recyclerView.setMarks(getLastMarks());

            collection.addView(recyclerView);

            return recyclerView;
        }

        @Override
        public void destroyItem(ViewGroup collection, int position, Object view) {
            collection.removeView((View) view);
        }

        @Override
        public int getCount() {
            return 2;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            String title;
            if (position == 0)
                title = "Тренировка";
            else
                title = "Тестирование";
            return title;
        }
    }

//    @Override
//    public void onClick(int position) {
//        String practise;
//        switch (position) {
//            case 0:
//                practise = CARDS;
//                break;
//            case 1:
//                practise = TEST;
//                break;
//            case 2:
//                practise = TRUE_FALSE;
//                break;
//            case 3:
//                practise = SORT;
//                break;
//            case 4:
//                practise = DISTRIBUTE;
//                break;
//            default:
//                return;
//        }
//        MainActivity mainActivity = ((MainActivity) getActivity());
//        mainActivity.getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, PractiseDetailsPickerFragment.newInstance(practise, mainActivity.getMode())).addToBackStack(null).commit();
//
//    }

//    public void onClick(int position) {
////        pressedPosition = position;
////        if (position == 5 || position == 6 || position == 8 || position == 9) {
////            typePicked(1);
////        } else {
////            TypePickDialog dialog = new TypePickDialog();
////            dialog.setListener(this);
////            dialog.show(mMainActivity.getSupportFragmentManager(), null);
////        }
//        String practise;
//        switch (position) {
//            case 0:
//                practise = CARDS;
//                break;
//            case 1:
//                practise = TEST;
//                break;
//            case 2:
//                practise = TRUE_FALSE;
//                break;
//            case 3:
//                practise = SORT;
//                break;
//            case 4:
//                practise = DISTRIBUTE;
//                break;
//            default:
//                return;
//        }
//        mMainActivity.getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, PractiseDetailsPickerFragment.newInstance(practise,mMainActivity.getMode())).addToBackStack(null).commit();
//
//    }


//    public void startPractise(ArrayList<Integer> arrayList) {
//        MainActivity mAc = mMainActivity;
//        mAc.hideBottomNavigationView();
//        Fragment fragment;
//        String event;
//        ArrayList<Date> dates = getListForPractise(arrayList);
//        switch (pressedPosition) {
//            case 0:
//                fragment = CardsFragment.newInstance(dates, type);
//                event = "CardsFragment";
//                break;
//            case 2:
//                fragment = TestFragment.newInstance(dates, type, false);
//                event = "Test";
//                break;
//            case 3:
//                showAds();
//                fragment = TestFragment.newInstance(dates, type, true);
//                event = "Test_20";
//                break;
//            case 5:
//                fragment = TrueFalseFragment.newInstance(dates, false);
//                event = "TrueFalse";
//                break;
//            case 6:
//                showAds();
//                fragment = TrueFalseFragment.newInstance(dates, true);
//                event = "TrueFalse_20";
//                break;
//            case 8:
//                fragment = SortFragment.newInstance(dates, false);
//                event = "Sort";
//                break;
//            case 9:
//                showAds();
//                fragment = SortFragment.newInstance(dates, true);
//                event = "Sort_20";
//                break;
//            default:
//                return;
//        }
//        FlurryAgent.logEvent(event);
//        setFragmentToPractise(fragment);
//    }

//    private void setFragmentToPractise(Fragment fragment) {
//        mMainActivity.getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, fragment).setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).addToBackStack(null).commit();
//    }

//    public void showAds() {
//        if (Appodeal.isLoaded(Appodeal.INTERSTITIAL))
//            Appodeal.show(mMainActivity, Appodeal.INTERSTITIAL);
//    }


//    private ArrayList<Date> getListForPractise(ArrayList<Integer> arrayList) {
//        ArrayList<Date> dates = mMainActivity.getDates();
//        ArrayList<Date> practiseList = new ArrayList<>();
//        int mode = mMainActivity.getMode();
//        if ((mode == FULL_DATES_MODE && arrayList.contains(10)) || (mode == EASY_DATES_MODE && arrayList.contains(2))) // Если выбраны все даты
//            return dates;
//        Pair<Integer, Integer> pair;
//        Collections.sort(arrayList);
//        for (Integer number : arrayList) {
//            pair = getDatesRange(number, mode);
//            practiseList.addAll(dates.subList(pair.first, pair.second));
//        }
//        return practiseList;
//    }

//    private Pair<Integer, Integer> getDatesRange(int pickedCentury, int mode) {
//        int start = 0, end = 0;
//        if (mode == FULL_DATES_MODE) {
//            switch (pickedCentury) {
//                case 0:
//                    start = 0;
//                    end = start + 21;
//                    break;
//                case 1:
//                    start = 21;
//                    end = start + 20;
//                    break;
//                case 2:
//                    start = 41;
//                    end = start + 35;
//                    break;
//                case 3:
//                    start = 76;
//                    end = start + 31;
//                    break;
//                case 4:
//                    start = 107;
//                    end = start + 40;
//                    break;
//                case 5:
//                    start = 147;
//                    end = start + 48;
//                    break;
//                case 6:
//                    start = 195;
//                    end = start + 48;
//                    break;
//                case 7:
//                    start = 242;
//                    end = start + 42;
//                    break;
//                case 8:
//                    start = 284;
//                    end = start + 50;
//                    break;
//                case 9:
//                    start = 334;
//                    end = start + 50;
//                    break;
//            }
//        } else {
//            switch (pickedCentury) {
//                case 0:
//                    start = 0;
//                    end = start + 48;
//                    break;
//                case 1:
//                    start = 48;
//                    end = start + 47;
//                    break;
//            }
//        }
//        return new Pair<>(start, end);
//    }


}