
public class norm_calc implements Runnable{

	data myobj;
	double tolerance;
	norm_calc(data myobj,double tol){
		this.myobj = myobj;
		this.tolerance = tol;
	}
	@Override
	public void run() {
		double result = 0;
		for(int i=0;i<myobj.matrix_size;i++){
			result = result + (myobj.x_n[i] - myobj.x_0[i])*(myobj.x_n[i] - myobj.x_0[i]);
		}
		result = Math.sqrt(result);
		if(result<tolerance){
			myobj.signal = false;
		}
		
	}

}
