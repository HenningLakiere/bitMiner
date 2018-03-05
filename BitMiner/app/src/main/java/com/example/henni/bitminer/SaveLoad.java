package com.example.henni.bitminer;



import android.content.Context;
import android.util.Log;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * Created by Henni on 01/03/2018.
 */

public class SaveLoad {

    private OutputStreamWriter outputStreamWriter;
    Integer[] readStats;
    String writeStats;



    public void newFile(Context context){
        try {
            File file = context.getFileStreamPath("stats.txt");
            if(file == null || !file.exists()){
                outputStreamWriter = new OutputStreamWriter(context.openFileOutput("stats.txt", Context.MODE_PRIVATE));
                outputStreamWriter.write("0,0,0,1,0,0,0,0,0,0,0,0,0,0,0");
                outputStreamWriter.close();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }

    }

    public void writeToFile(Integer[] writeStats, Context context) {

        try {
            outputStreamWriter = new OutputStreamWriter(context.openFileOutput("stats.txt", Context.MODE_PRIVATE));

            bundleData(writeStats);

            outputStreamWriter.write(this.writeStats);
            outputStreamWriter.close();
        }
        catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }

    public Integer[] readFromFile(Context context) {

        String statsFromFile = "";
        readStats = new Integer[15];

        try {
            InputStream inputStream = context.openFileInput("stats.txt");

            if ( inputStream != null ) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String receiveString = "";
                StringBuilder stringBuilder = new StringBuilder();

                while ( (receiveString = bufferedReader.readLine()) != null ) {
                    stringBuilder.append(receiveString);
                }

                inputStream.close();
                statsFromFile = stringBuilder.toString();
            }
        }
        catch (FileNotFoundException e) {
            Log.e("login activity", "File not found: " + e.toString());
        } catch (IOException e) {
            Log.e("login activity", "Can not read file: " + e.toString());
        }

        unbundleStats(statsFromFile);

        return readStats;
    }

    public void unbundleStats(String statsFromFile){
        String[] parts = statsFromFile.split(",");
        int i = 0;

        for(String value:parts){
            readStats[i] = Integer.valueOf(value);
            i++;
        }
    }

    public void bundleData(Integer[] statsFromGame){
        writeStats = "";

        for (Integer value:statsFromGame){
            writeStats = writeStats + String.valueOf(value) + ",";
        }
    }


}
