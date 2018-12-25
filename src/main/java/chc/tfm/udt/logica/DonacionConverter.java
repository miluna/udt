package chc.tfm.udt.logica;

import chc.tfm.udt.DTO.Donacion;
import chc.tfm.udt.DTO.ItemDonacion;
import chc.tfm.udt.DTO.Jugador;
import chc.tfm.udt.entidades.DonacionEntity;
import chc.tfm.udt.entidades.ItemDonacionEntity;
import chc.tfm.udt.entidades.JugadorEntity;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.stream.Collectors;

@Converter
@Component("DonacionConverter")
@AllArgsConstructor
public class DonacionConverter implements AttributeConverter<Donacion, DonacionEntity> {

    private final Logger log = LoggerFactory.getLogger(getClass());
    //Convertir de DTO a ENTITY
    @Override
    public DonacionEntity convertToDatabaseColumn(Donacion attribute) {
        DonacionEntity e = new DonacionEntity();
        log.info("Creamos el objeto DonacionEntity");
        e.setId(attribute.getId());
        e.setObservacion(attribute.getObservacion());
        e.setDescripcion(attribute.getDescripcion());
        e.setCreateAt(attribute.getCreateAt());
        // Set de lista de items de la factura
        e.setItems(attribute.getItems().
                stream().
                map(ItemDonacionEntity::new).
                collect(Collectors.toList()));
        // Set del jugador
        e.setJugadorEntity(new JugadorEntity(attribute.getJugador()));
        log.info("Se ha seteado bien el entity");
        return e;
    }

    //Convertir de entity a dto
    @Override
    public Donacion convertToEntityAttribute(DonacionEntity dbData) {
        Donacion d = new Donacion();
        log.info("Creamos el Donacion DTO");
        d.setDescripcion(dbData.getDescripcion());
        d.setObservacion(dbData.getObservacion());
        d.setCreateAt(dbData.getCreateAt());
        d.setId(dbData.getId());
        // Seteo de la lista de items de la factura
        d.setItems(dbData.getItems().
                stream().
                map(ItemDonacion::new).
                collect(Collectors.toList()));
        // Seteamos al jugador lo que viene de la entity
        d.setJugador(new Jugador(dbData.getJugadorEntity()));
    log.info("Se ha seteado bien el dto");

        return d;
    }
}
