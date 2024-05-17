package tpe;

public class Procesador {
    private String id;
    private String codigo;
    private boolean refrigerado;
    private Integer anio;
    public Procesador(String id, String codigo, boolean refrigerado, Integer anio) {
        this.id = id;
        this.codigo = codigo;
        this.refrigerado = refrigerado;
        this.anio = anio;
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
}
