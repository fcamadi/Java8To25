module org.francd.java9 {
    uses org.francd.java9.modules.MessageFormatter;
    provides org.francd.java9.modules.MessageFormatter with
        org.francd.java9.modules.JsonFormatter,
        org.francd.java9.modules.XmlFormatter;
    requires java.base;

    exports org.francd.java9.modules;
    exports org.francd.java9.collections;
    exports org.francd.java9.process;
    exports org.francd.java9.jshell;
    exports org.francd.java9.streams;
    exports org.francd.java9.interfaces;
}