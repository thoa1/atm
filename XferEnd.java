package assignment04;

public class XferEnd extends ATMstate {
	public void buttonD() {
		displayPanel.setCurrentButtonString(ATMInternals.INIT_STRING);
		aTMInternals.amountStr = "_________$";
		gui.setState(ATMgui.INIT);
	}		
}
