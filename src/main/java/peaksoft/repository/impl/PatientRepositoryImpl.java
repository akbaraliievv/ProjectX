package peaksoft.repository.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import peaksoft.entities.Patient;
import peaksoft.repository.PatientRepository;

import java.util.List;
@Repository
@Transactional
@RequiredArgsConstructor
public class PatientRepositoryImpl implements PatientRepository {
    @PersistenceContext
    private final EntityManager entityManager;
    @Override
    public List<Patient> findAll(Long hospitalId) {
        return entityManager.createQuery("select p from Patient p join p.hospital f where f.id = :id",
                Patient.class).setParameter("id",hospitalId).getResultList();
    }

    @Override
    public Patient getById(Long patientId) {
        return entityManager.find(Patient.class, patientId);
    }

    @Override
    public void update(Long id,Patient newPatient) {
        Patient patient = entityManager.find(Patient.class, id);
        patient.setFirstName(newPatient.getFirstName());
        patient.setLastName(newPatient.getLastName());
        patient.setEmail(newPatient.getEmail());
        patient.setGender(newPatient.getGender());
        patient.setPhoneNumber(newPatient.getPhoneNumber());
        entityManager.merge(patient);

    }

    @Override
    public void save(Patient patient) {
        entityManager.merge(patient);

    }

    @Override
    public void deleteById(Long id) {
        Patient patient = entityManager.find(Patient.class, id);
        patient.setHospital(null);
        patient.setAppointments(null);
        entityManager.remove(patient);

    }
}
