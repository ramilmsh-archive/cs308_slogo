package interpreter.util;

import controller.channel.PubSub;
import controller.channel.messages.LanguageChange;
import controller.channel.messages.Message;
import interpreter.exceptions.ValueException;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * Class that translates function names from a given language
 * into standard naming scheme, as defined in Language.properties
 *
 * @author ramilmsh
 */
public class Translator {
    private static final String[] languages = {
            "Chinese",
            "English",
            "French",
            "German",
            "Italian",
            "Portuguese",
            "Russian",
            "Spanish"
    };

    private static final Pattern comment = Pattern.compile("\\s*#.*");
    private static final Pattern assign = Pattern.compile("\\s*=\\s*");
    private static final Pattern or = Pattern.compile("\\s*\\|\\s*");

    private HashMap<String, HashMap<String, String>> cache;
    private HashMap<String, String> conversions;

    private String language;
    private PubSub pubsub;

    public Translator(PubSub pubsub) throws ValueException {
        this(pubsub, "English");
    }

    /**
     * Create a translator from language, with i/o
     *
     * @param language: input language
     * @throws ValueException: if language is not defined
     */
    public Translator(PubSub pubsub, String language) throws ValueException {
        this.language = language;
        this.pubsub = pubsub;

        cache = new HashMap<>();
        loadLanguages(languages);
        setLanguage(language);
    }

    /**
     * Set the language of the translator
     *
     * @param language: input language
     */
    public void setLanguage(String language) {
        conversions = cache.get(language);
        if (conversions == null)
            conversions = cache.get(this.language);
        this.language = language;

    }

    /**
     * Get function name of predefined functions
     *
     * @param command: command name in language
     * @return command name in standardized naming scheme
     */
    public String translate(String command) {
        if (conversions == null)
            return null;

        String name = conversions.get(command);

        return name == null ? command : name;
    }

    public void listenChangeLanguage(Message msg) {
        LanguageChange message = (LanguageChange) msg;
        setLanguage(message.language);
    }

    /**
     * Cache the translation mappings
     *
     * @param languages: list of languages
     */
    private void loadLanguages(String[] languages) {
        loop: for (String lang : languages) {
            Scanner scanner = null;
            try {
                scanner = new Scanner(new File("resources/languages/" + lang + ".properties"));
            } catch (FileNotFoundException e) {
                // console.warning("Language " + lang + " not found");
                continue;
            }

            HashMap <String, String> map = new HashMap<>();
            cache.put(lang, map);

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (comment.matcher(line).matches())
                    continue;

                String[] tokens = assign.split(line);
                try {
                    String[] interps = or.split(tokens[1]);
                    map.put(tokens[0], tokens[0]);
                    for (String interp : interps) {
                        // I mean, regex is cool, but this is one too many
                        interp = interp.replaceAll("\\\\", "");
                        if (!map.containsKey(interp))
                            map.put(interp, tokens[0]);
                        else if (!map.get(interp).equals(tokens[0]))
                            throw new ValueException("Ambiguous mapping: " + interp + " -> " + map.get(interp) + " or " + interp + " -> " + tokens[0] + " in " + lang);
                    }
                } catch (IndexOutOfBoundsException e) {
                    // console.warning("Invalid language definition for " + lang);
                    continue loop;
                } catch (ValueException e) {
                    // console.warning(e.toString());
                }

            }
        }
    }
}
