package com.basdxz.qtjambitools.util;

import lombok.*;
import lombok.experimental.*;

import java.io.File;
import java.io.IOException;

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
}
