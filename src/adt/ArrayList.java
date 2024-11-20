package adt;

import java.util.Comparator;

/**
 * @author Hafiz Chew Hoe Leong
 */
public class ArrayList<T extends Comparable<T>> implements ListInterface<T> {

    private T[] array;
    private int numberOfEntries;
    private static final int DEFAULT_SIZE = 10;

    public ArrayList() {
        this(DEFAULT_SIZE);
    }

    public ArrayList(int size) {
        numberOfEntries = 0;
        array = (T[]) new Comparable[size];
    }

    @Override
    public boolean add(T entry) {
        if (isFull()) {
            expandSize();
        }

        array[numberOfEntries] = entry;
        numberOfEntries++;
        return true;
    }

    @Override
    public boolean insert(int index, T entry) {
//      index indicate where entry located at

        boolean isSuccessful = true;

        //index start from 0 until (numberOfEntries==last index)
        if ((index > -1) && (index <= numberOfEntries)) {
            if (isFull()) {
                expandSize();
            }
            makeRoom(index);
            array[index] = entry;
            numberOfEntries++;
        } else {
            isSuccessful = false;
        }

        return isSuccessful;
    }

    /**
     * Task: Makes room for a new entry at selected index. Precondition:
     * numberOfEntries=1 <= index < numberOfEntries ; numberOfEntries is array's
     * numberOfEntries before addition. no need check for index range because it
     * only call by insert()
     */
    private void makeRoom(int index) {

        // move each entry to next higher index, starting at end of
        // array and continuing until the entry at newIndex is moved
        for (int moveIndex = numberOfEntries; moveIndex >= index; moveIndex--) {
            array[moveIndex + 1] = array[moveIndex];
        }
    }

    @Override
    public T get(int index) {
        T result = null;

        if ((index > -1) && (index < numberOfEntries)) {
            result = array[index];
        }

        return result;
    }

    @Override
    public boolean contains(T entry) {
        boolean found = false;
        for (int index = 0; !found && (index < numberOfEntries); index++) {
            if (entry.equals(array[index])) {
                found = true;
            }
        }
        return found;
    }

    @Override
    public boolean removeAt(int index) {
        boolean isSuccessful = true;

        if ((index > -1) && (index < numberOfEntries)) {
            removeGapAt(index);
            numberOfEntries--;
        } else {
            isSuccessful = false;
        }

        return isSuccessful;
    }

    /**
     * Task: Shifts entries that are beyond the entry to be removed to the next
     * lower position. Precondition: array is not empty; 1 <= givenPosition <
     * numberOfEntries; numberOfEntries is array's numberOfEntries before
     * removal.
     */
    private void removeGapAt(int index) {
        // move each entry to next lower position starting at entry after the
        // one removed and continuing until end of array
        int newLastIndex = numberOfEntries - 1;

        for (int moveIndex = index; index < newLastIndex; index++) {
            array[moveIndex] = array[moveIndex + 1];
        }

    }

    @Override
    public boolean removeBefore(int index) {
        boolean isSuccessful = true;

        if ((index > -1) && (index < numberOfEntries)) {
            removeGapBefore(index);
            numberOfEntries -= index;
        } else {
            isSuccessful = false;
        }

        return isSuccessful;
    }

    private void removeGapBefore(int index) {
        int newLastIndex = numberOfEntries - index;

        for (int moveIndex = 0; moveIndex < newLastIndex; moveIndex++) {
            array[moveIndex] = array[moveIndex + index];
        }
    }

    @Override
    public boolean removeAfter(int index) {
        boolean isSuccessful = true;

        if ((index > -1) && (index < numberOfEntries)) {
            numberOfEntries = index + 1;
        } else {
            isSuccessful = false;
        }

        return isSuccessful;
    }

    @Override
    public void clear() {
        numberOfEntries = 0;
    }

    @Override
    public int indexOf(T entry) {
        int result = -1;

        for (int index = 0; (result == -1) && (index < numberOfEntries); index++) {
            if (entry.equals(array[index])) {
                result = index;
            }
        }

        return result;  //-1 means not found
    }

    @Override
    public boolean replace(int index, T entry) {
        boolean isSuccessful = true;

        if ((index > -1) && (index < numberOfEntries)) {
            array[index] = entry;
        } else {
            isSuccessful = false;
        }

        return isSuccessful;
    }

    @Override
    public boolean swap(int index1, int index2) {
        boolean isSuccessful = true;

        if ((index1 > -1) && (index1 < numberOfEntries) && (index2 > -1) && (index2 < numberOfEntries)) {
            T temp = array[index1];
            array[index1] = array[index2];
            array[index2] = temp;
            
            
        } else {
            isSuccessful = false;
        }

        return isSuccessful;
    }

    @Override
    public int getNumberOfEntries() {
        return numberOfEntries;
    }

    @Override
    public boolean isEmpty() {
        return numberOfEntries == 0;
    }

    @Override
    public boolean isFull() {
        return numberOfEntries == array.length;
    }

    private void expandSize() {
        T[] oldArray = array;
        array = (T[]) new Comparable[oldArray.length * 2];
        for (int i = 0; i < numberOfEntries; i++) {
            array[i] = oldArray[i];
        }
    }

    @Override
    public String toString() {
        String outputStr = "";
        for (int index = 0; index < numberOfEntries; ++index) {
            outputStr += array[index] + "\n";
        }

        return outputStr;
    }

    @Override
    public void sort() {
        for (int sortIndex = 1; sortIndex < numberOfEntries; sortIndex++) {
            T unsortedEntry = array[sortIndex];
            int lastSortedEntry = sortIndex - 1;

            while (lastSortedEntry >= 0 && 
                    (unsortedEntry.compareTo(array[lastSortedEntry]) < 0)) {
                //if unsortedEntry should be sort infront of lastSortedEntry 
                //then move the lastSortedEntry to one behind
                array[lastSortedEntry + 1] = array[lastSortedEntry];
                //continue compare the rest of the sortedEntry by moving lastSortedEntry to one infront
                lastSortedEntry--;
            }

            //put the unsortedEntry to the correct sorted index 
            array[lastSortedEntry + 1] = unsortedEntry;
        }
    }

    @Override
    public void sort(Comparator<? super T> comparator) {
        for (int sortIndex = 1; sortIndex < numberOfEntries; sortIndex++) {
            T unsortedEntry = array[sortIndex];
            int lastSortedEntry = sortIndex - 1;

            //using specific comparator's compareTo method by calling comparator.compare
            while (lastSortedEntry >= 0 && 
                    (comparator.compare(array[lastSortedEntry], unsortedEntry) > 0)) {
                //if unsortedEntry should be sort infront of lastSortedEntry 
                //then move the lastSortedEntry to one behind
                array[lastSortedEntry + 1] = array[lastSortedEntry];
                //continue compare the rest of the sortedEntry by moving lastSortedEntry to one infront
                lastSortedEntry--;
            }

            //put the unsortedEntry to the correct sorted index 
            array[lastSortedEntry + 1] = unsortedEntry;
        }
    }

    @Override
    public void reverseOrder() {
        boolean isSuccessful = false;

        //do swap for half of numberOfEntries because symmetricly 
        //odd will not sort middle,even will sort middle2 with casting int
        for (int i = 0; i < numberOfEntries / 2 ; i++) {

            //swap(leftIndex, rightIndex) symmetricly
            isSuccessful = swap(i, numberOfEntries-i-1);
        }
    }
}
