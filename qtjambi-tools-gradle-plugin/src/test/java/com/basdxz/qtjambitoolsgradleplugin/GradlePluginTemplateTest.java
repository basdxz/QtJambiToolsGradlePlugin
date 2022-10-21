package com.basdxz.qtjambitoolsgradleplugin;

import lombok.*;
import org.gradle.testfixtures.ProjectBuilder;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GradlePluginTemplateTest {
    @Test
    public void greetingTest() {
        val project = ProjectBuilder.builder().build();
        project.getPluginManager().apply("com.basdxz.gradleplugintemplate");
        assertTrue(project.getPluginManager().hasPlugin("com.basdxz.gradleplugintemplate"));
        assertNotNull(project.getTasks().getByName("hello"));
    }
}
