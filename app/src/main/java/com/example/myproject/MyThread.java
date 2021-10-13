package com.example.myproject;

import android.os.Handler;
import android.util.Log;

public class MyThread implements Runnable{
    private int time=30;
    private MyThreadInterface myThreadInterface;
    private Boolean running=false;

    public MyThread(int time,MyThreadInterface myThreadInterface){
        this.time = time;
        this.myThreadInterface = myThreadInterface;
        this.running = true;
    }

    @Override
    public void run() {
        int i = 0;
        for(i = 0; i<time; i++){
            if(running==false){
                break;
            }
            try {
                Log.d("time",""+i );
                Thread.sleep(1000);
                myThreadInterface.setTimer(29- i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        myThreadInterface.time();


    }
    public void setRunning(Boolean running) {
        this.running = running;
    }
}
