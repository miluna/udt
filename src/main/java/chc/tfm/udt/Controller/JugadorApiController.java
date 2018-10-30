package chc.tfm.udt.Controller;

import chc.tfm.udt.DTO.Jugador;
import chc.tfm.udt.entidades.JugadorEntity;
import chc.tfm.udt.servicio.IGestionJugadoresService;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.http.ResponseEntity;

import java.util.List;


@RestController("/")
public class JugadorApiController implements CrudController {

    private static final Log logger = LogFactory.getLog(JugadorApiController.class);

    @Autowired
    IGestionJugadoresService gestionJugadores;


    @GetMapping(value = "/jugadores")

    public ResponseEntity<List> recuperarTodos(Model model) {
        model.addAttribute("clientes",gestionJugadores.listaNombre());
        return new ResponseEntity<>(gestionJugadores.listaNombre(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity crearUno(Object o) {
        return null;
    }

    @Override
    public ResponseEntity recuperarUno(Integer id) {
        return null;
    }

    @Override
    public ResponseEntity actualizarUno(Integer id) {
        return null;
    }

    @Override
    public ResponseEntity borrarUno(Integer id) {
        return null;
    }
    /*
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<String>> getJugadores(){
        gestionJugadores.listaNombre();
        return new ResponseEntity<>(gestionJugadores.listaNombre(), HttpStatus.OK);
    }
    @RequestMapping(value = "/{nombre}",method = RequestMethod.POST)
    public String crearJugador(@PathParam(value = "nombre") String nombre){
        gestionJugadores.crearJugador(nombre);
        return "hola "+ nombre;
    }*/





}

