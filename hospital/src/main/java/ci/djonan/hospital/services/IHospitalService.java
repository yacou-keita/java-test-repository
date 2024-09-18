package ci.djonan.hospital.services;

import ci.djonan.hospital.entities.Consultation;
import ci.djonan.hospital.entities.Medecin;
import ci.djonan.hospital.entities.Patient;
import ci.djonan.hospital.entities.RendezVous;

public interface IHospitalService {
    Patient savePatient(Patient patient);
    Medecin saveMedecin(Medecin medecin);
    Consultation saveConsultation(Consultation consultation);
    RendezVous saveRendezVous(RendezVous rendezVous);
}
