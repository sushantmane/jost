package org.jost.io;

import com.sun.jna.NativeLong;
import com.sun.jna.Structure;

import java.util.Arrays;
import java.util.List;

public class TimeSpec extends Structure {

    public NativeLong tv_sec;
    public NativeLong tv_nsec;

    protected List<String> getFieldOrder() {
        return Arrays.asList(new String[] {"tv_sec", "tv_nsec"});
    }

    public TimeSpec() {
        super();
    }

    public TimeSpec(NativeLong tv_sec, NativeLong tv_nsec) {
        super();
        this.tv_sec = tv_sec;
        this.tv_nsec = tv_nsec;
    }

    public static class ByReference extends TimeSpec implements Structure.ByReference {}

    public long seconds() {
        return this.tv_sec.longValue();
    }

    public long nanoseconds() {
        return this.tv_nsec.longValue();
    }
}
