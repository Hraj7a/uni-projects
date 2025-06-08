package HMS;

import java.util.Date;
import java.util.Scanner;
import java.text.SimpleDateFormat;

/**
 * Main application entry point for interacting with the Hospital System.
 * Provides a menu driven console interface to manage hospital operations.
 * @author [Mohammed Al Muraikhi + Abdulkareem]
 * @since 25/4/2025
 */
public class MainApp {

	// Core system object to manage hospital operations
	private static HospitalSystem hospitalSystem = new HospitalSystem();

	// Scanner for user input
	private static Scanner scanner = new Scanner(System.in);

	// Main method that runs the application loop
	public static void main(String[] args) {
		// Load existing data on startup
		hospitalSystem.loadDataFromFiles();

		int choice = -1;

		// Keep showing the menu until user chooses to exit
		while (choice != 10) {
			printMenu();
			try {
				choice = Integer.parseInt(scanner.nextLine());
			} catch (Exception e) {
				System.out.println("Invalid input. Try again.");
				continue;
			}

			switch (choice) {
			case 1:
				addPatient();
				break;
			case 2:
				addDoctor();
				break;
			case 3:
				addDepartment();
				break;
			case 4:
				assignDoctorToDepartment();
				break;
			case 5:
				scheduleAppointment();
				break;
			case 6:
				generateBill();
				break;
			case 7:
				showReports();
				break;
			case 8:
				showAllDoctors();
				break;
			case 9:
				showAllDepartments();
				break;
			case 10:
				hospitalSystem.saveDataToFiles();
				System.out.println("Exiting...");
				break;
			default:
				System.out.println("Invalid choice. Try again.");
			}
		}
	}

	// Prints the main menu options
	private static void printMenu() {
		System.out.println("===== Hospital Management Menu =====");
		System.out.println("1. Add Patient");
		System.out.println("2. Add Doctor");
		System.out.println("3. Add Department");
		System.out.println("4. Assign Doctor to Department");
		System.out.println("5. Schedule Appointment");
		System.out.println("6. Generate Bill");
		System.out.println("7. Show Reports");
		System.out.println("8. Show All Doctors");
		System.out.println("9. Show All Departments");
		System.out.println("10. Save and Exit");
		System.out.print("Enter your choice: ");
	}

	// Adds a new patient by collecting user input
	private static void addPatient() {
		System.out.print("Enter patient name: ");
		String name = scanner.nextLine();
		System.out.print("Enter gender (MALE/FEMALE): ");
		Gender gender = parseGender(scanner.nextLine());
		System.out.print("Enter age: ");
		int age = Integer.parseInt(scanner.nextLine());
		System.out.print("Enter medical history: ");
		String history = scanner.nextLine();
		System.out.print("Enter email: ");
		String email = scanner.nextLine();
		System.out.print("Enter phone number: ");
		String phone = scanner.nextLine();

		Contact contact = new Contact(email, phone);
		Patient patient = new Patient(0, name, contact, gender, age, history);
		hospitalSystem.addPatient(patient);
		System.out.println("Patient added with ID: " + patient.getId());
	}

	// Adds a new doctor to the system
	private static void addDoctor() {
		System.out.print("Enter doctor name: ");
		String name = scanner.nextLine();
		System.out.print("Enter gender (MALE/FEMALE): ");
		Gender gender = parseGender(scanner.nextLine());
		System.out.print("Enter specialization: ");
		String specialization = scanner.nextLine();
		System.out.print("Enter email: ");
		String email = scanner.nextLine();
		System.out.print("Enter phone number: ");
		String phone = scanner.nextLine();
		System.out.print("Enter department ID: ");
		int departmentId = Integer.parseInt(scanner.nextLine());

		Contact contact = new Contact(email, phone);
		Doctor doctor = new Doctor(0, name, contact, gender, specialization, departmentId);
		hospitalSystem.addDoctor(doctor);
		System.out.println("Doctor added with ID: " + doctor.getId());
	}

