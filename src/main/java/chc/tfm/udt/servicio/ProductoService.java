package chc.tfm.udt.servicio;

import chc.tfm.udt.DTO.Producto;
import chc.tfm.udt.entidades.ProductoEntity;
import chc.tfm.udt.logica.ProductoConverter;
import chc.tfm.udt.repositorios.IProductoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service(value = "ProductoService")
public class ProductoService implements CrudService<Producto> {

    private final Logger LOG = LoggerFactory.getLogger(getClass());
    private IProductoRepository iProductoRepository;
    private ProductoConverter productoConverter;

    public ProductoService(@Qualifier(value = "IProductosRepository") IProductoRepository iProductoRepository,
                           @Qualifier(value = "ProductoConverter") ProductoConverter productoConverter) {
        this.iProductoRepository = iProductoRepository;
        this.productoConverter = productoConverter;
    }

    @Override
    public Producto createOne(Producto producto) {
        ProductoEntity pe = productoConverter.convertToDatabaseColumn(producto);
        ProductoEntity saved = iProductoRepository.save(pe);
        Producto returned = productoConverter.convertToEntityAttribute(saved);
        return returned;
    }

    @Override
    @Transactional (readOnly = true)
    public Producto findOne(Long id) {
        Producto resultado = null;
        Optional<ProductoEntity> buscar = iProductoRepository.findById(id);
        if(buscar.isPresent()){
            ProductoEntity encontrado = buscar.get();
            resultado = productoConverter.convertToEntityAttribute(encontrado);
        }
        return resultado;
    }

    @Override
    public Producto updateOne(Long id, Producto producto) {
        LOG.info("Entramos en update");
        Producto resultado = null;
        Optional<ProductoEntity> buscar = iProductoRepository.findById(id);
        if(buscar.isPresent()){
            ProductoEntity encontrado = buscar.get();

            encontrado.setNombre(producto.getNombre());
            encontrado.setPrecio(producto.getPrecio());
            encontrado.setCreateAt(producto.getCreateAt());

            ProductoEntity guardado = iProductoRepository.save(encontrado);
            resultado = productoConverter.convertToEntityAttribute(guardado);
        }
        return resultado;
    }

    @Override
    public Boolean deleteOne(Long id) {
        if(iProductoRepository.findById(id).isPresent()){
            iProductoRepository.deleteById(id);
            return true;
        }else{
            return false;
        }
    }

    @Override
    public List<Producto> findAll() {
        List<Producto> resultado = iProductoRepository.findAll().
                stream().
                map(entity -> productoConverter.convertToEntityAttribute(entity)).
                collect(Collectors.toList());
        return resultado;
    }
}
