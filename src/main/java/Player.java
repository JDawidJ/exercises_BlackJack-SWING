import java.util.ArrayList;

public class Player {

    private String name;
    private ArrayList<Card> hand;

    public Player (String playerName) {
        hand = new ArrayList<>();
        name = playerName;
    }

    public String getName() {
        return name;
    }

    public void addCardToHand(Card newCard) {
        hand.add(newCard);
    }

    public int getHandSum() {

        int sumOfPoints = 0;
        int cardPoints;
        int numberOfAces = 0;

        for (int i = 0; i < hand.size(); i++) {

            cardPoints = hand.get(i).getCardValue().getValue();

            if (cardPoints == 11) {
                numberOfAces++;
                sumOfPoints += 11;
            } else {
                sumOfPoints += cardPoints;
            }
        }

        while (sumOfPoints > 21 && numberOfAces > 0) {
            sumOfPoints -= 10;
            numberOfAces--;
        }

        return sumOfPoints;
    }

    public String getCardsText(boolean showFirstHand) {

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < hand.size(); i++) {

            if (i == 0 && !showFirstHand) {
                sb.append("<br>[Ukryta]");
            } else {
                sb.append("<br>" + hand.get(i).toString());
            }
        }
        return sb.toString();
    }

    public String getCardsOnHand(boolean showFirstHand) {

        StringBuilder sb = new StringBuilder();
        sb.append("<html>");
        sb.append(name + ":<br>");
        sb.append(getCardsText(showFirstHand) + "<br>");

        if (showFirstHand) {
            sb.append("Punkty: [" + getHandSum() + "] <br>");
        } else {
            sb.append("Punkty: [?] <br>");
        }

        sb.append("</html>");
        return sb.toString();
    }

    public boolean checkIfBusted() {
        if (getHandSum() < 22) {
            return false;
        } else {
            return true;
        }
    }
}
