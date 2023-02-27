package peaksoft.repository.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import peaksoft.entities.Appointment;
import peaksoft.entities.Hospital;
import peaksoft.repository.AppointmentRepository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class AppointmentRepositoryImpl implements AppointmentRepository {
    @PersistenceContext
    private final EntityManager entityManager;


    @Override
    public List<Appointment> findAll(Long hospitalId) {
        return entityManager.createQuery("select a from Hospital h join h.appointments a where h.id=:id", Appointment.class)
                .setParameter("id", hospitalId)
                .getResultList();
    }



    @Transactional
    @Override
    public Appointment getById(Long appointmentId) {
        return entityManager.find(Appointment.class, appointmentId);
    }

    @Override
    public void update(Long appointmentId, Appointment appointment) {
        Appointment appointment1 = entityManager.find(Appointment.class, appointmentId);
        appointment1.setDate(appointment.getDate());
        entityManager.merge(appointment1);
    }


    @Override
    public Appointment save(Appointment appointment) {
        entityManager.persist(appointment);
        return appointment;
    }

    @Override
    public void deleteById(Hospital hospital, Long appointmentId) {
        entityManager.merge(hospital);
        entityManager.createQuery("delete from Appointment where id=:id").setParameter("id", appointmentId).executeUpdate();
    }
}
