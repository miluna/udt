package chc.tfm.udt.DTO;

import chc.tfm.udt.entidades.EquipoEntity;
import chc.tfm.udt.entidades.JugadorEntity;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Jugador {
    private Long id;
    private String nombre;
    private String apellido1;
    private String apellido2;
    private String edad;
    private String dorsal;
    private EquipoEntity equipo;

    public Jugador(JugadorEntity jugadorEntity){


    }

    public Jugador() {

    }
}
