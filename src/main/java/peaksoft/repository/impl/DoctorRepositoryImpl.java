package peaksoft.repository.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import peaksoft.entities.Department;
import peaksoft.entities.Doctor;
import peaksoft.entities.Hospital;
import peaksoft.repository.DoctorRepository;

import java.util.List;
@Repository
@Transactional
@RequiredArgsConstructor
public class DoctorRepositoryImpl implements DoctorRepository{
    @PersistenceContext
    private final EntityManager entityManager;
    @Override
    public List<Doctor> getAllDoctors(Long id) {
        return entityManager.createQuery("select d from Doctor d join d.hospital h where h.id= :id",
                Doctor.class).setParameter("id",id).getResultList();
    }

    @Override
    public void save(Doctor newDoctor) {
        entityManager.merge(newDoctor);
    }

    @Override
    public Doctor getById(Long doctorId) {
        return entityManager.find(Doctor.class, doctorId);
    }

    @Override
    public void update(Long doctorId,Doctor newDoctor) {
        Doctor oldDoctor = entityManager.find(Doctor.class, doctorId);
        oldDoctor.setFirstName(newDoctor.getFirstName());
        oldDoctor.setLastName(newDoctor.getLastName());
        oldDoctor.setEmail(newDoctor.getEmail());
        oldDoctor.setPosition(newDoctor.getPosition());
        entityManager.merge(oldDoctor);
    }





    @Override
    public void assignDoctor(Long departmentId, Long doctorId) {
        Department department = entityManager.find(Department.class, departmentId);
        Doctor doctor = entityManager.find(Doctor.class, doctorId);
        doctor.addDepartment(department);
        department.addDoctor(doctor);
        entityManager.merge(department);
        entityManager.merge(doctor);
    }
    @Override
    public void deleteById(Long id) {
        Doctor doctor = entityManager.find(Doctor.class, id);
        doctor.setHospital(null);
        doctor.setAppointments(null);
        doctor.setDepartments(null);
        entityManager.remove(doctor);


    }
}
