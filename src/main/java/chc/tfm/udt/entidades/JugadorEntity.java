package chc.tfm.udt.entidades;

import chc.tfm.udt.DTO.Jugador;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@AllArgsConstructor
@Entity
@Table(name = "jugadores")
public class JugadorEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idjugadores")
    private int id;

    @Column(name = "dni")
    private String dni;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "apellido1")
    private String apellido1;

    @Column(name = "apellido2")
    private String apellido2;

    @Column(name = "telefono")
    private String telefono;

    @Column(name = "mail")
    private String mail;

    @Column(name = "nacimiento")
    private String nacimiento;

    @Column(name = "edad")
    private String edad;

    @Column(name = "nacionalidad")
    private String nacionalidad;

    @Column(name = "inscripcion")
    private String inscripcion;

    @Column(name = "dorsal")
    private String dorsal;
    /*
       @Column(name = "equipos_idequipos")
        @ManyToOne(targetEntity = EquipoEntity.class)
        private EquipoEntity equipo;
    */

    public JugadorEntity(Jugador jugador) {
        this.dni = jugador.getDni();
        this.nombre = jugador.getNombre();
        this.apellido1 = jugador.getApellido1();
        this.apellido2 = jugador.getApellido2();
        this.telefono = jugador.getTelefono();
        this.mail = jugador.getMail();
        this.nacimiento = jugador.getNacimiento();
        this.edad = jugador.getEdad();
        this.nacionalidad = jugador.getNacionalidad();
        this.inscripcion = jugador.getInscripcion();
        this.dorsal = jugador.getDorsal();
    }
}
