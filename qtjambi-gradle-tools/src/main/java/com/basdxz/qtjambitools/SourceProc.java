package com.basdxz.qtjambitools;

import com.basdxz.qtjambitools.util.FileUtil;
import lombok.*;
import org.jboss.forge.roaster.Roaster;
import org.jboss.forge.roaster.model.source.JavaClassSource;
import org.jboss.forge.roaster.model.source.VisibilityScopedSource;

import java.io.File;

public class SourceProc {
    @SneakyThrows
    public static void main(String[] args) {
        val targetFile = new File("filename.txt");
        @Cleanup val inputStream = FileUtil.fileInputStream(targetFile);
        val javaUnit = Roaster.parseUnit(inputStream);
        val javaClassSource = (JavaClassSource) javaUnit.getTopLevelTypes().get(0);
        val fields = javaClassSource.getFields();
        fields.forEach(VisibilityScopedSource::setPublic);
        val processedSource = javaUnit.toUnformattedString();
        val formattedSource = Roaster.format(processedSource);
        @Cleanup val printWriter = FileUtil.filePrintWriter(targetFile);
        printWriter.print(formattedSource);
    }
}
