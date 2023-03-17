package com.hms.services;

import com.hms.model.Admin;
import com.hms.model.Doctor;
import com.hms.model.Staff;
import com.hms.repo.AdminRepo;
import com.hms.repo.DoctorRepo;
import com.hms.repo.StaffRepo;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.print.Doc;
import java.util.Base64;

@Service
public class additionalServices {
    @Autowired
    private AdminRepo adminRepo;
    @Autowired
    private DoctorRepo doctorRepo;
    @Autowired
    private StaffRepo staffRepo;

    public String myapi(HttpServletRequest request){
        String authHeader = request.getHeader("Authorization");
//"Authorization" header is used to provide authentication credentials for the request.
        // Check if the header is not null and starts with "Basic "
        if (authHeader != null && authHeader.startsWith("Basic ")) {

            // Extract the base64 encoded username and password
            String base64Credentials = authHeader.substring("Basic ".length());
            String credentials = new String(Base64.getDecoder().decode(base64Credentials));
            final String[] split = credentials.split(":", 2);

            // split[0] contains the username and split[1] contains the password
            String username = split[0];
            String password = split[1];
            boolean adminValue = adminLogin(username,password);
            boolean doctorValue = doctorLogin(username,password);
            boolean staffValue  = staffLogin(username,password);
            if(adminValue || doctorValue || staffValue){
                return username;
            }
            else {
                System.out.println("incorrect Username/Password");
            }
        }

        return null;
    }

    public boolean adminLogin(String username, String password){
        Admin a = adminRepo.adminDetails(username);
        if(a == null){
            return false;
        }
        String adminUsername = a.getUserName();
        String adminPassword = a.getPassword();
        if(adminUsername.equals(username) && adminPassword.equals(password)){
            return true;
        }
        return false;
    }

    public boolean doctorLogin(String username, String password){
        Doctor d = doctorRepo.doctorDetails(username);
        if(d == null){
            return false;
        }
        String doctorUsername = d.getUsername();
        String doctorPassword = d.getPassword();
        if(doctorUsername.equals(username) && doctorPassword.equals(password)){
            return true;
        }
        return false;
    }

    public boolean staffLogin(String username, String password){
        Staff s = staffRepo.staffDetails(username);
        if(s == null){
            return false;
        }
        String staffUsername = s.getUsername();
        String staffPassword = s.getPassword();
        if(staffUsername.equals(username) && staffPassword.equals(password)){
            return true;
        }
        return false;
    }

    public int getStaffRoleId(String username){
        Staff s = staffRepo.staffDetails(username);
        if(s==null)
        {
            return 0;
        }
        return s.getRoleId();
    }

    public int getDoctorRoleId(String username){
        Doctor d = doctorRepo.doctorDetails(username);
        if(d==null)
        {
            return 0;
        }
        return d.getRoleId();
    }

    public int getAdminRollId(String username){
        Admin a = adminRepo.adminDetails(username);
        if(a==null)
        {
            return 0;
        }
        return a.getRoleId();
    }

}
