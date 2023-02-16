package com.example.apollofy.utilites;

//Esta es la excepción de la capa de negocio, no tiene que ver con la API
public class ElementNotFoundException extends RuntimeException {

    public ElementNotFoundException(Class type, Long id ) {
        super("Element of type " + type.getSimpleName() + " with id " + id + " not found");
    }

}
