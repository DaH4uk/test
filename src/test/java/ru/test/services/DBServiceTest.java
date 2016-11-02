package ru.test.services;

import org.junit.Test;
import ru.test.dao.FileEntityDAO;
import ru.test.entities.FileEntity;

import static org.junit.Assert.*;

/**
 * Created by turov on 02.11.2016.
 */
public class DBServiceTest {
    FileEntityDAO fileEntityDAO = new FileEntityDAO();


    @Test
    public void writeFile() throws Exception {
        FileEntity fileEntity = new FileEntity();
        fileEntity.setFilename("hghfh.jsj");
        fileEntity.setUrl("/uploadedFiles");

        FileEntityDAO fileEntityDAO = new FileEntityDAO();

        fileEntityDAO.writeFile(fileEntity);
    }

    @org.junit.Test
    public void getAllFiles() throws Exception {
        fileEntityDAO.getAllFiles();
    }

}