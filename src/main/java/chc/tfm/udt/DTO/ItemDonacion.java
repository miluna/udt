package chc.tfm.udt.DTO;

import chc.tfm.udt.entidades.ItemDonacionEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
public class ItemDonacion {

    private Long id;

    private Integer cantidad;

    private Producto producto;

    public ItemDonacion(ItemDonacionEntity itemDonacionEntity){


    }
}
