package HMS;

import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;

/**
 * @author [Mohammed Al Muraikhi]
 * @since 25/4/2025
 * Represents a billing record for a patient, including services and payment info.
 */
@SuppressWarnings("serial")
public class Bill implements Payable, Serializable {

	// Unique identifier for the bill
	private int billId;

	// ID of the patient the bill is for
	private int patientID;

	// Total amount charged for the bill
	private double amount;

	// List of services billed to the patient
	private List<String> services = new ArrayList<>();

	// Date when the bill was created
	private String date;

	// Constructor to initialize the bill with ID, patient ID, and date
	public Bill(int billId, int patientID, String date) {
		super();
		this.billId = billId;
		this.patientID = patientID;
		this.date = date;
	}

	// Returns the bill ID
	public int getBillId() {
		return billId;
	}

	// Sets the bill ID
	public void setBillId(int billId) {
		this.billId = billId;
	}

	// Returns the patient ID linked to the bill
	public int getPatientID() {
		return patientID;
	}

	// Sets the patient ID
	public void setPatientID(int patientID) {
		this.patientID = patientID;
	}

	// Returns the total amount of the bill
	public double getAmount() {
		return amount;
	}

	// Sets the total amount of the bill
	public void setAmount(double amount) {
		this.amount = amount;
	}

	// Adds a service to the bill
	public void addService(String service) {
		services.add(service);
	}

	// Prints the invoice details to the console
	public void generateInvoice() {
		System.out.println("----- Invoice -----");
		System.out.println("Bill ID    : " + billId);
		System.out.println("Patient ID : " + patientID);
		System.out.println("Date       : " + date);
		System.out.println("Services   :");
		for (String service : services) {
			System.out.println("  - " + service);
		}
		System.out.println("Total Amount: $" + amount);
		System.out.println("-------------------");
	}

	// Calculates 30% of the bill amount to be paid
	@Override
	public double calculatePayment(int billId) {
		return amount;
	}
}
