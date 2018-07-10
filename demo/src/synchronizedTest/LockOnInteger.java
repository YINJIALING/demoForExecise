package synchronizedTest;

public class LockOnInteger implements Runnable{
	private static Integer i = 0;
	static LockOnInteger instance = new LockOnInteger();

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		Thread t1 = new Thread(instance);
		Thread t2 = new Thread(instance);
		t1.start();t2.start();
		t1.join();t2.join();
		System.out.println(i);
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		for(int j=0;j<1000000;j++) {
			synchronized(instance) {
				i++;
			}
		}
	}

}
