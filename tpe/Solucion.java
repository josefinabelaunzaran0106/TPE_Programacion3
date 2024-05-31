package tpe;


import java.util.LinkedList;

public class Solucion {
    private LinkedList <Procesador> procesadores;
    private int tMax;
    public Solucion() {
        this.procesadores = new LinkedList<Procesador>();
        this.tMax = 0;
    }
     public void addProcesador(Procesador p){
        procesadores.add(p);
    }
    // en este metodo no pasamos parametro porque lo que buscamos es que remueva el ultimo elemento de la lista vinculada 
    public void removeTarea(){
        procesadores.removeLast();
    }
    public int getTiempoMax(){
        int tiempoParcial=0;
        for (Procesador procesador : procesadores) {
            if(procesador.getTiempo()>tiempoParcial){
                tiempoParcial=procesador.getTiempo();
            }
        }
        return tiempoParcial;
    }
   

    
}
