package entities;

public class Vacant {
    private long id;
    private String titulo;
    private String descripcion;
    private Double salario;
    private String ubicacion;
    private String requisitos;
    private String fechas;

    public Vacant(long id, String titulo, Double salario, String descripcion, String ubicacion, String requisitos, String fechas) {
        this.id = id;
        this.titulo = titulo;
        this.salario = salario;
        this.descripcion = descripcion;
        this.ubicacion = ubicacion;
        this.requisitos = requisitos;
        this.fechas = fechas;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Double getSalario() {
        return salario;
    }

    public void setSalario(Double salario) {
        this.salario = salario;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getRequisitos() {
        return requisitos;
    }

    public void setRequisitos(String requisitos) {
        this.requisitos = requisitos;
    }

    public String getFechas() {
        return fechas;
    }

    public void setFechas(String fechas) {
        this.fechas = fechas;
    }
}


