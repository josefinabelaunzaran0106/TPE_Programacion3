package tpe;

public class Main {
	public static void main(String args[]) {
		Servicios servicios = new Servicios("./tpe/datasets/Procesadores.csv", "./tpe/datasets/Tareas.csv");
		System.out.println(servicios.servicio1("T2"));
		//Para Servicios Greedy y Backtracking se recomienda utilizar toString simplificado de la clase tarea
		System.out.println (" ");
		System.out.println ("Solucion Greedy: ");
		Solucion solucionGreedy= servicios.greedyAsignarTareasProcesadores(10);
		if(solucionGreedy.getTiempoSolucion()==null){
			System.out.println ("No existe solución para Greedy");
		}else{
			System.out.println (solucionGreedy);
			System.out.println ("Metrica: ["+ servicios.getContadorGreedy()+"]");
		}
		System.out.println ("Solucion Backtracking: ");
		Solucion solucionBacktracking= servicios.backtrackingAsignarTareasProcesadores(10);
		if(solucionBacktracking.getTiempoSolucion()==null){
			System.out.println ("No existe solución para Backtracking");
		}else{
		System.out.println (solucionBacktracking);
		System.out.println ("Metrica: ["+ servicios.getContadorBacktracking()+"]");
		}
	}
}