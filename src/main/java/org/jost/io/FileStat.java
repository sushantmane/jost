package org.jost.io;

import java.io.IOException;
enum FileType { FIFO, CHARACTER, DIRECTORY, BLOCK, REGULAR, SYMLINK, SOCKET }

public class FileStat {

    private static final int S_IFIFO = 0010000;
    private static final int S_IFCHR = 0020000;
    private static final int S_IFDIR = 0040000;
    private static final int S_IFBLK = 0060000;
    private static final int S_IFREG = 0100000;
    private static final int S_IFLNK = 0120000;
    private static final int S_IFSOCK = 0140000;
    private static final int S_IFMT = 0170000;
    private static final int S_BLKSIZE = 512;
    private static final int STAT_VER = 0;

    Stat stat;

    public FileStat(String pathname) throws IOException {
        this.stat = new Stat.ByReference();
        int rc = -1;
        try {
            rc = CLibrary.INSTANCE.__xstat(STAT_VER, pathname, stat);
        } catch(Exception e) {
        } finally {
            if (rc != 0) {
                throw new IOException("Failed to get file stats. rc = " + rc);
            }
        }
    }

    public long getDeviceId() {
        return this.stat.deviceId();
    }

    public long getInode() {
        return this.stat.inode();
    }

    public long getHardLinksCount() {
        return this.stat.links();
    }

    public int getMode() {
        return this.stat.mode();
    }

    public int getUserId() {
        return this.stat.uid();
    }

    public int getGroupId() {
        return this.stat.gid();
    }

    public long getSpecialFileDevId() {
        return this.stat.specialFileDevId();
    }

    public long getSize() {
        return this.stat.fsize();
    }

    public long getIoBlockSize() {
        return this.stat.blockSize();
    }

    public long getBlocksAllocated() {
        return this.stat.blocks();
    }

    public long getDiskUsage() {
        return getBlocksAllocated() * S_BLKSIZE;
    }

    public long getAccessTime() {
        return this.stat.atime();
    }

    public long getAccessTimeInNanos() {
        return this.stat.atimeNanos();
    }

    public long getChangeTime() {
        return this.stat.ctime();
    }

    public long getChangeTimeInNanos() {
        return this.stat.ctimeNanos();
    }

    public long getModificationTime() {
        return this.stat.mtime();
    }

    public long getModificationTimeInNanos() {
        return this.stat.mtimeNanos();
    }

    public static long getDiskUsage(String pathname) throws IOException {
        return new FileStat(pathname).getDiskUsage();
    }

    public static long getSize(String pathname) throws IOException {
        return new FileStat(pathname).getSize();
    }

    public static FileType getFileType(String pathname) throws IOException {
        return new FileStat(pathname).getFileType();
    }

    public FileType getFileType() throws IOException {
        FileType type = null;
        switch (getMode() & S_IFMT) {
            case S_IFIFO:
                type = FileType.FIFO;
                break;
            case S_IFCHR:
                type = FileType.CHARACTER;
                break;
            case S_IFDIR:
                type = FileType.DIRECTORY;
                break;
            case S_IFBLK:
                type = FileType.BLOCK;
                break;
            case S_IFREG:
                type = FileType.REGULAR;
                break;
            case S_IFLNK:
                type = FileType.SYMLINK;
                break;
            case S_IFSOCK:
                type = FileType.SOCKET;
                break;
        }
        return type;
    }

    public boolean isFifo() {
        if ((getMode() & S_IFMT) == S_IFIFO) {
            return true;
        }
        return false;
    }

    public boolean isNamedPipe() {
        return isFifo();
    }

    public boolean isCharDev() {
        if ((getMode() & S_IFMT) == S_IFCHR) {
            return true;
        }
        return false;
    }

    public boolean isDirectory() {
        if ((getMode() & S_IFMT) == S_IFDIR) {
            return true;
        }
        return false;
    }

    public boolean isBlockDev() {
        if ((getMode() & S_IFMT) == S_IFBLK) {
            return true;
        }
        return false;
    }


    public boolean isSymLink() {
        if ((getMode() & S_IFMT) == S_IFLNK) {
            return true;
        }
        return false;
    }

    public boolean isSocket() {
        if ((getMode() & S_IFMT) == S_IFSOCK) {
            return true;
        }
        return false;
    }

    public boolean isFile() {
        if ((getMode() & S_IFMT) == S_IFREG) {
            return true;
        }
        return false;
    }
}

