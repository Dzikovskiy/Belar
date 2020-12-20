package com.example.vitaliy.belor.dao;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

import entity.Word;

import static android.content.ContentValues.TAG;

public class WordDao {
    private FirebaseDatabase database;
    private ArrayList<Word> words;
    private DatabaseReference databaseReference;
    private final String WORDS = "words";


    public ArrayList<Word> getAll() {
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


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        return words;
    }
}
