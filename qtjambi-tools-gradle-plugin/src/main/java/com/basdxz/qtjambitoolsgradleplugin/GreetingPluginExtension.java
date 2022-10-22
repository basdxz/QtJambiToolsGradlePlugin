package com.basdxz.qtjambitoolsgradleplugin;

import lombok.*;
import lombok.experimental.*;

@Setter
@Getter
@Accessors(fluent = true, chain = true)
public class GreetingPluginExtension {
    protected String greeter = "Baeldung";
    protected String message = "Message from the plugin!";
}