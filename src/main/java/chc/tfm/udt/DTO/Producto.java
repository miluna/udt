package chc.tfm.udt.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Producto {

    private Long id;

    private String nombre;

    private Double precio;

    private Date createAt;
}
