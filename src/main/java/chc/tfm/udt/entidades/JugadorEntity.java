package chc.tfm.udt.entidades;

import chc.tfm.udt.DTO.Jugador;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.Date;

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
    @NotEmpty
    @Column(name = "dni")
    private String dni;
    @NotEmpty
    @Column(name = "nombre")
    private String nombre;
    @NotEmpty
    @Column(name = "apellido1")
    private String apellido1;
    @NotEmpty
    @Column(name = "apellido2")
    private String apellido2;
    @NotEmpty
    @Column(name = "telefono")
    private String telefono;
    @NotEmpty
    @Email
    @Pattern(regexp=".+@.+\\..+", message = "Formato invalido.")
    @Column(name = "mail")
    private String mail;
    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "nacimiento")
    private Date nacimiento;
    @NotEmpty
    @Column(name = "edad")
    private String edad;
    @NotEmpty
    @Column(name = "nacionalidad")
    private String nacionalidad;
    //Anotación para transformar la fecha para base de datos.
    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "inscripcion")
    private Date inscripcion;
    @NotEmpty
    @Column(name = "dorsal")
    private String dorsal;
    /*
       @Column(name = "equipos_idequipos")
        @ManyToOne(targetEntity = EquipoEntity.class)
        private EquipoEntity equipo;
    */

    /**
     * Metodo que se invoca justo antes de hacer la inserción en base de datos para generar la fecha.
     */
//    @PrePersist
//    public void prePersist(){
//        inscripcion = new Date();
//    }
    public JugadorEntity(){

    }

    public JugadorEntity(Jugador jugador) {
        this.nombre = jugador.getNombre();
        this.apellido1 = jugador.getApellido1();
        this.edad = jugador.getEdad();
        this.dorsal = jugador.getDorsal();
    }
}
