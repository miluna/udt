package chc.tfm.udt.repositorios;

import chc.tfm.udt.entidades.ProductoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository(value = "IProductosRepository")
public interface IProductoRepository extends JpaRepository<ProductoEntity, Long> {
}
