package assignment04;

public class LoanPaymentStart extends ATMstate {
	public void buttonA() {
		aTMInternals.toAccount = aTMInternals.mortgageAccount;
		aTMInternals.toAccountStr = "Mortgage";
		aTMInternals.toAccountAPR = aTMInternals.mortgageAPR;
		aTMInternals.toAccountPayment = aTMInternals.mortgagePmt;
		aTMInternals.updateCHOOSE_FROM();
		displayPanel.setCurrentButtonString(aTMInternals.CHOOSE_FROM);
		gui.setState(ATMgui.CHOOSE_FROM);
	}		
	public void buttonB() {
		aTMInternals.toAccount = aTMInternals.carLoanAccount;
		aTMInternals.toAccountStr = "Car Loan";
		aTMInternals.toAccountAPR = aTMInternals.carLoanAPR;
		aTMInternals.toAccountPayment = aTMInternals.carLoanPmt;
		aTMInternals.updateCHOOSE_FROM();
		displayPanel.setCurrentButtonString(aTMInternals.CHOOSE_FROM);
		gui.setState(ATMgui.CHOOSE_FROM);
	}		
	public void buttonC() {
		aTMInternals.toAccount = aTMInternals.personalLoanAccount;
		aTMInternals.toAccountStr = "Personal Loan";
		aTMInternals.toAccountAPR = aTMInternals.personalLoanAPR;
		aTMInternals.toAccountPayment = aTMInternals.personalLoanPmt;
		aTMInternals.updateCHOOSE_FROM();
		displayPanel.setCurrentButtonString(aTMInternals.CHOOSE_FROM);
		gui.setState(ATMgui.CHOOSE_FROM);

	}		
}
