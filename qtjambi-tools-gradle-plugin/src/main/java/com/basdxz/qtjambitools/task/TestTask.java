package com.basdxz.qtjambitools.task;

import lombok.*;
import lombok.experimental.*;
import org.gradle.api.DefaultTask;
import org.gradle.api.tasks.InputFile;
import org.gradle.api.tasks.OutputFile;
import org.gradle.api.tasks.TaskAction;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

@Setter
@Getter
@Accessors(fluent = false)
public class TestTask extends DefaultTask {
    @InputFile
    public File input;
    @OutputFile
    public File output;

    @TaskAction
    private void taskAction() throws IOException {
        Files.copy(input.toPath(), output.toPath(), StandardCopyOption.REPLACE_EXISTING);
    }
}