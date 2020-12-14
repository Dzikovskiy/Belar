package com.example.vitaliy.belor;


import android.database.Cursor;
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

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import entity.Book;

public class FragmentTest extends Fragment implements View.OnClickListener {

    private final Random rand = new Random();
    private Cursor cursor;
    private Cursor cursor_total;
    private Cursor cursor_rand;
    private final ArrayList<Book> books = new ArrayList<>(5);
    private final ArrayList<Book> bookArrayRand = new ArrayList<>(4);
    private String total;
    private int i = 0;
    private int s;
    private int button_ind_1;
    private int button_ind_2;
    private int button_ind_3;
    private int button_ind_4;
    private TextView textViewCounter;
    private TextView textViewBel;
    private TextView textViewBelLat;
    private Button Button_1;
    private Button Button_2;
    private Button Button_3;
    private Button Button_4;
    private DatabaseHelper databaseHelper;
    private SQLiteDatabase sqLiteDatabase;

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

        getBooks().clear();
        RelativeLayout relativeLayout = v.findViewById(R.id.fragmentTest);

        AnimationDrawable animationDrawable = (AnimationDrawable) relativeLayout.getBackground();
        animationDrawable.setEnterFadeDuration(750);
        animationDrawable.setExitFadeDuration(5000);
        animationDrawable.start();

        i = 0;
        databaseHelper = new DatabaseHelper(getActivity());
        try {
            getDatabaseHelper().updateDataBase();
        } catch (IOException mIOException) {
            throw new Error("UnableToUpdateDatabase");
        }
        sqLiteDatabase = getDatabaseHelper().getWritableDatabase();
        cursor = getSqLiteDatabase().rawQuery("SELECT * FROM words WHERE flag =2 ", null);
        getCursor().moveToFirst();
        cursor_total = getSqLiteDatabase().rawQuery("SELECT COUNT(*) FROM words WHERE flag =2 ", null);
        getCursor_total().moveToFirst();
        cursor_rand = getSqLiteDatabase().rawQuery("SELECT * FROM words", null);
        getCursor().moveToFirst();
        if (getCursor_rand() != null && getCursor_rand().getCount() > 3) {

            if (getCursor_rand().moveToFirst()) {
                do {// adding to array only processed of learn

                    Book book_rand;
                    book_rand = new Book(getCursor_rand().getString(0), getCursor_rand().getString(1), getCursor_rand().getString(2),
                            getCursor_rand().getString(3), getCursor_rand().getString(4));
                    getBookArrayRand().add(book_rand);
                } while (getCursor_rand().moveToNext());
            }
        }

        total = "0";
        total = getCursor_total().getString(0);

