package com.basdxz.qtjambitools.extension.impl;

public class GreetingPluginExtension implements IMuahaha {
    private String version = "Baeldung";
    private String greeter = "Baeldung";
    private String message = "Message from the plugin!";

    @Override
    public String getVersion() {
        return this.version;
    }

    @Override
    public String getGreeter() {
        return this.greeter;
    }

    @Override
    public String getMessage() {
        return this.message;
    }

    @Override
    public void setVersion(String version) {
        this.version = version;
    }

    public void setGreeter(String greeter) {
        this.greeter = greeter;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}