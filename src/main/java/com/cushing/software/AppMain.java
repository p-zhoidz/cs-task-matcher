package com.cushing.software;

import com.cushing.software.predicates.ModeFactory;
import com.cushing.software.settings.Settings;
import com.cushing.software.utils.FileReaderUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Stream;

/**
 * @author p.zhoidz.
 */
public class AppMain {
    private static final Settings SETTINGS = Settings.SettingsHolder.HOLDER_INSTANCE;

    /**
     * Application main entry point.
     *
     * @param args entry point arguments.
     *             Skipped in terms of current implementations
     */
    public static void main(String... args) {

        try (Scanner scanner = new Scanner(System.in)) {
            Predicate<String> predicate = null;
            while (predicate == null) {
                System.out.println("Please select one of the following options (1-4):");
                System.out.println("1. Output all the lines from input.txt that match exactly any pattern in patterns file");
                System.out.println("2. Output all the lines from input.txt that contain a match from patterns file somewhere in the line.");
                System.out.println("3. Output all the lines from input.txt that contain a match with edit distance <= 1 patterns file");
                System.out.println("4. Terminate application");

                String optionStr = scanner.nextLine();
                predicate = getPredicate(optionStr);
            }

            Path path = Paths.get(SETTINGS.getInputFilePath());

            //Reading file as stream. Stream should be closed.
            try (Stream<String> stream = Files.lines(path)) {
                stream.filter(predicate)
                        .forEach(System.out::println);
            }

        } catch (Throwable exception) {
            System.out.println("An exception has occurred. Message:");
            System.out.println(exception.getMessage());
        }
    }

    /**
     * Get predicate based on mode.
     *
     * @param selectedOption selected by user option.
     * @return appropriate predicate based on user input. Also application can be terminated.
     * @throws IOException if patterns file fails to initialize.
     */
    private static Predicate<String> getPredicate(String selectedOption) throws IOException {
        ModeFactory factory = new ModeFactory();

        switch (selectedOption) {
            case "1":
                return factory.getPredicate(ModeFactory.Type.SIMPLE, readPatterns());
            case "2":
                return factory.getPredicate(ModeFactory.Type.CONTAINS, readPatterns());
            case "3":
                return factory.getPredicate(ModeFactory.Type.EDIT_DISTANCE, readPatterns());
            case "4":
                System.exit(0);
            default:
                return null;
        }
    }

    /**
     * Read patterns
     *
     * @return Set of pattern strings.
     * @throws IOException standard I/O exception cases.
     */
    private static Set<String> readPatterns() throws IOException {
        return new HashSet<>(FileReaderUtils.getContent(SETTINGS.getPatternsFilePath()));
    }
}
