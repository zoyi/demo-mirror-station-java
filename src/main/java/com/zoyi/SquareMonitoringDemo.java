package com.zoyi;


import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;
import org.json.JSONObject;

import java.net.URISyntaxException;

/**
 * Created by huy on 15. 9. 17..
 */
public class SquareMonitoringDemo {
  public static void main(String[] args) throws URISyntaxException {
    // write your code here
    final Socket socket;

    IO.Options opts = new IO.Options();
    opts.forceNew = true;
    opts.reconnection = true;   // if you want to automatically reconnect, set true.
    opts.query = "user_email=" + "" +
        "&user_token=" + "" +
        "&square_mac=" + "" +
        "&mac=" + "";
    //a078baf1c33a
    opts.path = ApplicationHelper.getSensorPath();

    socket = IO.socket(ApplicationHelper.getSquareMonitoringURL(), opts);

    socket.on(Socket.EVENT_CONNECT, new Emitter.Listener() {
      public void call(Object... args) {
        System.out.println("connected");
      }
    }).on(Socket.EVENT_CONNECT_ERROR, new Emitter.Listener() {
      public void call(Object... args) {
        System.out.println("fail to connect");
      }
    }).on(Socket.EVENT_ERROR, new Emitter.Listener() {
      public void call(Object... args) {
        System.out.println("error");
        System.out.println(args[0]);
        socket.close();
      }
    }).on(Socket.EVENT_DISCONNECT, new Emitter.Listener() {
      public void call(Object... args) {
        System.out.println("disconnected");
      }
    }).on(ApplicationHelper.SIGNAL_EVENT, new Emitter.Listener() {
      // Listen to monitoring event.
      public void call(Object... args) {
        JSONObject obj = (JSONObject) args[0];
        System.out.println(obj);
      }

    });

    socket.connect();
  }
}
