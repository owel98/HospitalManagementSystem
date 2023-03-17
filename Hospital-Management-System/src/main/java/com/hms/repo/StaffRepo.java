package com.hms.repo;

import com.hms.model.Admin;
import com.hms.model.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface StaffRepo extends JpaRepository<Staff, String> {
    @Query(value = "select * from staff where username = :username",nativeQuery = true)
    public Staff staffDetails(@Param("username") String username);

}
