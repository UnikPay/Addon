package dk.manaxi.unikpay.listener;

import dk.manaxi.unikpay.Main;
import net.labymod.api.event.Subscribe;
import net.labymod.api.event.client.input.KeyEvent;

public class KeyPress {
  private final Main main;

  public KeyPress(Main main) {
    this.main = main;
  }

  @Subscribe
  public void onKeyInput(KeyEvent event) {


  }
}
