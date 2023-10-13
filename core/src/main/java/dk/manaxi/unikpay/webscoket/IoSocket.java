package dk.manaxi.unikpay.webscoket;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import dk.manaxi.unikpay.Main;
import dk.manaxi.unikpay.api.classes.Pakke;
import dk.manaxi.unikpay.events.AccountEvt;
import dk.manaxi.unikpay.menus.RequestMenu;
import dk.manaxi.unikpay.user.Account;
import io.socket.client.IO;
import io.socket.client.Socket;

import java.lang.reflect.Type;
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
        String ok = Arrays.toString(args);
        Gson gson = new Gson();
        JsonArray jsonArray = gson.fromJson(ok, JsonArray.class);

        JsonObject obj = jsonArray.get(0).getAsJsonObject();
        UUID uuid = UUID.fromString(obj.getAsJsonObject("mcAccount").get("uuid").getAsString());
        float balance = obj.getAsJsonObject("mcAccount").getAsJsonObject("balance").get("$numberDecimal").getAsFloat();

        String username = obj.getAsJsonObject("mcAccount").get("username").getAsString();

        account = new Account(
            uuid,
            username,
            balance
        );
        accounts.add(account);
        labyAPI().eventBus().fire(new AccountEvt(IoSocket.getAccount()));
      });

      socket.on("newRequest", args -> {
        String ok = Arrays.toString(args);
        Gson gson = new Gson();
        JsonArray jsonArray = gson.fromJson(ok, JsonArray.class);

        JsonObject obj = jsonArray.get(0).getAsJsonObject();
        String server = obj.get("server").getAsString();
        Type listType = new TypeToken<List<Pakke>>() {}.getType();
        List<Pakke> pakker = gson.fromJson(obj.getAsJsonArray("packages"), listType);
        Pakke[] pakkerArray = pakker.toArray(new Pakke[0]);
        String id = obj.get("_id").getAsString();

        RequestMenu request = new RequestMenu(server, id, pakkerArray);
        Main.getInstance().labyAPI().minecraft().executeNextTick(() -> Main.getInstance().labyAPI().minecraft().minecraftWindow().displayScreen(request));
      });

      socket.on("balance", args -> {
        String ok = Arrays.toString(args);
        Gson gson = new Gson();
        JsonArray jsonArray = gson.fromJson(ok, JsonArray.class);
        JsonObject obj = jsonArray.get(0).getAsJsonObject();
        System.out.println(obj);
        float balance = obj.getAsJsonObject("balance").get("$numberDecimal").getAsFloat();
        account.setBalance(balance);
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
