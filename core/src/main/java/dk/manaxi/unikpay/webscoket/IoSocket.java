package dk.manaxi.unikpay.webscoket;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import dk.manaxi.unikpay.Main;
import dk.manaxi.unikpay.events.AccountEvt;
import dk.manaxi.unikpay.user.Account;
import io.socket.client.IO;
import io.socket.client.Socket;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static java.util.Collections.singletonMap;
import static net.labymod.api.Laby.labyAPI;

public class IoSocket {
  private static Socket socket;
  private static Account account;
  private static List<Account> accounts = new ArrayList<>();

  public static void connectSocket() {
    try {
      IO.Options options = IO.Options.builder().setAuth(singletonMap("token", Main.getInstance().configuration.getToken().get())).build();
      socket = IO.socket("https://unikpay.manaxi.dk/", options);
      socket.on(Socket.EVENT_CONNECT, args -> System.out.println("Socket.io connected."));
      socket.on(Socket.EVENT_DISCONNECT, args -> System.out.println("Socket.io disconnected."));
      socket.emit("information");
      socket.on("information", args -> {
        System.out.println(args[0]);
        String ok = Arrays.toString(args);
        Gson gson = new Gson();
        JsonArray jsonArray = gson.fromJson(ok, JsonArray.class);

        JsonObject obj = jsonArray.get(0).getAsJsonObject();
        UUID uuid = UUID.fromString(obj.getAsJsonObject("mcAccount").get("uuid").getAsString());
        float balance = obj.getAsJsonObject("mcAccount").getAsJsonObject("balance").get("$numberDecimal").getAsFloat();

        String username = obj.getAsJsonObject("mcAccount").get("username").getAsString();
        System.out.println("UUID " + uuid);
        System.out.println("Username " + username);
        System.out.println("Balance " + balance);

        account = new Account(
            uuid,
            username,
            balance
        );
        accounts.add(account);
        labyAPI().eventBus().fire(new AccountEvt(IoSocket.getAccount()));
      });
      socket.connect();

    } catch (URISyntaxException ignored){

    }


  }

  public static Socket getSocket() {
    return socket;
  }

  public static Account getAccount() {
    return account;
  }

  public static List<Account> getAccounts() {
    return accounts;
  }

}
