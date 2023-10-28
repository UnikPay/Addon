package dk.manaxi.unikpay.config;

import dk.manaxi.unikpay.menus.Information;
import dk.manaxi.unikpay.utils.FormatingUtils;
import dk.manaxi.unikpay.utils.FormatingUtils.FORMATTING_MODE;
import dk.manaxi.unikpay.utils.NotificationUtil;
import dk.manaxi.unikpay.webscoket.IoSocket;
import net.labymod.api.addon.AddonConfig;
import net.labymod.api.client.gui.screen.activity.Activity;
import net.labymod.api.client.gui.screen.widget.widgets.activity.settings.ActivitySettingWidget.ActivitySetting;
import net.labymod.api.client.gui.screen.widget.widgets.input.ButtonWidget.ButtonSetting;
import net.labymod.api.client.gui.screen.widget.widgets.input.SwitchWidget.SwitchSetting;
import net.labymod.api.client.gui.screen.widget.widgets.input.TextFieldWidget.TextFieldSetting;
import net.labymod.api.client.gui.screen.widget.widgets.input.dropdown.DropdownWidget.DropdownSetting;
import net.labymod.api.configuration.loader.annotation.ConfigName;
import net.labymod.api.configuration.loader.annotation.SpriteSlot;
import net.labymod.api.configuration.loader.property.ConfigProperty;
import net.labymod.api.configuration.settings.Setting;
import net.labymod.api.util.MethodOrder;

@SuppressWarnings("FieldMayBeFinal")
@ConfigName("settings")
public class Configuration extends AddonConfig {
  @SwitchSetting
  private final ConfigProperty<Boolean> enabled = new ConfigProperty<>(true);

  @MethodOrder(after = "enabled")
  @ActivitySetting
  public Activity openInformation() {
    return new Information();
  }

  @TextFieldSetting
  public ConfigProperty<String> token = new ConfigProperty<>("Key");
  @MethodOrder(after = "token")
  @SpriteSlot(x = 2, y = 6)
  @ButtonSetting
  public void reconnect(Setting setting) {
    IoSocket.getSocket().disconnect();
    IoSocket.connectSocket();
    NotificationUtil.createNotification("Reconnected to socket");
  }

  @SpriteSlot(y = 3)
  @DropdownSetting
  private ConfigProperty<FormatingUtils.FORMATTING_MODE> type = new ConfigProperty<>(FORMATTING_MODE.INGEN);


  @Override
  public ConfigProperty<Boolean> enabled() {
    return this.enabled;
  }

  public ConfigProperty<FORMATTING_MODE> getType() {
    return this.type;
  }

}
