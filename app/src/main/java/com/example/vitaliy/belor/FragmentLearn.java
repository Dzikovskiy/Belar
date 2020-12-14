package com.example.vitaliy.belor;

import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.firebase.database.FirebaseDatabase;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import entity.Book;

// flag = 0 if unlearned, 1 if learned, 2 if processed of learn, 3 if tested

public class FragmentLearn extends Fragment {
    FirebaseDatabase database = FirebaseDatabase.getInstance();

    ArrayList<Book> books = new ArrayList<>(5);
    TextView textViewBel;
    TextView textViewEng;
    TextView textViewLat;
    TextView textViewCounter;
    Cursor cursor;
    int i = 0;
    int counter = 0;
    int id;
    int buttonPrev;
    private DatabaseHelper databaseHelper;
    private SQLiteDatabase sqLiteDatabase;
    private AdView mAdView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View v = inflater.inflate(R.layout.fragment_fragment_learn, container, false);
        mAdView = v.findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        final Button button_what = v.findViewById(R.id.buttonShow);
        final Button button_next = v.findViewById(R.id.buttonNext);
        final Button button_send = v.findViewById(R.id.buttonSend);
        button_next.setVisibility(View.INVISIBLE);
        button_what.setVisibility(View.INVISIBLE);
        button_send.setVisibility(View.INVISIBLE);
        buttonPrev = 0;// reset prev button index if nav bottom was changed

        counter = 0;
        RelativeLayout relativeLayout = v.findViewById(R.id.fragmentLearn);

        AnimationDrawable animationDrawable = (AnimationDrawable) relativeLayout.getBackground();
        animationDrawable.setEnterFadeDuration(750);
        animationDrawable.setExitFadeDuration(5000);
        animationDrawable.start();

        databaseHelper = new DatabaseHelper(getActivity());
        try {
            databaseHelper.updateDataBase();
        } catch (IOException mIOException) {
            throw new Error("UnableToUpdateDatabase");
        }
        try {
            sqLiteDatabase = databaseHelper.getWritableDatabase();
        } catch (SQLException mSQLException) {
            throw mSQLException;
        }
        textViewCounter = v.findViewById(R.id.textViewCounter);
        textViewBel = v.findViewById(R.id.textViewBel);
        textViewEng = v.findViewById(R.id.textViewEng);
        textViewLat = v.findViewById(R.id.textViewBelLat);
        textViewCounter.setVisibility(View.INVISIBLE);
        textViewBel.setText("Move to the next page");
        textViewEng.setVisibility(View.INVISIBLE);
        textViewLat.setVisibility(View.INVISIBLE);

        cursor = sqLiteDatabase.rawQuery("SELECT * FROM words WHERE flag =0 ", null);
        cursor.moveToFirst();
        int total = cursor.getCount();

        i = 0;
        books.clear();// clear from previus onCreate step
        if (cursor != null && cursor.getCount() > 0) {
            button_next.setVisibility(View.VISIBLE);
            button_what.setVisibility(View.VISIBLE);
            button_send.setVisibility(View.VISIBLE);
            if (cursor.moveToFirst()) {
                do {// adding to array only processed of learn

                    Book book;
                    book = new Book(cursor.getString(0), cursor.getString(1), cursor.getString(2),
                            cursor.getString(3), cursor.getString(4));
                    books.add(book);
                } while (cursor.moveToNext());
            }

            textViewCounter.setVisibility(View.VISIBLE);
            textViewEng.setVisibility(View.VISIBLE);
            textViewLat.setVisibility(View.VISIBLE);

            textViewCounter.setText("" + i + "/" + total);
            textViewBel.setText(books.get(i).getBel());
            textViewLat.setText("[" + books.get(i).getBel_lat() + "]");
            textViewEng.setText("");
            id = Integer.parseInt(books.get(i).get_id());

            button_next.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {


                    if (i < books.size() && i == (books.size() - 1)) {//cheking of the last element
                        sqLiteDatabase.execSQL("UPDATE words SET flag = 2 WHERE bel ='" + textViewBel.getText().toString() + "'");
                        textViewCounter.setText("" + i + "/" + cursor.getCount());
                        textViewBel.setText(books.get(i).getBel());
                        textViewLat.setText("[" + books.get(i).getBel_lat() + "]");
                        textViewEng.setText("");
                        i++;
                        counter++;
                    }
                    if (i < (books.size()) - 1) {//cheking if the begining and pre-ending element

                        if (buttonPrev == 2) {
                            Collections.swap(books, (books.size() - 1), i);

                            textViewBel.setText(books.get(i).getBel());
                            textViewLat.setText("[" + books.get(i).getBel_lat() + "]");
                            textViewEng.setText("");
                        } else {
                            sqLiteDatabase.execSQL("UPDATE words SET flag = 2 WHERE bel ='" + textViewBel.getText().toString() + "'");
                            i++;
                            counter++;
                            textViewCounter.setText("" + counter + "/" + cursor.getCount());
                            textViewBel.setText(books.get(i).getBel());
                            textViewLat.setText("[" + books.get(i).getBel_lat() + "]");
                            textViewEng.setText("");
                        }
                    }
                    if (books.size() < i || books.size() == i) {// cheking if more than array size and hide all
                        textViewBel.setText("Move to the next page");
                        textViewEng.setVisibility(View.INVISIBLE);
                        textViewLat.setVisibility(View.INVISIBLE);
                        textViewCounter.setVisibility(View.INVISIBLE);
                        button_next.setClickable(false);
                        button_what.setClickable(false);
                        button_send.setClickable(false);
                        button_next.setVisibility(View.INVISIBLE);
                        button_what.setVisibility(View.INVISIBLE);
                        button_send.setVisibility(View.INVISIBLE);

                    }

                    buttonPrev = 1;
                }


            });

            button_send.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    if (i < (books.size())) {
                        Intent intent = new Intent(Intent.ACTION_SEND);
                        intent.setType("text/plain");
                        // String body = book_array.get(i).bel+" "+book_array.get(i).eng;
                        String sub = books.get(i).getBel() + " " + books.get(i).getEng();
                        //intent.putExtra(Intent.EXTRA_SUBJECT,body);
                        intent.putExtra(Intent.EXTRA_TEXT, sub);
                        startActivity(Intent.createChooser(intent, "share"));
                    }
                }
            });

            button_what.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    if (i < (books.size())) {
                        textViewEng.setText(books.get(i).getEng());
                    }

                    buttonPrev = 2;

                }
            });
        }

        return v;
    }

}
