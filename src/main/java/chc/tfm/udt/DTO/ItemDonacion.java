package chc.tfm.udt.DTO;

import com.google.gson.Gson;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemDonacion {

    private Long id;

    private Integer cantidad;

    private Producto producto;

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
