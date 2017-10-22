package com.carrol.thread2;

public class ThRun2 implements Runnable{

	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println(2);
		try {
			throw new Exception("1111122222");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
