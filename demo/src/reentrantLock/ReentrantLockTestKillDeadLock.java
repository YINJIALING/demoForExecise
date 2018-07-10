package reentrantLock;

import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockTestKillDeadLock implements Runnable{

	private int num;

	public ReentrantLockTestKillDeadLock(int num) {
		super();
		this.num = num;
	}

	private ReentrantLock lock1 = new ReentrantLock();
	private ReentrantLock lock2 = new ReentrantLock();
	public static void main(String[] args) throws InterruptedException {
		ReentrantLockTestKillDeadLock deadlock1 = new ReentrantLockTestKillDeadLock(1);
		ReentrantLockTestKillDeadLock deadlock2 = new ReentrantLockTestKillDeadLock(2);
		Thread t1 = new Thread(deadlock1);
		Thread t2 = new Thread(deadlock2);
		t1.start();
		t2.start();
		Thread.sleep(1000);
		t2.interrupt();
	}

	@Override
	public void run() {
		try {
			if(num ==1) {
				lock1.lockInterruptibly();
				 //以可以响应中断的方式加锁
				Thread.sleep(500);
				lock2.lockInterruptibly();
			}else {
				lock2.lockInterruptibly();
				Thread.sleep(500);
				lock1.lockInterruptibly();
			}	
		}catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			System.out.println("lock1"+lock1.isHeldByCurrentThread()+Thread.currentThread().getName());
			System.out.println("lock2"+lock2.isHeldByCurrentThread()+Thread.currentThread().getName());
			if(lock1.isHeldByCurrentThread())//注意判断方式
				lock1.unlock();
			if(lock2.isHeldByCurrentThread())//注意判断方式
				lock2.unlock();
			System.out.println(Thread.currentThread().getName()+"\t"+Thread.currentThread().getId());
		}
/**
 * 开始时，t1,t2分别持有lock1,lock2分别请求lock2,lock1发生死锁。
 * 使用t2.interrupt();
 * 持有重入锁lock2的线程t2会响应中断，并不再继续等待lock1，
 * t2:lock1=true lock2=true 
 * 同时释放了其原本持有的lock2，这样t1获取到了lock2，正常执行完成。t2也会退出，但只是释放了资源并没有完成工作
 * t1:lock1=true lock2=true 
 * */		
	}

}
