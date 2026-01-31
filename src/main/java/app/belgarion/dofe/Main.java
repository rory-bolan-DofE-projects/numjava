package app.belgarion.dofe;



import java.util.Arrays;

public class Main {
    public static void main(String[] args) {


        NumJavaArray test = NumJavaArray.createArray(new float[]{1,2,3,4,5,6});
        System.out.println("---------");
        System.out.println("1 dimensional array");
        System.out.println("ndim: " + test.ndim);
        System.out.println("array: " + test);
        System.out.println("reversed: " + test.reversed());
        System.out.println("get(0): " + test.get(0));
        System.out.println("average: " + test.average());
        System.out.println("shape: " + Arrays.toString(test.shape));
        System.out.println("size: " + test.size);
        NumJavaArray test2 = NumJavaArray.createArray(new float[][]{new float[]{1,2}, new float[]{3,4}});
        System.out.println("---------");
        System.out.println("2 dimensional array");
        System.out.println("ndim: " + test2.ndim);
        System.out.println("array: " + test2);
        System.out.println("reversed: " + test2.reversed());
        System.out.println("get(0,0): " + test2.get(0,0));
        System.out.println("average: " + test2.average());
        System.out.println("shape: " + Arrays.toString(test2.shape));
        System.out.println("size: " + test2.size);
        float[][][] array3d = getArray3d();

        NumJavaArray test3 = NumJavaArray.createArray(array3d);

        System.out.println("---------");
        System.out.println("3 dimensional array");
        System.out.println("array: " + test3);
        System.out.println("ndim: " + test3.ndim);
        System.out.println("reversed: " + test3.reversed());
        System.out.println("get(0,0,0): " + test3.get(0,0,0));
        System.out.println("average: " + test3.average());
        System.out.println("shape: " + Arrays.toString(test3.shape));
        System.out.println("size: " + test3.size);
        System.out.println("---------");
        System.out.println("zeros:");
        NumJavaArray zeros = NumJavaArray.zeros(3,3,3);
        System.out.println(zeros);
        System.out.println("shape: "+ Arrays.toString(zeros.shape));
        System.out.println("size: "+zeros.size);
        System.out.println("average: "+zeros.average());
    }

    private static float[][][] getArray3d() {
        float[] part1 = new float[]{1,2,3};
        float[] part2 = new float[]{4,5,6};
        float[] part3 = new float[]{7,8,9};
        float[] part4 = new float[]{10,11,12};
        float[] part5 = new float[]{13,14,15};
        float[] part6 = new float[]{16,17,18};
        float[] part7 = new float[]{19,20,21};
        float[] part8 = new float[]{22,23,24};
        float[] part9 = new float[]{25,26,27};
        float[][] first = new float[][]{part1, part2, part3};
        float[][] second = new float[][]{part4, part5, part6};
        float[][] third = new float[][]{part7, part8, part9};
        return new float[][][]{first,second, third};
    }
}