package ru.test.servlet;

import ru.test.dao.FileEntityDAO;
import ru.test.entities.FileEntity;
import ru.test.services.DBService;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 * Автор: Туров Данил
 * Отвечает за загрузку файлов на сервер
 * Обрабатывает PUT запросы по url: /upload
 * 01.11.2016.
 */
@WebServlet(name = "FileUploadServlet", urlPatterns = {"/upload"})
@MultipartConfig
public class FileUploadServlet extends HttpServlet {
    private FileEntityDAO fileEntityDAO = new FileEntityDAO();


    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("text/html;charset=UTF-8");
        // Create path components to save the file
        Part filePart = null;
        PrintWriter writer = null;
        try {
            filePart = request.getPart("file"); //Читаем параметр file
            writer = response.getWriter(); //Кладем write в переменную writer
        } catch (Exception e) {
            writer.println("Не могу прочитать параметр file.");
            writer.println("<br/> ERROR: " + e.getMessage());
        }
        final String fileName = getFileName(filePart);


        File file = new File(fileName);             //создаем объект файла

        try (OutputStream out = new FileOutputStream(file);//создаем байтовый поток в файл
             InputStream filecontent = filePart.getInputStream()) { //берем поток из filepart


            int read = 0; //создаем переменную для чтения
            final byte[] bytes = new byte[1024]; //создаем байтовый массив

            while ((read = filecontent.read(bytes)) != -1) { //пока входной поток не вернет значение -1 (конец файла) читаем файл
                out.write(bytes, 0, read);
            }
            writer.println("New file " + fileName + " created.");

        } catch (FileNotFoundException fne) { //естественно обрабатываем exception
            writer.println("You either did not specify a file to upload or are "
                    + "trying to upload a file to a protected or nonexistent "
                    + "location.");
            writer.println("<br/> ERROR: " + fne.getMessage());
            fne.printStackTrace();

        } catch (Exception e) {
            writer.println("Не могу создать дирректорию.");
            writer.println("<br/> ERROR: " + e.getMessage());
            e.printStackTrace();
        }

        FileEntity fileEntity = new FileEntity(); //Создаем сущность файла для БД
        fileEntity.setFilename(fileName);   //задаем поля
        fileEntity.setUrl("/upload");

        fileEntityDAO.writeFile(fileEntity); //пишем файл
    }

    /**
     * Метод для получения имени файла
     *
     * @param part объект, содержащий информацию о файле
     * @return имя файла
     */
    private String getFileName(final Part part) {
        for (String content : part.getHeader("content-disposition").split(";")) {
            if (content.trim().startsWith("filename")) {
                return content.substring(
                        content.indexOf('=') + 1).trim().replace("\"", "");
            }
        }
        return null;
    }

}
