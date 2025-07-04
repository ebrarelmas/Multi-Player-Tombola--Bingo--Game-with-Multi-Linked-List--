/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proje1_data;

import java.util.Random;

/**
 *
 * @author Ebrar Yıldız ebrarelmas.yildiz@stu.fsm.edu.tr-2121221011
 */
/*
 This class represents a Multi-Linked List.
 It contains nodes with both 'next' and 'down' pointers, forming a matrix-like structure.
 'next' points to the next node in the same row, while 'down' points to the next node in the same column.
 The class provides methods for setting up a bingo card, marking numbers found, and checking for bingos and tombalas.

 */
class EbrarElmasYildiz_MultiLinkedList<T> {

    EbrarElmasYildiz_Node<T> head;
    EbrarElmasYildiz_Node[][] card;

    public EbrarElmasYildiz_MultiLinkedList() {
        this.head = null;
        card = new EbrarElmasYildiz_Node[3][9];

    }

    public void setCard(T[][] manuallyEnteredCard) {
        for (int i = 0; i < 3; i++) {
            int countNumber = 0;// Counter for the number of numbers in the row
            int countDash = 0;// Counter for the number of dashes (-1) in the row
            for (int j = 0; j < 9; j++) {
                T currentNumber = manuallyEnteredCard[i][j];
                if (!currentNumber.equals(-1)) {
                    int number = (int) currentNumber;
                    if (number < (j * 10) || number >= ((j + 1) * 10)) {// Validate the range of numbers in each column
                        if(j==0){
                            System.out.println("Hatali giris: " + (j + 1) + ". sutun " + 
                                   ((j * 10)+1) + "-" + ((j + 1) * 10 - 1) + " arasi sayilar icermelidir!");

                        }else{
                            System.out.println("Hatali giris: " + (j + 1) + ". sutun " + 
                                   (j * 10) + "-" + ((j + 1) * 10 - 1) + " arasi sayilar icermelidir!");
                            return;
                            
                        }
                    }
                    countNumber++;
                } else {
                    countDash++;
                }
                
            }
            // Validate the count of numbers and dashes in each row
            if (!(countNumber == 5 && countDash == 4)) {
                System.out.println("Hatali giris: " + (i + 1) + ". satirda 5 sayi ve 4 bosluk bulunmalidir!");
                return;

            }
        }

    // If the input data passes the validation, create the card layout
        for (int k = 0; k < 3; k++) {
            EbrarElmasYildiz_Node<T> rowHead = null;// Head node of the current row
            EbrarElmasYildiz_Node<T> prevNode = null;// Previous node

            for (int j = 0; j < 9; j++) {
                if (manuallyEnteredCard[k][j] != null) {
                    EbrarElmasYildiz_Node<T> newNode = new EbrarElmasYildiz_Node<>(manuallyEnteredCard[k][j]);
                    if (rowHead == null) {
                        rowHead = newNode;
                    } else {
                        prevNode.next = newNode;
                    }
                    prevNode = newNode;
                    
                    if (card[k][j] == null) {
                        card[k][j] = newNode;
                    }
                }
            }
            // Establish connections (down connections)
            if (k > 0) {
                for (int j = 0; j < 9; j++) {
                    if (card[k][j] != null && card[k - 1][j] != null) {
                        card[k - 1][j].down = card[k][j];
                    }
                }
            }
        }
    }

    public void display() {//kartı yazdırma
        for (int i = 0; i < 3; i++) {
            EbrarElmasYildiz_Node<T> temp = card[i][0]; // Her satırın başlangıç düğümü
            for (int j = 0; j < 9; j++) {
                if (temp != null) {
                    System.out.print(temp.data + "\t");
                    temp = temp.next;
                } else {
                    System.out.print(-1 + "\t");
                }
            }
            System.out.println();
        }
    }

   

    public void initializeCard() {
        T[][] manuallyEnteredCard = (T[][]) new Object[3][9]; // Diziyi Object olarak oluştur
        for (int i = 0; i < 3; i++) {
            EbrarElmasYildiz_Node<Integer> indexes = generateRandomIndexes();  // Generate random indexes to block cells
            int count = 1; // Counter for numbers inserted in the row
            for (int j = 0; j < 9; j++) {
                if (containsIndex(indexes, j + 1) && count <= 5) { // If the cell is not blocked and the row still needs numbers
                    int randomNumber;
                    do {
                        randomNumber = (int) generateRandomNumber(j, i); // Generate a random number within the column range
                    } while (checkDuplicate(manuallyEnteredCard, randomNumber, j)); // Increment count for each number inserted in the row
                    manuallyEnteredCard[i][j] = (T) Integer.valueOf(randomNumber);
                    count++; // Satıra eklenen her sayı için sayaç arttırılır
                } else {
                    manuallyEnteredCard[i][j] = (T) Integer.valueOf(-1); // Block the remaining cells
                }
                insert(manuallyEnteredCard[i][j]); //insert the number into the card
            }
        }
        setCard(manuallyEnteredCard);// constructs the bingo card structure using linked list nodes based on the provided data
    }

    private boolean checkDuplicate(T[][] card, int randomNumber, int columnIndex) {
        for (int i = 0; i < 3; i++) {
            if (card[i][columnIndex] != null && (int) card[i][columnIndex] == randomNumber) {
                return true; // Aynı sütunda aynı sayı varsa true döndür
            }
        }
        return false; // Aynı sütunda aynı sayı yoksa false döndür
    }

