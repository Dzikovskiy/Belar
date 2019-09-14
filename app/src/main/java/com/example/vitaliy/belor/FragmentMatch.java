package com.example.vitaliy.belor;

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

import POJO.Book;

public class FragmentMatch extends Fragment implements View.OnClickListener {


    buttonIndex buttonIndex = new buttonIndex(0, 0, 0, 0, 0, 0, 0, 0);
    Random rand = new Random();
    private DatabaseHelper mDBHelper;
    private SQLiteDatabase mDb;
    Cursor cursor;
    Button Button_1;
    Button Button_2;
    Button Button_3;
    Button Button_4;
    Button Button_5;
    Button Button_6;
    Button Button_7;
    Button Button_8;
    TextView textView;
    int s;
    int index_four;
    int i;
    int button_prev = 9;

    ArrayList<Book> book_array = new ArrayList<>(5);

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_fragment_match, container, false);

        RelativeLayout relativeLayout = v.findViewById(R.id.fragmentMatch);

        AnimationDrawable animationDrawable = (AnimationDrawable) relativeLayout.getBackground();
        animationDrawable.setEnterFadeDuration(750);
        animationDrawable.setExitFadeDuration(5000);
        animationDrawable.start();
        textView = v.findViewById(R.id.textView);
        Button_1 = v.findViewById(R.id.button1);
        Button_2 = v.findViewById(R.id.button2);
        Button_3 = v.findViewById(R.id.button3);
        Button_4 = v.findViewById(R.id.button4);
        Button_5 = v.findViewById(R.id.button5);
        Button_6 = v.findViewById(R.id.button6);
        Button_7 = v.findViewById(R.id.button7);
        Button_8 = v.findViewById(R.id.button8);
        Button_1.setVisibility(View.INVISIBLE);
        Button_2.setVisibility(View.INVISIBLE);
        Button_3.setVisibility(View.INVISIBLE);
        Button_4.setVisibility(View.INVISIBLE);
        Button_5.setVisibility(View.INVISIBLE);
        Button_6.setVisibility(View.INVISIBLE);
        Button_7.setVisibility(View.INVISIBLE);
        Button_8.setVisibility(View.INVISIBLE);
        textView.setVisibility(View.VISIBLE);

        book_array.clear();
        //  v.findViewById(R.id.button8).setVisibility(View.INVISIBLE);

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

        cursor = mDb.rawQuery("SELECT * FROM words WHERE flag =3 ", null);
        cursor.moveToFirst();

        if (cursor != null && cursor.getCount() >= 4) {

            i = 4;

            index_four = 0;

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
            Button_5.setVisibility(View.VISIBLE);
            Button_6.setVisibility(View.VISIBLE);
            Button_7.setVisibility(View.VISIBLE);
            Button_8.setVisibility(View.VISIBLE);
            textView.setVisibility(View.INVISIBLE);

            s = book_array.size();

            makeRandButtons.makeRand(Button_1, Button_2, Button_3, Button_4, Button_5,
                    Button_6, Button_7, Button_8, rand, book_array, buttonIndex, index_four);

            Button_1.setOnClickListener(this);
            Button_2.setOnClickListener(this);
            Button_3.setOnClickListener(this);
            Button_4.setOnClickListener(this);
            Button_5.setOnClickListener(this);
            Button_6.setOnClickListener(this);
            Button_7.setOnClickListener(this);
            Button_8.setOnClickListener(this);


        } else {
            textView.setVisibility(View.VISIBLE);
        }
        return v;

    }

    @Override
    public void onClick(View v) {

        String text = "";
        switch (button_prev) {
            case 1:
                text = Button_1.getText().toString();
                break;
            case 2:
                text = Button_2.getText().toString();
                break;
            case 3:
                text = Button_3.getText().toString();
                break;
            case 4:
                text = Button_4.getText().toString();
                break;
            case 5:
                text = Button_5.getText().toString();
                break;
            case 6:
                text = Button_6.getText().toString();
                break;
            case 7:
                text = Button_7.getText().toString();
                break;
            case 8:
                text = Button_8.getText().toString();
                break;
            default:
                text = "0";
                break;
        }

        int prevButtonArrayIndex = 999;
        int currentButtonArrayIndex = 999;

        for (int i = 0; i < book_array.size(); i++) {
            if ((text.compareTo(book_array.get(i).eng) == 0) || (text.compareTo(book_array.get(i).bel) == 0)) {

                prevButtonArrayIndex = i;
            }
        }

        String currentButtonText;
        currentButtonText = (String) ((Button) v).getText();

        for (int i = 0; i < book_array.size(); i++) {
            if ((currentButtonText.compareTo(book_array.get(i).eng) == 0) || (currentButtonText.compareTo(book_array.get(i).bel) == 0)) {

                currentButtonArrayIndex = i;
            }
        }

        if (text.compareTo((String) ((Button) v).getText()) != 0) {
            // может проверять обьекты

            if (currentButtonArrayIndex == prevButtonArrayIndex) { // book_array obj contains word and translating
                i--;
                v.findViewById(v.getId()).setVisibility(View.INVISIBLE);

                switch (button_prev) {
                    case 1:
                        Button_1.setVisibility(View.INVISIBLE);
                        break;
                    case 2:
                        Button_2.setVisibility(View.INVISIBLE);
                        break;
                    case 3:
                        Button_3.setVisibility(View.INVISIBLE);
                        break;
                    case 4:
                        Button_4.setVisibility(View.INVISIBLE);
                        break;
                    case 5:
                        Button_5.setVisibility(View.INVISIBLE);
                        break;
                    case 6:
                        Button_6.setVisibility(View.INVISIBLE);
                        break;
                    case 7:
                        Button_7.setVisibility(View.INVISIBLE);
                        break;
                    case 8:
                        Button_8.setVisibility(View.INVISIBLE);
                        break;
                    default:
                        break;
                }

                mDb.execSQL("UPDATE words SET flag = 1 WHERE _id =" + book_array.get(currentButtonArrayIndex)._id);

            }
        }


        switch (v.getId()) {// Storage the previous clicked button in button_prev

            case R.id.button1:
                button_prev = 1;
                break;
            case R.id.button2:
                button_prev = 2;
                break;
            case R.id.button3:
                button_prev = 3;
                break;
            case R.id.button4:
                button_prev = 4;
                break;
            case R.id.button5:
                button_prev = 5;
                break;
            case R.id.button6:
                button_prev = 6;
                break;
            case R.id.button7:
                button_prev = 7;
                break;
            case R.id.button8:
                button_prev = 8;
                break;
            default:
                break;
        }


        if ((book_array.size() - (index_four + 4)) >= 4) {
            if (i == 0) {
                button_prev = 9;
                i = 4;
                index_four = index_four + 4;
                makeRandButtons.makeRand(Button_1, Button_2, Button_3, Button_4, Button_5,
                        Button_6, Button_7, Button_8, rand, book_array, buttonIndex, index_four);
            }
        } else {
            if (i == 0) {
                textView.setVisibility(View.VISIBLE);
            }
        }

    }
}

