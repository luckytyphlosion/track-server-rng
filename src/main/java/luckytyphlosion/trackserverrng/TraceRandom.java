package luckytyphlosion.trackserverrng;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.stream.DoubleStream;

public class TraceRandom extends Random {

    /**
     * 
     */
    private static final long serialVersionUID = -3409321421684293515L;

    public final int finalField;

    public TraceRandom() {
        super();

        this.finalField = 1;
        StackTraceElement[] stackTraceElems = Thread.currentThread().getStackTrace();
        StringBuilder output = new StringBuilder();
        for (StackTraceElement stackTraceElem : stackTraceElems) {
            output.append(stackTraceElem);
            output.append('\n');
        }
        System.out.println(output);
        writeStringToFile(output.toString(), "trace_random.txt");
    }

    public static void writeStringToFile(String output, String filename) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(new File(filename));
            writer.write(output);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public DoubleStream doubles() {
        return super.doubles();
    }

    public static void main(String[] args) {
        new TraceRandom();
    }
}
