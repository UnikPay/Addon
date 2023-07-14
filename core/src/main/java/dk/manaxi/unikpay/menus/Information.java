package dk.manaxi.unikpay.menus;

import dk.manaxi.unikpay.Main;
import dk.manaxi.unikpay.hudwidget.AccountWidget;
import dk.manaxi.unikpay.user.Account;
import net.labymod.api.client.gui.icon.Icon;
import net.labymod.api.client.gui.mouse.MutableMouse;
import net.labymod.api.client.gui.screen.Parent;
import net.labymod.api.client.gui.screen.activity.Activity;
import net.labymod.api.client.gui.screen.activity.AutoActivity;
import net.labymod.api.client.gui.screen.activity.types.SimpleActivity;
import net.labymod.api.client.gui.screen.key.MouseButton;
import net.labymod.api.client.gui.screen.widget.attributes.bounds.Bounds;
import net.labymod.api.client.gui.screen.widget.widgets.layout.FlexibleContentWidget;
import net.labymod.api.client.gui.screen.widget.widgets.layout.ScrollWidget;
import net.labymod.api.client.gui.screen.widget.widgets.layout.list.VerticalListWidget;
import net.labymod.api.client.gui.screen.widget.widgets.renderer.IconWidget;
import net.labymod.api.client.render.font.text.TextRenderer;
import net.labymod.api.client.render.matrix.Stack;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

@AutoActivity
public class Information extends Activity {

  private final Main addon;
  private final Map<String, AccountWidget> accountWidgetMap;
  private final VerticalListWidget<AccountWidget> accountWidgetList;


  public Information() {
    this.addon = Main.getInstance();
    this.accountWidgetMap = new HashMap<>();
    this.accountWidgetList = new VerticalListWidget<>();


    this.accountWidgetList.addId("name-tag-list");
  }

  @Override
  public void initialize(Parent parent) {
    super.initialize(parent);
    FlexibleContentWidget container = new FlexibleContentWidget();
    container.addId("information");

    for (AccountWidget nameTagWidget : this.accountWidgetMap.values()) {
      this.accountWidgetList.addChild(nameTagWidget);
    }



  }

  @Override
  public boolean mouseClicked(MutableMouse mouse, MouseButton mouseButton) {

    return false;
  }



}
