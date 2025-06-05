package HMS;
import java.io.Serializable;

/**
 * Represents a doctor in the hospital system.
 * Inherits from the Person class and includes specialization and department ID.
 * 
 * @author [Uzair Shah]
 * @since 23/4/2025
 */
@SuppressWarnings("serial")
public class Doctor extends Person implements Serializable {

	// Area of medical expertise
	private String specialization;

	// ID of the department the doctor is assigned to
	private int departmentID;

	// Constructor to initialize doctor with personal and professional details
	public Doctor(int id, String name, Contact contact, Gender gender, String specialization, int departmentID) {
		
		super(id, name, contact, gender);
		this.specialization = specialization;
		this.departmentID = departmentID;
	}

	// Returns the doctor's specialization
	public String getSpecialization() {
		return specialization;
	}

	// Sets the doctor's specialization
	public void setSpecialization(String specialization) {
		this.specialization = specialization;
	}

	// Gets the department ID the doctor is assigned to
	public int getDepartmentID() {
		return departmentID;
	}

	// Sets the department ID for the doctor
	public void setDepartmentID(int departmentID) {
		this.departmentID = departmentID;
	}

	// Displays the doctor's details to the console
	@Override
	public void displayDetails() {
		System.out.println("Doctor Details:");
		System.out.println("ID: " + getId());
		System.out.println("Name: " + getName());
		System.out.println("Gender: " + getGender());
		System.out.println("Specialization: " + specialization);
		System.out.println("Department ID: " + departmentID);
		getContact().displayContactInfo();
	}

	// Returns a string summary of the doctor's profile
	@Override
	public String toString() {
		return super.toString() + "Doctor [Specialization=" + specialization + ", departmentID=" + departmentID + "]";
	}
}
