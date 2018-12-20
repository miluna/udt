package chc.tfm.udt.DTO;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Donacion {

    private Long id;

    private String descripcion;

    private String observacion;

    private Date createAt;

    private Jugador jugador;

    private List<ItemDonacion> items;

    public Donacion(Donacion donacion) {
    }
}
