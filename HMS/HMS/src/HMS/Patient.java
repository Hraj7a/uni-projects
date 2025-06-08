package HMS;

import java.io.Serializable;

/**
 * Represents a patient in the hospital system.
 * Inherits from Person and includes age and medical history.
 * 
 * @author [Uzair Shah]
 * @since 23/4/2025
 */
@SuppressWarnings("serial")
public class Patient extends Person implements Serializable {

	public Patient(int id, String name, Contact contact, Gender gender) {
		super(id, name, contact, gender);
	}

	// Patient's age
	private int age;

	// Summary of the patient's medical history
	private String medicalHistory;

	// Constructor to initialize patient with personal and medical details
	public Patient(int id, String name, Contact contact, Gender gender, int age, String medicalHistory) {
		super(id, name, contact, gender);
		this.age = age;
		this.medicalHistory = medicalHistory;
	}

	// Updates the patient's medical history
	public void updateMedicalHistory(String newMedicalInfo) {
		this.medicalHistory = newMedicalInfo;
	}

	// Returns the patient's age
	public int getAge() {
		return age;
	}

	// Sets the patient's age
	public void setAge(int age) {
		this.age = age;
	}

	// Returns the medical history of the patient
	public String getMedicalHistory() {
		return medicalHistory;
	}

	// Sets the patient's medical history
	public void setMedicalHistory(String medicalHistory) {
		this.medicalHistory = medicalHistory;
	}

	// Displays the patient's details to the console
	@Override
	public void displayDetails() {
		System.out.println("Patient Details:");
		System.out.println("ID: " + getId());
		System.out.println("Name: " + getName());
		System.out.println("Gender: " + getGender());
		System.out.println("Age: " + age);
		System.out.println("Medical History: " + medicalHistory);
		getContact().displayContactInfo();
	}

	// Returns a string representation of the patient profile
	@Override
	public String toString() {
		return super.toString() + "Patient [age=" + age + ", medicalHistory=" + medicalHistory + "]";
	}
}
