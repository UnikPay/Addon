package dk.manaxi.unikpay.user;

import java.util.UUID;

public class Account {

  private UUID uuid;
  private String username;
  private float balance;

  private Servers[] servers;

  public Account(UUID uuid, String username, float balance, Servers[] servers) {
    this.uuid = uuid;
    this.username = username;
    this.balance = balance;
    this.servers = servers;
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

  public Servers[] getServers() {
    return servers;
  }

  public void setBalance(float balance) {
    this.balance = balance;
  }
}
