import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;
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

    public static int[] reverseArray(int[] array) {
        for(int i=0, j=array.length-1; i<j; i++, j--) {
            int placeHolder = array[i];
            array[i] = array[j];
            array[j]= placeHolder;
        }
        return array;
    }

    public static int[] segregate0and1optimized(int[] array) {
        int arrayEndingIndex = checkEndOfZeroAndOnes(array);
        for(int start=0, end=arrayEndingIndex; start<end; start++, end--) {
            if(array[start] == 1 && array[end] == 0) {
                int placeHolder = array[start];
                array[start]=array[end];
                array[end]=placeHolder;
            } else if(array[start] == 0 && array[end] == 0) {
                array = addEndAtStartAndMoveElements(array, start, end);
            } else if(array[start] == 1 && array[end] == 1) {
                array = addStartAtEndAndMoveElements(array, start, end);
            }
        }
        if(!checkIfSorted(array)){
            segregate0and1optimized(array);
        }
        return array;
    }

    public static int[] addEndAtStartAndMoveElements(int[] array, int start, int end) {
        int lastElementPlaceholder = array[end];
        for(int i=end; i>=start; i--) {
            if(i==start) {
                array[i] = lastElementPlaceholder;
                return array;
            }

            array[i]=array[i-1];
        }
        return array;
    }

    public static int[] addStartAtEndAndMoveElements(int[] array, int start, int end) {
        int firstElementPlaceholder = array[0];
        for(int i=start; i<end; i++) {
            array[i]=array[i+1];
            if(i == end) {
                array[i] = firstElementPlaceholder;
                return array;
            }
        }
        return array;
    }

    public static Boolean checkIfSorted(int[] array) {
        for(int i=array.length-1; i>0; i--) {
            if(array[i] < array[i-1]){
                return false;
            }
        }
        return true;
    }

    public static int checkEndOfZeroAndOnes(int[] array) {
        for(int i=0; i<array.length; i++) {
            if(array[i] > 1) {
                return i-1;
            }
        }
        return array.length-1;
    }

    public static int[] segregate2and0and1optimized(int[] array) {
        for(int start=0, end=array.length-1; start<=end; start++, end--) {
            if(array[start] == 2 && array[end] != 2) {
                int placeHolder = array[start];
                array[start]=array[end];
                array[end]=placeHolder;
            } else if(array[start] == 2 && array[end] == 2) {
                array = addStartAtEndAndMoveElements(array, start, end);
            }
        }
        segregate0and1optimized(array);

        if(!checkIfSorted(array)){
            segregate0and1optimized(array);
        }

        return array;
    }

    public static ArrayList<Integer> findDuplicateElements(int[] array) {
        ArrayList<Integer> arrayList = new ArrayList();
        for(int element=0; element<array.length; element++) {
            for(int secElement=0; secElement<array.length; secElement++) {
                if(array[element]==array[secElement] && element!=secElement && !arrayList.contains(array[secElement])) {
                    arrayList.add(array[secElement]);
                }
            }
        }
        return arrayList;
    }

    public static void findDuplicateElementsHashTable(int[] array) {
        Hashtable<Integer, Integer> hashTable = new Hashtable<>();
        for (int element = 0; element < array.length; element++) {
            if (!hashTable.containsKey(array[element])) {
                hashTable.put(array[element], 1);
            } else {
                System.out.println(array[element]);
            }
        }
    }

    public static void findDuplicateElementsOptimized(int[] array) {
        int largerstElement = findLargestElementInArray(array);
        int[] duplicatesArray = new int[largerstElement+1];
        Arrays.fill(duplicatesArray, 0);

        for (int element = 0; element < array.length; element++) {
            duplicatesArray[array[element]] += 1;
        }

        for (int element = 0; element < duplicatesArray.length; element++) {
            if (duplicatesArray[element] > 1) {
                System.out.println(element);
            }
        }
    }

    public static void findMaxElementInSortedArray(int[] array, int minimumIndex, int maximumIndex, int initialMamElement) {
        int maxElement = initialMamElement;
        int pivot = findPivot(array, minimumIndex, maximumIndex);

        if(maxElement < array[pivot]) {
            maxElement = array[pivot];
            findMaxElementInSortedArray(array, pivot, maximumIndex, maxElement);
        } else {
            System.out.println(maxElement);
        }
    }

    public static int findPivot(int[] array, int minimumIndex, int maximumIndex) {
        if(maximumIndex < minimumIndex) {
            return -1;
        } else if (minimumIndex == maximumIndex) {
            return minimumIndex;
        }

        int middleIndex = (minimumIndex + maximumIndex) / 2;
        if(array[minimumIndex] > array[maximumIndex] && array[middleIndex] > array[middleIndex+1]) {
            findPivot(array, minimumIndex, middleIndex);
        } else if(array[minimumIndex] > array[maximumIndex] && array[middleIndex] < array[middleIndex+1]) {
            findPivot(array, middleIndex, maximumIndex);
        } else {
            return middleIndex;
        }

        return middleIndex;
    }

    public static void findTwoElementsThatSumsUp(int[] array, int value) {
        bubbleSort(array);

        int foundIndex = 0;
        for(int index=0; index<array.length; index++) {
            if(array[index] >= value) {
                foundIndex = index;
                break;
            }
            foundIndex = array.length-1;
        }

        for(int index=0; index<=foundIndex; index++) {
            for(int nextIndex=0; nextIndex<=foundIndex; nextIndex++) {
                if(index != nextIndex) {
                    int sum = array[index] + array[nextIndex];
                    if(sum == value) {
                        System.out.println("Found elements: " + array[index] + ", " + array[nextIndex]);
                        break;
                    }
                }
            }
        }

    }

    public static void bubbleSort(int[] array) {
        for(int index=0; index<array.length-1; index++) {
            if(array[index] > array[index+1]) {
                int temp = array[index];
                array[index] = array[index+1];
                array[index+1] = temp;
            }
        }
        if(!checkIfSorted(array)) {
            bubbleSort(array);
        }
    }

    public static void findTwoElementsThatSumsUpHashTable(int[] array, int value) {
        Hashtable<Integer, ArrayList<Integer>> hashtable = new Hashtable<>(array.length);
        for(int index=0; index<array.length; index++) {
            for(int nextIndex=0; nextIndex<array.length; nextIndex++) {
                int sum = array[index] + array[nextIndex];
                ArrayList indexes = new ArrayList<Integer>(2);
                indexes.add(index);
                indexes.add(nextIndex);
                hashtable.put(sum, indexes);
            }
        }

        if(hashtable.containsKey(value)) {
            System.out.println("Found: " + array[hashtable.get(value).get(0)] + ", " + array[hashtable.get(value).get(1)]);
        }
    }

    public static void sumElements(int[] array) {
        int[] sumArray = new int[array.length];
        for(int index=0; index < array.length; index++) {
            int number = 1;
            int fullNumber = array[index];
            int sum = 0;
            while(number > 0) {
                number = fullNumber % 10;
                sum += number;
                fullNumber = fullNumber / 10;
            }
            sumArray[index] = sum;
        }
        for(int index=0; index < array.length; index++) {
            System.out.println(sumArray[index]);
        }
    }

    public static void computeSumOf(int number) {
        int sum=0;
        while(number>0) {
            sum+=number;
            number--;
        }
        System.out.println(sum);
    }


}
