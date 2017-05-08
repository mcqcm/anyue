package com.common;

import java.util.Calendar;
import java.util.TimerTask;

import javax.servlet.ServletContext;

public class SampleTask extends TimerTask {
	
	private ServletContext context;  
    private static boolean isRunning = false;
    private MyListener workService;
   
    public SampleTask(ServletContext context){
     this.context = context; 
    }  

	@Override
	public void run() {
		System.out.println("task!");
		workService = new MyListener();
        if (!isRunning) {
                isRunning = true;
                workService.doWork();
                isRunning = false;
                //context.log("ָ������ִ�н���");
        } else{
            //context.log("��һ������ִ�л�δ����");
        }
      }
}
