package HMS;

import java.io.Serializable;

/**
 * 
 * @author [Uzair Shah]
 * @since 22/4/2025
 * Represents the contact information for a person, including email and phone number.
 */
@SuppressWarnings("serial")
public class Contact implements Serializable {
	
	// Stores the email address
	private String email;

	// Stores the phone number
	private String phoneNumber;

	// Constructor initializes contact with an email and a phone number
	public Contact(String email, String phoneNumber) {
		this.email = email;
		this.phoneNumber = phoneNumber;
	}

	// Returns the current email address
	public String getEmail() {
		return email;
	}

	// Sets a new email address
	public void setEmail(String email) {
		this.email = email;
	}

	// Returns the current phone number
	public String getPhoneNumber() {
		return phoneNumber;
	}

	// Sets a new phone number
	public void setPhoneNumber(String phoneNUmber) {
		this.phoneNumber = phoneNUmber;
	}

	// Displays the contact information to the console
	public void displayContactInfo() {
		System.out.println("Email: " + getEmail() + "\nPhone Number: " + getPhoneNumber());
	}

	// Returns a string representation of the contact object
	@Override
	public String toString() {
		return "Contact [email=" + email + ", phoneNumber=" + phoneNumber + "]";
	}
}
