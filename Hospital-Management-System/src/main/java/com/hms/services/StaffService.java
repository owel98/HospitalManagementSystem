package com.hms.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hms.model.Staff;

import com.hms.repo.StaffRepo;

@Service
public class StaffService {
	@Autowired
	private StaffRepo repo1;
	
	public Staff addStaff(Staff s) {
		String staffUsername=s.getUsername();
		boolean staffUserValue=repo1.existsById(staffUsername);
        if(staffUserValue){
            System.out.println("Staff Already exists with username " + staffUsername);
            return null;
        }
        return repo1.save(s);	
		
	}
	public List<Staff> getAllStaffList(){
		List<Staff> d=new ArrayList<>();
		List<Staff> d2=this.repo1.findAll();
		d2.forEach(d::add);
		return d;
	}
	public Optional<Staff> staffDetailsById(String username) {
		if(!repo1.existsById(username)) {
			System.out.println("Staff with username"+username+"not exists");
			return null;
		}
		else {
			return repo1.findById(username);
		}
	}

public void deleteStaff(String username) {
	repo1.deleteById(username);
}
public Staff updateStaffDetails(Staff s) {
    long staffId =s.getStaffId();
    boolean staffIdValue =repo1.existsById(s.getUsername());
    if(!staffIdValue){
        System.out.println("Staff details not exists");
        return null;
    }
    return repo1.save(s);
}
public boolean staffExistsById(String username){
    return repo1.existsById(username);
}


}
