package miPaquete;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Locale;
import java.util.Scanner;

public class MatrizMath implements Cloneable {

	private int fila, columna;
	private double[][] matriz;

	// CONSTRUCTOR SIN LECTURA DE ARCHIVO, PARAMETRO ---> LAS DIMENSIONES DE LA
	// MATRIZ
	public MatrizMath(int fila, int columna) {
		this.fila = fila;
		this.columna = columna;
		this.matriz = new double[fila][columna];
	}

	/*
	 * CONSTRUCTOR LEYENDO DE ARHCHIVO, PARAMETRO ---> EL NOMBRE DEL ARCHIVO.IN
	 * public MatrizMath(String ruta) { File archivo = new File(ruta); Scanner
	 * scan; try { scan = new Scanner(archivo); scan.useLocale(Locale.ENGLISH);
	 * this.fila = scan.nextInt(); this.columna = scan.nextInt(); matriz = new
	 * double[this.fila][this.columna]; for (int i = 0; i < (this.fila *
	 * this.columna); i++) { matriz[scan.nextInt()][scan.nextInt()] =
	 * scan.nextDouble(); } } catch (FileNotFoundException e) { } }
	 */

	public MatrizMath(String ruta) {
		Scanner scan;
		try {
			scan = new Scanner(new File(ruta));
			scan.useLocale(Locale.ENGLISH);
			this.fila = this.columna = scan.nextInt();
			this.matriz = new double[this.fila][this.columna];
			for (int i = 0; i < this.fila * this.columna; i++) {
				this.matriz[scan.nextInt()][scan.nextInt()] = scan.nextDouble();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}

	public MatrizMath(double[][] mat) {
		this.fila = mat.length;
		this.columna = mat[0].length;
		this.matriz = mat.clone();
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

	public void matIdentidad() {
		for (int i = 0; i < this.fila; i++) {
			this.matriz[i][i] = 1;
		}
	}

	@Override
	public String toString() {
		StringBuilder cadena = new StringBuilder();
		for (int i = 0; i < this.fila; i++) {
			cadena.append("|");
			for (int j = 0; j < this.columna; j++) {
				cadena.append(this.matriz[i][j] + " ");
			}
			cadena.deleteCharAt(cadena.length() - 1);
			cadena.append("|\n");
		}
		return cadena.toString();
	}

	// si son multiplico tampoco puede hacer inversa

	public MatrizMath inversaGauss() throws MatSinInversa, CloneNotSupportedException {

		MatrizMath matAuxiliar = (MatrizMath) this.clone();

		MatrizMath matInversa = new MatrizMath(matAuxiliar.fila, matAuxiliar.columna);
		matInversa.matIdentidad();
		double pivote, aux;
		for (int z = 0; z < matAuxiliar.fila - 1; z++) {

			if ((pivote = matAuxiliar.matriz[z][z]) != 0) {
				for (int i = z + 1; i < matAuxiliar.fila; i++) {
					aux = matAuxiliar.matriz[i][z];

					if (aux != 0) {
						for (int j = 0; j < matAuxiliar.columna; j++) {
							matAuxiliar.matriz[i][j] = (matAuxiliar.matriz[i][j] * pivote
									- matAuxiliar.matriz[z][j] * aux);
							matInversa.matriz[i][j] = (matInversa.matriz[i][j] * pivote
									- matInversa.matriz[z][j] * aux);
						}
					}
				}
			}
		}

		for (int i = 0; i < matAuxiliar.fila; i++) {
			if (matAuxiliar.matriz[i][i] != 1) {
				for (int j = 0; j < matAuxiliar.columna; j++) {
					matInversa.matriz[i][j] /= matAuxiliar.matriz[i][i];
				}
				matAuxiliar.matriz[i][i] /= matAuxiliar.matriz[i][i];
			}
		}

		for (int z = matAuxiliar.fila - 1; z >= 0; z--) {
			pivote = matAuxiliar.matriz[z][z];
			if (pivote != 0) {
				for (int i = 0; i < z; i++) {
					aux = matAuxiliar.matriz[i][z];
					if (aux != 0) {
						for (int j = 0; j < matAuxiliar.columna; j++) {
							matAuxiliar.matriz[i][j] = (matAuxiliar.matriz[i][j] * pivote
									- matAuxiliar.matriz[z][j] * aux);
							matInversa.matriz[i][j] = (matInversa.matriz[i][j] * pivote
									- matInversa.matriz[z][j] * aux);
						}
					}
				}
			}
		}

		return matInversa;

	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}

}
