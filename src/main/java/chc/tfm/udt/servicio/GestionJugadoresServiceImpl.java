package chc.tfm.udt.servicio;
import chc.tfm.udt.DTO.Jugador;
import chc.tfm.udt.entidades.JugadorEntity;
import chc.tfm.udt.repositorios.JugadorDAO;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GestionJugadoresServiceImpl implements IGestionJugadoresService {

    private static final Log logger = LogFactory.getLog(GestionJugadoresServiceImpl.class);

    @Autowired
    JugadorDAO jugadorDAO;
    @Transactional(readOnly = true)
    @Override
    public List listaNombre() {/*
        logger.info("Lista de nombres ");
        List<JugadorEntity> lista = jugadorDAO.findAll();
        List<String> nombres = new ArrayList<>();
        for(JugadorEntity j : lista) {
            nombres.add(j.getNombre());*/
        List<Jugador> resultado = jugadorDAO.findAll()
                .stream()
                .map(e -> new Jugador(e))
                .collect(Collectors.toList());
        return resultado;
        }
         //return nombres ;
        //LAMDA
        /*return jugadorDAO.findAll().
                stream().
                map(jugadorEntity -> jugadorEntity.getNombre()).
                collect(Collectors.toList());*/

    @Override
    public boolean crearJugador(String nombre) {
        JugadorEntity jugadorEntity = new JugadorEntity(0,"pepe","Federico","garcia","660566344","jose@gja.com","tomas","un dia","40","afroameri","hoy","69");
       jugadorDAO.save(jugadorEntity);
        return true;
    }

    @Override
    public boolean actualizarJugador(String nombre) {
        return false;
    }

    @Override
    public String eliminarJugador(String nombre) {
        return null;
    }
}
