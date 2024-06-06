package tpe;


import java.util.LinkedList;


public class Solucion {
    private LinkedList <Procesador> procesadores;

    public Solucion() {
        this.procesadores = new LinkedList<Procesador>();
    }

    public void addProcesador(Procesador p){
        procesadores.addLast(p);
    }
    // en este metodo no pasamos parametro porque lo que buscamos es que remueva el ultimo elemento de la lista vinculada 
    public void removeProcesador(){
        procesadores.removeLast();
    }
    public Integer getTiempoSolucion() {
        Integer max = null;
        for (Procesador procesador : procesadores) {
            if (max == null) {
                max = procesador.getTiempoMax();
            } else {
                if ((max < procesador.getTiempoMax())){
                    max=procesador.getTiempoMax();
                }
            }
        }
        return max;
    }

    public LinkedList<Procesador> getProcesadores (){
        LinkedList<Procesador> aux = new LinkedList<Procesador>();
        aux=procesadores;
        return aux;
    }

    public void setProcesadores (LinkedList<Procesador> nueva){
        this.procesadores = nueva;
    }

    public void clearProcesadores (){
        this.procesadores.clear();
    }
    public String toString (){
        return "Tiempo: [" +getTiempoSolucion()  +"] " + procesadores ;
    }
    public void copy(Solucion parcial){

        this.procesadores = new LinkedList<Procesador>();

        for (Procesador p : parcial.getProcesadores()) {
            Procesador aux = new Procesador(p.getId(), p.getCodigo(), p.isRefrigerado(), p.getTiempoMax());
            for (Tarea t : p.getTareas()){
                Tarea taux = new Tarea(t.getId(), t.getNombre(), t.getTiempo(),t.getCritica(), t.getPrioridad()); 
                aux.addTarea(taux);   
            }   
            this.procesadores.add(aux);
        }   
    }
}