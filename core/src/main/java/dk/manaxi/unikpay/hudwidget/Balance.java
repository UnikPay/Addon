package dk.manaxi.unikpay.hudwidget;

import dk.manaxi.unikpay.Main;
import dk.manaxi.unikpay.config.Configuration;

import dk.manaxi.unikpay.events.AccountEvt;
import dk.manaxi.unikpay.user.Account;
import dk.manaxi.unikpay.webscoket.IoSocket;
import net.labymod.api.client.gui.hud.hudwidget.text.TextHudWidget;
import net.labymod.api.client.gui.hud.hudwidget.text.TextHudWidgetConfig;
import net.labymod.api.client.gui.hud.hudwidget.text.TextLine;
import net.labymod.api.client.gui.icon.Icon;
import net.labymod.api.event.Subscribe;


public class Balance extends TextHudWidget<TextHudWidgetConfig> {

  private TextLine balance;
  private final Icon icon;
  private Main main;

  public Balance(String id, Icon icon) {
    super(id);
    this.icon = icon;
    this.main = main;
  }



  @Override
  public void load(TextHudWidgetConfig config) {
    super.load(config);
    if(!IoSocket.getSocket().connected()) {
      this.balance = super.createLine("UnikPay Balance", "Loading...");
    }
    this.setIcon(this.icon);
  }
  @Subscribe
  public void balance(AccountEvt evt) {
    this.balance.updateAndFlush(evt.getAccount().getBalanceFormatted() == null ? "0.00" : evt.getAccount().getBalanceFormatted());
  }



}
