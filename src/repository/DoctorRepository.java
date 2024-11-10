package repository;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import model.enums.UserRole;
import model.users.Doctor;
import model.users.User;

public class DoctorRepository implements Repository<Doctor> {
    // General class definition and constructors
    private final UserRepository repository;
    
    public DoctorRepository(UserRepository repository) {
        this.repository = repository;
    }
    
    // Retrieve all doctors from Doctor Repository
    @Override
    public Map<String, Doctor> getItems() {
        return this.repository.getItems().entrySet().stream()
            .filter(entry -> entry.getValue().getRole() == UserRole.DOCTOR)
            .collect(Collectors.toMap(Map.Entry::getKey, e -> (Doctor) e.getValue()));
    }

    // Returns the count of doctor entries
    @Override
    public int count() {
        return this.getItems().size();
    }

    // Find a doctor based on their ID
    @Override
    public Doctor findById(String doctorId) {
        User user = repository.findById(doctorId);
        if (user == null || user.getRole() != UserRole.DOCTOR) {
            return null; // Return null if no user or user is not a doctor
        }
        return (Doctor) user;
    }

    // Finds all doctors with role DOCTOR using findBy
    @Override
    public List<Doctor> findBy(Predicate<Doctor> predicate) {
        return repository.findBy(user -> user.getRole() == UserRole.DOCTOR).stream()
            .map(user -> (Doctor) user) // Cast User to Doctor
            .filter(predicate)          // Apply the given predicate
            .collect(Collectors.toList()); // Collect results as a List
    }

    // Checks if a doctor exists by doctorID
    @Override
    public boolean exists(String doctorId) {
        return this.findById(doctorId) != null; // Return true if doctor exists
    }

    // Check if any doctor satisfies the predicate
    @Override
    public boolean exists(Predicate<Doctor> predicate) {
        return !this.findBy(predicate).isEmpty(); // Return true if any doctor matches the predicate
    }

    // Save a single doctor via user repository
    @Override
    public Doctor save(Doctor item) {
        repository.save(item); // Save the doctor as a User in UserRepository
        return item;
    }

    // Converts a list of Doctor objects to Users and saves them
    @Override
    public List<Doctor> save(List<Doctor> collection) {
        repository.save(collection.stream().map(doctor -> (User) doctor).collect(Collectors.toList()));
        return collection; // Return the saved collection of Doctors
    }

    // Delete a Doctor by doctorId if it exists
    @Override
    public Doctor deleteById(String doctorId) {
        if (exists(doctorId)) { // Check if doctor exists before deletion
            return (Doctor) repository.deleteById(doctorId); // Delete and return the removed doctor
        }
        return null; // Return null if no doctor found
    }

    // Clear all Doctor entries from UserRepository
    @Override
    public void clear() {
        repository.findBy(user -> user.getRole() == UserRole.DOCTOR) // Find all users with DOCTOR role
                  .forEach(user -> repository.deleteById(user.getId())); // Delete each doctor by ID
    }
}


