package com.med_consultant.backend;

import java.util.ArrayList;

public class LinkedList {
    private ListNode head;

    public LinkedList() {
        head = null;
    }

    private boolean isEmpty(ListNode head) {
        return head == null;
    }


    public void addFirst(Hospitals data) {
        ListNode temp = new ListNode();
        if (isEmpty(head)) {
            temp.data = data;
            head = temp;
            head.next = head;
        }
        else {
            ListNode current = head;
            while (current.next != head) {
                current = current.next;
            }
            temp.next = head;
            current.next = temp;
            temp.data = data;
            head = temp;
        }
    }

    void delFirst() {
        ListNode trash;
        if (isEmpty(head)) {
            System.out.println( "Ошибка: удалить из начала не удалось так, как список пуст! " );
        }
        else if (head.next == head) {
            head.next = null;
            //trash = head;
            head = null;
        }else {
            ListNode current = head;
            while (current.next != head) {
                current = current.next;
            }
            trash = current.next;
            head = trash.next;
            current.next = head;
        }
    }

    public void remove(int i){
        if (isEmpty(head)) {

        }
        else {
            ListNode tempHead = head;
            //int tempI = 0;
            for(int tempI = 0; tempI < i; tempI++){
                tempHead = tempHead.next;
            }
            tempHead.next = tempHead.next.next;
        }
    }

    public int size()
    {
        int size = 0;
        if (isEmpty(head)) {
            return size;
        }
        else {
            ListNode tempHead = head;
            do {
                size++;
                tempHead = tempHead.next;
            } while (tempHead != head);
            return size;
        }
    }

    public Hospitals get(int i){
        if (isEmpty(head)) {
            return null;
        }
        else {
            ListNode tempHead = head;
            //int tempI = 0;
            for(int tempI = 0; tempI < i; tempI++){
                tempHead = tempHead.next;
            }
            return tempHead.data;
        }
    }

    /*void kInsert(int k, Hospitals data) {
        if (isEmpty(this.head)) {
            System.out.println("Ошибка: вставить перед каждым k-ым не удалось так, как список пуст!");
        }
	else if(k != 0) {
            ListNode tempHead = head;
            int i = 1;
            ListNode current = head;
            while (current.next != head) {
                current = current.next;
            }
            while (tempHead != current) {
                if (k == 1) {
                    if (i == 1) {
                        ListNode temp = new ListNode();
                        temp.data = data;
                        temp.next = current.next;
                        this.head = temp;
                        current.next = this.head;
                        tempHead = this.head;
                    }
                    else {
                        ListNode temp = new ListNode();
                        temp.next = tempHead.next;
                        temp.data = data;
                        tempHead.next = temp;
                        tempHead = tempHead.next;
                    }
                }
                else if ((i + 1) % k == 0) {
                    ListNode temp = new ListNode();
                    temp.next = tempHead.next;
                    tempHead.next = temp;
                    temp.data = data;
                    tempHead = tempHead.next;
                }
                tempHead = tempHead.next;
                i++;
            }
        }
        System.out.println( "Вставка перед каждым k-ым отработала: " );
    }*/


    /*void kDel(int k)
    {
        if (isEmpty(this.head)) {
        System.out.println( "Ошибка: удалить перед каждым k-ым не удалось так, как список пуст!" );
    }
	else if(k != 0) {
        ListNode tempHead = this.head;
        ListNode tempg = this.head;
        ListNode current = head;
        while (current.next != head) {
            current = current.next;
        }

        int i = 1;
        if (k == 1) {
            while (tempHead != current) {
                if (i == 1) {
                    ListNode temp = this.head;
                    while (temp.next != current) {
                        temp = temp.next;
                    }
                    ListNode tempEnd = current;
                    temp.next = tempEnd.next;
                    tempEnd = temp;
                    temp = temp.next;
                    i++;
                }
                else {
                    ListNode temp = this.head;
                    tempHead = tempHead.next;
                    this.head = tempHead;
                    i++;
                }
            }
        }
        else {
            tempHead = current;
            tempg = tempHead.next.next;
            i++;
            while (tempHead.next != current) {
                if (i % k == 0) {
                    tempHead.next = tempg;
                    this.head = tempg;
                    tempg = tempg.next;
                    this.head = tempHead.next;
                    i++;
                }
                else {
                    tempHead = tempHead.next;
                    tempg = tempg.next;
                    this.head = tempHead.next;
                    i++;
                }
            }
            this.head = tempHead.next.next;
        }
    }
        System.out.println( "Удаление перед каждым k-ым отработало: " );
    }*/


    /*int maxValue() {
        ListNode tempHead = head;
        if (isEmpty(head)) {
            System.out.println( "Ошибка!(Пустой список) " );
            return -1;
        }
        else {
            Hospitals tempData = head.data;
            do {
                tempHead = tempHead.next;
                tempData = (tempData < tempHead.data) ? tempHead.data : tempData;
            } while (tempHead != head);
            return tempData;
        }
    }*/


    /*void print() {
        if (isEmpty(head)) {
            System.out.println( "Ошибка: не удалось вывести список так, как он пуст! " );
        }
        else {
            ListNode tempHead = head;
            System.out.println( "Список: ");
            do{
                System.out.println(tempHead.data);
                tempHead = tempHead.next;
            } while (tempHead != head);
            System.out.println();
        }
    }*/
}