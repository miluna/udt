
package chc.tfm.udt.utils.paginator;

import org.springframework.data.domain.Page;

import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase en la que vamos a configurar nustro paginador a nuestro gusto.
 * @param <T>
 */
public class PageRender<T> {

    private String url;
    private Page<T> page;
    private List<PageItem> paginas; // Lista de paginas y le pasamos la clase donde definimos el numero de la pagina y si es la actual
    private int totalPaginas;
    private int numElementosPorPagina;
    private int paginaActual;

    /**
     * INIZIALIZAMOS  por contrustor las variable.
     * Con el objeto Page , recogemos datos de los elementos de la lista
     * page.getSize recogemos todos los elementos de la pagina y asignamos a numero de lementos por pagian.
     * page.getTotalPages: hace la llamada para recuperar el total de paginas que tenemos
     *  page.getNumbre + 1 : hace referencia a la pagina acutal que vamos a sumarle  1 para que siempre muestre 1 mas
     * @param url
     * @param page
     */
    public PageRender(String url, Page<T> page){
        this.url    = url;
        this.page   = page;
        this.paginas = new ArrayList<PageItem>();
        numElementosPorPagina = page.getSize();
        totalPaginas  = page.getTotalPages();
        paginaActual = page.getNumber() +1 ; // Default es 0 por eso se pone + 1
        int desde, hasta;
        //Si el total de paginas es menor al numero de elementos se mostrara el total de paginas. si hay 10 paginas se muestan las 10
        if(totalPaginas <= numElementosPorPagina){
            desde = 1;
            hasta = totalPaginas;
            //navegamos por rangos
        }else{
            //Si tenemos muchos elementos  vamos corriendo el rango de paginas para no mostrar todas a la vz.
            if(paginaActual <= numElementosPorPagina/2) {
                desde = 1;
                hasta = numElementosPorPagina;
                //Calculamos el rango final.
            }else if (paginaActual >= totalPaginas - numElementosPorPagina /2){
                desde = totalPaginas - numElementosPorPagina + 1 ;
                hasta =  numElementosPorPagina;
            }else{
                desde = paginaActual - numElementosPorPagina / 2;
                hasta = numElementosPorPagina;
            }
        }
        //recorremos el hasta y vamos a decir si es menor de los items, agregamos cada elemento con su numero y el desde + i
        for(int i=0; i< hasta; i++){
            paginas.add(new PageItem(desde + i, paginaActual == desde + i));
        }
    }

    public String getUrl() {
        return url;
    }

    public List<PageItem> getPaginas() {
        return paginas;
    }

    public int getTotalPaginas() {
        return totalPaginas;
    }

    public int getPaginaActual() {
        return paginaActual;
    }
    public  boolean isFirst(){
        return page.isFirst();
    }
    public boolean isLast(){
        return page.isLast();
    }
    public boolean isHasNext(){
        return page.hasNext();
    }
    public boolean isHasPrevious(){
        return page.hasPrevious();
    }
}
