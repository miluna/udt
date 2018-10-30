package chc.tfm.udt.repositorios;

import chc.tfm.udt.entidades.JugadorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JugadorDAO extends JpaRepository<JugadorEntity, Integer> {
}
