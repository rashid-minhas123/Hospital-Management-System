public class GenericSwap {

    public static <T> void swap(T[] arr, int i, int j) {
        T temp1 = arr[i];
        arr[i] = arr[j];
        arr[j] = temp1;
        System.out.println("After swap: " + arr[i] + " and " + arr[j]);
    }

    public static void main(String[] args) {
        Integer[] nums = {10, 20, 30};
        swap(nums, 0, 2);   

        String[] words = {"Rashid", "Awan"};
        swap(words, 0, 1);  
    }
}