package Medium;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

class CardCollection {
    private Map<String, List<String>> cardMap;

    public CardCollection() {
        cardMap = new HashMap<>();
    }

    public void addCard(String symbol, String card) {
        cardMap.putIfAbsent(symbol, new ArrayList<>());
        cardMap.get(symbol).add(card);
    }

    public List<String> getCardsBySymbol(String symbol) {
        return cardMap.getOrDefault(symbol, new ArrayList<>());
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CardCollection collection = new CardCollection();
        
        collection.addCard("Hearts", "Ace");
        collection.addCard("Hearts", "King");
        collection.addCard("Spades", "Queen");
        collection.addCard("Diamonds", "Jack");
        
        System.out.print("Enter card symbol to search: ");
        String symbol = scanner.nextLine();
        
        List<String> cards = collection.getCardsBySymbol(symbol);
        if (cards.isEmpty()) {
            System.out.println("No cards found for symbol: " + symbol);
        } else {
            System.out.println("Cards for " + symbol + ": " + cards);
        }
        
        scanner.close();
    }
}
