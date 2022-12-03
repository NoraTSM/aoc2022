package aoc.two;

import org.eclipse.collections.api.factory.Maps;
import org.eclipse.collections.api.list.ListIterable;
import org.eclipse.collections.api.map.MutableMap;
import org.eclipse.collections.impl.utility.ArrayIterate;

import static aoc.two.Rochambeau.Hand.PAPER;
import static aoc.two.Rochambeau.Hand.ROCK;
import static aoc.two.Rochambeau.Hand.SCISSORS;
import static aoc.two.Rochambeau.Outcome.DRAW;
import static aoc.two.Rochambeau.Outcome.LOSE;
import static aoc.two.Rochambeau.Outcome.WIN;

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

        Hand yours = Hand.getHand(pair[1].charAt(0));
        Hand opponent = Hand.getHand(pair[0].charAt(0));
        int score = yours.score;

        return score + switch (opponent) {
            case ROCK -> switch (yours) { 
                case PAPER -> WIN.score; 
                case ROCK -> DRAW.score; 
                default -> LOSE.score;
            };
            case PAPER -> switch (yours) { 
                case SCISSORS -> WIN.score; 
                case PAPER -> DRAW.score;
                default -> LOSE.score;
            };
            case SCISSORS -> switch (yours) {
                case ROCK -> WIN.score;
                case SCISSORS -> DRAW.score;
                default -> LOSE.score;
            };
        };

    }

    public static int calculateRoundTwo(String[] pair) {

        Outcome outcome = Outcome.getOutcome(pair[1].charAt(0));
        Hand opponent = Hand.getHand(pair[0].charAt(0));
        
        return outcome.score + switch (outcome) {
            case LOSE -> switch (opponent) {
                case ROCK -> SCISSORS.score; 
                case PAPER -> ROCK.score; 
                case SCISSORS -> PAPER.score; 
            };
            case DRAW -> opponent.score;
            case WIN -> switch (opponent) {
                case ROCK -> PAPER.score; 
                case PAPER -> SCISSORS.score;
                case SCISSORS -> ROCK.score;
            };
        };

    }


    enum Hand {

        ROCK('X', 'A', 1),
        PAPER('Y', 'B', 2),
        SCISSORS('Z', 'C', 3);

        public final char code;
        public final char other;
        public final int score;
        
        private static MutableMap<Character, Hand> hands = Maps.mutable.empty();

        static {
            for (Hand h : values()) {
                hands.put(h.code, h);
                hands.put(h.other, h);
            }
        }


        private Hand(char code, char other, int score) {
            this.code = code;
            this.other = other;
            this.score = score;
        }
        
        public static Hand getHand(Character code) {
            return hands.get(code);
        }
        
    }

    enum Outcome {

        LOSE('X', 0),
        DRAW('Y', 3),
        WIN('Z', 6);

        public final char code;
        public final int score;

        private static MutableMap<Character, Outcome> outcomes = Maps.mutable.empty();

        static {
            for (Outcome o : values()) {
                outcomes.put(o.code, o);
            }
        }


        private Outcome(char code, int score) {
            this.code = code;
            this.score = score;
        }

        public static Outcome getOutcome(Character code) {
            return outcomes.get(code);
        }


    }



}
