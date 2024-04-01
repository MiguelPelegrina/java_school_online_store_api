package com.java_school.final_task.utils;

public final class StringValues {
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Security                                                                                                    //
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //@Value("${SECRET}:mysecretkey")
    public static final String SECRET_KEY = System.getenv("SECRET") != null ? System.getenv("SECRET") : "mysecretkey";

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Exceptions                                                                                                   //
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public static final String EMAIL_ALREADY_IN_USE = "A user with that email already exists";
    public static final String INACTIVE_USER = "User is not active";
    public static final String INSUFFICIENT_PERMISSIONS = "Insufficient permissions";
    public static final String INSTANCE_NOT_FOUND = "Instance %s not found";
    public static final String INTERNAL_SERVER_ERROR = "An internal server error occurred";
    public static final String MAIL_SENDING_ERROR = "The mail could not be send";
    public static final String PRODUCT_ALREADY_EXISTS = "The following product already has the same ISBN: %s";
    public static final String PRODUCT_NOT_AVAILABLE = "The following product is not being sold anymore: %s";
    public static final String PRODUCT_OUT_OF_STOCK = "The following product is out of stock: %s";
    public static final String PASSWORD_NOT_MATCHING = "The user credentials (email and password) do not match";

    public static final String USER_DOES_NOT_EXIST = "The user with the email %s is not registered";

    private StringValues() {
        throw new IllegalStateException("Utility class");
    }
}
