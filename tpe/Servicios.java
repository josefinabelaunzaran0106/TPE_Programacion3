package tpe;

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
	public Servicios(String pathProcesadores, String pathTareas)
	{
		CSVReader reader = new CSVReader();
		this.hashtableTareas = new Hashtable<>();
		this.hashtableProcesadores = new Hashtable<>();
		hashtableProcesadores=reader.readProcessors(pathProcesadores);
		hashtableTareas=reader.readTasks(pathTareas);
	}
	
	/*
     * Expresar la complejidad temporal del servicio 1.
     */
	public Tarea getTarea (String ID) {	
		if (hashtableTareas.containsKey(ID)){
			return hashtableTareas.get(ID);
		}
		return null;
	}
    
    /*
     * Expresar la complejidad temporal del servicio 2.
     */
	public List<Tarea> isCritic(boolean esCritica) {
		LinkedList <Tarea> tareasCriticas= new LinkedList<Tarea>();
		Enumeration tareasKey= hashtableTareas.keys();
		while(tareasKey.hasMoreElements()){
			Tarea aux= hashtableTareas.get(tareasKey.nextElement());
			if(aux.getCritica()== esCritica){
				tareasCriticas.add(aux);
			}
		}
		return tareasCriticas;
	}

    /*
     * Expresar la complejidad temporal del servicio 3.
     */

	public List<Tarea> getTareasPrioridadIn(int prioridadInferior, int prioridadSuperior) {
		LinkedList <Tarea> tareasPrioridad= new LinkedList<Tarea>();
		Enumeration tareasKey= hashtableTareas.keys();
		while(tareasKey.hasMoreElements()){
			Tarea aux= hashtableTareas.get(tareasKey.nextElement());
			if(aux.getPrioridad()>prioridadInferior & aux.getPrioridad()<prioridadSuperior){
				tareasPrioridad.add(aux);
			}
		}
		return tareasPrioridad;
	}
}