package dk.manaxi.unikpay.menus;

import dk.manaxi.unikpay.api.classes.Pakke;
import net.labymod.api.client.gui.mouse.MutableMouse;
import net.labymod.api.client.gui.screen.Parent;
import net.labymod.api.client.gui.screen.activity.Activity;
import net.labymod.api.client.gui.screen.activity.AutoActivity;
import net.labymod.api.client.gui.screen.activity.Link;
import net.labymod.api.client.gui.screen.activity.types.IngameOverlayActivity;
import net.labymod.api.client.gui.screen.activity.types.SimpleActivity;
import net.labymod.api.client.gui.screen.widget.attributes.bounds.Bounds;
import net.labymod.api.client.gui.screen.widget.widgets.ComponentWidget;
import net.labymod.api.client.render.font.text.TextRenderer;
import net.labymod.api.client.render.matrix.Stack;
import net.labymod.api.util.Color;

@AutoActivity
@Link("example.lss")
public class Request extends IngameOverlayActivity {
  private Pakke pakke;
  private boolean visible;
  public Request(Pakke pakke) {
    this.pakke = pakke;
  }

  @Override
  public void render(Stack stack, MutableMouse mouse, float partialTicks) {
    super.render(stack, mouse, partialTicks);
  }

  @Override
  public boolean isVisible() {
    return visible;
  }

  public void setVisible(boolean visible) {
    this.visible = visible;
  }



  @Override
  public void initialize(Parent parent) {
    super.initialize(parent);

    ComponentWidget componentWidget = ComponentWidget.text(
        "I am an example text rendered with a ComponentWidget set via LSS"
    );
    componentWidget.addId("test-widget");
    this.document().addChild(componentWidget);
  }
}
