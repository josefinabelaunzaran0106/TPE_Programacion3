package tpe;

public class Main {
	public static void main(String args[]) {
		Servicios servicios = new Servicios("./tpe/datasets/Procesadores.csv", "./tpe/datasets/Tareas.csv");
		System.out.println(servicios.getTarea("T2"));
		//Para Servicios Greedy y Backtracking se recomienda utilizar toString simplificado de la clase tarea
		System.out.println (" ");
		System.out.println ("Solucion Greedy: ");
		if((servicios.greedyAsignarTareasProcesadores(30)).getTiempoSolucion()==null){
			System.out.println ("No existe solución para Greedy");
		}else{
			System.out.println (servicios.greedyAsignarTareasProcesadores(30));
			System.out.println ("Metrica: ["+ servicios.getContadorGreedy()+"]");
		}
		System.out.println ("Solucion Backtracking: ");
		if((servicios.backtrackingAsignarTareasProcesadores(30)).getTiempoSolucion()==null){
			System.out.println ("No existe solución para Backtracking");
		}else{
		System.out.println (servicios.backtrackingAsignarTareasProcesadores(30));
		System.out.println ("Metrica: ["+ servicios.getContadorBacktracking()+"]");
		}
	}
}