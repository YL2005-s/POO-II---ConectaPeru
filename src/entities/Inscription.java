package entities;

public class Inscription {
    private String candidatoDni;
    private long vacanteId;
    private String estado;
    private String fechaInscripcion;

    public Inscription(String candidatoDni, long vacanteId, String estado, String fechaInscripcion) {
        this.candidatoDni = candidatoDni;
        this.vacanteId = vacanteId;
        this.estado = estado;
        this.fechaInscripcion = fechaInscripcion;
    }

    public String getCandidatoDni() {
        return candidatoDni;
    }

    public void setCandidatoDni(String candidatoDni) {
        this.candidatoDni = candidatoDni;
    }

    public long getVacanteId() {
        return vacanteId;
    }
    public void setVacanteId(long vacanteId) {
        this.vacanteId = vacanteId;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getFechaInscripcion() {
        return fechaInscripcion;
    }

    public void setFechaInscripcion(String fechaInscripcion) {
        this.fechaInscripcion = fechaInscripcion;
    }
}
