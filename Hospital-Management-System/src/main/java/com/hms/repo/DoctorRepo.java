package com.hms.repo;


import com.hms.model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface DoctorRepo extends JpaRepository<Doctor, String> {
    @Query(value = "select * from doctor where username = :username",nativeQuery = true)
    public Doctor doctorDetails(@Param("username") String username);
}
