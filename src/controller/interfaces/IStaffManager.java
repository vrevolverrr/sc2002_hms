package controller.interfaces;

import java.time.LocalDate;
import java.util.List;

import model.enums.Gender;
import model.enums.Specialisation;
import model.users.User;

public interface IStaffManager {

    public List<User> getAllStaff();

    public List<User> findStaffByKeywords(String keyword);

    public void addDoctor(String name, int age, String password, Gender gender, LocalDate dob,
            String emailAddress, String phoneNumber, Specialisation specialisation);

    public void addAdmin(String name, int age, String password, Gender gender, LocalDate dob, 
            String emailAddress, String phoneNumber);

    public void addPharmacist(String name, int age, String password, Gender gender, LocalDate dob, 
            String emailAddress, String phoneNumber);

    public void updateStaff(User user);
    
    public void deleteStaff(User user);
}
