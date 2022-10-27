package com.github.basdxz.qtjavelin.util;

import lombok.*;
import lombok.experimental.*;
import org.apache.commons.io.input.BufferedFileChannelInputStream;

import java.io.*;

@UtilityClass
public final class FileUtil {
    public static void createDirectory(@NonNull File directory) throws IOException {
        if (directory.exists()) {
            if (!directory.isDirectory())
                throw new IOException(directory.getAbsolutePath() + " exists but is not a directory.");
            return;
        }
        if (!directory.mkdirs())
            throw new IOException("Failed to create directory: " + directory.getAbsolutePath());
    }

    public static void deleteFile(@NonNull File file) {
        if (!file.delete())
            throw new RuntimeException("Failed to delete file: " + file);
    }

    public static InputStream fileInputStream(@NonNull File inputFile) throws IOException {
        return new BufferedFileChannelInputStream(inputFile);
    }

    public static PrintWriter filePrintWriter(@NonNull File outputFile) throws FileNotFoundException {
        return new PrintWriter(fileOutputStream(outputFile));
    }

    public static OutputStream fileOutputStream(@NonNull File outputFile) throws FileNotFoundException {
        return new FileOutputStream(outputFile);
    }
}
