package chc.tfm.udt.config;

import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Clase que implimenta a WebMvcConfigurer , para poder complementar y modificar la configuración
 */
@Configuration
public class MvcConfig implements WebMvcConfigurer {


    /**
     * Metodo que lo utilizaremos para manejar los recursos
     * Agregamos la ruta donde estarán los recursos externos , y le vamos a indicar a spring donde está y que lo considere
     * como un directorio del proyecto , fuera del JAR.
     * @param registry

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/uploads/**")
                .addResourceLocations("file:/D:/worktfm//uploads");

    }
     */
}
