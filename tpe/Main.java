package tpe;

public class Main {

	public static void main(String args[]) {
		Servicios servicios = new Servicios("./tpe/datasets/Procesadores.csv", "./tpe/datasets/Tareas.csv");
		//System.out.println(servicios.getTareasPrioridadIn(8,80));
		System.out.println (servicios.asignarTareasProcesadores(3));
	}
}