package chc.tfm.udt.servicio;

import chc.tfm.udt.DTO.Donacion;
import chc.tfm.udt.DTO.Jugador;
import chc.tfm.udt.entidades.DonacionEntity;
import chc.tfm.udt.logica.DonacionConverter;
import chc.tfm.udt.logica.JugadorConverter;
import chc.tfm.udt.repositorios.IDonacionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.transaction.annotation.Transactional;


@Service(value = "DonacionesService")
public class DonacionesService implements CrudService<Donacion> {
    private final Logger log = LoggerFactory.getLogger(getClass());
    private IDonacionRepository donacionRepository;
    private DonacionConverter converter;
    private CrudService<Jugador> jugadoresService;
    private JugadorConverter jugadoresConverter;
    
    @Autowired
    public DonacionesService(@Qualifier("IDonacionRepository") IDonacionRepository donacionRepository,
                             @Qualifier("DonacionConverter") DonacionConverter converter,
                             @Qualifier("JugadoresService") CrudService<Jugador> jugadoresService,
                             @Qualifier("JugadorConverter") JugadorConverter jugadoresConverter){
        this.donacionRepository = donacionRepository;
        this.converter = converter;
        this.jugadoresService = jugadoresService;
        this.jugadoresConverter = jugadoresConverter;
    }

    @Override
    public Donacion createOne(Donacion donacion) {
//Basicamente es lo unico que me pidio gustavo , que hiciera los convertidores pero no a la chusca
        // usar converter para crear el entity -> el resultado no tendrá id
        //esa clase de donde la has sacado ^.-
        log.info("LLegamos al servicio");
        DonacionEntity d = converter.convertToDatabaseColumn(donacion);
        // el save devuelve el entity igual, pero con el ID, tu cuando creas lo creas con id=null
        log.info("HEmos comvertido bien ! ");
        DonacionEntity saved = donacionRepository.save(d);
        log.info("De aqui no pasa.");
        // ahora conviertes a DTO el que te devuelve el repositorio, que tiene el id, 
        // y ya lo devuelves con todos los datos
        log.info(" Apunto de convertir a dto de nuevo");
        Donacion returned = converter.convertToEntityAttribute(saved);
        log.info("Aqui creeo que peta");
        return returned;
    }
    //Sip, lo veo claro
    // lo ves ahora?

    @Override
@Transactional(readOnly = true)    
    public Donacion findOne(Long id) {
        Donacion resultado = null;
        Optional<DonacionEntity> buscar = donacionRepository.findById(id);
        if(buscar.isPresent()){
            DonacionEntity encontrado = buscar.get();
            resultado = converter.convertToEntityAttribute(encontrado);
        }
        return resultado;
    }

    @Override
    @Transactional
        public Donacion updateOne(Long id, Donacion donacion) {

        Optional<DonacionEntity> found = donacionRepository.findById(id);
        if (found.isPresent()) {
            DonacionEntity d = found.get();
            d.setObservacion(donacion.getObservacion());
            d.setDescripcion(donacion.getDescripcion());
            d.setId(donacion.getId());
            // tu cargas el objeto de la base de datos y le actualizas las cosas con el JSON que te llega
            // OKis!
            // set d atributos con los atributos del nuevo objeto que viene por parametro ---- que cosas

// osea que tengo que coger los atributos y setearselos   -- no me autocompleta pero es eso                     
            // guardar
            return null;
        } else return null;
    }

    @Override
    public Boolean deleteOne(Long id) {
        if(donacionRepository.findById(id).isPresent()) {
            donacionRepository.deleteById(id);
        }
        return true;
    }

    @Override
    public List<Donacion> findAll() {
        List<Donacion> resultado = donacionRepository.findAll().
                stream().
                map(d -> new Donacion(d)).
                collect(Collectors.toList());
        return resultado;
    }
}
