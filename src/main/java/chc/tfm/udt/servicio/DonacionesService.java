package chc.tfm.udt.servicio;

import chc.tfm.udt.DTO.Donacion;
import chc.tfm.udt.DTO.ItemDonacion;
import chc.tfm.udt.DTO.Jugador;
import chc.tfm.udt.entidades.DonacionEntity;
import chc.tfm.udt.entidades.ItemDonacionEntity;
import chc.tfm.udt.logica.DonacionConverter;
import chc.tfm.udt.logica.ItemConverter;
import chc.tfm.udt.logica.JugadorConverter;
import chc.tfm.udt.repositorios.IDonacionRepository;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.transaction.annotation.Transactional;


@Service(value = "DonacionesService")
public class DonacionesService implements CrudService<Donacion> {
    private final Logger log = LoggerFactory.getLogger(getClass());
    private IDonacionRepository donacionRepository;
    private DonacionConverter converter;
    private ItemConverter itemConverter;
    private ItemDonacionesService itemDonacionesService;

    @Autowired
    public DonacionesService(@Qualifier("IDonacionRepository") IDonacionRepository donacionRepository,
                             @Qualifier("DonacionConverter") DonacionConverter converter,
                             @Qualifier("ItemConverter")ItemConverter itemConverter,
                             @Qualifier("ItemDonacionService") ItemDonacionesService itemDonacionesService)
    {
        this.donacionRepository = donacionRepository;
        this.converter = converter;
        this.itemConverter = itemConverter;
        this.itemDonacionesService = itemDonacionesService;
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
        Donacion resultado = null;
        Optional<DonacionEntity> buscar = donacionRepository.findById(id);
        if(buscar.isPresent()){
            DonacionEntity encontrado = buscar.get();

            encontrado.setId(donacion.getId());
            encontrado.setObservacion(donacion.getObservacion());
            encontrado.setDescripcion(donacion.getDescripcion());
            encontrado.setCreateAt(donacion.getCreateAt());
            if(encontrado.getId() != null){
                //encontrado.setJugadorEntity(donacion.getJugador());
            }

            //Encontrar los items de cada donacion
            List<ItemDonacionEntity> itemsDonacion = findItemsDonacionFromDonacion(donacion);
            encontrado.setItems(itemsDonacion);
            // Guardamos todo
           DonacionEntity guardado = donacionRepository.save(encontrado);
           resultado = converter.convertToEntityAttribute(guardado);

        }

        return resultado;
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
    private List<ItemDonacionEntity> findItemsDonacionFromDonacion(Donacion d){
        List<ItemDonacionEntity> itemsEntities = new ArrayList<>();

        d.getItems().forEach(dto -> {
            ItemDonacion e = itemDonacionesService.findOne(dto.getId());
            if(d != null){
                ItemDonacionEntity convertido = itemConverter.convertToDatabaseColumn(e);
                itemsEntities.add(convertido);
            }
        });
        return itemsEntities;
    }
}
