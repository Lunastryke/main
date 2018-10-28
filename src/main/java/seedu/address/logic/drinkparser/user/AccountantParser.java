package seedu.address.logic.drinkparser.user;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.logic.drinkcommands.DrinkCommand;
import seedu.address.logic.drinkcommands.ExitCommand;
import seedu.address.logic.drinkcommands.HelpCommand;
import seedu.address.logic.drinkcommands.user.ChangePasswordCommand;
import seedu.address.logic.drinkparser.exceptions.DrinkParseException;

/**
 * Parses user input.
 */
public class AccountantParser {

    /**
     * Used for initial separation of command word and args.
     */
    private static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");

    /**
     * Parses user input into command for execution.
     *
     * @param userInput full user input string
     * @return the command based on the user input
     * @throws DrinkParseException if the user input does not conform the expected format
     */
    public DrinkCommand parseCommand(String userInput) throws DrinkParseException {
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim());
        if (!matcher.matches()) {
            throw new DrinkParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE));
        }

        String commandWord = matcher.group("commandWord");
        final String arguments = matcher.group("arguments");

        switch (commandWord) {

        //case ChangePasswordCommand.COMMAND_WORD:
        //    return new ChangePasswordCommandParser().parse(arguments);

        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();

        case HelpCommand.COMMAND_WORD:
            return new HelpCommand();

        default:
            throw new DrinkParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

}
