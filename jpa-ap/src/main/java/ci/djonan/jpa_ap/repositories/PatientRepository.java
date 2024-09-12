package ci.djonan.jpa_ap.repositories;

import ci.djonan.jpa_ap.entities.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;


public interface PatientRepository extends JpaRepository<Patient,Long> {
    List<Patient> findByMalade(boolean m);
     Page<Patient> findByMalade(boolean m, Pageable pageable);
     @Query("select p from Patient p where p.dateNaissance between :x and :y or p.nom like :z")
     List<Patient> chercherPatients(@Param("x") Date d1, @Param("y") Date d2,@Param("z") String nom);
     @Query("select p from Patient p where p.nom like :x and p.score < :y")
     List<Patient> cherchePatientWithMinScore(@Param("x") String nom, @Param("y") double minScore);
}
