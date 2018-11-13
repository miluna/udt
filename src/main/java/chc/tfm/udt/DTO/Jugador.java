package chc.tfm.udt.DTO;

import chc.tfm.udt.entidades.EquipoEntity;
import chc.tfm.udt.entidades.JugadorEntity;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Jugador {
    private int id;
    private String nombre;
    private String apellido1;
    private String edad;
    private String dorsal;
    private EquipoEntity equipo;


    public Jugador(JugadorEntity entity) {
        this.id = entity.getId();
        this.nombre = entity.getNombre();
        this.apellido1 = entity.getApellido1();
        this.edad = entity.getEdad();
        this.dorsal = entity.getDorsal();
    }
}
