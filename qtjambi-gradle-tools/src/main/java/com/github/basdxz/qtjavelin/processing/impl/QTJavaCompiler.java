package com.github.basdxz.qtjavelin.processing.impl;

import com.github.basdxz.qtjavelin.processing.FileProcessor;
import lombok.*;

import java.io.File;

@AllArgsConstructor
public class QTJavaCompiler implements FileProcessor {
    protected static final String OUTPUT_ARGUMENT = "--output";
    protected static final String PACKAGE_ARGUMENT = "--package";

    @NonNull
    protected final String outputDirectory;
    @NonNull
    protected final String packagePath;

    public QTJavaCompiler(@NonNull File outputDirectory, @NonNull String packagePath) {
        this(outputDirectory.getAbsolutePath(), packagePath);
    }

    @Override
    public void process(@NonNull File inputFile) {
        io.qt.uic.Main.main(uiCompilerArguments(inputFile));
    }

    protected String[] uiCompilerArguments(@NonNull File qtUiFile) {
        return new String[]{qtUiFile.getAbsolutePath(),
                            OUTPUT_ARGUMENT, outputDirectory,
                            PACKAGE_ARGUMENT, packagePath
        };
    }
}
