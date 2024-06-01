package tpe;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;

import tpe.utils.CSVReader;

/**
 * NO modificar la interfaz de esta clase ni sus métodos públicos.
 * Sólo se podrá adaptar el nombre de la clase "Tarea" según sus decisiones
 * de implementación.
 */
public class Servicios {

	private Hashtable<String, Tarea> hashtableTareas;
	private ArrayList<Tarea> tareas;
	private ArrayList<Procesador> procesadores;
	private ArrayList<Tarea> tareasCriticas;
	private ArrayList<Tarea> tareasNoCriticas;
	private final int MAX = 2; // cantidad máx de tareas criticas por procesador
	private Solucion solucionFinal = new Solucion();

	/*
	 * Expresar la complejidad temporal del constructor.
	 */
	public Servicios(String pathProcesadores, String pathTareas) {
		CSVReader reader = new CSVReader();
		this.hashtableTareas = new Hashtable<>();
		this.tareasCriticas = new ArrayList<Tarea>();
		this.tareasNoCriticas = new ArrayList<Tarea>();
		procesadores = reader.readProcessors(pathProcesadores);
		tareas = reader.readTasks(pathTareas);

		for (Tarea t : tareas) {
			hashtableTareas.put(t.getId(), t);
			if (t.getCritica() == true) {
				tareasCriticas.add(t);
			} else {
				tareasNoCriticas.add(t);
			}

		}
	}

	/*
	 * Expresar la complejidad temporal del servicio 1.
	 */
	public Tarea getTarea(String ID) {
		if (hashtableTareas.containsKey(ID)) {
			return hashtableTareas.get(ID);
		}
		return null;
	}

	/*
	 * Expresar la complejidad temporal del servicio 2.
	 */
	public List<Tarea> isCritic(boolean esCritica) {
		if (esCritica == true) {
			return tareasCriticas;
		} else {
			return tareasNoCriticas;
		}
	}

	/*
	 * Expresar la complejidad temporal del servicio 3.
	 */

	public List<Tarea> getTareasPrioridadIn(int prioridadInferior, int prioridadSuperior) {
		ArrayList<Tarea> aux = new ArrayList<Tarea>();
		for (Tarea t : tareas) {
			if (t.getPrioridad() > prioridadInferior & t.getPrioridad() < prioridadSuperior) {
				aux.add(t);
			}
		}
		return aux;
	}

	public Solucion backtrackingAsignarTareasProcesadores(Integer tiempo) {
		Solucion estadoInicial = new Solucion();
		for (Procesador procesador : procesadores) {
			estadoInicial.addProcesador(procesador);
		}
		this.backtrackingAsignarTareasProcesadores(0, tiempo, estadoInicial);
		return this.solucionFinal;
	}

	private void backtrackingAsignarTareasProcesadores(int indiceTarea, Integer tiempo, Solucion parcial) {
		if (indiceTarea == tareas.size()) {  
			solucionFinal.copy(parcial);						
		} else {
			Iterator<Procesador> it = parcial.getProcesadores().iterator();
			while (it.hasNext()) {
				Procesador siguiente = it.next();
				if ((siguiente.getCantCriticas() <= MAX)
						|| (!siguiente.isRefrigerado() && siguiente.getTiempoMax() < tiempo)) {
					// agregar
					siguiente.addTarea(tareas.get(indiceTarea));
					
					if (tareas.get(indiceTarea).getCritica()) {
						siguiente.incrementarCriticas();
					}
					
					if ((solucionFinal.getTiempoSolucion() == null) ||
							(parcial.getTiempoSolucion() < solucionFinal.getTiempoSolucion())) {
						backtrackingAsignarTareasProcesadores(indiceTarea + 1, tiempo, parcial);
					}
					
					// remover
					if (tareas.get(indiceTarea).getCritica()) {
						siguiente.decrementarCriticas();
					}
					//System.out.println (siguiente);
					siguiente.removeTarea();
					
				}
			}
		}

	}
}
