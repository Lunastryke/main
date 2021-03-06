package seedu.address.testutil.transaction;

import seedu.address.model.drink.Date;
import seedu.address.model.drink.Drink;
import seedu.address.model.drink.Name;
import seedu.address.model.drink.Price;
import seedu.address.model.drink.Quantity;
import seedu.address.model.transaction.Transaction;
import seedu.address.model.transaction.TransactionId;
import seedu.address.model.transaction.TransactionType;

/**
 * A utility class to help with building Transaction objects.
 */
public class TransactionBuilder {
    public static final String DEFAULT_NAME = "Coca Cola";
    public static final String DEFAULT_TRANSACTION_TYPE = "SALE";
    public static final String DEFAULT_DATE = new Date().toString();
    public static final String DEFAULT_QUANTITY = "200";
    public static final String DEFAULT_AMOUNT_EARNED = "10.00";
    public static final String DEFAULT_ID = "10001";

    private TransactionType transactionType;
    private Date transactionDate;
    private Drink drinkTransacted;
    private Quantity quantityTransacted;
    private Price amountMoney;
    private TransactionId id;


    public TransactionBuilder() {
        transactionType = TransactionType.valueOf(DEFAULT_TRANSACTION_TYPE);
        transactionDate = new Date(DEFAULT_DATE);
        drinkTransacted = new Drink(new Name(DEFAULT_NAME));
        quantityTransacted = new Quantity(DEFAULT_QUANTITY);
        amountMoney = new Price(DEFAULT_AMOUNT_EARNED);
        id = new TransactionId(DEFAULT_ID);
    }

    /**
     * Initializes the TransactionBuilder with the data of {@code transactionToCopy}.
     * @param transactionToCopy
     */
    public TransactionBuilder(Transaction transactionToCopy) {
        transactionType = transactionToCopy.getTransactionType();
        transactionDate = transactionToCopy.getTransactionDate();
        drinkTransacted = transactionToCopy.getDrinkTransacted();
        quantityTransacted = transactionToCopy.getQuantityTransacted();
        amountMoney = transactionToCopy.getAmountMoney();
        transactionDate = transactionToCopy.getTransactionDate();
        id = transactionToCopy.getTransactionId();
    }

    /**
     * Sets the {@code TransactionType} of the {@code Transaction} that we are building.
     */
    public TransactionBuilder withTransactionType(String type) {
        transactionType = TransactionType.valueOf(type.toUpperCase());
        return this;
    }

    /**
     * Sets the {@code Date} of the {@code Transaction} that we are building.
     */
    public TransactionBuilder withDate(String date) {
        transactionDate = new Date(date);
        return this;
    }

    /**
     * Sets the {@code Date} of the {@code Transaction} that we are building to the current date
     */
    public TransactionBuilder withDateToday() {
        transactionDate = new Date();
        return this;
    }



    /**
     * Sets the {@code Date} of the {@code Transaction} that we are building.
     */
    public TransactionBuilder withDrink(String drinkName) {
        drinkTransacted = new Drink(new Name(drinkName));
        return this;
    }

    /**
     * Sets the {@code Quantity} of the {@code Transaction} that we are building.
     */
    public TransactionBuilder withQuantity(String quantity) {
        quantityTransacted = new Quantity(quantity);
        return this;
    }

    /**
     * Sets the {@code Price amountMoney} of the {@code Transaction} that we are building.
     */
    public TransactionBuilder withAmountMoney(String amountMoney) {
        this.amountMoney = new Price(amountMoney);
        return this;
    }

    /**
     * Creates and returns a new Transaction object with given attributes
     */
    public Transaction build() {
        return new Transaction(transactionType, transactionDate, drinkTransacted, quantityTransacted, amountMoney, id);
    }
}
