package 多线程;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

class MyCallable implements Callable<Object>{
	
	private int task_num;

	public MyCallable(int task_num) {
		super();
		this.task_num = task_num;
	}

	@Override
	public Object call() throws Exception {
		// TODO Auto-generated method stub
		return task_num;
	}
	
}
public class ExecutorTest {
//线程池executorService
	private static int TASK_SIZE = 5;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub		
		Date date1 = new Date();
		//创建一个容量为5的线程池
		ExecutorService pool = Executors.newFixedThreadPool(TASK_SIZE);
		//存放多个future对象
		List<Future> list = new ArrayList<>();
		for(int i=0;i<TASK_SIZE;i++) {
			Callable c = new MyCallable(i);
			//执行任务，获取Future
			Future f = pool.submit(c);
			list.add(f);
		}
		//关闭线程池
		pool.shutdown();
		
		Date date2 = new Date();
		System.out.println("time:"+(date2.getTime()-date1.getTime()));
		
		//遍历list,获得所有Future对象的运行结果
		for(Future f:list) {
			try {
				System.out.println(f.get().toString());
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
