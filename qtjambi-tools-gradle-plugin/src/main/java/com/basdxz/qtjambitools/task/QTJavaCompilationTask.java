package com.basdxz.qtjambitools.task;

import com.basdxz.qtjambitools.extension.impl.QtJambiExtension;
import com.basdxz.qtjambitools.util.FileUtil;
import lombok.*;
import lombok.experimental.*;
import org.gradle.api.DefaultTask;
import org.gradle.api.tasks.Input;
import org.gradle.api.tasks.InputDirectory;
import org.gradle.api.tasks.OutputDirectory;
import org.gradle.api.tasks.TaskAction;

import java.io.File;
import java.io.IOException;

import static com.basdxz.qtjambitools.source.impl.JavaSourceSetWrapper.javaSourceSetWrapper;
import static com.basdxz.qtjambitools.source.impl.SourceType.MAIN_GENERATED;
import static com.basdxz.qtjambitools.util.Constants.JAVA_FILE_EXTENSION;
import static com.basdxz.qtjambitools.util.Constants.QT_UI_FILE_EXTENSION;
import static com.basdxz.qtjambitools.util.FileUtil.createDirectory;
import static org.apache.commons.io.FileUtils.listFiles;

@Setter
@Getter
@Accessors(fluent = false)
public class QTJavaCompilationTask extends DefaultTask {
    @InputDirectory
    protected File inputDirectory;
    @Input
    protected String packagePath;
    @OutputDirectory
    protected File outputDirectory;

    public QTJavaCompilationTask() {
        val qtJambiExtension = QtJambiExtension.qtJambiExtension(getProject());
        inputDirectory = new File(qtJambiExtension.defaultInputDirectory());
        packagePath = qtJambiExtension.defaultPackagePath();
        outputDirectory = new File(qtJambiExtension.defaultOutputDirectory());
    }

    public void init() {
        try {
            FileUtil.createDirectory(inputDirectory);
        } catch (IOException e) {
            throw new RuntimeException("Failed to create input directory", e);
        }
        javaSourceSetWrapper(getProject(), MAIN_GENERATED).add(outputDirectory);
    }

    @TaskAction
    public void compile() {
        prepareOutputDirectory();
        listFiles(inputDirectory, new String[]{QT_UI_FILE_EXTENSION}, true)
                .forEach(this::generateJavaFile);
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
