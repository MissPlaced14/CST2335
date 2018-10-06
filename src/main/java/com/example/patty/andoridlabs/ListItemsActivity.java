package com.example.patty.andoridlabs;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.Toast;

public class ListItemsActivity extends Activity {
    private static final String ACTIVITY_NAME = "ListItemsActivity";
    static final int REQUEST_IMAGE_CAPTURE = 1;
    protected ImageButton imgBtn;
    protected Switch aSwitch;
    protected CheckBox ckBx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_items);
        Log.i(ACTIVITY_NAME, "in onCreate()");

        imgBtn = findViewById(R.id.imgBtn);
        imgBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                takePictureIntent();
            }
        });
       aSwitch = findViewById(R.id.switch1);
       aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
           @Override
           public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
               CharSequence textOn = "Switch is On";
               CharSequence textOff = "Switch is off";
               int duration = Toast.LENGTH_SHORT;
               if (isChecked) {
                   Toast toastOn = Toast.makeText(getApplicationContext(), textOn, duration);
                   toastOn.show();
               } else {
                   duration = Toast.LENGTH_LONG;
                   Toast toastOff = Toast.makeText(getApplicationContext(), textOff, duration);
                   toastOff.show();
               }
           }});
    ckBx = findViewById(R.id.checkBx);
    ckBx.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            if (isChecked) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ListItemsActivity.this);
                builder.setMessage(R.string.dialogMsg)
                        .setTitle(R.string.dialogTitle)
                        .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent resultIntent = new Intent();
                                resultIntent.putExtra("Response", "Here is my response");
                                setResult(Activity.RESULT_OK, resultIntent);
                                finish();
                            }
                        })
                        .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        })
                        .show();
            }}});
    }
    private void takePictureIntent() {
        Intent takePicture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePicture.resolveActivity(getPackageManager()) != null){
            startActivityForResult(takePicture, REQUEST_IMAGE_CAPTURE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK){
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            imgBtn.setImageBitmap(imageBitmap);
        }
    }
    protected void onResume(){
        super.onResume();
        Log.i(ACTIVITY_NAME, "In onResume()");

    }
    protected void onStart(){
        super.onStart();
        Log.i(ACTIVITY_NAME, "In onStart()");
    }
    protected void onPause(){
        super.onPause();
        Log.i(ACTIVITY_NAME, "In onPause()");
    }
    protected void onStop(){
        super.onStop();
        Log.i(ACTIVITY_NAME, "In onStop()");
    }
    protected void onDestroy(){
        super.onDestroy();
        Log.i(ACTIVITY_NAME, "In onDestroy()");
    }
}
