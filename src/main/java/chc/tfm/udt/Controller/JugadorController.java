package chc.tfm.udt.Controller;


import chc.tfm.udt.entidades.JugadorEntity;
import chc.tfm.udt.servicio.IJugadorService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.Map;

@SessionAttributes("jugadorEntity")
@Controller
public class JugadorController {

    private static final Log logger = LogFactory.getLog(JugadorController.class);

    @Autowired
    private IJugadorService jugadorService;

    /**
     * USAMOS ESTE METODO PARA LISTAR TODOS LOS JUGADORES QUE ESTAN EN BASE DE DATOS.
     * @param model USAMOS ESTE OBJETO PARA PASAR DATOS A LA VISTA.
     * @return
     */
    @RequestMapping(value = "/listar", method = RequestMethod.GET)
    public String listar(Model model){
        model.addAttribute("titulo","listado de jugadores");
        model.addAttribute("jugadores",jugadorService.findAll());
        return "listar";
    }

    /**
     * Metodo que se encarga de mostrar el formulario al usuario.
     * @param model
     * @return
     */
    @RequestMapping(value = "/form")
    public String crear(Map<String, Object> model){
        JugadorEntity jugadorEntity = new JugadorEntity();
        model.put("jugadorEntity", jugadorEntity);
        model.put("titulo","Formulario de jugador");

        return "form";
    }

    /**
     * Metodo que utilizaremos para editar los jugadores, va mapeado al formulario de entrada.
     * le psamos un id con PathVariable.
     * @param id
     * @param model
     * @param push Objeto que usamos para mostrar mensajes de error o correcto al usuario por pantalla , al realizar 1 acción
     * @return
     */
    @RequestMapping(value = "/form/{id}")
    public String editar (@PathVariable(value = "id") Integer id, Map<String , Object> model,RedirectAttributes push){
        JugadorEntity jugadorEntity = null;
        //Comprobamos que el id es superior a 0, si no no existe el jugador.
        if(id >0){
            jugadorEntity = jugadorService.findOne(id);
            // Si el jugador es null , no existe en base de datos.
            if(jugadorEntity == null){
                push.addFlashAttribute("error","El jugador no existe ");
                return "redirect:/listar";
            }
            // Si el id introducido es 0 se lo mostramos como error.
        }else{
            push.addFlashAttribute("error","El id no puede ser 0 ");
            return "redirect:/listar";
        }
        model.put("jugadorEntity", jugadorEntity);
        model.put("titulo","Editar Jugador");
        return "form";
    }
    /**
     * Metodo para procesar el submit, almacenandlo en base de datos.
     * Recibe el objeto jugador entity y lo guardamos con save.
     * @Valid habilita la validación en el objeto mapeado al form
     * MODEL: USAMOS ESTE OBJETO PARA PASAR DATOS A LA VISTA CON .ADDATRIBUTE
     * SessionStatus , lo utilizamos para controlar la session que hemos creado.
     * BINDINGRESULT usamos este objeto para comprobar que se cumplen los requisitos de los campos y mostrar 1 mensaje.
     * REDIRECTATRIBUTES : USAMOS ESTE OBJETO PARA MOSTRAR AL USUARIO UN MENSAJE DE SUCCESS O ERROR AL REALIZAR 1 ACCIÓN.
     * SESSIONSTATUS : USAMOS ESTE OBJETO PARA OBTENER UN STATUS DE LA SESIÓN Y PODER TRABAJAR CON EL ID DE FORMA SEGURA.
     *
     */
    @RequestMapping(value = "/form", method = RequestMethod.POST)
    public String guardar(@Valid JugadorEntity jugadorEntity, BindingResult result, Model model, RedirectAttributes push, SessionStatus status){
        //Si el resultado contiene errores , retornamos al formulario
        if(result.hasErrors()) {
            model.addAttribute("titulo", "Formulario de jugador");
            return "form";
        }
        // CONDICIONAL QUE INDICA SI EL ID ES DISTINTO DE 0 SE ESTA EDITANDO Y SI ES 0 SE ESTA CREANDO PARA MOSTRAR EL MENSAJE
        String mensajePush = (jugadorEntity.getId() != 0)? "Jugador Editado con exito" : "Jugador creado con exito";

        jugadorService.save(jugadorEntity);
        //CERRAMOS LA SESSIÓN UNA VEZ COMPLETADO EL PROCESO DE GUARDADO.
        status.setComplete();
        push.addFlashAttribute("success", mensajePush);
        return "redirect:listar";
    }

    /**
     * Metodo que utilizaremos para llamar al servicio de eliminar, recogiendo el id de la vista.
     * @param id
     * REDIRECTATRIBUTES : USAMOS ESTE OBJETO PARA MOSTRAR AL USUARIO UN MENSAJE DE SUCCESS O ERROR AL REALIZAR 1 ACCIÓN.
     * @return
     */
    @RequestMapping(value = "/eliminar/{id}")
    public String eliminar(@PathVariable(value = "id") Integer id, RedirectAttributes push){
        if(id > 0){
            jugadorService.delete(id);
            push.addFlashAttribute("success","Jugador eliminado con exito");
        }
        return "redirect:/listar";
    }
}

