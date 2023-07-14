package dk.manaxi.unikpay.user;

public class Servers {

  private String name;
  private float server_balance;

  public Servers(String name, float server_balance) {
    this.name = name;
    this.server_balance = server_balance;
  }

  public String getName() {
    return name;
  }

  public float getServer_balance() {
    return server_balance;
  }
}
