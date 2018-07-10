package 多线程;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class CallableTest implements Callable<String>{
//有返回值的
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Callable c = new CallableTest();
		FutureTask<String> oneTask =  new FutureTask<String>(c);
		
		Thread t1 = new Thread(oneTask);
		t1.start();
		try {
			System.out.println(oneTask.get());
			//get()方法是阻塞的，如果线程一直没拿到返回结果，便会一直等待
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public String call() throws Exception {
		String str = (String) Thread.currentThread().getName();
		return  str;
	}

}
