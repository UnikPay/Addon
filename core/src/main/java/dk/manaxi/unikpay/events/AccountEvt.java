package dk.manaxi.unikpay.events;

import dk.manaxi.unikpay.user.Account;
import net.labymod.api.event.Event;

public class AccountEvt implements Event {
  Account account;

  public AccountEvt(Account account) {
    this.account = account;
  }
  public Account getAccount() {
    return account;
  }

}
