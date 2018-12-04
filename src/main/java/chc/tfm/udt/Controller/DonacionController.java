package chc.tfm.udt.Controller;

import chc.tfm.udt.entidades.DonacionEntity;
import chc.tfm.udt.entidades.JugadorEntity;
import chc.tfm.udt.servicio.IJugadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Map;

@Controller
@RequestMapping("/donacion")
@SessionAttributes("donacion")
public class DonacionController {
    @Autowired
    private IJugadorService jugadorService;
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
}