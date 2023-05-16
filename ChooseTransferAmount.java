package assignment04;

import java.math.BigDecimal;

public class ChooseTransferAmount extends ATMstate {
	public void acceptKey(String string) {
		if(string.equals("E")) {
			String temp = aTMInternals.amountStr;
			temp = temp.replace("$", "");
			while(temp.indexOf('_') >= 0) temp = temp.replace("_", "");
			BigDecimal amount = new BigDecimal(temp);
			if(amount.compareTo(aTMInternals.fromAccount.getBalance()) > 0) {
				//displayPanel.showWithdrawAccountError(aTMInternals.fromAccount);
				aTMInternals.updateXFER_ERROR();
				displayPanel.setCurrentButtonString(aTMInternals.XFER_ERROR);
				gui.setState(ATMgui.XFER_END);
			} else {
				aTMInternals.fromAccount.withdraw(amount);
				aTMInternals.toAccount.withdraw(amount);
				System.out.println("Transfer amount $" + amount);
				System.out.println(aTMInternals.fromAccount + " " + aTMInternals.toAccount);
				//		displayPanel.setCurrentButtonString(INIT_STRING);
				aTMInternals.amountStr = "_________$";
				aTMInternals.updateXFER_RESULT();
				displayPanel.setCurrentButtonString(aTMInternals.XFER_RESULT);
				gui.setState(ATMgui.XFER_END);
			}
		} else {
			if(aTMInternals.amountStr.contains(".")) {
				if(string.equals(".")) {
					// ignore only can have one "."
				} 
				else if(aTMInternals.amountStr.indexOf(".") + 3 == aTMInternals.amountStr.length()) {
					// ignore, cannot add more than 2 decimal places for cents
				} 
				else {
					aTMInternals.amountStr += string; // concatenate the entry
					String temp = "_________" + aTMInternals.amountStr;
					aTMInternals.amountStr = temp.substring("_________$".length());
				}
			} else if(string.equals("0") && aTMInternals.amountStr.equals("_________$")) {
				// ignore--do not allow leading 0's
			} 
			else { 
				aTMInternals.amountStr += string;
				String temp = "_________" + aTMInternals.amountStr;
				aTMInternals.amountStr = temp.substring("_________$".length());
			}
			aTMInternals.updateXFER();
			displayPanel.setCurrentButtonString(aTMInternals.XFER);
		}
	}
}
