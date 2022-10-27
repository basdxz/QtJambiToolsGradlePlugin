package com.basdxz.qtjambitools.processing.impl;

import lombok.*;
import org.jboss.forge.roaster.model.source.JavaClassSource;
import org.jboss.forge.roaster.model.source.VisibilityScopedSource;

@NoArgsConstructor
public class QTSourceTransformer extends SourceClassTransformer {
    @Override
    protected void transform(@NonNull JavaClassSource javaClassSource) {
        javaClassSource.getFields().forEach(VisibilityScopedSource::setPublic);
    }
}
