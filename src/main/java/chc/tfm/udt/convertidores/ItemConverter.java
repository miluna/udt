package chc.tfm.udt.convertidores;

import chc.tfm.udt.DTO.ItemDonacion;
import chc.tfm.udt.DTO.Producto;
import chc.tfm.udt.entidades.ItemDonacionEntity;
import chc.tfm.udt.entidades.ProductoEntity;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@NoArgsConstructor
@Converter
@Component("ItemConverter")
public class ItemConverter implements AttributeConverter<ItemDonacion, ItemDonacionEntity> {

    private  final Logger LOG = LoggerFactory.getLogger(getClass());

    @Override
    public ItemDonacionEntity convertToDatabaseColumn(ItemDonacion attribute) {
        ItemDonacionEntity e = new ItemDonacionEntity();

        e.setId(attribute.getId());
        e.setCantidad(attribute.getCantidad());
        e.setProductoEntity(new ProductoEntity(attribute.getProducto()));
        return e;
    }

    @Override
    public ItemDonacion convertToEntityAttribute(ItemDonacionEntity dbData) {
        ItemDonacion d = new ItemDonacion();
        d.setId(dbData.getId());
        d.setCantidad(dbData.getCantidad());
        d.setProducto(new Producto(dbData.getProductoEntity()));

        return d;
    }
}
