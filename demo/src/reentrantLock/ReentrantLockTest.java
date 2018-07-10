package reentrantLock;

import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockTest implements Runnable{

	private static ReentrantLock lock = new ReentrantLock();
	private static int i = 0;
	public static void main(String[] args) throws InterruptedException {
		ReentrantLockTest test = new ReentrantLockTest();
		Thread t1 = new Thread(test);
		Thread t2 = new Thread(test);
		t1.start();t2.start();
		t1.join();t2.join();//main会等待t1,t2都运行结束
		System.out.println("i:"+i);
	}

	@Override
	public void run() {
		for(int j=0;j<1000;j++) {
			lock.lock();
			try {
				i++;
			}finally{
				lock.unlock();
			}		
		}
	}

}
