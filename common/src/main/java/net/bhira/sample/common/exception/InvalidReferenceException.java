/**
 * The MIT License (MIT)
 *
 * Copyright (c) 2014 Baldeep Hira
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package net.bhira.sample.common.exception;

/**
 * Generic exception that is thrown when the object has invalid references.
 * 
 * @author Baldeep Hira
 */
public class InvalidReferenceException extends Exception {

	private static final long serialVersionUID = 20140627L;

	private int errorCode;

	/**
	 * Constructs a new InvalidReferenceException with the specified detail message.
	 * 
	 * @param message
	 *            the detail message. The detail message is saved for later retrieval by the
	 *            Throwable.getMessage() method.
	 */
	public InvalidReferenceException(String message) {
		super(message);
	}

	/**
	 * Constructs a new InvalidReferenceException with the specified detail message and cause.
	 * 
	 * @param message
	 *            the detail message. The detail message is saved for later retrieval by the
	 *            Throwable.getMessage() method.
	 * @param cause
	 *            the cause (which is saved for later retrieval by the Throwable.getCause() method).
	 *            (A null value is permitted, and indicates that the cause is nonexistent or
	 *            unknown.)
	 */
	public InvalidReferenceException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * Constructs a new InvalidReferenceException with the specified detail message and error code.
	 * 
	 * @param message
	 *            the detail message. The detail message is saved for later retrieval by the
	 *            Throwable.getMessage() method.
	 * @param errorCode
	 *            the integral error code value for the exception message.
	 */
	public InvalidReferenceException(String message, int errorCode) {
		super(message);
		this.errorCode = errorCode;
	}

	/**
	 * Constructs a new InvalidReferenceException with the specified detail message, cause.
	 * 
	 * @param message
	 *            the detail message. The detail message is saved for later retrieval by the
	 *            Throwable.getMessage() method.
	 * @param cause
	 *            the cause (which is saved for later retrieval by the Throwable.getCause() method).
	 *            (A null value is permitted, and indicates that the cause is nonexistent or
	 *            unknown.)
	 * @param errorCode
	 *            the integral error code value for the exception message.
	 */
	public InvalidReferenceException(String message, Throwable cause, int errorCode) {
		super(message, cause);
		this.errorCode = errorCode;
	}

	/**
	 * Get the error code associated with this exception.
	 * 
	 * @return the integral value for error code.
	 */
	public int getErrorCode() {
		return errorCode;
	}

}
