package miPaquete;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.RoundingMode;
import java.text.DecimalFormat;
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
		this.matriz = mat;
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

	// Si son multiplico tampoco puede hacer inversa, se simplifican
	// FILAS || COLUMNAS DE 0 ---> No hay inversa
	//
	// Version alpha
	/*
	 * public MatrizMath inversaGauss() throws MatSinInversa,
	 * CloneNotSupportedException {
	 * 
	 * MatrizMath matAuxiliar = new MatrizMath(this.matriz);
	 * 
	 * MatrizMath matInversa = new MatrizMath(matAuxiliar.fila,
	 * matAuxiliar.columna); matInversa.matIdentidad(); double pivote, aux; for
	 * (int z = 0; z < matAuxiliar.fila - 1; z++) {
	 * 
	 * if ((pivote = matAuxiliar.matriz[z][z]) != 0) { for (int i = z + 1; i <
	 * matAuxiliar.fila; i++) { aux = matAuxiliar.matriz[i][z];
	 * 
	 * if (aux != 0) { for (int j = 0; j < matAuxiliar.columna; j++) {
	 * matAuxiliar.matriz[i][j] = (matAuxiliar.matriz[i][j] * pivote -
	 * matAuxiliar.matriz[z][j] * aux); matInversa.matriz[i][j] =
	 * (matInversa.matriz[i][j] * pivote - matInversa.matriz[z][j] * aux); } } }
	 * } } System.out.println(matInversa);
	 * 
	 * for (int i = 0; i < matAuxiliar.fila; i++) { if (matAuxiliar.matriz[i][i]
	 * != 1) { for (int j = 0; j < matAuxiliar.columna; j++) {
	 * matInversa.matriz[i][j] /= matAuxiliar.matriz[i][i]; }
	 * matAuxiliar.matriz[i][i] /= matAuxiliar.matriz[i][i]; } }
	 * 
	 * for (int z = matAuxiliar.fila - 1; z >= 0; z--) { pivote =
	 * matAuxiliar.matriz[z][z]; if (pivote != 0) { for (int i = 0; i < z; i++)
	 * { aux = matAuxiliar.matriz[i][z]; if (aux != 0) { for (int j = 0; j <
	 * matAuxiliar.columna; j++) { matAuxiliar.matriz[i][j] =
	 * (matAuxiliar.matriz[i][j] * pivote - matAuxiliar.matriz[z][j] * aux);
	 * matInversa.matriz[i][j] = (matInversa.matriz[i][j] * pivote -
	 * matInversa.matriz[z][j] * aux); } } } } }
	 * 
	 * return matInversa;
	 * 
	 * }
	 */

	// Version beta
	public MatrizMath inversaGauss() {
		if(this.fila != this.columna)
			throw new MatSinInversa("Inversa solo de matrices cuadradas");
		
		double pivote, auxiliar;
		MatrizMath matAuxiliar = new MatrizMath(this.clonar());
		MatrizMath matInversa = new MatrizMath(this.fila, this.columna);
		matInversa.matIdentidad();

		for (int z = 0; z < matAuxiliar.fila - 1; z++) {
			matAuxiliar.ordDesdeI(z, matInversa);
			pivote = matAuxiliar.matriz[z][z];
			for (int i = z + 1; i < matAuxiliar.fila; i++) {
				auxiliar = matAuxiliar.matriz[i][z];
				if (auxiliar != 0) {
					for (int j = 0; j < matAuxiliar.columna; j++) {
						matAuxiliar.matriz[i][j] = matAuxiliar.matriz[i][j] * pivote
								- matAuxiliar.matriz[z][j] * auxiliar;
						matInversa.matriz[i][j] = matInversa.matriz[i][j] * pivote - matInversa.matriz[z][j] * auxiliar;
					}
				}
			}
		}
		//Una vez triangulada la matriz pregunto si su det es 0 para no continuar
		if (matAuxiliar.detIgual0()) {
			throw new NoInversaException("El determinante es 0");
		}

		for (int z = matAuxiliar.fila - 1; z > 0; z--) {
			pivote = matAuxiliar.matriz[z][z];
			for (int i = z - 1; i >= 0; i--) {
				auxiliar = matAuxiliar.matriz[i][z];
				if (auxiliar != 0) {
					for (int j = 0; j < matAuxiliar.columna; j++) {
						matAuxiliar.matriz[i][j] = matAuxiliar.matriz[i][j] * pivote
								- matAuxiliar.matriz[z][j] * auxiliar;
						matInversa.matriz[i][j] = matInversa.matriz[i][j] * pivote - matInversa.matriz[z][j] * auxiliar;
					}
					if ((auxiliar = matAuxiliar.matriz[i][i]) != 1) {
						for (int j = 0; j < matAuxiliar.columna; j++) {
							matInversa.matriz[i][j] /= auxiliar;
							matAuxiliar.matriz[i][j] /= auxiliar;
						}
					}
				}
			}
		}

		// ESTO ES MEDIO RANCIO PERO NOSE COMO SEA OPTIMO Y PODER DIVIDRLO 
		if ((auxiliar = matAuxiliar.matriz[this.fila - 1][this.fila - 1]) != 1) {
			for (int j = 0; j < matAuxiliar.columna; j++) {
				matInversa.matriz[this.fila - 1][j] /= auxiliar;
				matAuxiliar.matriz[this.fila - 1][j] /= auxiliar;
			}
		}

		// for (int i = 0; i < matAuxiliar.fila; i++) {
		// if ((auxiliar = matAuxiliar.matriz[i][i]) != 1) {
		// for (int j = 0; j < matAuxiliar.columna; j++) {
		// matInversa.matriz[i][j] /= auxiliar;
		// matAuxiliar.matriz[i][j] /= auxiliar;
		// }
		// }
		//
		// }

		return matInversa;
	}

