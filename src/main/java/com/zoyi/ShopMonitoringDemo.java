package com.zoyi;

import com.github.nkzawa.emitter.Emitter;
import com.github.nkzawa.socketio.client.IO;
import com.github.nkzawa.socketio.client.Socket;
import org.json.JSONObject;

import java.net.URISyntaxException;

/**
 * Created by huy on 15. 9. 17..
 */
public class ShopMonitoringDemo {

  public static void main(String[] args) throws URISyntaxException {
    // write your code here
    final Socket socket;

    IO.Options opts = new IO.Options();
    opts.forceNew = true;
    opts.reconnection = true;   // if you want to automatically reconnect, set true.
    opts.query = "user_email=" + "" +
                  "&user_token=" + "" +
                  "&shop_id=" + "" +
                  "&mac=" + "";
    opts.path = ApplicationHelper.getSensorPath();

    socket = IO.socket(ApplicationHelper.getShopMonitoringURL(), opts);

    socket.on(Socket.EVENT_CONNECT, new Emitter.Listener() {
      @Override
      public void call(Object... args) {
        System.out.println("connected");
      }
    }).on(Socket.EVENT_CONNECT_ERROR, new Emitter.Listener() {
      @Override
      public void call(Object... args) {
        System.out.println("fail to connect");
      }
    }).on(Socket.EVENT_ERROR, new Emitter.Listener() {
      @Override
      public void call(Object... args) {
        System.out.println("error");
        System.out.println(args[0]);
        socket.close();
      }
    }).on(Socket.EVENT_DISCONNECT, new Emitter.Listener() {
      @Override
      public void call(Object... args) {
        System.out.println("disconnected");
      }
    }).on(ApplicationHelper.SIGNAL_EVENT, new Emitter.Listener() {
      // Listen to monitoring event.
      @Override
      public void call(Object... args) {
        JSONObject obj = (JSONObject) args[0];
        System.out.println(obj);
      }

    });

    socket.connect();
  }
}
