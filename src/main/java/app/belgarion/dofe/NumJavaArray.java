package app.belgarion.dofe;

import java.util.Arrays;
import java.util.stream.IntStream;

public class NumJavaArray {
    public int ndim;
    public int[] shape;
    public int size;
    // I am aware of how primitive this is but unless we want 3 separate classes this will have to do
    private float[] array1D = null; // MAY BE NULL
    private float[][] array2D = null; // MAY BE NULL
    private float[][][] array3D = null; // MAY BE NULL

    private NumJavaArray(float[] items){
        this.ndim = 1;
        this.array1D = items;
        this.shape = new int[]{items.length};
        this.size = items.length;
    }
    private NumJavaArray(float[][] items) {
        this.ndim = 2;
        this.array2D = items;
        this.shape = new int[]{items.length, items[0].length};
        this.size = 0;

        int len = -1;
        for (float[] arr : items) {
            size+=arr.length;
            if (len == -1) {
                len = arr.length;
                continue;
            }
            if (arr.length != len) throw new IllegalArgumentException("all rows of a matrix must be of equal length");
        }
    }
    private NumJavaArray(float[][][] items) {
        this.ndim = 3;
        this.array3D = items;
        this.shape = new int[]{items.length, items[0].length, items[0][0].length};
        this.size = 0;

        int len = -1;
        for (float[][] arr2d : items) {
            for (float[] arr : arr2d) {
                if (len == -1) {
                    len = arr.length;
                }
                if (arr.length != len)
                    throw new IllegalArgumentException("all rows of a matrix must be of equal length");

                size += arr.length;

            }
        }
    }
    public static NumJavaArray createArray(float[] items) {
        return new NumJavaArray(items);
    }
    public static NumJavaArray createArray(float[][] items) {
        return new NumJavaArray(items);
    }
    public static NumJavaArray createArray(float[][][] items) {
        return new NumJavaArray(items);
    }
    public static NumJavaArray zeros(int... params) {
        if (params.length == 1) {
            float[] items = new float[params[0]];
            for (int i = 0; i < params[0]; i++) {
                items[i] = 0;
            }
            return new NumJavaArray(items);
        } else if (params.length == 2) {
            float[][] items = new float[params[0]][params[1]];
            for (int i = 0; i < params[0]; i++) {
                for (int j = 0; j < params[1]; j++) {
                    items[i][j] = 0;
                }
            }
            return new NumJavaArray(items);
        } else {
            float[][][] items = new float[params[0]][params[1]][params[2]];
            for (int i = 0; i < params[0]; i++) {
                for (int j = 0; j < params[1]; j++) {
                    for (int k = 0; k < params[2]; k++) {
                        items[i][j][k] = 0;
                    }
                }
            }
            return new NumJavaArray(items);
        }
    }
    public static NumJavaArray ones(int... params) {
        if (params.length == 1) {
            float[] items = new float[params[0]];
            for (int i = 0; i < params[0]; i++) {
                items[i] = 1;
            }
            return new NumJavaArray(items);
        } else if (params.length == 2) {
            float[][] items = new float[params[0]][params[1]];
            for (int i = 0; i < params[0]; i++) {
                for (int j = 0; j < params[1]; j++) {
                    items[i][j] = 1;
                }
            }
            return new NumJavaArray(items);
        } else {
            float[][][] items = new float[params[0]][params[1]][params[2]];
            for (int i = 0; i < params[0]; i++) {
                for (int j = 0; j < params[1]; j++) {
                    for (int k = 0; k < params[2]; k++) {
                        items[i][j][k] = 1;
                    }
                }
            }
            return new NumJavaArray(items);
        }
    }
    public static NumJavaArray arange(int... params) {
        if (params.length == 1) {
            IntStream items = IntStream.range(0, params[0]);

            int[] og = items.toArray();
            float[] itemsArray = new float[og.length];
            for (int i = 0; i < itemsArray.length; i++) {
                itemsArray[i] = (float) og[i];
            }
            return new NumJavaArray(itemsArray);

        } else if (params.length == 2) {
            // User will submit 2 items, length and width of matrix, for instance 3,3 returns [[1,2,3],[4,5,6],[7,8,9]]
            int[] items = IntStream.range(0, params[0]*params[1]).toArray();
            float[][] finalArray = new float[params[0]][params[1]];
            for(int i=0; i<params[0];i++)
                for(int j=0;j<params[1];j++)
                    finalArray[i][j] = (float) items[(j*params[0]) + i];
            return new NumJavaArray(finalArray);
        }
        return null;
    }
    public String toString() {
        return switch (ndim) {
            case 1 -> Arrays.toString(array1D);
            case 2 -> this.deepToString(array2D);
            case 3 -> deeperToString(array3D);
            default -> throw new IllegalStateException("Unexpected value: " + ndim);
        };
    }
    private String deepToString(float[][] items) {
        StringBuilder builder = new StringBuilder();
        builder.append('[');
        for (float[] arr : items) {
            builder.append(Arrays.toString(arr));
            builder.append(',');
        }
        builder.delete(builder.length()-1, builder.length());
        builder.append(']');
        return builder.toString();
    }
    private String deeperToString(float[][][] items) {
        StringBuilder builder = new StringBuilder();
        builder.append('[');

        for (float[][] arr : items) {

            builder.append(deepToString(arr));

            builder.append(',');

        }
        builder.append(']');
        builder.delete(builder.length()-2, builder.length()-1);
        return builder.toString();
    }
    private float[] reverse(float[] arr) {
        float[] list = new float[arr.length];
        for (int i = arr.length - 1; i >= 0; i--) {
            list[arr.length-i - 1] = arr[i];
        }
        return list;
    }
    private float[][] reverse(float[][] arr) {
        float[][] list = new float[arr.length][arr[0].length];
        for (int i = arr.length - 1; i >= 0; i--) {
            list[arr.length-i - 1] = arr[i];
        }
        return list;
    }
    private float[][][] reverse(float[][][] arr) {
        float[][][] list = new float[arr.length][arr[0].length][arr[0][0].length];
        for (int i = arr.length - 1; i >= 0; i--) {
            list[arr.length-i - 1] = arr[i];
        }
        return list;
    }

