package chc.tfm.udt.DTO;

import com.google.gson.Gson;
import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.Date;

@Data
@AllArgsConstructor
public class Producto {

    private Long id;

    private String nombre;

    private Double precio;

    private Date createAt;


    public Producto (){}

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