        if (getCursor() != null && getCursor().getCount() > 0) {

            if (getCursor().moveToFirst()) {
                do {// adding to array only processed of learn

                    Book book;
                    book = new Book(getCursor().getString(0), getCursor().getString(1), getCursor().getString(2), getCursor().getString(3), getCursor().getString(4));
                    getBooks().add(book);
                } while (getCursor().moveToNext());
            }

            getButton_1().setVisibility(View.VISIBLE);
            getButton_2().setVisibility(View.VISIBLE);
            getButton_3().setVisibility(View.VISIBLE);
            getButton_4().setVisibility(View.VISIBLE);
            getTextViewCounter().setVisibility(View.VISIBLE);

            s = getBookArrayRand().size();

            switch (getRand().nextInt(3)) {
                case 0:
                    getButton_1().setText(getBooks().get(0).getEng());
                    button_ind_2 = IndexChecker.indexChecking(getI(), getBookArrayRand(), getBooks());
                    getButton_2().setText(getBookArrayRand().get(getButton_ind_2()).getEng());
                    button_ind_3 = IndexChecker.indexChecking(getI(), getBookArrayRand(), getBooks());
                    getButton_3().setText(getBookArrayRand().get(getButton_ind_3()).getEng());
                    button_ind_4 = IndexChecker.indexChecking(getI(), getBookArrayRand(), getBooks());
                    getButton_4().setText(getBookArrayRand().get(getButton_ind_4()).getEng());
                    break;
                case 1:
                    button_ind_1 = IndexChecker.indexChecking(getI(), getBookArrayRand(), getBooks());
                    getButton_1().setText(getBookArrayRand().get(getButton_ind_1()).getEng());
                    getButton_2().setText(getBooks().get(0).getEng());
                    button_ind_3 = IndexChecker.indexChecking(getI(), getBookArrayRand(), getBooks());
                    getButton_3().setText(getBookArrayRand().get(getButton_ind_3()).getEng());
                    button_ind_4 = IndexChecker.indexChecking(getI(), getBookArrayRand(), getBooks());
                    getButton_4().setText(getBookArrayRand().get(getButton_ind_4()).getEng());
                    break;

                case 2:
                    button_ind_1 = IndexChecker.indexChecking(getI(), getBookArrayRand(), getBooks());
                    getButton_1().setText(getBookArrayRand().get(getButton_ind_1()).getEng());
                    button_ind_2 = IndexChecker.indexChecking(getI(), getBookArrayRand(), getBooks());
                    getButton_2().setText(getBookArrayRand().get(getButton_ind_2()).getEng());
                    getButton_3().setText(getBooks().get(0).getEng());
                    button_ind_4 = IndexChecker.indexChecking(getI(), getBookArrayRand(), getBooks());
                    getButton_4().setText(getBookArrayRand().get(getButton_ind_4()).getEng());
                    break;

                case 3:
                    button_ind_1 = IndexChecker.indexChecking(getI(), getBookArrayRand(), getBooks());
                    getButton_1().setText(getBookArrayRand().get(getButton_ind_1()).getEng());
                    button_ind_2 = IndexChecker.indexChecking(getI(), getBookArrayRand(), getBooks());
                    getButton_2().setText(getBookArrayRand().get(getButton_ind_2()).getEng());
                    button_ind_3 = IndexChecker.indexChecking(getI(), getBookArrayRand(), getBooks());
                    getButton_3().setText(getBookArrayRand().get(getButton_ind_3()).getEng());
                    getButton_4().setText(getBooks().get(0).getEng());
                    break;

                default:
                    button_ind_1 = IndexChecker.indexChecking(getI(), getBookArrayRand(), getBooks());
                    getButton_1().setText(getBookArrayRand().get(getButton_ind_1()).getEng());
                    button_ind_2 = IndexChecker.indexChecking(getI(), getBookArrayRand(), getBooks());
                    getButton_2().setText(getBookArrayRand().get(getButton_ind_2()).getEng());
                    button_ind_3 = IndexChecker.indexChecking(getI(), getBookArrayRand(), getBooks());
                    getButton_3().setText(getBookArrayRand().get(getButton_ind_3()).getEng());
                    getButton_4().setText(getBookArrayRand().get(0).getEng());
                    break;// выдаёт дубликаты
            }


            getTextViewCounter().setText("" + 1 + "/" + getTotal());
            getTextViewBel().setText(getBooks().get(0).getBel());
            getTextViewBelLat().setText(getBooks().get(0).getBel_lat());

            getButton_1().setOnClickListener(this);
            getButton_2().setOnClickListener(this);
            getButton_3().setOnClickListener(this);
            getButton_4().setOnClickListener(this);
        }

