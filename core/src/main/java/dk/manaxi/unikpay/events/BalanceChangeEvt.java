package dk.manaxi.unikpay.events;

import dk.manaxi.unikpay.Main;
import dk.manaxi.unikpay.utils.FormatingUtils;
import net.labymod.api.event.Event;

public class BalanceChangeEvt implements Event {
  private float balance;

  public BalanceChangeEvt(float balance) {
    this.balance = balance;
  }

  public float getBalance() {
    return balance;
  }

  public String getBalanceFormatted() {
    if (Main.getInstance().configuration.getType().get().name().equalsIgnoreCase("INGEN")) {
      return String.valueOf(getBalance());

    } else if (Main.getInstance().configuration.getType().get().name().equalsIgnoreCase("Punktum")) {
      return FormatingUtils.formatNumber(getBalance(), FormatingUtils.FORMATTING_MODE.PUNKTUM);
    } else if (Main.getInstance().configuration.getType().get().name().equalsIgnoreCase("ENDELSE")) {
      return FormatingUtils.formatNumber(getBalance(), FormatingUtils.FORMATTING_MODE.ENDELSE);
    }
    return null;
  }

}
