package top.redstarmc.plugin.consoleshout;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.tree.LiteralCommandNode;
import com.velocitypowered.api.command.BrigadierCommand;
import com.velocitypowered.api.command.CommandSource;
import com.velocitypowered.api.proxy.ProxyServer;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.gson.GsonComponentSerializer;

public class AlertRawCommand {
    public static BrigadierCommand createAlertRawCommand(final ProxyServer server){
        LiteralCommandNode<CommandSource> AlertRaw = BrigadierCommand.literalArgumentBuilder("root")
                .requires(source -> source.hasPermission("ConsoleShout.send"))
                .executes(context -> {
                    CommandSource source = context.getSource();
                    Component message = Help.getComponent();
                    source.sendMessage(message);
                    return Command.SINGLE_SUCCESS;
                })
                .then(BrigadierCommand.requiredArgumentBuilder("message",StringArgumentType.greedyString())
                        .executes(context ->{
                            String json = context.getArgument("message",String.class);
                            Component component = GsonComponentSerializer.gson().deserialize(json);
                            server.sendMessage(component);
                            return Command.SINGLE_SUCCESS;
                        })
                )
                .build();
        return new BrigadierCommand(AlertRaw);
    }
}
