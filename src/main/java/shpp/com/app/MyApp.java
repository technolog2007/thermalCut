package shpp.com.app;

import shpp.com.graf.Window;
import shpp.com.services.Receiver;

public class MyApp {

    public static void main(String[] args) {
        Window window = new Window();
        window.setReceiver(Receiver.getReceiver());
        window.createWindow();
    }

}
