import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList<Integer>[] arr = new ArrayList[3];
        arr[0] = new ArrayList<Integer>(2);
        arr[0].add(1);
        arr[0].add(4);
        arr[1] = new ArrayList<Integer>(2);
        arr[1].add(3);
        arr[1].add(6);
        arr[2] = new ArrayList<Integer>(2);
        arr[2].add(8);
        arr[2].add(10);

        for(int index=0; index < arr.length; index++) {
            System.out.println("Range " + index + ": ");
            System.out.println( arr[index].get(0) + ", " + arr[index].get(1));
        }

        ArrayList<Integer>[] newArr = Chapter1Exercises.mergeOverlappingIntervals(arr);
    }
}
