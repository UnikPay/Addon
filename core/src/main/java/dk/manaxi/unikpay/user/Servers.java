package dk.manaxi.unikpay.user;

public class Servers {

  private String name;
  private float serverBalance;

  public Servers(String name, float serverBalance) {
    this.name = name;
    this.serverBalance = serverBalance;
  }

  public String getName() {
    return name;
  }

  public float getServerBalance() {
    return serverBalance;
  }
}
