package HMS;
import java.io.Serializable;

/**
 * @author [Huthaifa Rajha]
 * @since 22/4/2025
 * Abstract class representing a general person in the hospital system,
 * such as a doctor or a patient.
 */
@SuppressWarnings("serial")
public abstract class Person implements Serializable {

	// Unique identifier for the person
	private int id;

	// Person's full name
	private String name;

	// Contact information (email and phone number)
	private Contact contact;

	// Gender of the person (enum: MALE, FEMALE, OTHER)
	private Gender gender;

	// Constructor to initialize a person with id, name, contact, and gender
	public Person(int id, String name, Contact contact, Gender gender) {
		this.id = id;
		this.name = name;
		this.contact = contact;
		this.gender = gender;
	}

	// Gets the unique ID of the person
	public int getId() {
		return id;
	}

	// Sets the unique ID for the person
	public void setId(int id) {
		this.id = id;
	}

	// Returns the name of the person
	public String getName() {
		return name;
	}

	// Updates the person's name
	public void setName(String name) {
		this.name = name;
	}

	// Returns the contact details
	public Contact getContact() {
		return contact;
	}

	// Updates the contact details
	public void setContact(Contact contact) {
		this.contact = contact;
	}

	// Returns the gender of the person
	public Gender getGender() {
		return gender;
	}

	// Sets the gender of the person
	public void setGender(Gender gender) {
		this.gender = gender;
	}

	// Updates the person's contact info using another Contact object
	public void updateContactInfo(Contact newcontact) {
		contact.setEmail(newcontact.getEmail());
		contact.setPhoneNumber(newcontact.getPhoneNumber());
	}

	// Validates the ID to ensure it's non-negative
	public boolean validateID(int id) {
		if (id < 0)
			return false;
		else 
			return true;
	}

	// Abstract method to be implemented by subclasses to show person details
	public abstract void displayDetails();

	// Returns a string summary of the person's details
	@Override
	public String toString() {
		return "Person [id=" + id + ", name=" + name + ", contact=" + contact + ", gender=" + gender + "]";
	}
}
