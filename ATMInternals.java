package assignment04;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

public class ATMInternals {
	Account checkingAccount;
	Account savingsAccount;
	Account moneyMarketAccount; 
	Account mortgageAccount;
	Account carLoanAccount;
	Account personalLoanAccount;
	Account fromAccount;
	String fromAccountStr;
	Account toAccount;
	String toAccountStr;
	BigDecimal toAccountPayment;
	BigDecimal toAccountAPR;
	String amountStr = "_________$";
	Account mortgageBalance;
	Account carLoanBalance;
	Account personalLoanBalance;
	BigDecimal mortgagePmt;
	BigDecimal carLoanPmt;
	BigDecimal personalLoanPmt; 
	BigDecimal mortgageAPR;
	BigDecimal carLoanAPR;
	BigDecimal personalLoanAPR; 
	LocalDate nextPaymentDate;
	static String[] INIT_STRING = {"Deposit","Withdraw","Transfer","More...","Cancel"};
	String[] CANCEL_STRING = {"Do you wish to Cancel the session?","Press E to exit, any other keypad key to cancel","","",""};
	String[] LOGOUT_STRING = {"You have logged out","","","",""};
	String[] LOAN_STRING = new String[5];
	String[] XFER_FROM = new String[5];
	String[] LOAN_PAYMENT_START = new String[5];
	String[] XFER = new String[5];
	String[] CHOOSE_FROM = new String[5];
	String[] CHOOSE_TRANSFER_AMOUNT = new String[5];
	String[] XFER_ERROR = new String[5];
	String[] XFER_RESULT = new String[5];
	void updateLOAN_STRING() {
		LOAN_STRING = new String[] {
				String.format("Pay to Mortgage, balance $%.2f", mortgageAccount.getBalance()), 
				String.format("Pay to Car Loan, balance $%.2f", carLoanAccount.getBalance()), 
				String.format("Pay to Personal Loan, balance $%.2f", personalLoanAccount.getBalance()),
				"", "Cancel"}; 
	}
	void updateLOAN_PAYMENT_START() {
		LOAN_PAYMENT_START = new String[] {
				String.format("Pay to Mortgage, balance %.2f", mortgageAccount.getBalance()), 
				String.format("Pay to Car Loan, balance %.2f", carLoanAccount.getBalance()), 
				String.format("Pay to Personal Loan, balance %.2f", personalLoanAccount.getBalance()),
				"", "Cancel"}; 
	}
	void updateCHOOSE_FROM() {
		CHOOSE_FROM = new String[] {
				String.format("Transfer to " + toAccountStr +" (%.2f) from:", toAccount.getBalance()),
				String.format("Checking, balance %.2f", checkingAccount.getBalance()), 
				String.format("Savings, balance %.2f", savingsAccount.getBalance()), 
				String.format("Money Market, balance %.2f", moneyMarketAccount.getBalance()),
		"Cancel"}; 
	}
	void updateCHOOSE_TRANSFER_AMOUNT() {
		CHOOSE_TRANSFER_AMOUNT = new String[] {
				String.format("Transfer amount: " + amountStr),
				String.format(""),
				String.format(toAccountStr + ", balance %.2f", toAccount.getBalance()), 
				String.format(fromAccountStr + ", balance %.2f", fromAccount.getBalance()), 
		"Cancel"}; 
	}
	void updateXFER_FROM() {
		XFER_FROM = new String[] {
				String.format("Transfer to " + toAccountStr +" ($%.2f) from:", toAccount.getBalance()),
				String.format("Checking, balance $%.2f", checkingAccount.getBalance()), 
				String.format("Savings, balance $%.2f", savingsAccount.getBalance()), 
				String.format("Money Market, balance $%.2f", moneyMarketAccount.getBalance()),
		"Cancel"}; 
	}

	void updateXFER() {
		XFER = new String[] {
				String.format("Transfer amount: " + amountStr),
				String.format(""),
				String.format(toAccountStr + ", balance $%.2f", toAccount.getBalance()), 
				String.format(fromAccountStr + ", balance $%.2f", fromAccount.getBalance()), 
		"Cancel"}; 
	}
	void updateXFER_ERROR() {
		String temp = amountStr;
		while(temp.indexOf('_') >= 0) temp = temp.replace("_", "");
		XFER_ERROR = new String[] {
				String.format("Not sufficient funds for transfer: " + temp),
				String.format(toAccountStr + ", balance $%.2f", toAccount.getBalance()), 
				String.format(fromAccountStr + ", balance $%.2f", fromAccount.getBalance()), 
				String.format("OK, start over"),
		"Cancel"}; 
	}
	void updateXFER_RESULT() {
		int num = 0;
		LocalDate finalDate = nextPaymentDate.minusMonths(1);
		BigDecimal temp = toAccount.getBalance();
		temp = temp.setScale(2, RoundingMode.HALF_EVEN);
		BigDecimal divisor = new BigDecimal("1200");
		while(temp.compareTo(this.toAccountPayment) >= 0) {
			BigDecimal interest = temp.multiply(toAccountAPR).divide(divisor, 2, RoundingMode.HALF_EVEN);
			interest = interest.setScale(2, RoundingMode.HALF_EVEN);
			temp = temp.add(interest);
			temp = temp.subtract(this.toAccountPayment);
			finalDate = finalDate.plusMonths(1);
			num++;
		}
		BigDecimal interest = temp.multiply(toAccountAPR).divide(divisor, 2, RoundingMode.HALF_EVEN);
		interest = interest.setScale(2, RoundingMode.HALF_EVEN);
		temp = temp.add(interest);
		finalDate = finalDate.plusMonths(1);
		
		XFER_RESULT = new String[] {
				String.format(toAccountStr + ", balance $%.2f, payment plan:", toAccount.getBalance()), 
				String.format("%d payments of $%.2f and", num, toAccountPayment),
				String.format("final payment of approximately $%.2f on %s", temp, finalDate), 
				String.format("OK"),
		"Cancel"}; 
	}

