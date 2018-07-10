package synchronizedTest;

//修饰代码块
public class Test4 implements Runnable{
	static Test4 instance = new Test4();

	static int i=0;
	
	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		Thread t1 = new Thread(new Test4());
		Thread t2 = new Thread(new Test4());
		t1.start();t2.start();
		t1.join();t2.join();
		System.out.println(i);
	}

	@Override
	public void run() {
		synchronized(instance) {
			for(int j=0;j<10000;j++) {
				i++;
			}
		}
	}

}
