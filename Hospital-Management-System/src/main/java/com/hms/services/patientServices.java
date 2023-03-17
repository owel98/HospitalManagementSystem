package com.hms.services;

import com.hms.model.Patient;
import com.hms.repo.PatientRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class patientServices {
    @Autowired
    private final PatientRepo patientRepo;

    public patientServices(PatientRepo patientRepo) {
        this.patientRepo = patientRepo;
    }

    public Patient addPatientDetails(Patient patient){
        long patientId = patient.getPatientId();
        boolean patientIdValue =patientRepo.existsById(patientId);
        if(patientIdValue){
            System.out.println("Patient Already exists with Id " + patientId);
            return null;
        }
        return patientRepo.save(patient);
    }

    public List<Patient> patientDetails(){
        List<Patient> patientList = new ArrayList<>();
        patientRepo.findAll().forEach(patientList::add);
        return patientList;
    }

    public Optional<Patient> patientDetailsById(Long patientId){
        if(!patientRepo.existsById(patientId)){
            System.out.println("Patient with Id "+patientId+" not exists");
            return null;
        }
        else {
            return patientRepo.findById(patientId);
        }
    }

    public Patient updatePatientDetails(Patient patient){
        long patientId = patient.getPatientId();
        boolean patientIdValue =patientRepo.existsById(patientId);
        if(!patientIdValue){
            System.out.println("Patient details not exists");
            return null;
        }
        return patientRepo.save(patient);
    }

    public void deletePatient(Long patientId){
        patientRepo.deleteById(patientId);
    }

    public boolean patientExistsById(Long patientId){
        return patientRepo.existsById(patientId);
    }

}
