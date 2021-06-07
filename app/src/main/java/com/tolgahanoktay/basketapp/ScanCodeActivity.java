package com.tolgahanoktay.basketapp;

import android.Manifest;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.zxing.Result;

import java.io.ByteArrayOutputStream;
import java.sql.Blob;
import java.util.ArrayList;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class ScanCodeActivity extends AppCompatActivity implements ZXingScannerView.ResultHandler {

    int MY_PERMİSSİON_REQUEST_CAMERA = 0;
    ZXingScannerView scannerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        scannerView = new ZXingScannerView(this);
        setContentView(scannerView);


        //CustomList,barcodeArray,image_line,product_nameArray,product_priceArray,product_unitPrice
        //arrayAdapter = new ArrayAdapter(new CustomList(product_nameArray,product_unitPrice,product_priceArray,product_priceArray));
    }

    @Override
    public void handleResult(Result result) {
        // MainActivity.resultTextView.setText(result.getText());
    }

        @Override
    protected void onPause() {
        super.onPause();
        scannerView.stopCamera();
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CAMERA) !=
                PackageManager.PERMISSION_GRANTED){

            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.CAMERA},MY_PERMİSSİON_REQUEST_CAMERA);
        }

        scannerView.setResultHandler(this);
        scannerView.startCamera();
    }



}