	// Adds a department by prompting user input
	private static void addDepartment() {
		System.out.print("Enter department ID: ");
		int departmentId = Integer.parseInt(scanner.nextLine());
		System.out.print("Enter department name: ");
		String departmentName = scanner.nextLine();
		Department department = new Department(departmentId, departmentName);
		hospitalSystem.addDepartment(department);
		System.out.println("Department added successfully.");
	}

	// Assigns a doctor to a department
	private static void assignDoctorToDepartment() {
		System.out.print("Enter doctor ID: ");
		int doctorId = Integer.parseInt(scanner.nextLine());
		System.out.print("Enter department ID: ");
		int departmentId = Integer.parseInt(scanner.nextLine());
		Doctor doctor = hospitalSystem.searchDoctorById(doctorId);
		if (doctor != null) {
			hospitalSystem.assignDoctorToDepartment(departmentId, doctor);
			System.out.println("Doctor assigned to department.");
		} else {
			System.out.println("Doctor not found.");
		}
	}

	// Schedules an appointment between a patient and doctor
	private static void scheduleAppointment() {
		try {
			System.out.print("Enter patient ID: ");
			int patientId = Integer.parseInt(scanner.nextLine());
			System.out.print("Enter doctor ID: ");
			int doctorId = Integer.parseInt(scanner.nextLine());
			System.out.print("Enter appointment date (yyyy-MM-dd): ");
			String dateStr = scanner.nextLine();
			Date appointmentDate = new SimpleDateFormat("yyyy-MM-dd").parse(dateStr);

			Appointment appointment = new Appointment(hospitalSystem.getNextAppointmentId(), patientId, doctorId,
					appointmentDate, AppointmentStatus.UNKNOWN);
			boolean success = hospitalSystem.scheduleAppointment(appointment);
			if (success) {
				System.out.println("Appointment scheduled successfully.");
			} else {
				System.out.println("Doctor not available at this time.");
			}
		} catch (Exception e) {
			System.out.println("Error scheduling appointment: " + e.getMessage());
		}
	}

	// Generates a bill for a patient with multiple services
	private static void generateBill() {
		try {
			System.out.print("Enter patient ID: ");
			int patientId = Integer.parseInt(scanner.nextLine());
			System.out.print("Enter bill amount: ");
			double amount = Double.parseDouble(scanner.nextLine());
			System.out.print("Enter date (yyyy-MM-dd): ");
			String date = scanner.nextLine();

			Bill bill = new Bill(hospitalSystem.getNextBillId(), patientId, date);
			bill.setAmount(amount);

			while (true) {
				System.out.print("Enter service (or 'done' to finish): ");
				String service = scanner.nextLine();
				if (service.equalsIgnoreCase("done"))
					break;
				bill.addService(service);
			}

			hospitalSystem.generateBill(bill);
			System.out.println("Bill generated.");
		} catch (Exception e) {
			System.out.println("Error generating bill: " + e.getMessage());
		}
	}

	// Displays different types of reports
	private static void showReports() {
		System.out.println("1. Patient Report");
		System.out.println("2. Doctor Report");
		System.out.println("3. Appointment Report");
		System.out.println("4. Billing Report");
		System.out.println("5. Department Report");
		System.out.print("Select report type: ");
		int reportChoice = Integer.parseInt(scanner.nextLine());

		switch (reportChoice) {
		case 1:
			hospitalSystem.generatePatientReport();
			break;
		case 2:
			hospitalSystem.generateDoctorReport();
			break;
		case 3:
			hospitalSystem.generateAppointmentReport();
			break;
		case 4:
			hospitalSystem.generateBillingReport();
			break;
		case 5:
			hospitalSystem.generateDepartmentReport();
			break;
		default:
			System.out.println("Invalid report type.");
		}
	}

	// Displays all doctors in the system
	private static void showAllDoctors() {
		hospitalSystem.generateDoctorReport();
	}

	// Displays all departments in the system
	private static void showAllDepartments() {
		hospitalSystem.generateDepartmentReport();
	}

	// Parses gender input with fallback
	private static Gender parseGender(String input) {
		try {
			return Gender.valueOf(input.toUpperCase());
		} catch (Exception e) {
			System.out.println("Invalid gender. Defaulting to MALE.");
			return Gender.MALE;
		}
	}
}
