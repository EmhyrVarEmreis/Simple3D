package xyz.morecraft.dev.simple3d.main;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import xyz.morecraft.dev.simple3d.configuration.EngineConfiguration;
import xyz.morecraft.dev.simple3d.engine.Listener;
import xyz.morecraft.dev.simple3d.engine.Screen;

import java.util.concurrent.TimeUnit;

@Component
public final class RenderThread implements Runnable {

    public static volatile boolean NEEDS_RECALC = Boolean.FALSE;

    private static final Logger log = LoggerFactory.getLogger(RenderThread.class);

    private final EngineConfiguration configuration;
    private final Listener listener;
    private final Screen screen;
    private final Window window;

    private final Thread thread;

    private boolean isRunning;

    @Autowired
    public RenderThread(EngineConfiguration configuration, Listener listener, Screen screen, Window window) {
        this.configuration = configuration;
        this.listener = listener;
        this.screen = screen;
        this.window = window;

        this.thread = new Thread(this);
    }

    synchronized void start() {
        log.info("RenderThread started");
        isRunning = true;
        thread.start();
    }

    @Override
    public void run() {
        final long nsFps = TimeUnit.SECONDS.toNanos(1) / configuration.getMaxFps();
        final long nsKey = TimeUnit.SECONDS.toNanos(1) / configuration.getMaxControlRefresh();

        log.info("FPS locked at maximum 1 frame per {} nanoseconds ({}FPS)", nsFps, configuration.getMaxFps());
        log.info("Key locked at maximum 1 frame per {} nanoseconds ({}FPS)", nsKey, configuration.getMaxControlRefresh());

        long lastTime = System.nanoTime();
        long lastTimeKey = System.nanoTime();
        long now;

        window.requestFocus();

        while (isRunning) {
            now = System.nanoTime();
            if (now - lastTime >= nsFps) {
                lastTime = now;
                if (NEEDS_RECALC) {
                    screen.recalculate();
                    NEEDS_RECALC = false;
                }
                screen.update();
            }
            if (now - lastTimeKey >= nsKey) {
                lastTimeKey = now;
                listener.update();
            }
            window.render();
        }
    }

}
