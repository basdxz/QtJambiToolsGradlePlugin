package com.basdxz.qtjambitools.extension;

import lombok.*;

import java.util.*;

import static org.apache.commons.lang3.SystemUtils.OS_ARCH;
import static org.apache.commons.lang3.SystemUtils.OS_NAME;

public enum QTJambiOSQualifier {
    WINDOWS_X64("windows-x64", "amd64", "windows", "win"),
    LINUX_X64("linux-x64", "x64", "linux", "bsd", "unix"),
    MACOS("macos", "x64", "osx", "mac"),
    UNKNOWN,
    ;

    private static final QTJambiOSQualifier CURRENT_QUALIFIER = findCurrent();

    private final String value;
    private final String arch;
    private final List<String> names;

    QTJambiOSQualifier() {
        value = "";
        arch = "";
        names = Collections.emptyList();
    }

    QTJambiOSQualifier(@NonNull String value, @NonNull String arch, @NonNull String... names) {
        if (names.length < 1)
            throw new IllegalArgumentException("Names must be more than zero!");
        this.value = value;
        this.arch = arch;
        this.names = List.of(names);
    }

    public static Optional<String> currentQualifier() {
        val value = CURRENT_QUALIFIER.value;
        return Optional.ofNullable(value.isEmpty() ? null : value);
    }

    private static QTJambiOSQualifier findCurrent() {
        if (OS_NAME == null || OS_ARCH == null)
            return UNKNOWN;
        val name = OS_NAME.split(" ", 2)[0].toLowerCase(Locale.US);
        val arch = OS_ARCH.toLowerCase(Locale.US);
        return Arrays.stream(values())
                     .filter(qualifier -> qualifier.names.contains(name))
                     .filter(qualifier -> qualifier.arch.equals(arch))
                     .findFirst().orElse(UNKNOWN);
    }
}
