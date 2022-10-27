package com.basdxz.qtjambitools.processing;

import lombok.*;

import java.io.File;
import java.util.Collection;

public interface FileProcessor {
    default void process(@NonNull Collection<File> inputFiles) {
        inputFiles.forEach(this::process);
    }

    void process(@NonNull File inputFile);
}
