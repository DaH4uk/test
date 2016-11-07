package ru.test.servlet;

import ru.test.dao.FileEntityDAO;
import ru.test.entities.FileEntity;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Set;

/**
 * Автор: Туров Данил
 * Отдает список файлов на фронтенд
 * Генерирует JSON файл
 * Обрабатывает GET запросы по url: /fileList
 * 02.11.2016.
 */
@WebServlet(name = "FileInfoServlet", urlPatterns = {"/fileList"})
@MultipartConfig
public class FileInfoServlet extends HttpServlet {
    FileEntityDAO fileEntityDAO = new FileEntityDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        final PrintWriter writer = response.getWriter();    //берем врайтер из response
        Set<FileEntity> fileList = fileEntityDAO.getAllFiles(); //получаем из БД список всех файлов
        StringBuilder stringBuilder = new StringBuilder();  //Создаем стрингбилдер, чтобы не переполнять память объектами string
        //Генерируем JSON
        stringBuilder.append("{\"list\": [");
        for (FileEntity o :fileList) {
            stringBuilder.append("{");
            stringBuilder.append("\"filename\": " + "\""+ o.getFilename() +"\",");
            stringBuilder.append("\"url\": " + "\""+ o.getUrl() +"\"");
            stringBuilder.append("}");
            stringBuilder.append(",");
        }
        String string = stringBuilder.toString();   //Создаем строку
        writer.write(string.substring(0,string.length()-1));    //убираем последнюю запятую
        writer.write("]}");

        writer.close();
    }
}
