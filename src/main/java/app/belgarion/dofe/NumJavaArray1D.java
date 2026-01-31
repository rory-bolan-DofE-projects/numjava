package app.belgarion.dofe;


@Deprecated
public class NumJavaArray1D {
    private final float[] itemList;
    public NumJavaArray1D(float[] items) {
        this.itemList = items;
    }
    public float[] getArray() {
        return this.itemList;
    }
    public static class DivisionByZeroException extends RuntimeException {
        public DivisionByZeroException(String message) {
            super(message);
        }
    }

    public float[] reverse() {
        float[] result = new float[itemList.length];
        int j = 0;
        for (int i = itemList.length - 1; i >= 0; i--) {
            result[j++] = itemList[i];
        }
        return result;
    }

    public float average() {
        float total = 0;
        for (float num : itemList) {
            total+=num;
        }
        int arrayLength = itemList.length;
        if (arrayLength != 0) {
            return total/arrayLength;
        } else {
            return 0;
        }

    }
    public float get(int index) {
        return itemList[index];
    }
    public float[] get(int lower, int upper) {
        float[] items = new float[upper-(lower-1)];
        // if lower is 5 and upper is 10 we do 6 loops to get elements 5,6,7,8,9,10
        int index = 0;
        for (int i = lower; i <=upper; i++) {
            items[index] = itemList[i];
            index++;
        }
        return items;
    }
}
