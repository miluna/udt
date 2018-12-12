package chc.tfm.udt.servicio;

import chc.tfm.udt.DTO.Donacion;
import org.springframework.stereotype.Service;
import java.util.List;


@Service(value = "DonacionesService")
public class DonacionesService implements CrudService<Donacion> {

    @Override
    public Donacion createOne(Donacion donacion) {
        return null;
    }

    @Override
    public Donacion findOne(Long id) {
        return null;
    }

    @Override
    public Donacion updateOne(Long id, Donacion donacion) {
        return null;
    }

    @Override
    public Boolean deleteOne(Long id) {
        return null;
    }

    @Override
    public List<Donacion> findAll() {
        return null;
    }
}