//	public void ordTrianInf(int inicio, MatrizMath mat2) {
//		int i = inicio, j = 0, fila;
//		while (j < this.columna - 1) {
//			fila = i;
//			while (i < this.fila) {
//				if (this.matriz[fila][j] == 0 && this.matriz[i][j] != 0) {
//					this.mover(fila, i);
//					mat2.mover(fila, i);
//					fila++;
//				}
//				i++;
//			}
//			j++;
//			i = j;
//		}
//	}
	
	/*Ordena desde la fila indicada en inicio */
	public void ordDesdeI(int inicio, MatrizMath mat2) {
		int i = inicio + 1, fila = inicio;
		while (i < this.fila) {
			if (this.matriz[fila][inicio] == 0 && this.matriz[i][inicio] != 0) {
				this.mover(fila, i);
				mat2.mover(fila, i);
				fila++;
			}
			i++;
		}
	}

	public void mover(int origen, int destino) throws DistDimException {
		if (origen < 0 || destino >= this.fila)
			throw new DistDimException("SE PASO DE LAS FILAS DE LA MATRIZ");
		double[] aux = new double[this.fila];
		aux = this.matriz[origen];
		this.matriz[origen] = this.matriz[destino];
		this.matriz[destino] = aux;

	}

	@Override
	public String toString() {
		DecimalFormat df = new DecimalFormat("##.##");
		df.setRoundingMode(RoundingMode.DOWN);
		StringBuilder cadena = new StringBuilder();
		for (int i = 0; i < this.fila; i++) {
			cadena.append("|");
			for (int j = 0; j < this.columna; j++) {
				cadena.append(df.format(this.matriz[i][j]) + " ");
			}
			cadena.deleteCharAt(cadena.length() - 1);
			cadena.append("|\n");
		}
		return cadena.toString();
	}
	
	/* Una vez triangulada la matriz pregunta si el det es 0*/
	private boolean detIgual0() {
		int i = 0;
		while (i < this.fila && this.matriz[i][i] != 0) {
			i++;
		}
		
		if(i == this.fila- 1)
			return true;
		
		return false;
	}

	public double[][] clonar() {
		double[][] mat = new double[this.fila][this.columna];
		for (int i = 0; i < matriz.length; i++) {
			for (int j = 0; j < matriz.length; j++) {
				mat[i][j] = this.matriz[i][j];
			}
		}
		return mat;
	}
}
