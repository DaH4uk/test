package ru.test.entities;

/**
 * Автор: Туров Данил
 * Сущность файла
 * 02.11.2016.
 */
public class FileEntity {
    /**
     * ID файла
     */
    Integer fileId;
    /**
     * Имя файла
     */
    String filename;
    /**
     * URL - путь к файлу
     */
    String url;

    public Integer getFileId() {
        return fileId;
    }

    public void setFileId(Integer fileId) {
        this.fileId = fileId;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
