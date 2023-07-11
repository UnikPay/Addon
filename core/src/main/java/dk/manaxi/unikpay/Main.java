package dk.manaxi.unikpay;

import dk.manaxi.unikpay.config.Configuration;
import dk.manaxi.unikpay.hudwidget.Balance;
import dk.manaxi.unikpay.listener.KeyPress;
import net.labymod.api.LabyAPI;
import net.labymod.api.addon.LabyAddon;
import net.labymod.api.client.Minecraft;
import net.labymod.api.client.gui.icon.Icon;
import net.labymod.api.client.gui.screen.key.Key;
import net.labymod.api.client.resources.ResourceLocation;
import net.labymod.api.models.addon.annotation.AddonMain;

@AddonMain
public class Main extends LabyAddon<Configuration> {
  public Configuration configuration = (Configuration)configuration();
  private static Main instance;
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



}
