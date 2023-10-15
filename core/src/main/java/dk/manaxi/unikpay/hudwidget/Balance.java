package dk.manaxi.unikpay.hudwidget;

import dk.manaxi.unikpay.Main;
import dk.manaxi.unikpay.events.AccountEvt;
import dk.manaxi.unikpay.events.BalanceChangeEvt;
import net.labymod.api.client.gui.hud.hudwidget.text.TextHudWidget;
import net.labymod.api.client.gui.hud.hudwidget.text.TextHudWidgetConfig;
import net.labymod.api.client.gui.hud.hudwidget.text.TextLine;
import net.labymod.api.client.gui.icon.Icon;
import net.labymod.api.event.Subscribe;


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
    this.balance = super.createLine("UnikPay Balance", "Loading...");

    this.setIcon(this.icon);
  }
  @Subscribe
  public void balance(AccountEvt evt) {
    System.out.println("Balance: " + evt.getAccount().getBalanceFormatted());
    System.out.println("Balance: " + evt.getAccount().getBalance());
    System.out.println("Balance: " + evt.getAccount().getUsername());
    this.balance.updateAndFlush(evt.getAccount().getBalanceFormatted() == null ? "0.00" : evt.getAccount().getBalanceFormatted());
  }

  @Subscribe
  public void balanceEnvet(BalanceChangeEvt evt) {
    System.out.println("Balance: " + evt.getBalance());
    this.balance.updateAndFlush(evt.getBalanceFormatted() == null ? "0.00" : evt.getBalanceFormatted());
  }




}
