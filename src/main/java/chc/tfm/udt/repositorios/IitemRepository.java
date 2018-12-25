package chc.tfm.udt.repositorios;

import chc.tfm.udt.entidades.ItemDonacionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("ItemRepository")
public interface IitemRepository extends JpaRepository<ItemDonacionEntity, Long> {
}
