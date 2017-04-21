package miPaquete;

public class Main {

	public static void main(String[] args) throws CloneNotSupportedException {

		MatrizMath mat = new MatrizMath("matriz.in");
		System.out.println(mat);
		System.out.println(mat.inversaGauss());
		System.out.println(mat);
//		System.out.println(mat);
//		System.out.println(mat.inversaGauss());
		
			
			
	}

}
