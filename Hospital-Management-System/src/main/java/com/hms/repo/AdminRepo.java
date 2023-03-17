package com.hms.repo;

import com.hms.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AdminRepo extends JpaRepository<Admin, String> {
    @Query(value = "select * from admin where user_name = :username",nativeQuery = true)
    public Admin adminDetails(@Param("username") String username);
}
