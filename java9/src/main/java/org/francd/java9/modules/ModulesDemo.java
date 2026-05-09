package org.francd.java9.modules;

import java.lang.module.ModuleDescriptor;
import java.util.*;
import java.util.stream.Collectors;

public class ModulesDemo {

    public static void main(String[] args) {
        moduleInfoExample();
        moduleReflection();
        serviceLoaderDemo();
    }

    private static void moduleInfoExample() {
        System.out.println("=== Module System (Java 9) ===");

        Optional<Module> coreModule = Optional.of(ModuleLayer.boot()
                .findModule("java.base").orElseThrow(RuntimeException::new));

        System.out.println("Module name: " + coreModule.get().getName());
        System.out.println("Module version: " + coreModule.get().getDescriptor().rawVersion().orElse("none"));
        System.out.println("Is modular: " + coreModule.get().isNamed());

        Set<String> packages = coreModule.get().getPackages();
        System.out.println("Number of packages: " + packages.size());

        ModuleDescriptor descriptor = coreModule.get().getDescriptor();
        System.out.println("Module descriptor: " + descriptor.name());

        if (!descriptor.exports().isEmpty()) {
            System.out.println("Some exports: " +
                descriptor.exports().stream()
                        .limit(3)
                        .map(ModuleDescriptor.Exports::source)
                        .collect(Collectors.toList()));
        }
    }

    private static void moduleReflection() {
        System.out.println("\n=== Module Reflection ===");

        Module currentModule = ModulesDemo.class.getModule();


        System.out.println("Current module: " + currentModule.getName());
        System.out.println("Is module named: " + currentModule.isNamed());

        System.out.println("Can read module: " + currentModule.canRead(coreModule()));
        if (currentModule.isNamed()) {
            System.out.println("Is module open: " + currentModule.isOpen(currentModule.getName()));
        } else {
            System.out.println("Is module open: N/A (unnamed module)");
        }

        if (currentModule.isNamed()) {
            List<? extends Class<? extends String>> packages = currentModule.getPackages().stream()
                .map(String::getClass)
                    .collect(Collectors.toList());
            System.out.println("packages: " + packages.size());
        } else {
            System.out.println("packages: N/A (unnamed module)");
        }

        Class<?> stringClass = String.class;
        Module stringModule = stringClass.getModule();
        System.out.println("String module: " + stringModule.getName());
    }

    private static void serviceLoaderDemo() {
        System.out.println("\n=== ServiceLoader ===");

        ServiceLoader<MessageFormatter> loader = ServiceLoader.load(MessageFormatter.class);

        System.out.println("Available formatters:");
        for (MessageFormatter formatter : loader) {
            System.out.println("  - " + formatter.getClass().getSimpleName());
        }

        ServiceLoader<MessageFormatter> providerLoader = ServiceLoader.loadInstalled(MessageFormatter.class);
        System.out.println("\nInstalled providers: " + providerLoader.stream().count());
    }

    private static Module coreModule() {
        return ModuleLayer.boot().findModule("java.base").orElseThrow(RuntimeException::new);
    }
}

