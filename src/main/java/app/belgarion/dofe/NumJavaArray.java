package app.belgarion.dofe;



import java.util.*;
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
    public static NumJavaArray arange(int... params) throws NotImplementedException {
        if (params.length == 1) {
            IntStream items = IntStream.range(0, params[0]);

            int[] og = items.toArray();
            float[] itemsArray = new float[og.length];
            for (int i = 0; i < itemsArray.length; i++) {
                itemsArray[i] = (float) og[i];
            }
            return new NumJavaArray(itemsArray);

        } else if (params.length == 2) {
            // params[0] = rows (length), params[1] = cols (width)

            int[] items = IntStream.range(0, params[0] * params[1]).toArray();
            float[][] finalArray = new float[params[0]][params[1]];

            for (int i = 0; i < params[0]; i++) {
                for (int j = 0; j < params[1]; j++) {
                    finalArray[i][j] = (float) items[(i * params[1]) + j];
                }
            }

            return new NumJavaArray(finalArray);

        } else if (params.length == 3) {
            throw new NotImplementedException("3 Dimensional array constructor of type range is not in place yet");
        }
        return null;
    }
    public static NumJavaArray empty(int... params) {
        Random random = new Random();
        if (params.length == 1) {
            float[] nums = new float[params[0]];
            for (int i = 0; i < params[0]; i++) {
                nums[i] = random.nextFloat();
            }
            return new NumJavaArray(nums);
        } else if (params.length == 2) {
            float[][] nums = new float[params[0]][params[1]];
            for (int i = 0; i < params[0]; i++) {
                for (int j = 0; j < params[1]; j++) {
                    nums[i][j] = random.nextFloat();
                }
            }
            return new NumJavaArray(nums);
        } else {
            float[][][] nums = new float[params[0]][params[1]][params[2]];
            for (int i = 0; i < params[0]; i++) {
                for (int j = 0; j < params[1]; j++) {
                    for (int k = 0; k < params[2]; k++) {
                        nums[i][j][k] = random.nextFloat();
                    }
                }
            }
            return new NumJavaArray(nums);
        }
    }


    public String toString() {
        return switch (ndim) {
            case 1 -> Arrays.toString(array1D);
            case 2 -> NumJavaArray.deepToString(array2D);
            case 3 -> deeperToString(array3D);
            default -> throw new IllegalStateException("Unexpected value: " + ndim);
        };
    }
    private static String deepToString(float[][] items) {
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
    private static String deeperToString(float[][][] items) {
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
    public static NumJavaArray concatenate(NumJavaArray a, NumJavaArray b) throws NotImplementedException {
        if (a.shape.length != b.shape.length) {
            throw new IllegalArgumentException("The two arrays to concatenate must be of equal dimensions");

        }
        if (a.shape.length == 1) {
            float[] merged = new float[a.array1D.length + b.array1D.length];

            int idx = 0;
            for (float n : a.array1D) merged[idx++] = n;
            for (float n : b.array1D) merged[idx++] = n;
            return new NumJavaArray(merged);
        } else if (a.shape.length == 2) {
            if (a.array2D[0].length != b.array2D[0].length)
                throw new IllegalArgumentException(
                        "column lengths must be the same with " +
                                deepToString(a.array2D) + " and " + deepToString(b.array2D)
                );

            float[][] finalArray = new float[
                    a.array2D.length + b.array2D.length
                    ][a.array2D[0].length];

            int index = 0;
            for (int i = 0; i < a.array2D.length; i++) {
                finalArray[index++] = a.array2D[i].clone();
            }
            for (int i = 0; i < b.array2D.length; i++) {
                finalArray[index++] = b.array2D[i].clone();
            }

            return new NumJavaArray(finalArray);

        } else {
            throw new NotImplementedException("not done yet");
        }
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


    public NumJavaArray get(int... indices) {
        if (ndim != indices.length) throw new IllegalArgumentException("incorrect number of indices");
        if (ndim == 1) {
            return new NumJavaArray(new float[]{array1D[indices[0]]});
        } else if (ndim == 2) {
            return new NumJavaArray(new float[]{array2D[indices[0]][indices[1]]});
        } else {
            return new NumJavaArray(new float[]{array3D[indices[0]][indices[1]][indices[2]]});
        }
    }
    public NumJavaArray get(int index) {
        if (array1D != null) {
            return new NumJavaArray(new float[]{array1D[index]});
        } else if (array2D != null) {
            return new NumJavaArray(new float[]{array2D[index/array2D.length][index%array2D.length]});
        } else if (array3D!=null) {
            int x = index / (array3D[0].length * array3D[0][0].length );
            int y = (index / array3D[0][0].length ) % array3D[0].length;
            int z = index % array3D[0][0].length;
            return new NumJavaArray(new float[]{array3D[x][y][z]});
        } else return null;
    }
    public NumJavaArray get(int[] lowerbound, int[] upperbound) {

        if (ndim == 1) {
            if (lowerbound.length > 1 || upperbound.length > 1)
                throw new IllegalArgumentException("Incorrect parameter lengths");

            float[] to_be_returned = new float[upperbound[0] - lowerbound[0] + 1]; // <-- +1 for inclusive
            System.arraycopy(array1D, lowerbound[0], to_be_returned, 0, upperbound[0] - lowerbound[0] + 1);

            return new NumJavaArray(to_be_returned);
        } else if (ndim == 2) {
            ArrayList<Float> finalArray = getFinalArray(lowerbound, upperbound);
            float[] arr = new float[finalArray.size()];
            for (int i = 0; i < finalArray.size(); i++) {
                arr[i] = finalArray.get(i);
            }
            return new NumJavaArray(arr);
        }
        return null;
    }

    private ArrayList<Float> getFinalArray(int[] lower, int[] upper) {

        int minX = Math.min(lower[0], upper[0]);
        int maxX = Math.max(lower[0], upper[0]);
        int minY = Math.min(lower[1], upper[1]);
        int maxY = Math.max(lower[1], upper[1]);

        ArrayList<Float> finalArray = new ArrayList<>();

        for (int y = minY; y <= maxY; y++) {
            for (int x = minX; x <= maxX; x++) {
                finalArray.add(array2D[y][x]);
            }
        }

        return finalArray;
    }

    public void set(float value, int... indices) {
        if (ndim != indices.length) throw new IllegalArgumentException("incorrect number of indices");
        if (ndim == 1) {
            array1D[indices[0]] = value;
        } else if (ndim == 2) {
            array2D[indices[0]][indices[1]] = value;
        } else {
            array3D[indices[0]][indices[1]][indices[2]] = value;
        }
    }
    public void set(float value, int index) {
        if (array1D != null) {
            array1D[index] = value;
        } else if (array2D != null) {
           array2D[index/array2D.length][index%array2D.length] = value;
        } else if (array3D!=null) {
            int x = index / (array3D[0].length * array3D[0][0].length );
            int y = (index / array3D[0][0].length ) % array3D[0].length;
            int z = index % array3D[0][0].length;
            array3D[x][y][z] = value;
        }
    }
    public void set(float[] values, Point2D lowerbound, Point2D upperbound) {
        if (ndim != 2) throw new IllegalArgumentException("Only use Point2D for matrices");
        int width = upperbound.x() - lowerbound.x() + 1;
        int height = upperbound.y() - lowerbound.y() + 1;

        int total = width * height;

        if (values.length != total) {
            throw new IllegalArgumentException(
                    "Expected " + total + " values, got " + values.length
            );
        }

        int k = 0;
        for (int i = lowerbound.x(); i <= upperbound.x(); i++) {
            for (int j = lowerbound.y(); j <= upperbound.y(); j++) {
                array2D[i][j] = values[k++];
            }
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
    public NumJavaArray sort() {
        if (array1D!=null) {
            float[] temp = Arrays.copyOf(array1D, array1D.length);
            Arrays.sort(temp);
            return new NumJavaArray(temp);
        } else if (array2D!=null) {
            float[][] temp = Arrays.copyOf(array2D, array2D.length);
            Arrays.sort(temp, Comparator.comparingDouble(o -> o[0]));
            return new NumJavaArray(temp);
        }
        return null;
    }
    public static class NotImplementedException extends Exception {
        private NotImplementedException(String message) {super(message);}
    }
    public record Point3D(int x, int y, int z) {}
    public record Point2D(int x, int y) {}
}

