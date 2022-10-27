package com.github.basdxz.qtjavelin.extension;

public interface IQtJavelinExtension {
    String EXTENSION_NAME = "qtjavelin";

    String getVersion();

    String getOsQualifier();

    String getDefaultInputDirectory();

    String getDefaultPackagePath();

    String getDefaultOutputDirectory();
}
