package tpe;

public class Main {

	public static void main(String args[]) {
		Servicios servicios = new Servicios("./TPE_Programacion3/datasets/Procesadores.csv", "./TPE_Programacion3/datasets/Tareas.csv");
		System.out.println(servicios.getTarea("T1"));
	}
}