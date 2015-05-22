package hello;

import javax.annotation.ManagedBean;

@ManagedBean
public class Hello {

    final String world = "Jedi warrior!";

    public String getworld() {
        return world;
    }
}
