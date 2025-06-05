package HMS;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Core class that manages all hospital system operations, including: managing
 * patients, doctors, appointments, bills, and departments.
 * 
* @author [Mohammed Al Muraikhi + Abdulkareem]
 * @since 25/4/2025
 */
public class HospitalSystem {

	// Auto-incrementing IDs for new entries
	private int nextPatientId = 1000;
	private int nextDoctorId = 2000;
	private int nextAppointmentId = 3000;
	private int nextBillid = 4000;

	// Internal lists representing database-like storage
	private List<Patient> patients = new ArrayList<>();
	private List<Doctor> doctors = new ArrayList<>();
	private List<Appointment> appointments = new ArrayList<>();
	private List<Bill> bills = new ArrayList<>();
	private List<Department> departments = new ArrayList<>();

	// Constructor initializes the system with empty lists
	public HospitalSystem() {
	}

	// Registers a new patient and assigns a unique ID
	public void addPatient(Patient patient) {
		patient.setId(nextPatientId);
		patients.add(patient);
		nextPatientId++;
	}

	// Removes a patient from the system using their ID
	public void deletePatient(int patientId) {
		for (int i = 0; i < patients.size(); i++) {
			if (patients.get(i).getId() == patientId) {
				patients.remove(i);
				break;
			}
		}
	}

	// Finds and returns a patient by their ID
	public Patient searchPatientByPateindId(int patientId) {
		for (Patient p : patients) {
			if (p.getId() == patientId) {
				return p;
			}
		}
		System.out.println("Patient with Id: " + patientId + " not found.");
		return null;
	}

	// Registers a new doctor and assigns a unique ID
	public void addDoctor(Doctor doctor) {
		doctor.setId(nextDoctorId);
		doctors.add(doctor);
		for (Department d : departments) {
			if (d.getDepartmentID() == doctor.getDepartmentID()) {
				d.addDoctor(doctor);
			}
		}
		nextDoctorId++;
	}

	// Removes a doctor using their ID
	public void deleteDoctor(int doctorId) {
		for (int i = 0; i < doctors.size(); i++) {
			if (doctors.get(i).getId() == doctorId) {
				doctors.remove(i);
				break;
			}
		}
	}

	// Finds and returns a doctor by their ID
	public Doctor searchDoctorById(int doctorId) {
		for (Doctor d : doctors) {
			if (d.getId() == doctorId) {
				return d;
			}
		}
		System.out.println("Doctor with Id: " + doctorId + " not found.");
		return null;
	}

	// Attempts to schedule an appointment if the slot is available
	public Boolean scheduleAppointment(Appointment appointment) {
		for (Appointment a : appointments) {
			if (a.getDoctorID() == appointment.getDoctorID()
					&& a.getAppointmentDate().equals(appointment.getAppointmentDate())
					&& a.getAppointmentStatus() != AppointmentStatus.CANCELLED) {
				return false; // Slot already taken
			}
		}
		appointments.add(appointment);
		appointment.confirmAppointment();
		return true;
	}

	// Cancels an appointment by ID if found
	public Boolean cancelAppointmentById(int appointmentId) {
		for (Appointment a : appointments) {
			if (a.getAppointmentID() == appointmentId) {
				a.setAppointmentStatus(AppointmentStatus.CANCELLED);
				return true;
			}
		}
		return false;
	}

	// Returns an appointment by its ID
	public Appointment searchAppointmentById(int appointmentId) {
		for (Appointment a : appointments) {
			if (a.getAppointmentID() == appointmentId) {
				return a;
			}
		}
		System.out.println("Appointment with Id: " + appointmentId + " not found.");
		return null;
	}

	// Adds a new bill and prints the invoice
	public void generateBill(Bill bill) {
		bills.add(bill);
		bill.generateInvoice();
	}

	// Searches and returns a bill by ID
	public Bill searchBillById(int billId) {
		for (Bill bill : bills) {
			if (bill.getBillId() == billId) {
				return bill;
			}
		}
		System.out.println("Bill with Id: " + billId + " not found.");
		return null;
	}

