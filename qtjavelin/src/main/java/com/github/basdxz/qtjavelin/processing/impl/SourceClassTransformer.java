package com.github.basdxz.qtjavelin.processing.impl;

import com.github.basdxz.qtjavelin.processing.FileProcessor;
import com.github.basdxz.qtjavelin.util.FileUtil;
import lombok.*;
import org.jboss.forge.roaster.Roaster;
import org.jboss.forge.roaster.model.JavaUnit;
import org.jboss.forge.roaster.model.source.JavaClassSource;

import java.io.File;
import java.io.IOException;

public abstract class SourceClassTransformer implements FileProcessor {
    @Override
    public void process(@NonNull File inputFile) {
        val javaUnit = readInputFile(inputFile);
        javaUnit.getTopLevelTypes().stream()
                .map(javaType -> (JavaClassSource) javaType)
                .forEach(this::transform);
        writeOutputFile(inputFile, formatSource(javaUnit.toUnformattedString()));
    }

    protected JavaUnit readInputFile(@NonNull File inputFile) {
        try (val inputStream = FileUtil.fileInputStream(inputFile)) {
            return Roaster.parseUnit(inputStream);
        } catch (IOException e) {
            throw new RuntimeException("Failed to parse input file: " + inputFile.getAbsolutePath(), e);
        }
    }

    protected abstract void transform(@NonNull JavaClassSource javaClassSource);

    protected String formatSource(@NonNull String source) {
        return Roaster.format(source);
    }

    protected void writeOutputFile(@NonNull File outputFile, @NonNull String output) {
        try (val printWriter = FileUtil.filePrintWriter(outputFile)) {
            printWriter.print(output);
        } catch (IOException e) {
            throw new RuntimeException("Failed to save output file: " + outputFile.getAbsolutePath(), e);
        }
    }
}
