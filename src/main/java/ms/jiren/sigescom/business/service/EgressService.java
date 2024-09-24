package ms.jiren.sigescom.business.service;

import lombok.RequiredArgsConstructor;
import ms.jiren.sigescom.business.repository.dao.EgressDao;
import ms.jiren.sigescom.business.repository.entity.Egress;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EgressService {
    private final EgressDao egressDao;

    public List<Egress> getAll() {
        return egressDao.findAll();
    }

    public Egress getById(int id) {
        return egressDao.findById(id).orElse(null);
    }

    public Egress save(Egress egress) {
        if (egress.getAmount() >= 0){
            return egressDao.save(egress);

        }
        return null;
    }

    public Egress update(int id, Egress egress) {
        Egress egressUpdated = getById(id);

        Egress.builder()
                .date(egress.getDate())
                .amount(egress.getAmount())
                .description(egress.getDescription())
                .category(egress.getCategory())
                .build();

        return egressDao.save(egress);
    }

    public boolean delete(int id) {
        if (egressDao.existsById(id)) {
            egressDao.deleteById(id);
            return true;
        }
        return false;
    }
}
