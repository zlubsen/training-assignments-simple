package eu.sig.training.ch04;

// tag::CheckingAccount[]
    public Transfer makeTransfer(String counterAccount, Money amount) 
        throws BusinessException {
        // 1. Assuming result is 9-digit bank account number, validate 11-test:
        if (passesElevenTest(counterAccount)) {
            // 2. Look up counter account and make transfer object:
            CheckingAccount acct = Accounts.findAcctByNumber(counterAccount);
            Transfer result = new Transfer(this, acct, amount); // <2>
            // 3. Check whether withdrawal is to registered counter account:
            if (result.getCounterAccount().equals(this.registeredCounterAccount)) {
                return result;
            } else {
                throw new BusinessException("Counter-account not registered!");
            }
        } else {
            throw new BusinessException("Invalid account number!!");
        }
    }
	
	private boolean passesElevenTest(String counterAccount) {
		int sum = 0;
		for (int i = 0; i < counterAccount.length(); i++) {
        	char character = counterAccount.charAt(i);
			int characterValue = Character.getNumericValue(character);
			sum = sum + (9 - i) * characterValue;
			}
		return (sum % 11 == 0);
	}

    public void addInterest() {
        Money interest = balance.multiply(INTEREST_PERCENTAGE);
        if (interest.greaterThan(0)) {
            balance.add(interest);
        } else {
            balance.substract(interest);
        }
    }
}
// end::CheckingAccount[]
