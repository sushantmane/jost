package org.jost.io;

import com.sun.jna.Library;
import com.sun.jna.Native;

interface CLibrary extends Library {

    static final CLibrary INSTANCE = (CLibrary) Native.loadLibrary("c", CLibrary.class);

    // native methods
    int __xstat(int __ver, String __filename, Stat __stat_buf);
    int __lxstat(int __ver, String __filename, Stat __stat_buf);
}
