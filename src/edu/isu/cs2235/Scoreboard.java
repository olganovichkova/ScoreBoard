package edu.isu.cs2235;

import edu.isu.cs2235.structures.List;
import edu.isu.cs2235.structures.impl.DoublyLinkedList;

/**
 * A class used to represent a scoreboard for a game which is constrained to
 * only contain a specific maximum number of entries.
 *
 * @author Isaac Griffith
 */
public class Scoreboard {

    private final int capacity; // maximum capacity constraint
    private List<GameEntry> board; // Underlying list data structure

    /**
     * Constructs a new scoreboard with the provided maximum capacity.
     *
     * @param capacity Maximum number of entries supported by this scoreboard.
     */
    public Scoreboard(int capacity) {
        board = new DoublyLinkedList<>();
        this.capacity = capacity;
    }

    /**
     * Insertes the provided GameEntry into the list, if that GameEntry's score
     * is at least >= the Scoreboards last item's score. If this is true it will
     * then look for the correct index in which to insert the entry and then
     * insert it.
     *
     * @param entry Entry to be added.
     */
    public void add(GameEntry entry) {
        if(board.isEmpty()){
            //insert first element if list is empty
            board.addFirst(entry);
        }
        else if(board.size() < capacity){
            //insert all elements if size of board is less than the capacity
            int i = 0;
            //find the position to insert the element
            while(i < board.size() && board.get(i).getScore() > entry.getScore()){
                i++;
            }
            if(i == board.size()){
                //adds element to last position if entry score is less than all scores in the list
                board.addLast(entry);
            }
            else {
                board.insert(entry, i);
            }
        }
        else if ( entry.getScore() > board.get(board.size()-1).getScore() && board.size() == capacity){
            //if entry score is greater than the last score and the board size is at its capacity
            //remove last entry and find position to insert new entry
            board.removeLast();
            int i = 0;
            while(i < board.size() && board.get(i).getScore() > entry.getScore()){
                i++;
            }
            if(i == board.size()){
                board.addLast(entry);
            }
            else {
                board.insert(entry, i);
            }
        }
        else if(board.size() == capacity && entry.getScore() == board.get(board.size()-1).getScore()){
            //if board size is at its capacity and new entry is the same as last score in list
            //remove the last entry and insert new entry to last position
            board.removeLast();
            board.addLast(entry);
        }
       // throw new UnsupportedOperationException();
    }

    /**
     * Removes the element at the provided index from the scoreboard.
     *
     * @param i the index of the element to be removed.
     * @return GameEntry at the specified index
     * @throws IndexOutOfBoundsException If the index is greater than or equal
     * to the list size or less than zero.
     */
    public GameEntry remove(int i) throws IndexOutOfBoundsException {
        if(i < 0 || i >= board.size()){
            throw new IndexOutOfBoundsException("");
        }
        return board.remove(i);
    }

    /**
     * Prints the contents of the scoreboard to the console.
     */
    public void printScores() {
        board.printList();
    }

    /**
     * @return current number of entries held by the scoreboard.
     */
    public int size() {
        return board.size();
    }
    
    /**
     * @param index index of GameEntry to retrieve.
     * @return GameEntry at provided index or null if index < 0 or index > capacity
     */
    public GameEntry get(int index) {
        return board.get(index);
    }
}
