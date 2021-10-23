package com.robsutar.sutarshotter.files;

import com.robsutar.sutarshotter.SutarShotter;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class AbstractFile {

    protected SutarShotter main;
    private File file;
    protected FileConfiguration config;

    public AbstractFile(SutarShotter main, String filename){
        this.main = main;
        this.file = new File (main.getDataFolder(),filename);
        if (!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e){
                e.printStackTrace();
            }
        }
        this.config = YamlConfiguration.loadConfiguration(file);
    }

    public void save(){
        try {
            config.save(file);
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
