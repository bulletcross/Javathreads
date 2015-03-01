import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;


public class JacobiMain {

	CyclicBarrier barrier;
	static double tolerance;
	data mydataobject;
	static FileWriter out;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			out = new FileWriter("output.txt");
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		double startTime;
		double endTime;
		double duration1;
		double duration2;
		tolerance = Math.pow(10,-8);
		
		
		data mydataobject = new data(20);
		serial_solver serial = new serial_solver(mydataobject,tolerance);
		startTime = System.currentTimeMillis();
		serial.solve();
		endTime = System.currentTimeMillis();
		duration1 = (double)(endTime - startTime)/1000;
		//System.out.println((double)duration/1000);
		//mydataobject.print_result();
		
		mydataobject.reset();
		int noThreads = Runtime.getRuntime().availableProcessors();
		
		CyclicBarrier barrier =
				  new CyclicBarrier(noThreads + 1,new norm_calc(mydataobject,tolerance));
		startTime = System.currentTimeMillis();
		for (int i = 0; i < noThreads; i++) {
			parallel_solver thr = new parallel_solver(i,mydataobject,noThreads,barrier);
			  thr.start();
			}
		while(mydataobject.signal){
			try {
				barrier.await();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (BrokenBarrierException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		endTime = System.currentTimeMillis();
		//mydataobject.print_result();
		duration2 = (double)(endTime - startTime)/1000;
		//System.out.println((double)duration/1000);
		try {
			out.write(duration1+ " "+duration2+"\n");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}



