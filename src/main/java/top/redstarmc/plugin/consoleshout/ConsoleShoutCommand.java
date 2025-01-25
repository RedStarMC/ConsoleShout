package top.redstarmc.plugin.consoleshout;

import com.velocitypowered.api.command.CommandSource;
import com.velocitypowered.api.command.SimpleCommand;
import com.velocitypowered.api.proxy.ProxyServer;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.event.ClickEvent;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import static net.kyori.adventure.text.Component.text;

public class ConsoleShoutCommand implements SimpleCommand {

    @Override
    public void execute(Invocation invocation) {
        CommandSource source = invocation.source();
        Component message =text()
                .append(text("=======欢迎使用", NamedTextColor.AQUA),
                        text("ConsoleShout", NamedTextColor.RED),
                        text("插件=======\n",NamedTextColor.AQUA),
                        text("本插件添加了三个指令:\n", NamedTextColor.RED),
                        text("1.",NamedTextColor.YELLOW),
                        text("/ConsoleShout 或 /cshout",NamedTextColor.GOLD),
                        text("--- 打开本页面\n",NamedTextColor.YELLOW),
                        text("2.",NamedTextColor.YELLOW),
                        text("/alert [文本内容]",NamedTextColor.GOLD),
                        text("       --- 直接喊话",NamedTextColor.YELLOW),
                        text("(前缀配置文件修改，可加格式化代码&)\n",NamedTextColor.AQUA),
                        text("3.",NamedTextColor.YELLOW),
                        text("/alertraw [JSON格式信息]",NamedTextColor.GOLD),
                        text("--- 使用JOSN格式信息喊话",NamedTextColor.YELLOW),
                        text("(无前缀，使用JOSN格式)\n",NamedTextColor.AQUA),
                        text("一个权限: ConsoleShout.send (Velocity上的权限，不是子服的权限)\n",NamedTextColor.RED)
                        )
                .append(
                        Component.keybind().keybind("作者: pingguomc  ")
                                .color(NamedTextColor.WHITE)
                                .decoration(TextDecoration.BOLD,true)
                                .build()
                )
                .append(
                        Component.keybind().keybind("https://github.com/RedStarMC/ConsoleShout")
                                .color(NamedTextColor.WHITE)
                                .decoration(TextDecoration.UNDERLINED,true)
                                .clickEvent(ClickEvent.openUrl("https://github.com/RedStarMC/ConsoleShout"))
                )
                .build();
        source.sendMessage(message);
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
