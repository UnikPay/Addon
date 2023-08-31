package dk.manaxi.unikpay.hudwidget;

import dk.manaxi.unikpay.user.Account;
import net.labymod.api.client.component.Component;
import net.labymod.api.client.gui.icon.Icon;
import net.labymod.api.client.gui.screen.Parent;
import net.labymod.api.client.gui.screen.widget.SimpleWidget;
import net.labymod.api.client.gui.screen.widget.widgets.ComponentWidget;
import net.labymod.api.client.gui.screen.widget.widgets.renderer.IconWidget;

public class AccountWidget extends SimpleWidget {
  private Account account;
  private String userName;

  public AccountWidget(String userName, Account account) {
    this.userName = userName;
    this.account = account;
  }

  @Override
  public void initialize(Parent parent) {
    super.initialize(parent);

    IconWidget iconWidget = new IconWidget(this.getIconWidget(this.account.getUsername()));
    iconWidget.addId("avatar");
    this.addChild(iconWidget);

    ComponentWidget nameWidget = ComponentWidget.component(Component.text(this.userName));
    nameWidget.addId("name");
    this.addChild(nameWidget);


  }


  public Icon getIconWidget(String userName) {
    return Icon.head(userName.isEmpty() ? "MHF_Question" : userName);
  }

  public String getUserName() {
    return this.userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }



}
