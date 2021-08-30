import java.util.ArrayList;
import java.util.zip.CheckedInputStream;

public class Main {
    public static void main(String[] args) {
        int[] array = new int[]{0,1,0,1,0};
        int[] newArray = Chapter1Exercises.segregate2and0and1optimized(array);

        for(int i=0; i<newArray.length; i++) {
            System.out.println(newArray[i]);
        }
    }
}
