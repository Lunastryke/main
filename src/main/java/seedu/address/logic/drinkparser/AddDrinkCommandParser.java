package seedu.address.logic.drinkparser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DEFAULT_SELLING_PRICE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DRINK_ITEM;

import java.util.stream.Stream;

import seedu.address.logic.drinkcommands.AddDrinkCommand;
import seedu.address.logic.drinkcommands.AddDrinkCommand;
import seedu.address.logic.drinkparser.exceptions.DrinkParseException;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.Prefix;

/**
 * Parses input arguments and creates a new AddDrinkCommand object
 */
public class AddDrinkCommandParser implements DrinkParser<AddDrinkCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddDrinkCommand
     * and returns an AddDrinkCommand object for execution.
     *
     * @throws DrinkParseException if the user input does not conform the expected format
     */
    public AddDrinkCommand parse(String args) throws DrinkParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_DRINK_ITEM, PREFIX_DEFAULT_SELLING_PRICE);

        if (!arePrefixesPresent(argMultimap, PREFIX_DRINK_ITEM, PREFIX_DEFAULT_SELLING_PRICE)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new DrinkParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddItemCommand.MESSAGE_USAGE));
        }

        String drinkName = ParserUtil.parseItemName(argMultimap.getValue(PREFIX_DRINK_ITEM).get());
        String defaultSellingPrice = ParserUtil.parseDefaultSellingPrice(
                argMultimap.getValue(PREFIX_DEFAULT_SELLING_PRICE).get());

        return new AddItemCommand(drinkName, defaultSellingPrice);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
