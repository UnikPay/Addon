package dk.manaxi.unikpay;

import dk.manaxi.unikpay.config.Configuration;
import dk.manaxi.unikpay.hudwidget.Balance;
import dk.manaxi.unikpay.listener.KeyPress;
import io.socket.client.IO;
import io.socket.client.Socket;
import net.labymod.api.LabyAPI;
import net.labymod.api.addon.LabyAddon;
import net.labymod.api.client.Minecraft;
import net.labymod.api.client.gui.icon.Icon;
import net.labymod.api.client.gui.screen.key.Key;
import net.labymod.api.client.resources.ResourceLocation;
import net.labymod.api.models.addon.annotation.AddonMain;
import java.net.URISyntaxException;

import static java.util.Collections.singletonList;
import static java.util.Collections.singletonMap;

@AddonMain
public class Main extends LabyAddon<Configuration> {
  public Configuration configuration = configuration();
  private static Main instance;
  private static Minecraft minecraft;
  private static Socket socket;
  private final Icon hudIcon = Icon.texture(ResourceLocation.create(
      "unikpay",
      "themes/vanilla/textures/settings/hud/emerald.png"
  )).resolution(64, 64);

  @Override
  protected void enable() {
    this.registerSettingCategory();
    labyAPI().hudWidgetRegistry().register(new Balance("balance", hudIcon));
    this.registerListener(new KeyPress(this));
    this.logger().info("Enabled the Addon");
    connectSocket();

  }

  public static void connectSocket() {
    System.out.println("TOKEN - " + Main.getInstance().configuration.getToken().get());
    System.out.println("TOKEN - " + Main.getInstance().configuration.getToken());
    try {
      IO.Options options = IO.Options.builder()
          .setAuth(singletonMap("token", Main.getInstance().configuration.getToken().get()))
          .build();
      socket = IO.socket("https://unikpay.manaxi.dk/", options);
      socket.on(Socket.EVENT_CONNECT, args -> System.out.println("Socket.io connected."));
      socket.on(Socket.EVENT_DISCONNECT, args -> System.out.println("Socket.io disconnected."));
    } catch (URISyntaxException ignored){}

    socket.connect();
  }


  @Override
  protected Class<Configuration> configurationClass() {
    return Configuration.class;
  }

  public Main() {
    instance = this;
  }
  public static Main getInstance() {
    return instance;
  }

  public static Minecraft getMinecraft() {
    return minecraft;
  }




}
