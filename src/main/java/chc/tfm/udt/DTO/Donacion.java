package chc.tfm.udt.DTO;

import chc.tfm.udt.entidades.DonacionEntity;
import chc.tfm.udt.entidades.ItemDonacionEntity;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
public class Donacion {

    private Long id;

    private String descripcion;

    private String observacion;

    private Date createAt;

    private Jugador jugador;

    private List<ItemDonacion> items;

    public Donacion(DonacionEntity donacionEntity) {

    }
    public Donacion() {
        this.items = new ArrayList<>();
    }
}
