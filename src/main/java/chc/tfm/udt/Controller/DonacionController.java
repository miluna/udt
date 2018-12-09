package chc.tfm.udt.Controller;

import chc.tfm.udt.entidades.DonacionEntity;
import chc.tfm.udt.entidades.JugadorEntity;
import chc.tfm.udt.entidades.ProductoEntity;
import chc.tfm.udt.servicio.IJugadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Map;

/**
 * Clase controladora de las donaciones destinadas a los jugadores
 *
 */

@Controller
@RequestMapping("/donacion")
@SessionAttributes("donacion")
public class DonacionController {
    @Autowired
    private IJugadorService jugadorService;

    /**
     * Con este metodo vamos a comunicarnos con el formulario que va hacer posible la inserción en base de datos de los
     * distintos productos que van a estar asociados a un jugador.
     * @param jugadorEntityId La id del jugador al que va a estar asociada la donación por parte del club.
     * @param model Objeto de tipo MAP , para pasar a la vista los distintos objetos de la aplicación
     * @param push Objeto que utilizaremos para mostar al usuario mensajes de información
     * @return
     */
    @GetMapping("form/{jugadorEntityId}")
    public String crear(@PathVariable(  value = "jugadorEntityId")
                                        Integer jugadorEntityId,
                                        Map<String, Object> model,
                                        RedirectAttributes push){
        JugadorEntity jugadorEntity = jugadorService.findOne(jugadorEntityId);
        if(jugadorEntity == null){
            push.addFlashAttribute("error", "El jugador no existe en la base de datos");
            return "redirect/listar";
        }
        DonacionEntity donacionEntity = new DonacionEntity();
        donacionEntity.setJugadorEntity(jugadorEntity);

        model.put("donacion", donacionEntity);
        model.put("titulo", "Crear Donacion.");
        return "donacion/form";
    }
    // tiene un pathVariable , que sería el texto , {term}
    // produces , salida del tipo json
    // @ResponseBody Suprime el cargar una vista de thymeleaft , en vez de eso , toma el return converido a json y lo va a poner en el body de la respuesta.

    /**
     * Metodo que utilizaremos para controlar la devolución de la base de datos de los productos asociados a un jugador.
     * @GetMapping : Mapeamos la misma url que definimos en la llamada AJAX al servidor, pasando como parametro el texto
     * @ResponseBody: Con esta anotación elminamos la carga de la vista con http://www.thymeleaf.org, en vez de eso,
     *                Retornamos convertido a Json para mostrarlo en el body de la respuesta.
     * @PathVariable: La variable que manejamos es el texto que se introduce en el imput de busqueda.
     * @param term
     * @return
     */
    @GetMapping (value = "/cargar-productos/{term}", produces = { "application/json" })
    public @ResponseBody List<ProductoEntity> cargarProducto(@PathVariable String term){
        return jugadorService.findByNombre(term);
    }


}