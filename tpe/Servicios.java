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
	private ArrayList<Tarea> tareas;
	private ArrayList<Procesador> procesadores;
	private ArrayList<Tarea> tareasCriticas;
	private ArrayList<Tarea> tareasNoCriticas;

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
			if(t.getCritica()==true){
				tareasCriticas.add(t);
			}else{
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
		if(esCritica==true){
			return tareasCriticas;
		}else{
			return tareasNoCriticas;
		}
	}

	/*
	 * Expresar la complejidad temporal del servicio 3.
	 */

	public List<Tarea> getTareasPrioridadIn(int prioridadInferior, int prioridadSuperior) {
		ArrayList <Tarea> aux=new ArrayList<Tarea>();
		for (Tarea t : tareas) {
			if (t.getPrioridad() > prioridadInferior & t.getPrioridad() < prioridadSuperior) {
				aux.add(t);
			}
		}
		return aux;
	}

	public Solucion asignarTareasProcesadores(int tiempo) {
		Solucion parcial= new Solucion();
		Solucion solucionFinal=new Solucion();
		for (Procesador procesador : procesadores) {
			parcial.addProcesador(procesador);
		}
		backtrackingAsignarTareasProcesadores(0, parcial, solucionFinal);
				//System.out.println (asignacionFinal);
		return solucionFinal;
	}
	private void backtrackingAsignarTareasProcesadores(int tarea, Solucion parcial, Solucion solucionFinal) {
		if (tarea==tareas.size()) {
			if (true) {
				
			}
		} else {
			Enumeration<String> keyProcesadores = hashtableProcesadores.keys();
			while (keyProcesadores.hasMoreElements()) {
				Procesador p = hashtableProcesadores.get(keyProcesadores.nextElement());	
				if(true){
					//agregar
					
				//if (!((tieneMenosDeDosCriticas(asignacionParcial.get(p.getId())))
					//&& (!((!p.isRefrigerado()) && tiempoProcesadores.get(p.getId()) < tiempo) ))) {
					backtrackingAsignarTareasProcesadores(tarea+1,parcial, solucionFinal);
					}
					//remover
					
				}
			}
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