import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList<ArrayList<Integer>> arr = new ArrayList();
        arr.add(new ArrayList());
        arr.get(0).add(1);
        arr.get(0).add(4);
        arr.add(new ArrayList());
        arr.get(1).add(3);
        arr.get(1).add(6);
        arr.add(new ArrayList());
        arr.get(2).add(5);
        arr.get(2).add(10);
        arr.add(new ArrayList<>());
        arr.get(3).add(8);
        arr.get(3).add(20);

        for(int index=0; index < arr.size(); index++) {
            System.out.println("Range " + index + ": ");
            System.out.println( arr.get(index).get(0) + ", " + arr.get(index).get(1));
        }

        ArrayList<ArrayList<Integer>> newArr = Chapter1Exercises.mergeOverlappingIntervals(arr);

        System.out.println("New array list");
        for(int index=0; index < newArr.size(); index++) {
            System.out.println("Range " + index + ": ");
            System.out.println( newArr.get(index).get(0) + ", " + newArr.get(index).get(1));
        }

    }
}
