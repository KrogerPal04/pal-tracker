package io.pivotal.pal.tracker;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class EnvController {


    @Value("${PORT:NOT SET}")
    private String port;
    @Value("${MEMORY_LIMIT:NOT SET}")
    private String memoryLimit;
    @Value("${CF_INSTANCE_INDEX:NOT SET}")
    private String cfInstanceIndex;
    @Value("${CF_INSTANCE_ADDR:NOT SET}")
    private String cfInstanceAddr;

    public EnvController() {

    }

    public EnvController(String port, String memoryLimit, String cfInstanceIndex, String cfInstanceAddr) {
        this.port = port;
        this.memoryLimit = memoryLimit;
        this.cfInstanceIndex = cfInstanceIndex;
        this.cfInstanceAddr = cfInstanceAddr;

    }

    @GetMapping("/env")
    public Map<String, String> getEnv() {
        Map<String, String> env = new HashMap<>();

        env.put("PORT", this.port);
        env.put("MEMORY_LIMIT", this.memoryLimit);
        env.put("CF_INSTANCE_INDEX", this.cfInstanceIndex);
        env.put("CF_INSTANCE_ADDR", this.cfInstanceAddr);

        return env;
    }
}
