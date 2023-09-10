package dk.manaxi.unikpay.events;

import dk.manaxi.unikpay.user.Account;
import net.labymod.api.client.gui.icon.Icon;
import net.labymod.api.event.Event;

public class AccountEvt implements Event {
  Account account;

  public AccountEvt(Account account) {
    System.out.println("AccountEvt -----------------------------------------------------------");
    this.account = account;
  }
  public Account getAccount() {
    return account;
  }


}
