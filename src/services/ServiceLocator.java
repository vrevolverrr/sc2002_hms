package services;

import java.util.HashMap;
import java.util.Map;

import controller.AppointmentManager;
import controller.DoctorManager;
import controller.InventoryManager;
import controller.MedicalRecordManager;
import controller.PatientManager;
import controller.PharmacistManager;
import controller.StaffManager;
import controller.UserManager;
import controller.interfaces.IAppointmentManager;
import controller.interfaces.IDoctorManager;
import controller.interfaces.IInventoryManager;
import controller.interfaces.IMedicalRecordManager;
import controller.interfaces.IPatientManager;
import controller.interfaces.IPharmacistManager;
import controller.interfaces.IStaffManager;
import controller.interfaces.IUserManager;
import repository.AppointmentRepository;
import repository.DoctorRepository;
import repository.InventoryRepository;
import repository.MedicalRecordRepository;
import repository.PatientRepository;
import repository.UserRepository;
import repository.interfaces.IAppointmentRepository;
import repository.interfaces.IDoctorRepository;
import repository.interfaces.IInventoryRepository;
import repository.interfaces.IMedicalRecordRepository;
import repository.interfaces.IPatientRepository;
import repository.interfaces.IUserRepository;

/**
 * A service locator that allows for the registration and retrieval of services, that is instances of manager classes.
 * The service locator centralises and manages all the dependencies on services into one class thus reduces coupling between classes.
 * 
 * @author Bryan Soong, Joyce Lee
 * @version 1.0
 * @since 2024-11-16
 */
public class ServiceLocator {
    /**
     * Private constructor of the {@link ServiceLocator} class to prevent instantiation.
     */
    private ServiceLocator() {}
    
    /**
     * A map of services registered with the service locator.
     */
    private final static Map<Class<?>, Object> services = new HashMap<>();

    /**
     * Register a service with the service locator.
     * 
     * @param <T> The type of the service.
     * @param serviceClass The class of the service.
     * @param serviceInstance The instance of the service.
     * @return The instance of the service.
     */
    public static <T> T registerService(Class<T> serviceClass, T serviceInstance) {
        if (serviceClass == null || serviceInstance == null) {
            throw new IllegalArgumentException("Service class and instance must not be null.");
        }
        services.put(serviceClass, serviceInstance);

        return serviceInstance;
    }

    /**
     * Get a service from the service locator.
     * 
     * @param <T> The type of the service.
     * @param serviceClass The class of the service.
     * @return The instance of the service.
     */
    public static <T> T getService(Class<T> serviceClass) {
        T service = serviceClass.cast(services.get(serviceClass));
        
        if (service == null) {
            throw new IllegalStateException("No service registered for " + serviceClass.getName());
        }
        return service;
    }

    /**
     * Unregister a service from the service locator.
     * 
     * @param <T> The type of the service.
     * @param serviceClass The class of the service.
     */
    public static <T> void unregisterService(Class<T> serviceClass) {
        services.remove(serviceClass);
    }

    /**
     * Register all the services for the HMS application while managing their dependencies.
     */
    public static void registerHMSServices() {
        /// These are base repositories
        IAppointmentRepository appointmentRepository = new AppointmentRepository();
        IInventoryRepository inventoryRepository = new InventoryRepository();
        IUserRepository userRepository = new UserRepository();
        IMedicalRecordRepository medicalRecordRepository = new MedicalRecordRepository();

        /// These repositories depend on the user repository
        IDoctorRepository doctorRepository = new DoctorRepository(userRepository);
        IPatientRepository patientRepository = new PatientRepository(userRepository);

        /// Register the services
        ServiceLocator.registerService(
            IAppointmentManager.class, 
            new AppointmentManager(appointmentRepository));

        ServiceLocator.registerService(
                IInventoryManager.class, new InventoryManager(inventoryRepository));

        ServiceLocator.registerService(
                    IUserManager.class, new UserManager(userRepository));
        
        ServiceLocator.registerService(
            IDoctorManager.class, 
            new DoctorManager(doctorRepository));
        
        ServiceLocator.registerService(
            IMedicalRecordManager.class, 
            new MedicalRecordManager(medicalRecordRepository, inventoryRepository));
        
        ServiceLocator.registerService(
            IPatientManager.class, 
            new PatientManager(patientRepository, appointmentRepository));
        
        ServiceLocator.registerService(
            IPharmacistManager.class, 
            new PharmacistManager(inventoryRepository, appointmentRepository));
        
        ServiceLocator.registerService(
            IStaffManager.class, new StaffManager(userRepository));
    }
}
