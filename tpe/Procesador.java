package tpe;

import java.util.LinkedList;

public class Procesador {
    private String id;
    private String codigo;
    private boolean refrigerado;
    private Integer anio;
    private LinkedList <Tarea> tareasAsignadas;
    private int tMax;
    private int cantCriticas;
    public Procesador(String id, String codigo, boolean refrigerado, Integer anio) {
        this.id = id;
        this.codigo = codigo;
        this.refrigerado = refrigerado;
        this.anio = anio;
        this.tMax = 0;
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
    public void incrementarTiempo(int t){
        tMax=tMax+t;
    }
    public void decrementarTiempo(int t){
        tMax=tMax-t;
    }
    public int getTiempo(){
        return this.getTiempo();
    }
    
    public void addTarea(Tarea t){
        tareasAsignadas.addLast(t);
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

}
