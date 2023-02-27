package peaksoft.repository;

import peaksoft.entities.Hospital;
import peaksoft.entities.Patient;

import java.util.List;

public interface PatientRepository {
    List<Patient> findAll(Long id);

    Patient getById(Long patientId);

    void update(Long id,Patient newPatient);

    void save(Patient patient);

    void deleteById(Long id);
}
