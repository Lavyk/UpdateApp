/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package update;

import convert.Funcoes;
import convert.Main;
import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import javax.swing.JOptionPane;
import sun.misc.BASE64Encoder;

/**
 *
 * @author Super i3
 */
public class Update {

    public Update() {
        super();
    }

    public String process() throws MalformedURLException, IOException {
        BASE64Encoder enc = new sun.misc.BASE64Encoder();
        String a = null;
        //
        URL url = new URL("http://lavyk.dx.am/mapconvert/version.html");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.addRequestProperty("Request-Method", "GET");
        connection.addRequestProperty("Authorization", "Basic " + enc.encode("user:password".getBytes()));
        connection.setDoInput(true);
        connection.setDoOutput(false);
        connection.connect();
        if (connection.getResponseCode() == 200) {//HttpURLConnection.HTTP_ACCEPTED
            BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuffer newData = new StringBuffer();
            String s = "";
            while (null != ((s = br.readLine()))) {
                newData.append(s);
            }
            br.close();
            a = new String(newData);
        }
        //"Resultado: " + connection.getResponseCode() + "/" + connection.getResponseMessage();

        connection.disconnect();
        return a;
    }

    public static void main(String[] args) throws IOException {
        Main main = new Main();
        Update a = new Update();
        Funcoes b = new Funcoes();
        Download down = new Download();

        if (a.process().equals(b.version)) {
            System.out.println("Versão atual: " + a.process());
            System.out.println("Seu conversor está atualizado.");
            main.setVisible(true);

        } else {

            Notice update = new Notice();
            update.setVersion(a.process());
            update.setVisible(true);
            if(!update.getSucess()){
                main.setVisible(true);
            }
            System.out.println("Seu conversor não está atualizado.");
        }

    }

}
