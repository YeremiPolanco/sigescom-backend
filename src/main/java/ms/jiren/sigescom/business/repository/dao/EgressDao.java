package ms.jiren.sigescom.business.repository.dao;

import ms.jiren.sigescom.business.repository.entity.Egress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EgressDao extends JpaRepository<Egress, Integer> {
}
