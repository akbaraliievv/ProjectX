package peaksoft.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import peaksoft.entities.Hospital;
import peaksoft.repository.HospitalRepository;
import peaksoft.service.HospitalService;

import java.util.List;
@Service
@RequiredArgsConstructor
@Transactional
public class HospitalServiceImpl implements HospitalService {

    private final HospitalRepository hospitalRepository;
    @Override
    public List<Hospital> findAll() {
        return hospitalRepository.findAll();
    }

    @Override
    public void save(Hospital hospital) {
        hospitalRepository.save(hospital);
    }

    @Override
    public Hospital getById(Long hospitalId) {
        return hospitalRepository.getById(hospitalId);
    }

    @Override
    public void update( Hospital newHospital) {
        hospitalRepository.update(newHospital);
    }


    @Override
    public void deleteById(Long hospitalId) {
        hospitalRepository.deleteById(hospitalId);
    }
}
