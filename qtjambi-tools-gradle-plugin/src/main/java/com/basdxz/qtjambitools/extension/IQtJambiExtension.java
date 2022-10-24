package com.basdxz.qtjambitools.extension;

public interface IQtJambiExtension {
    String version();

    String osQualifier();

    String defaultInputDirectory();

    String defaultPackagePath();

    String defaultOutputDirectory();
}
