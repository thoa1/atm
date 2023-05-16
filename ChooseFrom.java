package assignment04;

public class ChooseFrom extends ATMstate {
	public void buttonB() {
		aTMInternals.fromAccount = aTMInternals.checkingAccount;
		aTMInternals.fromAccountStr = "Checking";
		aTMInternals.updateCHOOSE_TRANSFER_AMOUNT();
		displayPanel.setCurrentButtonString(aTMInternals.CHOOSE_TRANSFER_AMOUNT);
		gui.setState(ATMgui.CHOOSE_TRANSFER_AMOUNT);
	}		
	public void buttonC() {
		aTMInternals.fromAccount = aTMInternals.savingsAccount;
		aTMInternals.fromAccountStr = "Savings";
		aTMInternals.updateCHOOSE_TRANSFER_AMOUNT();
		displayPanel.setCurrentButtonString(aTMInternals.CHOOSE_TRANSFER_AMOUNT);
		gui.setState(ATMgui.CHOOSE_TRANSFER_AMOUNT);
	}		
	public void buttonD() {
		aTMInternals.fromAccount = aTMInternals.moneyMarketAccount;
		aTMInternals.fromAccountStr = "Money Market";
		aTMInternals.updateCHOOSE_TRANSFER_AMOUNT();
		displayPanel.setCurrentButtonString(aTMInternals.CHOOSE_TRANSFER_AMOUNT);
		gui.setState(ATMgui.CHOOSE_TRANSFER_AMOUNT);
	}		
}
