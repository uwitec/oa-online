package com.fhi.framework.dao;

/**
 * @author Administrator
 */
public class DbDaoException extends Exception {

    /**
     * 
     */
    public DbDaoException() {
        super();
    }

    /**
     * @param message
     */
    public DbDaoException(String message) {
        super(message);
    }

    /**
     * @param cause
     */
    public DbDaoException(Throwable cause) {
        super(cause);
    }

    /**
     * @param message
     * @param cause
     */
    public DbDaoException(String message, Throwable cause) {
        super(message, cause);
    }
    
}
