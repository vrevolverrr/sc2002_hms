
## SC2002 Hospital Management System

Hospital Management System (HMS) program for SC2002 project. 

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