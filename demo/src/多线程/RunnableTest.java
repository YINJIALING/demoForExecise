package 多线程;

//public class RunnableTest extends otherclass implements Runnable
public class RunnableTest implements Runnable{

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		RunnableTest r= new RunnableTest();
		Thread thread0 = new Thread(r);
		thread0.start();
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("run:"+Thread.currentThread().getName());
	}

}
