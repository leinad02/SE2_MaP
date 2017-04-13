package at.michi.map;

/**
 * Created by Engin on 13.04.2017.
 */

        import android.os.Bundle;

        import java.util.HashMap;
        import java.util.Map;
        import java.util.Scanner;

public class Spielbrett_Alpha {

    static int turn = 0;
    static int[] gameBoard = new int[100];
    static Map<Integer, Integer> portalPositionValue;
    static Map<Integer, Integer> monsterPositionValue;
    static Map<Integer, Integer> riskPositionValue;
    static int userPosition = 0;
    static int rivalPosition = 0;


    public static void gameBoard() {
        portalPositionValue = new HashMap<>();
        monsterPositionValue = new HashMap<>();
        riskPositionValue = new HashMap<>();

        /* Achtung: Spielbrettgröße und die Anzahl der Monster & Portale & Risiken können sich jederzeit ändern.
         Ich habe einfach den Array für das Spielbrett auf 100 und die Anzahl der Begegnungen auf 3 x 7 gesetzt gesetzt */

        for (int i = 0; i < 7; i++) {
            int portalPositionKey = (int) (Math.random() * 85) + 10;

            //Todo: Wenn das Feld bereits ein Begegnungsfeld ist, dann wähle ein neues Feld aus

            int monsterPositionKey = (int) (Math.random() * 95) + 15;

            //Todo: Wenn das Feld bereits ein Begegnungsfeld ist, dann wähle ein neues Feld aus

            int riskPositionKey = (int) (Math.random() * 85) + 10;

            //Todo: Wenn das Feld bereits ein Begegnungsfeld ist, dann wähle ein neues Feld aus

        }
    }

    public static int rollDice(int repeat) {

        // Todo: Würfeln per schütteln oder klicken
        return 0;
    }

    public static boolean userTurn() {
        if (turn % 2 == 0) {
            turn++;
            return true;
        }
        turn++;
        return false;
    }


    public static void newUserPosition(int rolledNo) {
        userPosition = userPosition + rolledNo;
        System.out.println("Deine neue Position ist:" + userPosition);
        userPosition = checkMonsterOrPortalOrRisk(userPosition);
    }

    public static void newrivalPosition(int rolledNo) {
        rivalPosition = rivalPosition + rolledNo;
        rivalPosition = checkMonsterOrPortalOrRisk(userPosition);
    }

    private static int checkMonsterOrPortalOrRisk(int position) {
        if (monsterPositionValue.keySet().contains(position)) {
            System.out.println("Ohhh Nein! Ein MONSTER greift dich an!");
            position = position - monsterPositionValue.get(position);
        } else if (portalPositionValue.keySet().contains(position)) {
            System.out.println("Es ist ein magisches PORTAL!");
            position = position + portalPositionValue.get(position);
        } else if (riskPositionValue.keySet().contains(position)) {
            System.out.println("WoW, ein Risikofeld. Du ziehst jetzt eine RISIKOKARTE!");
            drawRiskcard();
        }
        return position;
    }

    public static void drawRiskcard(){

        // Todo: Risikokarten einfügen
        // Todo: Risikokarten müssen randomisiert werden
    }

    public static void main(String args[]) {
        System.out.println("WILLKOMMEN BEI MONSTERS & PORTALS!");
        gameBoard();
        while (userPosition != 100 && rivalPosition != 100) {
            if (userTurn()) {
                System.out.println("Du bist dran:");
                int rolledNo = rollDice(0);
                System.out.println("Ziehe weiter:");
                newUserPosition(rolledNo);
            } else {
                System.out.println("Dein Gegner ist dran:");
                int rolledNo = rollDice(0);
                System.out.println("Dein Gegner zieht weiter:");
                newrivalPosition(rolledNo);
            }
        }
        if (userPosition == 100) {
            System.out.println("Du hast GEWONNEN!");
        } else if (rivalPosition == 100) {
            System.out.println("Dein Gegner hat GEWONNEN!");
        }
    }
}
