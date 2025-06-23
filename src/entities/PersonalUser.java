package entities;

public class PersonalUser extends User {
    private final String cv;
    private final String experiencia;
    private final String estudios;

    private PersonalUser(Builder builder) {
        super(builder.dni, builder.password, builder.nombre, builder.rol);
        this.cv = builder.cv;
        this.experiencia = builder.experiencia;
        this.estudios = builder.estudios;
    }

    public String getCv() {
        return cv;
    }

    public String getExperiencia() {
        return experiencia;
    }

    public String getEstudios() {
        return estudios;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String dni;
        private String password;
        private String nombre;
        private Role rol;
        private String cv;
        private String experiencia;
        private String estudios;

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

        public Builder cv(String cv) {
            this.cv = cv;
            return this;
        }

        public Builder experiencia(String experiencia) {
            this.experiencia = experiencia;
            return this;
        }

        public Builder estudios(String estudios) {
            this.estudios = estudios;
            return this;
        }

        public PersonalUser build() {
            return new PersonalUser(this);
        }
    }
}