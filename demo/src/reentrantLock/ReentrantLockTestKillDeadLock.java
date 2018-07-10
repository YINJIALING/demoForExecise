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
				 //�Կ�����Ӧ�жϵķ�ʽ����
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
			if(lock1.isHeldByCurrentThread())//ע���жϷ�ʽ
				lock1.unlock();
			if(lock2.isHeldByCurrentThread())//ע���жϷ�ʽ
				lock2.unlock();
			System.out.println(Thread.currentThread().getName()+"\t"+Thread.currentThread().getId());
		}
/**
 * ��ʼʱ��t1,t2�ֱ����lock1,lock2�ֱ�����lock2,lock1����������
 * ʹ��t2.interrupt();
 * ����������lock2���߳�t2����Ӧ�жϣ������ټ����ȴ�lock1��
 * t2:lock1=true lock2=true 
 * ͬʱ�ͷ�����ԭ�����е�lock2������t1��ȡ����lock2������ִ����ɡ�t2Ҳ���˳�����ֻ���ͷ�����Դ��û����ɹ���
 * t1:lock1=true lock2=true 
 * */		
	}

}
