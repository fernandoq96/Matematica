package miPaquete;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Locale;
import java.util.Scanner;

public class Lector {
	private Scanner scan;
	//LA LOGICA DE LECTURA DEBE IR EN EL CONSTRUCTOR DEL VECTOR
	public Lector (String ruta)
	{
		File archivo = new File (ruta);
		try {
			scan = new Scanner (archivo);
			scan.useLocale(Locale.ENGLISH);
		} catch (FileNotFoundException e) {
			System.out.println("ERROR LECTOR ---");
		}
	}
	
	public VectorMath leerVector()
	{
		int dim = scan.nextInt();
		double [] v1 = new double[dim];
		
		for (int i = 0; i < v1.length; i++) {
			v1[i] = scan.nextDouble();
		}
		
		return new VectorMath(dim, v1);	
	}
	
//	public void cambiarArchivo(String ruta)
//	{
//		File archivo = new File(ruta);
//		this.scan
//	}
}
