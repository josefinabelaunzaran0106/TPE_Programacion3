package tpe;
public class Tarea implements Comparable<Tarea> {

    private String id;
    private String nombre;
    private Integer tiempo;
    private Boolean critica;
    private Integer prioridad;
    public Tarea(String id, String nombre, Integer tiempo, Boolean critica, Integer prioridad) {
        this.id = id;
        this.nombre = nombre;
        this.tiempo = tiempo;
        this.critica = critica;
        this.prioridad = prioridad;
    }
    public String getId() {
        return id;
    }
    public String getNombre() {
        return nombre;
    }
    public Integer getTiempo() {
        return tiempo;
    }
    public Boolean getCritica() {
        return critica;
    }
    public Integer getPrioridad() {
        return prioridad;
    }
    public String toString (){
        return "Id: "+ id + " Nombre: "+ nombre+ " Tiempo: "+ tiempo+ " Critica: " + critica+ " Prioridad: " + prioridad;
    } 
    /*
    //to String simplificado para servivio Backtracking y Greedy
    public String toString (){
        return "Id: "+ id; 
    } 
    */
    public int compareTo(Tarea t){
        return t.getTiempo()-this.getTiempo();
    }

}

    
    

