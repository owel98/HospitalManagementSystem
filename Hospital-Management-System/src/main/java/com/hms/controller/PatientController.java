package com.hms.controller;

import com.hms.model.Patient;
import com.hms.services.additionalServices;
import com.hms.services.patientServices;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Locale;
import java.util.Optional;

@RestController
public class PatientController {
    @Autowired
    private patientServices patientServ;
    @Autowired
    private additionalServices addServ;

    //Add Patient Details
    @PostMapping("/patient")
    public Patient addPatientDetails(HttpServletRequest request, @RequestBody Patient patient){
        String userName = addServ.myapi(request);
        int adminRoleId = addServ.getAdminRollId(userName);
        int doctorRoleId = addServ.getDoctorRoleId(userName);
        if(adminRoleId == 1 || doctorRoleId ==2) {
            if (patient.getPatientId() <= 0 || patient.getPatientAge() <= 0 || patient.getPatientName().isEmpty() || patient.getPatientPhoneNumber() <= 0 || patient.getPatientHealthDesc().isEmpty() || patient.getPatientProfileStatus().isEmpty()) {
                System.out.println("Invalid Details");
                return null;
            } else if (!patient.getPatientProfileStatus().equals("active") && !patient.getPatientProfileStatus().equals("inactive")) {
                System.out.println(patient.getPatientProfileStatus().toLowerCase(Locale.ROOT));
                System.out.println("Invalid Patient Profile Status, required 'active' or 'Inactive'");
                return null;
            } else if (patient.getRoleId() != 4) {
                System.out.println("invalid Patient Role Id, accepted only 4");
                return null;
            }
            return patientServ.addPatientDetails(patient);
        }
        System.out.println("Not Authorize");
        return null;
    }

    //View Patient Details
    @GetMapping("/patient")
    public List<Patient> viewPatientDetails(HttpServletRequest request){
        String userName = addServ.myapi(request);
        int adminRoleId = addServ.getAdminRollId(userName);
        int doctorRoleId = addServ.getDoctorRoleId(userName);
        int staffRoleId = addServ.getStaffRoleId(userName);
        if(adminRoleId ==1 || doctorRoleId ==2 || staffRoleId ==3) {
            return patientServ.patientDetails();
        }
        return null;
    }

    //view Patient by PatientId
    @GetMapping("/patient/{patientId}")
    public Optional<Patient> viewPatientById(HttpServletRequest request,@PathVariable Long patientId){
        String userName = addServ.myapi(request);
        int adminRoleId = addServ.getAdminRollId(userName);
        int doctorRoleId = addServ.getDoctorRoleId(userName);
        int staffRoleId = addServ.getStaffRoleId(userName);
        if(adminRoleId == 1 || doctorRoleId ==2 || staffRoleId == 3) {
            return patientServ.patientDetailsById(patientId);
        }
        return null;
    }

    //Update Patient Details
    @PutMapping("/patient/{patientId}")
    public Patient updatePatientDetails(HttpServletRequest request,@RequestBody Patient patient, @PathVariable Long patientId){
        String userName = addServ.myapi(request);
        int adminRoleId = addServ.getAdminRollId(userName);
        int doctorRoleId = addServ.getDoctorRoleId(userName);
        Patient p = new Patient();
        if(!patientId.equals(patient.getPatientId())){
            System.out.println("Invalid PatientId Details");
            return null;
        }
        boolean patientExists = patientServ.patientExistsById(patientId);
        if(patientExists){
            p.setPatientId(patient.getPatientId());
            p.setPatientAge(patient.getPatientAge());
            p.setPatientName(patient.getPatientName());
            p.setPatientHealthDesc(patient.getPatientHealthDesc());
            p.setPatientPhoneNumber(patient.getPatientPhoneNumber());
            p.setRoleId(patient.getRoleId());
            p.setPatientProfileStatus(patient.getPatientProfileStatus());
        }
        if(adminRoleId ==1 || doctorRoleId ==2) {
            if (p.getPatientId() <= 0 || p.getPatientAge() <= 0 || p.getPatientName().isEmpty() || p.getPatientPhoneNumber() <= 0 || p.getPatientHealthDesc().isEmpty() || p.getPatientProfileStatus().isEmpty()) {
                System.out.println("Invalid Details");
                return null;
            } else if (!p.getPatientProfileStatus().equals("active") && !p.getPatientProfileStatus().equals("inactive")) {
                System.out.println("Invalid Patient Profile Status, required 'active' or 'Inactive'");
                return null;
            } else if (p.getRoleId() != 4) {
                System.out.println("invalid Patient Role Id, accepted only 4");
                return null;
            }
            return patientServ.updatePatientDetails(p);
        }
        return null;
    }


    //Delete Patient Details
    @DeleteMapping("/patient/{patientId}")
    public boolean deletePatient(HttpServletRequest request, @PathVariable Long patientId){
        String userName = addServ.myapi(request);
        int adminRoleId = addServ.getAdminRollId(userName);
        boolean userNameExists = patientServ.patientExistsById(patientId);
        if(adminRoleId == 1 && userNameExists)
        {
            patientServ.deletePatient(patientId);
            return true;
        }

        System.out.println("Not Authorize");
        return false;
    }
}
