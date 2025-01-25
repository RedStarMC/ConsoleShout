package top.redstarmc.plugin.consoleshout;

import org.yaml.snakeyaml.Yaml;

import java.io.*;
import java.util.Map;

public class ConfigManager {
    public boolean initConfig(){
        File config_mkdir = new File("./plugins/ConsoleShout");
        File config_file = new File("./plugins/ConsoleShout/config.yml");
        if (!config_mkdir.exists()){
            try {
                config_mkdir.mkdir();
            } catch (Exception e) {
                ConsoleShout.getConsoleshout().getLogger().error(e.getMessage());
                return false;
            }
        }
        if(!config_file.exists()){
            try {
                config_file.createNewFile();
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(config_file))) {
                    String content = """
                            #请在下面的引号里设置前缀
                            prefix: '&f[&c全服喊话&f]'
                            """;
                    writer.write(content);
                    writer.newLine();
                }
            } catch (IOException e) {
                ConsoleShout.getConsoleshout().getLogger().error(e.getMessage());
                return false;
            }
        }
        return true;
    }

    public String readConfigPrefix() throws FileNotFoundException {
        File config_file_prefix = new File("./plugins/ConsoleShout/config.yml");
        if(!config_file_prefix.exists()){
            return null;
        }
        InputStream inputStream = new FileInputStream(config_file_prefix);
        Yaml config_prefix = new Yaml();
        Map<String, Object> data = config_prefix.load(inputStream);
        return (String) data.get("prefix");
    }
}
