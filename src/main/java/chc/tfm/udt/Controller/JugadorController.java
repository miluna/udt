package chc.tfm.udt.Controller;


import chc.tfm.udt.entidades.JugadorEntity;
import chc.tfm.udt.servicio.IJugadorService;
import chc.tfm.udt.utils.paginator.PageRender;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

@SessionAttributes("jugadorEntity")
@Controller
public class JugadorController {

    private static final Log logger = LogFactory.getLog(JugadorController.class);

    @Autowired
    private IJugadorService jugadorService;

    /**
     *
     * @param id El identificador del cliente
     * @param model Pasamos datos a la vista con el objeto MAP, clave Valor.
     * @param push REDIRECTATTributes para mensajes PUSH en la vista.
     * @return
     */
    @GetMapping(value = "/ver/{id}")
    public String ver(@PathVariable(value = "id") Integer id , Map<String,Object> model, RedirectAttributes push){
        JugadorEntity jugadorEntity = jugadorService.findOne(id);
        if(jugadorEntity == null){
            push.addFlashAttribute("error", "El jugador no existe en la base de datos");
            return "redirect/listar";
        }
        model.put("jugadorEntity",jugadorEntity);
        model.put("titulo","Detalle jugador: " + jugadorEntity.getNombre());
        return "ver";
    }

    /**
     * USAMOS ESTE METODO PARA LISTAR TODOS LOS JUGADORES QUE ESTAN EN BASE DE DATOS.
     * @param model USAMOS ESTE OBJETO PARA PASAR DATOS A LA VISTA.
     * CREAMOS EL OBJETO PAGEABLE Y LE PASAMOS UN NUMERO DE PAGINAS PARA MOSTRAR, LE ASIGNAMOS UN VALOR POR DEFECTO Y UNA VARIABLE.
     *
     * @return
     */
    @RequestMapping(value = "/listar", method = RequestMethod.GET)
    public String listar(@RequestParam(name = "page", defaultValue = "0") int page, Model model){
        Pageable pageRequest = PageRequest.of(page,5);
        Page<JugadorEntity> jugadorEntityPage = jugadorService.findAll(pageRequest);
        PageRender<JugadorEntity> pageRender = new PageRender<>("/listar", jugadorEntityPage);

        model.addAttribute("titulo","listado de jugadores");
        model.addAttribute("jugadores", jugadorEntityPage);
        model.addAttribute("page",pageRender);
        return "listar";
    }

    /**
     * Metodo que se encarga de conectar el formulario de la vista con el back.
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
        //Comprobamos que el id es superior a 0, si no, no existe el jugador.
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
     * @RequestParam(FILE) MultipartFile, Inyectamos de forma automatica un archivo que estamos enviando al servidor.
     *
     */
    @RequestMapping(value = "/form", method = RequestMethod.POST)
    public String guardar(@Valid    JugadorEntity jugadorEntity, BindingResult result,
                          Model model, RedirectAttributes push,
                          SessionStatus status, @RequestParam("file")MultipartFile foto){
        //Si el resultado contiene errores , retornamos al formulario
        if(result.hasErrors()) {
            model.addAttribute("titulo", "Formulario de jugador");
            return "form";
        }
        //Preguntamos que si no esta vacio el objeto foto.
        if(!foto.isEmpty()){
            /*Objeto que nos permite recuperar la ruta donde van a estar los archivos para usarlo desde el propio proyecto*/
                Path directorioRecursos = Paths.get("src//main//resources//static/uploads");
              //Creamos un string para poder trabajar con las imagenes en este directorio
            String rootPath = directorioRecursos.toFile().getAbsolutePath();
            //Creamos un STRING con una ruta externa al proyecto
            //String rootPath =
            try {
                //recuperamos los bytes de la foto para ajustarlo al limite de 10mb que hemos marcado en propiedades
                byte[] bytes = foto.getBytes();
                // recuperamos la URI completa de la foto.
                Path rutaCompleta = Paths.get(rootPath + "//" + foto.getOriginalFilename());
                // Escribimos la ruta completa y los bytes en el directorio UPLOAD.
                Files.write(rutaCompleta,bytes);
                //Mostramos un mensaje al usuario.
                push.addFlashAttribute("info", "Ha sido subida correctamente," + foto.getOriginalFilename()+"");
                //Pasamos la foto a la entity para que quede almacenada en base de datos.
                jugadorEntity.setFoto(foto.getOriginalFilename());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        // CONDICIONAL QUE INDICA SI EL ID ES DISTINTO DE 0 SE ESTA EDITANDO Y SI ES 0 SE ESTA CREANDO PARA MOSTRAR EL MENSAJE
        String mensajePush = (jugadorEntity.getId() != null)? "Jugador Editado con exito" : "Jugador creado con exito";

        jugadorService.save(jugadorEntity);
        //CERRAMOS LA SESSIÓN UNA VEZ COMPLETADO EL PROCESO DE GUARDADO.
        status.setComplete();
        push.addFlashAttribute("success", mensajePush);
        return "redirect:listar";
    }

    /**
     * Metodo que utilizaremos para llamar al servicio de eliminar, recogiendo el id de la vista.
     * @param id
     * REDIRECTATRIBUTES : USAMOS ESTE OBJETO PARA MOSTRAR AL USUARIO UN MENSAJE POR PANTALLA DE SUCCESS O ERROR AL REALIZAR 1 ACCIÓN.
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

