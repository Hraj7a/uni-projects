package HMS;

import java.util.Date;
import java.io.Serializable;

/**
 * @author [Abdulkareem]
 * @since 22/4/2025
 * Represents an appointment between a patient and a doctor.
 *        Includes the date and current status of the appointment.
 */
@SuppressWarnings("serial")
public class Appointment implements Serializable {

	// Unique ID of the appointment
	private int appointmentID;

	// ID of the patient involved in the appointment
	private int patientID;

	// ID of the doctor assigned to the appointment
	private int doctorID;

	// Scheduled date and time of the appointment
	private Date appointmentDate;

	// Current status of the appointment (Confirmed, Cancelled, Unknown)
	private AppointmentStatus appointmentStatus;

	// Constructor to initialize all fields of the appointment
	public Appointment(int appointmentID, int patientID, int doctorID, Date appointmentDate,
			AppointmentStatus appointmentStatus) {
		this.appointmentID = appointmentID;
		this.patientID = patientID;
		this.doctorID = doctorID;
		this.appointmentDate = appointmentDate;
		this.appointmentStatus = appointmentStatus;
	}

	// Returns the appointment ID
	public int getAppointmentID() {
		return appointmentID;
	}

	// Sets the appointment ID
	public void setAppointmentID(int appointmentID) {
		this.appointmentID = appointmentID;
	}

	// Gets the patient ID associated with this appointment
	public int getPatientID() {
		return patientID;
	}

	// Sets the patient ID
	public void setPatientID(int patientID) {
		this.patientID = patientID;
	}

	// Gets the doctor ID associated with this appointment
	public int getDoctorID() {
		return doctorID;
	}

	// Sets the doctor ID
	public void setDoctorID(int doctorID) {
		this.doctorID = doctorID;
	}

	// Returns the scheduled date of the appointment
	public Date getAppointmentDate() {
		return appointmentDate;
	}

	// Updates the scheduled date of the appointment
	public void setAppointmentDate(Date appointmentDate) {
		this.appointmentDate = appointmentDate;
	}

	// Returns the current appointment status
	public AppointmentStatus getAppointmentStatus() {
		return appointmentStatus;
	}

	// Updates the appointment status
	public void setAppointmentStatus(AppointmentStatus appointmentStatus) {
		this.appointmentStatus = appointmentStatus;
	}

	// Confirms the appointment
	public void confirmAppointment() {
		this.appointmentStatus = AppointmentStatus.CONFIRMED;
		System.out.println("Appointment confirmed.");
	}

	// Cancels the appointment
	public void cancelAppointment() {
		this.appointmentStatus = AppointmentStatus.CANCELLED;
		System.out.println("Appointment cancelled.");
	}

	// Reschedules the appointment to a new date (if not cancelled)
	public void rescheduleAppointment(Date newDate) {
		if (this.appointmentStatus != AppointmentStatus.CANCELLED) {
			this.appointmentDate = newDate;
			this.appointmentStatus = AppointmentStatus.CONFIRMED;
			System.out.println("Appointment rescheduled to: " + newDate);
		} else {
			System.out.println("Cannot reschedule a cancelled appointment.");
		}
	}

	// Returns a string summary of the appointment details
	@Override
	public String toString() {
		return "Appointment [appointmentID=" + appointmentID + ", patientID=" + patientID + ", doctorID=" + doctorID
				+ ", appointmentDate=" + appointmentDate + ", appointmentStatus=" + appointmentStatus + "]";
	}
}
