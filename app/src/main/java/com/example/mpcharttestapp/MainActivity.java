package com.example.mpcharttestapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.collection.CircularArray;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    SensemoreChart sensemoreChart;

    Timer _t = new Timer();
    int i=0;
    int maxEntryCount=10000;
    int burstSize=10000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sensemoreChart=new SensemoreChart((LineChart)findViewById(R.id.chart));
        for (int k = 0; k < burstSize; k++)
            sensemoreChart.addData(new Entry((float) i * burstSize + k, (float) Math.sin((double) (k) / 50.0)*1), SensemoreChart.DATA_TYPE.ACCELEROMETER_X);
        for (int k = 0; k < burstSize; k++)
            sensemoreChart.addData(new Entry((float) i * burstSize + k, (float) Math.sin((double) (k) / 50.0)*2), SensemoreChart.DATA_TYPE.ACCELEROMETER_Y);
        for (int k = 0; k < burstSize; k++)
            sensemoreChart.addData(new Entry((float) i * burstSize + k, (float) Math.sin((double) (k) / 50.0)*3), SensemoreChart.DATA_TYPE.ACCELEROMETER_Z);
        for (int k = 0; k < burstSize; k++)
            sensemoreChart.addData(new Entry((float) i * burstSize + k, (float) Math.sin((double) (k) / 50.0)*4), SensemoreChart.DATA_TYPE.GYROSCOPE_X);
        for (int k = 0; k < burstSize; k++)
            sensemoreChart.addData(new Entry((float) i * burstSize + k, (float) Math.sin((double) (k) / 50.0)*5), SensemoreChart.DATA_TYPE.GYROSCOPE_Y);
        for (int k = 0; k < burstSize; k++)
            sensemoreChart.addData(new Entry((float) i * burstSize + k, (float) Math.sin((double) (k) / 50.0)*6), SensemoreChart.DATA_TYPE.GYROSCOPE_Z);


        sensemoreChart.updateChart();
        _t.scheduleAtFixedRate( new TimerTask() {
            @Override
            public void run() {

                i++;
                runOnUiThread(new Runnable() //run on ui thread
                {
                    public void run() {
                        //update ui
                     //   sensemoreChart.updateChart();
                    }
                });


            }
        }, 40, 40 );
    }
}
