package peaksoft.service;

import peaksoft.entities.Hospital;

import java.util.List;

public interface HospitalService {
    List<Hospital> findAll();

    void save(Hospital hospital);

    void deleteById(Long hospitalId);

    Hospital getById(Long hospitalId);

    void update(Hospital newHospital);
}
