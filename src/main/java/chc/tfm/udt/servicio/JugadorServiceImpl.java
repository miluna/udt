package chc.tfm.udt.servicio;
import chc.tfm.udt.entidades.JugadorEntity;
import chc.tfm.udt.entidades.ProductoEntity;
import chc.tfm.udt.repositorios.IProductoDAO;
import chc.tfm.udt.repositorios.JugadorDAO;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
/**
 * Clase que usaremos para seguir el patron de diseño "Business Service Facade"
 */
@Service
public class JugadorServiceImpl implements IJugadorService {

    private static final Log logger = LogFactory.getLog(JugadorServiceImpl.class);


    @Autowired
    private JugadorDAO jugadorDAO;
    @Autowired
    private IProductoDAO productoDAO;

    @Override
    @Transactional(readOnly = true)
    public List<JugadorEntity> findAll() {
        return (List<JugadorEntity>)jugadorDAO.findAll();
    }

    @Override
    public Page<JugadorEntity> findAll(Pageable pageable) {
        return jugadorDAO.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public JugadorEntity findOne(Integer id) {
        return jugadorDAO.findById(id).orElse(null);
    }



    @Override
    @Transactional
    public void save(JugadorEntity jugadorEntity) {
        jugadorDAO.save(jugadorEntity);
    }
    @Transactional
    @Override
    public void delete(Integer id) {
        jugadorDAO.deleteById(id);

    }

    @Override
    public List<ProductoEntity> findByNombre(String term) {
        return productoDAO.findByNombreLikeIgnoreCase("%"+term+"%");
    }
}
