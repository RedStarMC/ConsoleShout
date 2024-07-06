package top.redstarmc.plugin.consoleshout;

import com.velocitypowered.api.command.CommandSource;
import com.velocitypowered.api.command.SimpleCommand;
import com.velocitypowered.api.proxy.ProxyServer;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TranslatableComponent;
import net.kyori.adventure.text.format.NamedTextColor;
import org.checkerframework.checker.units.qual.N;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

import static net.kyori.adventure.text.Component.text;
import static net.kyori.adventure.text.format.NamedTextColor.*;

public class Command implements SimpleCommand {

    @Override
    public void execute(Invocation invocation) {
        CommandSource source = invocation.source();
        String[] args = invocation.arguments();
        if (args.length == 1){
            String Cmd = args[0];
            ProxyServer server = ConsoleShout.getConsoleshout().getServer();
            Component message =text()
                    .append(text("[",NamedTextColor.GRAY),
                            text("全 服 公 告", NamedTextColor.RED),
                            text("]",NamedTextColor.GRAY),
                            text(Cmd))
                    .build();
            server.sendMessage(message);
        }else {
            source.sendMessage(Component.text("语法错误！！！不能加空格!", NamedTextColor.RED));
        }
    }

    @Override
    public CompletableFuture<List<String>> suggestAsync(Invocation invocation) {
        return SimpleCommand.super.suggestAsync(invocation);
    }

    @Override
    public boolean hasPermission(Invocation invocation) {
        return invocation.source().hasPermission("ConsoleShout.send");
    }
}
