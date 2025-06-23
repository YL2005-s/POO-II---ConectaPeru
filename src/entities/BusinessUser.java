package entities;

public class BusinessUser extends User {
    private String razonSocial;
    private String sector;
    private String descripcion;

    private BusinessUser(Builder builder) {
        super(builder.dni, builder.password, builder.nombre, builder.rol);
        this.razonSocial = builder.razonSocial;
        this.sector = builder.sector;
        this.descripcion = builder.descripcion;
    }

    public String getRazonSocial() {
        return razonSocial;
    }
    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public String getSector() {
        return sector;
    }
    public void setSector(String sector) {
        this.sector = sector;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String dni;
        private String password;
        private String nombre;
        private Role rol;
        private String razonSocial;
        private String sector;
        private String descripcion;

        public Builder dni(String dni) {
            this.dni = dni;
            return this;
        }

        public Builder password(String password) {
            this.password = password;
            return this;
        }

        public Builder nombre(String nombre) {
            this.nombre = nombre;
            return this;
        }

        public Builder rol(Role rol) {
            this.rol = rol;
            return this;
        }

        public Builder razonSocial(String razonSocial) {
            this.razonSocial = razonSocial;
            return this;
        }

        public Builder sector(String sector) {
            this.sector = sector;
            return this;
        }

        public Builder descripcion(String descripcion) {
            this.descripcion = descripcion;
            return this;
        }

        public BusinessUser build() {
            return new BusinessUser(this);
        }
    }
}
