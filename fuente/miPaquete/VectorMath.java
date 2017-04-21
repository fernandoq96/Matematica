package miPaquete;

import java.util.Arrays;

public class VectorMath {
	// LA LOGICA DE LECTURA DEBE IR EN EL CONSTRUCTOR DEL VECTOR
	private int dim;
	private double[] coor;

	public VectorMath(double[] coor) {
		this.dim = coor.length;
		this.coor = coor;
	}

	public VectorMath(int dim) {
		this.coor = new double[dim];
		this.dim = dim;

	}

	public VectorMath restar(VectorMath vector2) throws DistDimException {
		if (this.dim != vector2.dim)
			throw new DistDimException("ERROR--> Vectores de distinta dimension RESTA");
		VectorMath vecNuevo = new VectorMath(this.dim);
		for (int i = 0; i < this.coor.length; i++) {
			vecNuevo.coor[i] = this.coor[i] - vector2.coor[i];
		}

		return vecNuevo;

	}

	public VectorMath sumar(VectorMath vector2) throws DistDimException {
		if (this.dim != vector2.dim)
			throw new DistDimException("ERROR--> Vectores de distinta dimension SUMA");
		VectorMath vecNuevo = new VectorMath(this.dim);
		for (int i = 0; i < this.coor.length; i++) {
			vecNuevo.coor[i] = this.coor[i] + vector2.coor[i];
		}

		return vecNuevo;

	}

	@Override
	public String toString() {
		return "B = "+ Arrays.toString(coor);
	}

	// @Override
	// public String toString() {
	// StringBuilder cadena = new StringBuilder();
	// cadena.append("(");
	// for (int i = 0; i < this.dim; i++) {
	// cadena.append(this.coor[i]);
	// cadena.append(",");
	// }
	// cadena.append(")");
	// return cadena.toString();
	// }

	public double producto(VectorMath vector2) throws DistDimException {
		if (this.dim != vector2.dim)
			throw new DistDimException("ERROR--> Vectores de distinta dimension PROD ESCALAR");

		double acum = 0;
		for (int i = 0; i < coor.length; i++) {
			acum += this.coor[i] * vector2.coor[i];
		}

		return acum;
	}

	public VectorMath producto(double real) {
		VectorMath nuevoVector = new VectorMath(dim);
		for (int i = 0; i < dim; i++) {
			nuevoVector.coor[i] = this.coor[i] * real;
		}
		return nuevoVector;
	}

	public double normaUno() {
		double acumulador = 0;
		for (int i = 0; i < this.dim; i++) {
			acumulador += this.coor[i];
		}
		return acumulador;
	}

	public double normaDos() {
		double acumulador = 0;
		for (int i = 0; i < this.dim; i++) {
			acumulador += Math.pow(this.coor[i], 2);
		}
		return Math.sqrt(acumulador);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(coor);
		result = prime * result + dim;
		return result;
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		return super.clone();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		VectorMath other = (VectorMath) obj;
		if (!Arrays.equals(coor, other.coor))
			return false;
		if (dim != other.dim)
			return false;
		return true;
	}

}
