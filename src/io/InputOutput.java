package io;

import javax.swing.*;

public class InputOutput implements IO {
    private SimpleWindow gw;

    public InputOutput(SimpleWindow gw){
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
