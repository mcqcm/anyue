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
                //context.log("指定任务执行结束");
        } else{
            //context.log("上一次任务执行还未结束");
        }
      }
}
