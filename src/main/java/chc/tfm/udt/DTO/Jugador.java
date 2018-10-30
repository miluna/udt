package chc.tfm.udt.DTO;

import chc.tfm.udt.entidades.EquipoEntity;
import chc.tfm.udt.entidades.JugadorEntity;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Jugador {
    private int id;
    private String dni;
    private String nombre;
    private String apellido1;
    private String apellido2;
    private String telefono;
    private String mail;
    private String nacimiento;
    private String edad;
    private String nacionalidad;
    private String inscripcion;
    private String dorsal;
    private EquipoEntity equipo;


    public Jugador(JugadorEntity entity) {
        this.id = entity.getId();
        this.dni = entity.getDni();
        this.nombre = entity.getNombre();
        this.apellido1 = entity.getApellido1();
        this.apellido2 = entity.getApellido2();
        this.telefono = entity.getTelefono();
        this.mail = entity.getMail();
        this.nacimiento = entity.getNacimiento();
        this.edad = entity.getEdad();
        this.nacionalidad = entity.getNacionalidad();
        this.inscripcion = entity.getInscripcion();
        this.dorsal = entity.getDorsal();
    }
}
