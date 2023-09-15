package dk.manaxi.unikpay.utils;

import dk.manaxi.unikpay.Main;
import dk.manaxi.unikpay.Secret;
import net.labymod.api.client.gui.screen.NamedScreen;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.Response;
import java.io.IOException;

public class RequestUtil {

  public static void request(String id, String choice) throws IOException {

    OkHttpClient client = new OkHttpClient();
    String url = "https://unikpay.manaxi.dk/v1/request/" + id + "/" + choice;

    okhttp3.Request request = new okhttp3.Request.Builder().url(url).post(RequestBody.create(MediaType.parse("application/json"), ""))
        .header("Authorization", Secret.KEY)
        .build();
    Main.getInstance().labyAPI().minecraft().minecraftWindow().displayScreen(NamedScreen.MAIN_MENU.create());
    Response response = client.newCall(request).execute();
    if (response.code() != 200) {
      Main.getInstance().displayMessage("§8§l[ §a§lUNIKPAY §8§l] §fDer skete en fejl, prøv igen senere (code " + response.code() + ")");
    }
  }

}
