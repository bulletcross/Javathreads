import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

class parallel_solver extends Thread {
		int thread_id;
		data myobj;
		int total_thrads;
		int start_index;
		int end_index;
		CyclicBarrier barrier;
		parallel_solver(int t,data obj,int tt,CyclicBarrier cb){
			this.thread_id = t;
			this.myobj = obj;
			this.total_thrads = tt;
			this.start_index = thread_id*(myobj.matrix_size/total_thrads);
			this.end_index = (thread_id+1)*(myobj.matrix_size/total_thrads);
			this.barrier = cb;
		}
		public void run(){
			while(myobj.signal){
				for(int i=start_index;i<end_index;i++){
					myobj.x_0[i] = myobj.x_n[i];
					myobj.x_n[i] = 0;
				}
				for(int i=start_index;i<end_index;i++){
					for(int j=0;j<myobj.matrix_size;j++){
						if(i!=j){
							myobj.x_n[i] = myobj.x_0[j]*myobj.matrix[i][j];
						}
					}
					myobj.x_n[i] = myobj.b[i] - myobj.x_n[i];
					myobj.x_n[i] = myobj.x_n[i]*(1/myobj.matrix[i][i]);
				}
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
		}
	}
