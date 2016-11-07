package ru.test.dao;

import ru.test.entities.FileEntity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashSet;
import java.util.Set;

import static ru.test.services.DBService.getStatement;

/**
 * Автор: Туров Данил
 * Скрывает методы доступа к сущностям в БД
 * 02.11.2016.
 */
public class FileEntityDAO {
    /**
     * Получить список всех файлов
     * @return Set файлов
     */
    public Set<FileEntity> getAllFiles() {
        Set<FileEntity> fileEntities = new LinkedHashSet<FileEntity>();
        try {
            ResultSet result = getStatement().executeQuery("SELECT file_id, filename, url FROM my_schema.my_table;");   //Сам запрос к БД

            //Создаем сущности, пока они есть
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
            System.out.println("Не удалось получить сущность из БД.");
            e.printStackTrace();
        }
        return fileEntities;
    }

    /**
     * Запись сущности файла в БД
     * @param fileEntity сущность файла
     */
    public void writeFile(FileEntity fileEntity){
        try {
            getStatement().executeUpdate("INSERT INTO my_schema.my_table (filename, url) VALUES ('"+        //Сам запрос
                    fileEntity.getFilename()+"', '"+fileEntity.getUrl()+"');");
        } catch (SQLException e) {
            System.out.println("Не могу записать файл в БД.");
            e.printStackTrace();
        }
    }
}
