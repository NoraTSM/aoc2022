package aoc.two;

import org.eclipse.collections.api.list.ListIterable;
import org.eclipse.collections.impl.utility.ArrayIterate;

public class Rochambeau {
    public long calculateScore(String input) {
        return moves(input).collect(Rochambeau::calculateRound).sumOfInt(Integer::intValue);
    }

    public long calculateScoreTwo(String input) {
        return moves(input).collect(Rochambeau::calculateRoundTwo).sumOfInt(Integer::intValue);
    }

    public ListIterable<String[]> moves(String input) {
        return ArrayIterate.collect(input.split("\n"), each -> each.trim().split(" "));
    }

    public static int calculateRound(String[] pair) {

        char yours = pair[1].charAt(0);
        char opponent = pair[0].charAt(0);
        int score = handScore(yours);

        return score + switch (opponent) {
            case 'A' -> switch (yours) { //ROCK
                case 'Y' -> 6; //PAPER
                case 'X' -> 3; //ROCK
                default -> 0;
            };
            case 'B' -> switch (yours) { //PAPER
                case 'Z' -> 6; //SCISSORS
                case 'Y' -> 3; //PAPER
                default -> 0;
            };
            case 'C' -> switch (yours) { //SCISSORS
                case 'X' -> 6; //ROCK
                case 'Z' -> 3; //SCISSORS
                default -> 0;
            };
            default -> 0;
        };

    }

    private static int handScore(char yours) {
        return switch (yours) {
            case 'X' -> 1; //ROCK
            case 'Y' -> 2; //PAPER
            case 'Z' -> 3; //SCISSORS
            default -> 0;
        };
    }

    public static int calculateRoundTwo(String[] pair) {

        char outcome = pair[1].charAt(0);
        char opponent = pair[0].charAt(0);
        int score = switch (outcome) {
            case 'X' -> 0; //LOSE
            case 'Y' -> 3; //DRAW
            case 'Z' -> 6; //WIN
            default -> 0;
        };

        return score + switch (outcome) {
            case 'X' -> switch (opponent) { //LOSE
                case 'A' -> 3; //ROCK -> CHOOSE SCISSORS
                case 'B' -> 1; //PAPER -> CHOOSE ROCK
                case 'C' -> 2; //SCISSORS -> CHOOSE PAPER
                default -> 0;
            };
            case 'Y' -> switch (opponent) { //DRAW
                case 'A' -> 1; //ROCK
                case 'B' -> 2; //PAPER
                case 'C' -> 3; //SCISSORS
                default -> 0;
            };
            case 'Z' -> switch (opponent) { //WIN
                case 'A' -> 2; //ROCK -> CHOOSE PAPER
                case 'B' -> 3; //PAPER -> CHOOSE SCISSORS
                case 'C' -> 1; //SCISSORS -> CHOOSE ROCK
                default -> 0;
            };
            default -> 0;
        };

    }

}
