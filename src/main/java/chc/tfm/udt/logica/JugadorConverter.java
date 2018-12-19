package chc.tfm.udt.logica;

import chc.tfm.udt.DTO.Jugador;
import chc.tfm.udt.entidades.JugadorEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
@Component("JugadorConverter")
public class JugadorConverter implements AttributeConverter<Jugador, JugadorEntity> {

    private final Logger LOG = LoggerFactory.getLogger(getClass());

    @Override
    public JugadorEntity convertToDatabaseColumn(Jugador attribute) {
        JugadorEntity e = new JugadorEntity();
        e.setId(attribute.getId());
        e.setNombre(attribute.getNombre());
        e.setApellido1(attribute.getApellido1());
        e.setApellido2(attribute.getApellido2());
        e.setDorsal(attribute.getDorsal());

        return e;
    }

    @Override
    public Jugador convertToEntityAttribute(JugadorEntity dbData) {
        Jugador j = new Jugador();
        j.setId(dbData.getId());
        j.setNombre(dbData.getNombre());
        j.setApellido1(dbData.getApellido1());
        j.setApellido2(dbData.getApellido2());
        j.setDorsal(dbData.getDorsal());
        return j;
    }
}
