package com.carrol.thread;

class Demo2 implements Runnable {

    Thread t;

    //�չ��캯��
    Demo2() {
        t = new Thread(this, "�����߳�");
        System.out.println("���߳�" + t);
        t.start();
    }

    public void run() {
        try {
            for (int a = 5; a > 0; a--) {
                System.out.println("���߳�" + a);
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            System.out.println("�쳣");
        }
        System.out.println("�˳����߳�");
    }
}

/**
 * ����
 */
public class Th_runnable {
    public static void main(String args[]) {
        new Demo2();//����һ�����߳�
        try {
            for (int i = 5; i > 0; i--) {
                System.out.println("���߳�:" + i);
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            System.out.println("���߳��쳣");
        }
        //���߳��˳�
        System.out.println("���߳��˳�");

    }
}
