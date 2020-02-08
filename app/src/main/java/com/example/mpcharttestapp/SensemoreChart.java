package com.example.mpcharttestapp;

import android.graphics.Color;
import android.util.Log;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.nio.Buffer;
import java.util.ArrayList;
import java.util.LinkedList;

public class SensemoreChart {
    LineChart lineChart;
    LineData lineData;
    LinkedList<Entry> entryLinkedList;
    int maxDrawCount=1000;
    int maxDataCount=10000;
    public int getMaxDrawCount() {
        return maxDrawCount;
    }

    public void setMaxDrawCount(int maxDrawCount) {
        this.maxDrawCount = maxDrawCount;
    }

    enum DATA_TYPE{
        ACCELEROMETER_X,
        ACCELEROMETER_Y,
        ACCELEROMETER_Z,
        GYROSCOPE_X,
        GYROSCOPE_Y,
        GYROSCOPE_Z,
    }
    String []labels={"Accelerometer X","Accelerometer Y","Accelerometer Z","Gyroscope X","Gyroscope Y","Gyroscope Z"};
    int []colors={Color.GREEN,Color.RED,Color.BLUE,Color.CYAN,Color.MAGENTA,Color.YELLOW};
    void setData(float []x,float[]y,DATA_TYPE data_type){
        lineData.getDataSetByIndex(data_type.ordinal()).clear();
        if(x.length!=y.length)return;
        for(int i=0;i<x.length;i++)
            lineData.addEntry(new Entry(x[i],y[i]), data_type.ordinal());
        lineData.notifyDataChanged();
    }
    void addData(Entry entry,DATA_TYPE data_type){
        lineData.addEntry(entry, data_type.ordinal());
        lineData.notifyDataChanged();
    }
    void addData(ArrayList<Entry> entries, DATA_TYPE data_type){
        for (Entry entry:entries) {
            lineData.addEntry(entry, data_type.ordinal());
        }
        lineData.notifyDataChanged();
    }
    void addData(float x,float y, DATA_TYPE data_type){
        lineData.addEntry(new Entry(x,y), data_type.ordinal());
        lineData.notifyDataChanged();
    }
    void addData(float []x,float []y, DATA_TYPE data_type){
        if(x.length!=y.length)return;
        for(int i=0;i<x.length;i++)
        lineData.addEntry(new Entry(x[i],y[i]), data_type.ordinal());
        lineData.notifyDataChanged();
    }
    void addData(int []x,int []y, DATA_TYPE data_type){
        if(x.length!=y.length)return;
        for(int i=0;i<x.length;i++)
            lineData.addEntry(new Entry(x[i],y[i]), data_type.ordinal());
        lineData.notifyDataChanged();
    }
    void updateChart(){
        lineChart.notifyDataSetChanged();
        lineChart.moveViewToX(lineData.getEntryCount());
        lineChart.setVisibleXRangeMaximum(maxDrawCount);
    }
    SensemoreChart(LineChart lineChart){
        this.lineChart=lineChart;
        lineData = new LineData();
        lineData.addDataSet(createDataSet(DATA_TYPE.ACCELEROMETER_X));
        lineData.addDataSet(createDataSet(DATA_TYPE.ACCELEROMETER_Y));
        lineData.addDataSet(createDataSet(DATA_TYPE.ACCELEROMETER_Z));
        lineData.addDataSet(createDataSet(DATA_TYPE.GYROSCOPE_X));
        lineData.addDataSet(createDataSet(DATA_TYPE.GYROSCOPE_Y));
        lineData.addDataSet(createDataSet(DATA_TYPE.GYROSCOPE_Z));

        lineChart.setData(lineData);
        lineChart.invalidate();
    }
    private LineDataSet createDataSet(DATA_TYPE data_type){
        LineDataSet lineDataSet=new LineDataSet(null, labels[data_type.ordinal()]);
        lineDataSet.setAxisDependency(YAxis.AxisDependency.LEFT);
        lineDataSet.setLineWidth(1);
        lineDataSet.setColor(colors[data_type.ordinal()]);
        lineDataSet.setHighlightEnabled(false);
        lineDataSet.setDrawValues(false);
        lineDataSet.setDrawCircles(false);
        lineDataSet.setDrawIcons(false);
        lineDataSet.setMode(LineDataSet.Mode.LINEAR);
        return lineDataSet;
    }
}
