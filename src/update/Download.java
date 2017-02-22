/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package update;

import java.io.*;
import java.net.*;
import java.nio.channels.*;
import javax.swing.JOptionPane;

/**
 *
 * @author Super i3
 */
public class Download {

    public boolean start() throws MalformedURLException, IOException {
        try (
 
                ReadableByteChannel in = Channels.newChannel(new URL("http://lavyk.dx.am/mapconvert/Lavyk.jar").openStream());
                FileChannel out = new FileOutputStream("lib\\Lavyk.jar").getChannel()) {

            out.transferFrom(in, 0, Long.MAX_VALUE);
            JOptionPane.showMessageDialog(null, "Update completed");
            return true;
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error downloading update\nProceed to the old version!");
            System.out.print(e);
            return false;
        }
    }

}
