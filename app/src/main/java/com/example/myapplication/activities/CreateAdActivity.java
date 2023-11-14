package com.example.myapplication.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.content.AsyncTaskLoader;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.database.AdsDatabase;
import com.example.myapplication.entities.Ad;
import com.google.android.material.bottomsheet.BottomSheetBehavior;

import java.nio.channels.AsynchronousChannelGroup;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class CreateAdActivity extends AppCompatActivity {

    private EditText inputAdTitle, inputAdSubtitle, inputAdText;
    private TextView textDateTime;
    private View viewSubtitleIndicator;

    private String selectedAdColor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_ad);

        ImageView imageBack = findViewById(R.id.imageBack);
        imageBack.setOnClickListener((v)-> { onBackPressed();});
        inputAdTitle = findViewById(R.id.inputAdTitle);
        inputAdSubtitle = findViewById(R.id.inputNoteSubtitle);
        inputAdText = findViewById(R.id.inputAd);
        textDateTime = findViewById(R.id.textDateTime);
        viewSubtitleIndicator = findViewById(R.id.viewSubtitleIndicator);

        textDateTime.setText(
                new SimpleDateFormat("EEEE, dd MMMM yyyy HH:mm a", Locale.getDefault())
                        .format(new Date())
        );

        ImageView imageSave = findViewById(R.id.imageSave);
        imageSave.setOnClickListener((v)-> { saveAd(); });

        selectedAdColor = "#333333";
        initMiscellaneous();
        setSubtitleIndicatorColor();

         }

            private void saveAd() {
        if (inputAdTitle.getText().toString().trim().isEmpty()) {
            Toast.makeText(this, "Ad title can't be empty!",Toast.LENGTH_SHORT).show();
            return;
            } else if(inputAdSubtitle.getText().toString().trim().isEmpty()
              && inputAdText.getText().toString().trim().isEmpty()) {
            Toast.makeText(this, "Ad can't be empty!", Toast.LENGTH_SHORT).show();
            return;
        }

        final Ad ad = new Ad();
        ad.setTitle(inputAdTitle.getText().toString());
        ad.setSubtitle(inputAdSubtitle.getText().toString());
        ad.setAdText(inputAdText.getText().toString());
        ad.setDateTime(textDateTime.getText().toString());
        ad.setColor(selectedAdColor);


        @SuppressLint("StaticFieldLeak")
        class SaveAdTask extends AsyncTask<Void, Void, Void> {

            @Override
            protected Void doInBackground(Void... voids) {
                AdsDatabase.getDatabase(getApplicationContext()).adDao().insertAd(ad);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                Intent intent = new Intent();
                setResult(RESULT_OK, intent);
                finish();
            }

        }

        new SaveAdTask().execute();
            }

           private void initMiscellaneous(){
        final LinearLayout layoutMiscellaneous = findViewById(R.id.layoutMiscellaneous);
        final BottomSheetBehavior<LinearLayout> bottomsheetBehavior = BottomSheetBehavior.from(layoutMiscellaneous);
        layoutMiscellaneous.findViewById(R.id.textMiscellaneous).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (bottomsheetBehavior.getState() !=BottomSheetBehavior.STATE_EXPANDED) {
                    bottomsheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                } else {
                    bottomsheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                }

            }
        });

        final ImageView imageColor1 = layoutMiscellaneous.findViewById(R.id.imageColor1);
        final ImageView imageColor2 = layoutMiscellaneous.findViewById(R.id.imageColor2);
        final ImageView imageColor3 = layoutMiscellaneous.findViewById(R.id.imageColor3);
        final ImageView imageColor4 = layoutMiscellaneous.findViewById(R.id.imageColor4);
        final ImageView imageColor5 = layoutMiscellaneous.findViewById(R.id.imageColor5);

        layoutMiscellaneous.findViewById(R.id.viewColor1).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                selectedAdColor = "#333333";
                imageColor1.setImageResource(0);
                imageColor2.setImageResource(0);
                imageColor3.setImageResource(0);
                imageColor4.setImageResource(0);
                imageColor5.setImageResource(0);
                setSubtitleIndicatorColor();

            }
        });


               layoutMiscellaneous.findViewById(R.id.viewColor2).setOnClickListener(new View.OnClickListener(){
                   @Override
                   public void onClick(View v) {
                       selectedAdColor = "#FDBE3B";
                       imageColor1.setImageResource(0);
                       imageColor2.setImageResource(R.drawable.baseline_done_24);
                       imageColor3.setImageResource(0);
                       imageColor4.setImageResource(0);
                       imageColor5.setImageResource(0);
                       setSubtitleIndicatorColor();

                   }
               });


               layoutMiscellaneous.findViewById(R.id.viewColor3).setOnClickListener(new View.OnClickListener(){
                   @Override
                   public void onClick(View v) {
                       selectedAdColor = "#FF4842";
                       imageColor1.setImageResource(0);
                       imageColor2.setImageResource(0);
                       imageColor3.setImageResource(R.drawable.baseline_done_24);
                       imageColor4.setImageResource(0);
                       imageColor5.setImageResource(0);
                       setSubtitleIndicatorColor();

                   }
               });

               layoutMiscellaneous.findViewById(R.id.viewColor4).setOnClickListener(new View.OnClickListener(){
                   @Override
                   public void onClick(View v) {
                       selectedAdColor = "#3A52Fc";
                       imageColor1.setImageResource(0);
                       imageColor2.setImageResource(0);
                       imageColor3.setImageResource(0);
                       imageColor4.setImageResource(R.drawable.baseline_done_24);
                       imageColor5.setImageResource(0);
                       setSubtitleIndicatorColor();

                   }
               });

               layoutMiscellaneous.findViewById(R.id.viewColor5).setOnClickListener(new View.OnClickListener(){
                   @Override
                   public void onClick(View v) {
                       selectedAdColor = "#000000";
                       imageColor1.setImageResource(0);
                       imageColor2.setImageResource(0);
                       imageColor3.setImageResource(0);
                       imageColor4.setImageResource(0);
                       imageColor5.setImageResource(R.drawable.baseline_done_24);
                       setSubtitleIndicatorColor();

                   }
               });








           }

        private void setSubtitleIndicatorColor(){
            GradientDrawable gradientDrawable = (GradientDrawable) viewSubtitleIndicator.getBackground();
          gradientDrawable.setColor(Color.parseColor(selectedAdColor));
        }
    }
