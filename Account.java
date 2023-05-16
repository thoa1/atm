package assignment04;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;

public class Account {
	private BigDecimal balance;
	private String type;
	NumberFormat usdFormat = NumberFormat.getCurrencyInstance(Locale.US);
	
	public Account(String balanceIn, String typeIn) {
		usdFormat.setMinimumFractionDigits(2);
		usdFormat.setMaximumFractionDigits(2);
		balance = new BigDecimal(balanceIn);
		type = typeIn;
	}
	public void deposit(BigDecimal amount) {
		balance = balance.add(amount);
	}
	public void withdraw(BigDecimal amount) {
		balance = balance.subtract(amount);
	}
	public BigDecimal getBalance() {
		return balance;
	}
	@Override
	public String toString() {
		System.out.println( usdFormat.format(balance.doubleValue()));
		return String.format("%s $%.2f", type, balance);
	}	
}
