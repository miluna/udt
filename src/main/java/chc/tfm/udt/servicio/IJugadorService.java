package chc.tfm.udt.servicio;

import chc.tfm.udt.entidades.JugadorEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IJugadorService {

    /**
     * Metodo para buscar todos los Jugadores
     * @return
     */
    List<JugadorEntity> findAll();

    Page<JugadorEntity> findAll(Pageable pageable);


    /**
     * Metodo que utilizaremos para almacenar 1 jugador en la base de datos
     * @param jugadorEntity
     */
    void save(JugadorEntity jugadorEntity);

    /**
     * Metodo que utilizaremos para buscar por un id
     * @param id
     * @return
     */
    JugadorEntity findOne(Integer id);

    /**
     * Metodo que utilizaremos para borrar por id haciendo uso del findOne
     * @param id
     */
    void delete (Integer id);

}