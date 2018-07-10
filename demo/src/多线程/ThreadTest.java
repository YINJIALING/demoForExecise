package 多线程;

public class ThreadTest extends Thread{
	
	public void run() {
		System.out.println("run:"+Thread.currentThread().getName());
	}
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Thread thread1 = new ThreadTest();
		Thread thread2 = new ThreadTest();
		thread1.start();
		thread2.start();
	}

}
