package week3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ListIterator;

public class ChangePairLists<T> {


    public static void main(String[] args) {
        ChangePairLists<String> changePairLists = new ChangePairLists<>();
        List<String> aList = new ArrayList<>();
        aList.add("d");
        aList.add("e");
        aList.add("f");
        List<String> bList = new ArrayList<>();
        bList.add("w");
        bList.add("x");
        bList.add("y");
        bList.add("z");
        changePairLists.interleaved(aList, bList);
        changePairLists.printList(aList);
        changePairLists.deleteEverySecondEle(bList);
        changePairLists.printList(bList);
        changePairLists.removeEleFromAInB(aList, bList);
        changePairLists.printList(aList);

    }

    private void removeEleFromAInB(List<T> aList, List<T> bList) {
        aList.removeIf(bList::contains);
    }

    private void deleteEverySecondEle(List<T> bList) {
        ListIterator<T> tListIterator = bList.listIterator();
        int i = 0;
        while (tListIterator.hasNext()) {
            tListIterator.next();
            if (i % 2 == 1) {
                tListIterator.remove();
            }
            i++;
        }
    }

    public void interleaved(List<T> aList, List<T> bList) {
        /**
         * think about the loop, why do u use two LoopS!!!!!!!!!!
         *
         * assume!!!!!
         */
        if (aList.size() > bList.size()) {
            List<T> trans = aList;
            aList = bList;
            bList = trans;
        }
        ListIterator<T> aIterator = aList.listIterator();
        ListIterator<T> bIterator = bList.listIterator();
        while (bIterator.hasNext()) {
            if (aIterator.hasNext()) {
                T next = aIterator.next();
                bIterator.add(next);
            } else {
                break;
            }
            bIterator.next();
        }
        aList = bList;
    }

    public void printList(List<T> list) {
        list.forEach(t -> System.out.print(t + ", "));
        System.out.println();
    }
}
