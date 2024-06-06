package tpe;

import java.util.LinkedList;

public class Procesador implements Comparable <Procesador> {
    private String id;
    private String codigo;
    private boolean refrigerado;
    private Integer anio;
    private LinkedList <Tarea> tareasAsignadas;
    private int cantCriticas;
    public Procesador(String id, String codigo, boolean refrigerado, Integer anio) {
        this.id = id;
        this.codigo = codigo;
        this.refrigerado = refrigerado;
        this.anio = anio;
        this.tareasAsignadas= new LinkedList<>();
        this.cantCriticas=0;
    }
    public String getId() {
        return id;
    }
    public String getCodigo() {
        return codigo;
    }
    public boolean isRefrigerado() {
        return refrigerado;
    }
    public Integer getAnio() {
        return anio;
    }
    public void addTarea(Tarea t){
        if (t!=null) {
            tareasAsignadas.add(t);;
        }
    }
    public LinkedList <Tarea> getTareas() {
        return this.tareasAsignadas;
    }
    // en este metodo no pasamos parametro porque lo que buscamos es que remueva el ultimo elemento de la lista vinculada 
    public void removeTarea(){
        tareasAsignadas.removeLast();
    }
    public void incrementarCriticas(){
        this.cantCriticas++;
    }
    public void decrementarCriticas(){
        this.cantCriticas--;
    }
    public int getCantCriticas(){
        return this.cantCriticas;
    }
    public String toString (){
        return this.id +" "+ tareasAsignadas;
    }
    
    public Integer getTiempoMax() {
        int suma = 0;
        for (Tarea tareaAsignada : tareasAsignadas) {
            suma=suma+tareaAsignada.getTiempo();
        }
        return suma;
    }
    public int compareTo(Procesador p){
        return this.getTiempoMax()-p.getTiempoMax();
    } 

}
