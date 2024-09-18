package ci.djonan.hospital.services;

import ci.djonan.hospital.entities.Consultation;
import ci.djonan.hospital.entities.Medecin;
import ci.djonan.hospital.entities.Patient;
import ci.djonan.hospital.entities.RendezVous;
import ci.djonan.hospital.repositories.ConsultationRepository;
import ci.djonan.hospital.repositories.MedecinRepository;
import ci.djonan.hospital.repositories.PatientRepository;
import ci.djonan.hospital.repositories.RendezVousRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class IHospitalServiceImpl implements IHospitalService {
    private PatientRepository patientRepository;
    private MedecinRepository medecinRepository;
    private ConsultationRepository consultationRepository;
    private RendezVousRepository rendezVousRepository;

    public IHospitalServiceImpl(
            PatientRepository patientRepository,
            MedecinRepository medecinRepository,
            ConsultationRepository consultationRepository,
            RendezVousRepository rendezVousRepository
    ) {
        this.patientRepository = patientRepository;
        this.medecinRepository = medecinRepository;
        this.consultationRepository = consultationRepository;
        this.rendezVousRepository = rendezVousRepository;
    }

    public Patient savePatient(Patient patient) {
        return patientRepository.save(patient);
    }

    public Medecin saveMedecin(Medecin medecin) {
        return medecinRepository.save(medecin);
    }

    public Consultation saveConsultation(Consultation consultation) {
        return consultationRepository.save(consultation);
    }

    public RendezVous saveRendezVous(RendezVous rendezVous) {
        return rendezVousRepository.save(rendezVous);
    }
}
