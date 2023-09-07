package dk.manaxi.unikpay.user;

import dk.manaxi.unikpay.Main;
import dk.manaxi.unikpay.utils.FormatingUtils;
import net.labymod.api.client.gui.icon.Icon;

import java.util.UUID;

public class Account {

  private UUID uuid;
  private String username;
  private float balance;



  public Account(UUID uuid, String username, float balance) {
    this.uuid = uuid;
    this.username = username;
    this.balance = balance;
  }

  public UUID getUuid() {
    return uuid;
  }

  public String getUsername() {
    return username;
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

  public Icon getIconWidget() {
    return Icon.head(username.length() == 0 ? "MHF_Question" : username);
  }


  public void setBalance(float balance) {
    this.balance = balance;
  }
}
