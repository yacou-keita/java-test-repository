package ci.djonan.jpa_ap;

import ci.djonan.jpa_ap.entities.Patient;
import ci.djonan.jpa_ap.repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.awt.print.Pageable;
import java.util.Date;
import java.util.List;

@SpringBootApplication
public class JpaApApplication implements CommandLineRunner {

	@Autowired
	private PatientRepository patientRepository;

	public static void main(String[] args) {
		SpringApplication.run(JpaApApplication.class, args);
	}


	public void run(String... args) throws Exception {
		for (int i = 0; i < 10; i++) {
			patientRepository.save(new Patient(null,"yacou",new Date(), Math.random() > 0.5,(int)(Math.random() * 100)));
		}


		Page<Patient> patients = patientRepository.findAll(PageRequest.of(1,10));
		System.out.println("Total page: " + patients.getTotalPages());
		System.out.println("Total element: " + patients.getTotalElements());
		System.out.println("Numero de page: " + patients.getNumber());
		List<Patient> content = patients.getContent();
		Page<Patient> patientMalade = patientRepository.findByMalade(true,  PageRequest.of(0,5));
		List<Patient> patientList = patientRepository.cherchePatientWithMinScore("%y%",40);
		System.out.println("patientList size" + patientList.size());
		System.out.println("patientList " + patientList);

		patientList.forEach(patient -> {
			System.out.println("===== patientList =========");
			System.out.println(patient.getId());
			System.out.println(patient.getNom());
			System.out.println(patient.getDateNaissance());
			System.out.println(patient.isMalade());
			System.out.println(patient.getScore());
		});


//		patientMalade.forEach(patient -> {
//			System.out.println("======= Malade ==============");
//			System.out.println(patient.getId());
//			System.out.println(patient.getNom());
//			System.out.println(patient.getDateNaissance());
//			System.out.println(patient.isMalade());
//			System.out.println(patient.getScore());
//		});

		patients.forEach(patient -> {
			System.out.println("===== All =========");
			System.out.println(patient.getId());
			System.out.println(patient.getNom());
			System.out.println(patient.getDateNaissance());
			System.out.println(patient.isMalade());
			System.out.println(patient.getScore());
		});

		System.out.println("*******************************");
		Patient patient = patientRepository.findById(1L).orElse(null);
		if(patient != null){
			System.out.println(patient.getId());
			System.out.println(patient.getNom());
			System.out.println(patient.getDateNaissance());
			System.out.println(patient.isMalade());
			System.out.println(patient.getScore());

			patient.setScore(800);
			patientRepository.save(patient);
			System.out.println("Score after modify");
			System.out.println(patient.getScore());
//			patientRepository.deleteById(1L);
		}


	}
}
