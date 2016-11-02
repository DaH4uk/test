package ru.test.dao;

import ru.test.entities.FileEntity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashSet;
import java.util.Set;

import static ru.test.services.DBService.getStatement;

/**
 * Created by turov on 02.11.2016.
 */
public class FileEntityDAO {
    public Set<FileEntity> getAllFiles() {
        Set<FileEntity> fileEntities = new LinkedHashSet<FileEntity>();
        try {
            ResultSet result = getStatement().executeQuery("SELECT file_id, filename, url FROM my_schema.my_table;");

            while (result.next()) {
                System.out.println(result.getInt("file_id") + " "+
                        result.getString("filename") + " " +
                        result.getString("url"));


                FileEntity fileEntity = new FileEntity();
                fileEntity.setFileId(result.getInt("file_id"));
                fileEntity.setFilename(result.getString("filename"));
                fileEntity.setUrl(result.getString("url"));
                fileEntities.add(fileEntity);

            }
            result.close();

        } catch (SQLException e) {
            System.out.println("File entities getting error.");
            e.printStackTrace();
        }
        return fileEntities;
    }

    public void writeFile(FileEntity fileEntity){
        try {
            getStatement().executeUpdate("INSERT INTO my_schema.my_table (filename, url) VALUES ('"+
                    fileEntity.getFilename()+"', '"+fileEntity.getUrl()+"');");
        } catch (SQLException e) {
            System.out.println("Can't add file in DB.");
            e.printStackTrace();
        }
    }
}
