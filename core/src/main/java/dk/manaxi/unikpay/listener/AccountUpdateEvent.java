package dk.manaxi.unikpay.listener;

import dk.manaxi.unikpay.Main;
import net.labymod.api.event.Subscribe;
import net.labymod.api.event.client.lifecycle.GameTickEvent;

public class AccountUpdateEvent {

  private final Main addon;

  public AccountUpdateEvent(Main addon) {
    this.addon = addon;
  }

  @Subscribe
  public void onTick(GameTickEvent event) {

  }
}
