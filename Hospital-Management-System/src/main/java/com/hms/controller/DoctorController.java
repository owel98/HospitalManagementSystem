package com.hms.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

import com.hms.services.additionalServices;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.hms.model.Doctor;
import com.hms.model.Patient;
import com.hms.services.DoctorService;

@RestController
public class DoctorController {
	@Autowired
	private DoctorService docServ;
    @Autowired
    private additionalServices addServ;
	
	
    @PostMapping("/doctor")
    public Doctor addDoctor(HttpServletRequest request, @RequestBody Doctor doctor){
        String username = addServ.myapi(request);
        int adminRoleId = addServ.getAdminRollId(username);
        if(adminRoleId == 1) {
            if (doctor.getDoctorId() <= 0 || doctor.getDoctorName().isEmpty() || doctor.getPassword().isEmpty() || doctor.getUsername().isEmpty() || doctor.getDoctorProfileStatus().isEmpty()) {
                System.out.println(doctor);
                System.out.println("Invalid Details");
                return null;
            } else if (!doctor.getDoctorProfileStatus().equals("active") &&  !doctor.getDoctorProfileStatus().equals("inactive")) {
                System.out.println(doctor.getDoctorProfileStatus().toLowerCase(Locale.ROOT));
                System.out.println("Invalid Doctor Profile Status, required 'active' or 'Inactive'");
                return null;
            } else if (doctor.getRoleId() != 2) {
                System.out.println("invalid Doctor Role Id, accepted only 2");
                return null;
            }
            return docServ.addDoctor(doctor);
        }
        System.out.println("Not Authorize");
        return null;
    }
    
    @GetMapping("/doctor")
    public List<Doctor> viewDoctorDetails(HttpServletRequest request){
        String username = addServ.myapi(request);
        int adminRoleId = addServ.getAdminRollId(username);
        int doctorRoleId = addServ.getDoctorRoleId(username);
        if(adminRoleId == 1){
            return docServ.getAllDoctorList();
        }
        else if (doctorRoleId == 2){
            return docServ.doctorDetailsById(username).stream().toList();
        }
        System.out.println("Not Authorize");
        return null;
    }


    @GetMapping("/doctor/{username}")
    public Optional<Doctor> viewDoctorById(HttpServletRequest request,@PathVariable String username){
        String userName = addServ.myapi(request);
        int adminRoleId = addServ.getAdminRollId(userName);
        if(adminRoleId == 1 || username.equals(userName)){
            return docServ.doctorDetailsById(username);
        }
        System.out.println("Not Authorize");
        return null;
    }

    
    @PutMapping("/doctor/{username}")
    public Doctor updateDoctorDetails(HttpServletRequest request,@RequestBody Doctor doctor, @PathVariable String username){
        String userName = addServ.myapi(request);
        int adminRoleId = addServ.getAdminRollId(userName);
        int doctorRoleId = addServ.getDoctorRoleId(userName);
        Doctor d = new Doctor();
        if(!username.equals(doctor.getUsername())){
            System.out.println("Invalid DoctorUsername Details");
            return null;
        }
        boolean doctorExists =docServ.doctorExistsById(username);
        if(doctorExists){
            d.setDoctorId(doctor.getDoctorId());
            d.setDoctorName(doctor.getDoctorName());
            d.setUsername(doctor.getUsername());
            d.setPassword(doctor.getPassword());
            d.setDoctorProfileStatus(doctor.getDoctorProfileStatus());
            d.setRoleId(doctor.getRoleId());
        }
        if(adminRoleId == 1 || username.equals(userName)){
            if(d.getDoctorId()<=0  || d.getDoctorName().isEmpty() ||  d.getDoctorProfileStatus().isEmpty()||d.getPassword().isEmpty()|| d.getUsername().isEmpty())
            {
                System.out.println("Invalid Details");
                return null;
            }
            else if (!d.getDoctorProfileStatus().equals("active") && !d.getDoctorProfileStatus().equals("inactive")) {
                System.out.println("Invalid Doctor Profile Status, required 'active' or 'Inactive'");
                return null;
            }
            else if (d.getRoleId()!=2)
            {
                System.out.println("invalid Doctor Role Id, accepted only 2");
                return null;
            }
            return docServ.updateDocDetails(d);
        }
        System.out.println("Not Authorize");
        return null;
    }

    @DeleteMapping("/doctor/{username}")
    public boolean deleteDoctor(HttpServletRequest request, @PathVariable String username){
        String userName = addServ.myapi(request);
        int adminRoleId = addServ.getAdminRollId(userName);
        boolean userNameExists = docServ.doctorExistsById(username);
        if (adminRoleId == 1 && userNameExists ) {
            docServ.deleteDoc(username);
            return true;
        }

        System.out.println("Not Authorize");
        return false;
    }

}