	// Calculates 30% payment for a given bill
	public double calculatePayment(int billId) {
		for (Bill b : bills) {
			if (b.getBillId() == billId) {
				return 0.3 * b.getAmount();
			}
		}
		return 0;
	}

	// Adds a new department to the system
	public void addDepartment(Department department) {
		departments.add(department);
	}

	// Assigns a doctor to a department by setting the department ID
	public void assignDoctorToDepartment(int departmentId, Doctor doctor) {
		doctor.setDepartmentID(departmentId);
		for (Department d : departments) {
			if (d.getDepartmentID() == departmentId) {
				d.addDoctor(doctor);
			}
		}
	}

	// Displays details of all patients in the system
	public void generatePatientReport() {
		for (Patient p : patients) {
			p.displayDetails();
			System.out.println();
		}
	}

	// Displays details of all doctors in the system
	public void generateDoctorReport() {
		for (Doctor d : doctors) {
			d.displayDetails();
			System.out.println();
		}
	}

	// Displays all scheduled appointments 
	public void generateAppointmentReport() {
		for (Appointment a : appointments) {
			System.out.println(a.toString());
			System.out.println();
		}
	}

	// Displays all generated bills with invoices
	public void generateBillingReport() {
		for (Bill b : bills) {
			b.generateInvoice();
			System.out.println();
		}
	}

	// Displays information about each department
	public void generateDepartmentReport() {
		for (Department d : departments) {
			d.displayDepartmentDetails();
			System.out.println();
		}
	}

	// Saves hospital data to files using serialization
	public void saveDataToFiles() {
		try {
			ObjectOutputStream out1 = new ObjectOutputStream(new FileOutputStream("patients.dat"));
			out1.writeObject(patients);
			out1.close();

			ObjectOutputStream out2 = new ObjectOutputStream(new FileOutputStream("doctors.dat"));
			out2.writeObject(doctors);
			out2.close();

			ObjectOutputStream out3 = new ObjectOutputStream(new FileOutputStream("appointments.dat"));
			out3.writeObject(appointments);
			out3.close();

			ObjectOutputStream out4 = new ObjectOutputStream(new FileOutputStream("bills.dat"));
			out4.writeObject(bills);
			out4.close();

			ObjectOutputStream out5 = new ObjectOutputStream(new FileOutputStream("departments.dat"));
			out5.writeObject(departments);
			out5.close();

		} catch (Exception e) {
			System.out.println("Error saving data: " + e.getMessage());
		}
	}

	// Loads hospital data from files using deserialization
	@SuppressWarnings("unchecked")
	public void loadDataFromFiles() {
		try {
			ObjectInputStream in1 = new ObjectInputStream(new FileInputStream("patients.dat"));
			try {
				patients = (List<Patient>) in1.readObject();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			in1.close();

			ObjectInputStream in2 = new ObjectInputStream(new FileInputStream("doctors.dat"));
			try {
				doctors = (List<Doctor>) in2.readObject();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			in2.close();

			ObjectInputStream in3 = new ObjectInputStream(new FileInputStream("appointments.dat"));
			try {
				appointments = (List<Appointment>) in3.readObject();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			in3.close();

			ObjectInputStream in4 = new ObjectInputStream(new FileInputStream("bills.dat"));
			try {
				bills = (List<Bill>) in4.readObject();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			in4.close();

			ObjectInputStream in5 = new ObjectInputStream(new FileInputStream("departments.dat"));
			try {
				departments = (List<Department>) in5.readObject();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			in5.close();

		} catch (Exception e) {
		    System.out.println("Error loading data:");
		    e.printStackTrace();
		}

	}

	// Getters for next IDs 
	public int getNextPatientId() {
		return nextPatientId;
	}

	public int getNextDoctorId() {
		return nextDoctorId;
	}

	public int getNextAppointmentId() {
		return nextAppointmentId;
	}

	public int getNextBillId() {
		return nextBillid;
	}
}
