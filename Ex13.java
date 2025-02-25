/*
 * Ex13.java
 * This class has all the answers that are presented in the current Maman.
*/

public class Ex13
{
    public static int[] specialArr (int[] arr, int med){ // Returns a new array in which: arr[i] > arr[i + 1] < arr[i + 2] and so on... time complexity: o(n) (runs on the array one time), space complexity: o(n) (function creates new array)
        int[] newArr = new int[arr.length];
        int counterOdd = 1;
        int counterEven = 0;

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] >= med) {
                newArr[counterEven] = arr[i];
                counterEven += 2; // Move to the next even index
            }
            else{
                newArr[counterOdd] = arr[i];
                counterOdd += 2; // Move to the next odd index
            }
        }

        return newArr;
    }
    
    public static int first (int [] arr) { // Returns the minimal number which is not in the array. time complexity: o(n) (runs twice [seperate times] on the array, space complexity: o(1) (creates only intergers)
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] <= arr.length && arr[i] >= 1 && arr[i] - 1 != i) {
                swap(arr, arr[i] - 1, i);
            }
        }

        for (int i = 1; i <= arr.length; i++) {
            if (arr[i - 1] != i) {
                return i;
            }
        }
        return arr.length;
    }
    
    public static int longestNearlyPal(int[] arr) { // Overload
        return longestNearlyPal(arr, 0, 1, 0);
    }

    public static int longestNearlyPal(int[] arr, int left, int right, int maxLen) { // returns the length of the most long "nearly-palindrom"
        // If left reaches array length, we've checked all possibilities
        if (left == arr.length - 1) {
            return maxLen;
        }
        
        // If right reaches array length, move left pointer and reset right
        if (right == arr.length) {
            return longestNearlyPal(arr, left + 1, left + 2, maxLen);
        }
        
        // Try removing each internal element in the current sequence
        for (int remove = left + 1; remove < right; remove++) {
            if (isPalindrome(arr, left, right, remove)) {
                maxLen = Math.max(maxLen, right - left + 1);
            }
        }
        
        // Move right pointer
        return longestNearlyPal(arr, left, right + 1, maxLen);
    }


    public static int extreme(int[][] m) { // Overloaded method to start the recursive process
        return extreme(m, 0, 0, Integer.MIN_VALUE);
    }
    
    public static int extreme(int[][] m, int currentRow, int currentCol, int currentMax) { // Returns the value of the minimal number between the maximal numbers in every course
        // Update the maximum value encountered so far in this road
        currentMax = Math.max(currentMax, m[currentRow][currentCol]);
    
        // if we reach the last cell, return the maximum for this road
        if (currentRow == m.length - 1 && currentCol == m[0].length - 1) {
            return currentMax;
        }
    
        // explore all possible moves (down and right)
        int minValue = Integer.MAX_VALUE;
    
        // Move down (if valid)
        if (currentRow < m.length - 1) {
            minValue = Math.min(minValue, extreme(m, currentRow + 1, currentCol, currentMax));
        }
    
        // Move right (if valid)
        if (currentCol < m[0].length - 1) {
            minValue = Math.min(minValue, extreme(m, currentRow, currentCol + 1, currentMax));
        }
    
        return minValue;
    }
    
    //Auxiliary functions
    public static void swap(int[] arr, int first, int second) { // swaps two items in array
        int temp = arr[second];
        arr[second] = arr[first];
        arr[first] = temp;

    }
    
    public static boolean isPalindrome(int[] arr, int first, int last, int ignore) { // check if a specific part of an array is a palindrom (excluding the part which is ignored)
        if (first == ignore) {
            return isPalindrome(arr, first + 1, last, ignore);
        }
        
        if (last == ignore) {
            return isPalindrome(arr, first, last - 1, ignore);
        }
        
        if (first >= last) {
            return true;
        }
        
        if (arr[first] != arr[last]) {
            return false;
        }
        
        return isPalindrome(arr, first + 1, last - 1, ignore);
    }
}
