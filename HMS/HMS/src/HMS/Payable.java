package HMS;

/**
 * @author [Huthaifa Rajha]
 * @since 25/4/2025
 *  Interface for any class that supports payment calculation logic.
 */
public interface Payable {

	// Calculates the payment amount for a given bill ID
	double calculatePayment(int billId);
}
