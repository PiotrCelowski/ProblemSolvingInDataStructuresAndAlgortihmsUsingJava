import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Chapter1Exercises {
    public static int findAverageOfElementsInArray(int[] arr){
        int sum = 0;
        for(int index = 0; index < arr.length; index++) {
            sum += arr[index];
        }
        return sum/arr.length;
    }

    public static int findSumOfAllElementsInTwoDimensionalArray(int[][] arr) {
        int sum = 0;
        for(int rowIndex = 0; rowIndex < arr.length; rowIndex++) {
            for(int columnIndex = 0; columnIndex < arr[rowIndex].length; columnIndex++) {
                sum += arr[rowIndex][columnIndex];
            }
        }
        return sum;
    }

    public static int findLargestElementInArray(int[] arr) {
        int largestElement = 0;
        for(int index = 0; index < arr.length; index++) {
            if(index == 0) {
                largestElement = arr[index];
            } else {
                if (largestElement < arr[index]) {
                    largestElement = arr[index];
                }
            }
        }
        return largestElement;
    }

    public static int findSmallestElementInArray(int[] arr) {
        int smallestElement = 0;
        for(int index = 0; index < arr.length; index++) {
            if(index == 0) {
                smallestElement = arr[index];
            } else {
                if (smallestElement > arr[index]) {
                    smallestElement = arr[index];
                }
            }
        }
        return smallestElement;
    }

    public static int findSecondLargestElementInArray(int[] arr) {
        List<Integer> arrayList = IntStream.of(arr).boxed().collect(Collectors.toList());
        int largestElement = findLargestElementInArray(arr);
        arrayList.remove(arrayList.indexOf(largestElement));

        return findLargestElementInArray(arrayList.stream().mapToInt(Integer::intValue).toArray());
    }

    public static void printMaximasOfArray(int[] arr) {
        int maxElement = findSmallestElementInArray(arr);
        for(int i=arr.length-1; i>=0; i--) {
            if(arr[i] > maxElement) {
                maxElement = arr[i];
                if (i == 0 && arr[i+1] < maxElement) {
                    System.out.println("Maximum found: " + maxElement);
                } else if (i == 0) {
                    break;
                } else if(arr[i-1] < maxElement) {
                    System.out.println("Maximum found: " + maxElement);
                } else if (arr[i-1] < maxElement) {
                    System.out.println("Maximum found: " + maxElement);
                }
            }
            maxElement = findSmallestElementInArray(arr);
        }
    }

    public static void printAlternateELementsOfArray(int[] arr) {
        for(int i=0; i<arr.length-1; i++) {
            if (i%2 == 0) {
                System.out.println(arr[i]);
            }
        }
    }

    public static int[] segregate0and1(int[] arr) {
        int[] newArr =new int[arr.length];
        int j = 0;
        for(int i=0; i<arr.length; i++) {
            if(arr[i]==0) {
                newArr[j] = 0;
                j++;
            }
        }
        for(int i=0; i<arr.length; i++) {
            if(arr[i]==1) {
                newArr[j] = 1;
                j++;
            }
        }
        return newArr;
    }

    public static ArrayList<ArrayList<Integer>> mergeOverlappingIntervals(ArrayList<ArrayList<Integer>> arr) {
        ArrayList<ArrayList<Integer>> newArr = new ArrayList<ArrayList<Integer>>(0);
        for(int index=0; index < arr.size(); index++) {
            ArrayList<Integer> oneRange = new ArrayList<>();
            if(index == arr.size()-1) {
                oneRange.add(arr.get(index).get(0));
                oneRange.add(arr.get(index).get(1));
            } else if(arr.get(index).get(1) > arr.get(index+1).get(0)) {
                oneRange.add(arr.get(index).get(0));
                oneRange.add(arr.get(index+1).get(1));
                index++;
            } else {
                oneRange.add(arr.get(index).get(0));
                oneRange.add(arr.get(index).get(1));
            }
            newArr.add(oneRange);
        }
        if(checkForOverlappingRanges(newArr)) {
            newArr = mergeOverlappingIntervals(newArr);
        }

        return newArr;
    }

    public static Boolean checkForOverlappingRanges(ArrayList<ArrayList<Integer>> arr) {
        for(int index=0; index < arr.size(); index++) {
            if(index == arr.size()-1) {
                return false;
            } else if(arr.get(index).get(1) > arr.get(index+1).get(0)) {
                return true;
            }
        }
        return false;
    }
}
