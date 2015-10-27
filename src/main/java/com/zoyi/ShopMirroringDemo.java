package com.zoyi;

import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;
import org.json.JSONObject;

import java.net.URISyntaxException;

public class ShopMirroringDemo {

  public static void main(String[] args) throws URISyntaxException {
    // write your code here
    final Socket socket;

    IO.Options opts = new IO.Options();
    opts.forceNew = true;
    opts.reconnection = true;
    opts.query = "user_email=" + "" +
        "&user_token=" + "" +
        "&shop_id=" + "";
    opts.path = ApplicationHelper.getSensorPath();

    socket = IO.socket(ApplicationHelper.getShopMirroingURL(), opts);

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
      public void call(Object... args) {
        JSONObject obj = (JSONObject) args[0];
        System.out.println(obj);
      }

    });

    socket.connect();
  }
}
