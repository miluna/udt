package chc.tfm.udt.DTO;

import chc.tfm.udt.entidades.ProductoEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
public class Producto {

    private Long id;

    private String nombre;

    private Double precio;

    private Date createAt;


    public Producto(Producto producto) {
    }

    public Producto(ProductoEntity productoEntity) {
    }
}
