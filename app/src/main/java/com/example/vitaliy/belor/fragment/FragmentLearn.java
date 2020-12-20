package com.example.vitaliy.belor.fragment;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.vitaliy.belor.R;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import entity.User;
import entity.Word;

import static android.content.ContentValues.TAG;

// flag = 0 if unlearned, 1 if learned, 2 if processed of learn, 3 if tested

public class FragmentLearn extends Fragment {
    private FirebaseDatabase database;

    private ArrayList<Word> words = new ArrayList<>();
    private TextView textViewLangFirst;
    private TextView textViewLangSecond;
    private TextView textViewCounter;

    private int currentWordIndex = 0;

    private Button buttonShowTranslation;
    private Button buttonNextWord;
    private Button buttonShare;

    private AdView mAdView;

    private User user;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        words.clear();

        database = FirebaseDatabase.getInstance();
        getUser();
        getWords();

        currentWordIndex = 0;


        View v = inflater.inflate(R.layout.fragment_fragment_learn, container, false);
        mAdView = v.findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        buttonShowTranslation = v.findViewById(R.id.buttonShowTranslation);
        buttonNextWord = v.findViewById(R.id.buttonNextWord);
        buttonShare = v.findViewById(R.id.buttonShare);
        buttonNextWord.setVisibility(View.INVISIBLE);
        buttonShowTranslation.setVisibility(View.INVISIBLE);
        buttonShare.setVisibility(View.INVISIBLE);

        RelativeLayout relativeLayout = v.findViewById(R.id.fragmentLearn);

        AnimationDrawable animationDrawable = (AnimationDrawable) relativeLayout.getBackground();
        animationDrawable.setEnterFadeDuration(750);
        animationDrawable.setExitFadeDuration(5000);
        animationDrawable.start();


        textViewCounter = v.findViewById(R.id.textViewCounter);
        textViewLangFirst = v.findViewById(R.id.textViewLangFirst);
        textViewLangSecond = v.findViewById(R.id.textViewLangSecond);

