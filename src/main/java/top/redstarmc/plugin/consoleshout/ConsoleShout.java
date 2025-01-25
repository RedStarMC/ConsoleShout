package top.redstarmc.plugin.consoleshout;

import com.google.inject.Inject;
import com.velocitypowered.api.command.CommandManager;
import com.velocitypowered.api.command.CommandMeta;
import com.velocitypowered.api.command.SimpleCommand;
import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.proxy.ProxyInitializeEvent;
import com.velocitypowered.api.plugin.Plugin;
import com.velocitypowered.api.proxy.ProxyServer;
import org.checkerframework.checker.units.qual.C;
import org.slf4j.Logger;

import java.io.FileNotFoundException;

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
    private ConfigManager configManager;
    @Inject
    public ConsoleShout(Logger logger, ProxyServer server) {
        this.logger = logger;
        this.server = server;
    }

    @Subscribe
    public void onProxyInitialization(ProxyInitializeEvent event) {
        consoleshout = this;
        logger.info("====&bVelocity&c(控制台)全服喊话插件&f====");
        logger.info("作者：pingguomc github：https://github.com/RedStarMC/ConsoleShout");
        logger.info("虽然叫控制台喊话插件，但是你在游戏内也能喊话！");
        logger.info("正在加载中…………………………");
        logger.info("生成配置文件");
        ConfigManager configManager = new ConfigManager();
        configManager = this.configManager;
        boolean bool = configManager.initConfig();
        if(!bool){
            logger.error("配置文件创建失败，插件停止加载");
            return;
        }
        logger.info("注册指令");
        registerCommand();
        logger.info("====&b加载成功&f====");
    }

    public void registerCommand(){
        CommandManager commandManager = server.getCommandManager();

        CommandMeta console_shout = commandManager.metaBuilder("ConsoleShout").aliases("cshout").plugin(this).build();
        SimpleCommand ConsoleShout_command = new ConsoleShoutCommand();

        CommandMeta alert_meta = commandManager.metaBuilder("alert").plugin(this).build();


        CommandMeta alertraw_meta = commandManager.metaBuilder("alertraw").plugin(this).build();


        commandManager.register(console_shout,ConsoleShout_command);
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
