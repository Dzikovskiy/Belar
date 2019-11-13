package com.example.vitaliy.belor;


import POJO.Book;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class FragmentTest extends Fragment implements View.OnClickListener {

    Random rand = new Random();
    Cursor cursor;
    Cursor cursor_total;
    Cursor cursor_rand;
    ArrayList<Book> book_array = new ArrayList<>(5);
    ArrayList<Book> book_array_rand = new ArrayList<>(4);
    String total;
    int i = 0;
    int s;
    int button_ind_1;
    int button_ind_2;
    int button_ind_3;
    int button_ind_4;
    TextView textViewCounter;
    TextView textViewBel;
    TextView textViewBelLat;
    Button Button_1;
    Button Button_2;
    Button Button_3;
    Button Button_4;
    private DatabaseHelper mDBHelper;
    private SQLiteDatabase mDb;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_fragment_test, container, false);
        textViewCounter = v.findViewById(R.id.textViewCounter);
        textViewBel = v.findViewById(R.id.textViewBel);
        textViewBelLat = v.findViewById(R.id.textViewBelLat);
        Button_1 = v.findViewById(R.id.button1);
        Button_2 = v.findViewById(R.id.button2);
        Button_3 = v.findViewById(R.id.button3);
        Button_4 = v.findViewById(R.id.button4);

        book_array.clear();
        RelativeLayout relativeLayout = v.findViewById(R.id.fragmentTest);

        AnimationDrawable animationDrawable = (AnimationDrawable) relativeLayout.getBackground();
        animationDrawable.setEnterFadeDuration(750);
        animationDrawable.setExitFadeDuration(5000);
        animationDrawable.start();

        i = 0;
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
        cursor = mDb.rawQuery("SELECT * FROM words WHERE flag =2 ", null);
        cursor.moveToFirst();
        cursor_total = mDb.rawQuery("SELECT COUNT(*) FROM words WHERE flag =2 ", null);
        cursor_total.moveToFirst();
        cursor_rand = mDb.rawQuery("SELECT * FROM words", null);
        cursor.moveToFirst();
        if (cursor_rand != null && cursor_rand.getCount() > 3) {

            if (cursor_rand.moveToFirst()) {
                do {// adding to array only processed of learn

                    Book book_rand;
                    book_rand = new Book(cursor_rand.getString(0), cursor_rand.getString(1), cursor_rand.getString(2),
                            cursor_rand.getString(3), cursor_rand.getString(4));
                    book_array_rand.add(book_rand);
                } while (cursor_rand.moveToNext());
            }
        }

        total = "0";
        total = cursor_total.getString(0);

        if (cursor != null && cursor.getCount() > 0) {

            if (cursor.moveToFirst()) {
                do {// adding to array only processed of learn

                    Book book;
                    book = new Book(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4));
                    book_array.add(book);
                } while (cursor.moveToNext());
            }

            Button_1.setVisibility(View.VISIBLE);
            Button_2.setVisibility(View.VISIBLE);
            Button_3.setVisibility(View.VISIBLE);
            Button_4.setVisibility(View.VISIBLE);
            textViewCounter.setVisibility(View.VISIBLE);

            s = book_array_rand.size();

            switch (rand.nextInt(3)) {
                case 0:
                    Button_1.setText(book_array.get(0).eng);
                    button_ind_2 = IndexChecker.indexChecking(i, book_array_rand, book_array);
                    Button_2.setText(book_array_rand.get(button_ind_2).eng);
                    button_ind_3 = IndexChecker.indexChecking(i, book_array_rand, book_array);
                    Button_3.setText(book_array_rand.get(button_ind_3).eng);
                    button_ind_4 = IndexChecker.indexChecking(i, book_array_rand, book_array);
                    Button_4.setText(book_array_rand.get(button_ind_4).eng);
                    break;
                case 1:
                    button_ind_1 = IndexChecker.indexChecking(i, book_array_rand, book_array);
                    Button_1.setText(book_array_rand.get(button_ind_1).eng);
                    Button_2.setText(book_array.get(0).eng);
                    button_ind_3 = IndexChecker.indexChecking(i, book_array_rand, book_array);
                    Button_3.setText(book_array_rand.get(button_ind_3).eng);
                    button_ind_4 = IndexChecker.indexChecking(i, book_array_rand, book_array);
                    Button_4.setText(book_array_rand.get(button_ind_4).eng);
                    break;

                case 2:
                    button_ind_1 = IndexChecker.indexChecking(i, book_array_rand, book_array);
                    Button_1.setText(book_array_rand.get(button_ind_1).eng);
                    button_ind_2 = IndexChecker.indexChecking(i, book_array_rand, book_array);
                    Button_2.setText(book_array_rand.get(button_ind_2).eng);
                    Button_3.setText(book_array.get(0).eng);
                    button_ind_4 = IndexChecker.indexChecking(i, book_array_rand, book_array);
                    Button_4.setText(book_array_rand.get(button_ind_4).eng);
                    break;

                case 3:
                    button_ind_1 = IndexChecker.indexChecking(i, book_array_rand, book_array);
                    Button_1.setText(book_array_rand.get(button_ind_1).eng);
                    button_ind_2 = IndexChecker.indexChecking(i, book_array_rand, book_array);
                    Button_2.setText(book_array_rand.get(button_ind_2).eng);
                    button_ind_3 = IndexChecker.indexChecking(i, book_array_rand, book_array);
                    Button_3.setText(book_array_rand.get(button_ind_3).eng);
                    Button_4.setText(book_array.get(0).eng);
                    break;

                default:
                    button_ind_1 = IndexChecker.indexChecking(i, book_array_rand, book_array);
                    Button_1.setText(book_array_rand.get(button_ind_1).eng);
                    button_ind_2 = IndexChecker.indexChecking(i, book_array_rand, book_array);
                    Button_2.setText(book_array_rand.get(button_ind_2).eng);
                    button_ind_3 = IndexChecker.indexChecking(i, book_array_rand, book_array);
                    Button_3.setText(book_array_rand.get(button_ind_3).eng);
                    Button_4.setText(book_array_rand.get(0).eng);
                    break;// выдаёт дубликаты
            }


            textViewCounter.setText("" + 1 + "/" + total);
            textViewBel.setText(book_array.get(0).bel);
            textViewBelLat.setText(book_array.get(0).bel_lat);

            Button_1.setOnClickListener(this);
            Button_2.setOnClickListener(this);
            Button_3.setOnClickListener(this);
            Button_4.setOnClickListener(this);
        }

        return v;
    }

    @Override
    public void onClick(View v) {


        if (i < (book_array.size() - 1) || i != book_array.size() - 1) {

            if (((Button) v).getText().toString() == book_array.get(i).eng) {

                mDb.execSQL("UPDATE words SET flag = 3 WHERE _id =" + book_array.get(i)._id);
                i++;

                switch (rand.nextInt(3)) {
                    case 0:
                        Button_1.setText(book_array.get(i).eng);
                        button_ind_2 = rand.nextInt(s);
                        Button_2.setText(book_array_rand.get(button_ind_2).eng);
                        button_ind_3 = rand.nextInt(s);
                        Button_3.setText(book_array_rand.get(button_ind_3).eng);
                        button_ind_4 = rand.nextInt(s);
                        Button_4.setText(book_array_rand.get(button_ind_4).eng);
                        break;
                    case 1:
                        button_ind_1 = rand.nextInt(s);
                        Button_1.setText(book_array_rand.get(button_ind_1).eng);
                        Button_2.setText(book_array.get(i).eng);
                        button_ind_3 = rand.nextInt(s);
                        Button_3.setText(book_array_rand.get(button_ind_3).eng);
                        button_ind_4 = rand.nextInt(s);
                        Button_4.setText(book_array_rand.get(button_ind_4).eng);
                        break;

                    case 2:
                        button_ind_1 = rand.nextInt(s);
                        Button_1.setText(book_array_rand.get(button_ind_1).eng);
                        button_ind_2 = rand.nextInt(s);
                        Button_2.setText(book_array_rand.get(button_ind_2).eng);
                        Button_3.setText(book_array.get(i).eng);
                        button_ind_4 = rand.nextInt(s);
                        Button_4.setText(book_array_rand.get(button_ind_4).eng);
                        break;

                    case 3:
                        button_ind_1 = rand.nextInt(s);
                        Button_1.setText(book_array_rand.get(button_ind_1).eng);
                        button_ind_2 = rand.nextInt(s);
                        Button_2.setText(book_array_rand.get(button_ind_2).eng);
                        button_ind_3 = rand.nextInt(s);
                        Button_3.setText(book_array_rand.get(button_ind_3).eng);
                        Button_4.setText(book_array.get(i).eng);
                        break;

                    default:
                        button_ind_1 = rand.nextInt(s);
                        Button_1.setText(book_array_rand.get(button_ind_1).eng);
                        button_ind_2 = rand.nextInt(s);
                        Button_2.setText(book_array_rand.get(button_ind_2).eng);
                        button_ind_3 = rand.nextInt(s);
                        Button_3.setText(book_array_rand.get(button_ind_3).eng);

                        Button_4.setText(book_array_rand.get(i).eng);
                        break;// выдаёт дубликаты
                }

                textViewCounter.setText("" + i + "/" + total);
                textViewBel.setText(book_array.get(i).bel);
                textViewBelLat.setText(book_array.get(i).bel_lat);


            }
        }

        if (i == (book_array.size() - 1)) {
            if (((Button) v).getText().toString() == book_array.get(i).eng) {

                mDb.execSQL("UPDATE words SET flag = 3 WHERE _id =" + book_array.get(i)._id);

                Button_1.setVisibility(View.INVISIBLE);
                Button_2.setVisibility(View.INVISIBLE);
                Button_3.setVisibility(View.INVISIBLE);
                Button_4.setVisibility(View.INVISIBLE);
                textViewBel.setText("Learn some words to start here");
                textViewBelLat.setVisibility(View.INVISIBLE);
                textViewCounter.setVisibility(View.INVISIBLE);
            }
        }
    }

}

class IndexChecker {
    public static int indexChecking(int index, ArrayList<Book> book_rand, ArrayList<Book> book) {
        Random rand = new Random();
        int rand_size = book_rand.size();
        int rand_index = rand.nextInt(rand_size);

        while (book_rand.get(rand_index).eng.compareTo(book.get(index).eng) == 0) {
            rand_index = rand.nextInt(rand_size);
        }

        return rand_index;
    }

}

