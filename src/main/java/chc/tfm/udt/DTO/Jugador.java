package chc.tfm.udt.DTO;

import com.google.gson.Gson;
import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
public class Jugador {

    private Long id;
    private String nombre;
    private String apellido1;
    private String apellido2;
    private String edad;
    private String dni;
    private String mail;
    private String telefono;
    private Date nacimiento;
    private String nacionalidad;
    private Date inscripcion;
    private String foto;
    private String dorsal;
    private Equipo equipo;
    private List<Donacion> donaciones;


    public Jugador() {

    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
