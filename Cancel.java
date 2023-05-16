package assignment04;

public class Cancel extends ATMstate {
	public void acceptKey(String string) {
		if(string.equals("E")) {
			displayPanel.setCurrentButtonString(aTMInternals.LOGOUT_STRING);
			gui.setState(ATMgui.LOGGED_OUT);
		}else{
		aTMInternals.amountStr = "_________$";
			displayPanel.setCurrentButtonString(ATMInternals.INIT_STRING);
			
		}
	}
}
