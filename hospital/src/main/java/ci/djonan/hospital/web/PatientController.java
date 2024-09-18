package ci.djonan.hospital.web;

import ci.djonan.hospital.entities.Patient;
import ci.djonan.hospital.repositories.PatientRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PatientController {
    private PatientRepository patientRepository;

    public PatientController(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }
@GetMapping("/patient/all")
    List<Patient> patientList(){
        return patientRepository.findAll();
    }
}
