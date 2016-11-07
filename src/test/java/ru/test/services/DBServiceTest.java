package ru.test.services;

import org.junit.Test;
import ru.test.dao.FileEntityDAO;
import ru.test.entities.FileEntity;

import static org.junit.Assert.*;

/**
 * Автор: Туров Данил
 * Пара тестов, для продключения в БД.
 * 02.11.2016.
 */
public class DBServiceTest {
    FileEntityDAO fileEntityDAO = new FileEntityDAO();

    /**
     * Проверка возможности записи в БД
     * @throws Exception
     */
    @Test
    public void writeFile() throws Exception {
        FileEntity fileEntity = new FileEntity();
        fileEntity.setFilename("hghfh.jsj");
        fileEntity.setUrl("/uploadedFiles");

        FileEntityDAO fileEntityDAO = new FileEntityDAO();

        fileEntityDAO.writeFile(fileEntity);
    }

    /**
     * Проверка позможности получения файлов из БД
     * @throws Exception
     */
    @Test
    public void getAllFiles() throws Exception {
        fileEntityDAO.getAllFiles();
    }

}