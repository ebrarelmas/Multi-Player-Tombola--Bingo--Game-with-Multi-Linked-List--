In this project, you are expected to develop a program in Java / C programming language that imitates 
the multi-player game known as “Tombola”, using linked list data structure. Tombola is a popular game 
of chance played with numbered cards. The game involves randomly selecting numbers and players 
marking off those numbers on their cards if they match the ones called.

APPLICATION DETAILS 

The game is played with two players. Each player has a tombola card with 15 numbers, each of which 
is arranged in three rows. There are 5 numbers in each horizontal row and the remaining 4 boxes are 
randomly blocked out.  
A linked list should be used for each card. Each node in the linked list will correspond to a box on the 
card. You should not store the blocked boxes on the card. You must use a multi-linked list which is a 
special type of list that contains one or more logical key sequences. You must reach all nodes of the 
multi-linked list via the head pointer. You can design your own node class with multiple pointers. You 
can move in one direction (like single linked list) or two (like doubly linked list) in the multi linked list.  
In your main method, you should create a matrix that represents a card. The matrix (card) is randomly 
generated with respect to the card design, i.e. the first column has numbers from 1-9, the second from 
10-19 and so on up to 90. You will need to create as many matrices as there are players in the game. 
At the end of each step, the current version of the cards should be printed on the screen. 
