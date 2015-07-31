package com.zoyi;

import com.github.nkzawa.emitter.Emitter;
import com.github.nkzawa.socketio.client.IO;
import com.github.nkzawa.socketio.client.Socket;
import org.json.JSONObject;

import java.net.URISyntaxException;

public class Main {

  public static void main(String[] args) throws URISyntaxException {
    // write your code here
    final Socket socket;

    IO.Options opts = new IO.Options();
    opts.forceNew = true;
    opts.reconnection = false;
    opts.query = "user_email=" + "" + "&user_token=" + "" + "&shop_id=" + "";
    opts.path = "/sensor.io";

    socket = IO.socket("https://station5.walkinsights.com/api/shops/signals", opts);

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
    }).on("signal", new Emitter.Listener() {

      @Override
      public void call(Object... args) {
        JSONObject obj = (JSONObject)args[0];
        System.out.println(obj);
      }

    });

    socket.connect();
  }
}
