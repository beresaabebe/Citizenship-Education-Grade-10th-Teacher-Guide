package com.beckytech.citizenshipeducationgrade10.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.beckytech.citizenshipeducationgrade10.R;
import com.beckytech.citizenshipeducationgrade10.model.Model;
import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.scroll.DefaultScrollHandle;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import java.util.ArrayList;
import java.util.List;

public class BookDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_detail);

        MobileAds.initialize(this, initializationStatus -> {
        });

        AdView mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        findViewById(R.id.back_book_detail).setOnClickListener(v -> onBackPressed());

        Intent intent = getIntent();
        Model model = (Model) intent.getSerializableExtra("data");

        TextView title = findViewById(R.id.title_book_detail);
        title.setSelected(true);
        title.setText(model.getTitle());

        TextView subTitle = findViewById(R.id.sub_title_book_detail);
        subTitle.setSelected(true);
        subTitle.setText(model.getSubTitle());

        PDFView pdfView = findViewById(R.id.pdfView);

        int start = model.getStartPage();
        int end = model.getEndPage();

        List<Integer> list = new ArrayList<>();

        for (int i = start; i <= end; i++) {
            list.add(i);
        }

        int[] array = new int[list.size()];

        for (int j = 1; j < array.length; j++) {
            array[j] = list.get(j);
        }

        Toast.makeText(this, "Array "+array.length, Toast.LENGTH_SHORT).show();

        pdfView.fromAsset("celatest.pdf")
                .pages(array)
                .enableSwipe(true)
                .swipeHorizontal(false)
                .spacing(10)
                .enableDoubletap(true)
                .scrollHandle(new DefaultScrollHandle(this))
                .load();
    }
}