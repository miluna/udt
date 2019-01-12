package chc.tfm.udt.DTO;

import com.google.gson.Gson;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ItemDonacion {

    private Long id;

    private Integer cantidad;

    private Producto producto;

    public ItemDonacion() {

    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
