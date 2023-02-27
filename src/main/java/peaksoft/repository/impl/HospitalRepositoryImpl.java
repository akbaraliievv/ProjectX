package peaksoft.repository.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import peaksoft.entities.Hospital;
import peaksoft.repository.DepartmentRepository;
import peaksoft.repository.HospitalRepository;

import java.util.List;
@Repository
@Transactional
@RequiredArgsConstructor
public class HospitalRepositoryImpl implements HospitalRepository{
    @PersistenceContext
    private final EntityManager entityManager;
    @Override
    public List<Hospital> findAll() {
        return entityManager.createQuery("select h from Hospital h",Hospital.class).getResultList();
    }

    @Override
    public void save(Hospital hospital) {
        entityManager.persist(hospital);
    }

    @Override
    public Hospital getById(Long hospitalId) {
        return entityManager.find(Hospital.class, hospitalId);
    }

    @Override
    public void update(Hospital newHospital) {
        entityManager.merge(newHospital);
    }


    @Override
    public void deleteById(Long hospitalId) {
        entityManager.remove(entityManager.find(Hospital.class, hospitalId));
    }
}
