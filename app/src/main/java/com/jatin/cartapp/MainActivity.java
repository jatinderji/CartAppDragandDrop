package com.jatin.cartapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity implements View.OnLongClickListener {

    ImageView imgCart;
    ImageView img1, img2, img3;
    private static int items = 0;
    LinearLayout llay;
    Button btnNotification;
    public static final String TAG = "jv";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imgCart = findViewById(R.id.imgCart);
        img1 = findViewById(R.id.img1);
        img2 = findViewById(R.id.img2);
        img3 = findViewById(R.id.img3);
        llay = findViewById(R.id.llay);
        btnNotification = findViewById(R.id.btnNotification);

        img1.setOnLongClickListener(this);
        img2.setOnLongClickListener(this);
        img3.setOnLongClickListener(this);

        imgCart.setOnDragListener(new View.OnDragListener() {
            @Override
            public boolean onDrag(View view, DragEvent dragEvent) {

                int action = dragEvent.getAction();
                switch (action){
                    case DragEvent.ACTION_DRAG_ENTERED:{
                        items++;
                      //  Log.d(TAG, "onDrag: ACTION_DRAG_ENTERED");
                        break;
                    }

                    case DragEvent.ACTION_DRAG_ENDED:{
                  //      Log.d(TAG, "onDrag: Ended");
                        btnNotification.setText(String.valueOf(items));
                        Snackbar snackbar = Snackbar.make(llay,"Items: "+items,Snackbar.LENGTH_LONG);
                        snackbar.show();
                        break;
                    }

                    case DragEvent.ACTION_DRAG_EXITED:{
                        items--;
                        break;
                    }
                }
                return true;
            }
        });

    }

    @Override
    public boolean onLongClick(View view) {

        ClipData clipData = ClipData.newPlainText("ok", "ok");
        View.DragShadowBuilder dragShadow = new View.DragShadowBuilder(view);

        view.startDrag(clipData, dragShadow, view, 0); // startDrag is depricated
        // use startDragandDrop(clipData,dragShadow,v,0)

        return true;
    }
}