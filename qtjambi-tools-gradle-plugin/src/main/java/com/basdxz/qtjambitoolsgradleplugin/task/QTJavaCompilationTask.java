package com.basdxz.qtjambitoolsgradleplugin.task;

import lombok.*;
import org.gradle.api.DefaultTask;
import org.gradle.api.tasks.Input;
import org.gradle.api.tasks.InputFiles;
import org.gradle.api.tasks.OutputFiles;
import org.gradle.api.tasks.TaskAction;

import java.io.File;
import java.io.IOException;

import static com.basdxz.qtjambitoolsgradleplugin.util.Constants.JAVA_FILE_EXTENSION;
import static com.basdxz.qtjambitoolsgradleplugin.util.Constants.QT_UI_FILE_EXTENSION;
import static com.basdxz.qtjambitoolsgradleplugin.util.FileUtil.createDirectory;
import static org.apache.commons.io.FileUtils.listFiles;

@AllArgsConstructor
public class QTJavaCompilationTask extends DefaultTask {
    @InputFiles
    protected final File inputDirectory;
    @Input
    protected final String packagePath;
    @OutputFiles
    protected final File outputDirectory;

    @TaskAction
    protected void compile() {
        prepareInputDirectory();
        prepareOutputDirectory();
        listFiles(inputDirectory, new String[]{QT_UI_FILE_EXTENSION}, true).forEach(this::generateJavaFile);
    }

    protected void prepareInputDirectory() {
        try {
            createDirectory(inputDirectory);
        } catch (IOException e) {
            throw new RuntimeException("Cannot create input directory: ", e);
        }
    }

    protected void prepareOutputDirectory() {
        try {
            createDirectory(outputDirectory);
        } catch (IOException e) {
            throw new RuntimeException("Cannot create output directory: ", e);
        }
        listFiles(outputDirectory, new String[]{JAVA_FILE_EXTENSION}, true)
                .forEach(file -> {
                    if (!file.delete())
                        throw new RuntimeException("Failed to delete file: " + file);
                });
    }

    protected void generateJavaFile(@NonNull File qtUiFile) {
        io.qt.uic.Main.main(uiCompilerArguments(qtUiFile));
    }

    protected String[] uiCompilerArguments(@NonNull File qtUiFile) {
        return new String[]{qtUiFile.getAbsolutePath(),
                            "--output", outputDirectory.getAbsolutePath(),
                            "--package", packagePath};
    }
}
