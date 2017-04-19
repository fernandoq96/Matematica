package miPaquete;

import java.awt.Dimension;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class MatrizMath {

	private int fila, columna;
	private double[][] matriz;

	public MatrizMath(int fila, int columna) {
		this.fila = fila;
		this.columna = columna;
		this.matriz = new double[fila][columna];
	}

	public MatrizMath(String ruta) {
		File archivo = new File(ruta);
		Scanner scan;
		try {
			scan = new Scanner(archivo);
			int iaux = scan.nextInt();
			int jaux = scan.nextInt();
			matriz = new double[iaux][jaux];
			for (int i = 0; i < (iaux * jaux); i++) {
				matriz[scan.nextInt()][scan.nextInt()] = scan.nextDouble();
			}
		} catch (FileNotFoundException e) {
		}
	}

	public MatrizMath sumar(MatrizMath mat2) throws DistDimException {
		if (this.fila != mat2.fila || this.columna != mat2.fila)
			throw new DistDimException("ERROR ---> SUMA MATRIZ <> DIMENSION");

		MatrizMath matNueva = new MatrizMath(this.fila, this.columna);

		for (int i = 0; i < this.fila; i++) {
			for (int j = 0; j < this.columna; j++) {
				matNueva.matriz[i][j] = this.matriz[i][j] + mat2.matriz[i][j];
			}
		}

		return matNueva;
	}
	
	public MatrizMath restar(MatrizMath mat2) throws DistDimException {
		if (this.fila != mat2.fila || this.columna != mat2.fila)
			throw new DistDimException("ERROR ---> SUMA MATRIZ <> DIMENSION");

		MatrizMath matNueva = new MatrizMath(this.fila, this.columna);

		for (int i = 0; i < this.fila; i++) {
			for (int j = 0; j < this.columna; j++) {
				matNueva.matriz[i][j] = this.matriz[i][j] - mat2.matriz[i][j];
			}
		}

		return matNueva;
	}

	@Override
	public String toString() {
		StringBuilder cadena;
		for (int i = 0; i < this.fila; i++) {
			cadena.append("[");
			for (int j = 0; j < matriz.length; j++) {
				cadena.append(this.matriz[i][j]);
			}
		}
	}
	
	

}
