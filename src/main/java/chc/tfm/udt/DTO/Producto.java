package chc.tfm.udt.DTO;

import com.google.gson.Gson;
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

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
