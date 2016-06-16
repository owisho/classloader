package per.owisho.learn.outofmemoryerror;

/**
 * VM Args: -Xss2M(这时候不妨设置大些)
 * 没有成功获得内存溢出异常：Exception in thread "main" java.lang.OutOfMemoryError: unable to create new native thread 
 * @author owisho
 *
 */
public class JavaVMStackOOM {

	private void dontStop(){
		while(true){
			
		}
	}
	
	public void stackLeakByThread(){
		while(true){
			Thread thread = new Thread(new Runnable(){
				@Override
				public void run() {
					dontStop();
				}
			});
			thread.start();
		}
	}
	
	public static void main(String[] args) throws Throwable{
		JavaVMStackOOM oom = new JavaVMStackOOM();
		oom.stackLeakByThread();
	}
	
}
