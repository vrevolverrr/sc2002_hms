<h1 align="center">SC2002 Hospital Management System</h1>

<p align="center">
  <b><small>✨ Hospital Management System for SC2002 Object Oriented Design & Programming project ✨</small></b>
</p>
<div align="center">
	<img src="https://github.com/vrevolverrr/sc2002_hms/blob/main/res/preview.gif?raw=true" alt="preview of HMS App"  width="600"/>
</div>
<br />
<div align="center">
   <p><b>Team Members</b></p>
   <p>Soong Jun Shen (SCEB 24)</p>
   <p>Joyce Lee Jia Xuan (SCEB 13)</p>
   <p> Chng Jiade (Zhuang Jiade) (SCEB 4) </p>
   <p> Swaminathan Navitraa (SCEB 27) </p>
   <p> Shu Yanjun (SCEB 23) </p>
</div>

<br />
### Prerequisites 
1. **Java Development Kit (JDK) >= 16** 
2. **Terminal with support for ANSI/VT Escape Sequences (Windows Only)**
	 Linux/MacOS terminals and the VSCode Integrated terminal have support by default. For 	   Windows users, if the interface is not printed correctly, see [how to enable support for ANSI/VT codes on Powershell.](https://stackoverflow.com/questions/51680709/colored-text-output-in-powershell-console-using-ansi-vt100-codes)

 ### Installation and Setup

3. **Clone the Repository**

   ```bash
   git clone https://github.com/vrevolverrr/sc2002_hms.git
   cd sc2002_hms
   ```
   
4. **Compile the build script (optional)**
   
   ```bash
   chmod +x ./build.sh
   ```
   
5. **Run the build script to build the executable JAR and docs**
   
   ```bash
   ./build.sh
   ```
   
6. **Run the application**
   
   ```bash
   java -jar HMSApp.jar --mock  # to generate mock data before starting
   java -jar HMSApp.jar  # run without generating mock data (use exisiting)
    ```

### Design Approach
1. **Model-View-Controller (MVC) Architectural Pattern**

	**Model**: Encapsulates data and business logic, while the Repository layer manages retrieval, persistence, and manipulation. Examples: User, Appointment, Prescription.

	**View**: Handles user interface rendering, presenting data from the Controller in a user-friendly way. Examples: AdminView, DoctorView.

	**Controller (Manager)**: Mediates between the View, Model, and Repository layers, passing inputs from the View to the Model and retrieving data for the View. Examples: AppointmentsManager, InventoryManager.

2. **Service-Repository Pattern**

	**Repository Layer**: Provides abstractions for data access, separating business logic from data persistence, and centralizing data access logic. Examples: UserRepository, AppointmentsRepository.

	**Manager Classes**: Handle specific data models with exclusive access to their corresponding Repositories. They process View requests, apply business logic, and interact with Repositories/Models. For example, scheduling an appointment involves the IAppointmentsManager, which creates an Appointment model from View inputs and saves it via the IAppointmentRepository.
  
3. **Service Locator Pattern**

	**ServiceLocator**: Acts as a central registry for managing and injecting dependencies, delegating this responsibility away from dependent classes. It manages Manager and Repository instances, providing dependencies via constructor injection. Views access Managers using the getService method, promoting loose coupling and inversion of control.
	
### UML Diagram
![UML Diagram](https://github.com/vrevolverrr/sc2002_hms/blob/main/res/HMS_UML_Diagram.png?raw=true)