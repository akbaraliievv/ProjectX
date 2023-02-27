package peaksoft.repository;

import peaksoft.entities.Hospital;
import peaksoft.service.HospitalService;

import java.util.List;

public interface HospitalRepository {
    List<Hospital> findAll();

    void save(Hospital hospital);

    void deleteById(Long hospitalId);

    Hospital getById(Long hospitalId);


    void update(Hospital newHospital);
}