    public NumJavaArray reversed() {

        if (ndim == 1) {
            return new NumJavaArray(reverse(array1D));
        } else if (ndim == 2) {
            float[][] items = new float[array2D.length][array2D[0].length];
            for (int i = 0; i < array2D.length; i++) {
                items[i] = reverse(array2D[i]);

            }
            return new NumJavaArray(reverse(items));
        } else if (ndim == 3) {
            float[][][] items = new float[array3D.length][array3D[0].length][array3D[0][0].length];
//            items = reverse(array3D);
            // [[[1,2,3],[4,5,6]],[[7,8,9],[10,11,12]]]
            for (int i = 0; i < array3D.length; i++) {
                float[][] arr2d = array3D[i];
                items[i] = reverse(arr2d);
            }
            items = reverse(items);
            for (int i = 0; i < items.length; i++) {

                for (int j = 0; j < items[i].length; j++) {
                    items[i][j] = reverse(items[i][j]);

                }

            }
            return new NumJavaArray(items);
        }
        return null;
    }
    public float get(int... indices) {
        if (ndim != indices.length) throw new IllegalArgumentException("incorrect number of indices");
        if (ndim == 1) {
            return array1D[indices[0]];
        } else if (ndim == 2) {
            return array2D[indices[0]][indices[1]];
        } else {
            return array3D[indices[0]][indices[1]][indices[2]];
        }
    }
    public float average() {
        float total = 0;
        if (ndim==1) {
            for (float v : array1D) {
                total += v;
            }
            return total/(float) array1D.length;
        } else if (ndim == 2) {
            int amountofNumbers = 0;
            for (float[] floats : array2D) {
                for (float aFloat : floats) {
                    total += aFloat;
                    amountofNumbers++;
                }

            }
            return total/(float) amountofNumbers;
        } else if (ndim == 3) {
            int amountofNumbers = 0;
            for (float[][] floats : array3D) {
                for (float[] aFloat : floats) {
                    for (float v : aFloat) {
                        total += v;
                        amountofNumbers++;
                    }
                }
            }
            return total/(float) amountofNumbers;
        }
        return 0;
    }
}

