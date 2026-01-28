package app.belgarion.dofe;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        NumJavaArray arr = new NumJavaArray(new float[]{8,2,1,4});
        System.out.println(Arrays.toString(arr.getArray()));
        System.out.println(Arrays.toString(arr.reverse()));
        System.out.println(arr.average());

    }
}