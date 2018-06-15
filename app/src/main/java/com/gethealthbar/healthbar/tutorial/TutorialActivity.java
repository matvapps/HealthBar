package com.gethealthbar.healthbar.tutorial;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.edwardvanraak.materialbarcodescanner.MaterialBarcodeScanner;
import com.edwardvanraak.materialbarcodescanner.MaterialBarcodeScannerBuilder;
import com.gethealthbar.healthbar.R;
import com.gethealthbar.healthbar.data.KeyValueStorage;
import com.google.android.gms.vision.barcode.Barcode;

import me.relex.circleindicator.CircleIndicator;

import static com.edwardvanraak.materialbarcodescanner.MaterialBarcodeScanner.RC_HANDLE_CAMERA_PERM;

public class TutorialActivity extends AppCompatActivity {

    public static final String BARCODE_KEY = "BARCODE";

    private Barcode barcodeResult;
    private Button cancelBtn;
    private Button nextBtn;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        KeyValueStorage keyValueStorage = new KeyValueStorage(this);
//        if (keyValueStorage.getVisited()) {
//            startScan();
//            finishThisActivity();
//            if (savedInstanceState != null) {
//                Barcode restoredBarcode = savedInstanceState.getParcelable(BARCODE_KEY);
//                if (restoredBarcode != null) {
//                    barcodeResult = restoredBarcode;
//                }
//            }
//        } else {

            setContentView(R.layout.activity_tutorial);
            cancelBtn = findViewById(R.id.cancel_btn);
            nextBtn = findViewById(R.id.next_btn);
            final String[] mPermissions = new String[]{Manifest.permission.CAMERA};
            ActivityCompat.requestPermissions(this, mPermissions, 5);

            // Uncomment this to show tutorial only on app first launch
//            keyValueStorage.setVisited(true);
            TutorialPageAdapter tutorialPageAdapter = new TutorialPageAdapter(getSupportFragmentManager());

            final ViewPager viewpager = findViewById(R.id.viewpager);
            CircleIndicator indicator = findViewById(R.id.indicator);
            viewpager.setAdapter(tutorialPageAdapter);
            indicator.setViewPager(viewpager);


            viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                }

                @Override
                public void onPageSelected(int position) {
                    if (position == 2) {
                        nextBtn.setText("Start scan");
                    } else {
                        nextBtn.setText("NEXT");
                    }
                }

                @Override
                public void onPageScrollStateChanged(int state) {

                }
            });

            cancelBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startScan();
                    finishThisActivity();
                    if (savedInstanceState != null) {
                        Barcode restoredBarcode = savedInstanceState.getParcelable(BARCODE_KEY);
                        if (restoredBarcode != null) {
                            barcodeResult = restoredBarcode;
                        }
                    }
                }
            });

            nextBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (nextBtn.getText().equals("NEXT")) {
                        viewpager.setCurrentItem(viewpager.getCurrentItem() + 1, true);
                    } else {
                        cancelBtn.callOnClick();
                    }
                }
            });

        }

//    }

    private void finishThisActivity() {
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                finish();
            }
        }, 100);
    }

    private void startScan() {
        /**
         * Build a new MaterialBarcodeScanner
         */

        final MaterialBarcodeScanner materialBarcodeScanner = new MaterialBarcodeScannerBuilder()
                .withActivity(TutorialActivity.this)
                .withEnableAutoFocus(true)
                .withBleepEnabled(true)
                .withBackfacingCamera()
                .withCenterTracker()
                .withResultListener(new MaterialBarcodeScanner.OnResultListener() {
                    @Override
                    public void onResult(Barcode barcode) {
                        barcodeResult = barcode;
                        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.youtube.com"));
                        startActivity(browserIntent);
                    }
                })
                .build();
        materialBarcodeScanner.startScan();
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putParcelable(BARCODE_KEY, barcodeResult);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode != RC_HANDLE_CAMERA_PERM) {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
            return;
        }
        if (grantResults.length != 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            startScan();
            return;
        }
        DialogInterface.OnClickListener listener = new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        };
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Error")
                .setMessage(R.string.no_camera_permission)
                .setPositiveButton(android.R.string.ok, listener)
                .show();
    }
}
