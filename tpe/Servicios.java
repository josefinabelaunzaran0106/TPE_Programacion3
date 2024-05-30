package tpe;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;

import tpe.utils.CSVReader;

/**
 * NO modificar la interfaz de esta clase ni sus métodos públicos.
 * Sólo se podrá adaptar el nombre de la clase "Tarea" según sus decisiones
 * de implementación.
 */
public class Servicios {

	private Hashtable<String, Tarea> hashtableTareas;
	private Hashtable<String, Procesador> hashtableProcesadores;

	/*
	 * Expresar la complejidad temporal del constructor.
	 */
	public Servicios(String pathProcesadores, String pathTareas) {
		CSVReader reader = new CSVReader();
		this.hashtableTareas = new Hashtable<>();
		this.hashtableProcesadores = new Hashtable<>();
		hashtableProcesadores = reader.readProcessors(pathProcesadores);
		hashtableTareas = reader.readTasks(pathTareas);
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
		LinkedList<Tarea> tareasCriticas = new LinkedList<Tarea>();
		Enumeration<String> tareasKey = hashtableTareas.keys();
		while (tareasKey.hasMoreElements()) {
			Tarea aux = hashtableTareas.get(tareasKey.nextElement());
			if (aux.getCritica() == esCritica) {
				tareasCriticas.add(aux);
			}
		}
		return tareasCriticas;
	}

	/*
	 * Expresar la complejidad temporal del servicio 3.
	 */

	public List<Tarea> getTareasPrioridadIn(int prioridadInferior, int prioridadSuperior) {
		LinkedList<Tarea> tareasPrioridad = new LinkedList<Tarea>();
		Enumeration<String> tareasKey = hashtableTareas.keys();
		while (tareasKey.hasMoreElements()) {
			Tarea aux = hashtableTareas.get(tareasKey.nextElement());
			if (aux.getPrioridad() > prioridadInferior & aux.getPrioridad() < prioridadSuperior) {
				tareasPrioridad.add(aux);
			}
		}
		return tareasPrioridad;
	}

	public Hashtable<String, ArrayList<String>> asignarTareasProcesadores(int tiempo) {
		ArrayList<Tarea> tareas = new ArrayList<>();
		Enumeration<String> keyTareas = hashtableTareas.keys();
		Hashtable<String, Integer> tiempoProcesadores = new Hashtable<>();
		Integer mejorTiempo = 0;
		Hashtable<String, ArrayList<String>> asignacionParcial = new Hashtable<String, ArrayList<String>>();
		Hashtable<String, ArrayList<String>> asignacionFinal = new Hashtable<String, ArrayList<String>>();
		// copia de las tareas en el ArrayList tareas
		while (keyTareas.hasMoreElements()) {
			Tarea t = hashtableTareas.get(keyTareas.nextElement());
			tareas.add(t);
		}
		Enumeration<String> keyProcesadores = hashtableProcesadores.keys();

		// Configurar estado inicial tiempo de todos los procesadores en 0
		
		while (keyProcesadores.hasMoreElements()) {
			Procesador p = hashtableProcesadores.get(keyProcesadores.nextElement());
			tiempoProcesadores.put(p.getId(), 0);
			asignacionParcial.put(p.getId(), new ArrayList<>());
			asignacionFinal.put(p.getId(), new ArrayList<>());
		}

		backtrackingAsignarTareasProcesadores(0, tareas, asignacionParcial, asignacionFinal,
				tiempoProcesadores, tiempo, mejorTiempo);
		return asignacionFinal;
	}
	private void backtrackingAsignarTareasProcesadores(int tarea, ArrayList<Tarea> tareas,
			Hashtable<String, ArrayList<String>> asignacionParcial,
			Hashtable<String, ArrayList<String>> asignacionFinal, Hashtable<String, Integer> tiempoProcesadores,
			Integer tiempo, Integer mejorTiempo) {
				//System.out.println (asignacionFinal);
		if (tarea == hashtableTareas.size()) {
			if ((mejorTiempo == 0) || (getCosto(tiempoProcesadores) < mejorTiempo)) {
				asignacionFinal.clear();
				
				asignacionFinal.putAll(asignacionParcial);
				System.out.println (asignacionFinal);
				mejorTiempo = getCosto(tiempoProcesadores);
			}
		} else {
			Enumeration<String> keyProcesadores = hashtableProcesadores.keys();
			//System.out.println (keyProcesadores.hasMoreElements());
			while (keyProcesadores.hasMoreElements()) {
				Procesador p = hashtableProcesadores.get(keyProcesadores.nextElement());
				asignacionParcial.get(p.getId()).add(tareas.get(tarea).getId());
				tiempoProcesadores.put(p.getId(),tareas.get(tarea).getTiempo());
				if (!((tieneMenosDeDosCriticas(asignacionParcial.get(p.getId())))
					&& ((!p.isRefrigerado()) && tiempoProcesadores.get(p.getId()) > tiempo))) {
					backtrackingAsignarTareasProcesadores(tarea + 1, tareas, asignacionParcial,
					asignacionFinal, tiempoProcesadores, tiempo, mejorTiempo);
					System.out.println (tarea);
				}
				asignacionParcial.get(p.getId()).remove(tareas.get(tarea).getId());
				tiempoProcesadores.remove(p.getId(),tareas.get(tarea).getTiempo());
			}
		}
		
	}

	private Integer getCosto(Hashtable<String, Integer> tiempoProcesadores) {
		Enumeration<String> tiempos = tiempoProcesadores.keys();
		Integer min = 0;
		Integer t = 0;
		Integer aux = 0;
		while (tiempos.hasMoreElements()) {
			t = tiempoProcesadores.get(tiempos.nextElement());
			if (t < aux) {
				min = t;
			}
			aux = t;
		}
		// System.out.println(min);
		return min;
	}

	private boolean tieneMenosDeDosCriticas(ArrayList<String> tareas) {
		int cantidad = 0;
		if (tareas != null) {
			Enumeration<String> tareasKey = hashtableTareas.keys();
			while (tareasKey.hasMoreElements()) {
				Tarea t = hashtableTareas.get(tareasKey.nextElement());
				if (t.getCritica() == true) {
					cantidad++;
				}
			}
		}
		if (cantidad <= 2) {
			return true;
		}

		return false;
	}


/*
 * public void asignarTareasProcesadores(int x){
 * 
 * 
 * 
 * }
 * private void backtrackingAsignarTareasProcesadores(){
 * /*if (tareas.size()==0){
 * if (actual.getCosto(hashtbable)<solucion.getCosto(hashtable)){
 * solcion = actual;
 * }
 * else{
 * Enumeration keyProcesadores= hashTableProcesadores.key();
 * while (keyProcesadores.hasMoreElements()){
 * Procesador siguiente=
 * hastableProcesadores.get(keyProcesadores.nextElement());
 * if (puedoRecibirTareas(siguiente)){
 * siguiente.addTarea(?)
 * backtrackingAsignarTareasProcesadores()
 * siguiente.removeTarea(?)
 * }
 * }
 * }
 * }
 * }
 */
 }