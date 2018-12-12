package chc.tfm.udt.servicio;

import chc.tfm.udt.DTO.Donacion;
import chc.tfm.udt.entidades.DonacionEntity;
import chc.tfm.udt.logica.Converter;
import chc.tfm.udt.repositorios.IDonacionDAO;
import chc.tfm.udt.repositorios.IDonacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;



@Service(value = "DonacionesService")
public class DonacionesService implements CrudService<Donacion> {

    private IDonacionRepository donacionRepository;
    private Converter<DonacionEntity, Donacion> converter;
    
    @Autowired
    private DonacionesService(@Qualifier("IDonacionRepository") IDonacionRepository donacionRepository,
       @Qualifier("DonacionConverter") Converter<DonacionEntity, Donacion> converter){
        this.donacionRepository = donacionRepository;
        this.converter = converter;
    }

    @Override
    public Donacion createOne(Donacion donacion) {
//Basicamente es lo unico que me pidio gustavo , que hiciera los convertidores pero no a la chusca
        // usar converter para crear el entity -> el resultado no tendrá id
        //esa clase de donde la has sacado ^.-
        DonacionEntity d = converter.convertEntity(donacion);
        // el save devuelve el entity igual, pero con el ID, tu cuando creas lo creas con id=null
        DonacionEntity saved = donacionRepository.saveAndFlush(d);
        // ahora conviertes a DTO el que te devuelve el repositorio, que tiene el id, 
        // y ya lo devuelves con todos los datos
        //
        Donacion returned = converter.convertDTO(saved);
        return returned;
    }
    //Sip, lo veo claro
    // lo ves ahora?

    @Override
@Transactional(readOnly = true)    
    public Donacion findOne(Long id) {
        return donacionRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
        public Donacion updateOne(Long id, Donacion donacion) {

        Optional<DonacionEntity> found = donacionRepository.findById(id);
        if (found.isPresent()) {
            DonacionEntity d = found.get();
            // tu cargas el objeto de la base de datos y le actualizas las cosas con el JSON que te llega
            // OKis!
            // set d atributos con los atributos del nuevo objeto que viene por parametro ---- que cosas
            d.setDescripcion(donacion.getDescripcion());
// osea que tengo que coger los atributos y setearselos   -- no me autocompleta pero es eso                     
            // guardar
            return donacionRepository.save(d);                        
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
        return donacionRepository.findAll();
    }
}
