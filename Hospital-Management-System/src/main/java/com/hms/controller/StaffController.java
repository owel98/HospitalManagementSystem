package com.hms.controller;

import java.util.List;

import java.util.Locale;
import java.util.Optional;

import com.hms.services.additionalServices;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.hms.model.Staff;

import com.hms.services.StaffService;

@RestController
public class StaffController {
	@Autowired
	private StaffService staffServ;
    @Autowired
    private additionalServices addServ;
	
	@PostMapping("/staff")
	public Staff addStaff(HttpServletRequest request, @RequestBody Staff s) {
        String userName = addServ.myapi(request);
        int adminRoleId = addServ.getAdminRollId(userName);
        if(adminRoleId == 1){
            if(s.getStaffId()<=0 || s.getUsername().isEmpty() || s.getPassword().isEmpty() || s.getStaffName().isEmpty() || s.getStaffProfileStatus().isEmpty()) {
                System.out.println(s);
                System.out.println("invalid details");
                return null;
            }
            else if(!s.getStaffProfileStatus().equals("active") && !s.getStaffProfileStatus().equals("inactive")){
                System.out.println(s.getStaffProfileStatus().toLowerCase(Locale.ROOT));
                System.out.println("Invalid Staff Profile status,required 'active' or 'Inactive'");
                return null;
            }
            else if(s.getRoleId()!=3)
            {
                System.out.println("invalid staff Role Id, accepted only 3");
                return null;
            }
            return staffServ.addStaff(s);
        }
        System.out.println("Not Authorize");
        return null;
	}
    
    @GetMapping("/staff")
    public List<Staff> viewStaffDetails(HttpServletRequest request){
        String userName = addServ.myapi(request);
        int adminRoleId = addServ.getAdminRollId(userName);
        int staffRoleId = addServ.getStaffRoleId(userName);
        if(adminRoleId == 1) {
            return staffServ.getAllStaffList();
        }
        else if (staffRoleId ==3){
            return staffServ.staffDetailsById(userName).stream().toList();
        }
        System.out.println("Not Authorize");
        return null;
    }

    @GetMapping("/staff/{username}")
    public Optional<Staff> viewStaffById(HttpServletRequest request,@PathVariable String username) {
        String userName = addServ.myapi(request);
        int adminRoleId = addServ.getAdminRollId(userName);
        int staffRoleId = addServ.getStaffRoleId(userName);
        if (adminRoleId == 1 || username.equals(userName)) {
            return staffServ.staffDetailsById(username);
        }
        System.out.println("Not Authorize");
        return null;
    }
  
    @PutMapping("/staff/{username}")
    public Staff updateStaffDetails(HttpServletRequest request, @RequestBody Staff staff, @PathVariable String username){
        String userName = addServ.myapi(request);
        int adminRoleId = addServ.getAdminRollId(userName);
        Staff s=new Staff();
        if(!username.equals(staff.getUsername())){
            System.out.println("Invalid Staffusername Details");
            return null;
        }
        boolean staffExists =staffServ.staffExistsById(username);
        if(staffExists){
            s.setStaffId(staff.getStaffId());
            s.setUsername(staff.getUsername());
            s.setPassword(staff.getPassword());
            s.setStaffProfileStatus(staff.getStaffProfileStatus());
            s.setStaffName(staff.getStaffName());
            s.setRoleId(staff.getRoleId());

        }
        if(adminRoleId == 1) {
            if (s.getStaffId() <= 0 || s.getStaffName().isEmpty() || s.getStaffProfileStatus().isEmpty() || s.getPassword().isEmpty() || s.getUsername().isEmpty()) {
                System.out.println("Invalid Details");
                return null;
            } else if (!s.getStaffProfileStatus().equals("active") && !s.getStaffProfileStatus().equals("inactive")) {
                System.out.println("Invalid Staff Profile Status, required 'active' or 'Inactive'");
                return null;
            } else if (s.getRoleId() != 3) {
                System.out.println("invalid Staff Role Id, accepted only 3");
                return null;
            }
            return staffServ.updateStaffDetails(s);
        }
        System.out.println("Not Authorize");
        return null;
    }

    //Delete Patient Details
    @DeleteMapping("/staff/{username}")
    public boolean deleteStaff(HttpServletRequest request,@PathVariable String username) {
        String userName = addServ.myapi(request);
        int adminRoleId = addServ.getAdminRollId(userName);
        boolean userNameExists = staffServ.staffExistsById(username);
        if (adminRoleId == 1 && userNameExists) {
            staffServ.deleteStaff(username);
            return true;
        }

        System.out.println("Not Authorize");
        return false;
    }
}
