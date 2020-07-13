package luckytyphlosion.trackserverrng;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
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
    private boolean initialized = false;

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
        this.initialized = true;
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

    // Re-implemented methods with addStackTrace()

    public DoubleStream doubles() {
        addStackTrace();
        return super.doubles();
    }

    public DoubleStream doubles(double randomNumberOrigin, double randomNumberBound) {
        addStackTrace();
        return super.doubles(randomNumberOrigin, randomNumberBound);
    }

    public DoubleStream doubles(long streamSize) {
        addStackTrace();
        return super.doubles(streamSize);
    }

    public DoubleStream doubles(long streamSize, double randomNumberOrigin, double randomNumberBound) {
        addStackTrace();
        return super.doubles(streamSize, randomNumberOrigin, randomNumberBound);
    }

    public IntStream ints() {
        addStackTrace();
        return super.ints();
    }

    public IntStream ints(int randomNumberOrigin, int randomNumberBound) {
        addStackTrace();
        return super.ints(randomNumberOrigin, randomNumberBound);
    }

    public IntStream ints(long streamSize) {
        addStackTrace();
        return super.ints(streamSize);
    }

    public IntStream ints(long streamSize, int randomNumberOrigin, int randomNumberBound) {
        addStackTrace();
        return super.ints(streamSize, randomNumberOrigin, randomNumberBound);
    }

    public LongStream longs() {
        addStackTrace();
        return super.longs();
    }

    public LongStream longs(long randomNumberOrigin, long randomNumberBound) {
        addStackTrace();
        return super.longs(randomNumberOrigin, randomNumberBound);
    }

    public LongStream longs(long streamSize) {
        addStackTrace();
        return super.longs(streamSize);
    }

    public LongStream longs(long streamSize, long randomNumberOrigin, long randomNumberBound) {
        addStackTrace();
        return super.longs(streamSize, randomNumberOrigin, randomNumberBound);
    }

    public boolean nextBoolean() {
        addStackTrace();
        return super.nextBoolean();
    }

    public void nextBytes(byte[] bytes) {
        addStackTrace();
        super.nextBytes(bytes);
    }

    public double nextDouble() {
        addStackTrace();
        return super.nextDouble();
    }

    public float nextFloat() {
        addStackTrace();
        return super.nextFloat();
    }

    public double nextGaussian() {
        addStackTrace();
        return super.nextGaussian();
    }

    public int nextInt() {
        addStackTrace();
        return super.nextInt();
    }

    public int nextInt(int bound) {
        addStackTrace();
        return super.nextInt(bound);
    }

    public long nextLong() {
        addStackTrace();
        return super.nextLong();
    }

    public void setSeed(long seed) {
        if (initialized) {
            addStackTrace();
        }

        super.setSeed(seed);
    }
}
