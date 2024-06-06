package tpe;

public class Main {

	public static void main(String args[]) {
		Servicios servicios = new Servicios("./tpe/datasets/Procesadores.csv", "./tpe/datasets/Tareas.csv");
		System.out.print(servicios.getTarea("T2"));
		System.out.print (" Critica: " +servicios.getTarea("T2").getCritica());
		System.out.print (" Nombre: " +servicios.getTarea("T2").getNombre());
		System.out.print (" Prioridad: "+ servicios.getTarea("T2").getPrioridad());
		System.out.println (" Tiempo: "+ servicios.getTarea("T2").getTiempo());
		System.out.println ("Solucion Greedy: ");
		if((servicios.greedyAsignarTareasProcesadores(30))==null){
			System.out.println ("No existe solución para Greedy");
		}else{
			System.out.println (servicios.greedyAsignarTareasProcesadores(30));
			System.out.println ("Metrica: ["+ servicios.getContadorGreedy()+"]");
		}
		System.out.println ("Solucion Backtracking: ");
		if((servicios.backtrackingAsignarTareasProcesadores(30))==null){
			System.out.println ("No existe solución para Backtracking");
		}else{
		System.out.println (servicios.backtrackingAsignarTareasProcesadores(30));
		System.out.println ("Metrica: ["+ servicios.getContadorBacktracking()+"]");
		}
	}
}