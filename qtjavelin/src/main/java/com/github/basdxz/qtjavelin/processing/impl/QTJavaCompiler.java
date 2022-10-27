package com.github.basdxz.qtjavelin.processing.impl;

import com.github.basdxz.qtjavelin.processing.FileProcessor;
import lombok.*;

import java.io.File;

public class QTJavaCompiler implements FileProcessor {
    protected static final String OUTPUT_ARGUMENT = "--output";
    protected static final String PACKAGE_ARGUMENT = "--package";

    @NonNull
    protected final String inputDirectory;
    @NonNull
    protected final String outputDirectory;
    @NonNull
    protected final String packagePath;
    protected final boolean packageHierarchyInOutput;

    public QTJavaCompiler(@NonNull File inputDirectory, @NonNull File outputDirectory, @NonNull String packagePath, boolean packageHierarchyInOutput) {
        this.inputDirectory = inputDirectory.getAbsolutePath();
        this.outputDirectory = outputDirectory.getAbsolutePath();
        this.packagePath = packagePath;
        this.packageHierarchyInOutput = packageHierarchyInOutput;
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
        if (!packageHierarchyInOutput)
            return packagePath;
        val inputFilePath = qtUiFile.getAbsolutePath();
        val relativeInputFilePath = inputFilePath.replace(inputDirectory, "");
        val lastSeparatorIndex = relativeInputFilePath.lastIndexOf(File.separatorChar);
        val packageSuffix = relativeInputFilePath.substring(0, lastSeparatorIndex)
                                                 .replace(File.separatorChar, '.');
        return packagePath + packageSuffix;
    }
}
