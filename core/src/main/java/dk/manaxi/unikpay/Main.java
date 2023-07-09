package dk.manaxi.unikpay;

import dk.manaxi.unikpay.config.Configuration;
import net.labymod.api.addon.LabyAddon;
import net.labymod.api.models.addon.annotation.AddonMain;

@AddonMain
public class Main extends LabyAddon<Configuration> {

  @Override
  protected void enable() {
    this.registerSettingCategory();


    this.logger().info("Enabled the Addon");
  }

  @Override
  protected Class<Configuration> configurationClass() {
    return Configuration.class;
  }
}
