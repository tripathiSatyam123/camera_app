package com.example.cameraapp;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    ImageView image;
    Button button;
    public static final int CAMERA_PERMISSION_CODE = 1;
    public static final int CAMERA_IMAGE_CODE = 2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        image = findViewById(R.id.image);
        button = findViewById(R.id.btnCamera);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

                    if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                        // if camera is not allowed
                        String[] permissions = {Manifest.permission.CAMERA};

                        requestPermissions(permissions, CAMERA_PERMISSION_CODE);
                    } else {
                        // camera is allowed
                        takePicture();


                    }

                }else{
                   takePicture();
                }
            }
        });

    }

    private void takePicture(){
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(cameraIntent, CAMERA_IMAGE_CODE);
    }
}