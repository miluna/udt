package chc.tfm.udt.servicio;

import chc.tfm.udt.entidades.JugadorEntity;

import java.util.List;

public interface IGestionJugadoresService {

    /**
     * Metodo para crear una lista de jugadores
     * @return
     */
    List listaNombre();

    /**
     * Metodo que Crear un recurso de jugadores en base de datos
     * @param nombre
     * @return
     */
    boolean crearJugador(String nombre);

    /**
     * Metodo que actualiza un usuario en base de datos
     * @param nombre
     * @return
     */
    boolean actualizarJugador(String nombre);

    /**
     * Metodo que elimina un jugador de base de datos
     * @param nombre
     * @return
     */
    String eliminarJugador(String nombre);

}
