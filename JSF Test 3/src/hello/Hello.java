package hello;

import javax.annotation.ManagedBean;

@ManagedBean
public class Hello {

    final String world = "Hello barn";

    public String getworld() {
        return world;
    }
}
