package com.basdxz.qtjambitools.task.impl;

import com.basdxz.qtjambitools.extension.impl.QtJambiExtension;
import com.basdxz.qtjambitools.processing.FileProcessor;
import com.basdxz.qtjambitools.processing.impl.QTJavaCompiler;
import com.basdxz.qtjambitools.processing.impl.QTSourceTransformer;
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
import java.util.Collection;

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
    @OutputDirectory
    protected File outputDirectory;
    @Input
    protected String packagePath;

    public QTJavaCompilationTask() {
        val qtJambiExtension = QtJambiExtension.qtJambiExtension(getProject());
        inputDirectory = new File(qtJambiExtension.getDefaultInputDirectory());
        outputDirectory = new File(qtJambiExtension.getDefaultOutputDirectory());
        packagePath = qtJambiExtension.getDefaultPackagePath();
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
        generateJavaFiles();
    }

    protected void prepareOutputDirectory() {
        try {
            createDirectory(outputDirectory);
        } catch (IOException e) {
            throw new RuntimeException("Cannot create output directory: ", e);
        }
        outputFiles().forEach(FileUtil::deleteFile);
    }

    protected void generateJavaFiles() {
        newQTJavaCompiler().process(inputFiles());
        newQTSourceTransformer().process(outputFiles());
    }

    protected FileProcessor newQTJavaCompiler() {
        return new QTJavaCompiler(outputDirectory, packagePath);
    }

    protected FileProcessor newQTSourceTransformer() {
        return new QTSourceTransformer();
    }

    protected Collection<File> inputFiles() {
        return listFiles(inputDirectory, new String[]{QT_UI_FILE_EXTENSION}, true);
    }

    protected Collection<File> outputFiles() {
        return listFiles(outputDirectory, new String[]{JAVA_FILE_EXTENSION}, true);
    }
}
