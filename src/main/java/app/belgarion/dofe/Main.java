package app.belgarion.dofe;



import java.util.Arrays;

public class Main {
    public static void main(String[] args) {


        NumJavaArray test = NumJavaArray.createArray(new float[]{1,2,3,4,5,6});
        System.out.println("---------");
        System.out.println("1 dimensional array");
        printDetails(test);
        NumJavaArray test2 = NumJavaArray.createArray(new float[][]{new float[]{1,2}, new float[]{3,4}});
        System.out.println("---------");
        System.out.println("2 dimensional array");
        printDetails(test2);
        float[][][] array3d = getArray3d();

        NumJavaArray test3 = NumJavaArray.createArray(array3d);

        System.out.println("---------");
        System.out.println("3 dimensional array");
        printDetails(test3);
        System.out.println("zeros:");
        NumJavaArray zeros = NumJavaArray.zeros(3,3,3);
        printDetails(zeros);
        NumJavaArray ones = NumJavaArray.ones(3,4,5);
        printDetails(ones);
        NumJavaArray range1d = NumJavaArray.arange(9);
        printDetails(range1d);
        NumJavaArray range2d = NumJavaArray.arange(3,3);
        printDetails(range2d);
    }

    private static void printDetails(NumJavaArray range1d) {
        System.out.println(range1d);
        System.out.println("shape: "+ Arrays.toString(range1d.shape));
        System.out.println("size: "+range1d.size);
        System.out.println("ndim: " + range1d.ndim);
        System.out.println("average: "+range1d.average());
        System.out.println("---------");
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