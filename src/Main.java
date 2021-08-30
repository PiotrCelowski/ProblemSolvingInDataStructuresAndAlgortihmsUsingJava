import java.util.ArrayList;
import java.util.zip.CheckedInputStream;

public class Main {
    public static void main(String[] args) {
        int[] array = new int[]{0,1,0,1,0,2,2};
        ArrayList<Integer> newArray = Chapter1Exercises.findDuplicateElements(array);

        for(int i=0; i<newArray.size(); i++) {
            System.out.println(newArray.get(i));
        }
    }
}