class makeRandButtons {

    public static void makeRand(Button Button_1, Button Button_2, Button Button_3, Button Button_4,
                                Button Button_5, Button Button_6, Button Button_7, Button Button_8,
                                Random rand, ArrayList<Book> book_array, buttonIndex buttonIndex, int four_index) {
        switch (rand.nextInt(4)) {
            case 0:
                Button_1.setText(book_array.get(four_index + 0).eng);
                buttonIndex.button_ind_1 = 0;
                Button_2.setText(book_array.get(four_index + 0).bel);
                buttonIndex.button_ind_2 = 0;
                Button_3.setText(book_array.get(four_index + 3).eng);
                buttonIndex.button_ind_3 = 3;
                Button_4.setText(book_array.get(four_index + 1).bel);
                buttonIndex.button_ind_4 = 1;
                Button_5.setText(book_array.get(four_index + 2).eng);
                buttonIndex.button_ind_5 = 2;
                Button_6.setText(book_array.get(four_index + 2).bel);
                buttonIndex.button_ind_6 = 2;
                Button_7.setText(book_array.get(four_index + 1).eng);
                buttonIndex.button_ind_7 = 1;
                Button_8.setText(book_array.get(four_index + 3).bel);
                buttonIndex.button_ind_8 = 3;

                break;

            case 1:
                Button_1.setText(book_array.get(four_index + 3).eng);
                buttonIndex.button_ind_1 = 3;
                Button_2.setText(book_array.get(four_index + 2).bel);
                buttonIndex.button_ind_2 = 2;
                Button_3.setText(book_array.get(four_index + 0).eng);
                buttonIndex.button_ind_3 = 0;
                Button_4.setText(book_array.get(four_index + 0).bel);
                buttonIndex.button_ind_4 = 0;
                Button_5.setText(book_array.get(four_index + 1).eng);
                buttonIndex.button_ind_5 = 1;
                Button_6.setText(book_array.get(four_index + 1).bel);
                buttonIndex.button_ind_6 = 1;
                Button_7.setText(book_array.get(four_index + 2).eng);
                buttonIndex.button_ind_7 = 2;
                Button_8.setText(book_array.get(four_index + 3).bel);
                buttonIndex.button_ind_8 = 3;
                break;

            case 3:
                Button_1.setText(book_array.get(four_index + 1).eng);
                buttonIndex.button_ind_1 = 1;
                Button_2.setText(book_array.get(four_index + 1).bel);
                buttonIndex.button_ind_2 = 1;
                Button_3.setText(book_array.get(four_index + 2).eng);
                buttonIndex.button_ind_3 = 2;
                Button_4.setText(book_array.get(four_index + 0).bel);
                buttonIndex.button_ind_4 = 0;
                Button_5.setText(book_array.get(four_index + 3).eng);
                buttonIndex.button_ind_5 = 3;
                Button_6.setText(book_array.get(four_index + 3).bel);
                buttonIndex.button_ind_6 = 3;
                Button_7.setText(book_array.get(four_index + 0).eng);
                buttonIndex.button_ind_7 = 0;
                Button_8.setText(book_array.get(four_index + 2).bel);
                buttonIndex.button_ind_8 = 2;
                break;

            default:

                Button_1.setText(book_array.get(four_index + 0).eng);
                buttonIndex.button_ind_1 = 0;
                Button_2.setText(book_array.get(four_index + 3).bel);
                buttonIndex.button_ind_2 = 3;
                Button_3.setText(book_array.get(four_index + 1).eng);
                buttonIndex.button_ind_3 = 1;
                Button_4.setText(book_array.get(four_index + 1).bel);
                buttonIndex.button_ind_4 = 1;
                Button_5.setText(book_array.get(four_index + 2).eng);
                buttonIndex.button_ind_5 = 2;
                Button_6.setText(book_array.get(four_index + 2).bel);
                buttonIndex.button_ind_6 = 2;
                Button_7.setText(book_array.get(four_index + 3).eng);
                buttonIndex.button_ind_7 = 3;
                Button_8.setText(book_array.get(four_index + 0).bel);
                buttonIndex.button_ind_8 = 0;
                break;


        }
        Button_1.setVisibility(View.VISIBLE);
        Button_2.setVisibility(View.VISIBLE);
        Button_3.setVisibility(View.VISIBLE);
        Button_4.setVisibility(View.VISIBLE);
        Button_5.setVisibility(View.VISIBLE);
        Button_6.setVisibility(View.VISIBLE);
        Button_7.setVisibility(View.VISIBLE);
        Button_8.setVisibility(View.VISIBLE);
    }

}

class buttonIndex {
    public int button_ind_1;
    public int button_ind_2;
    public int button_ind_3;
    public int button_ind_4;
    public int button_ind_5;
    public int button_ind_6;
    public int button_ind_7;
    public int button_ind_8;

    public buttonIndex(int button_ind_1, int button_ind_2, int button_ind_3, int button_ind_4,
                       int button_ind_5, int button_ind_6, int button_ind_7, int button_ind_8) {
        this.button_ind_1 = button_ind_1;
        this.button_ind_2 = button_ind_2;
        this.button_ind_3 = button_ind_3;
        this.button_ind_4 = button_ind_4;
        this.button_ind_5 = button_ind_5;
        this.button_ind_6 = button_ind_6;
        this.button_ind_7 = button_ind_7;
        this.button_ind_8 = button_ind_8;
    }
}
