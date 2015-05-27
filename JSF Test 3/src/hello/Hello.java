package hello;

import javax.annotation.ManagedBean;

@ManagedBean
public class Hello {

    final String world = "Hello barn 200";

    public String getworld() {
        return world;
    }
}
