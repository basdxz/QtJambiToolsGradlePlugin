package com.github.basdxz.qtjavelin.extension;

public interface IQtJambiExtension {
    String EXTENSION_NAME = "qtjambi";

    String getVersion();

    String getOsQualifier();

    String getDefaultInputDirectory();

    String getDefaultPackagePath();

    String getDefaultOutputDirectory();
}
