package org.stas.demo;

/**
 * entry point for the application
 */
public class Application {

    /**
     * so far it is very simple, no args - no calculation, at least input file is supposed to be specified
     * @param args
     */
    public static void main(String[] args) {
        System.out.println("starting competition points calculation...");
        if (args.length == 0) {
            System.out.println("not enough arguments");
            System.out.println("the arguments format is as follows:");
            System.out.println("java -jar competitioncalc.jar <input_file> [output_file]");
        } else {
            new CompetitionCalculator().run(args);
        }
    }
}
