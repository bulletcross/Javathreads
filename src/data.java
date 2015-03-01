import java.util.Random;


public class data {
	int matrix_size;
	double[][] matrix;
	double[] b;
	double[] x_n;
	double[] x_0;
	boolean signal;
	
	data(int n){
		this.matrix_size = n;
		matrix = new double[matrix_size][matrix_size];
		b = new double[matrix_size];
		x_0 = new double[matrix_size];
		x_n = new double[matrix_size];
		signal = true;
		fill_data();
		reset();
	}
	
	public void fill_data(){
		Random r = new Random();
		for(int i=0;i<matrix_size;i++){
			for(int j=0;j<matrix_size;j++){
				matrix[i][j] = (r.nextDouble()*10)+1;
			}
			b[i] = r.nextDouble()*10+1;
		}
	}
	public void reset(){
		for(int i=0;i<matrix_size;i++){
			x_0[i] = 0;
			x_n[i] = b[i];
		}
		signal = true;
	}
	
	public void print_result(){
		for(int i=0;i<matrix_size;i++){
			System.out.println(x_n[i]);
		}
	}
	
	
}
