package com.example.vitaliy.belor;


import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import java.io.IOException;


public class FragmentScore extends Fragment {

    private DatabaseHelper mDBHelper;
    private SQLiteDatabase mDb;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v = inflater.inflate(R.layout.fragment_fragment_score, container, false);

        TextView textViewStat = v.findViewById(R.id.textView);
        mDBHelper = new DatabaseHelper(getActivity());

        try {
            mDBHelper.updateDataBase();
        } catch (IOException mIOException) {
            throw new Error("UnableToUpdateDatabase");
        }

        try {
            mDb = mDBHelper.getWritableDatabase();
        } catch (SQLException mSQLException) {
            throw mSQLException;
        }


        Cursor cursor_progress = mDb.rawQuery("SELECT COUNT (*) FROM words WHERE flag =2 OR flag =3 ", null);
        cursor_progress.moveToFirst();
        Cursor cursor = mDb.rawQuery("SELECT COUNT(*) FROM words WHERE flag =1 ", null);
        cursor.moveToFirst();
        Cursor cursor_test = mDb.rawQuery("SELECT COUNT(*) FROM words WHERE flag =3", null);
        cursor_test.moveToFirst();
        Cursor cursor_learn = mDb.rawQuery("SELECT COUNT(*) FROM words WHERE flag =2", null);
        cursor_learn.moveToFirst();

        textViewStat.setText("You have learned " + cursor.getString(0) + " words\n" + "-------------" + "\nIn progress "
                + cursor_progress.getString(0) + " words " + "\nKeep going on!" + " Tested: "
                + cursor_test.getString(0) + " Learn: " + cursor_learn.getString(0));

        return v;

    }
}
