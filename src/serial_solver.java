
public class serial_solver {
	data myobj;
	double tol;
	serial_solver(data obj,double tolerance){
		this.myobj = obj;
		this.tol = tolerance;
	}
	public void solve(){
		while(norm()>tol){
			for(int i=0;i<myobj.matrix_size;i++){
				myobj.x_0[i] = myobj.x_n[i];
				myobj.x_n[i] = 0;
			}
			for(int i=0;i<myobj.matrix_size;i++){
				for(int j=0;j<myobj.matrix_size;j++){
					if(i!=j){
						myobj.x_n[i] = myobj.x_0[j]*myobj.matrix[i][j];
					}
				}
				myobj.x_n[i] = myobj.b[i] - myobj.x_n[i];
				myobj.x_n[i] = myobj.x_n[i]*(1/myobj.matrix[i][i]);
			}
		}
	}
	public double norm(){
		double result = 0;
		for(int i=0;i<myobj.matrix_size;i++){
			result = result + (myobj.x_n[i] - myobj.x_0[i])*(myobj.x_n[i] - myobj.x_0[i]);
		}
		result = Math.sqrt(result);
		return result;
	}
}
