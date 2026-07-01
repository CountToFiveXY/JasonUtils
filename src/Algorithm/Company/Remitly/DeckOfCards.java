package Algorithm.Company.Remitly;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Design a Deck of Cards
 *
 * Requirements:
 * Card:
 *   - Suit: SPADES, HEARTS, DIAMONDS, CLUBS
 *   - Rank: A,2..10,J,Q,K
 *
 * Deck:
 *   - Contains all 52 unique cards
 *
 * Q1:
 *   printDeck()
 *   Format:
 *   SUIT: <suit>, RANK: <rank>
 *
 * Q2:
 *   shuffle()
 */
public class DeckOfCards {

    static enum SUIT {
        SPADES,
        HEART,
        DIAMONDS,
        CLUBS;
    }

    static enum RANK {
        ACE("A"),
        TWO("2"),
        THREE("3"),
        FOUR("4"),
        FIVE("5"),
        SIX("6"),
        SEVEN("7"),
        EIGHT("8"),
        NINE("9"),
        TEN("10"),
        JACK("J"),
        QUEEN("Q"),
        KING("K");

        private String value;

        RANK(String value) {
            this.value = value;
        }
    }

    static class Card {
        SUIT suit;
        RANK rank;

        public Card(SUIT suit, RANK rank) {
            this.suit = suit;
            this.rank = rank;
        }

        @Override
        public String toString() {
            return String.format("%s%s", suit, rank.value);
        }
    }

    static class Deck {
        List<Card> cards;
        public Deck() {
            cards = new ArrayList<>();
            for (RANK rank: RANK.values()) {
                for (SUIT suit: SUIT.values()) {
                    cards.add(new Card(suit, rank));
                }
            }
        }

        public void printDeck() {
            for (Card card: cards) {
                System.out.println(card);
            }
        }

        public void shuffle() {
            Collections.shuffle(cards);
            Random random = new Random();
            for (int i = 51; i >=0; i--) {
                int j = random.nextInt(i+1);
                swap(cards, i, j);
            }
        }

        private void swap( List<Card> cards, int i, int j) {
            Card temp = cards.get(i);
            cards.set(i, cards.get(j));
            cards.set(j, temp);
        }

        public Card drawCard() {
            Random random = new Random();
            int indexOfCard = random.nextInt(cards.size());
            int lastCardIndex = cards.size() - 1;

            Card card = cards.get(indexOfCard);
            cards.set(indexOfCard, cards.get(lastCardIndex));
            cards.remove(lastCardIndex);
            return card;
        }
    }


    public static void main(String[] args) {
        Deck deck = new Deck();

        System.out.println("=== Original Deck ===");
        deck.printDeck();

        System.out.println("\n=== After Shuffle ===");
        deck.shuffle();
        deck.printDeck();
        deck.drawCard();
    }

}