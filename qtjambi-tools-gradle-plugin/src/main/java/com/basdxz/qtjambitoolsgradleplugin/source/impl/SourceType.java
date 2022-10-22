package com.basdxz.qtjambitoolsgradleplugin.source.impl;

import com.basdxz.qtjambitoolsgradleplugin.source.ISourceType;
import lombok.*;

@Getter
@AllArgsConstructor
public enum SourceType implements ISourceType {
    MAIN("main", false, false),
    TEST("test", true, false),
    MAIN_GENERATED("main", false, true),
    TEST_GENERATED("test", true, true),
    ;

    private final String sourceSet;
    private final boolean test;
    private final boolean generated;
}