    private EbrarElmasYildiz_Node<Integer> generateRandomIndexes() {
        EbrarElmasYildiz_Node<Integer> head = null;
        Random random = new Random();
        for (int i = 0; i <= 5; i++) {
            int randomIndex;
            do {
                randomIndex = random.nextInt(10); // 1'dan 9'a kadar rastgele bir sütun dizini seçin
            } while (containsIndex(head, randomIndex)); // Dizinin daha önce seçilip seçilmediğini kontrol edin
            head = insertLast(head, randomIndex);//This line of code inserts a new node with the randomIndex value at the end of the linked list represented by the head
        }
        return head;
    }

    private EbrarElmasYildiz_Node<Integer> insertLast(EbrarElmasYildiz_Node<Integer> head, int data) {
        EbrarElmasYildiz_Node<Integer> newNode = new EbrarElmasYildiz_Node<>(data);
        if (head == null) {// If the linked list is empty, return the new node as the head
            return newNode;
        }
        EbrarElmasYildiz_Node<Integer> current = head;// Traverse the linked list to find the last node
        while (current.next != null) {
            current = current.next;
        }
        current.next = newNode;// Attach the new node to the next of the last node

        return head;
    }

    private boolean containsIndex(EbrarElmasYildiz_Node<Integer> head, int target) {
        EbrarElmasYildiz_Node<Integer> current = head;
        while (current != null) {
            if (current.data == target) {// If the current node's data matches the target, return true

                return true;
            }
            current = current.next; // Move to the next node in the linked list
        }
        return false;// If the target data is not found in the linked list, return false
    }

    private T generateRandomNumber(int columnIndex, int rowIndex) {
        Random random = new Random();
        int start = columnIndex * 10 + 1; // Calculate the start of the range based on the column index
        int end = columnIndex * 10 + 10; // Calculate the end of the range based on the column index
        int randomNumber = random.nextInt(end - start) + start; // Generate a random number within the specified range
        return (T) Integer.valueOf(randomNumber);
    }

    public void insert(T data) {
        EbrarElmasYildiz_Node<T> newNode = new EbrarElmasYildiz_Node<>(data); // Create a new node with the specified data
        if (head == null) { // If the list is empty
            head = newNode; // Set the new node as the head of the list
        } else { // If the list is not empty
            EbrarElmasYildiz_Node<T> temp = head; // Initialize a temporary node pointer to traverse the list
            while (temp.down != null) { // Traverse down to the bottom of the list
                temp = temp.down;
            }
            if (temp.next == null) { // If the current node has no next node
                temp.next = newNode; // Set the new node as the next node of the current node
            } else { // If the current node has a next node
                int count = 0; // Initialize a counter for the number of nodes traversed
                while (temp.next != null && count < 9) { // Traverse horizontally up to 9 nodes
                    temp = temp.next; // Move to the next node
                    count++; // Increment the counter
                }
                if (count >= 9) { // If the count exceeds 9 nodes
                    temp = head; // Reset the pointer to the head of the list
                    while (temp.down != null) { // Traverse down to the bottom of the list again
                        temp = temp.down;
                    }
                    while (temp.next != null) { // Traverse horizontally until reaching the end of the row
                        temp = temp.next;
                    }
                }
                temp.next = newNode; // Set the new node as the next node of the current node
            }
        }
    }
    
    
     public boolean markFound(T data) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 9; j++) {
                if (card[i][j] != null && card[i][j].data.equals(data)) {

                    card[i][j].data = "|" + data + "|";
                    return true;
                }
            }
        }
        return false;
    }

    public boolean checkFirstBingo() {
        // Herhangi bir satırda 5 sayı işaretlenmiş mi?
        for (int i = 0; i < 3; i++) {
            int count = 0;
            for (int j = 0; j < 9; j++) {
                if (card[i][j] != null) {
                    T data = (T) card[i][j].data;
                    if (data != null && data.toString().startsWith("|") 
                            && data.toString().endsWith("|")) {
                        count++;
                    }
                }
            }
            if (count == 5) {
                return true; // 1. çinko durumu
            }
        }
        return false;
    }

    public boolean checkSecondBingo() {
        // Toplamda iki satırda 10 sayı işaretlenmiş mi?
        int totalCount = 0;
        for (int i = 0; i < 3; i++) {
            int count = 0;
            for (int j = 0; j < 9; j++) {
                if (card[i][j] != null) {
                    T data = (T) card[i][j].data;
                    if (data != null && data.toString().startsWith("|") 
                            && data.toString().endsWith("|")) {
                        count++;
                    }
                }
            }
            if (count >= 5) {
                totalCount++;
            }
        }
        if (totalCount >= 2) {
            return true; // 2. çinko durumu
        }
        return false;
    }

    public boolean checkTombala() {
        // Her üç satırın toplamda 15 sayı işaretlenmiş mi?
        int totalCount = 0;
        for (int i = 0; i < 3; i++) {
            int count = 0;
            for (int j = 0; j < 9; j++) {
                if (card[i][j] != null) {
                    T data = (T) card[i][j].data;
                    if (data != null && data.toString().startsWith("|") 
                            && data.toString().endsWith("|")) {
                        count++;
                    }
                }
            }
            totalCount += count; // Her satırdaki işaretli sayıları toplam sayıya ekleme
        }
        if (totalCount == 15) {
            return true; // Tombala durumu
        }
        return false;
    }

}