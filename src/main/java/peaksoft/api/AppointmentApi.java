package peaksoft.api;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import peaksoft.entities.Appointment;
import peaksoft.service.AppointmentService;
import peaksoft.service.DepartmentService;
import peaksoft.service.DoctorService;
import peaksoft.service.PatientService;

@Controller
@RequestMapping("/appointments")
@RequiredArgsConstructor
public class AppointmentApi {
    private final AppointmentService appointmentService;
    private final PatientService patientService;

    private final DoctorService doctorService;
    private final DepartmentService departmentService;

    @GetMapping("/{hospitalId}")
    public String getAllAppointments(Model model,
                                     @PathVariable("hospitalId") Long hospitalId){
        model.addAttribute("appointments",appointmentService.findAll(hospitalId));
        return "appointment/savePage";
    }

    @GetMapping("/new/{hospitalId}")
    public String addAppointment(@PathVariable Long hospitalId,
                                 Model model){
        model.addAttribute("newAppointment", new Appointment());
        model.addAttribute("patients",patientService.findAll(hospitalId));
        model.addAttribute("departments", departmentService.findAll(hospitalId));
        model.addAttribute("doctors", doctorService.findAll(hospitalId));
        model.addAttribute(hospitalId);
        return "appointment/newAppointment";
    }


    @PostMapping("/save/{hospitalId}")
    public String save(@PathVariable("hospitalId") Long hospitalId,
                       @ModelAttribute("newAppointment") Appointment appointment){
        appointmentService.save(hospitalId,appointment);
        return "redirect:/appointments/" + hospitalId;
    }



    @GetMapping("/edit/{appointmentId}")
    public String edit(@PathVariable("appointmentId") Long appointmentId, Model model){
        Appointment appointment = appointmentService.getById(appointmentId);
        model.addAttribute("appointment", appointmentService.getById(appointmentId));
        model.addAttribute("hospitalId", appointment.getDoctor().getHospital().getId());
        return "appointment/edit";
    }
    @PutMapping("/{hospitalId}/{appointmentId}/update")
    public String update(@ModelAttribute("appointment")Appointment appointment,
                         @PathVariable("appointmentId")Long appointmentId,
                         @PathVariable("hospitalId")Long hospitalId){
        appointmentService.update(appointmentId, appointment);
        return "redirect:/appointments/" + hospitalId;
    }
    @DeleteMapping("/{hospitalId}/{appointmentId}/delete")
    public String deleteDoctor(@PathVariable("appointmentId")Long appointmentId,
                               @PathVariable("hospitalId")Long hospitalId){
        appointmentService.deleteById(hospitalId, appointmentId);
        return"redirect:/appointments/" + hospitalId;
    }
}
