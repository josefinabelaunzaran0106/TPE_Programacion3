package tpe;

import java.util.ArrayList;
import java.util.Collections;
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
	private Solucion solucionBacktracking = new Solucion();
	private Solucion solucionGreddy = new Solucion();
	private int contadorBackatracking = 0;
	private int contadorGreedy = 0;

	/*
	 * La complejidad temporal del constructor es O(n).
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
	 * La complejidad temporal del servicio 1 es O(1).
	 */
	public Tarea servicio1(String ID) {
		return hashtableTareas.get(ID);
	}
	/*
	 * La complejidad temporal del servicio 2 es O(1).
	*/
	public List<Tarea> servicio2(boolean esCritica) {
		if (esCritica == true) {
			return tareasCriticas;
		} else {
			return tareasNoCriticas;
		}
	}
	/*
	 * La complejidad temporal del servicio 3 es O(n)
	*/
	public List<Tarea> servicio3(int prioridadInferior, int prioridadSuperior) {
		ArrayList<Tarea> aux = new ArrayList<Tarea>();
		for (Tarea t : tareas) {
			if (t.getPrioridad() > prioridadInferior & t.getPrioridad() < prioridadSuperior) {
				aux.add(t);
			}
		}
		return aux;
	}
	/*
	 * La estrategia utilizada es ir generando estados soluciones a partir de
	 * asignar todas las tareas a los procesadores considerando las restricciones
	 * planteadas en el enunciado. Operamos los estados solución para encontrar la 
	 * mejor solución con el mismo criterio que empleamos en la poda.
	 */
	public Solucion backtrackingAsignarTareasProcesadores(Integer tiempo) {
        Solucion estadoInicial = new Solucion();
        // se agregan los procesadores al estado inicial
        for (Procesador procesador : procesadores) {
            estadoInicial.addProcesador(procesador);
        }
        // se llama a backstracking desde el metodo publico
        this.backtrackingAsignarTareasProcesadores(0, tiempo, estadoInicial);
        // se retorna la solución final
        return this.solucionBacktracking;
    }

    private void backtrackingAsignarTareasProcesadores(int indiceTarea, Integer tiempo, Solucion parcial) {
        this.contadorBackatracking++;
        // si llegue a destino
        if (indiceTarea == tareas.size()) {
            // restricción antes de copia
            if ((solucionBacktracking.getTiempoSolucion() == null) ||
                    (parcial.getTiempoSolucion() < solucionBacktracking.getTiempoSolucion())) {
                // se copia a la solución final la solucion parcial
                solucionBacktracking.copy(parcial);
            }
        } else {
            Iterator<Procesador> it = parcial.getProcesadores().iterator();
            while (it.hasNext()) {
                Procesador siguiente = it.next();
                // restricciones del procesador para asignar tareas
                if (((siguiente.getCantCriticas() < MAX && tareas.get(indiceTarea).getCritica()) || !tareas.get(indiceTarea).getCritica())
                        && (((!siguiente.isRefrigerado() && (siguiente.getTiempoMax() + tareas.get(indiceTarea).getTiempo()) <= tiempo))
                            || siguiente.isRefrigerado())) {
                    // se agrega la tarea al prcesador
                    siguiente.addTarea(tareas.get(indiceTarea));
                    // se corrobora si la tarea es critica, y en caso de serla se incrementa la
                    // cantidad de tareas crticas del procesador
                    if (tareas.get(indiceTarea).getCritica()) {
                        siguiente.incrementarCriticas();
                    }
                    // poda antes de llamar a backtracking
                    if ((solucionBacktracking.getTiempoSolucion() == null) ||
                            (parcial.getTiempoSolucion() < solucionBacktracking.getTiempoSolucion())) {
                        backtrackingAsignarTareasProcesadores(indiceTarea + 1, tiempo, parcial);
                    }
                    // se corrobora si la tarea es critica, y en caso de serla se decrementa la
                    // cantidad de tareas crticas del procesador
                    if (tareas.get(indiceTarea).getCritica()) {
                        siguiente.decrementarCriticas();
                    }
                    // se remueve la tarea al procesador
                    siguiente.removeTarea();
                }
            }
        }
    }

    /*
     * La estrategia utilizada es ir generando una solución a partir de asignarle
     * tareas con mayor tiempo hasta el momento a los procesadores
     * cuyo tiempo maximo sea el menor.
     */
    public Solucion greedyAsignarTareasProcesadores(Integer tiempo) {
        // realizamos copia de arreglo de procesadores
        ArrayList<Procesador> candidatosProcesadores = new ArrayList<Procesador>();
        for (Procesador p : this.procesadores) {
            Procesador aux = new Procesador(p.getId(), p.getCodigo(), p.isRefrigerado(), p.getTiempoMax());
            candidatosProcesadores.add(aux);
        }
        // realizamos copia de arreglo de tareas
        ArrayList<Tarea> candidatosTareas = new ArrayList<Tarea>();
        for (Tarea t : this.tareas) {
            Tarea taux = new Tarea(t.getId(), t.getNombre(), t.getTiempo(), t.getCritica(), t.getPrioridad());
            candidatosTareas.add(taux);
        }
        this.greedyAsignarTareasProcesadores(tiempo, candidatosProcesadores, candidatosTareas, solucionGreddy);
        return this.solucionGreddy;
    }

    private void greedyAsignarTareasProcesadores(Integer tiempo, ArrayList<Procesador> candidatosProcesadores,
            ArrayList<Tarea> candidatosTareas, Solucion solucion) {
        // ordenamos las tareas en forma descendente de acuerdo a su tiempo
        Collections.sort(candidatosTareas);
        // criterio de corte (no tener mas tareas)
        while (!candidatosTareas.isEmpty()) {
            // ordenamos los procesadores en forma ascendente segun su tiempo máximo
            Collections.sort(candidatosProcesadores);
            Iterator<Procesador> it = candidatosProcesadores.iterator();
            boolean continuar = true;
            while (it.hasNext() && continuar) {
                this.contadorGreedy++;
                Procesador siguiente = it.next();
                // criterio de asignación para agregar tareas a los procesadores
                if (((siguiente.getCantCriticas() < MAX && candidatosTareas.get(0).getCritica()) 
                        || !candidatosTareas.get(0).getCritica())
                        && (((!siguiente.isRefrigerado() 
                        && (siguiente.getTiempoMax() + candidatosTareas.get(0).getTiempo()) <= tiempo))
                        || siguiente.isRefrigerado())) {
                    siguiente.addTarea(candidatosTareas.get(0));
                    if (candidatosTareas.get(0).getCritica()) {
                        siguiente.incrementarCriticas();
                    }
                    // eliminamos tarea
                    candidatosTareas.remove(0);
                    continuar = false;
                }
            }
            if (continuar == true) {
                return;
            }
        }
        // agregamos a nuestra solucion el resultado de los procesadores con sus tareas
        // asignadas
        for (Procesador p : candidatosProcesadores) {
            solucion.addProcesador(p);
        }
    }
	public int getContadorBacktracking() {
		return this.contadorBackatracking;
	}
	public int getContadorGreedy() {
		return this.contadorGreedy;
	}
}
