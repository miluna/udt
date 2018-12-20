package chc.tfm.udt.logica;

import chc.tfm.udt.DTO.Donacion;
import chc.tfm.udt.DTO.Jugador;
import chc.tfm.udt.entidades.DonacionEntity;
import chc.tfm.udt.entidades.JugadorEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.stream.Collectors;

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
        e.setFoto(attribute.getFoto());
        e.setDni(attribute.getDni());
        e.setDonaciones(attribute.getDonaciones().stream().
                map(j -> new DonacionEntity(j)).
                collect(Collectors.toList()));
        e.setEdad(attribute.getEdad());
        e.setInscripcion(attribute.getInscripcion());
        e.setMail(attribute.getMail());
        e.setNacimiento(attribute.getNacimiento());
        e.setNacionalidad(attribute.getNacionalidad());
        e.setTelefono(attribute.getTelefono());

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
