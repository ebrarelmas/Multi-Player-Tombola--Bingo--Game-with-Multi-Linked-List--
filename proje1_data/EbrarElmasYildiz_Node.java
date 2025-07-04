/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package proje1_data;

/**
 *
 * @author Ebrar Yıldız
 * ebrarelmas.yildiz@stu.fsm.edu.tr-2121221011
 */
// This class represents the nodes in a multi-linked list.
//Each node holds a data item and has forward and downward pointers.

class EbrarElmasYildiz_Node<T> {
    T data;
    EbrarElmasYildiz_Node<T> next;
    EbrarElmasYildiz_Node<T> down;

    public EbrarElmasYildiz_Node(T data) {
        this.data = data;
        this.next = null;// The 'next' pointer points to the next node on the same level.
        this.down = null;// The 'down' pointer points to the node in the same column of the next row.

    }
}
