package org.jost.io;

import com.sun.jna.NativeLong;
import com.sun.jna.Structure;

import java.util.Arrays;
import java.util.List;

public class Stat extends Structure {

    // Stat structure
    public NativeLong       st_dev;     /* ID of device containing file */
    public NativeLong       st_ino;     /* inode number */
    public NativeLong       st_nlink;   /* number of hard links */
    public int              st_mode;    /* protection */
    public int              st_uid;     /* user ID of owner */
    public int              st_gid;     /* group ID of owner */
    public int              __pad0;
    public NativeLong       st_rdev;    /* device ID (if special file) */
    public NativeLong       st_size;    /* total size, in bytes */
    public NativeLong       st_blksize; /* blocksize for file system I/O */
    public NativeLong       st_blocks;  /* number of 512B blocks allocated */
    public TimeSpec         st_atime;   /* time of last access */
    public TimeSpec         st_mtime;   /* time of last modification */
    public TimeSpec         st_ctime;   /* time of last status change */
    public NativeLong[]     __unused = new NativeLong[3];

    public Stat() {
        super();
    }

    protected List<String> getFieldOrder() {
        return Arrays.asList(new String[] {"st_dev", "st_ino", "st_nlink", "st_mode",
                "st_uid", "st_gid", "__pad0","st_rdev", "st_size", "st_blksize",
                "st_blocks", "st_atime", "st_mtime", "st_ctime", "__unused"});
    }

    public static class ByReference extends Stat implements Structure.ByReference {}

    public long deviceId() {
        return this.st_dev.longValue();
    }

    public long inode() {
        return this.st_ino.longValue();
    }
    public long links() {
        return this.st_nlink.longValue();
    }

    public int mode() {
        return this.st_mode;
    }

    public int uid() {
        return this.st_uid;
    }

    public int gid() {
        return this.st_gid;
    }

    public long specialFileDevId() {
        return this.st_rdev.longValue();
    }

    public long fsize() {
        return this.st_size.longValue();
    }

    public long blockSize() {
        return this.st_blksize.longValue();
    }

    public long blocks() {
        return this.st_blocks.longValue();
    }

    public long atime() {
        return this.st_atime.seconds();
    }

    public long atimeNanos() {
        return this.st_atime.nanoseconds();
    }

    public long ctime() {
        return this.st_ctime.seconds();
    }

    public long ctimeNanos() {
        return this.st_ctime.nanoseconds();
    }

    public long mtime() {
        return this.st_mtime.seconds();
    }

    public long mtimeNanos() {
        return this.st_mtime.nanoseconds();
    }
}
