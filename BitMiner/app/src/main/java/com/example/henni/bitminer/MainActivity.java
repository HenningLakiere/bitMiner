package com.example.henni.bitminer;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.file.Path;

public class MainActivity extends AppCompatActivity {

    private SaveLoad i;
    private Context c;

    private int bitCoins = 0;
    private int price;
    private Integer[] stats;


    Handler h = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            final TextView txtBitCoins = findViewById(R.id.txtBitCoins);
            final TextView txtIdleValue = findViewById(R.id.txtIdleValue);
            final TextView txtClickValue = findViewById(R.id.txtClickValue);

            txtBitCoins.setText(stats[1] + " B");
            txtIdleValue.setText("GPU Speed: " + String.valueOf(stats[4]));
            txtClickValue.setText("CPU Speed: " + String.valueOf(stats[3]));

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        c = getBaseContext().getApplicationContext();
        stats = new Integer[15];

        i = new SaveLoad();
        i.newFile(c);

        stats = i.readFromFile(c);

        runFastThread();
        runSlowThread();


        final Button btnClick = findViewById(R.id.btnClick);
        btnClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stats[1] = stats[1] + stats[3];
                setBitCoins(stats[1]);
                i.writeToFile(stats,c);
            }
        });

        final ConstraintLayout lytShop = findViewById(R.id.lytShop);
        Button btnShop = findViewById(R.id.btnShop);
        btnShop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(lytShop.getVisibility()== View.VISIBLE){
                    lytShop.setVisibility(View.INVISIBLE);
                    btnClick.setEnabled(true);
                }
                else{
                    lytShop.setVisibility(View.VISIBLE);
                    btnClick.setEnabled(false);
                }

            }
        });

        final Button btnGpu1 = findViewById(R.id.btnGpu1);
        final Button btnGpu2 = findViewById(R.id.btnGpu2);
        final Button btnGpu3 = findViewById(R.id.btnGpu3);
        final Button btnGpu4 = findViewById(R.id.btnGpu4);
        final Button btnGpu5 = findViewById(R.id.btnGpu5);

        final Button btnCpu1 = findViewById(R.id.btnCpu1);
        final Button btnCpu2 = findViewById(R.id.btnCpu2);
        final Button btnCpu3 = findViewById(R.id.btnCpu3);
        final Button btnCpu4 = findViewById(R.id.btnCpu4);
        final Button btnCpu5 = findViewById(R.id.btnCpu5);

        btnGpu1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                price = (int) (1000 * Math.pow(1.1, stats[10]));

                if(stats[1]>=price){
                    stats[1]=stats[1]-price;
                    stats[4] = stats[4] + 10;
                    stats[10]++;
                    price = (int) (1000 * Math.pow(1.1, stats[10]));
                    btnGpu1.setText("GPU 1 " + price + "B");
                }
            }
        });

        btnGpu2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                price = (int) (10000 * Math.pow(1.1, stats[11]));

                if(stats[1]>=price){
                    stats[1]=stats[1]-price;
                    stats[4] = stats[4] + 50;
                    stats[11]++;
                    price = (int) (10000 * Math.pow(1.1, stats[11]));
                    btnGpu2.setText("GPU 2 " + price + "B");
                }
            }
        });

        btnGpu3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                price = (int) (100000 * Math.pow(1.1, stats[12]));

                if(stats[1]>=price){
                    stats[1]=stats[1]-price;
                    stats[4] = stats[4] + 200;
                    stats[12]++;
                    price = (int) (100000 * Math.pow(1.1, stats[12]));
                    btnGpu3.setText("GPU 3 " + price + "B");
                }
            }
        });

        btnGpu4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                price = (int) (1000000 * Math.pow(1.1, stats[13]));

                if(stats[1]>=price){
                    stats[1]=stats[1]-price;
                    stats[4] = stats[4] + 600;
                    stats[13]++;
                    price = (int) (1000000 * Math.pow(1.1, stats[13]));
                    btnGpu4.setText("GPU 4 " + price + "B");
                }
            }
        });

        btnGpu5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                price = (int) (10000000 * Math.pow(1.1, stats[14]));

                if(stats[1]>=price){
                    stats[1]=stats[1]-price;
                    stats[4] = stats[4] + 1200;
                    stats[14]++;
                    price = (int) (10000000 * Math.pow(1.1, stats[14]));
                    btnGpu5.setText("GPU 5 " + price + "B");
                }
            }
        });

        btnCpu1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                price = (int) (100 * Math.pow(1.1, stats[5]));

                if(stats[1]>=price){
                    stats[1]=stats[1]-price;
                    stats[3] = stats[3] + 1;
                    stats[5]++;
                    price = (int) (100 * Math.pow(1.1, stats[5]));
                    btnCpu1.setText("CPU 1 " + price + "B");
                }
            }
        });

        btnCpu2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                price = (int) (500 * Math.pow(1.1, stats[6]));

                if(stats[1]>=price){
                    stats[1]=stats[1]-price;
                    stats[3] = stats[3] + 5;
                    stats[6]++;
                    price = (int) (500 * Math.pow(1.1, stats[6]));
                    btnCpu2.setText("CPU 2 " + price + "B");
                }
            }
        });

        btnCpu3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                price = (int) (3000 * Math.pow(1.1, stats[7]));

                if(stats[1]>=price){
                    stats[1]=stats[1]-price;
                    stats[3] = stats[3] + 20;
                    stats[7]++;
                    price = (int) (3000 * Math.pow(1.1, stats[7]));
                    btnCpu3.setText("CPU 3 " + price + "B");
                }
            }
        });

        btnCpu4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                price = (int) (30000 * Math.pow(1.1, stats[8]));

                if(stats[1]>=price){
                    stats[1]=stats[1]-price;
                    stats[3] = stats[3] + 100;
                    stats[8]++;
                    price = (int) (30000 * Math.pow(1.1, stats[8]));
                    btnCpu4.setText("CPU 4 " + price + "B");
                }
            }
        });

        btnCpu5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                price = (int) (1000000 * Math.pow(1.1, stats[9]));

                if(stats[1]>=price){
                    stats[1]=stats[1]-price;
                    stats[3] = stats[3] + 3000;
                    stats[9]++;
                    price = (int) (1000000 * Math.pow(1.1, stats[9]));
                    btnCpu5.setText("CPU 5 " + price + "B");
                }
            }
        });

    }

    public void runFastThread(){
        Runnable r = new Runnable() {
            @Override
            public void run() {
                while(stats[1]>=0){
                    stats[1] = stats[1] + stats[4];
                    setBitCoins(bitCoins);
                    synchronized (this){
                        try {
                            wait(100);
                        }catch (Exception e){}
                    }
                }
            }
        };
        Thread t = new Thread(r);
        t.start();
    }

    public void runSlowThread(){
        Runnable r = new Runnable() {
            @Override
            public void run() {
                while(stats[1]>=0){
                    i.writeToFile(stats,c);
                    synchronized (this){
                        try {
                            wait(2000);
                        }catch (Exception e){}
                    }
                }
            }
        };
        Thread t = new Thread(r);
        t.start();
    }


    public void setBitCoins(double bitCoins){
        Bundle bundle = new Bundle();
        Message msg = new Message();

        bundle.putDouble("bitCoinsValue", bitCoins);
        msg.setData(bundle);
        h.sendMessage(msg);
    }

}
