/*  Hussein Adem 2024-01-26
    Hussein.adem@hotmail.com

    class for taking inputs and outputs
 */

package io;

import javax.swing.*;

public class InputOutput implements IO {
    private IO gw;

    public InputOutput(IO gw){
        this.gw = gw;
    }

    public String getString(){
        return gw.getString();
    }

    public void addString(String message){
        gw.addString(message);
    }

    @Override
    public void clear() {
        gw.clear();
    }

    public boolean yesNo(String prompt) {
        int answer = JOptionPane.showConfirmDialog(null, prompt);
        return answer == JOptionPane.YES_OPTION;
    }

    public void exit(){
        System.exit(0);
    }


}
