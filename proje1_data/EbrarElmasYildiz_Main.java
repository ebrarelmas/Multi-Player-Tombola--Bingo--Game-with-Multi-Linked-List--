/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proje1_data;

import java.util.*;


public class EbrarElmasYildiz_Main {

    public static void main(String[] args) {
        EbrarElmasYildiz_MultiLinkedList<Integer> playerCard1 = new EbrarElmasYildiz_MultiLinkedList<>();
        EbrarElmasYildiz_MultiLinkedList<Integer> playerCard2 = new EbrarElmasYildiz_MultiLinkedList<>();
        
        /*
        // Oyuncuların kartlarını oluştur
        Integer[][] card1Data = {{5, -1, 24, -1, 46, -1, 61, 74, -1},
                                 {-1, 11, -1, 32, 45, 59, 67, -1, -1},
                                 {-1, 18, 22, 35, -1, -1, -1, 73, 89}};
        playerCard1.setCard(card1Data);
  
        Integer[][] card2Data = {{6, -1, 22, -1, 45, -1, 60, 73, -1},
                                 {-1, 10, -1, 31, 47, 58, 68, -1, -1},
                                 {-1, 17, 26, 38, -1, -1, -1, 79, 86}};
        playerCard2.setCard(card2Data);
        */

        System.out.println("Player 1's Card:");
        playerCard1.initializeCard();
        playerCard1.display();
        System.out.println("------------------------------------------------------------------");
        System.out.println("\nPlayer 2's Card:");
        playerCard2.initializeCard();
        playerCard2.display();
        System.out.println("------------------------------------------------------------------");

        playGame(playerCard1, playerCard2);

    }

    public static void playGame(EbrarElmasYildiz_MultiLinkedList<Integer> playerCard1, EbrarElmasYildiz_MultiLinkedList<Integer> playerCard2) {
        int[] randomPermutation = generatePermutation(90);

        boolean firstBingoCalled1 = false;
        boolean firstBingoCalled2 = false;
        boolean secondBingoCalled2 = false;
        boolean secondBingoCalled1 = false;
        boolean tombalaCalled = false;

        for (int i = 0; i < randomPermutation.length; i++) {
            System.out.println("------------------------------------------------------------------");

            System.out.println("\nNumber drawn: " + randomPermutation[i]);

            boolean foundInCard1 = playerCard1.markFound(randomPermutation[i]);
            boolean foundInCard2 = playerCard2.markFound(randomPermutation[i]);

            System.out.println("\nPlayer 1's Card:");
            playerCard1.display();
            System.out.println("\nPlayer 2's Card:");
            playerCard2.display();

            if (foundInCard1) {
                System.out.println("Player 1: Number found!");
            }
            if (foundInCard2) {
                System.out.println("Player 2: Number found!");
            }

            if (!firstBingoCalled1 && playerCard1.checkFirstBingo()) {
                System.out.println("Player 1: Bingo (First Bingo)!");
                firstBingoCalled1 = true;
            }
            if (!firstBingoCalled2 && playerCard2.checkFirstBingo()) {
                System.out.println("Player 2: Bingo (First Bingo)!");
                firstBingoCalled2 = true;
            }

            if (!secondBingoCalled1 && playerCard1.checkSecondBingo()) {
                System.out.println("Player 1: Bingo (Second Bingo)!!");
                secondBingoCalled1 = true;
            }
            if (!secondBingoCalled2 && playerCard2.checkSecondBingo()) {
                System.out.println("Player 2: Bingo (Second Bingo)!!");
                secondBingoCalled2 = true;
            }

            if (!tombalaCalled && (playerCard1.checkTombala() || playerCard2.checkTombala())) {
                if (playerCard1.checkTombala() && !tombalaCalled) {
                    System.out.println("Player 1: Tombala!!!");
                }
                if (playerCard2.checkTombala() && !tombalaCalled) {
                    System.out.println("Player 2: Tombala!!!");
                }
                tombalaCalled = true;
                break;
            }
        }
    }

    public static int[] generatePermutation(int n) {
        int[] permutation = new int[n];
        for (int i = 0; i < n; i++) {
            permutation[i] = i + 1;
        }
        Random rand = new Random();
        for (int i = n - 1; i > 0; i--) {
            int index = rand.nextInt(i + 1);
            int temp = permutation[index];
            permutation[index] = permutation[i];
            permutation[i] = temp;
        }
        return permutation;
    }

}

