package assignment04;

public abstract class ATMstate {
	protected static ATMInternals aTMInternals;
	protected static DisplayPanel displayPanel;
	protected static ATMgui gui;
	public static void setATMInternals (ATMInternals internalsIn) {
		aTMInternals = internalsIn;
	}
	public static void setDisplayPanel (DisplayPanel panelIn) {
		displayPanel = panelIn;
	}
	public static void setATMgui (ATMgui guiIn) {
		gui = guiIn;
	}
	public static void commonExitCode() {
		if(gui!= null) gui.exit();
	}
	public void buttonA() {
		aTMInternals.updateLOAN_PAYMENT_START();
		displayPanel.setCurrentButtonString(aTMInternals.LOAN_PAYMENT_START);
		gui.setState(ATMgui.LOAN_PAYMENT_START);
	}
	public void buttonB() {
		displayPanel.setCurrentButtonString(aTMInternals.CHOOSE_FROM);
		gui.setState(ATMgui.CHOOSE_FROM);
	}
	public void buttonC() {
		displayPanel.setCurrentButtonString(aTMInternals.CHOOSE_TRANSFER_AMOUNT);
		gui.setState(ATMgui.CHOOSE_TRANSFER_AMOUNT);
	}
	public void buttonD() {
		displayPanel.setCurrentButtonString(aTMInternals.XFER);
		gui.setState(ATMgui.XFER_END);
	}
	public void buttonE() {
		displayPanel.setCurrentButtonString(aTMInternals.CANCEL_STRING);
		gui.setState(ATMgui.CANCEL);
	}
	public void acceptKey(String string) {
	}
	@Override
	public String toString() {
		return getClass().getSimpleName();
	}
}
