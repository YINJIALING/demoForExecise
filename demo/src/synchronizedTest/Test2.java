package synchronizedTest;

//修饰实例方法
public class Test2 implements Runnable{

	static int i=0;
	
	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		Thread t1 = new Thread(new Test2());
		Thread t2 = new Thread(new Test2());
		t1.start();t2.start();
		t1.join();t2.join();
		System.out.println(i);
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		for(int j=0;j<10000;j++) {
			increase();
		}
	}

	private synchronized void increase() {
		// TODO Auto-generated method stub
		i++;
	}

}
