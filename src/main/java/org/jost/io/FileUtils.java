package org.jost.io;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.Path;

public class FileUtils {

    // returns type of file
    public static FileType getFileType(Path path) throws IOException {
        return FileStat.getFileType(path.toString());
    }

    // returns size in bytes as seen in stat command
    public static long getSize(Path path) throws IOException {
        return FileStat.getSize(path.toString());

    }

    // returns actual bytes allocated for file on disk
    public static long getDiskUsage(Path path) throws IOException {
        return FileStat.getDiskUsage(path.toString());
    }

    // returns file size
    public static long getLength(Path path) {
        return path.toFile().length();
    }

    // create sparse file of given size
    public static void createSparseFile(Path path, long size) throws IOException {
        RandomAccessFile file = new RandomAccessFile(path.toString(), "rw");
        file.setLength(size);
    }

    public static void punchHole(Path path, long start, long end) {

    }

    public static void main(String[] args) throws IOException {
        File file = new File("/home/sushant/");
        System.out.println(FileStat.getDiskUsage(file.toString()));

        FileStat stat = new FileStat(file.toString());
        System.out.println(stat.getDeviceId());
        System.out.println(stat.getInode());
        System.out.println(stat.getHardLinksCount());
        System.out.println(stat.getMode());
        System.out.println(stat.getUserId());
        System.out.println(stat.getGroupId());
        System.out.println(stat.getSpecialFileDevId());
        System.out.println(stat.getSize());
        System.out.println(stat.getIoBlockSize());
        System.out.println(stat.getBlocksAllocated());
        System.out.println(stat.getAccessTime());
        System.out.println(stat.getChangeTime());
        System.out.println(stat.getModificationTime());
        System.out.println(getFileType(file.toPath()));



    }
}
