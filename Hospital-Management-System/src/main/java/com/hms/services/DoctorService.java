package com.hms.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hms.model.Doctor;
import com.hms.repo.DoctorRepo;

@Service
public class DoctorService {
	@Autowired
	private DoctorRepo repo;
	
	public Doctor addDoctor(Doctor d) {
		String doctorUsername=d.getUsername();
		boolean doctorUserValue=repo.existsById(doctorUsername);
        if(doctorUserValue){
            System.out.println("Doctor Already exists with username " + doctorUsername);
            return null;
        }
        return repo.save(d);	
		
	}
	public List<Doctor> getAllDoctorList(){
		List<Doctor> d=new ArrayList<>();
		List<Doctor> d2=this.repo.findAll();
		d2.forEach(d::add);
		return d;
	}
	public Optional<Doctor> doctorDetailsById(String username) {
		if(!repo.existsById(username)) {
			System.out.println("Doctor with username"+username+"not exists");
			return null;
		}
		else {
			return repo.findById(username);
		}
	}

public void deleteDoc(String username) {
	repo.deleteById(username);
}
public Doctor updateDocDetails(Doctor doc) {
    long doctorId =doc.getDoctorId();
    boolean doctorIdValue =repo.existsById(doc.getUsername());
    if(!doctorIdValue){
        System.out.println("Doctor details not exists");
        return null;
    }
    return repo.save(doc);
}
public boolean doctorExistsById(String username){
    return repo.existsById(username);
}


}
