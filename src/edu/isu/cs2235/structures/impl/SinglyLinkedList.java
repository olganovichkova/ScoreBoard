package edu.isu.cs2235.structures.impl;

import edu.isu.cs2235.structures.List;

public class SinglyLinkedList<E> implements List {

    private static class Node<E>{
        private E nodeElement;
        private Node<E> nextNode;
        public Node(E element, Node<E> next){
            nodeElement = element;
            nextNode = next;
        }
        public E getNodeElement() {
            return nodeElement;
        }
        public Node<E> getNextNode(){
            return nextNode;
        }
        public void setNextNode(Node<E> next){
            nextNode = next;
        }
    }

    private Node<E> head = null;
    private Node<E> tail = null;
    private int size = 0;
    public SinglyLinkedList(){}



    @Override
    public Object first() {
        if(isEmpty())
            return null;
        else
            return head.getNodeElement();
    }

    @Override
    public Object last() {
        if(isEmpty())
            return null;
        else
            return tail.getNodeElement();
    }

    @Override
    public void addLast(Object element) {
        if(element != null) {
            Node<E> newNode = new Node<E>((E) element, null);
            if (isEmpty())
                head = newNode;
            else
                tail.setNextNode(newNode);
            tail = newNode;
            size++;
        }
    }

    @Override
    public void addFirst(Object element) {
        if(element != null) {
            Node<E> newNode = new Node<E>((E) element, head);
            head = newNode;
            if (isEmpty())
                tail = head;
            size++;
        }
    }

    @Override
    public Object removeFirst() {
        if(isEmpty())
            return null;
        Object first = head.getNodeElement();
        head = head.getNextNode();
        size--;
        if(isEmpty())
            tail = null;
        return first;
    }

    @Override
    public Object removeLast() {
        if(isEmpty())
            return null;
        Object last = tail.getNodeElement();
        if(size == 1) {
            head = null;
            tail = null;
            size = 0;
            return last;
        }
        Node<E> runner = head;
        while(runner.nextNode.getNextNode() != null)
            runner = runner.nextNode;
        runner.setNextNode(null);
        tail = runner;
        size--;
        return last;
    }

    @Override
    public void insert(Object element, int index) {
        if(index >= 0 && element != null) {
            if (index <= size) {
                Node<E> newNode = new Node<E>((E) element, null);
                if (isEmpty()) {
                    head = newNode;
                    tail = newNode;
                    size = 1;
                }
                if (size == 1 && index == 1) {
                    head.setNextNode(newNode);
                    tail = newNode;
                } else if (size == 1 && index == 0) {
                    head = newNode;
                    newNode.setNextNode(tail);
                } else if (index == 0) {
                    newNode.setNextNode(head);
                    head = newNode;
                } else if (index == 1) {
                    newNode.setNextNode(tail);
                    head.setNextNode(newNode);
                } else if (index < size) {
                    Node<E> runner = head;
                    for (int i = 0; i < index - 1; i++) {
                        runner = runner.nextNode;
                    }
                    newNode.setNextNode(runner.nextNode);
                    runner.setNextNode(newNode);
                } else if (size == index) {
                    tail.setNextNode(newNode);
                    tail = newNode;
                }
                size++;
            }
            else{
                Node<E> newNode = new Node<E>((E) element, null);
                tail.setNextNode(newNode);
                tail = newNode;
                size++;
            }
        }
    }

    @Override
    public Object remove(int index) {
        if(index < 0)
            return null;
        if(index < size) {
            if (isEmpty())
                return null;
            if(size == 1) {
                E removedElement = head.getNodeElement();
                head = null;
                tail = null;
                size = 0;
                return removedElement;
            }
            else if(index == 0){
                E removedElement = head.getNodeElement();
                head = head.nextNode;
                size--;
                return removedElement;
            }
            else if(index == (size - 1)){
                E removedElement = tail.getNodeElement();
                Node<E> runner = head;
                while(runner.nextNode.getNextNode() != null){
                    runner = runner.nextNode;
                }
                tail = runner;
                tail.setNextNode(null);
                size--;
                return removedElement;
            }
            else {
                Node<E> runner = head;
                for(int i = 0; i < (index - 1); i++){
                    runner = runner.nextNode;
                }
                E removedElement = runner.nextNode.getNodeElement();
                Node<E> tempNode = runner.nextNode;
                runner.setNextNode(runner.nextNode.getNextNode());
                tempNode.setNextNode(null);
                size--;
                return removedElement;
            }
        }
        return null;
    }

    @Override
    public Object get(int index) {
        if(index < 0)
            return null;
        if(index < size) {
            if (isEmpty())
                return null;
            if(index == 0){
                E element = head.getNodeElement();
                return element;
            }
            else if(index == (size - 1)){
                E element = tail.getNodeElement();
                return element;
            }
            else {
               Node<E> runner = head;
               for(int i = 0; i < index; i++){
                   runner = runner.nextNode;
               }
               E element = runner.getNodeElement();
               return element;
            }
        }
        return null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        if (size == 0)
            return true;
        else
            return false;
    }

    @Override
    public void printList() {
        if (!isEmpty()) {
            Node<E> runner = head;
            if(size == 1){
                System.out.println(runner.getNodeElement().toString());
            }
            else {
                while (runner.getNextNode() != null) {
                    System.out.println(runner.getNodeElement().toString());
                    runner = runner.nextNode;
                }
                System.out.println(runner.getNodeElement().toString());
            }
        }
    }
}
