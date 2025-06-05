package HMS;

import java.util.List;
import java.util.ArrayList;
import java.io.Serializable;

/**
 * Represents a department within the hospital. Each department has a unique ID,
 * a name, and a list of assigned doctors.
 * 
 * @author [Uzair Shah]
 * @since 23/4/2025
 */
@SuppressWarnings("serial")
public class Department implements Serializable {

	// Unique identifier for the department
	private int departmentID;

	// Name of the department (e.g., Cardiology, Pediatrics)
	private String departmentName;

	// List of doctors assigned to this department
	private List<Doctor> doctors = new ArrayList<>();

	// Constructor to initialize a department with ID and name
	public Department(int departmentID, String departmentName) {
		super();
		this.departmentID = departmentID;
		this.departmentName = departmentName;
	}

	// Adds a doctor to the department's doctor list
	public void addDoctor(Doctor doctor) {
		doctors.add(doctor);
	}

	// Removes a doctor from the department by doctor ID
	public void removeDoctor(int doctorID) {
		for (int i = 0; i < doctors.size(); i++) {
			if (doctors.get(i).getId() == doctorID) {
				doctors.remove(i);
				System.out.println("Doctor with ID " + doctorID + " removed from department.");
				return;
			}
		}
		System.out.println("Doctor with ID " + doctorID + " not found in this department.");
	}
	
	public int getDepartmentID() {
		return departmentID;
	}

	public void setDepartmentID(int departmentID) {
		this.departmentID = departmentID;
	}

	// Displays department ID, name, and a list of doctors
	public void displayDepartmentDetails() {
		System.out.println("Department ID: " + departmentID);
		System.out.println("Department Name: " + departmentName);
		System.out.println("Doctors in this department:");
		for (Doctor d : doctors) {
			System.out.println(" - " + d.getName() + " (ID: " + d.getId() + ")");	
		}
	}
}