	public String info() {
		return ": " 
				+ (fromAccount==null?"null/":String.format("%.2f/",fromAccount.getBalance()))
				+ (toAccount==null?"null/":String.format("%.2f/",toAccount.getBalance()))
				+ (checkingAccount==null?"null/":String.format("%.2f/",checkingAccount.getBalance()))
				+ (savingsAccount==null?"null/":String.format("%.2f/",savingsAccount.getBalance()))
				+ (moneyMarketAccount==null?"null/":String.format("%.2f/",moneyMarketAccount.getBalance()))
				+ (mortgageAccount==null?"null/":String.format("%.2f/",mortgageAccount.getBalance()))
				+ (carLoanAccount==null?"null/":String.format("%.2f/",carLoanAccount.getBalance()))
				+ (personalLoanAccount==null?"null/":String.format("%.2f/",personalLoanAccount.getBalance()));
	}
		
	private ATMInternals() {
	}

	public static class Builder {
		Account checkingAccount;
		Account savingsAccount;
		Account moneyMarketAccount;
		Account mortgageAccount;
		Account carLoanAccount;
		Account personalLoanAccount;
		BigDecimal mortgagePmt;
		BigDecimal carLoanPmt;
		BigDecimal personalLoanPmt;
		BigDecimal mortgageAPR;
		BigDecimal carLoanAPR;
		BigDecimal personalLoanAPR; 
		LocalDate nextPaymentDate;

		public Builder withChecking(String amount) {
			checkingAccount = new Account(amount, "Checking");
			return this;
		}
		public Builder withSavings(String amount) {
			savingsAccount = new Account(amount, "Savings");
			return this;
		}
		public Builder withMoneyMarket(String amount) {
			moneyMarketAccount = new Account(amount, "Money Mkt");
			return this;
		}
		public Builder withMortgage(String amount) {
			mortgageAccount = new Account(amount, "Mortgage");
			return this;
		}
		public Builder withCarLoan(String amount) {
			carLoanAccount = new Account(amount, "Car Loan");
			return this;
		}
		public Builder withPersonalLoan(String amount) {
			personalLoanAccount = new Account(amount, "Personal Loan");
			return this;
		}
		public Builder withMortgagePayment(String amount) {
			mortgagePmt = new BigDecimal(amount);
			return this;
		}
		public Builder withCarLoanPayment(String amount) {
			carLoanPmt = new BigDecimal(amount);
			return this;
		}
		public Builder withPersonalLoanPayment(String amount) {
			personalLoanPmt = new BigDecimal(amount);
			return this;
		}
		public Builder withMortgageRate(String amount) {
			mortgageAPR = new BigDecimal(amount);
			return this;
		}
		public Builder withCarLoanRate(String amount) {
			carLoanAPR = new BigDecimal(amount);
			return this;
		}
		public Builder withPersonalLoanRate(String amount) {
			personalLoanAPR = new BigDecimal(amount);
			return this;
		}
		public Builder withNextPaymentDate(LocalDate date) {
			nextPaymentDate = date;
			return this;
		}
		public ATMInternals initialize() {
			var newObject = new ATMInternals();
			newObject.checkingAccount = checkingAccount;
			newObject.savingsAccount = savingsAccount;
			newObject.moneyMarketAccount = moneyMarketAccount;
			newObject.mortgageAccount = mortgageAccount;
			newObject.carLoanAccount = carLoanAccount;
			newObject.personalLoanAccount = personalLoanAccount;
			newObject.mortgagePmt = mortgagePmt;
			newObject.carLoanPmt = carLoanPmt;
			newObject.personalLoanPmt = personalLoanPmt;
			newObject.mortgageAPR = mortgageAPR;
			newObject.carLoanAPR = carLoanAPR;
			newObject.personalLoanAPR = personalLoanAPR; 
			newObject.nextPaymentDate = nextPaymentDate;
			
			return newObject;
		}
	}
}
