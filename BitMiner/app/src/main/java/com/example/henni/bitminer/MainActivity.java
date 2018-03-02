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

    private Initialize i;

    private int bitCoins = 0;
    private int idleValue = 0;
    private int clickValue = 1;
    private int price;

    private double GPU1Count = 0;
    private double GPU2Count = 0;
    private double GPU3Count = 0;
    private double GPU4Count = 0;
    private double GPU5Count = 0;

    private double CPU1Count = 0;
    private double CPU2Count = 0;
    private double CPU3Count = 0;
    private double CPU4Count = 0;
    private double CPU5Count = 0;

    Handler h = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            final TextView txtBitCoins = findViewById(R.id.txtBitCoins);
            final TextView txtIdleValue = findViewById(R.id.txtIdleValue);
            final TextView txtClickValue = findViewById(R.id.txtClickValue);

            txtBitCoins.setText(String.valueOf(bitCoins) + "B");
            txtIdleValue.setText("GPU Speed: " + String.valueOf(idleValue));
            txtClickValue.setText("CPU Speed: " + String.valueOf(clickValue));

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        i = new Initialize();
        runBackgroundThread();


        final Button btnClick = findViewById(R.id.btnClick);
        btnClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bitCoins = bitCoins + clickValue;
                setbitCoins(bitCoins);
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
                price = (int) (1000 * Math.pow(1.1, GPU1Count));

                if(bitCoins>=price){
                    bitCoins=bitCoins-price;
                    idleValue = idleValue + 10;
                    GPU1Count++;
                    price = (int) (1000 * Math.pow(1.1, GPU1Count));
                    btnGpu1.setText("GPU 1 " + price + "B");
                }
            }
        });

        btnGpu2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                price = (int) (10000 * Math.pow(1.1, GPU2Count));

                if(bitCoins>=price){
                    bitCoins=bitCoins-price;
                    idleValue = idleValue + 50;
                    GPU2Count++;
                    price = (int) (10000 * Math.pow(1.1, GPU2Count));
                    btnGpu2.setText("GPU 2 " + price + "B");
                }
            }
        });

        btnGpu3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                price = (int) (100000 * Math.pow(1.1, GPU3Count));

                if(bitCoins>=price){
                    bitCoins=bitCoins-price;
                    idleValue = idleValue + 200;
                    GPU3Count++;
                    price = (int) (100000 * Math.pow(1.1, GPU3Count));
                    btnGpu3.setText("GPU 3 " + price + "B");
                }
            }
        });

        btnGpu4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                price = (int) (1000000 * Math.pow(1.1, GPU4Count));

                if(bitCoins>=price){
                    bitCoins=bitCoins-price;
                    idleValue = idleValue + 600;
                    GPU4Count++;
                    price = (int) (1000000 * Math.pow(1.1, GPU4Count));
                    btnGpu4.setText("GPU 4 " + price + "B");
                }
            }
        });

        btnGpu5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                price = (int) (10000000 * Math.pow(1.1, GPU5Count));

                if(bitCoins>=price){
                    bitCoins=bitCoins-price;
                    idleValue = idleValue + 1200;
                    GPU5Count++;
                    price = (int) (10000000 * Math.pow(1.1, GPU5Count));
                    btnGpu5.setText("GPU 5 " + price + "B");
                }
            }
        });

        btnCpu1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                price = (int) (100 * Math.pow(1.1, CPU1Count));

                if(bitCoins>=price){
                    bitCoins=bitCoins-price;
                    clickValue = clickValue + 1;
                    CPU1Count++;
                    price = (int) (100 * Math.pow(1.1, CPU1Count));
                    btnCpu1.setText("CPU 1 " + price + "B");
                }
            }
        });

        btnCpu2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                price = (int) (500 * Math.pow(1.1, CPU2Count));

                if(bitCoins>=price){
                    bitCoins=bitCoins-price;
                    clickValue = clickValue + 5;
                    CPU2Count++;
                    price = (int) (500 * Math.pow(1.1, CPU2Count));
                    btnCpu2.setText("CPU 2 " + price + "B");
                }
            }
        });

        btnCpu3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                price = (int) (3000 * Math.pow(1.1, CPU3Count));

                if(bitCoins>=price){
                    bitCoins=bitCoins-price;
                    clickValue = clickValue + 20;
                    CPU3Count++;
                    price = (int) (3000 * Math.pow(1.1, CPU3Count));
                    btnCpu3.setText("CPU 3 " + price + "B");
                }
            }
        });

        btnCpu4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                price = (int) (30000 * Math.pow(1.1, CPU4Count));

                if(bitCoins>=price){
                    bitCoins=bitCoins-price;
                    clickValue = clickValue + 100;
                    CPU4Count++;
                    price = (int) (30000 * Math.pow(1.1, CPU4Count));
                    btnCpu4.setText("CPU 4 " + price + "B");
                }
            }
        });

        btnCpu5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                price = (int) (1000000 * Math.pow(1.1, CPU5Count));

                if(bitCoins>=price){
                    bitCoins=bitCoins-price;
                    clickValue = clickValue + 3000;
                    CPU5Count++;
                    price = (int) (1000000 * Math.pow(1.1, CPU5Count));
                    btnCpu5.setText("CPU 5 " + price + "B");
                }
            }
        });

    }

    public void runBackgroundThread(){
        Runnable r = new Runnable() {
            @Override
            public void run() {

                while(bitCoins>=0){
                    bitCoins = bitCoins + idleValue;
                    setbitCoins(bitCoins);

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

    public void setbitCoins(double bitCoins){
        Bundle bundle = new Bundle();
        Message msg = new Message();

        bundle.putDouble("bitCoinsValue", bitCoins);
        msg.setData(bundle);
        h.sendMessage(msg);
    }

    public int getBitCoins(){
        return bitCoins;
    }



}
