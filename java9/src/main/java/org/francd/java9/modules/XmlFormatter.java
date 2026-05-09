package org.francd.java9.modules;

public class XmlFormatter implements MessageFormatter {
    public XmlFormatter() {}
    @Override
    public String format(String message) {
        return "<message>" + message + "</message>";
    }
}