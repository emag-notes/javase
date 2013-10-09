package org.emamotor.javase.utility.list;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author emag
 */
public class ListImplPerformance {

    private static final int LIST_SIZE = 1_000_000;
    private static final int MIDDLE_SIZE_NUM = 500_000;

    private static final List<String> arrayList  = new ArrayList<>();
    private static final List<String> linkedList = new LinkedList<>();

    static {
        for ( int i = 0; i < LIST_SIZE; i++ ) {
            arrayList.add( "ArrayList Element_"  + i);
            linkedList.add("LinkedList Element_" + i);
        }
    }

    public static void main(String... args) {

        // Randome Access
        new AbstractPerformance() {
            @Override
            protected void targetOperation() {
                System.out.print("[ArrayList] Random Access:");
                arrayList.get(MIDDLE_SIZE_NUM);
            }
        }.exec();

        new AbstractPerformance() {
            @Override
            protected void targetOperation() {
                System.out.print("[LinkedList]Random Access:");
                linkedList.get(MIDDLE_SIZE_NUM);
            }
        }.exec();

        // Search
        new AbstractPerformance() {
            @Override
            protected void targetOperation() {
                System.out.print("[ArrayList] Search:");
                arrayList.indexOf("ArrayList Element_" + MIDDLE_SIZE_NUM);
            }
        }.exec();

        new AbstractPerformance() {
            @Override
            protected void targetOperation() {
                System.out.print("[LinkedList]Search:");
                linkedList.indexOf("LinkedList Element_" + MIDDLE_SIZE_NUM);            }
        }.exec();

        // Add
        new AbstractPerformance() {
            @Override
            protected void targetOperation() {
                System.out.print("[ArrayList] Add:");
                arrayList.add("Add Element");
            }
        }.exec();

        new AbstractPerformance() {
            @Override
            protected void targetOperation() {
                System.out.print("[LinkedList]Add:");
                linkedList.add("Add Element");
            }
        }.exec();

        // Insert
        new AbstractPerformance() {
            @Override
            protected void targetOperation() {
                System.out.print("[ArrayList] Insert:");
                arrayList.add(MIDDLE_SIZE_NUM, "Insert Element");
            }
        }.exec();

        new AbstractPerformance() {
            @Override
            protected void targetOperation() {
                System.out.print("[LinkedList]Insert:");
                linkedList.add(MIDDLE_SIZE_NUM, "Insert Element");
            }
        }.exec();

        // Remove
        new AbstractPerformance() {
            @Override
            protected void targetOperation() {
                System.out.print("[ArrayList] Remove:");
                arrayList.remove(MIDDLE_SIZE_NUM);
            }
        }.exec();

        new AbstractPerformance() {
            @Override
            protected void targetOperation() {
                System.out.print("[LinkedList]Remove:");
                linkedList.remove(MIDDLE_SIZE_NUM);
            }
        }.exec();

    }


}
