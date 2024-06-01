package tpe;
public class Tarea {

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
        return "Id: "+id;
    }
     
   
    @Override
    public boolean equals(Object t) {
		try{
			Tarea v1 = (Tarea)t;
			return v1.id== id;
		} catch(Exception e) {
			return false;
		}
	}

    
    
}
