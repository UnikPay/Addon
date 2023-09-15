package dk.manaxi.unikpay;

import dk.manaxi.unikpay.config.Configuration;
import dk.manaxi.unikpay.events.AccountEvt;
import dk.manaxi.unikpay.hudwidget.Balance;
import dk.manaxi.unikpay.listener.ChatListener;
import dk.manaxi.unikpay.listener.KeyPress;
import dk.manaxi.unikpay.webscoket.IoSocket;
import net.labymod.api.Laby;
import net.labymod.api.addon.LabyAddon;
import net.labymod.api.client.Minecraft;
import net.labymod.api.client.gui.icon.Icon;
import net.labymod.api.client.resources.ResourceLocation;
import net.labymod.api.models.addon.annotation.AddonMain;

@AddonMain
public class Main extends LabyAddon<Configuration> {
  public Configuration configuration = configuration();
  private static Main instance;
  private static Minecraft minecraft;
  private final Icon hudIcon = Icon.texture(ResourceLocation.create(
      "unikpay-addon",
      "themes/vanilla/textures/settings/hud/emerald.png"
  )).resolution(64, 64);

  @Override
  protected void enable() {
    this.registerSettingCategory();
    IoSocket.connectSocket();

    labyAPI().hudWidgetRegistry().register(new Balance("balance", hudIcon));
    this.logger().info("Enabled the Addon");
    labyAPI().eventBus().registerListener(new ChatListener(this));
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

  public void disconnect() {
    IoSocket.getSocket().disconnect();
  }

  public static Minecraft getMinecraft() {
    return minecraft;
  }



}
