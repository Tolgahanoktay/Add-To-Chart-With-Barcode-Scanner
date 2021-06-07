package com.tolgahanoktay.basketapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Map;

public class TryScreen extends AppCompatActivity {
    Button btn_add, btn_remove;
    private FirebaseFirestore firebaseFirestore;
    ArrayList<String> urun_adi_FBase;
    ArrayList<String> urun_br_fiyat_FBase;
    ArrayList<String> urun_resim_FBase;
    ArrayList<String> urun_son_tar_FBase;
    ShowDataAdapter showDataAdapter;

    @SuppressLint("WrongThread")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_try_screen);

        btn_add = findViewById(R.id.btn_add);

        urun_adi_FBase = new ArrayList<>();
        urun_br_fiyat_FBase =new ArrayList<>();
        urun_resim_FBase = new ArrayList<>();
        urun_son_tar_FBase = new ArrayList<>();

        firebaseFirestore = FirebaseFirestore.getInstance();


        getDataFromFirestore();

        RecyclerView recyclerView = findViewById(R.id.reyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        showDataAdapter = new ShowDataAdapter(urun_resim_FBase,urun_adi_FBase,urun_br_fiyat_FBase,urun_son_tar_FBase);
        recyclerView.setAdapter(showDataAdapter);

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TryScreen.this,ScanCodeActivity.class);
                startActivity(intent);
            }
        });

    }


    public void getDataFromFirestore() {

        CollectionReference collectionReference = firebaseFirestore.collection("Sepet");

        collectionReference.whereEqualTo("barkod", "8937680347").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {

                if (e != null) {
                    Toast.makeText(TryScreen.this, e.getLocalizedMessage().toString(), Toast.LENGTH_LONG).show();
                }

                if (queryDocumentSnapshots != null) {

                    for (DocumentSnapshot snapshot : queryDocumentSnapshots.getDocuments()) {

                        Map<String, Object> data = snapshot.getData();

                        //Casting
                        String urun_adi = (String) data.get("urun_adi");
                        String urun_br_fiyat = (String) data.get("urun_br_fiyat");
                        String urun_resim = (String) data.get("urun_resim");
                        String urun_son_tarih = (String) data.get("urun_son_tarih");

                        urun_adi_FBase.add(urun_adi);
                        urun_resim_FBase.add(urun_resim);
                        urun_br_fiyat_FBase.add(urun_br_fiyat);
                        urun_son_tar_FBase.add(urun_son_tarih);

                        showDataAdapter.notifyDataSetChanged();

                    }


                }

            }
        });

    }

    }
