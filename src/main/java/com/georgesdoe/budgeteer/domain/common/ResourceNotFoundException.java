package com.georgesdoe.budgeteer.domain.common;

public class ResourceNotFoundException extends Exception {

    Class<?> resourceClass;

    public ResourceNotFoundException(Class<?> resourceClass) {
        this.resourceClass = resourceClass;
    }

    @Override
    public String getMessage() {
        return resourceClass.getSimpleName() + " not found.";
    }
}
