/*  Hussein Adem 2024-01-26
    Hussein.adem@hotmail.com

    interface for implementing a class with input and outputs
 */

package io;

public interface IO {
    boolean yesNo(String prompt);

    String getString();

    void addString(String s);

    void clear();

    void exit();

}
