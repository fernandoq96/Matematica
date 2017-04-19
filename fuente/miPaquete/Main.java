package miPaquete;

import java.io.File;
import java.util.Vector;

public class Main {

	public static void main(String[] args) {
			System.out.println("hOLA");
			Lector yoLeo1 = new Lector("v1.in");
			Lector yoLeo2 = new Lector("v2.in");
			Lector yoLeo3 = new Lector("v3.in");
			Lector yoLeo4 = new Lector("v4.in");
			
			VectorMath vector1 = yoLeo1.leerVector();
			VectorMath vector2 = yoLeo2.leerVector();
			VectorMath vector3 = yoLeo3.leerVector();
			VectorMath vector4 = yoLeo4.leerVector();
			
			VectorMath resultado = vector1.sumar(vector2);
			System.out.println("V1 " + vector1);
			System.out.println("V2 " +vector2);
			System.out.println(vector1.sumar(vector2));
//			System.out.println(vector1.restar(vector3));
			
			System.out.println(vector1.producto(vector2));
			
			MatrizMath matriz = new MatrizMath ("matriz.in");
			System.out.println(matriz);
	}

}
