package com.carrol.thread2;

/**
 * @author carrol
 * 使用runnable接口实现多线程方式
 */
public class ThreadRunnableTest {
	public static void main(String[] args) {
		ThRun1 t1 = new ThRun1();
		ThRun2 t2 = new ThRun2();
		
		Thread td1 = new Thread(t1);
		Thread td2 = new Thread(t2);
		
		td1.start();
		td2.start();
	}
}