        return v;
    }

    @Override
    public void onClick(View v) {


        if (getI() < (getBooks().size() - 1) || getI() != getBooks().size() - 1) {

            if (((Button) v).getText().toString() == getBooks().get(getI()).getEng()) {

                getSqLiteDatabase().execSQL("UPDATE words SET flag = 3 WHERE _id =" + getBooks().get(getI()).get_id());
                i = getI() + 1;

                switch (getRand().nextInt(3)) {
                    case 0:
                        getButton_1().setText(getBooks().get(getI()).getEng());
                        button_ind_2 = getRand().nextInt(getS());
                        getButton_2().setText(getBookArrayRand().get(getButton_ind_2()).getEng());
                        button_ind_3 = getRand().nextInt(getS());
                        getButton_3().setText(getBookArrayRand().get(getButton_ind_3()).getEng());
                        button_ind_4 = getRand().nextInt(getS());
                        getButton_4().setText(getBookArrayRand().get(getButton_ind_4()).getEng());
                        break;
                    case 1:
                        button_ind_1 = getRand().nextInt(getS());
                        getButton_1().setText(getBookArrayRand().get(getButton_ind_1()).getEng());
                        getButton_2().setText(getBooks().get(getI()).getEng());
                        button_ind_3 = getRand().nextInt(getS());
                        getButton_3().setText(getBookArrayRand().get(getButton_ind_3()).getEng());
                        button_ind_4 = getRand().nextInt(getS());
                        getButton_4().setText(getBookArrayRand().get(getButton_ind_4()).getEng());
                        break;

                    case 2:
                        button_ind_1 = getRand().nextInt(getS());
                        getButton_1().setText(getBookArrayRand().get(getButton_ind_1()).getEng());
                        button_ind_2 = getRand().nextInt(getS());
                        getButton_2().setText(getBookArrayRand().get(getButton_ind_2()).getEng());
                        getButton_3().setText(getBooks().get(getI()).getEng());
                        button_ind_4 = getRand().nextInt(getS());
                        getButton_4().setText(getBookArrayRand().get(getButton_ind_4()).getEng());
                        break;

                    case 3:
                        button_ind_1 = getRand().nextInt(getS());
                        getButton_1().setText(getBookArrayRand().get(getButton_ind_1()).getEng());
                        button_ind_2 = getRand().nextInt(getS());
                        getButton_2().setText(getBookArrayRand().get(getButton_ind_2()).getEng());
                        button_ind_3 = getRand().nextInt(getS());
                        getButton_3().setText(getBookArrayRand().get(getButton_ind_3()).getEng());
                        getButton_4().setText(getBooks().get(getI()).getEng());
                        break;

                    default:
                        button_ind_1 = getRand().nextInt(getS());
                        getButton_1().setText(getBookArrayRand().get(getButton_ind_1()).getEng());
                        button_ind_2 = getRand().nextInt(getS());
                        getButton_2().setText(getBookArrayRand().get(getButton_ind_2()).getEng());
                        button_ind_3 = getRand().nextInt(getS());
                        getButton_3().setText(getBookArrayRand().get(getButton_ind_3()).getEng());

                        getButton_4().setText(getBookArrayRand().get(getI()).getEng());
                        break;// выдаёт дубликаты
                }

                getTextViewCounter().setText("" + getI() + "/" + getTotal());
                getTextViewBel().setText(getBooks().get(getI()).getBel());
                getTextViewBelLat().setText(getBooks().get(getI()).getBel_lat());


            }
        }

        if (getI() == (getBooks().size() - 1)) {
            if (((Button) v).getText().toString() == getBooks().get(getI()).getEng()) {

                getSqLiteDatabase().execSQL("UPDATE words SET flag = 3 WHERE _id =" + getBooks().get(getI()).get_id());

                getButton_1().setVisibility(View.INVISIBLE);
                getButton_2().setVisibility(View.INVISIBLE);
                getButton_3().setVisibility(View.INVISIBLE);
                getButton_4().setVisibility(View.INVISIBLE);
                getTextViewBel().setText("Learn some words to start here");
                getTextViewBelLat().setVisibility(View.INVISIBLE);
                getTextViewCounter().setVisibility(View.INVISIBLE);
            }
        }
    }

    public Random getRand() {
        return rand;
    }

    public Cursor getCursor() {
        return cursor;
    }

    public Cursor getCursor_total() {
        return cursor_total;
    }

    public Cursor getCursor_rand() {
        return cursor_rand;
    }

    public ArrayList<Book> getBooks() {
        return books;
    }

    public ArrayList<Book> getBookArrayRand() {
        return bookArrayRand;
    }

    public String getTotal() {
        return total;
    }

    public int getI() {
        return i;
    }

    public int getS() {
        return s;
    }

    public int getButton_ind_1() {
        return button_ind_1;
    }

    public int getButton_ind_2() {
        return button_ind_2;
    }

    public int getButton_ind_3() {
        return button_ind_3;
    }

    public int getButton_ind_4() {
        return button_ind_4;
    }

    public TextView getTextViewCounter() {
        return textViewCounter;
    }

    public TextView getTextViewBel() {
        return textViewBel;
    }

    public TextView getTextViewBelLat() {
        return textViewBelLat;
    }

    public Button getButton_1() {
        return Button_1;
    }

    public Button getButton_2() {
        return Button_2;
    }

    public Button getButton_3() {
        return Button_3;
    }

    public Button getButton_4() {
        return Button_4;
    }

    public DatabaseHelper getDatabaseHelper() {
        return databaseHelper;
    }

    public SQLiteDatabase getSqLiteDatabase() {
        return sqLiteDatabase;
    }
}

class IndexChecker {
    public static int indexChecking(int index, ArrayList<Book> book_rand, ArrayList<Book> book) {
        Random rand = new Random();
        int rand_size = book_rand.size();
        int rand_index = rand.nextInt(rand_size);

        while (book_rand.get(rand_index).getEng().compareTo(book.get(index).getEng()) == 0) {
            rand_index = rand.nextInt(rand_size);
        }

        return rand_index;
    }

}

