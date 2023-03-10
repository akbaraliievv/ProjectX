package peaksoft.api;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import peaksoft.entities.Patient;
import peaksoft.enums.Gender;
import peaksoft.service.PatientService;

@Controller
@RequiredArgsConstructor
@RequestMapping("/patients")
public class PatientApi {

    private final PatientService patientService;



    @GetMapping("/{id}")
    public String getAllPatients(Model model, @PathVariable Long id) {
        model.addAttribute("patients", patientService.findAll(id));
        model.addAttribute("hospitalId", id);
        return "patient/savePage";
    }


    @GetMapping("/new/{id}")
    public String create(Model model,
                         @PathVariable("id") Long id) {
        model.addAttribute("newPatient", new Patient());
        model.addAttribute("hospitalId", id);
        model.addAttribute("male", Gender.MALE);
        model.addAttribute("female", Gender.FEMALE);
        return "/patient/newPatient";
    }

    @PostMapping("/save/{hospitalId}")
    public String save(@ModelAttribute("newPatient")  Patient patient,
                       BindingResult bindingResult,
                       @PathVariable Long hospitalId, Model model) {

        if (bindingResult.hasErrors()) {
            return "/patient/newPatient";
        }
        patientService.save(hospitalId, patient);
        return "redirect:/patients/" + hospitalId;

    }


    @GetMapping("/edit/{patientId}")
    public String edit(@PathVariable("patientId") Long patientId,
                       Model model) {
        Patient patient = patientService.getById(patientId);
        model.addAttribute("patient", patient);
        model.addAttribute("hospitalId", patient.getHospital().getId());
        model.addAttribute("male", Gender.MALE);
        model.addAttribute("female", Gender.FEMALE);
        return "/patient/edit";
    }

    @PostMapping("/{hospitalId}/{patientId}/update")
    public String update(@ModelAttribute("patient")  Patient patient, BindingResult bindingResult,
                         @PathVariable("patientId") Long patientId,
                         @PathVariable("hospitalId") Long hospitalId) {

        if (bindingResult.hasErrors()) {
            return "/patient/edit";
        }
        patientService.update(patientId, patient);
        return "redirect:/patients/" + hospitalId;
    }


    @GetMapping("/{hospitalId}/{patientId}/delete")
    public String deletePatient(@PathVariable("patientId") Long id,
                                @PathVariable("hospitalId") Long hospitalId) {
        patientService.deleteById(id);
        return "redirect:/patients/" + hospitalId;
    }

}
