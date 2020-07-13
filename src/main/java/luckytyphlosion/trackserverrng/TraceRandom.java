package luckytyphlosion.trackserverrng;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.DoubleStream;
import java.util.TreeSet;

public class TraceRandom extends Random {

    /**
     * 
     */
    private static final long serialVersionUID = -3409321421684293515L;
    private TreeSet<String> stackTraces;
    private transient StringBuilder stringBuilderTemp;
    private String filename;
    private PrintWriter pw;

    public TraceRandom() {
        super();
        this.stackTraces = new TreeSet<>();
        this.stringBuilderTemp = new StringBuilder();
        this.filename = "server_rng_" + System.currentTimeMillis() + ".txt";
        try {
            this.pw = new PrintWriter(new BufferedWriter(new FileWriter(this.filename, true)));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void addStackTrace() {
        StackTraceElement[] stackTraceElems = Thread.currentThread().getStackTrace();

        for (int i = 2; i < stackTraceElems.length; i++) {
            stringBuilderTemp.append(stackTraceElems[i].toString());
            stringBuilderTemp.append('\n');
        }

        String output = stringBuilderTemp.toString();
        if (this.stackTraces.add(output)) {
            this.pw.println(output);
            System.out.println(output);
        }
        stringBuilderTemp.setLength(0);
    }

    public void closePrintWriter() {
        this.pw.close();
    }

    public void flushPrintWriter() {
        this.pw.flush();
    }

    public DoubleStream doubles() {
        addStackTrace();
        return super.doubles();
    }

    public int nextInt(int bound) {
        addStackTrace();
        return super.nextInt(bound);
    }

    public static void main(String[] args) {
        new TraceRandom();
    }
}
