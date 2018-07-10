package synchronizedTest;

public class StringBufferSyn {
	public void add(String str1,String str2) {
		StringBuffer sb = new StringBuffer();
		sb.append(str1).append(str2);//append是同步方法，使用synchronized修饰
		//sb属于不可共享的资源，jvm自动消除内部的锁
	}

	public static void main(String[] args) {
		StringBufferSyn rmsync = new StringBufferSyn();
		for(int i=0;i<10000000;i++) {
			rmsync.add("abc","123");
		}
	}

}
