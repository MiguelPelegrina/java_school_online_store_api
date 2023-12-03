package com.java_school.final_task.utils;

public final class StringValues {
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Security                                                                                                    //
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public static final String SECRET_KEY = System.getenv("SECRET") != null ? System.getenv("SECRET") : "mysecretkey";

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Exceptions                                                                                                   //
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public static final String ACCEPTABLE_MEDIA = "Acceptable MIME type: ";
    public static final String EMAIL_ALREADY_IN_USE = "A user with that email already exists";
    public static final String ID_ALREADY_TAKEN = "ID is already taken %s";
    public static final String INACTIVE_USER = "User is not active";
    public static final String INSUFFICIENT_PERMISSIONS = "Insufficient permissions";
    public static final String INSTANCE_NOT_FOUND = "Instance %s not found";
    public static final String INVALID_CREDENTIALS = "Invalid username or password";
    public static final String PRODUCT_NOT_AVAILABLE = "The following product is not being sold anymore: %s";
    public static final String PRODUCT_OUT_OF_STOCK = "The following product is out of stock: %s";
    public static final String PASSWORD_NOT_MATCHING = "The user credentials (email and password) do not match";
    public static final String USER_DOES_NOT_EXIST = "The user with the email %s is not registered";

    private StringValues() {
        throw new IllegalStateException("Utility class");
    }
}
