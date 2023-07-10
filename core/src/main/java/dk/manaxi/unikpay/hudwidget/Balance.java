package dk.manaxi.unikpay.hudwidget;

import dk.manaxi.unikpay.config.Configuration;

import net.labymod.api.client.gui.hud.hudwidget.text.TextHudWidget;
import net.labymod.api.client.gui.hud.hudwidget.text.TextHudWidgetConfig;
import net.labymod.api.client.gui.hud.hudwidget.text.TextLine;
import net.labymod.api.client.gui.icon.Icon;


public class Balance extends TextHudWidget<TextHudWidgetConfig> {

  private TextLine balance;
  private final Icon icon;

  public Balance(String id, Icon icon) {
    super(id);
    this.icon = icon;
  }

  @Override
  public void load(TextHudWidgetConfig config) {
    super.load(config);

    this.balance = super.createLine("UnikPay Balance", 0.00);
    this.setIcon(this.icon);
  }


}
