package seedu.address.logic.commands.stocktaker;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DRINK_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_QUANTITY;

import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.drink.Drink;
import seedu.address.model.drink.Quantity;
import seedu.address.model.drink.exceptions.InsufficientQuantityException;
import seedu.address.model.transaction.Transaction;
import seedu.address.model.transaction.TransactionType;
import seedu.address.model.user.stocktaker.StockTakerModel;

/**
 * Sell a drink from inventory.
 * // TODO: STUB
 */
public class SellDrinkCommand extends Command {

    public static final String COMMAND_WORD = "sell";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Sell an drink that is recorded in Drink I/O. \n"
            + "Parameters: "
            + PREFIX_DRINK_NAME + "DRINK NAME "
            //+ PREFIX_DATE + "DATE SOLD "
            + PREFIX_QUANTITY + "QUANTITY SOLD \n"
            //+ PREFIX_PRICE + "TOTAL REVENUE "
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_DRINK_NAME + "Coca Cola Original "
            //+ PREFIX_DATE + "10/06/18 "
            + PREFIX_QUANTITY + "12 ";
    //+ PREFIX_PRICE + "345.68 ";

    public static final String MESSAGE_SUCCESS = "Sale transaction recorded!\n"
            + "%1$s sold on %2$s. Quantity sold: %3$s";
    public static final String MESSAGE_DRINK_NOT_FOUND = "The drink entered does not exist in the inventory list";
    public static final String MESSAGE_FAILURE = "Insufficient quantity in stock to perform operation";

    private final Drink drink;
    // private final Date date; // TODO: add date support
    private final Quantity quantity;
    private final Transaction transaction;

    /**
     * Creates a SellDrinkCommand to sell the specified {@code drink}
     */
    public SellDrinkCommand(Drink drink, Quantity quantity) {
        requireAllNonNull(drink, quantity);
        this.drink = drink;
        this.quantity = quantity;
        transaction = new Transaction(TransactionType.SALE, drink, quantity);
        // actual amount transacted is computed at inventoryList level
    }

    @Override
    public CommandResult execute(Model model, CommandHistory history) throws CommandException {
        requireAllNonNull(model);
        assert model instanceof StockTakerModel;
        StockTakerModel stockTakerModel = (StockTakerModel) model;

        if (!stockTakerModel.hasDrink(drink)) {
            throw new CommandException(MESSAGE_DRINK_NOT_FOUND);
        }

        try {
            stockTakerModel.sellDrink(transaction);
        } catch (InsufficientQuantityException e) {
            throw new CommandException(MESSAGE_FAILURE);
        }

        return new CommandResult(String.format(MESSAGE_SUCCESS, drink.getName(), transaction.getTransactionDate(),
                quantity));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof SellDrinkCommand); // instanceof handles nulls;
    }
}
