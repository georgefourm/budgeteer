package com.georgesdoe.budgeteer.common.domain;

public class ResourceNotFoundException extends RuntimeException {

    Class<?> resourceClass;

    public ResourceNotFoundException(Class<?> resourceClass) {
        this.resourceClass = resourceClass;
    }

    @Override
    public String getMessage() {
        var name = resourceClass.getSimpleName();
        if (name.endsWith("Entity")) {
            name = name.substring(0, name.length() - "Entity".length());
        }
        return name + " not found.";
    }
}
