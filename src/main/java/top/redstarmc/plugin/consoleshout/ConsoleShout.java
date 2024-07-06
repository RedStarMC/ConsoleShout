package top.redstarmc.plugin.consoleshout;

import com.google.inject.Inject;
import com.velocitypowered.api.command.CommandManager;
import com.velocitypowered.api.command.CommandMeta;
import com.velocitypowered.api.command.SimpleCommand;
import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.proxy.ProxyInitializeEvent;
import com.velocitypowered.api.plugin.Plugin;
import com.velocitypowered.api.proxy.ProxyServer;
import org.slf4j.Logger;

@Plugin(
        id = "console_shout",
        name = "ConsoleShout",
        version = "1.0.0",
        authors = {"pingguomc"},
        url = "https://github.com/RedStarMC/ConsoleShout"
)

public class ConsoleShout {
    private static ConsoleShout consoleshout = null;
    private final Logger logger;
    private final ProxyServer server;
    @Inject
    public ConsoleShout(Logger logger, ProxyServer server) {
        this.logger = logger;
        this.server = server;
    }

    @Subscribe
    public void onProxyInitialization(ProxyInitializeEvent event) {
        consoleshout = this;
        logger.info("控制台全服喊话插件");
        logger.info("作者：pingguomc    github：https://github.com/RedStarMC/ConsoleShout");
        logger.info("正在加载中…………………………");
        logger.info("正在注册指令");
        registerCommand();
        logger.info("加 载 成 功 ！");
    }

    public void registerCommand(){
        CommandManager commandManager = server.getCommandManager();
        CommandMeta shout_Meta = commandManager.metaBuilder("ConsoleShout").aliases("cshout").plugin(this).build();
        SimpleCommand ConsoleShout_command = new Command();
        commandManager.register(shout_Meta,ConsoleShout_command);
    }

    public Logger getLogger() {
        return logger;
    }

    public ProxyServer getServer() {
        return server;
    }

    public static ConsoleShout getConsoleshout() {
        return consoleshout;
    }
}
