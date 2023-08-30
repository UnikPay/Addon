package dk.manaxi.unikpay.config;

import dk.manaxi.unikpay.menus.Information;
import dk.manaxi.unikpay.utils.FormatingUtils;
import dk.manaxi.unikpay.utils.FormatingUtils.FORMATTING_MODE;
import net.labymod.api.addon.AddonConfig;
import net.labymod.api.client.gui.screen.activity.Activity;
import net.labymod.api.client.gui.screen.key.Key;
import net.labymod.api.client.gui.screen.widget.widgets.activity.settings.ActivitySettingWidget.ActivitySetting;
import net.labymod.api.client.gui.screen.widget.widgets.activity.settings.AddonActivityWidget.AddonActivitySetting;
import net.labymod.api.client.gui.screen.widget.widgets.input.KeybindWidget.KeyBindSetting;
import net.labymod.api.client.gui.screen.widget.widgets.input.SwitchWidget.SwitchSetting;
import net.labymod.api.client.gui.screen.widget.widgets.input.TextFieldWidget.TextFieldSetting;
import net.labymod.api.client.gui.screen.widget.widgets.input.dropdown.DropdownWidget.DropdownSetting;
import net.labymod.api.configuration.loader.annotation.ConfigName;
import net.labymod.api.configuration.loader.annotation.Exclude;
import net.labymod.api.configuration.loader.annotation.SpriteSlot;
import net.labymod.api.configuration.loader.annotation.SpriteTexture;
import net.labymod.api.configuration.loader.property.ConfigProperty;
import net.labymod.api.configuration.settings.annotation.SettingSection;
import net.labymod.api.util.MethodOrder;

@SuppressWarnings("FieldMayBeFinal")
@SpriteTexture("settings")
@ConfigName("settings")
public class Configuration extends AddonConfig {

  @SettingSection("general")
  @SpriteSlot
  @SwitchSetting
  private final ConfigProperty<Boolean> enabled = new ConfigProperty<>(true);

  @TextFieldSetting
  private ConfigProperty<String> text = new ConfigProperty<>("Key");

  @MethodOrder(after = "enabled")
  @ActivitySetting
  public Activity openInformation() {
    return new Information();
  }

  @SpriteSlot(y = 3)
  @DropdownSetting
  private ConfigProperty<FormatingUtils.FORMATTING_MODE> type = new ConfigProperty<>(FORMATTING_MODE.INGEN);


  @Override
  public ConfigProperty<Boolean> enabled() {
    return this.enabled;
  }

  public ConfigProperty<String> getToken() {
    return this.text;
  }

  public ConfigProperty<FORMATTING_MODE> getType() {
    return this.type;
  }

}
