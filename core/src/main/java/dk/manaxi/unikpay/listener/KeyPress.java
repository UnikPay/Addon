package dk.manaxi.unikpay.listener;

import dk.manaxi.unikpay.Main;
import dk.manaxi.unikpay.menus.Information;
import net.labymod.api.client.Minecraft;
import net.labymod.api.event.Subscribe;
import net.labymod.api.event.client.input.KeyEvent;

import static net.labymod.api.Laby.labyAPI;

public class KeyPress {
  private final Main main;

  public KeyPress(Main main) {
    this.main = main;
  }

  @Subscribe
  public void onKeyInput(KeyEvent event) {


  }
}
