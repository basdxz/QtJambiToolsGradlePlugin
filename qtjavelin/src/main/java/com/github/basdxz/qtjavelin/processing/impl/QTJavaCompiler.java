package com.github.basdxz.qtjavelin.processing.impl;

import com.github.basdxz.qtjavelin.processing.FileProcessor;
import lombok.*;

import java.io.File;

@AllArgsConstructor
public class QTJavaCompiler implements FileProcessor {
    protected static final String OUTPUT_ARGUMENT = "--output";
    protected static final String PACKAGE_ARGUMENT = "--package";

    @NonNull
    protected final String inputDirectory;
    @NonNull
    protected final String outputDirectory;
    @NonNull
    protected final String packagePath;

    public QTJavaCompiler(@NonNull File inputDirectory, @NonNull File outputDirectory, @NonNull String packagePath) {
        this(inputDirectory.getAbsolutePath(), outputDirectory.getAbsolutePath(), packagePath);
    }

    @Override
    public void process(@NonNull File inputFile) {
        System.out.println("Processing: " + inputFile.getAbsolutePath());
        io.qt.uic.Main.main(uiCompilerArguments(inputFile));
    }

    protected String[] uiCompilerArguments(@NonNull File qtUiFile) {
        return new String[]{qtUiFile.getAbsolutePath(),
                            OUTPUT_ARGUMENT, outputDirectory,
                            PACKAGE_ARGUMENT, packagePath(qtUiFile)
        };
    }

    protected String packagePath(@NonNull File qtUiFile) {
        val inputFilePath = qtUiFile.getAbsolutePath();
        val relativeInputFilePath = inputFilePath.replace(inputDirectory, "");
        val lastSeparatorIndex = relativeInputFilePath.lastIndexOf(File.separatorChar);
        val packageSuffix = relativeInputFilePath.substring(0, lastSeparatorIndex)
                                                 .replace(File.separatorChar, '.');
        return packagePath + packageSuffix;
    }
}
