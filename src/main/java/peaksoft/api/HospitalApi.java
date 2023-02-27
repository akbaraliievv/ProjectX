package peaksoft.api;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import peaksoft.entities.Hospital;
import peaksoft.service.HospitalService;


@Controller
@RequiredArgsConstructor
@RequestMapping("/hospitals")
public class HospitalApi {

    private final HospitalService hospitalService;

    @GetMapping()
    public String getAllHospitals(Model model){
        model.addAttribute("hospitals", hospitalService.findAll());
        return "hospital/savePage";
    }

    @GetMapping("/new")
    public String create(Model model){
        model.addAttribute("newHospital", new Hospital());
        return "hospital/newHospital";
    }


    @PostMapping("/save")
    public String save(@ModelAttribute("newHospital") Hospital hospital){
        hospitalService.save(hospital);
        return "redirect:/hospitals";
    }

    @DeleteMapping("{hospitalId}/delete")
    public String deleteHospital(@PathVariable("hospitalId") Long hospitalId){
        hospitalService.deleteById(hospitalId);
        return "redirect:/hospitals";
    }

    @GetMapping("{hospitalId}/edit")
    public String edit(@PathVariable("hospitalId") Long hospitalId, Model model){
        model.addAttribute("hospital", hospitalService.getById(hospitalId));
        return "hospital/edit";
    }

    @PostMapping("/{id}/update")
    public String update(@ModelAttribute("hospital") Hospital hospital){
        hospitalService.update(hospital);
        return "redirect:/hospitals";
    }

    @GetMapping("/profile/{hospitalId}")
    public String profile(@PathVariable("hospitalId") Long hospitalId,
                          Model model){
        model.addAttribute("hospitals",hospitalService.getById(hospitalId));
        return "hospital/details";
    }
}
