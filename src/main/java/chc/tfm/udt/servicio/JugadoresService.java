package chc.tfm.udt.servicio;

import chc.tfm.udt.DTO.Jugador;
import chc.tfm.udt.entidades.JugadorEntity;
import chc.tfm.udt.logica.JugadorConverter;
import chc.tfm.udt.repositorios.IJugadorRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service(value = "JugadoresService")
public class JugadoresService implements CrudService<Jugador> {
    private final Logger LOG = LoggerFactory.getLogger(getClass());
    private IJugadorRepository jugadorRepository;
    private JugadorConverter converter;

    public JugadoresService (@Qualifier("IJugadorRepository") IJugadorRepository jugadorRepository,
                             @Qualifier("JugadorConverter") JugadorConverter converter){
        this.jugadorRepository = jugadorRepository;
        this.converter = converter;


    }
    @Override
    public Jugador createOne(Jugador jugador) {
        JugadorEntity j = converter.convertToDatabaseColumn(jugador);
        JugadorEntity saved = jugadorRepository.save(j);
        Jugador returned = converter.convertToEntityAttribute(saved);
        return returned;
    }

    @Override
    @Transactional(readOnly = true)
    public Jugador findOne(Long id) {
        Jugador resultado = null;
        Optional<JugadorEntity> buscar = jugadorRepository.findById(id);
        if(buscar.isPresent()){
            resultado = new Jugador(buscar.get());
        }

        return resultado;
    }

    @Override
    public Jugador updateOne(Long id, Jugador jugador) {
        return null;
    }

    @Override
    public Boolean deleteOne(Long id) {
        if(jugadorRepository.findById(id).isPresent()){
            jugadorRepository.deleteById(id);
        }
        return true;
    }

    @Override
    public List<Jugador> findAll() {
        List<Jugador> resultado = jugadorRepository.findAll().
                stream().
                map(r -> new Jugador(r)).
                collect(Collectors.toList());
        return resultado;
    }
}