        textViewCounter.setVisibility(View.INVISIBLE);
        textViewLangFirst.setText("Move to the next page");
        textViewLangSecond.setVisibility(View.INVISIBLE);


//
//        cursor = sqLiteDatabase.rawQuery("SELECT * FROM words WHERE flag =0 ", null);
//        cursor.moveToFirst();
//        int total = cursor.getCount();
//
//        i = 0;
//        words.clear();// clear from previus onCreate step
//        if (cursor != null && cursor.getCount() > 0) {
//            buttonNextWord.setVisibility(View.VISIBLE);
//            buttonShowTranslation.setVisibility(View.VISIBLE);
//            buttonShare.setVisibility(View.VISIBLE);
//            if (cursor.moveToFirst()) {
//                do {// adding to array only processed of learn
//
//                    Word word;
//                    word = new Word(cursor.getString(0), cursor.getString(1), cursor.getString(2),
//                            cursor.getString(3), cursor.getString(4));
//                    words.add(word);
//                } while (cursor.moveToNext());
//            }
//
//            textViewCounter.setVisibility(View.VISIBLE);
//            textViewEng.setVisibility(View.VISIBLE);
//            textViewLat.setVisibility(View.VISIBLE);
//
//            textViewCounter.setText("" + i + "/" + total);
//            textViewBel.setText(words.get(i).getBel());
//            textViewLat.setText("[" + words.get(i).getBel_lat() + "]");
//            textViewEng.setText("");
//            id = Integer.parseInt(words.get(i).get_id());
//
//            buttonNextWord.setOnClickListener(new View.OnClickListener() {
//                public void onClick(View v) {
//
//
//                    if (i < words.size() && i == (words.size() - 1)) {//cheking of the last element
//                        sqLiteDatabase.execSQL("UPDATE words SET flag = 2 WHERE bel ='" + textViewBel.getText().toString() + "'");
//                        textViewCounter.setText("" + i + "/" + cursor.getCount());
//                        textViewBel.setText(words.get(i).getBel());
//                        textViewLat.setText("[" + words.get(i).getBel_lat() + "]");
//                        textViewEng.setText("");
//                        i++;
//                        counter++;
//                    }
//                    if (i < (words.size()) - 1) {//cheking if the begining and pre-ending element
//
//                        if (buttonPrev == 2) {
//                            Collections.swap(words, (words.size() - 1), i);
//
//                            textViewBel.setText(words.get(i).getBel());
//                            textViewLat.setText("[" + words.get(i).getBel_lat() + "]");
//                            textViewEng.setText("");
//                        } else {
//                            sqLiteDatabase.execSQL("UPDATE words SET flag = 2 WHERE bel ='" + textViewBel.getText().toString() + "'");
//                            i++;
//                            counter++;
//                            textViewCounter.setText("" + counter + "/" + cursor.getCount());
//                            textViewBel.setText(words.get(i).getBel());
//                            textViewLat.setText("[" + words.get(i).getBel_lat() + "]");
//                            textViewEng.setText("");
//                        }
//                    }
//                    if (words.size() < i || words.size() == i) {// cheking if more than array size and hide all
//                        textViewBel.setText("Move to the next page");
//                        textViewEng.setVisibility(View.INVISIBLE);
//                        textViewLat.setVisibility(View.INVISIBLE);
//                        textViewCounter.setVisibility(View.INVISIBLE);
//                        buttonNextWord.setClickable(false);
//                        buttonShowTranslation.setClickable(false);
//                        buttonShare.setClickable(false);
//                        buttonNextWord.setVisibility(View.INVISIBLE);
//                        buttonShowTranslation.setVisibility(View.INVISIBLE);
//                        buttonShare.setVisibility(View.INVISIBLE);
//
//                    }
//
//                    buttonPrev = 1;
//                }
//
//
//            });
//
//            buttonShare.setOnClickListener(new View.OnClickListener() {
//                public void onClick(View v) {
//                    if (i < (words.size())) {
//                        Intent intent = new Intent(Intent.ACTION_SEND);
//                        intent.setType("text/plain");
//                        // String body = book_array.get(i).bel+" "+book_array.get(i).eng;
//                        String sub = words.get(i).getBel() + " " + words.get(i).getEng();
//                        //intent.putExtra(Intent.EXTRA_SUBJECT,body);
//                        intent.putExtra(Intent.EXTRA_TEXT, sub);
//                        startActivity(Intent.createChooser(intent, "share"));
//                    }
//                }
//            });
//

//        }
        buttonShowTranslation.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                textViewLangSecond.setVisibility(View.VISIBLE);
            }
        });

        buttonNextWord.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (!(user.getAcquaintedWords() == null)) {
                    user.getAcquaintedWords().add(words.get(currentWordIndex));
                } else {
                    ArrayList<Word> wordArrayList = new ArrayList<>();
                    wordArrayList.add(words.get(currentWordIndex));
                    user.setAcquaintedWords(wordArrayList);

                }

                updateUser();
                getUser();

                currentWordIndex++;
                textViewLangFirst.setText(words.get(currentWordIndex).getLangFirst());
                textViewLangSecond.setText(words.get(currentWordIndex).getLangSecond());

            }
        });

        return v;
    }

    private void updateUser() {
        FirebaseDatabase.getInstance().getReference("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(user)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(getView().getContext(), "User updated", Toast.LENGTH_LONG).show();

                        } else {
                            Toast.makeText(getView().getContext(), "User not updated", Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }

    private void getWords() {
        DatabaseReference databaseReference;
        final String WORDS = "words";

        words = new ArrayList<>();
        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference();

        databaseReference.child(WORDS).addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                Iterable<DataSnapshot> children = snapshot.getChildren();

                for (DataSnapshot child : children) {
                    Word word = child.getValue(Word.class);
                    words.add(word);
                }

                onWordsLoaded();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    private void getUser() {
        final String USERS = "Users";

        final String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();

        FirebaseDatabase.getInstance().getReference(USERS).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    if (dataSnapshot.getKey().equals(uid)) {
                        user = dataSnapshot.getValue(User.class);
                        onUserLoaded();
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    private void onWordsLoaded() {
        Toast.makeText(getView().getContext(), "Word Downloaded", Toast.LENGTH_LONG).show();
        buttonNextWord.setVisibility(View.VISIBLE);
        buttonShowTranslation.setVisibility(View.VISIBLE);
        textViewLangFirst.setVisibility(View.VISIBLE);
        textViewLangSecond.setVisibility(View.INVISIBLE);

        final Handler handler = new Handler(Looper.getMainLooper());
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (!(user.getAcquaintedWords() == null)) {
                    words.removeAll(user.getAcquaintedWords());
                }
                if (!(user.getTestedWords() == null)) {
                    words.removeAll(user.getTestedWords());
                }
                if (!(user.getMatchedWords() == null)) {
                    words.removeAll(user.getMatchedWords());
                }
            }
        }, 2000);

        textViewLangFirst.setText(words.get(currentWordIndex).getLangFirst());
        textViewLangSecond.setText(words.get(currentWordIndex).getLangSecond());


        Log.w(TAG, "Data to Download");
    }

    private void onUserLoaded() {
        Toast.makeText(getView().getContext(), "User Downloaded " + user.getEmail() + user.getPassword(), Toast.LENGTH_LONG).show();
    }

}
