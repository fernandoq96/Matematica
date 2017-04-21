package miPaquete;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Locale;
import java.util.Scanner;

public class SistemaEcuacion implements Cloneable {
	private MatrizMath matrizSist;
	private VectorMath vectorSist;

	public SistemaEcuacion(String ruta) {
		Scanner scan;
		
		try {
			scan = new Scanner(new File(ruta));
			scan.useLocale(Locale.ENGLISH);
			int dim = scan.nextInt(), i;
			double[][] a = new double[dim][dim];
			
			for (i = 0; i < dim*dim; i++) {
					a[scan.nextInt()][scan.nextInt()] = scan.nextDouble();
			}
			this.matrizSist = new MatrizMath(a);
			double[] b = new double[dim];
			
			for (i = 0; i < dim; i++) {
				b[i] = scan.nextDouble();
			}
			this.vectorSist = new VectorMath(b);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String toString() {
		return this.matrizSist.toString() +" "+this.vectorSist.toString();
	}
	
	
	public MatrizMath inversaGauss() throws MatSinInversa, CloneNotSupportedException
	{
		return (this.matrizSist.inversaGauss());
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
	
	
}
