package top.redstarmc.plugin.consoleshout;

import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.Command;
import com.mojang.brigadier.tree.LiteralCommandNode;
import com.velocitypowered.api.command.BrigadierCommand;
import com.velocitypowered.api.command.CommandSource;
import com.velocitypowered.api.proxy.ProxyServer;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;

public class AlertCommand {
    public static BrigadierCommand createBrigadierCommand(final ProxyServer proxy) {
        LiteralCommandNode<CommandSource> Alert = BrigadierCommand.literalArgumentBuilder("test")
                //在这里，您可以筛选可以执行命令的主题。
                //这是进行“hasPermission”检查的理想场所
                .requires(source -> source.hasPermission("ConsoleShout.send"))
                //在这里，您可以添加将在中使用的逻辑
                //不带任何参数执行“/test”命令
                .executes(context -> {
                    // 在这里，您将获得执行该命令的主体
                    CommandSource source = context.getSource();
                    Component message = Help.getComponent();
                    source.sendMessage(message);

                    // 返回 Command.SINGLE_SUCCESS 表示执行成功
                    // 返回 BrigadierCommand.FORWARD 将向服务器发送命令
                    return Command.SINGLE_SUCCESS;
                })
                // Using the "then" method, you can add sub-arguments to the command.
                // For example, this subcommand will be executed when using the command "/test <some argument>"
                // A RequiredArgumentBuilder is a type of argument in which you can enter some undefined data
                // of some kind. For example, this example uses a StringArgumentType.word() that requires
                // a single word to be entered, but you can also use different ArgumentTypes provided by Brigadier
                // that return data of type Boolean, Integer, Float, other String types, etc
                //使用“then”方法，您可以向命令添加子参数。
                //例如，当使用命令“/test <some argument>”时，将执行此子命令
                //RequiredArgumentBuilder是一种参数类型，您可以在其中输入一些未定义的数据
                //某种形式的。例如，此示例使用StringArgumentType.word（），它需要
                //只需输入一个单词，但您也可以使用Brigadier提供的不同ArgumentType
                //返回布尔、整数、浮点、其他字符串类型等类型的数据
                .then(BrigadierCommand.requiredArgumentBuilder("argument",StringArgumentType.greedyString())
                        // Here you can define the hints to be provided in case the ArgumentType does not provide them.
                        // In this example, the names of all connected players are provided
                        //在这里，您可以定义在ArgumentType不提供提示的情况下提供的提示。
                        //在这个例子中，提供了所有连接的玩家的名字
                        .suggests((ctx, builder) -> {
                            // Here we provide the names of the players along with a tooltip,
                            // which can be used as an explanation of a specific argument or as a simple decoration
                            //在这里，我们提供了玩家的名字以及工具提示，
                            //它既可以作为对特定论点的解释，也可以作为一种简单的装饰
//                            proxy.getAllPlayers().forEach(player -> builder.suggest(
//                                    player.getUsername(),
//                                    // A VelocityBrigadierMessage takes a component.
//                                    // In this case, the player's name is provided with a rainbow
//                                    // gradient created using MiniMessage.
//                                    //VelocityBrigadierMessage需要一个component。
//                                    //在这种情况下，玩家的名字会出现彩虹
//                                    //使用MiniMessage创建的渐变。
//                                    VelocityBrigadierMessage.tooltip(
//                                            MiniMessage.miniMessage().deserialize("<rainbow>" + player.getUsername())
//                                    )
//                            ));
                            // If you do not need to add a tooltip to the hint
                            // or your command is intended only for versions lower than Minecraft 1.13,
                            // you can omit adding the tooltip, since for older clients,
                            // the tooltip will not be displayed.
                            //如果不需要在提示中添加工具提示
                            //或者您的命令仅适用于低于Minecraft 1.13的版本，
                            //您可以省略添加工具提示，因为对于较旧的客户端，
                            //工具提示将不会显示。


                            builder.suggest("all");
                            return builder.buildFuture();
                        })
                        // Here the logic of the command "/test <some argument>" is executed
                        // 这里执行命令“/test<some argument>”的逻辑
                        .executes(context -> {
                            // Here you get the argument that the CommandSource has entered.
                            // You must enter exactly the name as you have named the argument
                            // and you must provide the class of the argument you expect, in this case... a String
                            //在这里，您可以得到CommandSource已输入的参数。
                            //您必须输入与命名参数相同的名称
                            //在这种情况下，你必须提供你期望的论点类别。。。a字符串
//                            String argumentProvided = context.getArgument("argument", String.class);
                            // This method will check if the given string corresponds to a
                            // player's name and if it does, it will send a message to that player
                            //此方法将检查给定的字符串是否对应于
                            //玩家的名字，如果是，它将向该玩家发送一条消息
//                            proxy.getPlayer(argumentProvided).ifPresent(player ->
//                                    player.sendMessage(Component.text("Hello!"))
//                            );
                            // Returning Command.SINGLE_SUCCESS means that the execution was successful
                            // Returning BrigadierCommand.FORWARD will send the command to the server
                            String text = ConsoleShout.getConsoleshout().getConfigManager().readConfigPrefix();
                            String s = context.getArgument("argument",String.class);
                            proxy.sendMessage(Component.text().append(LegacyComponentSerializer.legacyAmpersand().deserialize(text+s)));

                            return Command.SINGLE_SUCCESS;
                        })
                )
                .build();

        // BrigadierCommand implements Command
        return new BrigadierCommand(Alert);
    }
}
