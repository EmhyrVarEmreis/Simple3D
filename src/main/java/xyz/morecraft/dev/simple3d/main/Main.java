package xyz.morecraft.dev.simple3d.main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public final class Main {

    private final RenderThread renderThread;
    private final Window window;

    @Autowired
    public Main(RenderThread renderThread, Window window) {
        this.renderThread = renderThread;
        this.window = window;
    }

    public void run() {
        window.setVisible(true);
        renderThread.start();
    }

}
