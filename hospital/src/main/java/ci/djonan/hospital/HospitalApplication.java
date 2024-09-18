package ci.djonan.hospital;

import ci.djonan.hospital.entities.*;
import ci.djonan.hospital.repositories.ConsultationRepository;
import ci.djonan.hospital.repositories.MedecinRepository;
import ci.djonan.hospital.repositories.PatientRepository;
import ci.djonan.hospital.repositories.RendezVousRepository;
import ci.djonan.hospital.services.IHospitalService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.UUID;
import java.util.stream.Stream;

@SpringBootApplication
public class HospitalApplication  {

	public static void main(String[] args) {
		SpringApplication.run(HospitalApplication.class, args);
	}

	@Bean
	CommandLineRunner start(
			IHospitalService iHospitalService,
			PatientRepository patientRepository,
			MedecinRepository medecinRepository,
			RendezVousRepository rendezVousRepository
	)
	{
		return args -> {

			Stream.of("yacou","Soro","Issouf")
					.forEach(name -> {
						Patient patient = new Patient();
						patient.setNom(name);
						patient.setDataNaissance(new Date());
						patient.setMalade(false);
						iHospitalService.savePatient(patient);
					});

			Stream.of("sedric","maurelle","nafi")
					.forEach(name -> {
						Medecin medecin = new Medecin();
						medecin.setNom(name);
						medecin.setEmail(name+"@gmail.com");
						medecin.setSpecialite(Math.random()>0.5?"Cardio":"Dentiste");
						iHospitalService.saveMedecin(medecin);
					});

			Patient patient = patientRepository.findByNom("yacou");
			Medecin medecin = medecinRepository.findByNom("sedric");
			RendezVous rendezVous = new RendezVous();
			rendezVous.setId(UUID.randomUUID().toString());
			rendezVous.setDate(new Date());
			rendezVous.setPatient(patient);
			rendezVous.setMedecin(medecin);
			rendezVous.setStatusRDV(StatusRDV.PENDIND);
			iHospitalService.saveRendezVous(rendezVous);

			RendezVous rendezVous1 = rendezVousRepository.findAll().getFirst();

			Consultation consultation = new Consultation();
			consultation.setDateConsultation(new Date());
			consultation.setRapport("Vous être en bonne santé");
			consultation.setRendezVous(rendezVous1);
			iHospitalService.saveConsultation(consultation);


		};
}
}
